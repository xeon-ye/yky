package com.deloitte.services.srpmp.project.apply.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by lixin on 08/03/2019.
 * 批复件单位维度DTO
 */
@Data
public class ProjectApproveDeptDTO {

    //单位ID
    private String id;

    //单位名称
    private String deptName;

    //预算金额
    private double budget;

    //承担单位ID
    private String leadDeptId;


    //项目数
    private int size;

    //项目集合
    private List<ProjectApproveProjectDTO> projectList;


}
