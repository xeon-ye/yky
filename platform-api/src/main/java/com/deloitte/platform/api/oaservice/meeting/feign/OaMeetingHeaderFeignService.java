package com.deloitte.platform.api.oaservice.meeting.feign;


import com.deloitte.platform.api.oaservice.meeting.client.OaMeetingHeaderClient;
import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingHeaderQueryForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingHeaderForm;
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
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :   OaMeetingHeader feign客户端
 * @Modified :
 */
@FeignClient(name = "oaMeetingHeader-service", fallbackFactory = OaMeetingHeaderFeignService.HystrixOaMeetingHeaderService.class,primary = false)
public interface OaMeetingHeaderFeignService extends OaMeetingHeaderClient {

    @Component
    @Slf4j
    class HystrixOaMeetingHeaderService implements FallbackFactory<OaMeetingHeaderFeignService> {

        @Override
        public OaMeetingHeaderFeignService create(Throwable throwable) {
            return new OaMeetingHeaderFeignService() {
                @Override
                public Result add(@Valid @RequestBody OaMeetingForm oaMeetingForm) {
                    log.error("OaMeetingHeaderService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OaMeetingHeaderService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OaMeetingForm oaMeetingForm) {
                    log.error("OaMeetingHeaderService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OaMeetingHeaderService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaMeetingHeaderQueryForm oaMeetingHeaderQueryForm) {
                    log.error("OaMeetingHeaderService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaMeetingHeaderQueryForm oaMeetingHeaderQueryForm) {
                    log.error("OaMeetingHeaderService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}