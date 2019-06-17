package com.deloitte.services.srpmp.relust.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 成果管理-新药器械
 * </p>
 *
 * @author Apeng
 * @since 2019-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_RESULT_MEDICAL")
@ApiModel(value="SrpmsResultMedical对象", description="成果管理-新药器械")
public class SrpmsResultMedical extends Model<SrpmsResultMedical> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单位CODE")
    @TableField("DEPT_CODE")
    private Long deptCode;

    @ApiModelProperty(value = "年")
    @TableField("YEAR")
    private String year;

    @ApiModelProperty(value = "月")
    @TableField("MONTH")
    private String month;

    @ApiModelProperty(value = "项目编码")
    @TableField("PRO_NUM")
    private String proNum;

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

    @ApiModelProperty(value = "药品分类")
    @TableField("MEDICAL_TYPE")
    private String medicalType;

    @ApiModelProperty(value = "药品等级")
    @TableField("MEDICAL_LEVEL")
    private String medicalLevel;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;


    public static final String ID = "ID";

    public static final String DEPT_CODE = "DEPT_CODE";

    public static final String YEAR = "YEAR";

    public static final String MONTH = "MONTH";

    public static final String PRO_NUM = "PRO_NUM";

    public static final String PRO_NAME = "PRO_NAME";

    public static final String MEDICAL_NAME = "MEDICAL_NAME";

    public static final String MEDICAL_CAT = "MEDICAL_CAT";

    public static final String MEDICAL_CERTIFICATE_NUM = "MEDICAL_CERTIFICATE_NUM";

    public static final String APPROVAL_TIME = "APPROVAL_TIME";

    public static final String PRO_IN_CHARGE = "PRO_IN_CHARGE";

    public static final String MEDICAL_TYPE = "MEDICAL_TYPE";

    public static final String MEDICAL_LEVEL = "MEDICAL_LEVEL";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
