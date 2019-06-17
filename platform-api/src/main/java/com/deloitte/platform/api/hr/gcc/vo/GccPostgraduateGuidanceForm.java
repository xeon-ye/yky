package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccPostgraduateGuidance新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccPostgraduateGuidance表单")
@Data
public class GccPostgraduateGuidanceForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "人员编号")
    private Long userId;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "博士生毕业人数")
    private Long doctoralGraduatesNum;

    @ApiModelProperty(value = "博士再度人数")
    private Long doctoralCandidates;

    @ApiModelProperty(value = "硕士生毕业人数")
    private Long graduateStudentsNum;

    @ApiModelProperty(value = "硕士生再度人数")
    private Long degreeCandidates;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentForm attachmentForm;

}
