package com.deloitte.platform.api.hr.check.feign;

import com.deloitte.platform.api.hr.check.client.CheckTimeClient;
import com.deloitte.platform.api.hr.check.param.CheckTimeQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckTimeForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :   CheckTime feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckTimeFeignService.HystrixCheckTimeService.class,primary = false)
public interface CheckTimeFeignService extends CheckTimeClient {

    @Component
    @Slf4j
    class HystrixCheckTimeService implements FallbackFactory<CheckTimeFeignService> {

        @Override
        public CheckTimeFeignService create(Throwable throwable) {
            return new CheckTimeFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckTimeForm checkTimeForm) {
                    log.error("CheckTimeService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckTimeService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckTimeForm checkTimeForm) {
                    log.error("CheckTimeService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckTimeService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckTimeQueryForm checkTimeQueryForm) {
                    log.error("CheckTimeService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckTimeQueryForm checkTimeQueryForm) {
                    log.error("CheckTimeService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchDelete( @Valid @RequestBody List<String> ids) {
                    log.error("CheckTimeService batchDelete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}