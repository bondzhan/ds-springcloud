/**   
* @Title: DbContextHolder.java 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2016年9月2日 上午10:55:52 
* @version V1.0   
*/
package org.bond.yy.common.db;

import org.springframework.stereotype.Component;

/** 
* @ClassName: DbContextHolder 
* @Description: 设置当前线程的数据源
* @author bond
* @date 2016年9月2日 上午10:55:52 
*  
*/
@Component
public class DbContextHolder {

	/*
     * 当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
 
    /**
     * 使用setDataSourceType设置当前的
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType) {
       contextHolder.set(dataSourceType);
    }
 
    public static String getDataSourceType() {
       return contextHolder.get();
    }
 
    public static void clearDataSourceType() {
       contextHolder.remove();
    }
}
