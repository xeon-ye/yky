package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.ProcessOperatorTransferClient;
import com.deloitte.platform.api.contract.param.ProcessOperatorTransferQueryForm;
import com.deloitte.platform.api.contract.vo.ProcessOperatorTransferForm;
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
 * @Author : hemingzheng
 * @Date : Create in 2019-04-16
 * @Description :   ProcessOperatorTransfer feign客户端
 * @Modified :
 */
@FeignClient(name = "processOperatorTransfer-service", fallbackFactory = ProcessOperatorTransferFeignService.HystrixProcessOperatorTransferService.class,primary = false)
public interface ProcessOperatorTransferFeignService extends ProcessOperatorTransferClient {

    @Component
    @Slf4j
    class HystrixProcessOperatorTransferService implements FallbackFactory<ProcessOperatorTransferFeignService> {

        @Override
        public ProcessOperatorTransferFeignService create(Throwable throwable) {
            return new ProcessOperatorTransferFeignService() {
                @Override
                public Result add(@Valid @RequestBody ProcessOperatorTransferForm processOperatorTransferForm) {
                    log.error("ProcessOperatorTransferService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ProcessOperatorTransferService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ProcessOperatorTransferForm processOperatorTransferForm) {
                    log.error("ProcessOperatorTransferService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ProcessOperatorTransferService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ProcessOperatorTransferQueryForm processOperatorTransferQueryForm) {
                    log.error("ProcessOperatorTransferService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ProcessOperatorTransferQueryForm processOperatorTransferQueryForm) {
                    log.error("ProcessOperatorTransferService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}