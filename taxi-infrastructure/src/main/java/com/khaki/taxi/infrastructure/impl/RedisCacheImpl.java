package com.khaki.taxi.infrastructure.impl;

import cn.hutool.core.util.BooleanUtil;
import com.khaki.taxi.infrastructure.CacheAdapter;
import com.khaki.taxi.infrastructure.exception.CacheException;
import com.khaki.taxi.infrastructure.exception.CacheKeyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheImpl implements CacheAdapter {
    private static final Logger logger = LoggerFactory.getLogger(RedisCacheImpl.class);

    private StringRedisTemplate redisTemplate;

    public RedisCacheImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void set(String key, String value, Long expire) throws CacheException {
        try{
            this.redisTemplate.opsForValue().set(key,value,expire, TimeUnit.SECONDS);
        }catch (Exception e){
            logger.error("RedisCacheImpl-set失败",e);
            throw new CacheException();
        }

    }

    @Override
    public Optional<String> get(String key) throws CacheException {
        try{
            String value = this.redisTemplate.opsForValue().get(key);
            return Optional.ofNullable(value);
        }catch (Exception e){
            logger.error("RedisCacheImpl-get失败",e);
            throw new CacheException();
        }

    }

    @Override
    public void del(String key) throws CacheException, CacheKeyNotFoundException {
        try{
            Boolean delete = this.redisTemplate.delete(key);
            if(BooleanUtil.isFalse(delete)){
                throw new CacheKeyNotFoundException(String.format("%s删除失败",key));
            }
        }catch (Exception e){
            logger.error("删除key：{}失败",key,e);
            throw new CacheException();
        }

    }

    @Override
    public void setNx(String key, String value, Long expire) throws CacheException {
        try{
            this.redisTemplate.opsForValue().setIfAbsent(key,value,expire,TimeUnit.SECONDS);
        }catch (Exception e){
            logger.error("RedisCacheImpl-setNx失败",e);
            throw new CacheException();
        }

    }
}
