package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : jiangString
 * @Date : Create in 2019-04-01
 * @Description : GccOnjobInformation返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccOnjobInformationAllVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "人员编号")
    private String userId;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "入选年份")
    private String choiceYear;

    @ApiModelProperty(value = "人才项目名称")
    private String projectName;


    @ApiModelProperty(value = "人才项目类别")
    private String projectCategory;


    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "一级学科")
    private String firstLevelSubject;

    @ApiModelProperty(value = "二级学科")
    private String secondLevelSubject;

    @ApiModelProperty(value = "研究方向")
    private String researchDirection;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "到岗时间")
    private LocalDateTime onjobTime;

    @ApiModelProperty(value = "是否到岗")
    private String whetherWork;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "附件")
    private String enclosure;

    @ApiModelProperty(value = "入选人员编号")
    private String  highLevelId;


    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentVo attachmentVo;
}
