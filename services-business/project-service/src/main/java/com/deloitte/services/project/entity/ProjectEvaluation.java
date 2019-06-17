package com.deloitte.services.project.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/6/6 14:37
 * @Description :
 * @Modified:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProjectEvaluation对象", description = "项目评价查询对象")
public class ProjectEvaluation {

    @ApiModelProperty(value = "projectId")
    private String id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目编码Code")
    private String projectCode;

    @ApiModelProperty(value = "批复编码Code")
    private String replyCode;

    @ApiModelProperty(value = "项目类型Code")
    private String projectTypeCode;

    @ApiModelProperty(value = "项目类型名称")
    private String projectTypeName;

    @ApiModelProperty(value = "项目状态Code")
    private String projectStatusCode;

    @ApiModelProperty(value = "项目状态名称")
    private String projectStatusName;

    @ApiModelProperty(value = "负责人Id")
    private String projectHeaderId;

    @ApiModelProperty(value = "负责人名称")
    private String projectHeaderName;

    @ApiModelProperty(value = "单位Id")
    private String organizationId;

    @ApiModelProperty(value = "单位Code")
    private String organizationCode;

    @ApiModelProperty(value = "单位名称")
    private String organizationName;

    @ApiModelProperty(value = "开始执行年度")
    private String planYear;

    @ApiModelProperty(value = "项目周期")
    private String cycle;

    @ApiModelProperty(value = "当前页码")
    private Long currentPage;

    @ApiModelProperty(value = "当前页码 所要显示数据条数")
    private Long pageSize;

    @ApiModelProperty(value = "附件ID")
    private String enclosureId;

    @ApiModelProperty(value = "附件名称")
    private String fileName;

    @ApiModelProperty(value = "附件类型")
    private String fileType;

    @ApiModelProperty(value = "附件UIR地址链接")
    private String fileUrl;

    @ApiModelProperty(value = "文件ID")
    private String fileId;

    @ApiModelProperty(value = "附件上传时间")
    private String uploadTime;
}
