/**   
* @Title: XyLogger.java 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2016年8月24日 下午2:52:23 
* @version V1.0   
*/
package org.bond.yy.common.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
* @ClassName: XyLogger 
* @Description: 全局Log注解
* @author bond
* @date 2016年8月24日 下午2:52:23 
*  
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface YouLogger {
	String loggerComment() default "";
}
