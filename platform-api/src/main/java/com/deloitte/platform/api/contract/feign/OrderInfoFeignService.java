package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.OrderInfoClient;
import com.deloitte.platform.api.contract.param.OrderInfoQueryForm;
import com.deloitte.platform.api.contract.vo.OrderInfoForm;
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
 * @Date : Create in 2019-03-26
 * @Description :   OrderInfo feign客户端
 * @Modified :
 */
@FeignClient(name = "orderInfo-service", fallbackFactory = OrderInfoFeignService.HystrixOrderInfoService.class,primary = false)
public interface OrderInfoFeignService extends OrderInfoClient {

    @Component
    @Slf4j
    class HystrixOrderInfoService implements FallbackFactory<OrderInfoFeignService> {

        @Override
        public OrderInfoFeignService create(Throwable throwable) {
            return new OrderInfoFeignService() {
                @Override
                public Result add(@Valid @RequestBody OrderInfoForm orderInfoForm) {
                    log.error("OrderInfoService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OrderInfoService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OrderInfoForm orderInfoForm) {
                    log.error("OrderInfoService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OrderInfoService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OrderInfoQueryForm orderInfoQueryForm) {
                    log.error("OrderInfoService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OrderInfoQueryForm orderInfoQueryForm) {
                    log.error("OrderInfoService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}