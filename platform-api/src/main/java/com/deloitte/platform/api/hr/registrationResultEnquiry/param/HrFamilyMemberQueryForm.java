package com.deloitte.platform.api.hr.registrationResultEnquiry.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description :   HrFamilyMember查询from对象
 * @Modified :
 */
@ApiModel("HrFamilyMember查询表单")
@Data
public class HrFamilyMemberQueryForm extends BaseQueryForm<HrFamilyMemberQueryParam>  {


    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "社会人员个人基本情况ID")
    private String socialId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "关系")
    private String relation;

    @ApiModelProperty(value = "年龄")
    private String age;

    @ApiModelProperty(value = "所在单位及部门")
    private String companyAndDepartment;

    @ApiModelProperty(value = "职务")
    private String duty;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "毕业生个人基本情况ID")
    private String graduateId;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;
}
