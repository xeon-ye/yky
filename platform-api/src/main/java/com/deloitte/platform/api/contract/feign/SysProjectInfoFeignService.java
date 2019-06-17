package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.SysProjectInfoClient;
import com.deloitte.platform.api.contract.param.SysProjectInfoQueryForm;
import com.deloitte.platform.api.contract.vo.SysProjectInfoForm;
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
 * @Description :   SysProjectInfo feign客户端
 * @Modified :
 */
@FeignClient(name = "sysProjectInfo-service", fallbackFactory = SysProjectInfoFeignService.HystrixSysProjectInfoService.class,primary = false)
public interface SysProjectInfoFeignService extends SysProjectInfoClient {

    @Component
    @Slf4j
    class HystrixSysProjectInfoService implements FallbackFactory<SysProjectInfoFeignService> {

        @Override
        public SysProjectInfoFeignService create(Throwable throwable) {
            return new SysProjectInfoFeignService() {
                @Override
                public Result add(@Valid @RequestBody SysProjectInfoForm sysProjectInfoForm) {
                    log.error("SysProjectInfoService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("SysProjectInfoService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody SysProjectInfoForm sysProjectInfoForm) {
                    log.error("SysProjectInfoService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("SysProjectInfoService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody SysProjectInfoQueryForm sysProjectInfoQueryForm) {
                    log.error("SysProjectInfoService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody SysProjectInfoQueryForm sysProjectInfoQueryForm) {
                    log.error("SysProjectInfoService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}