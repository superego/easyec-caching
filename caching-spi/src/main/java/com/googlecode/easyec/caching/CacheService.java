package com.googlecode.easyec.caching;

/**
 * 缓存服务定义接口。
 *
 * @author JunJie
 */
public interface CacheService {

    /**
     * 全局默认的缓存区名称
     */
    String DEFAULT_CACHE = "globalCache";

    /**
     * 通过给定的key，从全局的cache中获取缓存的内容。
     *
     * @param key 缓存的key
     * @return key对应的值内容
     */
    Object get(Object key);

    /**
     * 通过给定的缓存名和key，获取缓存的内容。
     *
     * @param cacheName 缓存名
     * @param key       缓存的key
     * @return key对应的值内容
     */
    Object get(String cacheName, Object key);

    /**
     * 向全局的缓存中存放一个缓存对象。
     *
     * @param key    缓存的KEY
     * @param object 缓存对象实例
     * @return 存放成功，返回true；否则返回false
     */
    boolean put(Object key, Object object);

    /**
     * 向给定的缓存名中存放一个缓存对象。
     *
     * @param cacheName 缓存名
     * @param key       缓存的KEY
     * @param object    缓存对象实例
     * @return 存放成功，返回true；否则返回false
     */
    boolean put(String cacheName, Object key, Object object);

    /**
     * 通过给定的缓存名，清空此缓存中缓存的信息。
     *
     * @param cacheName 缓存名
     */
    void removeAllValues(String cacheName);

    /**
     * 通过给定的key，清除全局的缓存信息。
     *
     * @param cacheKey 缓存的key
     * @return 移除缓存成功，返回true；否则返回false
     */
    boolean removeValue(Object cacheKey);

    /**
     * 通过给定的缓存名和key，清除其缓存信息。
     *
     * @param cacheName 缓存名
     * @param cacheKey  缓存的key
     * @return 移除缓存成功，返回true；否则返回false
     */
    boolean removeValue(String cacheName, Object cacheKey);
}
