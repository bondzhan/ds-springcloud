package org.bond.yy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;

/**
 * 
* @ClassName: AmqpApplication 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年3月29日 下午5:49:44 
*
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = RabbitAutoConfiguration.class)
public class AmqpApplication {

	public static void main(String args[]) throws InterruptedException {
		SpringApplication.run(AmqpApplication.class, args);
	}

	
}
