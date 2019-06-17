package com.deloitte.platform.api.hr.employee.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.util.List;

/**
 * @Author : LJH
 * @Date : Create in 2019-05-22
 * @Description : HrdataApply新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrdataApply表单")
@Data
public class HrdataApplyForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "截止时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "单位")
    private String company;

    @ApiModelProperty(value = "人员类型")
    private String popType;

    @ApiModelProperty(value = "数据用途")
    private String dataUsage;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "申请人ID")
    private String empId;

    @ApiModelProperty(value = "流程编号")
    private String processNum;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "${field.comment}")
    private String applyState;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty("附件")
    private List<HrAttachmentForm> attachments;

    private String id;

}
