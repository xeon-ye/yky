package com.deloitte.platform.api.project.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainProVo {
    private String projectId;

    @ApiModelProperty(value = "项目")
    private ProjectsVo projectsVo;

    @ApiModelProperty(value = "项目维护")
    private List<MaintenanceVo> maintenanceVoList;

    @ApiModelProperty(value = "项目成员")
    private List<PersonVo> personVoList;

    @ApiModelProperty(value = "项目支出预算")
    private List<MaintBudgetVo> maintBudgetVoList;

    @ApiModelProperty(value = "状态code")
    private String projectStatusCode;

    @ApiModelProperty(value = "状态name")
    private String projectStatusName;

    @ApiModelProperty(value = "项目审批")
    private List<MaintReplyVo> maintReplyVoList;

    @ApiModelProperty(value = "项目审批历史表")
    private List<MaintReplyNoteVo> maintReplyNoteVos;
}
