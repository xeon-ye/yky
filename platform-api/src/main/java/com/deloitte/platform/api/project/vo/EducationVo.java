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
 * @Date : Create in 2019/5/5 9:44
 * @Description :
 * @Modified:
 */
@ApiModel("教育修购项目VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目基本信息")
    private ProjectsVo projectsVo;

    @ApiModelProperty(value = "申报书基本信息")
    private ApplicationVo applicationVo;

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
