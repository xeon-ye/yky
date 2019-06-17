package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.BasicAttamentMapClient;
import com.deloitte.platform.api.contract.param.BasicAttamentMapQueryForm;
import com.deloitte.platform.api.contract.vo.BasicAttamentMapForm;
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
 * @Date : Create in 2019-03-29
 * @Description :   BasicAttamentMap feign客户端
 * @Modified :
 */
@FeignClient(name = "basicAttamentMap-service", fallbackFactory = BasicAttamentMapFeignService.HystrixBasicAttamentMapService.class,primary = false)
public interface BasicAttamentMapFeignService extends BasicAttamentMapClient {

    @Component
    @Slf4j
    class HystrixBasicAttamentMapService implements FallbackFactory<BasicAttamentMapFeignService> {

        @Override
        public BasicAttamentMapFeignService create(Throwable throwable) {
            return new BasicAttamentMapFeignService() {
                @Override
                public Result add(@Valid @RequestBody BasicAttamentMapForm basicAttamentMapForm) {
                    log.error("BasicAttamentMapService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("BasicAttamentMapService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody BasicAttamentMapForm basicAttamentMapForm) {
                    log.error("BasicAttamentMapService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("BasicAttamentMapService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody BasicAttamentMapQueryForm basicAttamentMapQueryForm) {
                    log.error("BasicAttamentMapService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody BasicAttamentMapQueryForm basicAttamentMapQueryForm) {
                    log.error("BasicAttamentMapService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}