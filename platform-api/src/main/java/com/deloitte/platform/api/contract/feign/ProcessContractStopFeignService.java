package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.ProcessContractStopClient;
import com.deloitte.platform.api.contract.param.ProcessContractStopQueryForm;
import com.deloitte.platform.api.contract.vo.ProcessContractStopForm;
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
 * @Date : Create in 2019-03-26
 * @Description :   ProcessContractStop feign客户端
 * @Modified :
 */
@FeignClient(name = "processContractStop-service", fallbackFactory = ProcessContractStopFeignService.HystrixProcessContractStopService.class,primary = false)
public interface ProcessContractStopFeignService extends ProcessContractStopClient {

    @Component
    @Slf4j
    class HystrixProcessContractStopService implements FallbackFactory<ProcessContractStopFeignService> {

        @Override
        public ProcessContractStopFeignService create(Throwable throwable) {
            return new ProcessContractStopFeignService() {
                @Override
                public Result add(@Valid @RequestBody ProcessContractStopForm processContractStopForm) {
                    log.error("ProcessContractStopService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ProcessContractStopService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ProcessContractStopForm processContractStopForm) {
                    log.error("ProcessContractStopService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ProcessContractStopService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ProcessContractStopQueryForm processContractStopQueryForm) {
                    log.error("ProcessContractStopService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ProcessContractStopQueryForm processContractStopQueryForm) {
                    log.error("ProcessContractStopService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}