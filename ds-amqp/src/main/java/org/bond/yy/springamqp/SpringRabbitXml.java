/**   
* @Title: SpringRabbitXml.java 
* @Package org.bond.yy.springamqp 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年3月27日 下午2:10:04 
* @version V1.0   
*/
package org.bond.yy.springamqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
* @ClassName: SpringRabbitXml 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年3月27日 下午2:10:04 
*  
*/
public class SpringRabbitXml {

	public static void main(final String... args) throws Exception {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("springrabbit.xml");
		RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
		template.convertAndSend("Hello, world!");
		Thread.sleep(1000);
		ctx.destroy();
	}
}
