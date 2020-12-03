/**   
* @Title: YouMybatisItemReader.java 
* @Package org.bond.yy.batch.job 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2019年4月1日 下午6:15:44 
* @version V1.0   
*/
package org.bond.yy.batch.job;

import org.mybatis.spring.batch.MyBatisPagingItemReader;

/** 
* @ClassName: YouMybatisItemReader 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
 * @param <T>
* @date 2019年4月1日 下午6:15:44 
*  
*/
public class YouMybatisItemReader<T> extends MyBatisPagingItemReader<T> {

	@Override
	protected void doReadPage() {
		// TODO Auto-generated method stub
		super.doReadPage();
	}

}
