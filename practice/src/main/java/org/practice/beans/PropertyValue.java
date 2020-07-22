package org.practice.beans;

/**
 * @author yeyulin
 * @description: PropertyValue来表示 XML中property属性
 * bean 的附带属性
 * @date 2020/7/22 10:53
 **/
public class PropertyValue {
    private final String name;
    private final Object value;
    private boolean converted = false;

    private Object convertedValue;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public Object getValue() {
        return this.value;
    }

    public synchronized boolean isConverted() {
        return this.converted;
    }

    public synchronized void setConvertedValue(Object value) {
        this.converted = true;
        this.convertedValue = value;
    }

    public Object getConvertedValue() {
        return this.convertedValue;
    }
}
