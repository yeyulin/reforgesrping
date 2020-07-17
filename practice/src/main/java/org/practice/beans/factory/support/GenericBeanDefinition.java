package org.practice.beans.factory.support;

import org.practice.beans.BeanDefinition;

/**
 * @author yeyulin
 * @description:
 * @date 2020/6/23 10:11
 **/
public class GenericBeanDefinition implements BeanDefinition {
    private String id;
    private String className;

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.className = beanClassName;
    }

    public String getBeanClassName() {
        return this.className;
    }
}
