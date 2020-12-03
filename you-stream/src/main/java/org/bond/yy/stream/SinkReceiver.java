/**   
* @Title: SinkReceiver.java 
* @Package org.bond.yy.stream 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年8月7日 下午7:24:32 
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
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年8月7日 下午7:24:32 
*  
*/
public class SinkReceiver {
	
	private static Logger logger = LoggerFactory.getLogger(SinkReceiver.class);

    public void receive(Object payload) {
        logger.info("Received: " + payload.toString());
    }

}
