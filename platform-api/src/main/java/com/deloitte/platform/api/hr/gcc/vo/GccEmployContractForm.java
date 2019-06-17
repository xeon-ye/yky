package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-12
 * @Description : GccEmployContract新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccEmployContract表单")
@Data
public class GccEmployContractForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "教职工编号")
    private Long userId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "所属单位")
    private String unit;

    @ApiModelProperty(value = "合同开始日期")
    private LocalDate contractStartdate;

    @ApiModelProperty(value = "合同结束日期")
    private LocalDate contractEnddate;

    @ApiModelProperty(value = "是否需签合同")
    private String signContract;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentForm attachmentForms;

    @ApiModelProperty(value = "附件信息对象-离职报告证明")
    private HrAttachmentForm attachmentForms1;

    @ApiModelProperty(value = "附件信息对象-合同稿")
    private HrAttachmentForm attachmentForms2;

    @ApiModelProperty(value = "入选人员编号")
    private Long highLevelId;

    @ApiModelProperty(value = "是否提醒 0 否，1是")
    private String warn;
}
