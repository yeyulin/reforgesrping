package org.practice.beans.factory;

import org.practice.beans.BeanDefinition;

public interface BeanFactory {
    Object getBean(String beanId);

    BeanDefinition getBeanDefinition(String beanId);


}
