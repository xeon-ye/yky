package com.deloitte.platform.api.hr.gcc.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccTalentProject新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccTalentProject表单")
@Data
public class GccTalentProjectForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "编号")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "主管部门")
    private Long departmentCode;

    @ApiModelProperty(value = "项目性质")
    private String projectNature;

    @ApiModelProperty(value = "是否需要申报 0否 1是")
    private String declare;

    @ApiModelProperty(value = "是否需要考核  0否，1是")
    private String assessed;

    @ApiModelProperty(value = "是否有效 0 否，1是")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "是否拨付0 否，1是")
    private String allocated;

    @ApiModelProperty(value = "拨付时间")
    private LocalDateTime allocatedTime;

    @ApiModelProperty(value = "到账时间")
    private LocalDateTime accoutTime;

    @ApiModelProperty(value = "是否到账0 否，1是")
    private String whetherAccount;

    @ApiModelProperty(value = "项目简称")
    private String shortName;

    @ApiModelProperty(value = "项目级别")
    private String projectLevel;

}
