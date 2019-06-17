package com.deloitte.platform.api.hr.recruitment.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :   HrZpcpDeclare查询from对象
 * @Modified :
 */
@ApiModel("HrZpcpDeclare查询表单")
@Data
public class HrZpcpDeclareQueryForm extends BaseQueryForm<HrZpcpDeclareQueryParam>  {


    @ApiModelProperty(value = "通知名称")
    private String noticeName;

    @ApiModelProperty(value = "岗位名称")
    private String stationName;

    @ApiModelProperty(value = "发布单位")
    private String unit;

    @ApiModelProperty(value = "发布单位编号")
    private String deptCode;

    @ApiModelProperty(value = "申报状态")
    private String status;

    @ApiModelProperty(value = "申报人姓名")
    private String name;

    @ApiModelProperty(value = "申报人编号(教职工编号)")
    private String empCode;

    @ApiModelProperty(value = "年分")
    private String year;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "2.聘任工作组,4学术工作组,6.教授工作组,8 教职工作组")
    private Integer checkType;

    /*
    @ApiModelProperty(value = "申报一级学科1")
    private String firstClassDiscipline1;

    @ApiModelProperty(value = "申报一级学科2")
    private String firstClassDiscipline2;

    @ApiModelProperty(value = "申报岗位1")
    private String declarationPost1;

    @ApiModelProperty(value = "申报岗位2")
    private String declarationPost2;

    @ApiModelProperty(value = "申报单位")
    private String declarationUnit;

    @ApiModelProperty(value = "申报时间")
    private LocalDateTime declarationTime;

    @ApiModelProperty(value = "申报人详情")
    private String declarationDateil;

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
    private Long updateBy;*/
}
