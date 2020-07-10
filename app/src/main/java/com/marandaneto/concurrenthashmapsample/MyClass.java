package com.marandaneto.concurrenthashmapsample;

import java.util.HashMap;
import java.util.Map;

public class MyClass implements IUnknownPropertiesConsumer {

    private String name;
    private Integer age;

    private Map<String, Object> unknown;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public void acceptUnknownProperties(Map<String, Object> unknown) {
        this.unknown = unknown;
    }

    public void addUnknown(String key, String value) {
        unknown = new HashMap<>();
        unknown.put(key, value);
    }
}
