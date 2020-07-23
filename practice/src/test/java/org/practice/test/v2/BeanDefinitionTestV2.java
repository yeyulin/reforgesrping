package org.practice.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.practice.beans.BeanDefinition;
import org.practice.beans.PropertyValue;
import org.practice.beans.factory.config.RuntimeBeanReference;
import org.practice.beans.factory.support.DefaultBeanFactory;
import org.practice.beans.factory.xml.XmlBeanDefinitionReader;
import org.practice.core.io.ClassPathResource;

import java.util.List;

/**
 * @author yeyulin
 * @description:
 * @date 2020/7/23 15:34
 **/
public class BeanDefinitionTestV2 {
    @Test
    public void testGetBeanDefinition() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));

        BeanDefinition bd = factory.getBeanDefinition("petStore");

        List<PropertyValue> pvs = bd.getPropertyValues();

        Assert.assertTrue(pvs.size() == 4);
        {
            PropertyValue pv = this.getPropertyValue("accountDao", pvs);

            Assert.assertNotNull(pv);

            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }

        {
            PropertyValue pv = this.getPropertyValue("itemDao", pvs);

            Assert.assertNotNull(pv);

            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }
    }

    private PropertyValue getPropertyValue(String name, List<PropertyValue> pvs){
        for(PropertyValue pv : pvs){
            if(pv.getName().equals(name)){
                return pv;
            }
        }
        return null;
    }
}
