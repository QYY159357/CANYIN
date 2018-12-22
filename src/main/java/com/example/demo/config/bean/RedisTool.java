package com.example.demo.config.bean;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.config.bean.iter.RedisClient;


@Component
public class RedisTool<T> implements RedisClient<T> {

	@Resource
	private RedisTemplate<String,T> redisTemplate;

	public void set(String key, T value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public void set(String key, T value, Long expire) {
		redisTemplate.opsForValue().set(key, value, expire);
	}

	public void set(String key, T value, Long expire, TimeUnit timeUnit) {
		redisTemplate.opsForValue().set(key, value, expire, timeUnit);
	}

	public T get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public Long getExpire(String key) {
		return redisTemplate.getExpire(key);
	}

}
