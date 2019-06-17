package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.SysSignSubjectInfoClient;
import com.deloitte.platform.api.contract.param.SysSignSubjectInfoQueryForm;
import com.deloitte.platform.api.contract.vo.SysSignSubjectInfoForm;
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
 * @Date : Create in 2019-04-12
 * @Description :   SysSignSubjectInfo feign客户端
 * @Modified :
 */
@FeignClient(name = "sysSignSubjectInfo-service", fallbackFactory = SysSignSubjectInfoFeignService.HystrixSysSignSubjectInfoService.class,primary = false)
public interface SysSignSubjectInfoFeignService extends SysSignSubjectInfoClient {

    @Component
    @Slf4j
    class HystrixSysSignSubjectInfoService implements FallbackFactory<SysSignSubjectInfoFeignService> {

        @Override
        public SysSignSubjectInfoFeignService create(Throwable throwable) {
            return new SysSignSubjectInfoFeignService() {
                @Override
                public Result add(@Valid @RequestBody SysSignSubjectInfoForm sysSignSubjectInfoForm) {
                    log.error("SysSignSubjectInfoService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("SysSignSubjectInfoService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody SysSignSubjectInfoForm sysSignSubjectInfoForm) {
                    log.error("SysSignSubjectInfoService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("SysSignSubjectInfoService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody SysSignSubjectInfoQueryForm sysSignSubjectInfoQueryForm) {
                    log.error("SysSignSubjectInfoService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody SysSignSubjectInfoQueryForm sysSignSubjectInfoQueryForm) {
                    log.error("SysSignSubjectInfoService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}