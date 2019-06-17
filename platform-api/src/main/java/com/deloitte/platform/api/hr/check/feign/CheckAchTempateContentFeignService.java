package com.deloitte.platform.api.hr.check.feign;


import com.deloitte.platform.api.hr.check.client.CheckAchTempateContentClient;
import com.deloitte.platform.api.hr.check.param.CheckAchTempateContentQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchTempateContentForm;
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
 * @Date : Create in 2019-04-01
 * @Description :   CheckAchTempateContent feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckAchTempateContentFeignService.HystrixCheckAchTempateContentService.class,primary = false)
public interface CheckAchTempateContentFeignService extends CheckAchTempateContentClient {

    @Component
    @Slf4j
    class HystrixCheckAchTempateContentService implements FallbackFactory<CheckAchTempateContentFeignService> {

        @Override
        public CheckAchTempateContentFeignService create(Throwable throwable) {
            return new CheckAchTempateContentFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckAchTempateContentForm checkAchTempateContentForm) {
                    log.error("CheckAchTempateContentService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckAchTempateContentService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckAchTempateContentForm checkAchTempateContentForm) {
                    log.error("CheckAchTempateContentService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckAchTempateContentService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckAchTempateContentQueryForm checkAchTempateContentQueryForm) {
                    log.error("CheckAchTempateContentService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckAchTempateContentQueryForm checkAchTempateContentQueryForm) {
                    log.error("CheckAchTempateContentService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}