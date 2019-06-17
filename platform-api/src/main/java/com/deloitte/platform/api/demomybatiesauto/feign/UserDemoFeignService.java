package com.deloitte.platform.api.demomybatiesauto.feign;


import com.deloitte.platform.api.demomybatiesauto.UserDemoClient;
import com.deloitte.platform.api.demomybatiesauto.param.UserDemoQueryForm;
import com.deloitte.platform.api.demomybatiesauto.vo.UserDemoForm;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
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
 * @Author : jack
 * @Date : Create in 2019-02-20
 * @Description :   UserDemo feign客户端
 * @Modified :
 */
//@FeignClient(name = "api-gateway", fallbackFactory = UserDemoFeignService.HystrixUserDemoService.class)
@FeignClient(name = "demo-mybatis-auto", fallbackFactory = UserDemoFeignService.HystrixUserDemoService.class)
public interface UserDemoFeignService extends UserDemoClient {

    @Component
    @Slf4j
    class HystrixUserDemoService implements FallbackFactory<UserDemoFeignService> {

        @Override
        public UserDemoFeignService create(Throwable throwable) {
            return new UserDemoFeignService() {

                @Override
                public Result add(@Valid @RequestBody UserDemoForm userDemoForm) {
                    log.error("UserDemoService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("UserDemoService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody UserDemoForm userDemoForm) {
                    log.error("UserDemoService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("UserDemoService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody UserDemoQueryForm userDemoQueryForm) {
                    log.error("UserDemoService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody UserDemoQueryForm userDemoQueryForm) {
                    log.error("UserDemoService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}