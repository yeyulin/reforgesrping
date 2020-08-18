package org.practice.beans;

import org.practice.beans.factory.BeanCreationException;
import org.practice.beans.factory.support.AbstractBeanFactory;
import org.practice.beans.factory.support.BeanDefinitionValueResolver;

import java.lang.reflect.Constructor;

/**
 * @author yeyulin
 * @description:
 * @date 2020/8/7 17:03
 * 构造方法或者工厂类初始化bean的委托类
 **/
public class ConstructorResolver {
    private AbstractBeanFactory beanFactory;

    public ConstructorResolver(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object autowireConstructor(BeanDefinition bd) {
        // 构造方法
        Constructor<?> constructorToUse = null;
        // 构造参数
        Object[] argsToUse = null;
        Class<?> beanClass = null;
        try {
            beanClass = this.beanFactory.getClassLoader().loadClass(bd.getBeanClassName());
        } catch (ClassNotFoundException e) {
            throw new BeanCreationException(bd.getBeanName(), "Instantiation of bean failed, can't resolve class", e);
        }
        Constructor<?>[] candidates = beanClass.getConstructors();
        //封装
        BeanDefinitionValueResolver valueResolver =
                new BeanDefinitionValueResolver(this.beanFactory);
        // 构造函数的参数
        ConstructorArgument cargs = bd.getConstructorArgument();
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();
        // 找符合要求的构造函数
        for (int i = 0; i < candidates.length; i++) {

        }
        return beanFactory.getBean(bd.getBeanName());
    }
}
