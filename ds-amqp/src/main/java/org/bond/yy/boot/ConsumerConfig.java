/**   
* @Title: ConsumerConfig.java 
* @Package org.bond.yy.boot 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author bond
* @date 2018��3��29�� ����3:37:39 
* @version V1.0   
*/
package org.bond.yy.boot;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * 
* @ClassName: ConsumerConfig 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年3月29日 下午5:48:10 
*
 */
@Configuration
@EnableRabbit//开启@RabbitListener
public class ConsumerConfig {

	@Bean
	@DependsOn("connectionFactory")
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setConcurrentConsumers(3);
		factory.setMaxConcurrentConsumers(10);
		factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return factory;
	}
}
