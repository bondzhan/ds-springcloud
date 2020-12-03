/**   
* @Title: CacheLock.java 
* @Package org.bond.yy.common.redis 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2019年1月16日 下午2:09:58 
* @version V1.0   
*/
package org.bond.yy.common.redis.lock;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
* @ClassName: CacheLock 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2019年1月16日 下午2:09:58 
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {
	 String lockedPrefix() default "";//redis 锁key的前缀
	 long timeOut() default 2000;//轮询锁的时间
	 int expireTime() default 1000;//key在redis里存在的时间，1000S
	 
}
