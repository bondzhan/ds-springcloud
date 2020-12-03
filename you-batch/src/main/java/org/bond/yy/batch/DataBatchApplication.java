/**   
* @Title: DataBatchApplication.java 
* @Package com.xingyun.xyb2b.databatch 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年4月12日 下午6:34:38 
* @version V1.0   
*/
package org.bond.yy.batch;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/** 
* @ClassName: DataBatchApplication 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年4月12日 下午6:34:38 
*  
*/
@SpringBootApplication
@ComponentScan(basePackages="org.bond.yy")
public class DataBatchApplication {

	public static void main(String args[]){
		SpringApplication.run(DataBatchApplication.class, args);
	}
	
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return new DataSource();
	}
}
