/**   
* @Title: SinkReceiver.java 
* @Package org.bond.yy.stream 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author bond
* @date 2018��8��7�� ����7:24:32 
* @version V1.0   
*/
package org.bond.yy.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/** 
* @ClassName: SinkReceiver 
* @Description: TODO(������һ�仰��������������) 
* @author bond
* @date 2018��8��7�� ����7:24:32 
*  
*/
public class SinkReceiver {
	
	private static Logger logger = LoggerFactory.getLogger(SinkReceiver.class);

    public void receive(Object payload) {
        logger.info("Received: " + payload.toString());
    }

}
