package com.googlecode.easyec.ehcache;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.util.Assert;

/**
 * @author JunJie
 */
public class EhcacheCacheManagerFactoryBean implements FactoryBean<EhCacheCacheManager>, InitializingBean {

    private net.sf.ehcache.CacheManager cacheManager;
    private EhCacheCacheManager ehCacheCacheManager;
    private boolean transactionAware;

    @Override
    public EhCacheCacheManager getObject() throws Exception {
        return ehCacheCacheManager;
    }

    @Override
    public Class<?> getObjectType() {
        return EhCacheCacheManager.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cacheManager, "EHCACHE CacheManager object cannot be null.");
        this.ehCacheCacheManager = new EhCacheCacheManager(this.cacheManager);
        this.ehCacheCacheManager.setTransactionAware(transactionAware);
    }

    /**
     * 设置EHCACHE缓存管理对象的实例
     *
     * @param cacheManager <code>CacheManager</code>
     */
    public void setCacheManager(net.sf.ehcache.CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * 标识是否支持事务
     *
     * @param transactionAware 事务通知标识
     */
    public void setTransactionAware(boolean transactionAware) {
        this.transactionAware = transactionAware;
    }
}
