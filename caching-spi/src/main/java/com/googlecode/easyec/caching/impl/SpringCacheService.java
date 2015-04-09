package com.googlecode.easyec.caching.impl;

import com.googlecode.easyec.caching.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;

/**
 * 支持Spring缓存框架的缓存业务实现类
 *
 * @author JunJie
 */
public class SpringCacheService implements CacheService {

    /**
     * 日志对象
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private CacheManager cacheManager;

    public SpringCacheService(CacheManager cacheManager) {
        Assert.notNull(cacheManager, "CacheManager cannot be null.");
        this.cacheManager = cacheManager;
    }

    @Override
    public Object get(Object key) {
        return get(DEFAULT_CACHE, key);
    }

    @Override
    public Object get(String cacheName, Object key) {
        Cache cache = getCache(cacheName);
        if (cache == null) {
            logger.warn("No Cache was named [{}], so ignore this operation.", cacheName);

            return null;
        }

        try {
            Cache.ValueWrapper wrapper = cache.get(key);
            return wrapper != null ? wrapper.get() : null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return null;
        }
    }

    @Override
    public boolean put(Object key, Object object) {
        return put(DEFAULT_CACHE, key, object);
    }

    @Override
    public boolean put(String cacheName, Object key, Object object) {
        Cache cache = getCache(cacheName);
        if (cache == null) {
            logger.warn("No Cache was named [{}], so ignore this operation.", cacheName);

            return false;
        }

        try {
            cache.put(key, object);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return false;
        }
    }

    @Override
    public void removeAllValues(String cacheName) {
        Cache cache = getCache(cacheName);
        if (cache == null) {
            logger.warn("No Cache was named [{}], so ignore this operation.", cacheName);

            return;
        }

        try {
            cache.clear();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean removeValue(Object cacheKey) {
        return removeValue(DEFAULT_CACHE, cacheKey);
    }

    @Override
    public boolean removeValue(String cacheName, Object cacheKey) {
        Cache cache = getCache(cacheName);
        if (cache == null) {
            logger.warn("No Cache was named [{}], so ignore this operation.", cacheName);

            return false;
        }

        try {
            cache.evict(cacheKey);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return false;
        }
    }

    /**
     * 获取<code>CacheManager</code>对象实例
     */
    protected CacheManager getCacheManager() {
        return cacheManager;
    }

    /**
     * 获取封装的缓存对象实例
     *
     * @param cacheName 缓存区名称
     */
    @SuppressWarnings("unchecked")
    protected Cache getCache(String cacheName) {
        return cacheManager.getCache(cacheName);
    }
}
