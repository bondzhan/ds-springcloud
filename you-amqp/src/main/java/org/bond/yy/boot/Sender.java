package org.bond.yy.boot;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
* @ClassName: Sender 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年3月29日 下午5:49:14 
*
 */
@Component
public class Sender implements ConfirmCallback {

	@Autowired 
	private RabbitTemplate rabbitTemplate;

	public void send() {
		String context = "hello " + new Date();
		Msg msg = new Msg();
		msg.setMsgbody("abcdefg" +context) ;
		System.out.println("Sender : " + msg.getMsgbody());
		this.rabbitTemplate.convertAndSend("bond", msg);
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		// TODO Auto-generated method stub
		if (ack) {
			System.out.println("��Ϣ�ɹ�����");
		} else {
			System.out.println("��Ϣ����ʧ��:" + cause);
		}
	}
}
