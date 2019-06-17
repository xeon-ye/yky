package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckAchTempate新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckAchTempate表单")
@Data
public class CheckAchTempateForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "业绩考核模板名称")
    private String achTempateName;

    @ApiModelProperty(value = "考核表类型")
    private Long checkTableTypeId;

    @ApiModelProperty(value = "考核工作名称")
    private Long checkWorkId;

    @ApiModelProperty(value = "考核单位")
    private Long checkDepartId;

    @ApiModelProperty(value = "权重")
    private String weight;

    @ApiModelProperty(value = "允许条数")
    private Long allowNumber;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "业绩考核模板内容")
    private List<CheckAchTempateContentForm> contentFormList ;


}
