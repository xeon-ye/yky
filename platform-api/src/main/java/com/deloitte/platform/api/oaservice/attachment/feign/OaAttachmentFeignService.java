package com.deloitte.platform.api.oaservice.attachment.feign;

import com.deloitte.platform.api.oaservice.attachment.client.OaAttachmentClient;
import com.deloitte.platform.api.oaservice.attachment.param.OaAttachmentQueryForm;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentForm;
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
 * @Author : jianghaixun
 * @Date : Create in 2019-04-15
 * @Description :   OaAttachment feign客户端
 * @Modified :
 */
@FeignClient(name = "oaAttachment-service", fallbackFactory = OaAttachmentFeignService.HystrixOaAttachmentService.class,primary = false)
public interface OaAttachmentFeignService extends OaAttachmentClient {

    @Component
    @Slf4j
    class HystrixOaAttachmentService implements FallbackFactory<OaAttachmentFeignService> {

        @Override
        public OaAttachmentFeignService create(Throwable throwable) {
            return new OaAttachmentFeignService() {
                @Override
                public Result add(@Valid @RequestBody OaAttachmentForm oaAttachmentForm) {
                    log.error("OaAttachmentService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OaAttachmentService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OaAttachmentForm oaAttachmentForm) {
                    log.error("OaAttachmentService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OaAttachmentService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaAttachmentQueryForm oaAttachmentQueryForm) {
                    log.error("OaAttachmentService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaAttachmentQueryForm oaAttachmentQueryForm) {
                    log.error("OaAttachmentService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}