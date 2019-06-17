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
 * 题录 04成果转化信息表
 * </p>
 *
 * @author Apeng
 * @since 2019-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_OUTLINE_RESULT_TRANS")
@ApiModel(value="SrpmsOutlineResultTrans对象", description="题录 04成果转化信息表")
public class SrpmsOutlineResultTrans extends Model<SrpmsOutlineResultTrans> {

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

    @ApiModelProperty(value = "成果名称")
    @TableField("PRO_RESULT_NAME")
    private String proResultName;

    @ApiModelProperty(value = "负责人")
    @TableField("PRO_IN_CHARGE")
    private String proInCharge;

    @ApiModelProperty(value = "技术成果转化形式")
    @TableField("TRANS_WAY")
    private String transWay;

    @ApiModelProperty(value = "合同签订年份")
    @TableField("CONTRACT_SIGNING_YEAR")
    private String contractSigningYear;

    @ApiModelProperty(value = "合同金额(万元)")
    @TableField("CONTRACT_AMOUNT")
    private Double contractAmount;

    @ApiModelProperty(value = "本年到位金额(万元)")
    @TableField("YEAR_ENSURED_AMOUNT")
    private Double yearEnsuredAmount;

    @ApiModelProperty(value = "转化费来源")
    @TableField("TRANS_FEE_SOURCE")
    private String transFeeSource;

    @ApiModelProperty(value = "备注")
    @TableField("REMARKS")
    private String remarks;

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


    public static final String ID = "ID";

    public static final String BASE_ID = "BASE_ID";

    public static final String PRO_CODE = "PRO_CODE";

    public static final String PRO_NAME = "PRO_NAME";

    public static final String PRO_RESULT_NAME = "PRO_RESULT_NAME";

    public static final String PRO_IN_CHARGE = "PRO_IN_CHARGE";

    public static final String TRANS_WAY = "TRANS_WAY";

    public static final String CONTRACT_SIGNING_YEAR = "CONTRACT_SIGNING_YEAR";

    public static final String CONTRACT_AMOUNT = "CONTRACT_AMOUNT";

    public static final String YEAR_ENSURED_AMOUNT = "YEAR_ENSURED_AMOUNT";

    public static final String TRANS_FEE_SOURCE = "TRANS_FEE_SOURCE";

    public static final String REMARKS = "REMARKS";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
