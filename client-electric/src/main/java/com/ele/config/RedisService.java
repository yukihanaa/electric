package com.ele.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Tao Hong Hui
 * @date 2018年6月29日 下午1:39:49
 * @description: redis 的服务类 （目前只是用来做session的共享，故配置简单，如果要使用redis来做其他的功能，配置类建议单独出来，而且需要做主从复制）
 */
@Service
@SuppressWarnings("all")
public class RedisService {
 
	/**
	 * redis数据惭怍模板，springboot自动配置的.
	 */
	@Autowired
    private RedisTemplate<Object,Object> redisTemplate;
	
	/**
	 * @function: 将对象放入redis中是采取将其序列化后在放入，获取是反序列化成对象.
	 * @author:Tao Hong Hui
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public void setObject(final String key,Object value) throws Exception {
		ValueOperations operations = redisTemplate.opsForValue();
        operations.set(key, value);
	}
	
	/**
	 * @function: 添加对象并且设置对象的过期时间.
	 * @author:Tao Hong Hui
	 * @param key
	 * @param value
	 * @param expireTimeSecond
	 * @throws Exception
	 */
	public void setObjectWithExpireTime(final String key,Object value,Long expireTime,TimeUnit timeUnit) throws Exception {
		ValueOperations operations = redisTemplate.opsForValue();
        operations.set(key, value);
        Boolean expire = redisTemplate.expire(key, expireTime, timeUnit);
        if(!expire) {
        	throw new Exception("缓存失败!");
        }
	}
	
	/**
	 * @function: 查询key是否存在.
	 * @author:Tao Hong Hui
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Boolean hasKey(final String key) throws Exception {
		return redisTemplate.hasKey(key);
	}
	
	/**
	 * @function: 读取key的数据.
	 * @author:Tao Hong Hui
	 * @param key
	 * @return
	 */
    public Object get(final String key) {
        ValueOperations operations = redisTemplate.opsForValue();
        return operations.get(key);
    }
    
    /**
     * @function: 删除缓存数据.
     * @author:Tao Hong Hui
     * @param key
     */
    public void del(final String key) {
    	redisTemplate.delete(key);
    }
}
