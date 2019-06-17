package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.SysWatermarkClient;
import com.deloitte.platform.api.contract.param.SysWatermarkQueryForm;
import com.deloitte.platform.api.contract.vo.SysWatermarkForm;
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
 * @Description :   SysWatermark feign客户端
 * @Modified :
 */
@FeignClient(name = "sysWatermark-service", fallbackFactory = SysWatermarkFeignService.HystrixSysWatermarkService.class,primary = false)
public interface SysWatermarkFeignService extends SysWatermarkClient {

    @Component
    @Slf4j
    class HystrixSysWatermarkService implements FallbackFactory<SysWatermarkFeignService> {

        @Override
        public SysWatermarkFeignService create(Throwable throwable) {
            return new SysWatermarkFeignService() {
                @Override
                public Result add(@Valid @RequestBody SysWatermarkForm sysWatermarkForm) {
                    log.error("SysWatermarkService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("SysWatermarkService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody SysWatermarkForm sysWatermarkForm) {
                    log.error("SysWatermarkService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("SysWatermarkService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody SysWatermarkQueryForm sysWatermarkQueryForm) {
                    log.error("SysWatermarkService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody SysWatermarkQueryForm sysWatermarkQueryForm) {
                    log.error("SysWatermarkService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}