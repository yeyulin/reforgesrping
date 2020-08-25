package org.practice.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.practice.beans.factory.BeanCreationException;
import org.practice.beans.factory.support.AbstractBeanFactory;
import org.practice.beans.factory.support.BeanDefinitionValueResolver;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @author yeyulin
 * @description:
 * @date 2020/8/7 17:03
 * 构造方法或者工厂类初始化bean的委托类
 **/
public class ConstructorResolver {
    protected final Log logger = LogFactory.getLog(getClass());
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
            //得到构造函数的数量
            Class<?>[] parameterTypes = candidates[i].getParameterTypes();
            if (parameterTypes.length != cargs.getArgumentCount()) {
                continue;
            }

            argsToUse = new Object[parameterTypes.length];
            boolean result = this.valuesMatchTypes(parameterTypes,
                    cargs.getArgumentValues(),
                    argsToUse,
                    valueResolver,
                    typeConverter);
            // 找到匹配的构造函数
            if (result) {
                constructorToUse = candidates[i];
                break;
            }
            //找不到一个合适的构造函数
            if (constructorToUse == null) {
                throw new BeanCreationException(bd.getBeanName(), "can't find a apporiate constructor");
            }
        }
        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            throw new BeanCreationException(bd.getBeanName(), "can't find a create instance using " + constructorToUse);
        }
    }

    /**
     * 配置文件和构造函数是否匹配
     *
     * @param parameterTypes 当前bean的构造函数
     * @param valueHolders
     * @param argsToUse
     * @param valueResolver
     * @param typeConverter
     * @return
     */
    private boolean valuesMatchTypes(Class<?>[] parameterTypes,
                                     List<ConstructorArgument.ValueHolder> valueHolders,
                                     Object[] argsToUse,
                                     BeanDefinitionValueResolver valueResolver,
                                     SimpleTypeConverter typeConverter) {
        //以下标为顺序
        for (int i = 0; i < parameterTypes.length; i++) {
            ConstructorArgument.ValueHolder valueHolder = valueHolders.get(i);
            //获取参数的值，可能是TypedStringValue, 也可能是RuntimeBeanReference
            Object originalValue = valueHolder.getValue();
            try {
                //获得真正的值 是bean还是具体的值
                Object resolvedValue = valueResolver.resolveValueIfNecessary(originalValue);
                //如果参数类型是 int, 但是值是字符串,例如"3",还需要转型
                //如果转型失败，则抛出异常。说明这个构造函数不可用
                //是否与构造函数适配
                Object convertedValue = typeConverter.convertIfNecessary(resolvedValue, parameterTypes[i]);
                //转型成功，记录下来
                argsToUse[i] = convertedValue;
            } catch (Exception e) {
                logger.error(e);
                return false;
            }
        }
        return true;
    }
}
