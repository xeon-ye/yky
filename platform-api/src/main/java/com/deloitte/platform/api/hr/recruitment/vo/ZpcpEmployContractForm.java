package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-24
 * @Description : ZpcpEmployContract新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpEmployContract表单")
@Data
public class ZpcpEmployContractForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "合同类型")
    private String contractType;

    @ApiModelProperty(value = "合同开始日期")
    private LocalDate contractStartdate;

    @ApiModelProperty(value = "合同结束日期")
    private LocalDate contractEnddate;

    @ApiModelProperty(value = "聘用时长")
    private String appointmentTime;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "在聘信息表ID")
    private Long infoId;

    @ApiModelProperty(value = "附件对象")
    private HrAttachmentForm hrAttachmentForm;

}
