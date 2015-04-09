package com.googlecode.easyec.caching.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 默认的缓存框架命名空间处理类
 *
 * @author JunJie
 */
public class DefaultCacheServiceNamespaceHandler extends NamespaceHandlerSupport {

    protected static final String ROOT_NAME = "service";

    @Override
    public void init() {
        registerBeanDefinitionParser("noOpCache", new NoOpCacheServiceBeanDefinitionParser());
        registerBeanDefinitionParser("ehcache", new EhcacheCacheServiceBeanDefinitionParser());
        registerBeanDefinitionParser("simple", new SimpleCacheServiceBeanDefinitionParser());
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String localName = parserContext.getDelegate().getLocalName(element);
        if (ROOT_NAME.equals(localName)) {
            NodeList children = element.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node item = children.item(i);
                if (!(item instanceof Element)) continue;

                BeanDefinition beanDefinition = super.parse((Element) item, parserContext);
                if (beanDefinition == null) {
                    String name = parserContext.getDelegate().getLocalName(item);
                    parserContext.getReaderContext().fatal(
                        "Cannot parse the element [" + name + "].", item
                    );
                }

                break;
            }
        }

        return null;
    }
}
