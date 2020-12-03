/**   
* @Title: UserService.java 
* @Package org.bond.yy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年3月15日 下午6:08:58 
* @version V1.0   
*/
package org.bond.yy.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/** 
* @ClassName: UserService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年3月15日 下午6:08:58 
*  
*/
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private RestTemplate restTemplate;
	
    @HystrixCommand(fallbackMethod = "fallback")
	public String getUserOrderById(String orderId){
		Map<String, String> params = new HashMap<String, String>();
	    params.put("orderId", orderId);
		return (String) restTemplate.getForObject("http://order-service/order/getOrder?orderId={orderId}", String.class, params);
	}
    
    public String fallback(String orderId) {
        return "this is fallback " + orderId;
    }
	
}
