/**   
* @Title: DynamicDataSourceAspect.java 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2016年9月2日 上午11:04:16 
* @version V1.0   
*/
package org.bond.yy.common.db;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName: DynamicDataSourceAspect
 * @Description: 数据源AOP,实现数据源切换
 * @author bond
 * @date 2016年9月2日 上午11:04:16
 * 
 */
@Aspect
@Order(-10) // 设置优先级,保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {
	private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

	/**
	 * 
	* @Title: dbCut 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param   参数说明 
	* @return void    返回类型 
	* @author Administrator
	* @throws
	 */
	@Pointcut("execution(* com.xingyun.xyb2b.*.service..*.*(..))")
	public void dbCut() {
	}

   /**
	* 
	* @Title: changeDataSource 
	* @Description: 在方法执行之前进行执行：
	* @param @param point
	* @param @throws Throwable  参数说明 
	* @return void    返回类型 
	* @author Administrator
	* @throws
	 */
	@Before("dbCut()")
	public void changeDataSource(JoinPoint point) throws Throwable {
		MethodSignature ms = (MethodSignature) point.getSignature();
		String methodName = ms.getMethod().getName();
		if (methodName.startsWith("insert") || methodName.startsWith("save") || methodName.startsWith("delete")
				|| methodName.startsWith("update") || methodName.startsWith("modify") || methodName.startsWith("add")
				|| methodName.startsWith("create")) {
			logger.debug("捕获到方法 " + methodName + "切换数据源至【master】");
			// 找到的话，那么设置到动态数据源上下文中。
			DbContextHolder.setDataSourceType("master");
		} else {
			logger.debug("捕获到方法 " + methodName + "切换数据源至【slave】");
			DbContextHolder.setDataSourceType("slave");
		}
	}

}
