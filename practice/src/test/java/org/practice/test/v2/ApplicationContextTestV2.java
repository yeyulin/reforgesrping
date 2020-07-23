package org.practice.test.v2;

import org.junit.Test;
import org.practice.context.ApplicationContext;
import org.practice.context.support.ClassPathXmlApplicationContext;
import org.practice.dao.v2.AccountDao;
import org.practice.dao.v2.ItemDao;
import org.practice.service.v2.PetStoreService;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author yeyulin
 * @description:
 * @date 2020/7/23 15:31
 **/
public class ApplicationContextTestV2 {
    @Test
    public void testGetBeanProperty() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v2.xml");
        PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");

        assertNotNull(petStore.getAccountDao());
        assertNotNull(petStore.getItemDao());
        assertTrue(petStore.getAccountDao() instanceof AccountDao);
        assertTrue(petStore.getItemDao() instanceof ItemDao);

        assertTrue(petStore.getAccountDao() instanceof AccountDao);
        assertTrue(petStore.getItemDao() instanceof ItemDao);
        assertEquals("hjs",petStore.getOwner());
        assertEquals(2, petStore.getVersion());
    }
}
