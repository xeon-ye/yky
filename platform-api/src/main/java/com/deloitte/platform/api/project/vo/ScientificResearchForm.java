package com.deloitte.platform.api.project.vo;

import com.alibaba.fastjson.JSONArray;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/5 9:24
 * @Description :
 * @Modified:
 */
@ApiModel("新增ScientificResearchForm（科研修购）")
@Data
public class ScientificResearchForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目基本信息")
    private ProjectsForm projectsForm;

    @ApiModelProperty(value = "申报书基本信息")
    private ApplicationForm applicationForm;

    @ApiModelProperty(value = "单位基本信息")
    private OuForm ouForm;

    @ApiModelProperty(value = "仪器设备")
    private List<InsandequipForm> insandequipFormList;

    @ApiModelProperty(value = "科技经费")
    private List<ResearchfundsForm> researchfundsFormList;

    @ApiModelProperty(value = "科技成果")
    private List<AchieveResearchForm> achieveResearchFormList;

    @ApiModelProperty(value = "人员信息（多条）")
    private List<PersonForm> personFormList;

    @ApiModelProperty(value = "房屋修缮基本信息(多条)")
    private List<AppAttachmentForm> appAttachmentFormList;

    @ApiModelProperty(value = "基础设施改造基本信息(多条)")
    private List<ImprovementsForm> improvementsFormList;

    @ApiModelProperty(value = "仪器设备购置信息(多条)")
    private List<EquipmentForm> equipmentFormList;

    @ApiModelProperty(value = "仪器设备升级改造信息(多条)")
    private List<EquipmentTransformForm> equipmentTransformFormList;

    @ApiModelProperty(value = "项目总投资（多条）")
    private List<AllActForm> allActFormList;

    @ApiModelProperty(value = "项目支出计划(多条)")
    private List<ActForm> actFormList;

    @ApiModelProperty(value = "项目支出计划子活动(多条)")
    private List<SubactForm> subactFormList;

    @ApiModelProperty(value = "项目预算(多条)")
    private JSONArray budgetFormList;

    @ApiModelProperty(value = "项目绩效")
    private JSONArray performanceFormList;

    @ApiModelProperty(value = "附件（多条）")
    private List<FileVo> enclosureFormList;

}
