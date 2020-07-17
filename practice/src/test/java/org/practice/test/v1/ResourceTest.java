package org.practice.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.practice.core.io.Resource;
import org.practice.core.io.XmlClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yeyulin
 * @description:
 * @date 2020/6/28 9:30
 **/
public class ResourceTest {
    @Test
    public void testClassPathResource() throws IOException {
        Resource r = new XmlClassPathResource("petstore-v1.xml");
        InputStream is = null;

        try {
            is = r.getInputStream();
            // 注意：这个测试其实并不充分！！
            Assert.assertNotNull(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
