package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.StandardTemplateClient;
import com.deloitte.platform.api.contract.param.StandardTemplateQueryForm;
import com.deloitte.platform.api.contract.vo.StandardTemplateForm;
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
 * @Date : Create in 2019-04-25
 * @Description :   StandardTemplate feign客户端
 * @Modified :
 */
@FeignClient(name = "standardTemplate-service", fallbackFactory = StandardTemplateFeignService.HystrixStandardTemplateService.class,primary = false)
public interface StandardTemplateFeignService extends StandardTemplateClient {

    @Component
    @Slf4j
    class HystrixStandardTemplateService implements FallbackFactory<StandardTemplateFeignService> {

        @Override
        public StandardTemplateFeignService create(Throwable throwable) {
            return new StandardTemplateFeignService() {
                @Override
                public Result add(@Valid @RequestBody StandardTemplateForm standardTemplateForm) {
                    log.error("StandardTemplateService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("StandardTemplateService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody StandardTemplateForm standardTemplateForm) {
                    log.error("StandardTemplateService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("StandardTemplateService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody StandardTemplateQueryForm standardTemplateQueryForm) {
                    log.error("StandardTemplateService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody StandardTemplateQueryForm standardTemplateQueryForm) {
                    log.error("StandardTemplateService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}