/**   
* @Title: OrderController.java 
* @Package org.bond.yy.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年3月9日 下午5:42:08 
* @version V1.0   
*/
package org.bond.yy.controller;

import org.bond.yy.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: OrderController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author bond
 * @date 2018年3月9日 下午5:42:08
 * 
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	Logger log = LoggerFactory.getLogger(getClass());

	@Value("${server.port}")
	String port;

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/getOrder", method = RequestMethod.GET)
	public String getOrder(@RequestParam String orderId) {
		log.info("this is " + orderId);
		return "This is " + orderId + ",this is " + port;
	}
	
	
	@RequestMapping(value = "/getOrderList", method = RequestMethod.POST)
	public String getOrderList(@RequestBody String userId) {
		log.info("this is orderList " + userId);
		return "This is orderList " + userId + ",this is " + port;
	}
	
	@RequestMapping(value = "/testRedis", method = RequestMethod.GET)
	public void testRedis(){
		String num = orderService.getUserOrderList("a", 101, "123456789", false);
		log.info("剩余库存" + num);
	}

}
