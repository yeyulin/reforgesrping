package org.practice.beans.factory.support;

import org.practice.beans.factory.config.RuntimeBeanReference;
import org.practice.beans.factory.config.TypedStringValue;

/**
 * @author yeyulin
 * @description: 用于把beanID生成相应的实例 封装
 * @date 2020/7/22 11:25
 **/
public class BeanDefinitionValueResolver {
    private final AbstractBeanFactory beanFactory;

    public BeanDefinitionValueResolver(AbstractBeanFactory factory) {
        this.beanFactory = factory;
    }

    public Object resolveValueIfNecessary(Object value) {
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference ref = (RuntimeBeanReference) value;
            String refName = ref.getBeanName();
            return this.beanFactory.getBean(refName);
        }else if(value instanceof TypedStringValue){
            return ((TypedStringValue) value).getValue();
        } else {
            throw new RuntimeException("error");
        }
    }
}
