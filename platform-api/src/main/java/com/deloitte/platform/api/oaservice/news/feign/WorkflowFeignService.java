package com.deloitte.platform.api.oaservice.news.feign;

import com.deloitte.platform.api.oaservice.news.client.WorkflowClient;
import com.deloitte.platform.api.oaservice.news.vo.NewsForm;
import com.deloitte.platform.api.oaservice.news.vo.NewsProcessForm;
import com.deloitte.platform.api.oaservice.news.vo.RollBackProcessVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@FeignClient(name = "service-oa", fallbackFactory = WorkflowFeignService.HystrixWorkflowService.class, primary = false)
public interface WorkflowFeignService extends WorkflowClient {

    @Component
    @Slf4j
    class HystrixWorkflowService implements FallbackFactory<WorkflowFeignService> {
        @Override
        public WorkflowFeignService create(Throwable throwable) {
            return new WorkflowFeignService() {
                @Override
                public Result submitProcess(@Valid @RequestBody NewsForm newsForm, @RequestHeader(value = "token") String token) {
                    log.error("WorkflowService submitProcess服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result approval(@Valid @RequestBody NewsProcessForm newsProcessForm, @RequestHeader(value = "token") String token) {
                    log.error("WorkflowService approval服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result rollBackProcess(@Valid @RequestBody RollBackProcessVo rollBackProcessVo) {
                    log.error("WorkflowService rollBackProcess服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result getNextMatrix(@RequestParam Map<String, String> processVariables) {
                    log.error("WorkflowService processStatus服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result processStatus(@RequestParam(value="processInstanceId") String processInstanceId, @RequestParam(value = "sourceSystem") String sourceSystem) {
                    log.error("WorkflowService processStatus服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result getHistoryList(String processInstanceId, String taskKey) {
                    log.error("WorkflowService getHistoryList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}
