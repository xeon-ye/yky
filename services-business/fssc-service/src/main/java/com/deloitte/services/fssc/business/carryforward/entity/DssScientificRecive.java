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
 * 科研项目到位经费表
 * </p>
 *
 * @author chenx
 * @since 2019-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("DSS_SCIENTIFIC_RECIVE")
@ApiModel(value="DssScientificRecive对象", description="财务项目到位经费表")
public class DssScientificRecive extends Model<DssScientificRecive> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识 唯一标识")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目编码")
    @TableField("PRO_CODE")
    private String proCode;

    @ApiModelProperty(value = "任务编码")
    @TableField("TASK_CODE")
    private String taskCode;

    @ApiModelProperty(value = "到位时间 到位时间 存储yyyymm")
    @TableField("RECIVE_DATE")
    private LocalDateTime reciveDate;

    @ApiModelProperty(value = "到位金额 到位金额")
    @TableField("FUNDS")
    private BigDecimal funds;

    @ApiModelProperty(value = "到位单位ID 到位单位ID")
    @TableField("RECIVE_DEPT_ID")
    private Long reciveDeptId;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "来源系统")
    @TableField("FROM_SYSTEM")
    private String fromSystem;

    @ApiModelProperty(value = "预算年度")
    @TableField("BUDGET_YEAR")
    private String budgetYear;

    @ApiModelProperty(value = "财务单据编码")
    @TableField("DOCUMENT_NUM  ")
    private String documentNum  ;

    @ApiModelProperty(value = "Y  在用  N 已冲销")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "财务系统总唯一标识数据的ID")
    @TableField("CW_LINE_ID")
    private Long cwLineId;


    public static final String ID = "ID";

    public static final String PRO_CODE = "PRO_CODE";

    public static final String TASK_CODE = "TASK_CODE";

    public static final String RECIVE_DATE = "RECIVE_DATE";

    public static final String FUNDS = "FUNDS";

    public static final String RECIVE_DEPT_ID = "RECIVE_DEPT_ID";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String FROM_SYSTEM = "FROM_SYSTEM";

    public static final String BUDGET_YEAR = "BUDGET_YEAR";

    public static final String DOCUMENT_NUM   = "DOCUMENT_NUM  ";

    public static final String STATUS = "STATUS";

    public static final String CW_LINE_ID = "CW_LINE_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
