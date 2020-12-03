package org.bond.yy.common.db;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.bond.yy.common.wrapper.MapWrapperFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.github.pagehelper.PageHelper;

/**
 * 
* @ClassName: DatasourceConfig 
* @Description: 初始化数据源,SqlSessionFactory,DataSourceTransactionManager
* @author bond
* @date 2016年9月2日 下午6:14:28 
*
 */
@Configuration
@MapperScan(basePackages = "com.xingyun.xyb2b.*.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class DatasourceConfig{
	 private static Logger logger = LoggerFactory.getLogger(DatasourceConfig.class);

	 
	/**
	* @Title: 主库数据源
	* @Description: 取出配置文件数据库参数且初始化 
	* @return DataSource    返回类型 
	* @author bond
	* @throws
	 */
	@Bean(name="masterDatasource")
	@ConfigurationProperties(prefix = "datasource.master")
	public DataSource masterSourceConfig() {
		logger.info("初始化Master数据源");
		return new DataSource();
	}

	/**
	* @Title: 从库数据源
	* @Description: 取出配置文件数据库参数且初始化 
	* @return DataSource    返回类型 
	* @author bond
	* @throws
	 */
	@Bean(name="slaveDatasource")
	@ConfigurationProperties(prefix = "datasource.slave")
	public DataSource slaveSourceConfig() {
		logger.info("初始化Slave数据源");
		return new DataSource();
	}

	/**
	 * 
	* @Title: 注入自动切换数据源 
	* @Description: 多个数据源初始化及依赖,注意此方法注入依赖@DependsOn({"masterDatasource","slaveDatasource"})
	* @param @param masterDatasource
	* @param @param slaveDatasource
	* @param @return  参数说明 
	* @return RoutingDataSource    返回类型 
	* @author bond
	* @throws
	 */
	@Bean(name="rdataSource")
	@Primary//该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
	@DependsOn({"masterDatasource","slaveDatasource"})
	public RoutingDataSource dataSource(DataSource masterDatasource,DataSource slaveDatasource) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put("master", masterDatasource);
		targetDataSources.put("slave", slaveDatasource);
		RoutingDataSource dataSource = new RoutingDataSource();
		dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
		dataSource.setDefaultTargetDataSource(masterDatasource);// 默认的datasource设置为masterDatasource
		return dataSource;
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
	public DataSourceTransactionManager transactionManager(RoutingDataSource dataSource) throws Exception {
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
	@DependsOn("rdataSource")
	public SqlSessionFactory sqlSessionFactoryBean(RoutingDataSource dataSource) throws Throwable {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		// 分页插件
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("dialect", "mysql");
		// 启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页;禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据
		properties.setProperty("reasonable", "true");
		// 设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果
		properties.setProperty("pageSizeZero", "true");
		// 设置为true时，使用RowBounds分页会进行count查询
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("supportMethodsArguments", "true");//支持通过Mapper接口参数来传递分页参数,默认false
		// properties.setProperty("returnPageInfo", "check"); 	//always总是返回PageInfo类型,check检查返回类型是否为PageInfo,none返回Page
		// properties.setProperty("params", "count=countSql");	//不理解该含义的前提下，不要随便复制该配置
		// properties.setProperty("offsetAsPageNum", "true"); //设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用,和startPage中的pageNum效果一样
		pageHelper.setProperties(properties);
		// 添加插件
		sessionFactoryBean.setPlugins(new Interceptor[] { pageHelper });
		
		SqlSessionFactory sqlSessionFactory = sessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
		//返回map 字段值为null时, 显示该字段
		sqlSessionFactory.getConfiguration().setCallSettersOnNulls(true);
		//设置结果映射下划线转驼峰 resultType = 'java.util.Map'
		sqlSessionFactory.getConfiguration().setObjectWrapperFactory(new MapWrapperFactory());
	//	sqlSessionFactory.getConfiguration().getTypeAliasRegistry().registerAliases("com.xingyun.s2b2c.*.model");

		//PathMatchingResourcePatternResolver resolver = new
		//PathMatchingResourcePatternResolver();
		//sessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/com/xingyun/xyb2b/admin/mapper/get/*.xml"));
		return sqlSessionFactory;
	}
}
