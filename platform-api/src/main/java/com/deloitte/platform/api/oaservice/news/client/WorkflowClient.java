package com.deloitte.platform.api.oaservice.news.client;

import com.deloitte.platform.api.oaservice.news.vo.NewsForm;
import com.deloitte.platform.api.oaservice.news.vo.NewsProcessForm;
import com.deloitte.platform.api.oaservice.news.vo.RollBackProcessVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-12
 * @Description :  新闻发布控制器接口
 * @Modified :
 */
public interface WorkflowClient {

    public static final String path = "/oaservice/process";

    /**
     * 新闻审批流程提交
     *
     * @param newsForm
     * @return
     */
    @PostMapping(value = path + "/submit")
    Result submitProcess(@Valid @RequestBody NewsForm newsForm, @RequestHeader(value = "token") String token);

    /**
     * 新闻审批流程审批
     *
     * @param newsProcessForm
     * @return
     */
    @PostMapping(value = path + "/approval")
    Result approval(@Valid @RequestBody NewsProcessForm newsProcessForm, @RequestHeader(value = "token") String token);

    /**
     * 新闻审批流程撤回
     *
     * @param rollBackProcessVo
     * @return
     */
    @PostMapping(value = path + "/rollBackProcess")
    Result rollBackProcess(@Valid @RequestBody RollBackProcessVo rollBackProcessVo);

    /**
     * 获取下一节点审批人
     * @param processVariables
     * @return
     */
    @PostMapping(value = path + "/getNextMatrix")
    Result getNextMatrix(@RequestParam Map<String, String> processVariables);

    /**
     * 查询流程状态
     * @param
     * @return
     */
    @GetMapping(value = path + "/status")
    Result processStatus(@RequestParam(value="processInstanceId") String processInstanceId, @RequestParam(value = "sourceSystem") String sourceSystem);

    @GetMapping(value = path + "/historyList")
    Result getHistoryList(@RequestParam(value="processInstanceId") String processInstanceId, @RequestParam(value = "taskKey") String taskKey);


}
