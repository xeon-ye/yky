package com.deloitte.platform.api.hr.check.feign;


import com.deloitte.platform.api.hr.check.client.CheckRuleContentClient;
import com.deloitte.platform.api.hr.check.param.CheckRuleContentQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckRuleContentForm;
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
 * @Date : Create in 2019-04-13
 * @Description :   CheckRuleContent feign客户端
 * @Modified :
 */
@FeignClient(name = "checkRuleContent-service", fallbackFactory = CheckRuleContentFeignService.HystrixCheckRuleContentService.class,primary = false)
public interface CheckRuleContentFeignService extends CheckRuleContentClient {

    @Component
    @Slf4j
    class HystrixCheckRuleContentService implements FallbackFactory<CheckRuleContentFeignService> {

        @Override
        public CheckRuleContentFeignService create(Throwable throwable) {
            return new CheckRuleContentFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckRuleContentForm checkRuleContentForm) {
                    log.error("CheckRuleContentService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckRuleContentService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckRuleContentForm checkRuleContentForm) {
                    log.error("CheckRuleContentService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckRuleContentService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckRuleContentQueryForm checkRuleContentQueryForm) {
                    log.error("CheckRuleContentService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckRuleContentQueryForm checkRuleContentQueryForm) {
                    log.error("CheckRuleContentService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}