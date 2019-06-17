package com.deloitte.platform.api.oaservice.applycenter.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.applycenter.client.ApplyCenterClient;
import com.deloitte.platform.api.oaservice.applycenter.param.ApplyCenterQueryForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.ApplyCenterForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.ApplyCenterVo;
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
 * @Author : yidaojun
 * @Date : Create in 2019-04-12
 * @Description :   ApplyCenter feign客户端
 * @Modified :
 */
@FeignClient(name = "applyCenter-service", fallbackFactory = ApplyCenterFeignService.HystrixApplyCenterService.class, primary = false)
public interface ApplyCenterFeignService extends ApplyCenterClient {

    @Component
    @Slf4j
    class HystrixApplyCenterService implements FallbackFactory<ApplyCenterFeignService> {

        @Override
        public ApplyCenterFeignService create(Throwable throwable) {
            return new ApplyCenterFeignService() {
                @Override
                public Result add(@Valid @RequestBody ApplyCenterForm applyCenterForm) {
                    log.error("ApplyCenterService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable String id) {
                    log.error("ApplyCenterService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable String id, @Valid @RequestBody ApplyCenterForm applyCenterForm) {
                    log.error("ApplyCenterService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable String id) {
                    log.error("ApplyCenterService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ApplyCenterQueryForm applyCenterQueryForm) {
                    log.error("ApplyCenterService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result<IPage<ApplyCenterVo>> search(@Valid @RequestBody ApplyCenterQueryForm applyCenterQueryForm) {
                    log.error("ApplyCenterService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result<IPage<ApplyCenterVo>> searchPage(ApplyCenterQueryForm applyCenterQueryForm) {
                    log.error("ApplyCenterService searchPage服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}