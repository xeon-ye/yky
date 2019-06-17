package com.deloitte.platform.api.hr.staffAllotment.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-03
 * @Description : HrExternalTransfer新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrExternalTransfer表单")
@Data
public class HrExternalTransferForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工编码")
    private String staffCode;

    @ApiModelProperty(value = "申请部门")
    private String department;

    @ApiModelProperty(value = "申请岗位")
    private String position;

    @ApiModelProperty(value = "申请说明")
    private String explain;

    @ApiModelProperty(value = "申请单位")
    private String company;

    @ApiModelProperty(value = "拟调动日期")
    private LocalDateTime proposedTransferDate;

    @ApiModelProperty(value = "档案接收单位")
    private String fileReceivingUnit;

    @ApiModelProperty(value = "流程编号")
    private String taskId;

    @ApiModelProperty(value = "附件列表")
    private ArrayList<HrAttachmentForm> attachments;


}
