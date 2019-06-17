package com.deloitte.platform.api.hr.employee.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-08
 * @Description : WorkproveApply新增修改form对象
 * @Modified :
 */
@ApiModel("新增WorkproveApply表单")
@Data
public class WorkproveApplyForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主表ID")
    private String id;

    @ApiModelProperty(value = "员工表唯一标识 ")
    private String userid;

    @ApiModelProperty(value = "申请原因")
    private String applyReason;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "备注")
    private String remake;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "流程编号")
    private String processNum;

    @ApiModelProperty(value = "申请人姓名")
    private String empId;

    @ApiModelProperty("附件")
    private List<HrAttachmentForm> attachments;

    @ApiModelProperty(value = "使用标准模板 0不使用 1使用")
    private String worktemplate;

}
