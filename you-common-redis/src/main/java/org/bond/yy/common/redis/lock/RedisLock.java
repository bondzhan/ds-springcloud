/**   
* @Title: RedisLock.java 
* @Package org.bond.yy.common.redis 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2019年1月16日 下午4:29:48 
* @version V1.0   
*/
package org.bond.yy.common.redis.lock;

import java.util.Random;

import org.bond.yy.common.redis.RedisManager;

/**
 * @ClassName: RedisLock
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author bond
 * @date 2019年1月16日 下午4:29:48
 * 
 */
public class RedisLock {
	
	private boolean lock;
	
	private RedisManager redisClient;
	
	private String key;
	
	public RedisLock(String preFix, String objVal){
		this.key = preFix + objVal;
	}

	/**
	 * 加锁 使用方式为： lock(); try{ executeMethod(); }finally{ unlock(); }
	 * 
	 * @param timeout
	 *            timeout的时间范围内轮询锁 
	 * @param expire
	 *            设置锁超时时间
	 * @return 成功 or 失败
	 */
	public boolean lock(long timeout, int expire) {
		long nanoTime = System.nanoTime();
		timeout *= 10L;
		try {
			// 在timeout的时间范围内不断轮询锁
			while (System.nanoTime() - nanoTime < timeout) {
				// 锁不存在的话，设置锁并设置锁过期时间，即加锁
//				if (this.redisClient.setnx(this.key, LOCKED) == 1) {
//					this.redisClient.expire(key, expire);// 设置锁过期时间是为了在没有释放
					// 锁的情况下锁过期后消失，不会造成永久阻塞
//					this.lock = true;
//					return this.lock;
//				} 
				System.out.println("出现锁等待");
				// 短暂休眠，避免可能的活锁
				Thread.sleep(3, 5);
			}
		} catch (Exception e) {
			throw new RuntimeException("locking error", e);
		}
		return false;
	}

	public void unlock() {
		try {
			if (this.lock) {
//				redisClient.delKey(key);// 直接删除
			}
		} catch (Throwable e) {
		}
	}
}
