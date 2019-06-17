package com.deloitte.platform.api.srpmp.outline.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineCooperation新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsOutlineCooperation表单")
@Data
public class SrpmsOutlineCooperationForm extends SrpmsOutlineFormListBasic {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    private String id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    private String baseId;

    @ApiModelProperty(value = "项目编码")
    private String proCode;

    @ApiModelProperty(value = "项目名称")
    private String proName;

    @ApiModelProperty(value = "合作类别")
    private String cooperationCat;

    @ApiModelProperty(value = "合作单位")
    private String cooperationOrg;

    @ApiModelProperty(value = "项目负责人")
    private String proInCharge;

    @ApiModelProperty(value = "项目经费(万元)")
    private Double proOutlay;

    @ApiModelProperty(value = "项目开始时间")
    private LocalDateTime proStartDate;

    @ApiModelProperty(value = "项目结束时间")
    private LocalDateTime proEndDate;

}
