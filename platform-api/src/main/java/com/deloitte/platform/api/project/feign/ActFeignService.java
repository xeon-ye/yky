package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ActClient;
import com.deloitte.platform.api.project.param.ActQueryForm;
import com.deloitte.platform.api.project.vo.ActForm;
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
 * @Date : Create in 2019-05-14
 * @Description :   Act feign客户端
 * @Modified :
 */
@FeignClient(name = "act-service", fallbackFactory = ActFeignService.HystrixActService.class,primary = false)
public interface ActFeignService extends ActClient {

    @Component
    @Slf4j
    class HystrixActService implements FallbackFactory<ActFeignService> {

        @Override
        public ActFeignService create(Throwable throwable) {
            return new ActFeignService() {
                @Override
                public Result add(@Valid @RequestBody ActForm actForm) {
                    log.error("ActService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ActService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ActForm actForm) {
                    log.error("ActService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ActService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ActQueryForm actQueryForm) {
                    log.error("ActService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ActQueryForm actQueryForm) {
                    log.error("ActService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}