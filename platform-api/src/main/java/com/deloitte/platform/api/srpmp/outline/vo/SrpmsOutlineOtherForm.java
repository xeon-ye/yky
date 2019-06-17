package com.deloitte.platform.api.srpmp.outline.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineOther新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsOutlineOther表单")
@Data
public class SrpmsOutlineOtherForm extends SrpmsOutlineFormListBasic {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    private String id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    private String baseId;

    @ApiModelProperty(value = "科研基地类型")
    private String srpmsBaseCat;

    @ApiModelProperty(value = "基地级别")
    private String srpmsBaseLevel;

    @ApiModelProperty(value = "实验室/中心名称")
    private String expCenterName;

    @ApiModelProperty(value = "实验室/中心主任")
    private String expCenterDirector;

    @ApiModelProperty(value = "依托单位")
    private String supportOrg;

    @ApiModelProperty(value = "批准时间")
    private LocalDateTime approvalTime;

    @ApiModelProperty(value = "批准文号")
    private String approvalNum;

    @ApiModelProperty(value = "成立时间")
    private LocalDateTime builtTime;

    @ApiModelProperty(value = "详细地址")
    private String detailAddress;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
