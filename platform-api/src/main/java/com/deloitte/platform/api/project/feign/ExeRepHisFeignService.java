package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ExeRepHisClient;
import com.deloitte.platform.api.project.param.ExeRepHisQueryForm;
import com.deloitte.platform.api.project.vo.ExeRepHisForm;
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
 * @Date : Create in 2019-05-24
 * @Description :   ExeRepHis feign客户端
 * @Modified :
 */
@FeignClient(name = "exeRepHis-service", fallbackFactory = ExeRepHisFeignService.HystrixExeRepHisService.class,primary = false)
public interface ExeRepHisFeignService extends ExeRepHisClient {

    @Component
    @Slf4j
    class HystrixExeRepHisService implements FallbackFactory<ExeRepHisFeignService> {

        @Override
        public ExeRepHisFeignService create(Throwable throwable) {
            return new ExeRepHisFeignService() {
                @Override
                public Result add(@Valid @RequestBody ExeRepHisForm exeRepHisForm) {
                    log.error("ExeRepHisService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ExeRepHisService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ExeRepHisForm exeRepHisForm) {
                    log.error("ExeRepHisService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ExeRepHisService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ExeRepHisQueryForm exeRepHisQueryForm) {
                    log.error("ExeRepHisService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ExeRepHisQueryForm exeRepHisQueryForm) {
                    log.error("ExeRepHisService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}