package org.practice.beans.factory.support;

import org.practice.beans.BeanDefinition;
import org.practice.beans.PropertyValue;
import org.practice.beans.SimpleTypeConverter;
import org.practice.beans.factory.BeanCreationException;
import org.practice.beans.factory.BeanInstanceException;
import org.practice.utils.ClassUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class DefaultBeanFactory extends AbstractBeanFactory implements BeanDefinitionRegister {
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private ClassLoader classLoader;

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }

    @Override
    public void registerBeanDefinition(String beanID, BeanDefinition bd) {
        beanDefinitionMap.put(beanID, bd);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanId);
        if (null == beanDefinition) {
            return null;
        }
        //是单例，创建后注册
        if (beanDefinition.isSingleton()) {
            Object bean = this.getSingleton(beanDefinition.getBeanClassName());
            if (bean == null) {
                bean = createBean(beanDefinition);
                this.registerSingleton(beanDefinition.getBeanName(), bean);
            }
            return bean;
        }
        return createBean(beanDefinition);
    }

    @Override
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getClassLoader() {
        return (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    protected Object createBean(BeanDefinition bd) throws BeanCreationException {
        //创建实例
        Object bean = instantiateBean(bd);
        //设置属性
        populateBean(bd, bean);
        return bean;
    }

    private void populateBean(BeanDefinition bd, Object bean) {
        // pvs 是在xml文件中配置的property
        // pds 是PetStoreService中的定义
        // 所以要把pvs和pds进行遍历，(双重循环), 符合要求，则使用set方法进行注入
        List<PropertyValue> pvs = bd.getPropertyValues();

        if (pvs == null || pvs.isEmpty()) {
            return;
        }
        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this);
        SimpleTypeConverter converter = new SimpleTypeConverter();
        try {
            for (PropertyValue pv : pvs) {
                String propertyName = pv.getName();
                Object originalValue = pv.getValue();
                Object resolvedValue = valueResolver.resolveValueIfNecessary(originalValue);
                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor pd : pds) {
                    if (pd.getName().equals(propertyName)) {
                        Object convertedValue = converter.convertIfNecessary(resolvedValue, pd.getPropertyType());
                        pd.getWriteMethod().invoke(bean, convertedValue);
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            throw new BeanCreationException("Failed to obtain BeanInfo for class [" + bd.getBeanClassName() + "]", ex);
        }
    }

    /**
     * 创建实例
     *
     * @param bd
     * @return
     */
    private Object instantiateBean(BeanDefinition bd) {
        String beanClassName = bd.getBeanClassName();
        ClassLoader classLoader = this.getClassLoader();
        try {
            Class<?> aClass = classLoader.loadClass(beanClassName);
            Object instance = aClass.newInstance();
            //是否单例
            boolean singleton = bd.isSingleton();
            if (singleton) {
                registerSingleton(beanClassName, instance);
            }
            return instance;
        } catch (Exception e) {
            throw new BeanInstanceException("BeanInstanceException create Bean from " + beanClassName, e);
        }
    }
}
