/**   
* @Title: SpringRabbit.java 
* @Package org.bond.yy.springamqp 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年3月27日 下午1:49:29 
* @version V1.0   
*/
package org.bond.yy.springamqp;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

/** 
* @ClassName: SpringRabbit 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年3月27日 下午1:49:29 
*  
*/
public class SpringRabbit {
	
	public static void main(String...strings){
		try {
			converAndSend();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void converAndSend() throws InterruptedException{
		ConnectionFactory cf = new CachingConnectionFactory("localhost");
		// set up the queue, exchange, binding on the broker
		RabbitAdmin admin = new RabbitAdmin(cf);
		Queue queue = new Queue("myQueue");
		admin.declareQueue(queue);
		TopicExchange exchange = new TopicExchange("myExchange");
		admin.declareExchange(exchange);
		admin.declareBinding(
			BindingBuilder.bind(queue).to(exchange).with("foo.*"));

		// set up the listener and container
		SimpleMessageListenerContainer container =
				new SimpleMessageListenerContainer(cf);
		Object listener = new Object() {
			@SuppressWarnings("unused")
			public void handleMessage(String foo) {
				System.out.println(foo);
			}
		};
		MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
		container.setMessageListener(adapter);
		container.setQueueNames("myQueue");
		container.start();

		// send something
		RabbitTemplate template = new RabbitTemplate(cf);
		template.convertAndSend("myExchange", "foo.bar", "Hello, world!");
		Thread.sleep(1000);
		container.stop();
	}
}
