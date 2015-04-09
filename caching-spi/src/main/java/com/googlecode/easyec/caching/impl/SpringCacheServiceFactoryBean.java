package com.googlecode.easyec.caching.impl;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;

/**
 * 支持Spring缓存框架的工厂类
 *
 * @author JunJie
 */
public class SpringCacheServiceFactoryBean implements FactoryBean<SpringCacheService>, InitializingBean {

    private SpringCacheService cacheService;
    private CacheManager cacheManager;

    @Override
    public SpringCacheService getObject() throws Exception {
        return cacheService;
    }

    @Override
    public Class<?> getObjectType() {
        return SpringCacheService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    /**
     * 设置缓存管理器的实现对象
     *
     * @param cacheManager 缓存管理对象
     */
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cacheManager, "CacheManager object cannot be null.");
        cacheService = new SpringCacheService(cacheManager);
    }
}
