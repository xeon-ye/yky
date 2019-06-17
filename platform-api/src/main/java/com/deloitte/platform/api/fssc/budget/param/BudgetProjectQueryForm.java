package com.deloitte.platform.api.fssc.budget.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-20
 * @Description :   BudgetProject查询from对象
 * @Modified :
 */
@ApiModel("BudgetProject查询表单")
@Data
public class BudgetProjectQueryForm extends BaseQueryForm<BudgetProjectQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "项目ID")
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

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "财务-会计科目")
    private String accountCode;

}
