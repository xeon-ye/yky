package com.deloitte.platform.api.oaservice.meeting.feign;


import com.deloitte.platform.api.oaservice.meeting.client.OaMeetingAddressClient;
import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingAddressQueryForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingAddressForm;
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
 * @Description :   OaMeetingAddress feign客户端
 * @Modified :
 */
@FeignClient(name = "oaMeetingAddress-service", fallbackFactory = OaMeetingAddressFeignService.HystrixOaMeetingAddressService.class,primary = false)
public interface OaMeetingAddressFeignService extends OaMeetingAddressClient {

    @Component
    @Slf4j
    class HystrixOaMeetingAddressService implements FallbackFactory<OaMeetingAddressFeignService> {

        @Override
        public OaMeetingAddressFeignService create(Throwable throwable) {
            return new OaMeetingAddressFeignService() {
                @Override
                public Result add(@Valid @RequestBody OaMeetingAddressForm oaMeetingAddressForm) {
                    log.error("OaMeetingAddressService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OaMeetingAddressService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OaMeetingAddressForm oaMeetingAddressForm) {
                    log.error("OaMeetingAddressService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OaMeetingAddressService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaMeetingAddressQueryForm oaMeetingAddressQueryForm) {
                    log.error("OaMeetingAddressService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaMeetingAddressQueryForm oaMeetingAddressQueryForm) {
                    log.error("OaMeetingAddressService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}