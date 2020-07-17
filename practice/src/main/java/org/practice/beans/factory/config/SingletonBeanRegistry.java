package org.practice.beans.factory.config;

/**
 * @author yeyulin
 * @description:
 * @date 2020/7/17 16:29
 **/
public interface SingletonBeanRegistry {
    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);
}
