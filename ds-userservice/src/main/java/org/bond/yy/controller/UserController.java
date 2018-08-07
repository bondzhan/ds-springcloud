package org.bond.yy.controller;

import java.util.HashMap;
import java.util.Map;

import org.bond.yy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class UserController {
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${server.port}")
	String port;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getUser",method = RequestMethod.POST)
	public String getUserName(@RequestParam String userName){
		log.info(userName);
		return "hello " + userName + ",this is " + port;
	}
    
    public String fallback(String orderId) {
        return "this is fallback " + orderId;
    }
	
	@RequestMapping(value = "/getUserOrder",method = RequestMethod.POST)
	public String getUserOrderById(@RequestParam String orderId){
		return userService.getUserOrderById(orderId);
	}
    
	@RequestMapping(value = "/getUserOrderList",method = RequestMethod.POST)
	public String getUserOrderList(@RequestParam String userId){
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("userId", userId);
		return (String) restTemplate.postForObject("http://order-service/order/getOrderList", params, String.class);
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String userLogin(@RequestParam String userName, @RequestParam String userPasswd){
		return "Login Successful!!!";
	}
}
