package org.bond.yy.util.constant;

public class YYConstant {

	/**
	 * token有效时间：24小时
	 */
	public static final long TOKEN_EXPIRATION = 60 * 60 * 1000 * 24;
	
	/**
	 * 自动登录的token有效时间
	 */
	public static final long TOKEN_AUTO_LOGIN_EXPIRATION = 60 * 60 * 1000 * 24 * 7;

	/**
	 * JWT发行人
	 */
	public static final String TOKER_ISSUER = "yy";

	/**
	 * JWT私钥
	 */
	public static final String TOKEN_SERCRET = "org.yy";
}
