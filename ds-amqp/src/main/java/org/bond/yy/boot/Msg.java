package org.bond.yy.boot;

import java.io.Serializable;

/**
 * 
* @ClassName: Msg 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年3月29日 下午5:48:27 
*
 */
public class Msg implements Serializable {

	private static final long serialVersionUID = 1L;
	private String msgbody;

	public String getMsgbody() {
		return msgbody;
	}

	public void setMsgbody(String msgbody) {
		this.msgbody = msgbody;
	}
}
