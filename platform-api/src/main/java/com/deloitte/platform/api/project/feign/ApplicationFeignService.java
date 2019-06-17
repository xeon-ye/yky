package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ApplicationClient;
import com.deloitte.platform.api.project.param.ApplicationQueryForm;
import com.deloitte.platform.api.project.vo.ApplicationForm;
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
 * @Date : Create in 2019-05-22
 * @Description :   Application feign客户端
 * @Modified :
 */
@FeignClient(name = "application-service", fallbackFactory = ApplicationFeignService.HystrixApplicationService.class,primary = false)
public interface ApplicationFeignService extends ApplicationClient {

    @Component
    @Slf4j
    class HystrixApplicationService implements FallbackFactory<ApplicationFeignService> {

        @Override
        public ApplicationFeignService create(Throwable throwable) {
            return new ApplicationFeignService() {
                @Override
                public Result add(@Valid @RequestBody ApplicationForm applicationForm) {
                    log.error("ApplicationService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ApplicationService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ApplicationForm applicationForm) {
                    log.error("ApplicationService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ApplicationService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ApplicationQueryForm applicationQueryForm) {
                    log.error("ApplicationService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ApplicationQueryForm applicationQueryForm) {
                    log.error("ApplicationService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}