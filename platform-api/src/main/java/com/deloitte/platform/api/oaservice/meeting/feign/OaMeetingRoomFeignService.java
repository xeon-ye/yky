package com.deloitte.platform.api.oaservice.meeting.feign;


import com.deloitte.platform.api.oaservice.meeting.client.OaMeetingRoomClient;
import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingRoomQueryForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingRoomForm;
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
 * @Description :   OaMeetingRoom feign客户端
 * @Modified :
 */
@FeignClient(name = "oaMeetingRoom-service", fallbackFactory = OaMeetingRoomFeignService.HystrixOaMeetingRoomService.class,primary = false)
public interface OaMeetingRoomFeignService extends OaMeetingRoomClient {

    @Component
    @Slf4j
    class HystrixOaMeetingRoomService implements FallbackFactory<OaMeetingRoomFeignService> {

        @Override
        public OaMeetingRoomFeignService create(Throwable throwable) {
            return new OaMeetingRoomFeignService() {
                @Override
                public Result add(@Valid @RequestBody OaMeetingRoomForm oaMeetingRoomForm) {
                    log.error("OaMeetingRoomService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OaMeetingRoomService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OaMeetingRoomForm oaMeetingRoomForm) {
                    log.error("OaMeetingRoomService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OaMeetingRoomService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaMeetingRoomQueryForm oaMeetingRoomQueryForm) {
                    log.error("OaMeetingRoomService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaMeetingRoomQueryForm oaMeetingRoomQueryForm) {
                    log.error("OaMeetingRoomService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}