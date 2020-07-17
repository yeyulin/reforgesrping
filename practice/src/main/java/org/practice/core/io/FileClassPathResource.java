package org.practice.core.io;


import org.practice.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class FileClassPathResource implements Resource {

    private final String path;
    private ClassLoader classLoader;

    public FileClassPathResource(String path) {
        this(path, ClassUtils.getDefaultClassLoader());
    }

    public FileClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = classLoader;
    }

    public InputStream getInputStream() throws FileNotFoundException {
        InputStream is = this.classLoader.getResourceAsStream(this.path);

        if (is == null) {
            throw new FileNotFoundException(path + " cannot be opened");
        }
        return is;
    }
}
