package com.deloitte.platform.api.srpmp.outline.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description :   SrpmsOutlineMedical查询from对象
 * @Modified :
 */
@ApiModel("SrpmsOutlineMedical查询表单")
@Data
public class SrpmsOutlineMedicalQueryForm extends BaseQueryForm<SrpmsOutlineMedicalQueryParam>  {


    @ApiModelProperty(value = "ID，主键，业务不相关")
    private Long id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    private Long baseId;

    @ApiModelProperty(value = "项目编码")
    private String proCode;

    @ApiModelProperty(value = "项目名称")
    private String proName;

    @ApiModelProperty(value = "新药/器械名称")
    private String medicalName;

    @ApiModelProperty(value = "类别")
    private String medicalCat;

    @ApiModelProperty(value = "新药/器械证书号")
    private String medicalCertificateNum;

    @ApiModelProperty(value = "批准时间")
    private LocalDateTime approvalTime;
    @ApiModelProperty(value = "批准开始时间")
    private LocalDateTime approvalStartTime;
    @ApiModelProperty(value = "批准结束时间")
    private LocalDateTime approvalEndTime;

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

    @ApiModelProperty(value = "药品分类")
    private String medicalType;

    @ApiModelProperty(value = "药品等级")
    private String medicalLevel;

    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "年")
    private String year;

    @ApiModelProperty(value = "月")
    private String month;
}
