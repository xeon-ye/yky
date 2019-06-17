package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-17
 * @Description : HrZpcpDeclare返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrZpcpDeclareVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "通知名称")
    private String noticeName;

    @ApiModelProperty(value = "发布时间")
    private String publicDate;

    @ApiModelProperty(value = "年分")
    private String year;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "申报单位名称")
    private String deptName;

    @ApiModelProperty(value = "申报人姓名")
    private String name;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "申报一级学科1")
    private String firstClassDiscipline1;

    @ApiModelProperty(value = "申报一级学科2")
    private String firstClassDiscipline2;

    @ApiModelProperty(value = "申报岗位1")
    private String declarationPost1;

    @ApiModelProperty(value = "申报岗位")
    private String stations;

    @ApiModelProperty(value = "申报岗位2")
    private String declarationPost2;

    @ApiModelProperty(value = "申报二级学科1")
    private String secondClassDiscipline1;

    @ApiModelProperty(value = "申报二级学科2")
    private String secondClassDiscipline2;

    @ApiModelProperty(value = "申报单位编码Code")
    private String declarationUnit;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "申报时间")
    private LocalDate declarationTime;

    @ApiModelProperty(value = "申报人详情")
    private String declarationDateil;

    @ApiModelProperty(value = "状态(0.审核未通过,1已保存,2已提交申报,3.聘任工作组审核通过,4学术委员会审核通过,5教授委员会审核通过,6.教职聘任委员会审核通过)")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "申报类型")
    private String declarationType;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;

    @ApiModelProperty(value = "通知ID")
    private String noticeId;

    @ApiModelProperty(value = "科技成果转化说明")
    private String techResultDesc;

    @ApiModelProperty(value = "科技成果转化得分")
    private String techResultScore;

    @ApiModelProperty(value = "主要学术成绩、影响力、科学意义及社会经济价值")
    private String impactDescription;

    @ApiModelProperty(value = "最后一级学科")
    private String finalFirstClass;

    @ApiModelProperty(value = "最终岗位")
    private String finalPost;


    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "聘用开始时间")
    private LocalDate startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "聘用结束时间")
    private LocalDate endTime;

    @ApiModelProperty(value = "任职年限(月)")
    private String tenureService;

}
