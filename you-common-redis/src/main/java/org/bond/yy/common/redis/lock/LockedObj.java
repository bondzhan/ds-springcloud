/**   
* @Title: LockedObj.java 
* @Package org.bond.yy.common.redis 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2019年1月16日 下午2:17:21 
* @version V1.0   
*/
package org.bond.yy.common.redis.lock;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
* @ClassName: LockedObj 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2019年1月16日 下午2:17:21 
*  
*/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedObj {

}
