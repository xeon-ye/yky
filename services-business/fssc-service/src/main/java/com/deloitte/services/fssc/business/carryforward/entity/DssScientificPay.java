package com.deloitte.services.fssc.business.carryforward.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 财务项目支出表
 * </p>
 *
 * @author chenx
 * @since 2019-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("DSS_SCIENTIFIC_PAY")
@ApiModel(value="DssScientificPay对象", description="财务项目支出表")
public class DssScientificPay extends Model<DssScientificPay> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "来源系统")
    @TableField("FROM_SYSTEM")
    private String fromSystem;

    @ApiModelProperty(value = "财务单据编码")
    @TableField("DOCUMENT_NUM")
    private String documentNum;

    @ApiModelProperty(value = "支出时间")
    @TableField("PAY_DATE")
    private String payDate;

    @ApiModelProperty(value = "支出金额")
    @TableField("FUNDS")
    private BigDecimal funds;

    @ApiModelProperty(value = "项目编码")
    @TableField("PRO_CODE")
    private String proCode;

    @ApiModelProperty(value = "任务编码")
    @TableField("TASK_CODE")
    private String taskCode;

    @ApiModelProperty(value = "预算年度")
    @TableField("BUDGET_YEAR")
    private String budgetYear;

    @ApiModelProperty(value = "支出分类支出分类（预算项目） 支出分类（预算项目）")
    @TableField("PHYLETIC")
    private String phyletic;

    @ApiModelProperty(value = "支出分类（预算项目）名称 支出分类（预算项目）名称")
    @TableField("PHYLETIC_NAME")
    private String phyleticName;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "Y 在用，N 已冲销")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "经济分类-取单据头中“支出大类”对应的预算科目")
    @TableField("ECONOMIC_CLASSIFICATION_ID")
    private Long economicClassificationId;

    @ApiModelProperty(value = "经济分类-取单据头中“支出大类”对应的预算科目CODE")
    @TableField("ECONOMIC_CLASSIFICATION_CODE")
    private String  economicClassificationCode;

    @ApiModelProperty(value = "单据头中的创建人名称")
    @TableField("CREATE_USER_NAME")
    private String  createUserName;

    @ApiModelProperty(value = "单据头中备注")
    @TableField("REMARK")
    private String  remark;

    public static final String REMARK = "REMARK";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";
    public static final String  ECONOMIC_CLASSIFICATION_CODE = "ECONOMIC_CLASSIFICATION_CODE";
    public static final String ID = "ID";

    public static final String FROM_SYSTEM = "FROM_SYSTEM";

    public static final String DOCUMENT_NUM = "DOCUMENT_NUM";

    public static final String PAY_DATE = "PAY_DATE";

    public static final String FUNDS = "FUNDS";

    public static final String PRO_CODE = "PRO_CODE";

    public static final String TASK_CODE = "TASK_CODE";

    public static final String BUDGET_YEAR = "BUDGET_YEAR";

    public static final String PHYLETIC = "PHYLETIC";

    public static final String PHYLETIC_NAME = "PHYLETIC_NAME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String STATUS = "STATUS";

    public static final String ECONOMIC_CLASSIFICATION_ID = "ECONOMIC_CLASSIFICATION_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
