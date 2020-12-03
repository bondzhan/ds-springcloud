/**   
* @Title: CaptchaConfig.java 
* @Package com.xingyun.s2b2c.buyer 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年8月3日 下午2:53:42 
* @version V1.0   
*/
package org.bond.yy.util.kaptcha;

import java.util.Properties;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/** 
* @ClassName: CaptchaConfig 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年8月3日 下午2:53:42 
*  
*/
public class CaptchaConfig {
	
	public static  DefaultKaptcha getKaptchaBean(){
		DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
		Properties properties=new Properties();
		properties.setProperty("kaptcha.border", "yes");
		properties.setProperty("kaptcha.border.color", "105,179,90");
		properties.setProperty("kaptcha.textproducer.font.color", "blue");
		properties.setProperty("kaptcha.image.width", "125");
		properties.setProperty("kaptcha.image.height", "45");
		properties.setProperty("kaptcha.session.key", "code");
		properties.setProperty("kaptcha.textproducer.char.length", "4");
		properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");		
		Config config=new Config(properties);
		defaultKaptcha.setConfig(config);
		return defaultKaptcha;
	}
	
}
