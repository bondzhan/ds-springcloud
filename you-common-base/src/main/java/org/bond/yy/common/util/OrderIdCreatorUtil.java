/**   
* @Title: OrderIdCreatorUtil.java
* @Package com.xingyun.xyb2b.common.util 
* @Description: 订单生成工具类
* @author hq
* @date 2018年12月13日 下午3:17:18
* @company 版权所有 深圳市天行云供应链有限公司
* @version V1.0   
*/
package org.bond.yy.common.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/** 
* @ClassName: OrderIdCreatorUtil
* @Description: 订单生成工具类
* @author Tito
* @date 2018年12月13日 下午3:17:18
*  
*/
public class OrderIdCreatorUtil {
	

	private static List<String> createdIds = new LinkedList<>();	// 缓存生成过的ID，防止重复
	
	private static int maxCacheIdNum = 100; //缓存id数量
	
	private static long lastTime = 0L; //上一次的时间戳，用于单用户单线程

	private static int sameTimeCount = 1; //同时间段的数量，用于单用户单线程
	
	/**
	* @Title: getOrderId 
	* @Description: 生成订单ID
	* 生成策略：orderType + serverId + 时间戳(毫秒，不要第一位) + 4位随机数 。总共18位。
	* @param orderType	订单类型
	* @param serverId	服务器编号
	* @param fuid		用户ID
	* @return
	* @author Tito
	 */
	public static String getOrderId(String orderType, String serverId, int fuid) {
		long currentTime = System.currentTimeMillis(); // 毫秒
		StringBuilder builder = new StringBuilder();
		builder.append(orderType).append(serverId).append(String.valueOf(currentTime).substring(1));
		// 拼接随机数
		Random random = new Random();
		int randomLen = 4; // 随机数的长度
		random.setSeed(currentTime + fuid); // 设置随机因子
		String randomNum = String.valueOf(random.nextInt((int) Math.pow(10, randomLen))); // 生成随机数
		// 往左填充0
		for (int i = randomNum.length(); i < randomLen; i++) {
			builder.append('0');
		}
		builder.append(randomNum);
		String id = builder.toString();
		// 处理重复订单；缓存订单
		if (createdIds.contains(id)) {
			getOrderId(orderType, serverId, fuid);
		} else {
			int catchIdNum = createdIds.size();
			if (catchIdNum == maxCacheIdNum) {
				createdIds.remove(0);
			} else if (catchIdNum > maxCacheIdNum) {
				createdIds.remove(0);
			}
			createdIds.add(id);
		}
		return id;
	}
	
	/**
	* @Title: getOrderIdBySingleUser 
	* @Description: 单个用户单线程循环生成ID，适用场景：API
	* @param orderType	订单类型
	* @param serverId	服务器编号
	* @return
	* @author Tito
	 */
	public static String getOrderIdBySingleUser(String orderType, String serverId) {
		long currentTime = System.currentTimeMillis(); // 毫秒
		StringBuilder builder = new StringBuilder();
		builder.append(orderType).append(serverId).append(String.valueOf(currentTime).substring(1));
		// 如果当前时间戳>上一次的时间戳，sameTimeCount重置为1，否则sameTimeCount+1
		if (currentTime > lastTime) {
			lastTime = currentTime;
			sameTimeCount = 1;
		} else {
			sameTimeCount++;
		}
		// 拼接随机数
		Random random = new Random();
		int randomLen = 4; // 随机数的长度
		random.setSeed(currentTime + sameTimeCount); // 设置随机因子
		String randomNum = String.valueOf(random.nextInt((int) Math.pow(10, randomLen))); // 生成随机数
		// 往左填充0
		for (int i = randomNum.length(); i < randomLen; i++) {
			builder.append('0');
		}
		builder.append(randomNum);
		String id = builder.toString();
		// 处理重复订单；缓存订单
		if (createdIds.contains(id)) {
			getOrderIdBySingleUser(orderType, serverId);
		} else {
			int catchIdNum = createdIds.size();
			if (catchIdNum == maxCacheIdNum) {
				createdIds.remove(0);
			} else if (catchIdNum > maxCacheIdNum) {
				createdIds.remove(0);
			}
			createdIds.add(id);
		}
		return id;
	}
	
}
