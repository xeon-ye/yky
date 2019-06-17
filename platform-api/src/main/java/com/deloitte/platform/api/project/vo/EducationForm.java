package com.deloitte.platform.api.project.vo;

import com.alibaba.fastjson.JSONArray;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/5 9:44
 * @Description :
 * @Modified:
 */
@ApiModel("教育修购FORM")
@Data
public class EducationForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目基本信息")
    private ProjectsForm projectsForm;

    @ApiModelProperty(value = "申报书基本信息")
    private ApplicationForm applicationForm;

    @ApiModelProperty(value = "项目总投资")
    private List<AllActForm> allActFormList;

    @ApiModelProperty(value = "项目支出计划(多条)")
    private List<ActForm> actFormList;

    @ApiModelProperty(value = "项目支出计划子活动(多条)")
    private List<SubactForm> subactFormList;

    @ApiModelProperty(value = "项目预算(多条)")
    private JSONArray budgetFormList;

    @ApiModelProperty(value = "项目绩效")
    private JSONArray performanceFormList;

    @ApiModelProperty(value = "附件")
    private List<FileVo> enclosureFormList;

}
