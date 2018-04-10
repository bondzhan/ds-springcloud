/**   
* @Title: SpringRabbitXml.java 
* @Package org.bond.yy.springamqp 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author bond
* @date 2018��3��27�� ����2:10:04 
* @version V1.0   
*/
package org.bond.yy.springamqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRabbitXml {

	public static void main(final String... args) throws Exception {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("springrabbit.xml");
		RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
		template.convertAndSend("Hello, world!");
		Thread.sleep(1000);
		ctx.destroy();
	}
}
