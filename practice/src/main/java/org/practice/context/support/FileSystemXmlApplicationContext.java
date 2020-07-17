package org.practice.context.support;

import org.practice.core.io.FileClassPathResource;
import org.practice.core.io.Resource;

/**
 * @author yeyulin
 * @description:
 * @date 2020/6/28 14:15
 **/
public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

    public FileSystemXmlApplicationContext(String path) {
        super(path);
    }


    @Override
    protected Resource getResourceByPath(String path) {
        return new FileClassPathResource(path);
    }
}
