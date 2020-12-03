package org.bond.yy.common.constant;

import java.math.BigDecimal;

public class Xyb2bConstant {

    /**
     *默认分页参数开始页
     */
    public static final int DEFAULT_PAGE_INDEX = 1;

    /**
     *默认分页参数页大小
     */
    public static final int DEFAULT_PAGE_SIZE = 10;


    /**
     * token有效时间：24小时
     */
    public static final long TOKEN_EXPIRATION = 60 * 60 * 1000 * 24;
    
    /**
     * APPtoken有效时间：7天
     */
    public static final long APP_TOKEN_EXPIRATION = 60 * 60 * 1000 * 24 * 7;
    
	
	/**
	 * 自动登录的token有效时间
	 */
	public static final long TOKEN_AUTO_LOGIN_EXPIRATION = 60 * 60 * 1000 * 24 * 30;
	
	/**
	 * 用户注册后免认证时间，单位为天
	 */
	public static final long VERIFY_EXPIRATION = 30;
	//国家馆，banner,主题,入驻分销商缓存
	public static final String REDIS_AREA_KEY = "sku:area";
	public static final String REDIS_BANNER_KEY = "sku:banner";
	public static final String REDIS_TOPIC_KEY = "sku:topic";
	public static final String REDIS_BRAND_KEY = "sku:hotBrand";
	public static final String REDIS_CONT_KEY = "sku:cont";
	public static final String REDIS_FLOOR_KEY = "sku:floor";
	

    /**
     * 导入文件大小限制：2M
     */
    public static final Integer IMPORT_FILE_MAX_SIZE = 1024 * 1024 * 2;


    /**
     * token有效时间：1小时50分钟(110分钟)
     */
    public static final long COMPONENT_ACCESS_TOKEN_EXPIRATION = 110 * 60  ;


    /**
     * 短信签名
     */
    public static final String XYB2B_SMS_PREFIX = "【行云全球汇】";//行云服务平台的短信【行云易购】


    /**
     * 汇率转换单位 JPY -> CNY
     */
    public static final BigDecimal EXCHANGE_RATE_UNIT = new BigDecimal(10000);



    /**
     * excel数字类型数据后缀
     */
    public static final String EXCEL_NUMBER_SUFFIX = ".0";



	public static final String CARRIAGE_TEMPLATE_REDIS_KEY = "carriageTemplate";

    /**
     * 库存不足
     */

    public static final String MORE_THAN_STOCK = "MORE_THAN_STOCK";

    /**
     * 商品总金额超出海关清关限制
     */

    public static final String CUSTOMS_LIMIT_AMOUNT = "CUSTOMS_LIMIT_AMOUNT";

    /**
     * 小于最小起拍数
     */


    public static final String LESS_THAN_FMINNO = "LESS_THAN_FMINNO";

    /**
     * 大于最大起拍数
     */


    public static final String MORE_THAN_FMAXNO = "MORE_THAN_FMAXNO";

    /**
     * 不在价格区间范围内
     */


    public static final String OUT_BUYNUM_RANGE = "OUT_BUYNUM_RANGE";


    /**
     * 批次未找到
     */
    public static final String BATCH_NOT_FOUND = "BATCH_NOT_FOUND";

    /**
     * 批次不一致
     */
    public static final String BATCH_NOT_SAME = "BATCH_NOT_SAME";


    /**
     * 批次已下架
     */
    public static final String BATCH_SOLD_OUT = "BATCH_SOLD_OUT";

    /**
     * 批次有效期过期
     */

    public static final String BATCH_VALIDITY_OVERDUE = "BATCH_VALIDITY_OVERDUE";


    /**
     * 批次商品保质期过期
     */

    public static final String BATCH_QUALITY_OVERDUE = "BATCH_QUALITY_OVERDUE";

    /**
     * 下单限制redis key前缀
     */
    public static final String PLACE_AN_ORDER_KEY_PREFIX = "placeAnOrder:";

    /**
     * 用户禁用状态
     */
    public static final String USER_FORBIDDEN = "USER_FORBIDDEN";

    /**
     * 用户冻结状态
     */
    public static final String USER_FREEZE = "USER_FREEZE";

    /**
     * 用户尚未认证
     */
    public static final String NOT_VERIFY = "NOT_VERIFY";
    
    public static final String MOBILE_AUTH_NUM = "MOBILE_AUTH_NUM";
    
	/**
	 * 费率设置redis key前缀
	 */
	public static final String FEE_RATE_KEY = "feeRatePercent";

    public static final Integer DEFAULT_USER_LEVEL = 1;

    public static final String ORDER_SETTING_KEY_PREFIX = "orderSetting:";
    
    /** 
	* 到期时间和单号消息队列key值(微仓订单)
	*/ 
	public static final String MICRO_EXPIRE_REDIS_KEY="micro:expire";
	
	/** 
	* 到期时间和单号消息队列key值
	*/ 
	public static final String ORDER_EXPIRE_REDIS_KEY="order:expire";

    //app首页
    public static final String REDIS_APP_AREA_KEY = "app:area";
    public static final String REDIS_APP_BANNER_KEY = "app:banner";
    public static final String REDIS_APP_TOPIC_KEY = "app:topic";
    public static final String REDIS_APP_FLOOR_KEY = "app:floor";
    public static final String REDIS_APP_CATEGORY_KEY = "app:category";

    /**
     *未读消息数量
     */
    public static final String REDIS_APP_UNREAD_MSG_KEY = "unreadMsg";

    /**
     * sku 未上架
     */
    public static final String SKU_NOT_PUTWAY = "SKU_NOT_PUTWAY";

    /**
     * sku 已删除
     */
    public static final String SKU_ALREADY_DELETE = "SKU_ALREADY_DELETE";

    /**
     * sku 已置于回收状态
     */
    public static final String SKU_ALREADY_RECYCLE = "SKU_ALREADY_RECYCLE";

    /**
     * sku 为草稿状态
     */
    public static final String SKU_ALREADY_DRAFT = "SKU_ALREADY_DRAFT";

    /**
     * sku 需要上传身份证正反面
     */
    public static final String SKU_NEED_IDCARE = "SKU_NEED_IDCARE";

    /**
     * redis配置年度限额黑名单key
     */
    public static final String REDIS_YEAR_AMOUNT_LIMIT_BLACKLIST_KEY = "yearAmountLimitBlacklist";


    /**
     * 加入进货单fskuId参数异常
     */
    public static final String SHOPCAR_ADD_NOT_FOUND_SKU = "SHOPCAR_ADD_NOT_FOUND_SKU";

    /**
     * 加入进货单fbatchId参数异常
     */
    public static final String SHOPCAR_ADD_NOT_FOUND_BATCH = "SHOPCAR_ADD_NOT_FOUND_BATCH";

    /**
     * 加入进货单fuid参数异常
     */
    public static final String SHOPCAR_NOT_FOUND_USERID = "SHOPCAR_NOT_FOUND_USERID";

    /**
     * 加入进货单fbuyNum购买数量 参数不存在
     */
    public static final String SHOPCAR_NOT_FOUND_BUYNUM= "SHOPCAR_NOT_FOUND_BUYNUM";

    /**
     * 加入进货单fbuyNum购买数量 小于等于0
     */

    public static final String SHOPCAR_BUYNUM_NEGATIVE_OR_ZERO = "SHOPCAR_BUYNUM_NEGATIVE_OR_ZERO";

    /**
     * 加入进货单失败
     */

    public static final String SHOPCAR_ADD_FAIL = "SHOPCAR_ADD_FAIL";

    /**
     * 加入进货单成功
     */

    public static final String SHOPCAR_ADD_SUCCESS = "SHOPCAR_ADD_SUCCESS";

    /**
     *已认证
     */
    public static final Integer HAVING_VERIFIED = 2;

    /**
     * 未认证
     */
    public static final Integer UN_VERIFIED = 3;

    /**
     * 个人认证
     */
    public static final Integer PERSONAL_VERIFY = 0;

    /**
     *企业认证
     */
    public static final Integer COMPANY_VERIFIED = 1;

    /**
     * 订单到期时间
     */
    public static final int ORDER_EXPIRATION_TIME=6;

    /**
     * 消息已读
     */
    public static final int NEWS_HAVING_RED = 1;

    /**
     * 消息未删除
     */
    public static final int NEWS_UNDELETED = 0;


    /**
     * 收货地址批次不支持发货
     */

    public static final String BATCH_REGION_NOT_DELIVERY = "BATCH_REGION_NOT_DELIVERY";

    /**
     * 单品限额提示
     */
    public static final String ITEM_LIMIT_REMIND = "商品总金额超出海关清关${itemLimit}元法规限制";

    /**
     * 年度限额提示
     */
    public static final String ORDER_YEAR_LIMIT_REMIND = "${goodsType}商品总金额超出海关清关年度限额${yearLimit}元法规限制 ";


    /**
     * 批量加入进货单时单品限额提示
     */
    public static final String SHOPCAR_LOT_ITEM_LIMIT_REMIND = "${skuName}总金额超出海关清关${itemLimit}元法规限制 ";

}
