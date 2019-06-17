package com.deloitte.platform.api.project.vo;

import com.alibaba.fastjson.JSONArray;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/5 9:24
 * @Description :
 * @Modified:
 */
@ApiModel("科研修购（VO）")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScientificResearchVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目基本信息")
    private ProjectsVo projectsVo;

    @ApiModelProperty(value = "申报书基本信息")
    private ApplicationVo applicationVo;

    @ApiModelProperty(value = "单位基本信息")
    private OuVo ouVo;

    @ApiModelProperty(value = "仪器设备")
    private List<InsandequipVo> insandequipVoList = Lists.newArrayList();

    @ApiModelProperty(value = "科技经费")
    private List<ResearchfundsVo> researchfundsVoList = Lists.newArrayList();

    @ApiModelProperty(value = "科技成果")
    private List<AchieveResearchVo> achieveResearchVoList = Lists.newArrayList();

    @ApiModelProperty(value = "单位基本信息--人员信息（多条）")
    private List<PersonVo> personVoList = Lists.newArrayList();

    @ApiModelProperty(value = "房屋修缮基本信息(多条)")
    private List<AppAttachmentVo> appAttachmentVoList = Lists.newArrayList();

    @ApiModelProperty(value = "基础设施改造基本信息(多条)")
    private List<ImprovementsVo> improvementsVoList = Lists.newArrayList();

    @ApiModelProperty(value = "仪器设备购置信息(多条)")
    private List<EquipmentVo> equipmentVoList = Lists.newArrayList();

    @ApiModelProperty(value = "仪器设备升级改造信息(多条)")
    private List<EquipmentTransformVo> equipmentTransformVoList = Lists.newArrayList();

    @ApiModelProperty(value = "项目总投资")
    private List<AllActVo> allActVoList = Lists.newArrayList();

    @ApiModelProperty(value = "项目支出计划(多条)")
    private List<ActVo> actVoList = Lists.newArrayList();

    @ApiModelProperty(value = "项目支出计划子活动(多条)")
    private List<SubactVo> subactsVoList = Lists.newArrayList();

    @ApiModelProperty(value = "项目预算(多条)")
    private JSONArray budgetVoList = new JSONArray();

    @ApiModelProperty(value = "项目绩效")
    private JSONArray performanceVoList = new JSONArray();

    @ApiModelProperty(value = "附件（多个）")
    private List<FileVo> enclosureVoList = Lists.newArrayList();

    @ApiModelProperty(value = "审批历史信息")
    List<BpmProcessTaskVo> bpmProcessTaskVoList;

}
