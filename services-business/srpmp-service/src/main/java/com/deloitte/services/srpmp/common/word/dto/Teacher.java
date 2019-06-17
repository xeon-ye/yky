package com.deloitte.services.srpmp.common.word.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lixin on 06/03/2019.
 */
@Data
public class Teacher implements Serializable {
    private String name;
    private String age;
    private String birthday;
    private String gender;
    private String email;
    private String phone;
    private List<Student> stuList;

}
