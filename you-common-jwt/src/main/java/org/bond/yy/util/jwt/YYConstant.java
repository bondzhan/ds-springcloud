package org.bond.yy.util.jwt;

public class YYConstant {

	/**
	 * token有效时间24小时
	 */
	public static final long TOKEN_EXPIRATION = 60 * 60 * 1000 * 24;
	
	/**
	 * 自动登录的token有效时间
	 */
	public static final long TOKEN_AUTO_LOGIN_EXPIRATION = 60 * 60 * 1000 * 24 * 7;

	/**
	 * JWT发行方
	 */
	public static final String ADMIN_TOKER_ISSUER = "admin~_~";

	/**
	 * JWT私钥
	 */
	public static final String ADMIN_TOKEN_SERCRET = "xyb2b_admin~&~";

	/**
	 * JWT发行方
	 */
	public static final String USER_TOKER_ISSUER = "user^_^";

	/**
	 * JWT私钥
	 */
	public static final String USER_TOKEN_SERCRET = "xyb2b_user^&^^";

}
