/**   
* @Title: RedisLockAspect.java 
* @Package org.bond.yy.common.redis 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2019年1月22日 下午12:00:42 
* @version V1.0   
*/
package org.bond.yy.common.redis;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.bond.yy.common.redis.lock.CacheLock;
import org.bond.yy.common.redis.lock.CacheLockException;
import org.bond.yy.common.redis.lock.LockedComplexObject;
import org.bond.yy.common.redis.lock.LockedObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/** 
* @ClassName: RedisLockAspect 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2019年1月22日 下午12:00:42 
*  
*/
@Aspect
@Order(-8)
@Component
public class RedisLockAspect {

	@Autowired
	RedisManager redisManager;
	
    @Pointcut("@annotation(org.bond.yy.common.redis.lock.CacheLock)")
    public void lockCut(){ }
    
    @Before("lockCut()")
    public void beforeHandler(JoinPoint joinPoint) throws CacheLockException, InterruptedException{
		MethodSignature ms = (MethodSignature) joinPoint.getSignature();
		Method method = ms.getMethod();
		String preFix = method.getAnnotation(CacheLock.class).lockedPrefix();
		// 获得方法中参数的注解
		Annotation[][] annotations = method.getParameterAnnotations();
		String lockValue = "";
		int index = -1;
		//不支持多个参数加锁，只支持遍历至第一个注解为lockedObject或者lockedComplexObject的参数
		for(int i = 0; i < annotations.length; i++){
			 for(int j = 0; j < annotations[i].length; j++){
				 if(annotations[i][j] instanceof LockedComplexObject){//注解为LockedComplexObject
					 try {
						 lockValue = annotations[i][j].getClass().getField(((LockedComplexObject)annotations[i][j]).field()).toString();
						 index = i;
						 break;
					 } catch (NoSuchFieldException | SecurityException e) {
						 throw new CacheLockException("注解对象中没有该属性" + ((LockedComplexObject)annotations[i][j]).field());
					 }
				 }else if(annotations[i][j] instanceof  LockedObj){
					 lockValue = joinPoint.getArgs()[i].toString();
					 index = i;
					 break;
				 }
			 }
			 if(index >= 0){
				 break;
			 }
		}
		long startTime = System.currentTimeMillis();
		boolean isLock = false;
		//允许时间范围内不断轮询锁
		while (System.currentTimeMillis() - startTime <= 5000){
			boolean lock = redisManager.lock(preFix + lockValue);
			if(lock){
				isLock =  true;
				break;
			}
		}
		if(!isLock){
			throw new CacheLockException("锁库失败");
		}
		System.out.println("################" + method.getName()  + "#################");
		System.out.println("################" + preFix + lockValue + "#################");
    }
     
    @After("lockCut()")
    public void afterHandler(JoinPoint joinPoint){
    }
     	
	
}
