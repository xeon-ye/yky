package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description : ZpcpTalentProject新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpTalentProject表单")
@Data
public class ZpcpTalentProjectForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "人才项目名称")
    private String talentName;

    @ApiModelProperty(value = "入选时间")
    private LocalDate selectedTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
