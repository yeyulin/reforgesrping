package org.practice.beans.factory.config;

/**
 * @author yeyulin
 * @description:
 * @date 2020/7/22 14:29
 **/
public class TypedStringValue {
    private String value;

    public TypedStringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
