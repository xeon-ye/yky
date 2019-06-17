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
 * @Description : CorptopublicApply新增修改form对象
 * @Modified :
 */
@ApiModel("新增CorptopublicApply表单")
@Data
public class CorptopublicApplyForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "用户表唯一标识")
    private String userid;

    @ApiModelProperty(value = "证件类型（1.原件 2 复印件）")
    private String certificatesType;

    @ApiModelProperty(value = "使用部门")
    private String usedep;

    @ApiModelProperty(value = "证书名称")
    private String certificate;

    @ApiModelProperty(value = "使用份数")
    private String useNumbers;

    @ApiModelProperty(value = "使用原因")
    private String useReason;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "流程编号")
    private String processNum;

    @ApiModelProperty(value = "员工表ID")
    private String empId;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentForm> attachments;

    @ApiModelProperty(value = "主键ID")
    private String id;

}
