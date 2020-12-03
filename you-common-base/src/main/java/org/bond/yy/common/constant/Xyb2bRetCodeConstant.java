/**   
* @Title: Xyb2bRetCodeConstant.java
* @Package com.xingyun.xyb2b.common.constant
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2016年8月25日 下午1:41:34 
* @version V1.0   
*/
package org.bond.yy.common.constant;

/**
 * @ClassName: Xyb2bRetCodeConstant
 * @Description: 定义系统编码
 * @author bond
 * @date 2016年8月25日 下午1:41:34
 * 
 */
public enum Xyb2bRetCodeConstant {
	/**
	 * 通用提示
	 */
	COMMON_OPERATION_SUCCESS(00000, "业务操作成功"),
	COMMON_OPERATION_FAILTURE(10001, "业务操作失败"),
	COMMON_PARAM_EMPTY(10002, "参数为空"),
	COMMON_PARAM_ERROR(10003, "参数错误"),
	SIGN_FAIL(10004, "验签失败"),
	TRANS_RESULT_FAIL(10005,"交易失败"),
	TRANS_RESULT_SUCCESS(10006,"交易成功"),
	COMMON_ADD_FAIL(10007,"新增失败"),
	COMMON_UPDATE_FAIL(10008,"更新失败"),
	FILE_DOWNLOAD_FAIL(10009,"文件下载失败"),
	COMMON_RESULT_EMPTY(10010, "返回结果为空"),
	/**
	 * helper 模块错误编码
	 */
	HELPER_UPLOAD_FAILTURE(10000, "上传失败"),
	SUPPLIER_SEND_SUCCEED(1902, "发送成功"),
	/**
	 * user模块错误编码
	 */
	LOGIN_SUCCESS(200, "登录成功"),
	MOBILE_NOTCORRECT(1200, "手机号格式不正确"),
	LOGINFUNAME_NOTEXIST(1201, "登录用户名为空"),
	MOBILE_NOT_REGISTER(1206, "该手机号尚未注册"),
	LOGINPassword_NOTEXIST(1202, "登录密码为空"),
	BIND_MOBILE_ERROR(1203,"绑定手机号错误"),
	SMS_SEND_FAILD(1204,"发送信息失败"),
	SMS_SEND_FAILD_M1(1211,"1分钟内只允许生成一次短信发送"),
	SMS_SEND_FAILD_M5(1212,"您的操作太频繁，请于5分钟后重试"),
	SMS_SEND_FAILD_DAY(1213,"您的今日获取验证码次数已超出限制"),
	TRANS_ORDER_ERROR(1205,"订单号为空"),
	LOGIN_NOTEXIST(1001, "用户名不存在"),
	LOGIN_FAILURE(1002, "用户名或密码错误"),
	NOT_LOGIN(1004, "用户未登录"),
	LOGIN_NAMEORPASSW_FAILURE(1003, "账号或密码错误"),
	FORBIDDEN_USER(1110, "该账号已禁用"),
	PARAMETER_ERROR(1016, "参数异常"),
	SMS_AUTH_NUM_OUT_TIME(1102, "手机验证码过期"),
	MOBILE_AUTH_NOT_MATCH(1109, "手机号与验证码不匹配"),
	SMS_AUTH_NUM_ERROR(1017, "短信验证码错误"),
	REGISTER_NAME_EXIST(1103, "用户名已注册"),
	REGISTER_MOBILE_EXIST(1104, "手机号已注册"),	
	INVITE_CODE_NOT_EXIST(1108, "邀请码不存在"),
	REGISTER_FAILED(1106, "注册失败"),
	REGISTER_IDCARDNUM_ERROR(1113, "身份证号格式错误"),
	REGISTER_DSUNAME_ERROR(1114, "电商注册用户名必须以DSDS开头"),
	REGISTER_PHONENUM_EXIST(1112, "身份证号已绑定其它账号"),
	MODIFY_SUCCESS(1601, "修改信息成功"),
	MODIFY_FAIL(1602, "修改信息失败"),
	RESULT_ISEMPTY(1007, "结果为空！"),
	PIC_AUTH_NUM_OUTTIME(1305, "图形验证码过期"),
	ERROR_AUTONUM(1302, "验证码错误"),
	PASSWORD_WRONG(1303, "原密码错误"),
	MODIFY_PASSWORD_SUCCESS(1602, "密码修改成功"),
	ACCOUNT_PWD_WRONG(1304, "账户密码错误"),
	USER_FREEZE_ERROR(1353, "该用户已被冻结账号，无权购买"),
	USER_FORBIN_ERROR(1354, "该用户已被禁止使用"),
	NULL_ERROR_WITHDRAWPSD(1306, "尚未设置提现密码"),
	WITHDRAW_PSD_WRONG(1307, "支付密码错误"),
	BALANCE_NOT_ENOUGH(1152, "余额不足"),
	MODIFY_PAY_PWD_SUCCESS(1603, "支付密码修改成功"),
	UPLOAD_HEAD_PIC_SUCCESS(1600, "头像上传成功"),
	UPLOAD_FILE_TYPE_ERROR(1052, "上传文件类型错误"),
	UPLOAD_FAILED(1053, "上传失败"),
	TOKEN_EXPIRED(1013, "会话超时！"),
	NO_BIND_EMAIL(1309, "未绑定邮箱"),
	EMAIL_SEND_FAILD(1312, "邮件发送失败"),
	NULL_PARAM(1050, "导入内容为为空"),
	RIGHT_AUTONUM(1701, "验证码正确"),
	PIC_AUTH_NUM_SUCCESS(1604, "生成图形验证码成功"),
	PWD_MIDIFY_FAILED(1308, "密码修改失败"),
	PIC_AUTH_NUM_ERROR(1018, "图形验证码错误"),
	EMAIL_AUTH_NUM_OUT_TIME(1310, "邮件验证码过期"),
	EMAIL_AUTH_NUM_ERROR(1311, "邮件验证码错误"),
	MODIFY_FAILED(1019, "修改失败"),
	WECHAT_BIND_EXIST(1061, "微信已绑定有用户"),
	LOGOUT_SUCCESS(1801, "退出成功"),
	NOT_EXIST_ERROR(1201, "请求内容不存在"),
	INSERT_VERIFY_SUCCESS(1702, "审核信息提交成功"),
	MODIFY_VERIFY_SUCCESS(1703, "审核信息修改成功"),
	DUPLICATE_CLAIM(1704, "已有该内容，请勿重复申请"),
	DUPLICATE_VERIFY_SUBMIT(1705, "已认证通过，无法修改认证信息"),
	REGISTER_EMAIL_EXIST(1111, "邮箱已绑定其它账号"),
	DELIVERY_ADDRESS_IS_NULL(1706,"收货地址不存在,请刷新后重试"),
	NOT_VERIFY_ERROR(1352, "尚未认证，无权操作"),
	DELETED_FAILED(1020,"删除失败"),
	/**
	 * sku模块错误信息
	 */
	NOT_SKU_RESULT(3000, "商品不存在"),
	
	/**
	 * pay模块错误信息
	 */
	PAY_PLATFORM_ERROR(1212, "支付平台连接异常，请联系客服！"),
	FUID_ISNULL(1010, "用户id为空！"),
	PAY_PWD_IS_NOT_SET(1012, "支付密码未设置！"),
	RECHARGE_NOT_EXIST_OR_FINISHED(1014, "充值单不存在或者已完成"),
	FUID_ORDER_NOT_MATCHING(1011, "订单与用户不匹配！"),
	ORDER_NOT_EXIST(1026, "订单不存在！"),
	TRANS_TRANS_CANCEL(1009, "该订单已取消！"),
	TRANS_COMPLETION(1006, "支付完成的交易！"),
	ORDER_CANNOT_PAY_BY_CREDIT_PAY(1027, "您微仓采购时使用了信用支付，请继续使用信用支付该代发订单。"),
	ORDER_CANNOT_PAY_NOT_CREDIT_PAY(1028, "您微仓采购时使用了余额或第三方支付，不允许使用信用支付该代发订单。"),
	

	/**
	 * shopcar模块错误信息
	 */

	SHOPCAR_ADD_NOT_FOUND_SKU(40000,"fskuId参数异常"),
	SHOPCAR_ADD_NOT_FOUND_BATCH(40001,"fbatchId参数异常"),
	SHOPCAR_NOT_FOUND_BUYNUM(40002,"fbuyNum参数异常"),
	SHOPCAR_BUYNUM_NEGATIVE_OR_ZERO(40003,"fbuyNum必须大于0"),
	BATCH_NOT_FOUND(40004,"补货中"),//批次不存在
	BATCH_NOT_SAME(40005,"补货中"),//批次不一致
	BATCH_VALIDITY_OVERDUE(40006,"补货中"),//批次有效期过期
	BATCH_QUALITY_OVERDUE(40007,"补货中"),//批次保质期过期
	MORE_THAN_STOCK(40008,"库存不足"),
	CUSTOMS_LIMIT_AMOUNT(40009, "商品总金额超出海关清关法规限制"),
	LESS_THAN_FMINNO(40010, "小于最小起拍数"),
	MORE_THAN_FMAXNO(40011, "大于最大起拍数"),
	OUT_BUYNUM_RANGE(40012, "不在价格区间范围内"),
	SHOPCAR_ADD_FAIL(40013,"商品加入进货单失败"),
	SHOPCAR_DELETE_PARAM_ERROR(40014,"删除进货单参数异常"),
	SHOPCAR_DELETE_FAIL(40015,"删除进货单失败"),
	SHOPCAR_ID_NOT_FOUND(40016,"进货单ID不存在"),
	SHOPCAR_UPDATE_NUM_FAIL(40017,"进货单修改购买数量失败"),
	SKU_NOT_PUTWAY(40018,"商品已下架"),
	SKU_ALREADY_DELETE(40019,"商品已删除"),
	SKU_ALREADY_RECYCLE(40020,"商品已置于回收状态"),
	SKU_ALREADY_DRAFT(40021,"商品已置于草稿状态"),
	BATCH_SOLD_OUT(40022,"批次已下架"),
	SKU_ID_CARE(40023,"部分商品下单时需要上传身份证正反面图片，请更新收货地址"),

	/**
	 * order模块错误信息
	 */
	ORDER_SHOPCARID_PARAM_ERROR(50000,"fgoodsShopcarIds参数异常"),
	ORDER_ADDRESS_PARAM_ERROR(50001,"fdeliveryAddrId参数异常"),
	ORDER_SUBMIT_FAIL(50002,"提交订单失败"),
	ORDER_AMOUNT_CARDID_OUT_LIMIT(50003,"身份证号码超过年度限额"),
	ORDER_AMOUNT_MOBILE_OUT_LIMIT(50004,"手机号码超过年度限额"),
	ORDER_COUNT_CARDID_OUT_LIMIT(50005,"身份证号码超过单数限制"),
	ORDER_COUNT_MOBILE_OUT_LIMIT(50006,"手机号码超过单数限制"),
	ORDER_CARDID_OUT_LIMIT(50009,"身份证号码对应订单数量或年度限额超过限制"),
	ORDER_MOBILE_OUT_LIMIT(50010,"手机号码对应订单数量或年度限额超过限制"),
	ORDER_IMMEDIATE_PURCHASE_SUCCESS(50011,"立即购买成功"),
	ORDER_IMMEDIATE_PURCHASE_FAIL(50012,"立即购买失败"),
	BATCH_REGION_NOT_DELIVERY(50013,"批次运费模板不支持该区域发货"),
	ORDER_COUNT_OUT_LIMIT_PERMIT(50014,"身份证号码或手机号码对应订单数量超过限制允许下单");

	
	private final int value;

	private final String reasonPhrase;

	Xyb2bRetCodeConstant(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public int value(){
		return this.value;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getReasonPhrase() {
		return this.reasonPhrase;
	}

}
