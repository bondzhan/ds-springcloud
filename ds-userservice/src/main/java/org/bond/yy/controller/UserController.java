/**   
* @Title: UserController.java 
* @Package org.bond.yy.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2017年11月16日 下午4:24:58 
* @company 版权所有 深圳市天行云供应链有限公司
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
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2017年11月16日 下午4:24:58 
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
