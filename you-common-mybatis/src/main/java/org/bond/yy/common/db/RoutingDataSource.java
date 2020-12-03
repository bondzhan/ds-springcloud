/**   
* @Title: RoutingDataSource.java 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2016年9月2日 上午10:55:24 
* @version V1.0   
*/
package org.bond.yy.common.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/** 
* @ClassName: RoutingDataSource 
* @Description: 动态切换数据源
* @author bond
* @date 2016年9月2日 上午10:55:24 
*  
*/
public class RoutingDataSource extends AbstractRoutingDataSource {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
	 */
	@Override
	protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDataSourceType();
	}
}
