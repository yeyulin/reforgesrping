package org.practice.beans.factory.config;

/**
 * @author yeyulin
 * @description:
 * @date 2020/7/22 14:19
 **/
public class RuntimeBeanReference {
    private final String beanName;

    public RuntimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
