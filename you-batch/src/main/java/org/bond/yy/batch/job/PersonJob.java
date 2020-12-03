/**   
* @Title: PersonJob.java 
* @Package com.xingyun.xyb2b.databatch.job 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年4月13日 下午6:16:52 
* @version V1.0   
*/
package org.bond.yy.batch.job;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.bond.yy.batch.model.Person;
import org.bond.yy.batch.processor.PersonItemToFileProcessor;
import org.bond.yy.batch.processor.PersonJobComplete;
import org.bond.yy.batch.processor.PersonRowMapper;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


/** 
* @ClassName: PersonJob 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年4月13日 下午6:16:52 
*  
*/
@Configuration
public class PersonJob {
	

	@Bean("personToFileJob")
	public Job importPersonToFileJob(JobBuilderFactory jobBuilderFactory, PersonJobComplete listener,
			Step writeStep) {
		return jobBuilderFactory
				.get("importPersonToFileJob")
				.incrementer(new RunIdIncrementer())
//				.listener(listener)
//				.start(step)
				.flow(writeStep)
				.end()
				.build();
	}


	@Bean(name="writeStep")
	public Step writeStep(StepBuilderFactory stepBuilderFactory, PersonItemToFileProcessor processor,MyBatisPagingItemReader<Person> reader,
			FlatFileItemWriter csvFileItemWriter) {
		return stepBuilderFactory.get("writeStep")
				.<Person, String>chunk(100)
				.reader(reader)
				.processor(processor)
				.writer(csvFileItemWriter)
				.faultTolerant()  
                .retry(Exception.class)   // 重试  
                .noRetry(ParseException.class)  
                .retryLimit(1)           //每条记录重试一次  
                .skip(Exception.class)  
                .skipLimit(10)         //一共允许跳过500次异常  
//                .taskExecutor(new SimpleAsyncTaskExecutor()) //设置并发方式执行  
//                .throttleLimit(10)        //并发任务数为 10,默认为4  
				.build();
	}
	@Bean
	@StepScope
	public JdbcPagingItemReader<Person> jdbcReader( DataSource dataSource,@Value("#{jobParameters[firstName]}") String firstName) {
		JdbcPagingItemReader<Person> reader = new JdbcPagingItemReader<>();
		reader.setDataSource(dataSource);
		reader.setFetchSize(50);
		reader.setPageSize(50);
		reader.setQueryProvider(new MySqlPagingQueryProvider() {{
			setSelectClause("person_id, first_name, last_name");
			setFromClause("people");
			setWhereClause("first_name= :firstName");//这里有坑,注意格式 "=空格:变量"
			setSortKeys(new HashMap<String, Order>() {{
					put("person_id", Order.ASCENDING);
				}});
		}});
		reader.setParameterValues(new HashMap<String, Object>() {
			{
				put("firstName", firstName);
			}
		});
		reader.setRowMapper(new PersonRowMapper());
		return reader;
	}
	
	@Bean
	@StepScope
	public MyBatisPagingItemReader<Person> mybatisReader(SqlSessionFactory sqlSessionFactory,@Value("#{jobParameters[firstName]}") String firstName){
		MyBatisPagingItemReader<Person> mybatisReader = new MyBatisPagingItemReader<Person>();
		mybatisReader.setPageSize(50);
		mybatisReader.setSqlSessionFactory(sqlSessionFactory);
		mybatisReader.setQueryId("org.bond.yy.batch.mapper.PersonMapper.queryUser");
	    Map<String, Object> parameterValues = new HashMap<String, Object>();
	    parameterValues.put("firstName", firstName);
	    mybatisReader.setParameterValues(parameterValues);
		return mybatisReader;
	}

	@Bean
	public FlatFileItemWriter<Person> csvFileItemWriter() {
		FlatFileItemWriter<Person> writer = new FlatFileItemWriter<>();
		writer.setAppendAllowed(true);
		writer.setEncoding("UTF-8");
		writer.setResource(new ClassPathResource("data/data.csv"));
		writer.setLineAggregator(new DelimitedLineAggregator<Person>() {
			{
				setDelimiter(",");
			}
		});
		return writer;
	}
}
