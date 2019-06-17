package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckUser新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckUser表单")
@Data
public class CheckUserForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考核工作id")
    private Long checkWorkId;

    @ApiModelProperty(value = "考核组织id")
    private Long checkOrgId;

    @ApiModelProperty(value = "教职工id")
    private Long userId;

    @ApiModelProperty(value = "是否参与")
    private String isPartake;

    @ApiModelProperty(value = "不参加原因")
    private String notPartakeReason;

    @ApiModelProperty(value = "组织code")
    private String orgCode;




}
