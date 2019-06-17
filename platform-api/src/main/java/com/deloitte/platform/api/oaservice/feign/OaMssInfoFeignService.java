package com.deloitte.platform.api.oaservice.feign;

import com.deloitte.platform.api.oaservice.client.OaMssInfoClient;
import com.deloitte.platform.api.oaservice.vo.OaMssInfoForm;
import com.deloitte.platform.api.push.form.OaMssSendInfoForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "service-oa", fallbackFactory = OaMssInfoFeignService.HystrixOaAssistantMappingService.class,primary = false)
public interface OaMssInfoFeignService extends OaMssInfoClient {

    @Component
    @Slf4j
    class HystrixOaAssistantMappingService implements FallbackFactory<OaMssInfoFeignService> {
        @Override
        public OaMssInfoFeignService create(Throwable throwable) {
            return new OaMssInfoFeignService() {
                @Override
                public Result saveOaMssInfo(@Valid @RequestBody OaMssInfoForm oaMssInfo) {
                    log.error("OaAssistantMappingService save服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result saveOaMssSendInfo(@Valid @RequestBody OaMssSendInfoForm oaMssInfo) {
                    log.error("OaAssistantMappingService save服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }

}
