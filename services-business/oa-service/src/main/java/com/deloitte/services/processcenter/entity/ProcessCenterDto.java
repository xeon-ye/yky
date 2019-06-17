package com.deloitte.services.processcenter.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProcessCenterDto implements Serializable {
    private int currentPage;
    private int pageSize;
    private String userId;
    /**
     * 各系统下的模块分类标识 默认待办事宜
     */
    private String modelType = "backLog";
    /**
     * 系统标识
     */
    private String sourceSystem;

    @ApiModelProperty(value = "审批对象描述")
    private String objectDescription;
}
