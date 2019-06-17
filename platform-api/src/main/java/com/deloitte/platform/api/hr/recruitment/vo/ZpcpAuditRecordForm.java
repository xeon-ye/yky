package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-26
 * @Description : ZpcpAuditRecord新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpAuditRecord表单")
@Data
public class ZpcpAuditRecordForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "申报表ids数组")
    private List<Long> declareIds;

    @ApiModelProperty(value = "2.聘任工作组审核未通过,3.聘任工作组审核通过,4学术委员会审核未通过,5.学术委员会审核通过,6.教授委员会审核未通过,7.教授委员会审核通过,8.教职聘任委员会审核未通过,9.教职聘任委员会审核通过")
    private String checkStatus;

    @ApiModelProperty(value = "审核意见")
    private String auditOpinion;

    @ApiModelProperty(value = "组织机构")
    private String organizationCode;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "审核类型(1.工作组审核,2.学术委员会,3.教授聘任委员会,4.教职聘任委员会)")
    private String checkType;
}
