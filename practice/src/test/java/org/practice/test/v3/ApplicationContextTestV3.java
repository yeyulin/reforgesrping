package org.practice.test.v3;

import org.junit.Assert;
import org.junit.Test;
import org.practice.context.ApplicationContext;
import org.practice.context.support.ClassPathXmlApplicationContext;
import org.practice.service.v3.PetStoreService;

/**
 * @author yeyulin
 * @description:
 * @date 2020/8/7 9:43
 **/
public class ApplicationContextTestV3 {
    @Test
    public void testGetBeanProperty() {
        ApplicationContext context=new ClassPathXmlApplicationContext("petstore-v3.xml");
        PetStoreService petStore = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(petStore.getAccountDao());
        Assert.assertNotNull(petStore.getItemDao());
        Assert.assertEquals(1, petStore.getVersion());
    }
}
