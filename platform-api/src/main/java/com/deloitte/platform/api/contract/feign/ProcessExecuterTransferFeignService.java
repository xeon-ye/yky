package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.ProcessExecuterTransferClient;
import com.deloitte.platform.api.contract.param.ProcessExecuterTransferQueryForm;
import com.deloitte.platform.api.contract.vo.ProcessExecuterTransferForm;
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
 * @Description :   ProcessExecuterTransfer feign客户端
 * @Modified :
 */
@FeignClient(name = "processExecuterTransfer-service", fallbackFactory = ProcessExecuterTransferFeignService.HystrixProcessExecuterTransferService.class,primary = false)
public interface ProcessExecuterTransferFeignService extends ProcessExecuterTransferClient {

    @Component
    @Slf4j
    class HystrixProcessExecuterTransferService implements FallbackFactory<ProcessExecuterTransferFeignService> {

        @Override
        public ProcessExecuterTransferFeignService create(Throwable throwable) {
            return new ProcessExecuterTransferFeignService() {
                @Override
                public Result add(@Valid @RequestBody ProcessExecuterTransferForm processExecuterTransferForm) {
                    log.error("ProcessExecuterTransferService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ProcessExecuterTransferService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ProcessExecuterTransferForm processExecuterTransferForm) {
                    log.error("ProcessExecuterTransferService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ProcessExecuterTransferService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ProcessExecuterTransferQueryForm processExecuterTransferQueryForm) {
                    log.error("ProcessExecuterTransferService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ProcessExecuterTransferQueryForm processExecuterTransferQueryForm) {
                    log.error("ProcessExecuterTransferService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}