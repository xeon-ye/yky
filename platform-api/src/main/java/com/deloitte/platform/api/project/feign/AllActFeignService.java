package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.AllActClient;
import com.deloitte.platform.api.project.param.AllActQueryForm;
import com.deloitte.platform.api.project.vo.AllActForm;
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
 * @Date : Create in 2019-04-24
 * @Description :   AllAct feign客户端
 * @Modified :
 */
@FeignClient(name = "allAct-service", fallbackFactory = AllActFeignService.HystrixAllActService.class,primary = false)
public interface AllActFeignService extends AllActClient {

    @Component
    @Slf4j
    class HystrixAllActService implements FallbackFactory<AllActFeignService> {

        @Override
        public AllActFeignService create(Throwable throwable) {
            return new AllActFeignService() {
                @Override
                public Result add(@Valid @RequestBody AllActForm allActForm) {
                    log.error("AllActService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("AllActService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody AllActForm allActForm) {
                    log.error("AllActService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("AllActService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody AllActQueryForm allActQueryForm) {
                    log.error("AllActService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody AllActQueryForm allActQueryForm) {
                    log.error("AllActService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}