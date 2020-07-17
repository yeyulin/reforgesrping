package org.practice.beans.factory.support;

import org.practice.beans.BeanDefinition;

public interface BeanDefinitionRegister {
    BeanDefinition getBeanDefinition(String beanId);
    void registerBeanDefinition(String beanID, BeanDefinition bd);
}
