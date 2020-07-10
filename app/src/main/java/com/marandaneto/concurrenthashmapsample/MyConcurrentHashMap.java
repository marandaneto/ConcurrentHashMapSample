package com.marandaneto.concurrenthashmapsample;

import java.util.concurrent.ConcurrentHashMap;

public class MyConcurrentHashMap extends ConcurrentHashMap<String, Object> {

    public static final String MY_CLASS = "myclass";

    private <T> T toContextType(String key, Class<T> clazz) {
        Object item = get(key);
        return clazz.isInstance(item) ? clazz.cast(item) : null;
    }

    public MyClass getMyclass() {
        return toContextType(MY_CLASS, MyClass.class);
    }

    public void setMyclass(MyClass myclass) {
        this.put(MY_CLASS, myclass);
    }
}
