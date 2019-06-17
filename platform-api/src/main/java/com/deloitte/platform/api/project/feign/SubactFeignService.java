package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.SubactClient;
import com.deloitte.platform.api.project.param.SubactQueryForm;
import com.deloitte.platform.api.project.vo.SubactForm;
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
 * @Date : Create in 2019-05-24
 * @Description :   Subact feign客户端
 * @Modified :
 */
@FeignClient(name = "subact-service", fallbackFactory = SubactFeignService.HystrixSubactService.class,primary = false)
public interface SubactFeignService extends SubactClient {

    @Component
    @Slf4j
    class HystrixSubactService implements FallbackFactory<SubactFeignService> {

        @Override
        public SubactFeignService create(Throwable throwable) {
            return new SubactFeignService() {
                @Override
                public Result add(@Valid @RequestBody SubactForm subactForm) {
                    log.error("SubactService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("SubactService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody SubactForm subactForm) {
                    log.error("SubactService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("SubactService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody SubactQueryForm subactQueryForm) {
                    log.error("SubactService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody SubactQueryForm subactQueryForm) {
                    log.error("SubactService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}