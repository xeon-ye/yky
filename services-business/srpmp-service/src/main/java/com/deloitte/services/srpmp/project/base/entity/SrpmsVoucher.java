package com.deloitte.services.srpmp.project.base.entity;

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
 * 项目审核单据表
 * </p>
 *
 * @author lixin
 * @since 2019-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_VOUCHER")
@ApiModel(value="SrpmsVoucher对象", description="项目审核单据表")
public class SrpmsVoucher extends Model<SrpmsVoucher> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单据业务编号")
    @TableField("BIZ_NUMBER")
    private String bizNumber;

    @ApiModelProperty(value = "单据名称")
    @TableField("VOUCHER_NAME")
    private String voucherName;

    @ApiModelProperty(value = "单据类型")
    @TableField("VOUCHER_TYPE")
    private String voucherType;

    @ApiModelProperty(value = "单据状态")
    @TableField("VOUCHER_STATUS")
    private String voucherStatus;

    @ApiModelProperty(value = "申请人ID")
    @TableField("USER_ID")
    private Long userId;

    @ApiModelProperty(value = "申请人姓名")
    @TableField("PERSON_NAME")
    private String personName;

    @ApiModelProperty(value = "申请时间")
    @TableField("START_TIME")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    @TableField("END_TIME")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "项目ID")
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "项目标识")
    @TableField("PROJECT_FLAG")
    private String projectFlag;

    @ApiModelProperty(value = "项目类型")
    @TableField("PROJECT_TYPE")
    private String projectType;

    @ApiModelProperty(value = "是否允许撤回标识")
    @TableField("RECALL_FLAG")
    private int recallFlag;

    @ApiModelProperty(value = "承担单位CODE")
    @TableField("LEAD_DEPT_CODE")
    private String leadDeptCode;

    @ApiModelProperty(value = "审批模式 selfOnly:本单位审批 topOnly:医科院审批 all:本单位及医科院共同审批")
    @TableField("AUDIT_MODE")
    private String auditMode;

    public static final String ID = "ID";

    public static final String BIZ_NUMBER = "BIZ_NUMBER";

    public static final String VOUCHER_NAME = "VOUCHER_NAME";

    public static final String VOUCHER_TYPE = "VOUCHER_TYPE";

    public static final String VOUCHER_STATUS = "VOUCHER_STATUS";

    public static final String USER_ID = "USER_ID";

    public static final String PERSON_NAME = "PERSON_NAME";

    public static final String START_TIME = "START_TIME";

    public static final String END_TIME = "END_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String PROJECT_FLAG = "PROJECT_FLAG";

    public static final String PROJECT_TYPE = "PROJECT_TYPE";

    public static final String RECALL_FLAG = "RECALL_FLAG";

    public static final String LEAD_DEPT_CODE = "LEAD_DEPT_CODE";

    public static final String AUDIT_MODE = "AUDIT_MODE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
