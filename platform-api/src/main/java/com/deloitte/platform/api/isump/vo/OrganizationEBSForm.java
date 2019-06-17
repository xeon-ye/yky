package com.deloitte.platform.api.isump.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : zhangdi
 * @Date : Create in 2019-05-29
 */
@ApiModel("新增Organization表单")
@Data
public class OrganizationEBSForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "上级ID")
    private Long parentId;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "排序")
    private Long sort;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "上级code")
    private String parentCode;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "统一信用代码")
    private String commonCreditCode;

    @ApiModelProperty(value = "分组（ 虚拟组：fiction）")
    private String groupType;

    @ApiModelProperty(value = "处室负责人")
    private String dutyperson;

    @ApiModelProperty(value = "分管领导")
    private String leader;

    @ApiModelProperty(value = "简称")
    private String simpleName;

    @ApiModelProperty(value = "层级")
    private String orgLevel;

    @ApiModelProperty(value = "开户银行")
    private String bankAccount;

    @ApiModelProperty(value = "账户名称")
    private String bankName;

    @ApiModelProperty(value = "银行账号")
    private String bankAccountNumber;

}
