package com.googlecode.easyec.test.service.impl;

import com.googlecode.easyec.test.service.SimpleService;
import org.springframework.cache.annotation.Cacheable;

import static com.googlecode.easyec.caching.CacheService.DEFAULT_CACHE;

/**
 * @author JunJie
 */
public class SimpleServiceImpl implements SimpleService {

    @Override
    @Cacheable(value = DEFAULT_CACHE, key = "#key")
    public String getValue(String key) {
        return key + 1;
    }
}
