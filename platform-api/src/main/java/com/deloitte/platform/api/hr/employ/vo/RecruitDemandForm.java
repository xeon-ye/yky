package com.deloitte.platform.api.hr.employ.vo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-09
 * @Description : RecruitDemand新增修改form对象
 * @Modified :
 */
@ApiModel("新增RecruitDemand表单")
@Data
public class RecruitDemandForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "部门")
    private String dep;

    @ApiModelProperty(value = "编制")
    private String organization;

    @ApiModelProperty(value = "现有人员总数")
    private String numberallNow;

    @ApiModelProperty(value = "编内人数")
    private String numbersInside;

    @ApiModelProperty(value = "外聘编制")
    private String numbersOutside;

    @ApiModelProperty(value = "招聘当年年份")
    private String yearNow;

    @ApiModelProperty(value = "当年增加")
    private String yearIncrease;

    @ApiModelProperty(value = "当年减少")
    private String yearReduction;

    @ApiModelProperty(value = "近三年退休人")
    private String threeYearsRetire;

    @ApiModelProperty(value = "申请增员人数")
    private String applyNumbers;

    @ApiModelProperty(value = "增员岗位")
    private String increaseDep;

    @ApiModelProperty(value = "增员原因")
    private String reason;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "主键ID")
    private String id;
}
