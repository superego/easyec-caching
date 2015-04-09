package com.googlecode.easyec.caching.config;

import com.googlecode.easyec.caching.impl.SpringCacheServiceFactoryBean;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * 缓存业务实现类的抽象的Bean定义的解析类。
 * 该类实现了如何获取缓存业务类的id名称。
 *
 * @author JunJie
 */
public abstract class AbstractCacheServiceBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return SpringCacheServiceFactoryBean.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        super.doParse(element, parserContext, builder);

        String cacheManagerBeanName = resolveCacheManagerBeanName(element);
        // 注册CacheManager Bean对象的定义
        BeanDefinition definition = buildCustomCacheManagerBeanDefinition(element);
        if (definition != null) {
            registerBeanDefinition(
                new BeanDefinitionHolder(
                    definition, cacheManagerBeanName
                ), parserContext.getRegistry()
            );

            postCustomCacheManagerBeanDefinition(builder, cacheManagerBeanName);
        }
    }

    @Override
    protected String resolveId(Element element, AbstractBeanDefinition definition, ParserContext parserContext)
    throws BeanDefinitionStoreException {
        Node parent = element.getParentNode();
        if (parent instanceof Element) {
            String idVal = ((Element) parent).getAttribute(ID_ATTRIBUTE);
            if (StringUtils.hasLength(idVal)) return idVal;
            else return "cacheService";
        }

        return super.resolveId(element, definition, parserContext);
    }

    /**
     * 解析元素cache-manager的值内容。
     * 该名字将作为缓存管理器对象的实例名，
     * 被Spring进行管理。
     *
     * @param element w3c元素对象
     */
    protected String resolveCacheManagerBeanName(Element element) {
        Node parent = element.getParentNode();
        Assert.isTrue(parent instanceof Element, "Parent of element must be a type of element. [" + element.getLocalName() + "].");

        String aVal = ((Element) parent).getAttribute("cache-manager");
        return StringUtils.hasLength(aVal) ? aVal : "cacheManager";
    }

    /**
     * 获取元素的属性值，如果属性值没有设置，
     * 则返回给定的默认值
     *
     * @param element     w3c元素对象
     * @param attributeId 属性名称
     * @param defaultVal  默认的属性值
     */
    protected String getValue(Element element, String attributeId, String defaultVal) {
        String attributeVal = element.getAttribute(attributeId);
        if (StringUtils.hasLength(attributeVal)) {
            return attributeVal;
        }

        return defaultVal;
    }

    /**
     * 自定义缓存管理器Bean定义的后置方法
     *
     * @param builder              当前Bean定义的构建器对象
     * @param cacheManagerBeanName 缓存管理器Bean名称
     */
    protected void postCustomCacheManagerBeanDefinition(BeanDefinitionBuilder builder, String cacheManagerBeanName) {
        builder.addPropertyValue("cacheManager", new RuntimeBeanReference(cacheManagerBeanName));
    }

    /**
     * 构建一个自定义的缓存管理器Bean的定义
     *
     * @param element w3c元素对象
     */
    abstract public BeanDefinition buildCustomCacheManagerBeanDefinition(Element element);
}
