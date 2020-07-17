package org.practice.beans.factory;

import org.practice.beans.BeansException;

public class BeanInstanceException extends BeansException {
    public BeanInstanceException(String msg) {
        super(msg);
    }

    public BeanInstanceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
