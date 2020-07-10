package com.marandaneto.concurrenthashmapsample;

import java.util.HashMap;
import java.util.Map;

public class MyJson implements IUnknownPropertiesConsumer {

    private MyConcurrentHashMap hashmap;

    private Map<String, Object> unknown;

    public MyConcurrentHashMap getHashMap() {
        return hashmap;
    }

    public void setHashMap(MyConcurrentHashMap hashmap) {
        this.hashmap = hashmap;
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
