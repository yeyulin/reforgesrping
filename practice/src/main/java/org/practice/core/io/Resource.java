package org.practice.core.io;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author yeyulin
 * @description:
 * @date 2020/6/28 9:32
 * @since soc2.2
 **/
public interface Resource {
    InputStream getInputStream() throws FileNotFoundException;
}
