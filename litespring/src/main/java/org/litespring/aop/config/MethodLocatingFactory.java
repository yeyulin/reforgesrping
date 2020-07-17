package org.litespring.aop.config;

import org.litespring.beans.BeanUtils;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.BeanFactoryAware;
import org.litespring.beans.factory.FactoryBean;
import org.litespring.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 根据bean的名字和方法名，获取BeanDefinition,
 * 继而获取到bean的字节码，最后定位到Method.
 */
public class MethodLocatingFactory implements FactoryBean<Method>, BeanFactoryAware {

    private String targetBeanName;

    private String methodName;

    private Method method;

    /**
     * Set the name of the bean to locate the {@link Method} on.
     * <p>This property is required.
     *
     * @param targetBeanName the name of the bean to locate the {@link Method} on
     */
    public void setTargetBeanName(String targetBeanName) {
        this.targetBeanName = targetBeanName;
    }

    /**
     * Set the name of the {@link Method} to locate.
     * <p>This property is required.
     *
     * @param methodName the name of the {@link Method} to locate
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     *
     * @param beanFactory owning BeanFactory (never {@code null}).
     *                    The bean can immediately call methods on the factory.
     */
    public void setBeanFactory(BeanFactory beanFactory) {
        if (!StringUtils.hasText(this.targetBeanName)) {
            throw new IllegalArgumentException("Property 'targetBeanName' is required");
        }
        if (!StringUtils.hasText(this.methodName)) {
            throw new IllegalArgumentException("Property 'methodName' is required");
        }
        /**
         * 根据bean的name获取bean的字节码
         */
        Class<?> beanClass = beanFactory.getType(this.targetBeanName);
        if (beanClass == null) {
            throw new IllegalArgumentException("Can't determine type of bean with name '" + this.targetBeanName + "'");
        }

        this.method = BeanUtils.resolveSignature(this.methodName, beanClass);

        if (this.method == null) {
            throw new IllegalArgumentException("Unable to locate method [" + this.methodName +
                    "] on bean [" + this.targetBeanName + "]");
        }
    }

    public Method getObject() throws Exception {
        return this.method;
    }

    public Class<?> getObjectType() {
        return Method.class;
    }
}