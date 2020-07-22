package org.practice.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.practice.beans.factory.config.RuntimeBeanReference;
import org.practice.beans.factory.config.TypedStringValue;
import org.practice.beans.factory.support.BeanDefinitionValueResolver;
import org.practice.beans.factory.support.DefaultBeanFactory;
import org.practice.beans.factory.xml.XmlBeanDefinitionReader;
import org.practice.core.io.ClassPathResource;
import org.practice.dao.v2.AccountDao;

/**
 * @author yeyulin
 * @description:
 * @date 2020/7/22 11:24
 **/
public class BeanDefinitionValueResolverTest {
    @Test
    public void testResolveRuntimeBeanReference() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);

        RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");
        Object value = resolver.resolveValueIfNecessary(reference);

        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof AccountDao);
    }
    @Test(expected=RuntimeException.class)
    public void testResolveTypedStringValue() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));
        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
        String errorStringValue = "test";
        Object errorValue = resolver.resolveValueIfNecessary(errorStringValue);
        Assert.assertNotNull(errorValue);
        Assert.assertEquals("test", errorValue);
    }
}
