package org.bond.yy.common.util;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FreightUtils {
	Logger log = LoggerFactory.getLogger(getClass());
	static BigDecimal ZERO = BigDecimal.ZERO;
	/**
	 * 
	* @Title: calculateCarriage 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	//* @param @param valuateType 计价方式 1，按件计价 2，按重量计价 3按体积
	* @param @param buyNum 
	* @param @param singleMeasure 单个量 如果是按计件 则为1
	* @param @param firstCompany 首量
	* @param @param firstPrice 首费
	* @param @param secondCompany 续量
	* @param @param secondPrice 续费
	* @param @return  参数说明 
	* @return BigDecimal    返回类型 分 
	* @author sup
	* @throws
	 */
	public static BigDecimal calculateCarriage(int buyNum, BigDecimal singleMeasure, BigDecimal firstCompany, BigDecimal firstPrice, BigDecimal secondCompany, BigDecimal secondPrice) {
		if (firstCompany.compareTo(ZERO) == 0 && firstPrice.compareTo(ZERO) == 0 && secondCompany.compareTo(ZERO) == 0 && secondPrice.compareTo(ZERO) == 0) {
			return ZERO;
		}
		BigDecimal allMeasure = singleMeasure.multiply(new BigDecimal(buyNum));
		if(allMeasure.compareTo(firstCompany) <= 0){
			return firstPrice;
		}else{
			// ((allMeasure-c1)/c2取整进1)*p2 + p1
			return allMeasure.subtract(firstCompany).divide(secondCompany).setScale(0, BigDecimal.ROUND_UP).multiply(secondPrice).add(firstPrice).setScale(2, BigDecimal.ROUND_UP);
		}
	}
}
