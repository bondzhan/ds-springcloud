package org.bond.yy.boot;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 
* @ClassName: Receiver 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年3月29日 下午5:48:57 
*
 */
@Component
@RabbitListener(queues = "bond")
public class Receiver {

	@RabbitHandler
	public void process(Msg msg) {
		System.out.println("Receiver : " + msg.getMsgbody());
	}

}
