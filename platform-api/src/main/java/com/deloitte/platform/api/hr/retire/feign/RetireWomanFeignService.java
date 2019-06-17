package com.deloitte.platform.api.hr.retire.feign;


import com.deloitte.platform.api.hr.retire.client.RetireWomanClient;
import com.deloitte.platform.api.hr.retire.param.RetireWomanQueryForm;
import com.deloitte.platform.api.hr.retire.vo.RetireWomanForm;
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
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-11
 * @Description :   RetireWoman feign客户端
 * @Modified :
 */
@FeignClient(name = "retireWoman-service", fallbackFactory = RetireWomanFeignService.HystrixRetireWomanService.class,primary = false)
public interface RetireWomanFeignService extends RetireWomanClient {

    @Component
    @Slf4j
    class HystrixRetireWomanService implements FallbackFactory<RetireWomanFeignService> {

        @Override
        public RetireWomanFeignService create(Throwable throwable) {
            return new RetireWomanFeignService() {
                @Override
                public Result add(@Valid @RequestBody RetireWomanForm retireWomanForm) {
                    log.error("RetireWomanService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("RetireWomanService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody RetireWomanForm retireWomanForm) {
                    log.error("RetireWomanService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("RetireWomanService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody RetireWomanQueryForm retireWomanQueryForm) {
                    log.error("RetireWomanService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody RetireWomanQueryForm retireWomanQueryForm) {
                    log.error("RetireWomanService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}