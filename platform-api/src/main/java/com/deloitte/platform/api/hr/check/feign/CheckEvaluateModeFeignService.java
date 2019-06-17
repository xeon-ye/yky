package com.deloitte.platform.api.hr.check.feign;


import com.deloitte.platform.api.hr.check.client.CheckEvaluateModeClient;
import com.deloitte.platform.api.hr.check.param.CheckEvaluateModeQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckEvaluateModeForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author : woo
 * @Date : Create in 2019-04-11
 * @Description :   CheckEvaluateMode feign客户端
 * @Modified :
 */
@FeignClient(name = "checkEvaluateMode-service", fallbackFactory = CheckEvaluateModeFeignService.HystrixCheckEvaluateModeService.class,primary = false)
public interface CheckEvaluateModeFeignService extends CheckEvaluateModeClient {

    @Component
    @Slf4j
    class HystrixCheckEvaluateModeService implements FallbackFactory<CheckEvaluateModeFeignService> {

        @Override
        public CheckEvaluateModeFeignService create(Throwable throwable) {
            return new CheckEvaluateModeFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckEvaluateModeForm checkEvaluateModeForm) {
                    log.error("CheckEvaluateModeService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckEvaluateModeService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckEvaluateModeForm checkEvaluateModeForm) {
                    log.error("CheckEvaluateModeService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckEvaluateModeService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckEvaluateModeQueryForm checkEvaluateModeQueryForm) {
                    log.error("CheckEvaluateModeService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckEvaluateModeQueryForm checkEvaluateModeQueryForm) {
                    log.error("CheckEvaluateModeService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}