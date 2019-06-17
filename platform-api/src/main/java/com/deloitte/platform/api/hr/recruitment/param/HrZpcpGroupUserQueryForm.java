package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :   HrZpcpGroupUser查询from对象
 * @Modified :
 */
@ApiModel("HrZpcpGroupUser查询表单")
@Data
public class HrZpcpGroupUserQueryForm extends BaseQueryForm<HrZpcpGroupUserQueryParam>  {

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "教职工姓名")
    private String name;

    @ApiModelProperty(value = "聘任工作组编号")
    private Long workgroupId;

    private String isvalid;
}
