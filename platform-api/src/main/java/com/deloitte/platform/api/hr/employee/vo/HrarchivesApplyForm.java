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
 * @Date : Create in 2019-05-21
 * @Description : HrarchivesApply新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrarchivesApply表单")
@Data
public class HrarchivesApplyForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "查档事由")
    private String applyReason;

    @ApiModelProperty(value = "${field.comment}")
    private String applyState;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "员工表ID")
    private String empId;

    @ApiModelProperty(value = "流程编号")
    private String processNum;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "查档内容")
    private String applyContent;

    @ApiModelProperty("档案人员")
    List<HrarchivesinfoApplyForm> hrarchivesinfoApplyFormList;

    @ApiModelProperty("附件")
    private List<HrAttachmentForm> attachments;

    private String id;

    private LocalDateTime returnTime;

    private String copynum;

    private String types;

}
