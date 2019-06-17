package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.BasicFinanceMapClient;
import com.deloitte.platform.api.contract.param.BasicFinanceMapQueryForm;
import com.deloitte.platform.api.contract.vo.BasicFinanceMapForm;
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
 * @Description :   BasicFinanceMap feign客户端
 * @Modified :
 */
@FeignClient(name = "basicFinanceMap-service", fallbackFactory = BasicFinanceMapFeignService.HystrixBasicFinanceMapService.class,primary = false)
public interface BasicFinanceMapFeignService extends BasicFinanceMapClient {

    @Component
    @Slf4j
    class HystrixBasicFinanceMapService implements FallbackFactory<BasicFinanceMapFeignService> {

        @Override
        public BasicFinanceMapFeignService create(Throwable throwable) {
            return new BasicFinanceMapFeignService() {
                @Override
                public Result add(@Valid @RequestBody BasicFinanceMapForm basicFinanceMapForm) {
                    log.error("BasicFinanceMapService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("BasicFinanceMapService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody BasicFinanceMapForm basicFinanceMapForm) {
                    log.error("BasicFinanceMapService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("BasicFinanceMapService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody BasicFinanceMapQueryForm basicFinanceMapQueryForm) {
                    log.error("BasicFinanceMapService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody BasicFinanceMapQueryForm basicFinanceMapQueryForm) {
                    log.error("BasicFinanceMapService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}