package org.bond.yy.common.redis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

/**
 * @Description: 如需使用,请自行在各自模块注入
 * @author bond
 */
@Component
public class RedisManager {
	
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;
	
	public RedisManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate){
		this.redisTemplate = redisTemplate;
	}
	
	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	@SuppressWarnings("unchecked")
	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	@SuppressWarnings("unchecked")
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object get(final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}

	/**
     *  最终加强分布式锁
     * @param key key值
     * @return 是否获取到
     */
    @SuppressWarnings("unchecked")
	public boolean lock(String key){
        String lock = key;
        // 利用lambda表达式
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            long expireAt = System.currentTimeMillis() + 1000;
            Boolean acquire = connection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
            if (acquire) {
                return true;
            } else {
                byte[] value = connection.get(lock.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {
                    long expireTime = Long.parseLong(new String(value));
                    if (expireTime < System.currentTimeMillis()) {
                        // 如果锁已经过期
                        byte[] oldValue = connection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + 1000).getBytes());
//                        connection.expire(key.getBytes(), 1l);
                        // 防止死锁
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });
    }
    
    public static void main(String args[]) throws InterruptedException{
    	long lastTime = System.currentTimeMillis();
    	System.out.println(System.currentTimeMillis());
    	Thread.sleep(2000);
    	System.out.println(System.currentTimeMillis() - lastTime);
    }
    /**
     * 删除锁
     *
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }
 
	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 
	* @Title: lpush 
	* @Description: 增加值至�?左边 
	* @param @param key
	* @param @param value
	* @param @return  参数说明 
	* @return boolean    返回类型 
	* @author leo
	* @throws 
	*/
	public boolean lpush(String key,String value){
		boolean result = false;
		try {
			ListOperations<String, String> operations = redisTemplate.opsForList();
			operations.leftPush(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 
	* @Title: range 
	* @Description: 获取指定list的指定位置�?? 
	* @param @param key
	* @param @param start
	* @param @param end
	* @param @return  参数说明 
	* @return boolean    返回类型 
	* @author leo
	* @throws 
	*/
	public List<String> range(String key,int start,int end){
		ListOperations<String, String> operations = redisTemplate.opsForList();
		return operations.range(key, start,end);
	}
	
	/** 
	* @Title: rpop 
	* @Description: 移除�?后一个元�? 
	* @param @param key
	* @param @return  参数说明 
	* @return boolean    返回类型 
	* @author leo
	* @throws 
	*/
	public boolean rpop(String key){
		boolean result = false;
		try {
			ListOperations<String, String> operations = redisTemplate.opsForList();
			operations.rightPop(key);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 
	* @Title: remove 
	* @Description: 根据值移除list的�?�（只要和�?�相同的都会移除�?
	* @param @param key list的名�?
	* @param @param index 从哪个下标开始移除（0为第�?个，-1为最后一个）
	* @param @param value  �? 
	* @return void    返回类型 
	* @author leo
	* @throws 
	*/
	public void remove(String key,int index,String value){
		ListOperations<String, String> operations = redisTemplate.opsForList();
		operations.remove(key,index,value);
	}
	
	/** 
	* @Title: hset 
	* @Description: 写hashMap
	* @param @param key
	* @param @param hashKey �?
	* @param @param value   �?
	* @param @return  参数说明 
	* @return boolean    返回类型 
	* @author leo
	* @throws 
	*/
	@SuppressWarnings("unchecked")
	public boolean hset(String key,String hashKey,String value){
		boolean result = false;
		try {
			HashOperations<String, String, String> hashOper = redisTemplate.opsForHash();			
			hashOper.put(key, hashKey, value);			
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 
	* @Title: hdel 
	* @Description: 删除键�?�对 
	* @param @param key
	* @param @param hashKey
	* @param @param value
	* @param @return  参数说明 
	* @return boolean    返回类型 
	* @author leo
	* @throws 
	*/
	@SuppressWarnings("unchecked")
	public boolean hdel(String key,String hashKey){
		boolean result = false;
		try {
			HashOperations<String, String, String> hashOper = redisTemplate.opsForHash();			
			hashOper.delete(key, hashKey);			
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 
	* @Title: hget 
	* @Description: 根据键获取�?? 
	* @param @param key
	* @param @param hashKey
	* @param @return  参数说明 
	* @return Object    返回类型 
	* @author leo
	* @throws 
	*/
	@SuppressWarnings("unchecked")
	public Object hget(Object key,Object hashKey){
		HashOperations<Object, Object, String> hashOper = redisTemplate.opsForHash();
		return hashOper.get(key, hashKey);
	}
	
	/** 
	* @Title: hvalues 
	* @Description: 根据redis的key获取hashValues 
	* @param @param key
	* @param @return  参数说明 
	* @return List<String>    返回类型 
	* @author leo
	* @throws 
	*/
	public List<String> hvalues(Object key){
		HashOperations<Object, Object, String> hashOper = redisTemplate.opsForHash();
		return hashOper.values(key);		
	}
	
	/** 
	* @Title: hkeys 
	* @Description: 根据redis的key获取hashKey
	* @param @param key
	* @param @return  参数说明 
	* @return Set<Object>    返回类型 
	* @author leo
	* @throws 
	*/
	public Set<Object> hkeys(Object key){
		HashOperations<Object, Object, String> hashOper = redisTemplate.opsForHash();
		return hashOper.keys(key);		
	}
	
	/** 
	* @Title: entries 
	* @Description: 根据redis的key获取hashMap
	* @param @param key
	* @param @return  参数说明 
	* @return Map<Object,String>    返回类型 
	* @author leo
	* @throws 
	*/
	public Map<String, Object> entries(Object key){
		HashOperations<Object, String, Object> hashOper = redisTemplate.opsForHash();
		return hashOper.entries(key);
	}

	/**
	 *         redis自增
	 * @param key
	 * @return
	 */
	public Long incr(String key){
		RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key,redisTemplate.getConnectionFactory());
		return redisAtomicLong.getAndIncrement();
	}

	/**
	* 为key设置过期时间
	* @Title: expire
	* @Description:
	* @param key
	* @param expireTime
	* @return java.lang.Boolean
	* @Author lchm
	* @date 2018/8/30
	* @throws Exception
	*/
	public Boolean expire(final String key,Long expireTime){
		return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
	}

	/**
	 * @Title: hashFieldExists
	 * @Description: 根据redis的hash key 判断hash中某个指定field
	 * @param @param key
	 * @param @param field 参数说明
	 * @return Boolean    返回类型
	 * @author Thstone
	 * @throws
	 */

    public Boolean hashFieldExists(final String key,final String field){
		HashOperations<Object, Object, String> hashOper = redisTemplate.opsForHash();
		return hashOper.hasKey(key,field);
	}
	/**
	 *          返回hashMap中所有的�?
	 * @param key
	 * @return
	 */
	public Set<String> hgetKeys(String key){
		try {
			HashOperations<String, String, String> hashOper = redisTemplate.opsForHash();
			return hashOper.keys(key);
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
