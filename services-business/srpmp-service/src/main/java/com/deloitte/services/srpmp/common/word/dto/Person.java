package com.deloitte.services.srpmp.common.word.dto;

import com.deloitte.services.srpmp.common.word.annotation.WordField;
import com.deloitte.services.srpmp.common.word.annotation.WordImport;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.lang.reflect.Field;

/**
 * Created by lixin on 28/02/2019.
 */
@WordImport(tableIndex = 1, desc = "人员表")
@JsonInclude
@Data
public class Person {

    @WordField(tableIndex = 1, rowIndex = 1, cellIndex = 0)
    private String name;

    @WordField(tableIndex = 1, rowIndex = 1, cellIndex = 2)
    private String gender;

    @WordField(tableIndex = 1, rowIndex = 0, cellIndex = 3)
    private Book book;


    public static void main(String[] args) throws IllegalAccessException {
        boolean hasAnn = Person.class.isAnnotationPresent(WordImport.class);
        if ( hasAnn ){
            WordImport wordImport = Person.class.getAnnotation(WordImport.class);
            System.out.println(wordImport.tableIndex());
            System.out.println(wordImport.desc());
            Field[] fieldArr = Person.class.getDeclaredFields();
            Person person = new Person();
            for (Field field: fieldArr){
                System.out.println(field.getName());

            }
            System.out.println(person.toString());

        }

    }


}
