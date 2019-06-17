package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.BasicTicketMapClient;
import com.deloitte.platform.api.contract.param.BasicTicketMapQueryForm;
import com.deloitte.platform.api.contract.vo.BasicTicketMapForm;
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
 * @Description :   BasicTicketMap feign客户端
 * @Modified :
 */
@FeignClient(name = "basicTicketMap-service", fallbackFactory = BasicTicketMapFeignService.HystrixBasicTicketMapService.class,primary = false)
public interface BasicTicketMapFeignService extends BasicTicketMapClient {

    @Component
    @Slf4j
    class HystrixBasicTicketMapService implements FallbackFactory<BasicTicketMapFeignService> {

        @Override
        public BasicTicketMapFeignService create(Throwable throwable) {
            return new BasicTicketMapFeignService() {
                @Override
                public Result add(@Valid @RequestBody BasicTicketMapForm basicTicketMapForm) {
                    log.error("BasicTicketMapService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("BasicTicketMapService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody BasicTicketMapForm basicTicketMapForm) {
                    log.error("BasicTicketMapService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("BasicTicketMapService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody BasicTicketMapQueryForm basicTicketMapQueryForm) {
                    log.error("BasicTicketMapService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody BasicTicketMapQueryForm basicTicketMapQueryForm) {
                    log.error("BasicTicketMapService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}