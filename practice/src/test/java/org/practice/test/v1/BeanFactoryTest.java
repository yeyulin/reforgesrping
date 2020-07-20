package org.practice.test.v1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.practice.beans.BeanDefinition;
import org.practice.beans.factory.support.DefaultBeanFactory;
import org.practice.beans.factory.xml.XmlBeanDefinitionReader;
import org.practice.core.io.ClassPathResource;
import org.practice.service.v1.PetStoreService;

import static org.junit.Assert.*;

public class BeanFactoryTest {
    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);

    }

    @Test
    public void testGetBean() {

        //得到bean工厂的默认实现,通过加载配置文件
//        BeanFactory factory=new DefaultBeanFactory("petstore-v1.xml");
        String petStore = "petStore";
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));

        //通过Bean id得到Bean的基本定义
        BeanDefinition beanDefinition = factory.getBeanDefinition(petStore);
        System.out.println(beanDefinition.getBeanClassName());
        Assert.assertEquals("org.practice.service.v1.PetStoreService", beanDefinition.getBeanClassName());
        //通过Bean id 实例化bean
        PetStoreService petStoreService = (PetStoreService) factory.getBean(petStore);
        Assert.assertNotNull(petStoreService);

    }

    /**
     * 测试用例和空的类实现
     * 实现了根据xml文件的beanID生成相应实例的方法
     */
    @Test
    public void testGetBeanSingleton() {
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));
        BeanDefinition bd = factory.getBeanDefinition("petStoreSingleton");
        //默认是单例
        assertTrue(bd.isSingleton());

        assertFalse(bd.isPrototype());

        assertEquals(BeanDefinition.SCOPE_SINGLETON, bd.getScope());

        assertEquals("org.practice.service.v1.PetStoreService", bd.getBeanClassName());
        // 根据beanID获取类的实例
        PetStoreService petStore = (PetStoreService) factory.getBean("petStoreSingleton");
        assertNotNull(petStore);
        // 判断两次生成的实例是否一样
        PetStoreService petStore1 = (PetStoreService) factory.getBean("petStoreSingleton");
        assertTrue(petStore.equals(petStore1));
    }

    @Test
    public void testGetBeanPrototype() {
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));
        BeanDefinition bd = factory.getBeanDefinition("petStorePrototype");


        assertFalse(bd.isSingleton());
        assertTrue(bd.isPrototype());

        assertEquals(BeanDefinition.SCOPE_PROTOTYPE, bd.getScope());

        assertEquals("org.practice.service.v1.PetStoreService", bd.getBeanClassName());
        // 根据beanID获取类的实例
        PetStoreService petStore = (PetStoreService) factory.getBean("petStorePrototype");
        assertNotNull(petStore);
        // 判断两次生成的实例是否一样
        PetStoreService petStore1 = (PetStoreService) factory.getBean("petStorePrototype");
        assertNotEquals(petStore, petStore1);
    }
}
