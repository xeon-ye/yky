package com.deloitte.platform.api.project.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonVo extends BaseVo{

    private UserVo userVo;
    private DeptVo deptVo;

    private ApplicationVo applicationVo;
    private ProjectsVo projectsVo;
    //自定义项目表
    private ProjectsAndApplicationVo projectsAndApplicationVo;

    //评审-----------------
    //支出
    private  List<AllActVo> allActVoList;
    private List<ActVo> actVoList;
    private List<List<SubactVo>> subActVoList;
    //预算
    private List<BudgetVo> projectBudgetVoList;
    //绩效
    private List<PerformanceVo> performanceVoList;
    //评审表
    private List<ReviewVo> reviewVo;
    //专家
    private List<ExpertVo> expertVoList;
    private IPage<ExpertVo> expertVoIPage;
    //审批意见
    private List<ReviewNoteVo> reviewNoteVoList;

    //立项批复------
    private List<ReplyVo> replyVoList;
    //立项批复支出明细
    private List<ExpenseVo> expenseVoList;
    //立项批复经济
    private List<EconomicVo> economicVoList;
    //立项批复专家列表
    private List<PersonVo> personVoList;
    private IPage<PersonVo> personVoIPage;

    //项目执行--------
    private List<ExecutionVo> executionVoList;
    //项目执行变更记录表
    private List<ExeChangeVo> exeChangeVoList;

    private List<ExeBudgetVo> exeBudgetVoList;

    //评审事件 2006通过 2004调整 2005拒绝
    private String reviewCode;
    //批复事件 7003保存 7004提交
    private String replyCode;
    //项目执行事件

    private String projectId;
    private String applicationId;
}
