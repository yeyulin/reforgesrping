package org.practice.beans.factory.config;

import org.practice.beans.factory.BeanFactory;

/**
 * @author yeyulin
 * @description:
 * @date 2020/6/28 16:19
 **/
public interface ConfigurableBeanFactory extends BeanFactory {
    void setClassLoader(ClassLoader classLoader);

    ClassLoader getClassLoader();

}
