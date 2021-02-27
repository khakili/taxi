package com.khaki.taxi.infrastructure.impl;

import com.khaki.taxi.infrastructure.CacheAdapter;
import com.khaki.taxi.infrastructure.exception.CacheException;
import com.khaki.taxi.infrastructure.exception.CacheKeyNotFoundException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheImpl implements CacheAdapter {
    private StringRedisTemplate redisTemplate;

    public RedisCacheImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void set(String key, String value, Long expire) throws CacheException {
        this.redisTemplate.opsForValue().set(key,value,expire, TimeUnit.SECONDS);
    }

    @Override
    public Optional<String> get(String key) throws CacheException {
        return Optional.empty();
    }

    @Override
    public void del(String key) throws CacheException, CacheKeyNotFoundException {
        try{
            Boolean delete = this.redisTemplate.delete(key);
            if(!delete){
                throw new CacheKeyNotFoundException(String.format("weiffjjj",key));
            }
        }catch (Exception e){

        }

    }

    @Override
    public void setNx(String key, String value, Long expire) throws CacheException {
        this.redisTemplate.opsForValue().setIfAbsent(key,value,expire,TimeUnit.SECONDS);
    }
}
