package com.deloitte.services.fssc.budget.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 预算调整-预算细化调整头表
 * </p>
 *
 * @author jaws
 * @since 2019-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BUDGET_DETAILING_ADJUST_HEAD")
@ApiModel(value="BudgetDetailingAdjustHead对象", description="预算调整-预算细化调整头表")
public class BudgetDetailingAdjustHead extends Model<BudgetDetailingAdjustHead> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单据编号")
    @TableField("DOCUMENT_NUM")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    @TableField("DOCUMENT_STATUS")
    private String documentStatus;

    @ApiModelProperty(value = "归属单位ID")
    @TableField("UNIT_ID")
    private Long unitId;

    @ApiModelProperty(value = "单位编码")
    @TableField("UNIT_CODE")
    private String unitCode;

    @ApiModelProperty(value = "归属单位名称")
    @TableField("UNIT_NAME")
    private String unitName;

    @ApiModelProperty(value = "归属部门id")
    @TableField("DEPT_ID")
    private Long deptId;

    @ApiModelProperty(value = "部门编码")
    @TableField("DEPT_CODE")
    private String deptCode;

    @ApiModelProperty(value = "归属部门名称")
    @TableField("DEPT_NAME")
    private String deptName;

    @ApiModelProperty(value = "申请金费类型")
    @TableField("FUND_TYPE")
    private String fundType;

    @ApiModelProperty(value = "关联单据编号")
    @TableField("RELATED_DOCUMENT_NUM")
    private String relatedDocumentNum;

    @ApiModelProperty(value = "关联单据ID")
    @TableField("RELATED_DOCUMENT_ID")
    private Long relatedDocumentId;

    @ApiModelProperty(value = "关联单据类型")
    @TableField("RELATED_DOCUMENT_TYPE")
    private String relatedDocumentType;

    @ApiModelProperty(value = "序号")
    @TableField("LINE_NUM")
    private Long lineNum;

    @ApiModelProperty(value = "申请行ID")
    @TableField("LINE_ID")
    private Long lineId;

    @ApiModelProperty(value = "申请总额")
    @TableField("APPLY_TOTAL")
    private BigDecimal applyTotal;

    @ApiModelProperty(value = "说明")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "组织ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "组织路径")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "申请人")
    @TableField("APPLY_FOR_PERSON")
    private String applyForPerson;

    @ApiModelProperty(value = "申请人名称")
    @TableField("APPLY_FOR_PERSON_NAME")
    private String applyForPersonName;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "扩展字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "版本")
    @TableField("VERSION")
    @Version
    private Long version;

    public static final String ID = "ID";

    public static final String DOCUMENT_NUM = "DOCUMENT_NUM";

    public static final String DOCUMENT_STATUS = "DOCUMENT_STATUS";

    public static final String UNIT_ID = "UNIT_ID";

    public static final String UNIT_CODE = "UNIT_CODE";

    public static final String UNIT_NAME = "UNIT_NAME";

    public static final String DEPT_ID = "DEPT_ID";

    public static final String DEPT_CODE = "DEPT_CODE";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String FUND_TYPE = "FUND_TYPE";

    public static final String RELATED_DOCUMENT_NUM = "RELATED_DOCUMENT_NUM";

    public static final String RELATED_DOCUMENT_ID = "RELATED_DOCUMENT_ID";

    public static final String RELATED_DOCUMENT_TYPE = "RELATED_DOCUMENT_TYPE";

    public static final String LINE_NUM = "LINE_NUM";

    public static final String APPLY_TOTAL = "APPLY_TOTAL";

    public static final String REMARK = "REMARK";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String APPLY_FOR_PERSON = "APPLY_FOR_PERSON";

    public static final String APPLY_FOR_PERSON_NAME = "APPLY_FOR_PERSON_NAME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String VERSION = "VERSION";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
