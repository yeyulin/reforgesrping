package org.practice.context.support;

import org.practice.beans.factory.support.DefaultBeanFactory;
import org.practice.beans.factory.xml.XmlBeanDefinitionReader;
import org.practice.context.ApplicationContext;
import org.practice.core.io.Resource;

/**
 * @author yeyulin
 * @description:
 * @date 2020/6/28 14:20
 **/
public abstract class AbstractApplicationContext implements ApplicationContext {
    protected String path;
    private DefaultBeanFactory factory = null;

    public AbstractApplicationContext(String path) {
        this.path = path;
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = this.getResourceByPath(path);
        reader.loadBeanDefinitions(resource);

    }
    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

    protected abstract Resource getResourceByPath(String path);
}
