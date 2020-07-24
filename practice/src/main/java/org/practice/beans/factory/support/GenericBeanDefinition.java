package org.practice.beans.factory.support;

import org.practice.beans.BeanDefinition;
import org.practice.beans.PropertyValue;

import java.util.List;

/**
 * @author yeyulin
 * @description:
 * @date 2020/6/23 10:11
 **/
public class GenericBeanDefinition implements BeanDefinition {
    private String beanName;
    private String className;
    private boolean singleton = true;
    private boolean prototype = false;
    private String scope = SCOPE_DEFAULT;

    public GenericBeanDefinition(String beanName, String beanClassName) {
        this.beanName = beanName;
        this.className = beanClassName;
    }

    @Override
    public boolean isSingleton() {
        return this.singleton;
    }

    @Override
    public boolean isPrototype() {
        return this.prototype;
    }

    @Override
    public String getScope() {
        return this.scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    @Override
    public String getBeanClassName() {
        return this.className;
    }

    @Override
    public String getBeanName() {
        return this.beanName;
    }

    @Override
    public List<PropertyValue> getPropertyValues() {
        return null;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

}
