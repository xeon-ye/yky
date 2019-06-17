package com.deloitte.platform.api.project.vo;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel("项目评审（VO）")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectReviewVo extends BaseVo {
    private String projectId;
    private String applicationId;

    private ProjectsVo projectsVo;
    private ApplicationVo applicationVo;

    private ReviewVo reviewVo;
    private List<ExpertVo> expertVoList;

    private List<ActVo> actVoList;
    private List<SubactVo> subActVos;
    private List<List<SubactVo>> subActList;

    private JSONArray budgetJsonArray;

    private JSONArray performanceVoList;
    private List<ReviewNoteVo> reviewNoteVoList;

    private List<EnclosureVo> enclosureVos;
    private JSONArray revFileJsonArr;
    //评审事件 6002调整 6003通过 6004拒绝
    private String reviewCode;

}
