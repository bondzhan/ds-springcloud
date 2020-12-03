/**   
* @Title: XyLogHandler.java 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2016年8月24日 下午2:55:15 
* @version V1.0   
*/
package org.bond.yy.common.log;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/** 
* @ClassName: XyLogHandler 
* @Description: TODO 日志切面反射
* @author bond
* @date 2016年8月24日 下午2:55:15 
*  
*/
@Aspect
@Component
public class YouLogHandler {
	private static Logger logger = LoggerFactory.getLogger(YouLogHandler.class);

	ThreadLocal<Long> timeThead=new ThreadLocal<Long>();
    
    /**
    * @Title: log 
    * @Description: 添加一个切入点 
     */
    @Pointcut("@annotation(org.bond.yy.common.log.XyLogger)")
    public void logCut(){ }
    
    @Before("logCut()")
    public void beforeHandler(JoinPoint joinPoint){
    	timeThead.set(System.currentTimeMillis());
    	
    	MethodSignature ms=(MethodSignature) joinPoint.getSignature();
        Method method=ms.getMethod();
        logger.info("【" + joinPoint.getSignature()  + "");
    	logger.info("【" + ms.getName()  + "】" + method.getAnnotation(YouLogger.class).loggerComment());
//        Object[] os=joinPoint.getArgs();
//        StringBuilder argsStr = new StringBuilder();
//        if(os.length > 0){
//        	for(int i=0; i < os.length; i++){
//            	argsStr.append("param["+i+"]="+os[i].toString());
//        	}
//            logger.info("【" +  argsStr.toString()  + "】");
//        }
    }
     
    @After("logCut()")
    public void afterHandler(JoinPoint joinPoint){
    	logger.info("【" + joinPoint.getSignature()  + "】" + " 执行时间" + (System.currentTimeMillis()-timeThead.get())+"ms");
    }
     	
}
