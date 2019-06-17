package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.SysContractNoClient;
import com.deloitte.platform.api.contract.param.SysContractNoQueryForm;
import com.deloitte.platform.api.contract.vo.SysContractNoForm;
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
 * @Description :   SysContractNo feign客户端
 * @Modified :
 */
@FeignClient(name = "sysContractNo-service", fallbackFactory = SysContractNoFeignService.HystrixSysContractNoService.class,primary = false)
public interface SysContractNoFeignService extends SysContractNoClient {

    @Component
    @Slf4j
    class HystrixSysContractNoService implements FallbackFactory<SysContractNoFeignService> {

        @Override
        public SysContractNoFeignService create(Throwable throwable) {
            return new SysContractNoFeignService() {
                @Override
                public Result add(@Valid @RequestBody SysContractNoForm sysContractNoForm) {
                    log.error("SysContractNoService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("SysContractNoService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody SysContractNoForm sysContractNoForm) {
                    log.error("SysContractNoService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("SysContractNoService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody SysContractNoQueryForm sysContractNoQueryForm) {
                    log.error("SysContractNoService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody SysContractNoQueryForm sysContractNoQueryForm) {
                    log.error("SysContractNoService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}