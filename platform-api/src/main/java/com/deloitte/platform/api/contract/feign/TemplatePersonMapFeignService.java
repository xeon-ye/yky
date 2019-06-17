package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.TemplatePersonMapClient;
import com.deloitte.platform.api.contract.param.TemplatePersonMapQueryForm;
import com.deloitte.platform.api.contract.vo.TemplatePersonMapForm;
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
 * @Author : yangyq
 * @Date : Create in 2019-04-24
 * @Description :   TemplatePersonMap feign客户端
 * @Modified :
 */
@FeignClient(name = "templatePersonMap-service", fallbackFactory = TemplatePersonMapFeignService.HystrixTemplatePersonMapService.class,primary = false)
public interface TemplatePersonMapFeignService extends TemplatePersonMapClient {

    @Component
    @Slf4j
    class HystrixTemplatePersonMapService implements FallbackFactory<TemplatePersonMapFeignService> {

        @Override
        public TemplatePersonMapFeignService create(Throwable throwable) {
            return new TemplatePersonMapFeignService() {
                @Override
                public Result add(@Valid @RequestBody TemplatePersonMapForm templatePersonMapForm) {
                    log.error("TemplatePersonMapService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("TemplatePersonMapService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody TemplatePersonMapForm templatePersonMapForm) {
                    log.error("TemplatePersonMapService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("TemplatePersonMapService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody TemplatePersonMapQueryForm templatePersonMapQueryForm) {
                    log.error("TemplatePersonMapService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody TemplatePersonMapQueryForm templatePersonMapQueryForm) {
                    log.error("TemplatePersonMapService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}