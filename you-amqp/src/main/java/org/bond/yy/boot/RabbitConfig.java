package org.bond.yy.boot;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.support.DefaultMessagePropertiesConverter;
import org.springframework.amqp.rabbit.support.MessagePropertiesConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * 
* @ClassName: RabbitConfig 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年3月29日 下午5:49:24 
*
 */
@Configuration
public class RabbitConfig {

	@Bean("connectionFactory")
	@ConfigurationProperties(prefix = "spring.rabbitmq")
	public ConnectionFactory getConnectionFactory() {
		return new CachingConnectionFactory();
	}

	@Bean(name = "rabbitAdmin")
	@DependsOn("connectionFactory")
	public RabbitAdmin getRabbitAdmin(ConnectionFactory connectionFactory) {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
		rabbitAdmin.setAutoStartup(true);
		return rabbitAdmin;
	}

	@Bean(name = "serializerMessageConverter")
	public MessageConverter getMessageConverter() {
		return new SimpleMessageConverter();
	}

	@Bean(name = "messagePropertiesConverter")
	public MessagePropertiesConverter getMessagePropertiesConverter() {
		return new DefaultMessagePropertiesConverter();
	}

}
