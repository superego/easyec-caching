package com.googlecode.easyec.test;

import com.googlecode.easyec.caching.CacheService;
import com.googlecode.easyec.test.service.SimpleService;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @author JunJie
 */
@ContextConfiguration(locations = "classpath:applicationContext-caching.xml")
public class SimpleCacheTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private CacheService cacheService;

    @Resource
    private SimpleService simpleService;

    @Test
    public void myFirstTest() {
        Assert.notNull(cacheService);

        System.out.println(simpleService.getValue("key0_"));
        System.out.println(simpleService.getValue("key0_"));
        System.out.println(simpleService.getValue("key1_"));
    }
}
