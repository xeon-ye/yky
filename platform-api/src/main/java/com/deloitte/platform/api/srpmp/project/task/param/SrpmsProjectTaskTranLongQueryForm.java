package com.deloitte.platform.api.srpmp.project.task.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-17
 * @Description :   SrpmsProjectTaskTranLong查询from对象
 * @Modified :
 */
@ApiModel("SrpmsProjectTaskTranLong查询表单")
@Data
public class SrpmsProjectTaskTranLongQueryForm extends BaseQueryForm<SrpmsProjectTaskTranLongQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "预期成果类型")
    private String projectResultType;

    @ApiModelProperty(value = "所在领域")
    private String belongDomain;

    @ApiModelProperty(value = "任务分解相关说明")
    private String taskDecompositionInstruction;

    @ApiModelProperty(value = "参加人员相关说明")
    private String joinPersonInstruction;

    @ApiModelProperty(value = "预算相关说明")
    private String budgetInstruction;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
