package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ExeChangeClient;
import com.deloitte.platform.api.project.param.ExeChangeQueryForm;
import com.deloitte.platform.api.project.vo.ExeChangeForm;
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
 * @Date : Create in 2019-05-17
 * @Description :   ExeChange feign客户端
 * @Modified :
 */
@FeignClient(name = "exeChange-service", fallbackFactory = ExeChangeFeignService.HystrixExeChangeService.class,primary = false)
public interface ExeChangeFeignService extends ExeChangeClient {

    @Component
    @Slf4j
    class HystrixExeChangeService implements FallbackFactory<ExeChangeFeignService> {

        @Override
        public ExeChangeFeignService create(Throwable throwable) {
            return new ExeChangeFeignService() {
                @Override
                public Result add(@Valid @RequestBody ExeChangeForm exeChangeForm) {
                    log.error("ExeChangeService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ExeChangeService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ExeChangeForm exeChangeForm) {
                    log.error("ExeChangeService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ExeChangeService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ExeChangeQueryForm exeChangeQueryForm) {
                    log.error("ExeChangeService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ExeChangeQueryForm exeChangeQueryForm) {
                    log.error("ExeChangeService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}