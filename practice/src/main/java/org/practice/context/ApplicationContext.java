package org.practice.context;

/**
 * @author yeyulin
 * @description:
 * @date 2020/6/28 14:13
 * @since soc2.2
 **/
public interface ApplicationContext {
    Object getBean(String beanId);
}
