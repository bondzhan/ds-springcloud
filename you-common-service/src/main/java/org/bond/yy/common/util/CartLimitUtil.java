package org.bond.yy.common.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bond.yy.common.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;

/** 
* @ClassName: CartLimitUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author X.W
* @date 2018年12月13日 上午10:01:50 
*  
*/
public class CartLimitUtil {
	private static Logger logger = LoggerFactory.getLogger(CartLimitUtil.class);
	/** 
	* @Title: setOrderCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @param fdeliveryCardid
	* @param @param fmobile
	* @param @param fgoodsType
	* @param @param deliveryType
	* @param @return  参数说明 
	* @return Map<String,Long>    返回类型 
	* @author X.W
	* @throws 
	*/
	public static Map<String, Long> setOrderCount(Map<String, Long> map, String fdeliveryCardid, String fmobile,
			Integer fgoodsType, Integer deliveryType) {
		Map<String, Long> result = new HashMap<>();
		Long orderCount1 = 0l;
		Long orderCount2 = 0l;
		if (map.containsKey(fmobile + "_" + fgoodsType)) {
			orderCount1 = map.get(fmobile + "_" + fgoodsType) + 1;

		} else {
			orderCount1 = 1l;
		}
		if (map.containsKey(fdeliveryCardid + "_" + fgoodsType)) {
			orderCount2 = map.get(fdeliveryCardid + "_" + fgoodsType) + 1;

		} else {
			orderCount2 = 1l;
		}
		map.put(fdeliveryCardid + "_" + fgoodsType, orderCount2);
		map.put(fmobile + "_" + fgoodsType, orderCount1);
		result.put("fmobile", orderCount1);
		result.put("fdeliveryCardid", orderCount2);
		return result;
	}
	
	/** 
	* @Title: setOrderAmount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @param fdeliveryCardid
	* @param @param fmobile
	* @param @param amount
	* @param @param fgoodsType
	* @param @param deliveryType
	* @param @return  参数说明 
	* @return Map<String,Long>    返回类型 
	* @author X.W
	* @throws 
	*/
	public static Map<String, Long> setOrderAmount(Map<String, Long> map, String fdeliveryCardid, String fmobile,
			Long amount, Integer fgoodsType, Integer deliveryType) {
		Map<String, Long> result = new HashMap<>();
		Long amount1 = 0l;
		Long amount2 = 0l;
		if (map.containsKey(fmobile + "_Amount_" + fgoodsType)) {
			amount1 = map.get(fmobile + "_Amount_" + fgoodsType) + amount;
		} else {
			amount1 = amount;
		}
		if (map.containsKey(fdeliveryCardid + "_Amount_" + fgoodsType)) {
			amount2 = map.get(fdeliveryCardid + "_Amount_" + fgoodsType) + amount;
		} else {
			amount2 = amount;
		}
		map.put(fmobile + "_Amount_" + fgoodsType, amount1);
		map.put(fdeliveryCardid + "_Amount_" + fgoodsType, amount2);
		result.put("fmobile", amount1);
		result.put("fdeliveryCardid", amount2);
		return result;
	}

	/** 
	* @Title: getFdeliveryDate 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orderCycle
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author X.W
	* @throws 
	*/
	public static String getFdeliveryDate(String orderCycle){
		if(!Strings.isNullOrEmpty(orderCycle)){
			if(orderCycle.equals("1")){
				return DateUtil.getDate(new Date());
			}else if(orderCycle.equals("2")){
				return DateUtil.getCurrentFirstDayByWeek();
			}else if(orderCycle.equals("3")){
				return DateUtil.getCurrentFirstDayByMonth();
			}else if(orderCycle.equals("4")){
				return DateUtil.getCurrentFirstDayByYear();
			}
		}
		return "";
	}
	
	/** 
	* @Title: getChOfDate 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param flag
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author X.W
	* @throws 
	*/
	public static String getChOfDate(String flag){
		if(flag == "1"){
			return "天";
		}else if(flag == "2"){
			return "周";
		}else if(flag == "3"){
			return "月";
		}else if(flag == "4"){
			return "年";
		}
		return null;
	}
	
	
	/** 
	* @Title: orderLimitCondition 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orderFlag
	* @param @param fgoodsType
	* @param @param consigneeMap
	* @param @param fmobile
	* @param @param buyNum
	* @param @param fdeliveryCardid
	* @param @param fgoodsPrice
	* @param @return  参数说明 
	* @return Map<String,Object>    返回类型 
	* @author X.W
	* @throws 
	*/
	public static Map<String, Object> orderLimitCondition(String orderFlag, Integer fgoodsType, HashMap<String, Long> consigneeMap, String fmobile, 
			Integer buyNum, String fdeliveryCardid, Long fgoodsPrice){
		Map<String, Object> map = new HashMap<>();
		if(!Strings.isNullOrEmpty(orderFlag) ){
			Map<String, Long> countMap = new HashMap<>();
			Map<String, Long> amountMap = new HashMap<>();
			//计算收货人本次下单的单数，收货人本次下单的限额
			countMap = setOrderCount(consigneeMap, fdeliveryCardid, fmobile, fgoodsType, Integer.parseInt(orderFlag));
			amountMap = setOrderAmount(consigneeMap, fdeliveryCardid, fmobile, fgoodsPrice * buyNum, fgoodsType, Integer.parseInt(orderFlag));
			map.put("orderCount", countMap);
			map.put("orderAmount", amountMap);
		}
		return map;
	}
	
	//检查是否在设置的价格区间中
	/** 
	* @Title: checkBuyNumRange 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param priceList
	* @param @param buyNum
	* @param @return  参数说明 
	* @return boolean    返回类型 
	* @author X.W
	* @throws 
	*/
	public static boolean checkBuyNumRange(List<Map<String, Object>> priceList, int buyNum){
		boolean flag = false;
		for(Map<String, Object> rangeMap : priceList){
			Integer rangeEnd = (Integer) rangeMap.get("frangeEnd");
			Integer rangeStart = (Integer) rangeMap.get("frangeStart");
			if(rangeEnd != null){
				if(rangeEnd == -1 && buyNum >= rangeStart){
					flag = true;
				}else if(buyNum >= rangeStart && buyNum <= rangeEnd){
					flag = true ;
				}
			}else{
				if(buyNum >= rangeStart){
					flag = true;
				}
			}
			if(flag == true){
				break;
			}
		}
		return flag ;
	}
	//检查是否在设置的价格区间中
		/** 
		* @Title: checkBuyNumRange 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param rangeStartKey
		* @param @param rangeEndKey
		* @param @param priceList
		* @param @param buyNum
		* @param @return  参数说明 
		* @return boolean    返回类型 
		* @author X.W
		* @throws 
		*/
		public static boolean checkBuyNumRange(String rangeStartKey, String rangeEndKey, List<Map<String, Object>> priceList, int buyNum){
			boolean flag = false;
			for(Map<String, Object> rangeMap : priceList){
				Integer rangeEnd = (Integer) rangeMap.get(rangeEndKey);
				Integer rangeStart = (Integer) rangeMap.get(rangeStartKey);
				if(rangeEnd != null){
					if(rangeEnd == -1 && buyNum >= rangeStart){
						flag = true;
					}else if(buyNum >= rangeStart && buyNum <= rangeEnd){
						flag = true ;
					}
				}else{
					if(buyNum >= rangeStart){
						flag = true;
					}
				}
				if(flag == true){
					break;
				}
			}
			return flag ;
		}
	
	
	
	/** 
	* @Title: orderLimitRule 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param yearCount
	* @param @param yearLimit
	* @param @param itemLimit
	* @param @param countLimit
	* @param @param currentCount
	* @param @return  参数说明 
	* @return boolean    返回类型 
	* @author X.W
	* @throws 
	*/
	public static boolean orderLimitRule(Long yearCount, Long yearLimit, Long itemLimit, Integer countLimit, Integer currentCount){
		boolean flag = false;
		int count = (int) (yearLimit/itemLimit);
		if( countLimit + currentCount + yearCount <= count){
			flag = true;
		}
		return flag;
	}

	
	/**
	 * 特殊微仓发货切换发货价
	* @Title: exchangeSalePrice 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param microOrderInfo
	* @param @param timeParam
	* @param @param nowTm
	* @param @return  参数说明 
	* @return boolean    返回类型 
	* @author sup
	* @throws
	 */
	public static boolean exchangeSalePrice(Map<String, Object> microOrderInfo ,String timeParam, String nowTm, int buyNum){
		logger.info("售价维护数据："+microOrderInfo.get("Fapi_price_date"));
		JSONArray jsonArr = JSON.parseArray(String.valueOf(microOrderInfo.get("Fapi_price_date")));
		boolean flag = false;
		//Integer maxNo = (Integer) microOrderInfo.get("Frange_end");
		for (int k = 0; k < jsonArr.size(); k++) {
			JSONObject obj = (JSONObject)jsonArr.get(k);//yyyy-MM-dd HH:mm:ss
			String startDate = obj.getString("fpriceStartDate");
			String endDate = obj.getString("fpriceEndDate");
			logger.info("售价及有效起止时间："+obj.get("fapiSalePrice")+"--"+startDate+"-"+endDate);
			flag = DateUtil.dateIfbetweenDates(timeParam, startDate, endDate);
			logger.info("是否符合设置发货价1:"+flag);
			if(flag){
				JSONArray priceRange = JSONArray.parseArray(obj.getString("fapiSalePrice"));
				BigDecimal sellPri = getMicroSellPrice(priceRange, buyNum);
				logger.info("发货价1:"+sellPri);
				microOrderInfo.put("Fsku_price", sellPri.longValue());
				break;
			}
		}
		if(!flag){
			for (int k = 0; k < jsonArr.size(); k++) {
				JSONObject obj = (JSONObject)jsonArr.get(k);//yyyy-MM-dd HH:mm:ss
				String startDate = obj.getString("fpriceStartDate");
				String endDate = obj.getString("fpriceEndDate");
				flag = DateUtil.dateIfbetweenDates(nowTm, startDate, endDate);
				logger.info("是否符合设置发货价2:"+flag);
				if(flag){
					JSONArray priceRange = JSONArray.parseArray(obj.getString("fapiSalePrice"));
					BigDecimal sellPri = getMicroSellPrice(priceRange, buyNum);
					logger.info("发货价2:"+sellPri);
					microOrderInfo.put("Fsku_price", sellPri.longValue());
					break;
				}
			}
		}
		if(!flag){
			logger.info("无匹配的设置发货价:"+flag);
			return false;
		}
		return true;
	}
	
	public static BigDecimal getMicroSellPrice(JSONArray rangePriceList,int buyNum){
		BigDecimal amount = new BigDecimal(0);
		for (int i = 0; i < rangePriceList.size(); i++) {
			JSONObject rg = (JSONObject)rangePriceList.get(i);
			int rangeSt = rg.getIntValue("frangeStart");
			if(rg.containsKey("frangeEnd") && null != rg.get("frangeEnd")){
				int rangeEd = rg.getIntValue("frangeEnd");
				if (buyNum <= rangeEd && buyNum >= rangeSt) {
					amount = rg.getBigDecimal("frangePrice");
					break;
				}/*else if (maxNo == -1 && buyNum > rangeEd) { // 起拍无上限时取最后区间
					amount = rg.getBigDecimal("frangePrice");
					break;
				}*/
			}else {
				if (buyNum >= rangeSt) {
					amount = rg.getBigDecimal("frangePrice");
					break;
				}
			}
		}
		
		return amount;
	}
	public static Map checkMaxRange(List<Map<String, Object>> priceList){
		boolean flag = false;
		int maxNo = 0;
		int minNo = Integer.MAX_VALUE;
		for(Map<String, Object> rangeMap : priceList){
			Integer rangeEnd = (Integer) rangeMap.get("frangeEnd");
			Integer rangeStart = (Integer) rangeMap.get("frangeStart");
			if(rangeEnd != null){
					minNo = Integer.min(rangeStart,minNo);
					maxNo = Integer.max(rangeEnd,maxNo);

			}else{
				minNo = Integer.min(rangeStart,minNo);
				maxNo = -1;
			}

		}
        Map retMap = new HashMap();
		retMap.put("minNo",String.valueOf(minNo));
		retMap.put("maxNo",String.valueOf(maxNo));
		return retMap;
	}
	public static Map getMaxRange(List<Map<String, Object>> priceList){
		boolean flag = false;
		int maxNo = 0;
		int minNo = Integer.MAX_VALUE;
		int count = 0;
		for(Map<String, Object> rangeMap : priceList){
			Integer rangeEnd = (Integer) rangeMap.get("frangeEnd");
			Integer rangeStart = (Integer) rangeMap.get("frangeStart");
			if(rangeEnd != null){
				minNo = Integer.min(rangeStart,minNo);
				maxNo = Integer.max(rangeEnd,maxNo);

			}else{
				minNo = Integer.min(rangeStart,minNo);
				maxNo = -1;
			}

		}
		Map retMap = new HashMap();
		retMap.put("minNo",String.valueOf(minNo));
		retMap.put("maxNo",String.valueOf(maxNo));
		return retMap;
	}
}
