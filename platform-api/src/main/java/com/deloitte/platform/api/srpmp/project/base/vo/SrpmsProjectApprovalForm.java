package com.deloitte.platform.api.srpmp.project.base.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-27
 * @Description : SrpmsProjectApproval新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectApproval表单")
@Data
public class SrpmsProjectApprovalForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目编码")
    private String projectNum;

    @ApiModelProperty(value = "审批意见")
    private String approvalComments;

    @ApiModelProperty(value = "审批时间")
    private LocalDateTime approvalTime;

    @ApiModelProperty(value = "审批员工")
    private Long approver;

}
