package com.deloitte.services.fssc.business.carryforward.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 收入结转
 * </p>
 *
 * @author chenx
 * @since 2019-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("INCOME_OF_CARRY_FORWARD")
@ApiModel(value="IncomeOfCarryForward对象", description="收入结转")
public class IncomeOfCarryForward extends Model<IncomeOfCarryForward> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "支出大类")
    @TableField("MAIN_TYPE_ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "单据编码")
    @TableField("DOCUMENT_NUM")
    private String documentNum;

    @ApiModelProperty(value = "单据类型")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "项目编码")
    @TableField(value = "PROJECT_CODE",strategy = FieldStrategy.IGNORED)
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    @TableField(value = "PROJECT_NAME",strategy = FieldStrategy.IGNORED)
    private String projectName;

    @ApiModelProperty(value = "归属单位ID")
    @TableField("UNIT_ID")
    private Long unitId;

    @ApiModelProperty(value = "归属部门ID")
    @TableField("DEPT_ID")
    private Long deptId;

    @ApiModelProperty(value = "归属单位名称")
    @TableField("UNIT_NAME")
    private String unitName;

    @ApiModelProperty(value = "归属部门名称")
    @TableField("DEPT_NAME")
    private String deptName;

    @ApiModelProperty(value = "入账日期")
    @TableField("ENTER_DATE")
    private LocalDateTime enterDate;

    @ApiModelProperty(value = "金额")
    @TableField("MONEY")
    private BigDecimal money;

    @ApiModelProperty(value = "结转状态")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新日期")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "单据编号")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "扩展字段1")
    @TableField("ETX_1")
    private String etx1;

    @ApiModelProperty(value = "扩展字段2")
    @TableField("ETX_2")
    private String etx2;

    @ApiModelProperty(value = "扩展字段3")
    @TableField("ETX_3")
    private String etx3;

    @ApiModelProperty(value = "扩展字段4")
    @TableField("ETX_4")
    private String etx4;

    @ApiModelProperty(value = "扩展字段5")
    @TableField("ETX_5")
    private String etx5;

    @ApiModelProperty(value = "凭证ID")
    @TableField("JE_HEADER_ID")
    private Long jeHeaderId;

    @ApiModelProperty(value="支出大类名")
    @TableField("MAIN_TYPE_NAME")
    private String mainTypeName;

    @ApiModelProperty(value="项目ID")
    @TableField(value = "PROJECT_ID",strategy = FieldStrategy.IGNORED)
    private Long projectId;
    @ApiModelProperty(value="部门code")
    @TableField("DEPT_CODE")
    private String deptCode;
    @ApiModelProperty(value="支出大类编码")
    @TableField("MAIN_TYPE_CODE")
    private String mainTypeCode;
    @ApiModelProperty(value="归属单位编码")
    @TableField("UNIT_CODE")
    private String unitCode;

    @ApiModelProperty(value="归属单位编码")
    @TableField("FSSC_CODE")
    private String fsscCode;


    @ApiModelProperty(value="归属单位编码")
    @TableField("DR_ACCOUNT_CODE")
    private String drAccountCode;


    @ApiModelProperty(value="归属单位编码")
    @TableField("CR_ACCOUNT_CODE")
    private String crAccountCode;


    public static final String ID = "ID";
    public static final String CR_ACCOUNT_CODE = "CR_ACCOUNT_CODE";
    public static final String DR_ACCOUNT_CODE = "DR_ACCOUNT_CODE";
    public static final String FSSC_CODE = "FSSC_CODE";
    public static final String PROJECT_ID = "PROJECT_ID";
    public static final String MAIN_TYPE_ID = "MAIN_TYPE_ID";
    public static final String DEPT_CODE = "DEPT_CODE";
    public static final String MAIN_TYPE_CODE = "MAIN_TYPE_CODE";
    public static final String UNIT_CODE = "UNIT_CODE";

    public static final String DOCUMENT_NUM = "DOCUMENT_NUM";

    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";

    public static final String PROJECT_CODE = "PROJECT_CODE";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String UNIT_ID = "UNIT_ID";

    public static final String DEPT_ID = "DEPT_ID";

    public static final String UNIT_NAME = "UNIT_NAME";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String ENTER_DATE = "ENTER_DATE";

    public static final String MONEY = "MONEY";

    public static final String STATUS = "STATUS";

    public static final String REMARK = "REMARK";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String ETX_1 = "ETX_1";

    public static final String ETX_2 = "ETX_2";

    public static final String ETX_3 = "ETX_3";

    public static final String ETX_4 = "ETX_4";

    public static final String ETX_5 = "ETX_5";

    public static final String JE_HEADER_ID = "JE_HEADER_ID";

    public static final String MAIN_TYPE_NAME = "MAIN_TYPE_NAME";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
