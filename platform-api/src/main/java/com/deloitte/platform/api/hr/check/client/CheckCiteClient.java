package com.deloitte.platform.api.hr.check.client;


import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :  CheckCiteClient控制器接口
 * @Modified :
 */
public interface CheckCiteClient {

    public static final String path="/hr/check-cite";


    /**
     * 检查考核期间引用
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkTimeCite")
    Result checkTimeCite(@Valid @RequestBody Long id);

    /**
     * 检查考核表类型引用
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkTableTypeCite")
    Result checkTableTypeCite(@Valid @RequestBody Long id);

    /**
     * 检查考核模板引用
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkemplateCite")
    Result checkemplateCite(@Valid @RequestBody Long id);


    /**
     * 检查评估方式引用
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkEvaluateModeCite")
    Result checkEvaluateModeCite(@Valid @RequestBody Long id);


    /**
     * 检查业绩考核模板引用
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkAchTempateCite")
    Result checkAchTempateCite(@Valid @RequestBody Long id);


    /**
     * 检查业绩考核模板内容引用
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkAchTempateContentCite")
    Result checkAchTempateContentCite(@Valid @RequestBody Long id);

    /**
     * 检查考核等级规则引用
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkRuleCite")
    Result checkRuleCite(@Valid @RequestBody Long id);

    /**
     * 检查考核等级规则内容引用
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkRuleContentCite")
    Result checkRuleContentCite(@Valid @RequestBody Long id);

    /**
     * 检查测评优秀比例引用
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkExcellentRatiCite")
    Result checkExcellentRatiCite(@Valid @RequestBody Long id);


    /**
     * 检查截至时间引用
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkDeadlineCite")
    Result checkDeadlineCite(@Valid @RequestBody Long id);

    /**
     * 检查考核工作引用
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkCheckWorkCite")
    Result checkCheckWorkCite(@Valid @RequestBody Long id);

    /**
     * 检查考核工作权限引用
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkRightCite")
    Result checkRightCite(@Valid @RequestBody Long id);

    /**
     * 检查考核人员引用
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkUserCite")
    Result checkUserCite(@Valid @RequestBody Long id);

    /**
     * 检查考核分组权限引用
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkGroupCite")
    Result checkGroupCite(@Valid @RequestBody Long id);


    /**
     * 检查考核关系引用
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkRelationCite")
    Result checkRelationCite(@Valid @RequestBody Long id);

    /**
     * 检查考核关系内容引用
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkRelationContentCite")
    Result checkRelationContentCite(@Valid @RequestBody Long id);


    /**
     * 检查考核分组人员引用
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkGroupUserCite")
    Result checkGroupUserCite(@Valid @RequestBody Long id);

    /**
     * 业绩填写通知
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkAchNotifyCite")
    Result checkAchNotifyCite(@Valid @RequestBody Long id);

    /**
     * 个人业绩考核
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkAchUserCite")
    Result checkAchUserCite(@Valid @RequestBody Long id);

    /**
     * 个人业绩考核内容
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkAchUserContentCite")
    Result checkAchUserContentCite(@Valid @RequestBody Long id);

    /**
     * 个人业绩考核审批
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkAchUserApprovalCite")
    Result checkAchUserApprovalCite(@Valid @RequestBody Long id);

    /**
     * 业绩考核测评通知
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkAchEvaluateNotifyCite")
    Result checkAchEvaluateNotifyCite(@Valid @RequestBody Long id);

    /**
     * 个人业绩考核测评通知
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkAchEvaluateUserCite")
    Result  checkAchEvaluateUserCite(@Valid @RequestBody Long id);


    /**
     * 测评内容
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkAchEvaluateContentCite")
    Result  checkAchEvaluateContentCite(@Valid @RequestBody Long id);


    /**
     * 考核结果
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkResultCite")
    Result  checkResultCite(@Valid @RequestBody Long id);

    /**
     * 等级调整历史
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkAdjustHistoryCite")
    Result  checkAdjustHistoryCite(@Valid @RequestBody Long id);

    /**
     * 绩效沟通
     * @param id
     * @return
     */
    @PostMapping(value = path+"/checkAchChatCite")
    Result  checkAchChatCite(@Valid @RequestBody Long id);
}
