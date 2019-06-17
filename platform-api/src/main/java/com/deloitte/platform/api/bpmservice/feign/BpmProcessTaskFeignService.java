package com.deloitte.platform.api.bpmservice.feign;


import com.deloitte.platform.api.bpmservice.clinet.BpmProcessTaskClient;
import com.deloitte.platform.api.bpmservice.param.BpmConductListQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryWrapper;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessImgInfoVo;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskForm;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.GdcPage;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 2019-02-28
 * @Description :   BpmProcessTask feign客户端
 * @Modified :
 */
@FeignClient(name = "bpm-service", fallbackFactory = BpmProcessTaskFeignService.HystrixBpmProcessTaskService.class,primary = false)
public interface BpmProcessTaskFeignService extends BpmProcessTaskClient {

    @Component
    @Slf4j
    class HystrixBpmProcessTaskService implements FallbackFactory<BpmProcessTaskFeignService> {
        @Override
        public BpmProcessTaskFeignService create(Throwable throwable) {
            return new BpmProcessTaskFeignService() {

                @Override
                public Result add(@Valid @RequestBody BpmProcessTaskForm bpmProcessTaskForm) {
                    log.error("BpmProcessTaskService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("BpmProcessTaskService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody BpmProcessTaskForm bpmProcessTaskForm) {
                    log.error("BpmProcessTaskService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result updateByProcessInsIdAndTaskId(@Valid @RequestBody BpmProcessTaskForm bpmProcessTaskForm){
                    log.error("BpmProcessTaskService updateByProcessInsIdAndTaskId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result updateHistoryByProcessInsIdAndTaskId(@Valid @RequestBody BpmProcessTaskForm bpmProcessTaskForm){
                    log.error("BpmProcessTaskService updateHistoryByProcessInsIdAndTaskId 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }


                @Override
                public Result get(@PathVariable long id) {
                    log.error("BpmProcessTaskService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody BpmProcessTaskQueryForm bpmProcessTaskQueryForm) {
                    log.error("BpmProcessTaskService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody BpmProcessTaskQueryForm bpmProcessTaskQueryForm) {
                    log.error("BpmProcessTaskService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
                @Override
                public Result<GdcPage<BpmProcessTaskVo>> searchByWrapper(@Valid @RequestBody BpmProcessTaskQueryWrapper queryWrapper){
                    log.error("BpmProcessTaskService searchByWrapper服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result<GdcPage<BpmProcessTaskVo>> searchBackLog(BpmConductListQueryForm bpmConductListQueryForm) {
                    log.error("BpmProcessTaskService searchBackLog服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result<GdcPage<BpmProcessTaskVo>> searchDone( BpmConductListQueryForm bpmConductListQueryForm) {
                    log.error("BpmProcessTaskService searchDone服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());

                }

                @Override
                public Result<HashMap<String, Object>>  getProcessVariables(@PathVariable String processInstanceId){
                    log.error("BpmProcessTaskService getProcessVariables 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public void getFlowImgByInstantId(@PathVariable(value = "processInstanceId") String processInstanceId, HttpServletResponse response){
                    log.error("BpmProcessTaskService getFlowImgByInstantId 服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result getFlowImgInfoByInstantId(@PathVariable(value = "processInstanceId") String processInstanceId){
                    log.error("BpmProcessTaskService getFlowImgInfoByInstantId 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result workflowEnd(@RequestParam(name = "processInstanceId") String processInstanceId){
                    log.error("BpmProcessTaskService workflowEnd 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public  Result taskIsEnd(@RequestParam(name = "processInstanceId") String processInstanceId,@RequestParam(name = "taskId") String taskId){
                    log.error("BpmProcessTaskService taskIsEnd 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}