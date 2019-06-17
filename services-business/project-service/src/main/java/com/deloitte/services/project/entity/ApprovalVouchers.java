package com.deloitte.services.project.entity;

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
 * 合同审核单据表
 * </p>
 *
 * @author zhengchun
 * @since 2019-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_APPROVAL_VOUCHERS")
@ApiModel(value="ApprovalVouchers对象", description="合同审核单据表")
public class ApprovalVouchers extends Model<ApprovalVouchers> {

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
    @TableField("USER_NAME")
    private String userName;

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

    @ApiModelProperty(value = "单据归属机构")
    @TableField("ORG_CODE")
    private String orgCode;

    @ApiModelProperty(value = "业务id")
    @TableField("BUSINESS_ID")
    private String businessId;

    @ApiModelProperty(value = "业务名称")
    @TableField("BUSINESS_NAME")
    private String businessName;


    public static final String ID = "ID";

    public static final String BIZ_NUMBER = "BIZ_NUMBER";

    public static final String VOUCHER_NAME = "VOUCHER_NAME";

    public static final String VOUCHER_TYPE = "VOUCHER_TYPE";

    public static final String VOUCHER_STATUS = "VOUCHER_STATUS";

    public static final String USER_ID = "USER_ID";

    public static final String USER_NAME = "USER_NAME";

    public static final String START_TIME = "START_TIME";

    public static final String END_TIME = "END_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String ORG_CODE = "ORG_CODE";

    public static final String BUSINESS_ID = "BUSINESS_ID";

    public static final String BUSINESS_NAME = "BUSINESS_NAME";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
