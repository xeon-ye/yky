package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.EconomicClient;
import com.deloitte.platform.api.project.param.EconomicQueryForm;
import com.deloitte.platform.api.project.vo.EconomicForm;
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
 * @Date : Create in 2019-05-15
 * @Description :   Economic feign客户端
 * @Modified :
 */
@FeignClient(name = "economic-service", fallbackFactory = EconomicFeignService.HystrixEconomicService.class,primary = false)
public interface EconomicFeignService extends EconomicClient {

    @Component
    @Slf4j
    class HystrixEconomicService implements FallbackFactory<EconomicFeignService> {

        @Override
        public EconomicFeignService create(Throwable throwable) {
            return new EconomicFeignService() {
                @Override
                public Result add(@Valid @RequestBody EconomicForm economicForm) {
                    log.error("EconomicService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EconomicService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EconomicForm economicForm) {
                    log.error("EconomicService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EconomicService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EconomicQueryForm economicQueryForm) {
                    log.error("EconomicService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EconomicQueryForm economicQueryForm) {
                    log.error("EconomicService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}