/**   
* @Title: UserController.java 
* @Package org.bond.yy.controller 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author bond
* @date 2017��11��16�� ����4:24:58 
* @company ��Ȩ���� �����������ƹ�Ӧ�����޹�˾
* @version V1.0   
*/
package org.bond.yy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 
* @ClassName: UserController 
* @Description: TODO(������һ�仰��������������) 
* @author bond
* @date 2017��11��16�� ����4:24:58 
*  
*/
@RestController
@RequestMapping("/user")
public class UserController {
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${server.port}")
	String port;
	
	@RequestMapping("/getUser")
	public String getUserName(@RequestParam String userName){
		log.info(userName);
		return "hello " + userName + ",this is " + port;
	}
}
