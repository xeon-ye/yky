package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.AppAttachmentClient;
import com.deloitte.platform.api.project.param.AppAttachmentQueryForm;
import com.deloitte.platform.api.project.vo.AppAttachmentForm;
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
 * @Date : Create in 2019-04-24
 * @Description :   AppAttachment feign客户端
 * @Modified :
 */
@FeignClient(name = "appAttachment-service", fallbackFactory = AppAttachmentFeignService.HystrixAppAttachmentService.class,primary = false)
public interface AppAttachmentFeignService extends AppAttachmentClient {

    @Component
    @Slf4j
    class HystrixAppAttachmentService implements FallbackFactory<AppAttachmentFeignService> {

        @Override
        public AppAttachmentFeignService create(Throwable throwable) {
            return new AppAttachmentFeignService() {
                @Override
                public Result add(@Valid @RequestBody AppAttachmentForm appAttachmentForm) {
                    log.error("AppAttachmentService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("AppAttachmentService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody AppAttachmentForm appAttachmentForm) {
                    log.error("AppAttachmentService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("AppAttachmentService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody AppAttachmentQueryForm appAttachmentQueryForm) {
                    log.error("AppAttachmentService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody AppAttachmentQueryForm appAttachmentQueryForm) {
                    log.error("AppAttachmentService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}