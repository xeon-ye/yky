package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.BasicConfiguraClient;
import com.deloitte.platform.api.contract.param.BasicConfiguraQueryForm;
import com.deloitte.platform.api.contract.vo.BasicConfiguraForm;
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
 * @Date : Create in 2019-04-23
 * @Description :   BasicConfigura feign客户端
 * @Modified :
 */
@FeignClient(name = "basicConfigura-service", fallbackFactory = BasicConfiguraFeignService.HystrixBasicConfiguraService.class,primary = false)
public interface BasicConfiguraFeignService extends BasicConfiguraClient {

    @Component
    @Slf4j
    class HystrixBasicConfiguraService implements FallbackFactory<BasicConfiguraFeignService> {

        @Override
        public BasicConfiguraFeignService create(Throwable throwable) {
            return new BasicConfiguraFeignService() {
                @Override
                public Result add(@Valid @RequestBody BasicConfiguraForm basicConfiguraForm) {
                    log.error("BasicConfiguraService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("BasicConfiguraService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody BasicConfiguraForm basicConfiguraForm) {
                    log.error("BasicConfiguraService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("BasicConfiguraService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody BasicConfiguraQueryForm basicConfiguraQueryForm) {
                    log.error("BasicConfiguraService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody BasicConfiguraQueryForm basicConfiguraQueryForm) {
                    log.error("BasicConfiguraService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}