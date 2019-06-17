package com.deloitte.platform.api.hr.common.feign;


import com.deloitte.platform.api.hr.common.client.AttachmentClient;
import com.deloitte.platform.api.hr.common.param.HrAttachmentQueryForm;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
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
 * @Author : jianglong
 * @Date : Create in 2019-04-04
 * @Description :   Attachment feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = HrAttachmentFeignService.HystrixHrAttachmentService.class,primary = false)
public interface HrAttachmentFeignService extends AttachmentClient {

    @Component
    @Slf4j
    class HystrixHrAttachmentService implements FallbackFactory<HrAttachmentFeignService> {

        @Override
        public HrAttachmentFeignService create(Throwable throwable) {
            return new HrAttachmentFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrAttachmentForm hrAttachmentForm) {
                    log.error("AttachmentService add服务不可用......");
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
                public Result update(@PathVariable long id, @Valid @RequestBody HrAttachmentForm hrAttachmentForm) {
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
                public Result list(@Valid @RequestBody HrAttachmentQueryForm hrAttachmentQueryForm) {
                    log.error("AttachmentService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrAttachmentQueryForm hrAttachmentQueryForm) {
                    log.error("AttachmentService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}