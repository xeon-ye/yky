package com.deloitte.platform.api.hr.retireRehiring.vo;

import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
public class HRBPMForm extends BaseForm {

    /**
     * 如：培训申请, 处级以上 处级以下，审核流程不同,
     * 在流程图中采用 rank < 1 判断流程走向,
     * 那么 就在 map 中 put("rank","?") 来控制流程
     * 没有则为空
     */
    private Map<String, String> processVariables = new HashMap<String, String>();

    //负责组织
    private String chargeOrg = "1001001";

    /**
     * 流程单号
     */
    private String taskId = "start";

    /**
     * 流程定义key不能为空
     * 区别不同流程定义的唯一标识
     * 如: 休假申请审批流程为: A
     * 个人培训审批流程为: B
     * 在 com.deloitte.services.hr.bpm.constant.ProcessDefineKey 中定义
     */
    private String processDefineKey;

    //发起该流程人的组织机构
    private String submitterOrg = "1001001";

    //单据类型 单据简要说明
    private String objectType = "退休返聘到期提醒";

    //发起该流程人的姓名
    private String submitterName = "系统";

    //发起该流程的人的账号
    private String submitterCode = "HR001";

    //HR系统 固定为 HR
    //private String sourceSystem;

    //单据流程编号
    private String objectOrderNum;

    //审批单据ID
    private String objectId;

    //下一审批环节参数
    private ArrayList<NextNodeParamVo> nextNodeParamVo;

    //流程发起人岗位
    private String submitterStation = "系统通知";




}
