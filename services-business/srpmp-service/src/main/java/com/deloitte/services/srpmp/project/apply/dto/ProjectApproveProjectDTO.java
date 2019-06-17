package com.deloitte.services.srpmp.project.apply.dto;

import lombok.Data;

/**
 * Created by lixin on 08/03/2019.
 * 批复吉件项目维度DTO
 */
@Data
public class ProjectApproveProjectDTO {

    //项目ID
    private Long id;

    //项目编号
    private String projectNum;

    //依托单位ID
    private String deptName;
    //依托单位ID

    private Long dependDeptId;

    private String leadDeptCode;

    //项目类型
    private String projectCategory;

    //项目名称
    private String projectName;

    //预算金额
    private double budget;

    //首席专家
    private String personName;

}
