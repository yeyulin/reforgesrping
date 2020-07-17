package org.practice.beans.factory.support;

import org.practice.beans.BeanDefinition;
import org.practice.beans.factory.BeanInstanceException;
import org.practice.beans.factory.config.ConfigurableBeanFactory;
import org.practice.utils.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean的主体信息
 */
public class DefaultBeanFactory implements ConfigurableBeanFactory, BeanDefinitionRegister {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    private ClassLoader classLoader;


    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }

    public void registerBeanDefinition(String beanID, BeanDefinition bd) {
        beanDefinitionMap.put(beanID, bd);
    }

    public Object getBean(String beanId) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanId);
        if (null == beanDefinition) {
            return null;
        }
        String beanClassName = beanDefinition.getBeanClassName();
        ClassLoader classLoader =this.getClassLoader();
        try {
            Class<?> aClass = classLoader.loadClass(beanClassName);
            return aClass.newInstance();
        } catch (Exception e) {
            throw new BeanInstanceException("BeanInstanceException create Bean from " + beanClassName, e);
        }

    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public ClassLoader getClassLoader() {
        return (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }
}
