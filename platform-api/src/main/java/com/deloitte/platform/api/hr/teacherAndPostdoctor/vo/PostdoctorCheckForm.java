package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-22
 * @Description : TeacherTrainTask新增修改form对象
 * @Modified :
 */
@ApiModel("审批表单")
@Data
public class PostdoctorCheckForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "申请信息ID")
    @NotNull(message = "申请信息ID不能为空")
    private String appId;

    @ApiModelProperty(value = "审核类型（1审核通过，2审核不通过）")
    @NotNull(message = "审核类型不能为空")
    private Integer checkType;

    @ApiModelProperty(value = "任务节点ID")
    @NotNull(message = "任务节点ID不能为空")
    private String taskId;

    @ApiModelProperty(value = "流程定义")
    @NotNull(message = "流程定义不能为空")
    private String processDefineKey;

    @ApiModelProperty(value = "审批意见/驳回意见")
    private String opinion;

}
