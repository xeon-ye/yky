package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.ProcessTaskClient;
import com.deloitte.platform.api.contract.param.ProcessTaskQueryForm;
import com.deloitte.platform.api.contract.vo.ProcessTaskForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-13
 * @Description :   ProcessTask feign客户端
 * @Modified :
 */
@FeignClient(name = "processTask-service", fallbackFactory = ProcessTaskFeignService.HystrixProcessTaskService.class,primary = false)
public interface ProcessTaskFeignService extends ProcessTaskClient {

    @Component
    @Slf4j
    class HystrixProcessTaskService implements FallbackFactory<ProcessTaskFeignService> {

        @Override
        public ProcessTaskFeignService create(Throwable throwable) {
            return new ProcessTaskFeignService() {
                @Override
                public Result add(@Valid @RequestBody ProcessTaskForm processTaskForm) {
                    log.error("ProcessTaskService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ProcessTaskService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ProcessTaskForm processTaskForm) {
                    log.error("ProcessTaskService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ProcessTaskService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ProcessTaskQueryForm processTaskQueryForm) {
                    log.error("ProcessTaskService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ProcessTaskQueryForm processTaskQueryForm) {
                    log.error("ProcessTaskService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}