package com.deloitte.platform.api.srpmp.outline.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineMedical新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsOutlineMedical表单")
@Data
public class SrpmsOutlineMedicalForm extends SrpmsOutlineFormListBasic {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    private String id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    private String baseId;

    @ApiModelProperty(value = "项目编码")
    private String proCode;

    @ApiModelProperty(value = "项目名称")
    private String proName;

    @ApiModelProperty(value = "新药/器械名称")
    private String medicalName;

    @ApiModelProperty(value = "类别")
    private String medicalCat;

    @ApiModelProperty(value = "药品分类")
    private String medicalType;

    @ApiModelProperty(value = "药品等级")
    private String medicalLevel;

    @ApiModelProperty(value = "新药/器械证书号")
    private String medicalCertificateNum;

    @ApiModelProperty(value = "批准时间")
    private LocalDateTime approvalTime;

    @ApiModelProperty(value = "项目负责人")
    private String proInCharge;

}
