package com.deloitte.platform.api.fssc.budget.vo;

import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-20
 * @Description : BudgetProject返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "项目预算")
public class BudgetProjectVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "项目ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long projectId;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "类型名称")
    private String typeName;

    @ApiModelProperty(value = "项目大类")
    private String projectBigType;

    @ApiModelProperty(value = "大类名称")
    private String bigTypeName;

    @ApiModelProperty(value = "项目中类")
    private String projectMiddleType;

    @ApiModelProperty(value = "中类名称")
    private String middleTypeName;

    @ApiModelProperty(value = "项目小类")
    private String projectSmallType;

    @ApiModelProperty(value = "小类名称")
    private String smallTypeName;

    @ApiModelProperty(value = "项目负责人")
    private String projectDuty;

    @ApiModelProperty(value = "外部承担单位")
    private String responsibleUnitCode;

    @ApiModelProperty(value = "外部承担单位名称")
    private String responsibleUnitName;

    @ApiModelProperty(value = "归属部门")
    private Long belongToDepartId;

    @ApiModelProperty(value = "是否父项目")
    private String parentFlag;

    @ApiModelProperty(value = "父项目ID")
    private Long parentId;

    @ApiModelProperty(value = "父编码(名称)")
    private String parentCodeName;

    @ApiModelProperty(value = "内部单位ID")
    private Long orgUnitId;

    @ApiModelProperty(value = "内部单位")
    private String orgUnitCode;

    @ApiModelProperty(value = "内部单位名称")
    private String orgUnitName;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

    @ApiModelProperty(value = "状态")
    private String projectStatus;

    @ApiModelProperty(value = "预算科目ID")
    private Long budgetAccountId;

    @ApiModelProperty(value = "预算科目编码")
    private String budgetAccountCode;

    @ApiModelProperty(value = "预算科目名称")
    private String budgetAccountName;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    @ApiModelProperty(value = "项目开始时间")
    private LocalDateTime projectStartTime;

    @ApiModelProperty(value = "项目结束时间")
    private LocalDateTime projectEndTime;


    //新增财务自己的字段
    @ApiModelProperty(value = "财务code")
    private String fsscCode;

    @ApiModelProperty(value = "经费类型")
    private String moneyType;

    @ApiModelProperty(value = "计划号")
    private String planNum;

    @ApiModelProperty(value = "关联号")
    private String connectNum;

    @ApiModelProperty(value = "是否允许报账")
    private String isAllowExpense;

    @ApiModelProperty(value = "款项大类id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long inComeMainTypeId;

    @ApiModelProperty(value = "款项小类id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long inComeSubTypeId;

    @ApiModelProperty(value = "项目总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    private String ext5;

    @ApiModelProperty(value = "收入结转方式")
    private String paymentConfirmType;

    @ApiModelProperty(value = "财务-会计科目编码")
    private String accountCode;

}
