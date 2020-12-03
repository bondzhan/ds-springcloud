/**   
* @Title: OrderServiceImpl.java 
* @Package org.bond.yy.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年3月15日 下午6:02:29 
* @version V1.0   
*/
package org.bond.yy.service;

import java.util.HashMap;
import java.util.Map;

import org.bond.yy.common.redis.lock.CacheLock;
import org.bond.yy.common.redis.lock.LockedObj;
import org.springframework.stereotype.Service;

/** 
* @ClassName: OrderServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年3月15日 下午6:02:29 
*  
*/
@Service
public class OrderServiceImpl implements OrderService {

	static Map<String, Long> inventory ;
	 static{
	 inventory = new HashMap<>();
	 inventory.put("sku_id_lock123456789", 10l);
	 }
	/* (non-Javadoc)
	 * @see org.bond.yy.service.OrderService#getUserOrderList(java.lang.String)
	 */
	@Override
	@CacheLock(lockedPrefix="sku_id_lock")
	public String getUserOrderList(String a, int b, @LockedObj String userId, boolean c) {
		inventory.put("sku_id_lock" +userId ,inventory.get("sku_id_lock" +userId) - 1);
		 return inventory.get("sku_id_lock" +userId).toString();
	}

}
