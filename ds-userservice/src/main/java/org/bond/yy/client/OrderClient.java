/**   
* @Title: OrderClient.java 
* @Package org.bond.yy.client 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年7月24日 下午2:33:16 
* @version V1.0   
*/
package org.bond.yy.client;

import org.bond.yy.client.fallback.OrderHystrixClientFallbackFacotry;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/** 
* @ClassName: OrderClient 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年7月24日 下午2:33:16 
*  
*/
@FeignClient(name="s2b2c-order-service", fallbackFactory = OrderHystrixClientFallbackFacotry.class)
public interface OrderClient {

	/**
	 * get 请求时需将URL指定参数名称:(@RequestParam("orderId")),不然feign会使用POST请求(feign.FeignException: status 405)
 	 * 多参数的URL也可使用Map来构建:@RequestParam Map<String, Object> map
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/order/getOrder")
	String getOrder(@RequestParam("orderId") String orderId); 
	
	@RequestMapping(method = RequestMethod.POST, value = "/order/getOrderList")
	String getOrderList(@RequestBody String userId);
}
