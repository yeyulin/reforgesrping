package org.practice.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.practice.context.ApplicationContext;
import org.practice.context.support.ClassPathXmlApplicationContext;
import org.practice.service.v1.PetStoreService;

/**
 * @author yeyulin
 * @description:
 * @date 2020/6/28 14:10
 **/
public class ApplicationContextTest {

    @Test
    public void testGenBen(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");
        Assert.assertNotNull(petStore);
    }
}
