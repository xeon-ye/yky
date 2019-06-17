package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.PersonClient;
import com.deloitte.platform.api.project.param.PersonQueryForm;
import com.deloitte.platform.api.project.vo.PersonForm;
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
 * @Description :   Person feign客户端
 * @Modified :
 */
@FeignClient(name = "person-service", fallbackFactory = PersonFeignService.HystrixPersonService.class,primary = false)
public interface PersonFeignService extends PersonClient {

    @Component
    @Slf4j
    class HystrixPersonService implements FallbackFactory<PersonFeignService> {

        @Override
        public PersonFeignService create(Throwable throwable) {
            return new PersonFeignService() {
                @Override
                public Result add(@Valid @RequestBody PersonForm personForm) {
                    log.error("PersonService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("PersonService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody PersonForm personForm) {
                    log.error("PersonService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("PersonService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody PersonQueryForm personQueryForm) {
                    log.error("PersonService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody PersonQueryForm personQueryForm) {
                    log.error("PersonService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}