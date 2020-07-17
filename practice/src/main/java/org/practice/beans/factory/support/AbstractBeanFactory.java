package org.practice.beans.factory.support;

import org.practice.beans.BeanDefinition;
import org.practice.beans.factory.BeanCreationException;
import org.practice.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author yeyulin
 * @description:
 * @date 2020/7/17 16:46
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    /**
     * 创建bean
     * @param bd
     * @return
     * @throws BeanCreationException
     */
    protected abstract Object createBean(BeanDefinition bd) throws BeanCreationException;

}
