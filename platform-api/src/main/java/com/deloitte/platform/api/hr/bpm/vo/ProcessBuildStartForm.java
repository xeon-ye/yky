package com.deloitte.platform.api.hr.bpm.vo;

import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Mr.Zhong
 * @Date ：Created in 2019/4/19 19:19
 */

/**
 * 构建流程启动参数
 * 该类中为启动流程需要参数中必须带的参数
 *    processVariables 只在需要的时候传入
 */
@Data
public class ProcessBuildStartForm extends BaseForm {

    /**
     * 如：培训申请, 处级以上 处级以下，审核流程不同,
     * 在流程图中采用 rank < 1 判断流程走向,
     * 那么 就在 map 中 put("rank","?") 来控制流程
     * 没有则为空
     */
    private Map<String, String> processVariables;

    /**
     * 流程定义key不能为空
     * 区别不同流程定义的唯一标识
     * 如: 休假申请审批流程为: A
     *     个人培训审批流程为: B
     * 在 com.deloitte.services.hr.bpm.constant.ProcessDefineKey 中定义
     */
    private String processDefineKey;

    /**
     * 发起该流程的人的账号
     * 暂定员工基础信息表中 ACCOUNT_ID 字段
     */
    private String submitterCode;

    /**
     * 发起该流程人的姓名
     */
    private String submitterName;

    /**
     * 发起该流程人的组织机构
     */
    private String submitterOrg;

    /**
     * HR系统 固定为 HR
     */
    private String sourceSystem;

    /**
     * 审批单据ID
     */
    private String objectId;

    /**
     * 下一审批环节参数
     */
    private ArrayList<NextNodeParamVo> nextNodeParamVo;

    /**
     * 负责组织
     */
    private String chargeOrg;

    /**
     * 单据类型
     */
    private String objectType;

    /**
     * 流程发起人岗位
     */
    private String submitterStation;

    /**
     * 单据流程编号
     */
    private String objectOrderNum;

    /**
     * 前端用url
     */
    private String objectUrl;


}
