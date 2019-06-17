package com.deloitte.platform.api.srpmp.outline.vo;

import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineMedical返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsOutlineMedicalVo extends SrpmsOutlineVoListBasic {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long baseId;

    @ApiModelProperty(value = "项目编码")
    private String proCode;

    @ApiModelProperty(value = "项目名称")
    private String proName;

    @ApiModelProperty(value = "新药/器械名称")
    private String medicalName;

    @ApiModelProperty(value = "类别")
    private String medicalCat;
    @ApiModelProperty(value = "类别字典名称")
    private String medicalCatName;

    @ApiModelProperty(value = "药品分类")
    private String medicalType;
    @ApiModelProperty(value = "药品分类字典名称")
    private String medicalTypeName;

    @ApiModelProperty(value = "药品等级")
    private String medicalLevel;
    @ApiModelProperty(value = "药品等级字典名称")
    private String medicalLevelName;

    @ApiModelProperty(value = "新药/器械证书号")
    private String medicalCertificateNum;

    @ApiModelProperty(value = "批准时间")
    private LocalDateTime approvalTime;

    @ApiModelProperty(value = "项目负责人")
    private String proInCharge;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

}
