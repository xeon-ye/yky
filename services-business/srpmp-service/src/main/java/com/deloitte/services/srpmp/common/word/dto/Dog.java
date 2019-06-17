package com.deloitte.services.srpmp.common.word.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * Created by lixin on 12/03/2019.
 */
@Data
public class Dog extends Animal {

    private int age;
    private String name;

    public static void main(String[] args){
        String json = "{\n" +
                "    \"name\": \"hello\",\n" +
                "    \"color\": \"red\",\n" +
                "    \"age\": 10\n" +
                "}";
        Dog d = JSONObject.parseObject(json, Dog.class);
        System.out.println(d.getName());
        Animal animal = JSONObject.parseObject(json, Animal.class);

        System.out.println(d.getName());
        System.out.println(animal.getColor());

    }
}
