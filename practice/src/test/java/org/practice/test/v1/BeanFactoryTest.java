package org.practice.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.practice.beans.BeanDefinition;
import org.practice.beans.factory.support.DefaultBeanFactory;
import org.practice.beans.factory.xml.XmlBeanDefinitionReader;
import org.practice.core.io.XmlClassPathResource;
import org.practice.service.v1.PetStoreService;

public class BeanFactoryTest {
    @Test
    public void testGetBean() {

        //得到bean工厂的默认实现,通过加载配置文件
//        BeanFactory factory=new DefaultBeanFactory("petstore-v1.xml");
        String petStore = "petStore";
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new XmlClassPathResource("petstore-v1.xml"));

        //通过Bean id得到Bean的基本定义
        BeanDefinition beanDefinition = factory.getBeanDefinition(petStore);
        System.out.println(beanDefinition.getBeanClassName());
        Assert.assertEquals("org.practice.service.v1.PetStoreService", beanDefinition.getBeanClassName());
        //通过Bean id 实例化bean
        PetStoreService petStoreService = (PetStoreService) factory.getBean(petStore);
        Assert.assertNotNull(petStoreService);


    }
}
