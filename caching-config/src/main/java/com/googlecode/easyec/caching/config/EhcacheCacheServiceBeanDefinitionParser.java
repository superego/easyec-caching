package com.googlecode.easyec.caching.config;

import com.googlecode.easyec.ehcache.EhcacheCacheManagerFactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.w3c.dom.Element;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * @author JunJie
 */
class EhcacheCacheServiceBeanDefinitionParser extends AbstractCacheServiceBeanDefinitionParser {

    @Override
    public BeanDefinition buildCustomCacheManagerBeanDefinition(Element element) {
        BeanDefinitionBuilder rawCacheManagerBuilder = genericBeanDefinition(EhCacheManagerFactoryBean.class);
        rawCacheManagerBuilder.addPropertyValue(
            "configLocation", getValue(element, "configLocation", "classpath:ehcache.xml")
        );
        rawCacheManagerBuilder.addPropertyValue(
            "shared", Boolean.parseBoolean(getValue(element, "shared", "false"))
        );

        rawCacheManagerBuilder.setDestroyMethodName("destroy");

        BeanDefinitionBuilder cacheManagerBuilder = genericBeanDefinition(EhcacheCacheManagerFactoryBean.class);
        cacheManagerBuilder.addPropertyValue(
            "cacheManager", rawCacheManagerBuilder.getBeanDefinition()
        );
        cacheManagerBuilder.addPropertyValue(
            "transactionAware", Boolean.parseBoolean(getValue(element, "transactionAware", "false"))
        );

        return cacheManagerBuilder.getBeanDefinition();
    }
}
