package com.deloitte.platform.api.project.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectChangeVo {
    private String projectId;
    private String replyId;
    private String mainId;
    private String mainMark;//是否为项目维护项目

    private ApplicationVo applicationVo;
    private ProjectsVo projectsVo;

    private ReplyVo replyVo;

    private MaintReplyVo maintReplyVo;
    private List<MaintBudgetVo> maintBudgetVos;
    private List<ChangeBudgetBakVo> changeBudgetBakVos;

    private List<ChangeProVo> changeProVoList;
    private List<ChangeNoteVo> changeNoteVoList;

    private List<ActVo> actVoList;
    private List<SubactVo> subactsVoList;
    private List<ActBakVo> actBakVoList;
    private List<SubactBakVo> subBakLists;

    private JSONObject budgetJsonObject;
    private JSONObject budgetBakJsonObject;

    private List<PerformanceVo> performanceVoList;
    private List<PerformanceBakVo> performanceBakVoList;

    private List<PersonVo> personVoList;
    private List<PersonBakVo> personBakVoList;

    private List<ExeReplyVo> exeReplyVos;
    private List<ExeRepHisVo> exeRepHisVos;
}
