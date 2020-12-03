/**   
* @Title: LockedComplexObject.java 
* @Package org.bond.yy.common.redis 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2019年1月16日 下午2:19:13 
* @version V1.0   
*/
package org.bond.yy.common.redis.lock;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/** 
* @ClassName: LockedComplexObject 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2019年1月16日 下午2:19:13 
*  
*/
@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface LockedComplexObject {
	 String field() default "";//含有成员变量的复杂对象中需要加锁的成员变量，如一个商品对象的商品ID
}
