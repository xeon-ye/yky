package com.deloitte.platform.api.srpmp.project.base.vo.ext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetYearVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Author:LIJUN
 * Date:22/04/2019
 * Description:
 */
@ApiModel("变更同步财务VO")
@Data
public class SrpmsUpdateSyncFsscVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    //立项消息
    public static final String TYPE_PROJECT_TASK_PASS = "TYPE_PROJECT_TASK_PASS";
    //参与人变更消息
    public static final String TYPE_MODIFY_JOIN_PERSON = "TYPE_MODIFY_JOIN_PERSON";
    //负责人变更消息
    public static final String TYPE_MODIFY_LEAD_PERSON = "TYPE_MODIFY_LEAD_PERSON";
    //项目状态变更消息
    public static final String TYPE_MODIFY_PROJECT_STATUS = "TYPE_MODIFY_PROJECT_STATUS";
    //绩效目标消息
    public static final String TYPE_BUDGET_TASK_SYNC = "TYPE_BUDGET_TASK_SYNC";
    //年度预算消息
    public static final String TYPE_BUDGET_APPLY_SYNC = "TYPE_BUDGET_APPLY_SYNC";
    //预算变更消息
    public static final String TYPE_MODIFY_BUDGET_SYNC = "TYPE_MODIFY_BUDGET_SYNC";

    @ApiModelProperty(value = "消息类型（TYPE_XXX）")
    private String msgType;

    @ApiModelProperty(value = "项目信息")
    private SrpmsProjectSyncVo srpmsProjectSyncVo;

    @ApiModelProperty(value = "任务信息")
    private List<SrpmsProjectTaskSyncVo> srpmsProjectTaskSyncVoList;

    @ApiModelProperty(value = "参与人员变更最新版本")
    private SrpmsPersonUpdateSyncVo updateMemberVo;

    @ApiModelProperty(value = "负责人变更最新版本")
    private SrpmsPersonUpdateSyncVo updateLeaderVo;

    @ApiModelProperty(value = "绩效目标")
    private SrpmsBudgetTaskSyncVo budgetTask;

}
