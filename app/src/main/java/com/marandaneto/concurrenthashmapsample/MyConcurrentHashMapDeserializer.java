package com.marandaneto.concurrenthashmapsample;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class MyConcurrentHashMapDeserializer implements JsonDeserializer<MyConcurrentHashMap> {
    @Override
    public MyConcurrentHashMap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json != null && !json.isJsonNull()) {
            MyConcurrentHashMap hashmap = new MyConcurrentHashMap();
            final JsonObject jsonObject = json.getAsJsonObject();

            if (jsonObject != null && !jsonObject.isJsonNull()) {
                for (final String key : jsonObject.keySet()) {
                    switch (key) {
                        case MyConcurrentHashMap.MY_CLASS:
                            MyClass myClass = parseObject(context, jsonObject, key, MyClass.class);
                            if (myClass != null) {
                                hashmap.setMyclass(myClass);
                            }
                            break;
                        default:
                            final JsonElement element = jsonObject.get(key);
                            if (element != null && !element.isJsonNull()) {
                                final Object object = context.deserialize(element, Object.class);
                                hashmap.put(key, object);
                            }
                            break;
                    }
                }
                return hashmap;
            }
        }
        return null;
    }

    private <T> T parseObject(
            final JsonDeserializationContext context,
            final JsonObject jsonObject,
            final String key,
            final Class<T> clazz)
            throws JsonParseException {
        final JsonObject object = jsonObject.getAsJsonObject(key);
        if (object != null && !object.isJsonNull()) {
            return context.deserialize(object, clazz);
        }
        return null;
    }
}
