package com.deloitte.platform.api.oaservice.feign;


import com.deloitte.platform.api.oaservice.client.OaAssistantMappingClient;
import com.deloitte.platform.api.oaservice.param.OaAssistantMappingQueryForm;
import com.deloitte.platform.api.oaservice.vo.OaAssistantMappingForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-03
 * @Description :   OaAssistantMapping feign客户端
 * @Modified :
 */
@FeignClient(name = "service-oa", fallbackFactory = OaAssistantMappingFeignService.HystrixOaAssistantMappingService.class,primary = false)
public interface OaAssistantMappingFeignService extends OaAssistantMappingClient {

    @Component
    @Slf4j
    class HystrixOaAssistantMappingService implements FallbackFactory<OaAssistantMappingFeignService> {

        @Override
        public OaAssistantMappingFeignService create(Throwable throwable) {
            return new OaAssistantMappingFeignService() {
                @Override
                public
                Result save(@Valid @ModelAttribute OaAssistantMappingForm[] oaAssistantMappingForm){
                    log.error("OaAssistantMappingService save服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result add(@Valid @RequestBody OaAssistantMappingForm oaAssistantMappingForm) {
                    log.error("OaAssistantMappingService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable String id) {
                    log.error("OaAssistantMappingService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable String id, @Valid @RequestBody OaAssistantMappingForm oaAssistantMappingForm) {
                    log.error("OaAssistantMappingService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable String id) {
                    log.error("OaAssistantMappingService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaAssistantMappingQueryForm oaAssistantMappingQueryForm) {
                    log.error("OaAssistantMappingService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaAssistantMappingQueryForm oaAssistantMappingQueryForm) {
                    log.error("OaAssistantMappingService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}