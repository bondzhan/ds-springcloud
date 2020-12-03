/**   
* @Title: TokenUtils.java 
* @Package org.bond.yy.util 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author bond
* @date 2018��3��5�� ����6:30:40 
* @version V1.0   
*/
package org.bond.yy.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.bond.yy.util.constant.YYConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {

	public static void main(String args[]){
		System.out.println(createJWT("1", "bond"));
	}
	
	/**
	* @Title: 根据用户生成TOKEN
	* @Description:  用签名算法HS256和私钥key生成token
	* @param id
	* @param issuer 设置发行人
	* @param subject 设置抽象主题
	* @param 参数说明 
	* @return String  返回类型 
	* @author Administrator
	* @throws
	 */
	public static String createJWT(String id, String subject) {
		return createJwt(id,subject,YYConstant.TOKEN_EXPIRATION);
	}
	
	/**
	* @Title: 根据用户生成TOKEN,且指定token过期时间
	* @Description:  用签名算法HS256和私钥key生成token
	* @param id
	* @param issuer 设置发行人
	* @param subject 设置抽象主题
	* @param expireTime 过期时间
	* @param 参数说明 
	* @return String  返回类型 
	* @author Administrator
	* @throws
	 */
	public static String createJwtByExpire(String id, String subject, Long expireTime){
		return createJwt(id,subject,expireTime);

	}
	
	private static String createJwt(String id, String subject, Long expireTime){
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		//设置现在时间
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		
		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(YYConstant.TOKEN_SERCRET);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(YYConstant.TOKER_ISSUER)
				.signWith(signatureAlgorithm, signingKey);
		// if it has been specified, let's add the expiration
		long expMillis = nowMillis + expireTime;
		Date exp = new Date(expMillis);
		builder.setExpiration(exp);

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}
	
	
	// Sample method to validate and read the JWT
		public static Claims parseJWT(String jwt) {
			// This line will throw an exception if it is not a signed JWS (as
			// expected)
			Claims claims  = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(YYConstant.TOKEN_SERCRET)).parseClaimsJws(jwt).getBody();
			return claims;
		}
	
}
