package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.SysCommonLanguageClient;
import com.deloitte.platform.api.contract.param.SysCommonLanguageQueryForm;
import com.deloitte.platform.api.contract.vo.SysCommonLanguageForm;
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
 * @Description :   SysCommonLanguage feign客户端
 * @Modified :
 */
@FeignClient(name = "sysCommonLanguage-service", fallbackFactory = SysCommonLanguageFeignService.HystrixSysCommonLanguageService.class,primary = false)
public interface SysCommonLanguageFeignService extends SysCommonLanguageClient {

    @Component
    @Slf4j
    class HystrixSysCommonLanguageService implements FallbackFactory<SysCommonLanguageFeignService> {

        @Override
        public SysCommonLanguageFeignService create(Throwable throwable) {
            return new SysCommonLanguageFeignService() {
                @Override
                public Result add(@Valid @RequestBody SysCommonLanguageForm sysCommonLanguageForm) {
                    log.error("SysCommonLanguageService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("SysCommonLanguageService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody SysCommonLanguageForm sysCommonLanguageForm) {
                    log.error("SysCommonLanguageService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("SysCommonLanguageService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody SysCommonLanguageQueryForm sysCommonLanguageQueryForm) {
                    log.error("SysCommonLanguageService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody SysCommonLanguageQueryForm sysCommonLanguageQueryForm) {
                    log.error("SysCommonLanguageService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}