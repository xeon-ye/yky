package com.deloitte.platform.api.hr.check.feign;

import com.deloitte.platform.api.hr.check.client.CheckTemplateClient;
import com.deloitte.platform.api.hr.check.param.CheckTemplateQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckTemplateForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;


/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :   CheckTemplate feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckTemplateFeignService.HystrixCheckTemplateService.class,primary = false)
public interface CheckTemplateFeignService extends CheckTemplateClient {

    @Component
    @Slf4j
    class HystrixCheckTemplateService implements FallbackFactory<CheckTemplateFeignService> {

        @Override
        public CheckTemplateFeignService create(Throwable throwable) {
            return new CheckTemplateFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckTemplateForm checkTemplateForm) {
                    log.error("CheckTemplateService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckTemplateService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckTemplateForm checkTemplateForm) {
                    log.error("CheckTemplateService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckTemplateService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckTemplateQueryForm checkTemplateQueryForm) {
                    log.error("CheckTemplateService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckTemplateQueryForm checkTemplateQueryForm) {
                    log.error("CheckTemplateService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchDelete(@Valid @RequestBody List<String> ids) {
                    log.error("CheckTemplateService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}