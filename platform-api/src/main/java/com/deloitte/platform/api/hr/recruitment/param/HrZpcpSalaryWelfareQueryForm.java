package com.deloitte.platform.api.hr.recruitment.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-24
 * @Description :   HrZpcpSalaryWelfare查询from对象
 * @Modified :
 */
@ApiModel("HrZpcpSalaryWelfare查询表单")
@Data
public class HrZpcpSalaryWelfareQueryForm extends BaseQueryForm<HrZpcpSalaryWelfareQueryParam>  {


    /*@ApiModelProperty(value = "主键")
    private Long id;*/

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "在聘信息表ID")
    private String infoId;
    /*
    @ApiModelProperty(value = "岗位")
    private String post;

    @ApiModelProperty(value = "薪酬制度")
    private String salarySystem;

    @ApiModelProperty(value = "基本年薪")
    private Long salaryYear;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;

    @ApiModelProperty(value = "安家费")
    private Double settle;*/

}
