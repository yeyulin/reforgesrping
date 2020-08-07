package org.practice.beans;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yeyulin
 * @description:
 * @date 2020/8/7 11:15
 **/
public class ConstructorArgument {
    private final List<ValueHolder> argumentValues = new LinkedList<>();

    public void addArgumentValue(Object value) {
        this.argumentValues.add(new ValueHolder(value));
    }

    public void addArgumentValue(ValueHolder valueHolder) {
        this.argumentValues.add(valueHolder);
    }

    public List<ValueHolder> getArgumentValues() {
        return argumentValues;
    }

    public int getArgumentCount() {
        return this.argumentValues.size();
    }

    public boolean isEmpty() {
        return this.argumentValues.isEmpty();
    }

    public void clear() {
        this.argumentValues.clear();
    }

    @Data
    public static class ValueHolder {
        private Object value;
        private String type;
        private String name;
        private Integer index;

        public ValueHolder(Object value) {
            this.value = value;
        }
    }
}
