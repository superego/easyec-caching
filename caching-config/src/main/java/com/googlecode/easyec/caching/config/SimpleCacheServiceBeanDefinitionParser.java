package com.googlecode.easyec.caching.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * 简单的缓存服务Bean定义的解析类
 *
 * @author JunJie
 */
class SimpleCacheServiceBeanDefinitionParser extends AbstractCacheServiceBeanDefinitionParser {

    @Override
    public BeanDefinition buildCustomCacheManagerBeanDefinition(Element element) {
        List<String> cacheNames = _resolveCacheNames(element.getChildNodes());

        BeanDefinitionBuilder builder = genericBeanDefinition(ConcurrentMapCacheManager.class);
        if (!cacheNames.isEmpty()) {
            builder.addPropertyValue("cacheNames", cacheNames);
        }

        return builder.getBeanDefinition();
    }

    private List<String> _resolveCacheNames(NodeList nodes) {
        List<Element> elements = _findChildElements(nodes);
        if (elements.isEmpty()) return emptyList();

        Element element = elements.get(0);
        if ("dynamic-cache".equals(element.getLocalName())) {
            return emptyList();
        }

        List<Element> cacheElements = _findChildElements(element.getChildNodes());
        if (cacheElements.isEmpty()) return emptyList();

        List<String> cacheNames = new ArrayList<String>();
        for (Element cacheEle : cacheElements) {
            cacheNames.add(cacheEle.getAttribute("name"));
        }

        return cacheNames;
    }

    /* 查找子元素列表 */
    private List<Element> _findChildElements(NodeList nodes) {
        if (nodes == null || nodes.getLength() == 0) return emptyList();
        List<Element> elements = new ArrayList<Element>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (!(node instanceof Element)) continue;

            elements.add((Element) node);
        }

        return elements;
    }
}
