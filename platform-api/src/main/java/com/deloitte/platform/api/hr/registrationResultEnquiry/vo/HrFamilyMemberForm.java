package com.deloitte.platform.api.hr.registrationResultEnquiry.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description : HrFamilyMember新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrFamilyMember表单")
@Data
public class HrFamilyMemberForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "社会人员个人基本情况ID")
    private Long socialId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "关系")
    private String relation;

    @ApiModelProperty(value = "年龄")
    private Long age;

    @ApiModelProperty(value = "所在单位及部门")
    private String companyAndDepartment;

    @ApiModelProperty(value = "职务")
    private String duty;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "毕业生个人基本情况ID")
    private Long graduateId;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;

}
