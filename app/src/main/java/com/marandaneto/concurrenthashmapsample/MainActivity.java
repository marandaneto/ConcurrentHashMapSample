package com.marandaneto.concurrenthashmapsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyJson myJson = new MyJson();

        MyConcurrentHashMap myConcurrentHashMap = new MyConcurrentHashMap();
        myConcurrentHashMap.put("hi", "ciao");

        MyClass myClass = new MyClass();
        myClass.setAge(21);
        myClass.setName("ahoi");
        myClass.addUnknown("hello", "hey");

        myConcurrentHashMap.setMyclass(myClass);

        myJson.setHashMap(myConcurrentHashMap);

        myJson.addUnknown("blabla", "kk");

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(UnknownPropertiesTypeAdapterFactory.get())
                .registerTypeAdapter(MyConcurrentHashMap.class, new MyConcurrentHashMapDeserializer())
                .create();
        String json = gson.toJson(myJson, MyJson.class);

        System.out.println("json: " + json);

        MyJson myNewJson = gson.fromJson(json, MyJson.class);
        System.out.println("name: " + myNewJson.getHashMap().getMyclass().getName());
        System.out.println("age: " + myNewJson.getHashMap().getMyclass().getAge());
    }
}