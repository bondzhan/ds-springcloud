package org.bond.yy.elasticsearch.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.utils.DateUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Component;

@Component
public class EsManager {
	
	private RestHighLevelClient client;
	
	
	
	public EsManager(RestHighLevelClient client) {
		this.client = client;
	}
	
	
	private SearchResponse queryForResponse(EsCriteria criteria) throws Exception{
		
		SearchRequest searchRequest = new SearchRequest(); 
		
    	SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    	searchSourceBuilder.query(criteria.getFilterBuilder());
    	if(criteria.getSortBuilder() != null) {
    		searchSourceBuilder.sort(criteria.getSortBuilder());
    	}
    	searchSourceBuilder.from(criteria.getStartIndext()).size(criteria.getPageSize()); 
    	searchSourceBuilder.fetchSource(criteria.getIncludeFields(), criteria.getExcludeFields());
    	criteria.getAggBuilders().forEach((Key, value) -> searchSourceBuilder.aggregation(value));
		searchSourceBuilder.highlighter(criteria.getHighlightBuilder());
    	searchRequest.source(searchSourceBuilder);
    	SearchResponse sResponse = client.search(searchRequest, RequestOptions.DEFAULT);	
		return sResponse;
	}
	
	
	private List<Map<String, Object>> getHitList(SearchResponse sResponse){
		List<Map<String, Object>> resList = new LinkedList<>();
		try {
    		for(SearchHit hit : sResponse.getHits().getHits()) {
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.putAll(convertUpperUndercsoreToLowerCamel(hit.getSourceAsMap()));
				resultMap.putAll(getHighlightField(hit));
    			resList.add(resultMap);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
		return resList;
		
	}


	private Map<String,Object> convertUpperUndercsoreToLowerCamel(Map<String,Object> resourceMap){
	    Map<String,Object> resultMap =  new HashMap<>();
	    for(Map.Entry<String,Object> entry:resourceMap.entrySet()){
            resultMap.put(convertUpperUndercsoreToLowerCamel(entry.getKey()),entry.getValue());
        }
        return resultMap;
    }

    private String convertUpperUndercsoreToLowerCamel(String key){
	    StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile("_(\\w)");
			Matcher m = p.matcher(key);
        while (m.find()){
            m.appendReplacement(sb,m.group(1).toUpperCase());
        }
        m.appendTail(sb);
        return sb.toString();
    }

	private Map<String,String> getHighlightField(SearchHit hit){
		Map<String,String> highlightMap = new HashMap<>();
		Map<String, HighlightField> highlightFields = hit.getHighlightFields();
		for(Map.Entry<String, HighlightField> entry : highlightFields.entrySet()){
			HighlightField highlight = entry.getValue();
			Text[] fragments = highlight.fragments();
			String fragmentString = fragments[0].string();
			highlightMap.put(entry.getKey(),fragmentString);
		}
		return highlightMap;
	}
	
	private Map<String, Object> getBaseInfoMap(SearchResponse sResponse, EsCriteria criteria){
		Map<String, Object> baseInfo = new HashMap<>();
		if(sResponse == null) return baseInfo;
		baseInfo.put("totalHits", sResponse.getHits().getTotalHits());
		baseInfo.put("currentDate", DateUtils.formatDate(new Date(), "yyyy/MM/dd HH:mm:ss"));
		baseInfo.put("pageSize", criteria.getPageSize());
		baseInfo.put("pageIndex", criteria.getPageIndex());
		baseInfo.put("totalPage", criteria.getTotalPage(
				Integer.parseInt(String.valueOf(baseInfo.get("totalHits")))));
		return baseInfo;
	}
	
	public List<Map<String, Object>> queryForList(EsCriteria criteria){
    	try {
    		SearchResponse sResponse = queryForResponse(criteria);
    		List<Map<String, Object>> resultList = getHitList(sResponse);
    		return resultList;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
		return null;
	}
	
	
	public Map<String, Object> queryWithBaseInfo(EsCriteria criteria) throws Exception{
		
			Map<String, Object> resultMap = new HashMap<>();
    		SearchResponse sResponse = queryForResponse(criteria);
    		resultMap.put("baseInfoMap", getBaseInfoMap(sResponse, criteria));
    		resultMap.put("resultList", getHitList(sResponse));
    		return resultMap;
    	
	}
	
	public Map<String, Object> queryWithAggregation(EsCriteria criteria) throws Exception{
		
			Map<String, Object> resultMap = new HashMap<>();
			SearchResponse sResponse = queryForResponse(criteria);
			resultMap.put("resultList", getHitList(sResponse));
			
			Map<String, Object> aggregationMap = new HashMap<>();
			Aggregations aggregations = sResponse.getAggregations();
			
			Map<String, AggregationBuilder> aggBuilderMap = criteria.getAggBuilders();
			if(!aggBuilderMap.isEmpty()) {
				aggBuilderMap.forEach((key, value)->
						aggregationMap.put(key, getElasticGroupInfo(aggregations, key)));
			}
			resultMap.put("baseInfoMap", getBaseInfoMap(sResponse, criteria));
			resultMap.put("aggregation", aggregationMap);
			return resultMap;

	}
	
	private List<Map<String, Object>> getElasticGroupInfo(Aggregations aggs, String groupListName) {
		List<Map<String, Object>> groupList = new ArrayList<>();
		Terms terms = aggs.get(groupListName);
		Collection<? extends Bucket> buckets = terms.getBuckets();
		Map<String, Object> groupInfo;
		for (Terms.Bucket bucket : buckets) {
			long key = (long) bucket.getKey();
			Terms subTearms = bucket.getAggregations().get(EsCriteria.SUBAGGREGATION_NAME);
			Collection<? extends Bucket> subBuckets = subTearms.getBuckets();
			for (Terms.Bucket subBucket : subBuckets) {
				groupInfo = new HashMap<>();
				String subKey = (String) subBucket.getKey();
				groupInfo.put("id", key);
				groupInfo.put("name", subKey);
				groupList.add(groupInfo);
			}
		}
		return groupList;
	}
	
}
