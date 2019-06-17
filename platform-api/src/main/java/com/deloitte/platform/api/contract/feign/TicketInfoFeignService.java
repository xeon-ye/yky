package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.TicketInfoClient;
import com.deloitte.platform.api.contract.param.TicketInfoQueryForm;
import com.deloitte.platform.api.contract.vo.TicketInfoForm;
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
 * @Author : yangyq
 * @Date : Create in 2019-04-16
 * @Description :   TicketInfo feign客户端
 * @Modified :
 */
@FeignClient(name = "ticketInfo-service", fallbackFactory = TicketInfoFeignService.HystrixTicketInfoService.class,primary = false)
public interface TicketInfoFeignService extends TicketInfoClient {

    @Component
    @Slf4j
    class HystrixTicketInfoService implements FallbackFactory<TicketInfoFeignService> {

        @Override
        public TicketInfoFeignService create(Throwable throwable) {
            return new TicketInfoFeignService() {
                @Override
                public Result add(@Valid @RequestBody TicketInfoForm ticketInfoForm) {
                    log.error("TicketInfoService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("TicketInfoService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody TicketInfoForm ticketInfoForm) {
                    log.error("TicketInfoService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("TicketInfoService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody TicketInfoQueryForm ticketInfoQueryForm) {
                    log.error("TicketInfoService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody TicketInfoQueryForm ticketInfoQueryForm) {
                    log.error("TicketInfoService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}