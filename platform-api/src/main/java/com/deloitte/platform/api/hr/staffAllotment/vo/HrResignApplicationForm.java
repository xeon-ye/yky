package com.deloitte.platform.api.hr.staffAllotment.vo;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Description : HrResignApplication新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrResignApplication表单")
@Data
public class HrResignApplicationForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工编码")
    private String staffCode;

    @ApiModelProperty(value = "申请人日期")
    private LocalDateTime dat;

    @ApiModelProperty(value = "辞职原因：下拉选项")
    private String reason;

    @ApiModelProperty(value = "辞职说明")
    private String exp;

    @ApiModelProperty(value = "档案接收单位")
    private String fileReceivingUnit;

    @ApiModelProperty(value = "离职去向")
    private String leaveTo;

    @ApiModelProperty(value = "流程编号")
    private String taskId;

    @ApiModelProperty(value = "附件列表")
    private ArrayList<HrAttachmentForm> attachments;

}
