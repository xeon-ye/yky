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
 * @Description : IncumproveApply新增修改form对象
 * @Modified :
 */
@ApiModel("新增IncumproveApply表单")
@Data
public class IncumproveApplyForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "申请原因")
    private String applyReason;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "申请单位")
    private String applyDep;

    @ApiModelProperty(value = "备注")
    private String remake;

    @ApiModelProperty(value = "签字盖章")
    private String sign;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "流程编号")
    private String processNum;

    @ApiModelProperty(value = "申请人姓名")
    private String name;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentForm> attachments;

    @ApiModelProperty(value = "员工基础信息表ID")
    private String empId;

    @ApiModelProperty(value = "使用标准模板 0不使用 1使用")
    private String worktemplate;

}
