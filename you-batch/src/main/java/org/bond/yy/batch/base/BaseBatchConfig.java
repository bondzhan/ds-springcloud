/**   
* @Title: BatchJdbcConfig.java 
* @Package org.bond.batch 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年4月10日 下午2:55:17 
* @version V1.0   
*/
package org.bond.yy.batch.base;

import java.util.Properties;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.DatabaseType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;


/**
 * @ClassName: BatchJdbcConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author bond
 * @date 2018年4月10日 下午2:55:17
 * 
 */
@Configuration
@EnableBatchProcessing // 开启批处理
@EnableAutoConfiguration
@MapperScan(basePackages = "org.bond.yy.batch.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class BaseBatchConfig {

	/**
     * 作业仓库
     * 
     * @param dataSource
     * @param transactionManager
     * @return
     * @throws Exception
     */
    @Bean
	@DependsOn("dataSource")
    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception{
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType(DatabaseType.MYSQL.name());
        return jobRepositoryFactoryBean.getObject();
    }

    /**
     * 作业调度器
     * 
     * @param dataSource
     * @param transactionManager
     * @return
     * @throws Exception
     */
    @Bean
	@DependsOn("dataSource")
    public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception{
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(this.jobRepository(dataSource, transactionManager));
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return jobLauncher;
    }
    
    /**
	 * 
	* @Title: transactionManager 
	* @Description: 配置事务管理器 
	* @param @param dataSource
	* @return DataSourceTransactionManager    返回类型 
	* @author bond
	* @throws
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) throws Exception {
		return new DataSourceTransactionManager(dataSource);
	}
	
	/**
	* @Title: 注入 SqlSessionFactory
	* @Description: 设置session,供自动化的事务切面 注意此方法注入依赖@DependsOn("rdataSource")
	* @param @param dataSource
	* @param @return
	* @return SqlSessionFactory    返回类型 
	* @author bond
	* @throws
	 */
	@Bean(name="sqlSessionFactory")
	@DependsOn("dataSource")
	public SqlSessionFactory  sqlSessionFactoryBean(DataSource dataSource) throws Throwable {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		Properties properties = new Properties();
		properties.setProperty("dialect", "mysql");
		// 启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页;禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据
		properties.setProperty("reasonable", "true");
		// 设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果
		properties.setProperty("pageSizeZero", "true");
		// 设置为true时，使用RowBounds分页会进行count查询
		properties.setProperty("rowBoundsWithCount", "true");
		return sessionFactoryBean.getObject();
	}
    

}
