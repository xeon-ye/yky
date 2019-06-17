package com.deloitte.platform.api.oaservice.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.param.BpmConductListQueryForm;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.client.OaWorkflowClient;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@FeignClient(name = "service-oa", fallbackFactory = OaWorkflowFeignService.HystrixOaScheduleTableService.class,primary = false)
public interface OaWorkflowFeignService extends OaWorkflowClient {

    @Component
    @Slf4j
    class HystrixOaScheduleTableService implements FallbackFactory<OaWorkflowFeignService> {
        @Override
        public OaWorkflowFeignService create(Throwable throwable) {
            return new OaWorkflowFeignService() {
                @Override
                public Result<IPage<BpmProcessTaskVo>> queryUserBackLog(@RequestBody BpmConductListQueryForm queryForm) {
                    log.error("OaWorkflowFeignService queryUserBackLog服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result<IPage<BpmProcessTaskVo>> queryUserDoneList(@RequestBody BpmConductListQueryForm queryForm) {
                    log.error("OaWorkflowFeignService queryUserDoneList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result<List<BpmProcessTaskVo>> queryAuditHistory(@PathVariable(value = "processId") String processId) {
                    log.error("OaWorkflowFeignService queryAuditHistory服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result workflowDriving(@RequestParam(name="businessId")String businessId, @RequestParam(name="bizType")String bizType){
                    log.error("OaScheduleTableService workflowDriving服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<BpmProcessTaskVo>> validationTaskEnd(@PathVariable(value = "id") long id) {
                    log.error("OaWorkflowFeignService validataTaskEnd服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result getNextNodeParamVo(@RequestParam(name="bizType")String bizType,@RequestParam(name="chargeOrg")String chargeOrg,@RequestParam(name="processDefineKey")String processDefineKey,@RequestParam(name="taskId")String taskId,@RequestParam(name="url")String url,@RequestParam(name="processVariables")String processVariables){
                    log.error("OaWorkflowFeignService getNextNodeParamVo服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result signTask(@PathVariable(value="id") long id){
                    log.error("OaWorkflowFeignService signTask服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getFlowImgInfoByInstantId(@PathVariable(value = "processInstanceId") String processInstanceId){
                    log.error("OaWorkflowFeignService getFlowImgInfoByInstantId 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}
