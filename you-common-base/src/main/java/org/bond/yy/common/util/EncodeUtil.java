/**   
* @Title: EncodeUtil.java 
* @Package com.xingyun.xyb2b.common.util 
* @Description: TODO
* @author leo
* @date 2017年1月5日 下午3:43:13 
* @company 版权所有 深圳市天行云供应链有限公司
* @version V1.0   
*/
package org.bond.yy.common.util;


import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
* @ClassName: EncodeUtil 
* @Description: 加密
* @author leo
* @date 2017年1月5日 下午3:43:13 
*  
*/
public class EncodeUtil {
	private static Logger logger =	 LoggerFactory.getLogger(EncodeUtil.class);
	/** 
	* @Title: decodeApp 
	* @Description: 移动端加密 
	* @param pwd
	* @return
	* @author leo
	*/
	public static String encodeApp(String pwd){
		return encode(pwd,5,3);
	}
	
	/** 
	* @Title: decodeWeb 
	* @Description: pc端加密
	* @param pwd
	* @return
	* @author leo
	*/
	public static String encodeWeb(String pwd){
		return encode(pwd,5,3);
	}
	
	/** 
	* @Title: decode 
	* @Description: 解密 
	* @param pwd 密码
	* @param prefix 前增加多少个字符
	* @param suffix 后增加多少个字符
	* @return
	* @author leo
	*/
	private static String encode(String pwd,int prefix,int suffix){
		String password="";
		try {
			password=Base64.getEncoder().encodeToString(pwd.getBytes("utf-8"));
			password=getStr(prefix)+password+getStr(suffix);
			password=Base64.getEncoder().encodeToString(password.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("加密报错，密码为："+pwd);
		}
		return password;
	}
	
	private static String getStr(int len){
		String str="";
		String source="qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
		for(int i=0;i<len;i++){
			int num=(int)(Math.random()*source.length()-1)+1;
			str+=source.charAt(num);
		}
		return str;
	}
	
//	public static void main(String[] args) {
//		String str=EncodeUtil.encodeApp("123456");
//		str=DecodeUtil.decodeApp(str);
//	}
}
