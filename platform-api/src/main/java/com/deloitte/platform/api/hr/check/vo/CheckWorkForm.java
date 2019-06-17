package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckWork新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckWork表单")
@Data
public class CheckWorkForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考核工作名称")
    private String workName;

    @ApiModelProperty(value = "考核期间id")
    private Long checkTimeId;

    @ApiModelProperty(value = "考核单位id")
    private Long departId;

    @ApiModelProperty(value = "考核层级")
    private String checkLevel;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
