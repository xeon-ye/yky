package com.deloitte.platform.api.project.vo;

import com.alibaba.fastjson.JSONArray;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel("项目执行（VO）")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectExecutionVo extends BaseVo {
    private String applicationId;
    private String projectId;
    private String replyId;
    private String mainId; //项目维护id
    private String mainMark; //查询结果是否为项目维护
    private String exeId; //项目执行id

    private ProjectsVo projectsVo;
    private ReplyVo replyVo;
    private MaintReplyVo maintReplyVo;
    private List<ExeBudgetVo> exeBudgetVoList;
    private JSONArray budgetVoList;
    private List<ExePerformanceVo> exePerformanceVoList;

}
