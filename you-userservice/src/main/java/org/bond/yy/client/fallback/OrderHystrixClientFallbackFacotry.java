/**   
* @Title: OrderHystrixClientFallbackFacotry.java 
* @Package com.xingyun.s2b2c.user.client.fallback 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年7月25日 下午8:41:42 
* @version V1.0   
*/
package org.bond.yy.client.fallback;

import org.bond.yy.client.OrderClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;


/**
 * @ClassName: OrderHystrixClientFallbackFacotry
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author bond
 * @date 2018年7月25日 下午8:41:42
 * 
 */
@Component
public class OrderHystrixClientFallbackFacotry implements FallbackFactory<OrderClient> {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public OrderClient create(Throwable cause) {
		// TODO Auto-generated method stub
		return new OrderClient() {
			@Override
			public String getOrder(String orderId) {
				log.error("异常处理={}", cause);
				return "Execute raw fallback: access service fail , req= " + orderId + " reason = " + cause;
			}

			@Override
			public String getOrderList(String userId) {
				log.error("异常处理={}", cause);
				return "Execute raw fallback: access service fail , req= " + userId + " reason = " + cause;
			}
		};
	}

}
