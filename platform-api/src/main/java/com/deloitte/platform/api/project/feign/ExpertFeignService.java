package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ExpertClient;
import com.deloitte.platform.api.project.param.ExpertQueryForm;
import com.deloitte.platform.api.project.vo.ExpertForm;
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
 * @Date : Create in 2019-05-16
 * @Description :   Expert feign客户端
 * @Modified :
 */
@FeignClient(name = "expert-service", fallbackFactory = ExpertFeignService.HystrixExpertService.class,primary = false)
public interface ExpertFeignService extends ExpertClient {

    @Component
    @Slf4j
    class HystrixExpertService implements FallbackFactory<ExpertFeignService> {

        @Override
        public ExpertFeignService create(Throwable throwable) {
            return new ExpertFeignService() {
                @Override
                public Result add(@Valid @RequestBody ExpertForm expertForm) {
                    log.error("ExpertService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ExpertService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ExpertForm expertForm) {
                    log.error("ExpertService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ExpertService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ExpertQueryForm expertQueryForm) {
                    log.error("ExpertService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ExpertQueryForm expertQueryForm) {
                    log.error("ExpertService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}