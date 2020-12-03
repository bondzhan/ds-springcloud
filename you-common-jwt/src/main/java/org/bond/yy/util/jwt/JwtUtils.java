package org.bond.yy.util.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.google.common.base.Strings;

/**
 * 
* @ClassName: JwtUtils 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年7月21日 下午6:31:12 
*
 */
public class JwtUtils {

	public static void main(String args[]){
		System.out.println(createUserJWT("71","Fate"));
	}
	

	public static String createAdminJWT(String id, String subject){
		return createJWT(id, subject,YYConstant.ADMIN_TOKEN_SERCRET, YYConstant.ADMIN_TOKER_ISSUER);
	}

	public static String createUserJWT(String id, String subject){
		return createJWT(id,subject,YYConstant.USER_TOKEN_SERCRET, YYConstant.USER_TOKER_ISSUER);
	}


	/**
	* @Title: 根据用户生成TOKEN
	* @Description:  用签名算法HS256和私钥key生成token
	* @param id
	* @param issuer 设置发行方
	* @param subject 设置抽象主题
	* @param issuer 参数说明
	* @return String  返回类型 
	* @author Administrator
	* @throws
	 */
	public static String createJWT(String id, String subject , String secret, String issuer) {
		return createJwt(id,subject,YYConstant.TOKEN_EXPIRATION , secret, issuer);
	}
	
	/**
	* @Title: 根据用户生成TOKEN,且指定token过期时间
	* @Description:  用签名算法HS256和私钥key生成token
	* @param id
	* @param issuer 设置发行�?
	* @param subject 设置抽象主题
	* @param expireTime 过期时间
	* @param secret token 私钥
    * @param issuer 发行方
	* @return String  返回类型 
	* @author Administrator
	* @throws
	 */
	public static String createJwtByExpire(String id, String subject, Long expireTime, String secret, String issuer){
		return createJwt(id,subject,expireTime, secret, issuer);

	}
	
	private static String createJwt(String id, String subject, Long expireTime, String secret, String issuer){
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		//设置现在时间
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		
		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);
		// if it has been specified, let's add the expiration
		long expMillis = nowMillis + expireTime;
		Date exp = new Date(expMillis);
		builder.setExpiration(exp);

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}
	
	
	// Sample method to validate and read the JWT
		public static Claims parseJWT(String jwt, String sercret) {
			// This line will throw an exception if it is not a signed JWS (as
			// expected)
			Claims claims  = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(sercret)).parseClaimsJws(jwt).getBody();
			return claims;
		}

	/**
	 *
	 * @Title: parseTokenForFuid
	 * @Description: 从Token里解析出fuid
	 * @param request
	 * @return
	 * @author Thstone
	 */
	public static String parseTokenForFuid(HttpServletRequest request){
		String accessToken = request.getHeader("accessToken");
		String fuid = null;
		if (!Strings.isNullOrEmpty(accessToken)) {
			Claims claims = null;
			try {
				claims = JwtUtils.parseJWT(accessToken,YYConstant.USER_TOKEN_SERCRET);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(claims != null){
				fuid = claims.getId();
			}
		}
		return fuid;
	}

	public static String parseTokenForFuidCheck(HttpServletRequest request){
		String accessToken = request.getHeader("accessToken");
		String fuid = null;
		if (!Strings.isNullOrEmpty(accessToken)) {
			final Claims claims = JwtUtils.parseJWT(accessToken,YYConstant.USER_TOKEN_SERCRET);
			if(claims != null){
				fuid = claims.getId();
			}
			long nowTime = new Date().getTime();
			if (null == claims || nowTime > claims.getExpiration().getTime()) {
				return null;
			}
		}
		return fuid;
	}

	/**
	* @Title:
	* @Description: (生成短信验证码等 redis key)
	* @param  id 参数说明
	* @param  subject 参数说明
	* @param  expireTime 参数说明
	* @return  String    返回类型 短信验证码redis key
	* @author Thstone
	* @throws
	*/
	public static String createJwtByExpire(String id, String subject, Long expireTime){
		return createJwt(id,subject,expireTime,YYConstant.USER_TOKEN_SERCRET, YYConstant.USER_TOKER_ISSUER);
	}
	
}
