package com.googlecode.easyec.caching.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cache.support.NoOpCacheManager;
import org.w3c.dom.Element;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * 默认不做缓存的bean定义解析器类。
 *
 * @author JunJie
 */
class NoOpCacheServiceBeanDefinitionParser extends AbstractCacheServiceBeanDefinitionParser {

    @Override
    public BeanDefinition buildCustomCacheManagerBeanDefinition(Element element) {
        return genericBeanDefinition(NoOpCacheManager.class).getBeanDefinition();
    }
}
