package org.practice.beans.factory.support;

import org.practice.beans.BeanDefinition;
import org.practice.beans.factory.BeanCreationException;
import org.practice.beans.factory.BeanInstanceException;
import org.practice.utils.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class DefaultBeanFactory extends AbstractBeanFactory implements BeanDefinitionRegister {
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private ClassLoader classLoader;

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }

    @Override
    public void registerBeanDefinition(String beanID, BeanDefinition bd) {
        beanDefinitionMap.put(beanID, bd);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanId);
        if (null == beanDefinition) {
            return null;
        }
        //是单例，创建后注册
        if (beanDefinition.isSingleton()) {
            Object bean = this.getSingleton(beanDefinition.getBeanClassName());
            if (bean == null) {
                bean = createBean(beanDefinition);
                this.registerSingleton(beanDefinition.getBeanName(), bean);
            }
            return bean;
        }
        return createBean(beanDefinition);
    }

    @Override
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getClassLoader() {
        return (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    protected Object createBean(BeanDefinition bd) throws BeanCreationException {
        String beanClassName = bd.getBeanClassName();
        ClassLoader classLoader = this.getClassLoader();
        try {
            Class<?> aClass = classLoader.loadClass(beanClassName);
            Object instance = aClass.newInstance();
            //是否单例
            boolean singleton = bd.isSingleton();
            if (singleton) {
                registerSingleton(beanClassName, instance);
            }
            return instance;
        } catch (Exception e) {
            throw new BeanInstanceException("BeanInstanceException create Bean from " + beanClassName, e);
        }
    }
}
