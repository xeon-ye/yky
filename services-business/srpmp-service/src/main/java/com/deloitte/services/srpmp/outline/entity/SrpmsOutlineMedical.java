package com.deloitte.services.srpmp.outline.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 题录 06新药器械
 * </p>
 *
 * @author Apeng
 * @since 2019-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_OUTLINE_MEDICAL")
@ApiModel(value="SrpmsOutlineMedical对象", description="题录 06新药器械")
public class SrpmsOutlineMedical extends Model<SrpmsOutlineMedical> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    @TableField("BASE_ID")
    private Long baseId;

    @ApiModelProperty(value = "项目编码")
    @TableField("PRO_CODE")
    private String proCode;

    @ApiModelProperty(value = "项目名称")
    @TableField("PRO_NAME")
    private String proName;

    @ApiModelProperty(value = "新药/器械名称")
    @TableField("MEDICAL_NAME")
    private String medicalName;

    @ApiModelProperty(value = "类别")
    @TableField("MEDICAL_CAT")
    private String medicalCat;

    @ApiModelProperty(value = "新药/器械证书号")
    @TableField("MEDICAL_CERTIFICATE_NUM")
    private String medicalCertificateNum;

    @ApiModelProperty(value = "批准时间")
    @TableField("APPROVAL_TIME")
    private LocalDateTime approvalTime;

    @ApiModelProperty(value = "项目负责人")
    @TableField("PRO_IN_CHARGE")
    private String proInCharge;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "药品分类")
    @TableField("MEDICAL_TYPE")
    private String medicalType;

    @ApiModelProperty(value = "药品等级")
    @TableField("MEDICAL_LEVEL")
    private String medicalLevel;


    public static final String ID = "ID";

    public static final String BASE_ID = "BASE_ID";

    public static final String PRO_CODE = "PRO_CODE";

    public static final String PRO_NAME = "PRO_NAME";

    public static final String MEDICAL_NAME = "MEDICAL_NAME";

    public static final String MEDICAL_CAT = "MEDICAL_CAT";

    public static final String MEDICAL_CERTIFICATE_NUM = "MEDICAL_CERTIFICATE_NUM";

    public static final String APPROVAL_TIME = "APPROVAL_TIME";

    public static final String PRO_IN_CHARGE = "PRO_IN_CHARGE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String MEDICAL_TYPE = "MEDICAL_TYPE";

    public static final String MEDICAL_LEVEL = "MEDICAL_LEVEL";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
