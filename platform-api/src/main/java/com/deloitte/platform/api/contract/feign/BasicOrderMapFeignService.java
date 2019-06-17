package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.BasicOrderMapClient;
import com.deloitte.platform.api.contract.param.BasicOrderMapQueryForm;
import com.deloitte.platform.api.contract.vo.BasicOrderMapForm;
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
 * @Description :   BasicOrderMap feign客户端
 * @Modified :
 */
@FeignClient(name = "basicOrderMap-service", fallbackFactory = BasicOrderMapFeignService.HystrixBasicOrderMapService.class,primary = false)
public interface BasicOrderMapFeignService extends BasicOrderMapClient {

    @Component
    @Slf4j
    class HystrixBasicOrderMapService implements FallbackFactory<BasicOrderMapFeignService> {

        @Override
        public BasicOrderMapFeignService create(Throwable throwable) {
            return new BasicOrderMapFeignService() {
                @Override
                public Result add(@Valid @RequestBody BasicOrderMapForm basicOrderMapForm) {
                    log.error("BasicOrderMapService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("BasicOrderMapService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody BasicOrderMapForm basicOrderMapForm) {
                    log.error("BasicOrderMapService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("BasicOrderMapService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody BasicOrderMapQueryForm basicOrderMapQueryForm) {
                    log.error("BasicOrderMapService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody BasicOrderMapQueryForm basicOrderMapQueryForm) {
                    log.error("BasicOrderMapService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}