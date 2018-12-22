package com.example.demo.config.bean.iter;

import java.util.concurrent.TimeUnit;

public interface RedisClient<T> {

	public void set(String key, T value);

	public void set(String key, T value, Long expire);

	public void set(String key, T value, Long expire, TimeUnit timeUnit);

	public T get(String key);

	public Long getExpire(String key);

}
