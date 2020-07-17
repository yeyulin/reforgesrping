package org.practice.context.support;

import org.practice.core.io.Resource;
import org.practice.core.io.XmlClassPathResource;

/**
 * @author yeyulin
 * @description:
 * @date 2020/6/28 14:14
 **/
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    public ClassPathXmlApplicationContext(String path) {
        super(path);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new XmlClassPathResource(path);
    }



}
