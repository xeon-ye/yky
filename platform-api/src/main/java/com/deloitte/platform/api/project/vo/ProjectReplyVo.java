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
public class ProjectReplyVo extends BaseVo {
    private String newMark; //80001,80002 是否 新建
    private String applicationId;
    private String projectId;
    private String replyId;
    private String msg;
    private String isReply;
    private ApplicationVo applicationVo;
    private ProjectsVo projectsVo;
    private ReplyVo replyVo;

    //支出
    private  List<AllActVo> allActVoList;
    private List<ActVo> actVoList;
    private List<SubactVo> subactsVoList;
    //绩效
    private JSONArray performanceVoList;
    //立项批复支出明细
    private List<ExpenseVo> expenseVoList;
    //立项批复经济
    private JSONArray ecnomicJsonArr;
    private JSONArray budgetJsonArray;
    private List<BudgetVo> budgetVoList;
    //立项批复专家列表
    private List<PersonVo> personVoList;
    private IPage<PersonVo> personVoIPage;
    //批复事件 7003保存 7004提交
    private String replyCode;
}
