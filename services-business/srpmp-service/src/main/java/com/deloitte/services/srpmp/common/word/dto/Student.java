package com.deloitte.services.srpmp.common.word.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lixin on 06/03/2019.
 */
@Data
public class Student implements Serializable {
    private String serial;
    private String name;
    private String position;
    private String salary;
}
