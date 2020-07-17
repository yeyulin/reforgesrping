package org.practice.core.io;

import org.practice.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author yeyulin
 * @description:
 * @date 2020/6/28 9:34
 **/
public class ClassPathResource implements Resource {
    private String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, ClassUtils.getDefaultClassLoader());
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = classLoader;
    }
    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        InputStream is = this.classLoader.getResourceAsStream(this.path);

        if (is == null) {
            throw new FileNotFoundException(path + " cannot be opened");
        }
        return is;
    }

}
