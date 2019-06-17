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
 * @Description : CorptoprivateApply新增修改form对象
 * @Modified :
 */
@ApiModel("新增CorptoprivateApply表单")
@Data
public class CorptoprivateApplyForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "证书名称")
    private String certificateName;

    @ApiModelProperty(value = "使用份数")
    private String useNumbers;

    @ApiModelProperty(value = "使用事由")
    private String useReason;

    @ApiModelProperty(value = "状态")
    private String applyState;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "流程编号")
    private String processNum;

    @ApiModelProperty(value = "员工基础信息表ID")
    private String empId;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentForm> attachments;

    @ApiModelProperty(value = "主键ID")
    public String id;

}
