package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.PersonBakClient;
import com.deloitte.platform.api.project.param.PersonBakQueryForm;
import com.deloitte.platform.api.project.vo.PersonBakForm;
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
 * @Date : Create in 2019-05-20
 * @Description :   PersonBak feign客户端
 * @Modified :
 */
@FeignClient(name = "personBak-service", fallbackFactory = PersonBakFeignService.HystrixPersonBakService.class,primary = false)
public interface PersonBakFeignService extends PersonBakClient {

    @Component
    @Slf4j
    class HystrixPersonBakService implements FallbackFactory<PersonBakFeignService> {

        @Override
        public PersonBakFeignService create(Throwable throwable) {
            return new PersonBakFeignService() {
                @Override
                public Result add(@Valid @RequestBody PersonBakForm personBakForm) {
                    log.error("PersonBakService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("PersonBakService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody PersonBakForm personBakForm) {
                    log.error("PersonBakService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("PersonBakService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody PersonBakQueryForm personBakQueryForm) {
                    log.error("PersonBakService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody PersonBakQueryForm personBakQueryForm) {
                    log.error("PersonBakService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}