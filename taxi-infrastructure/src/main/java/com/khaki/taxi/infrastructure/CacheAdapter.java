package com.khaki.taxi.infrastructure;

import com.khaki.taxi.infrastructure.exception.CacheException;
import com.khaki.taxi.infrastructure.exception.CacheKeyNotFoundException;

import java.util.Optional;

public interface CacheAdapter {

    void set(String key,String value,Long expire) throws CacheException;

    Optional<String> get(String key) throws CacheException;

    void del(String key) throws CacheException, CacheKeyNotFoundException;

    void setNx(String key,String value,Long expire) throws CacheException;
}
