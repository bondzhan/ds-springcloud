package org.bond.yy.boot;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.MessagePropertiesConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * 
* @ClassName: ProducerConfig 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年3月29日 下午5:48:35 
*
 */
@Configuration
public class ProducerConfig {
	
	@Bean(name = "rabbitTemplate")
	@DependsOn({ "connectionFactory", "serializerMessageConverter", "messagePropertiesConverter" })
	public RabbitTemplate getRabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter,
			MessagePropertiesConverter messagePropertiesConverter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setUseTemporaryReplyQueues(false);
		rabbitTemplate.setMessageConverter(messageConverter);
		rabbitTemplate.setMessagePropertiesConverter(messagePropertiesConverter);
		//设置消息回传
		rabbitTemplate.setConfirmCallback(new Sender());
		// rabbitTemplate.setReplyAddress(AppConstants.REPLY_QUEUE_NAME);
		rabbitTemplate.setReceiveTimeout(60000);
		return rabbitTemplate;
	}
	
	@Bean
	public Queue bondQueue() {
		return new Queue("bond");
	}
}
