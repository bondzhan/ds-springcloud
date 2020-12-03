package org.bond.yy.elasticsearch.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bond.yy.elasticsearch.config.autobuild.EsMark;
import org.elasticsearch.common.Strings;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

public class EsCriteria {
	
	private static final int DEFAULT_PAGE_SIZE = 10;
	private static final int DEFAULT_PAGE_INDEX = 1;
	private static final int DEFAULT_START_INDEX = 0;
	private static final String KEYWORD_FIELD_SUFFIX = ".keyword";
	private static final String PINYIN_FIELD_SUFFIX = ".pinyin";
	private static final String FIELD_SEPERATOR = ",";
	public static final String SUBAGGREGATION_NAME = "name";
	
	private BoolQueryBuilder filterBuilder = QueryBuilders.boolQuery();	
	private Map<String, AggregationBuilder> aggBuilders = new HashMap<>();
	private AggregationBuilder currentAggregationBuilder = null;
	private SortBuilder sortBuilder = null;

	private HighlightBuilder highlightBuilder = null;
	private int pageSize = DEFAULT_PAGE_SIZE;	
	private int pageIndex = DEFAULT_PAGE_INDEX;	
	private int startIndext = DEFAULT_START_INDEX;
	private String[] includeFields = null;
	private String[] excludeFields = null;
	
	
	
	public int getStartIndext() {
		return startIndext;
	}

	public void setStartIndext(int startIndext) {
		this.startIndext = startIndext;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String[] getIncludeFields() {
		return includeFields;
	}

	public void setIncludeFields(String[] includeFields) {
		this.includeFields = includeFields;
	}

	public String[] getExcludeFields() {
		return excludeFields;
	}

	public void setExcludeFields(String[] excludeFields) {
		this.excludeFields = excludeFields;
	}

	public BoolQueryBuilder getFilterBuilder() {
		return filterBuilder;
	}

	public void setFilterBuilder(BoolQueryBuilder filterBuilder) {
		this.filterBuilder = filterBuilder;
	}

	
	public Map<String, AggregationBuilder> getAggBuilders() {
		return aggBuilders;
	}

	public void setAggBuilders(Map<String, AggregationBuilder> aggBuilders) {
		this.aggBuilders = aggBuilders;
	}

	public SortBuilder getSortBuilder() {
		return sortBuilder;
	}

	public void setSortBuilder(SortBuilder sortBuilder) {
		this.sortBuilder = sortBuilder;
	}

	
	public EsCriteria page(int pageIndex, int pageSize) {
		if(pageIndex > 0) {
			this.pageIndex = pageIndex;
		}
		if(pageSize > 0) {
			this.pageSize = pageSize;
		}
		this.startIndext = (this.pageIndex - 1) * this.pageSize;
		return this;
	}
	
	public EsCriteria page(Object pageIndex, Object pageSize) {
		int index = -1;
		int size = -1;
		
		if(pageIndex != null) {
			index = Integer.parseInt(String.valueOf(pageIndex));
		}
		
		if(pageSize != null) {
			size = Integer.parseInt(String.valueOf(pageSize));
		}
		page(index, size);
		return this;
	}
	
	public EsCriteria must(String fieldname, String fieldvalue) {
		String[] fieldValueArr = fieldvalue.split(FIELD_SEPERATOR);
		filterBuilder.must(QueryBuilders.termsQuery(fieldname, fieldValueArr));
		return this;
	}
	
	public EsCriteria must(Object fieldname, Object fieldvalue) {
		if(fieldname != null && fieldvalue != null) {
			must(String.valueOf(fieldname), String.valueOf(fieldvalue));
		}
		return this;
	}
	
	public EsCriteria must(String fieldname, int fieldvalue) {
		filterBuilder.must(QueryBuilders.termQuery(fieldname, fieldvalue));
		return this;
	}
	
	public EsCriteria mustText(String fieldname, String fieldvalue){
		filterBuilder.must(QueryBuilders.termQuery(FieldNameParser_KeyWord(fieldname), fieldvalue));
		return this;
	}
	
	public EsCriteria mustText(Object fieldname, Object fieldvalue){
		if(fieldname != null && fieldvalue != null) {
			String s_filedName = String.valueOf(fieldname);
			filterBuilder.must(QueryBuilders.termQuery(FieldNameParser_KeyWord(s_filedName), fieldvalue));
		}
		return this;
	}
	
	public EsCriteria mustText(String fieldname, int fieldvalue){
		filterBuilder.must(QueryBuilders.termQuery(FieldNameParser_KeyWord(fieldname), fieldvalue));
		return this;
	}

	public EsCriteria exist(String fieldname) {
		filterBuilder.must(QueryBuilders.existsQuery(fieldname));
		return this;
	}
	
	public EsCriteria mustNot(String fieldname, String fieldvalue) {
		filterBuilder.mustNot(QueryBuilders.termQuery(fieldname, fieldvalue));
		return this;
	}
	
	public EsCriteria mustNot(String fieldname, int fieldvalue) {
		filterBuilder.mustNot(QueryBuilders.termQuery(fieldname, fieldvalue));
		return this;
	}
	
	public EsCriteria mustNotText(String fieldname, String fieldvalue) {
		filterBuilder.mustNot(QueryBuilders.termQuery(FieldNameParser_KeyWord(fieldname), fieldvalue));
		return this;
	}
	
	public EsCriteria mustNotText(String fieldname, int fieldvalue) {
		filterBuilder.mustNot(QueryBuilders.termQuery(FieldNameParser_KeyWord(fieldname), fieldvalue));
		return this;
	}
	
	public EsCriteria range(String fromFieldName, String toFieldName,Object fromValue, Object toValue) {
		rangeFrom(fromFieldName, fromValue);
		rangeTo(toFieldName, toValue);
        return this;
	}
	
	public EsCriteria range(String fieldName, Object fromValue, Object toValue) {
		rangeFrom(fieldName, fromValue);
		rangeTo(fieldName, toValue);
        return this;
	}
	
	public EsCriteria rangeFrom(String fromFieldName, Object fromValue) {
		RangeQueryBuilder rangeQuery = null;
		if(fromValue != null && fromFieldName != null) {
			rangeQuery = QueryBuilders.rangeQuery(fromFieldName).from(fromValue);
			filterBuilder.filter(rangeQuery);
        }
		return this;
	}
	
	public EsCriteria rangeTo(String toFieldName, Object toValue) {
		RangeQueryBuilder rangeQuery = null;
		if(toValue != null && toFieldName != null) {
			rangeQuery = QueryBuilders.rangeQuery(toFieldName).to(toValue);
			filterBuilder.filter(rangeQuery);
        }
		return this;
	}
	
	public EsCriteria keyWord(String keyWord, String...fieldNames) {
		if(!Strings.isNullOrEmpty(keyWord)) {
			QueryBuilder multyMatch = QueryBuilders.multiMatchQuery(keyWord, FieldNameParser_PinYin(fieldNames));
			QueryBuilder preMultyMatch = QueryBuilders.multiMatchQuery(keyWord, FieldNameParser_PinYin(fieldNames));
			filterBuilder.should(multyMatch).should(preMultyMatch).minimumShouldMatch(1);
		}
		return this;
	}
	
	public EsCriteria keyWord(Object keyWord, Object fieldNames) {
		if(keyWord != null && fieldNames != null) {
			keyWord(String.valueOf(keyWord), String.valueOf(fieldNames).split(FIELD_SEPERATOR));
		}
		return this;
	}
	
	public EsCriteria _source(String[] includeFields, String[] excludeFields) {
		this.includeFields = includeFields;
		this.excludeFields = excludeFields;
		return this;
	}
	
	public EsCriteria _sourceInclude(String includeFieldsString) {
		String[] includeFields = includeFieldsString.split(FIELD_SEPERATOR);
		_source(includeFields, null);
		return this;
	}
	
	public EsCriteria _sourceInclude(Object includeFieldsString) {
		if(includeFieldsString != null) {
			String s_includeFields = String.valueOf(includeFieldsString);
			_sourceInclude(s_includeFields);
		}
		return this;
	}
	
	public EsCriteria sortBy(String fieldName, SortOrder order) {
		sortBuilder = SortBuilders.fieldSort(fieldName).order(order);
		return this;
	}
	
	public EsCriteria sortByScore(SortOrder order) {
		sortBuilder = SortBuilders.scoreSort().order(order);
		return this;
	}
	
	private static String FieldNameParser_KeyWord(String origin) {
		if(Strings.isNullOrEmpty(origin)) {
			return null;
		}
		if(origin.endsWith(KEYWORD_FIELD_SUFFIX)) {
			return origin;
		}
		return origin + KEYWORD_FIELD_SUFFIX;
	}

	private static String[] FieldNameParser_PinYin(String... fieldNames) {
		List<String> list = new ArrayList<>();

		for(String origin:fieldNames) {
			if (Strings.isNullOrEmpty(origin)) {
				continue;
			}
			if (origin.endsWith(PINYIN_FIELD_SUFFIX)) {
				list.add(origin);
			}
			list.add(origin + PINYIN_FIELD_SUFFIX);
		}
		String[] ary = (String[]) list.toArray(new String[list.size()]);
		return ary;
	}
	
	public Integer getTotalPage(int totalHits) {
		Integer total = new Integer(0);
		if(totalHits < 0) return total;
		
		if(totalHits % pageSize == 0) {
			total = totalHits/pageSize;
		}else {
			total = totalHits/pageSize + 1;
		}
		
		return total;
	}
	
	public EsCriteria termAggregate(String aggName, String fieldName) {		
		if(aggName != null && fieldName != null) {
			AggregationBuilder aggBuilder = 
						AggregationBuilders.terms(aggName)
										   .field(fieldName)
										   .order(BucketOrder.count(false))
										   .size(10);
			this.aggBuilders.put(aggName, aggBuilder);
			currentAggregationBuilder = aggBuilder;
		}
		return this;
	}
	
	public EsCriteria subAggregate(String fieldName) {
		if(currentAggregationBuilder != null) {
			currentAggregationBuilder.subAggregation(
					AggregationBuilders.terms(EsCriteria.SUBAGGREGATION_NAME).field(FieldNameParser_KeyWord(fieldName)));
		}
		return this;
	}

	public EsCriteria highlightFields(String... fieldNames){
		this.highlightBuilder = new HighlightBuilder();
		for(String fieldName : fieldNames){
			HighlightBuilder.Field hightField = new HighlightBuilder.Field(fieldName);
			highlightBuilder.field(hightField);
		}
		return this;
	}

	public HighlightBuilder getHighlightBuilder() {
		return highlightBuilder;
	}

	public void setHighlightBuilder(HighlightBuilder highlightBuilder) {
		this.highlightBuilder = highlightBuilder;
	}
	
	public EsCriteria autoBuild(Object param) throws Exception{
		Class<?> cl = param.getClass();
		for(Field field :cl.getDeclaredFields()) {
			EsMark mark = field.getAnnotation(EsMark.class);
			if(mark != null) {
				field.setAccessible(true);
				Object fieldValue = field.get(param);
				mark.policy().build(this, mark, fieldValue);
			}
		}
		
		return this;
	}
	

	
}
