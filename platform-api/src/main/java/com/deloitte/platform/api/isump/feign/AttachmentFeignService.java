package com.deloitte.platform.api.isump.feign;


import com.deloitte.platform.api.isump.AttachmentClient;
import com.deloitte.platform.api.isump.param.AttachmentQueryForm;
import com.deloitte.platform.api.isump.vo.AttachmentForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-04
 * @Description :   Attachment feign客户端
 * @Modified :
 */
@FeignClient(name = "attachment-service", fallbackFactory = AttachmentFeignService.HystrixAttachmentService.class,primary = false)
public interface AttachmentFeignService extends AttachmentClient {

    @Component
    @Slf4j
    class HystrixAttachmentService implements FallbackFactory<AttachmentFeignService> {

        @Override
        public AttachmentFeignService create(Throwable throwable) {
            return new AttachmentFeignService() {
                @Override
                public Result add(@Valid @RequestBody AttachmentForm attachmentForm) {
                    log.error("AttachmentService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchAdd(@Valid @RequestBody List<AttachmentForm> attachmentForms) {
                    log.error("AttachmentService batchAdd服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("AttachmentService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody AttachmentForm attachmentForm) {
                    log.error("AttachmentService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("AttachmentService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody AttachmentQueryForm attachmentQueryForm) {
                    log.error("AttachmentService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody AttachmentQueryForm attachmentQueryForm) {
                    log.error("AttachmentService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}