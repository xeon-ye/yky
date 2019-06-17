package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckUser新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckUser表单")
@Data
public class CheckUserAddForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id删除的时候传")
    private String id;

    @ApiModelProperty(value = "考核工作id")
    private Long checkWorkId;

    @ApiModelProperty(value = "考核组织id")
    private Long checkOrgId;

    @ApiModelProperty(value = "教职工IdList")
    private List<String> userList;


    @ApiModelProperty(value = "导入时，组织机构List")
    private List<String> orgCodeList;

}
