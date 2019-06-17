package com.deloitte.platform.api.hr.check.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description :   CheckUser查询from对象
 * @Modified :
 */
@ApiModel("CheckUserInfo查询表单")
@Data
public class CheckUserInfoQueryForm extends BaseQueryForm<CheckUserQueryParam>  {

    @ApiModelProperty(value = "考核组织id")
    private String checkOrgId;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "考核工作id")
    private String checkWorkId;

}
