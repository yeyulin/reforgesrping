package org.practice.context;

/**
 * @author yeyulin
 * @description:
 * @date 2020/6/28 14:13
 * @since soc2.2
 **/
public interface ApplicationContext {
    /**
     * 通过BeanID得到
     * @param beanId
     * @return
     */
    Object getBean(String beanId);
}
