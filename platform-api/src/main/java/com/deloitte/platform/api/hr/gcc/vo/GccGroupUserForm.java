package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-08
 * @Description : GccGroupUser新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccGroupUser表单")
@Data
public class GccGroupUserForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "人员信息id")
    private Long userId;

    @ApiModelProperty(value = "专家小组编号")
    private Long groupId;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "院校（校内，校外）")
    private String academy;

    @ApiModelProperty(value = "专家职称")
    private String expertsTitles;

    @ApiModelProperty(value = "个人电话")
    private String mobilePhone;

    @ApiModelProperty(value = "个人邮箱")
    private String personalEmail;

    @ApiModelProperty(value = "个人银行账号")
    private String bankNumber;

    @ApiModelProperty(value = "开户行")
    private String openBank;

    @ApiModelProperty(value = "银联号")
    private String unionpay;

    @ApiModelProperty(value = "是否有效")
    private String status;

    @ApiModelProperty(value = "员工号")
    private String empCode;

    @ApiModelProperty(value = "所在单位类型 0：内部单位  1：供应商  2：外部注册单位")
    private String deptGroupType;

}
