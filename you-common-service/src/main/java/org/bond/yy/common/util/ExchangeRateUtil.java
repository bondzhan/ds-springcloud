package org.bond.yy.common.util;

import java.math.BigDecimal;

import org.bond.yy.common.constant.Xyb2bConstant;

/**
 * @author lchm
 * @Description: 汇率计算工具类
 * @ClassName: ExchangeRateUtil
 * @date 2018-08-31 11:52
 */
public class ExchangeRateUtil {

    private ExchangeRateUtil() {
    }

    /**
     * 根据汇率计算转换成RMB的价格(单位:分 保留两位小数)
     *
     * @param localPrice
     * @param exchangeRate
     * @return java.math.BigDecimal
     * @throws Exception
     * @Title: getCNYPrice
     * @Description:
     * @Author lchm
     * @date 2018/8/31
     */
    public static BigDecimal getCNYPrice(Integer localPrice, Integer exchangeRate) {
        if (null == localPrice || null == exchangeRate) {
            return null;
        }
        return getCNYPrice(new BigDecimal(localPrice),exchangeRate);
    }

    /**
    * 根据汇率计算转换成RMB的价格(单位:分 保留两位小数)
    * @Title: getCNYPrice
    * @Description:
    * @param local
    * @param exchangeRate
    * @return java.math.BigDecimal
    * @Author lchm
    * @date 2018/9/17
    * @throws Exception
    */
    public static BigDecimal getCNYPrice(BigDecimal local, Integer exchangeRate) {
        if (null == local || null == exchangeRate) {
            return null;
        }
        BigDecimal rate = new BigDecimal(exchangeRate);
        return local.multiply(rate).divide(Xyb2bConstant.EXCHANGE_RATE_UNIT).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
