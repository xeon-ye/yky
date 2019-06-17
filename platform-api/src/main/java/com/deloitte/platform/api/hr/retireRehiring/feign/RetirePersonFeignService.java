package com.deloitte.platform.api.hr.retireRehiring.feign;


import com.deloitte.platform.api.hr.retireRehiring.client.RetirePersonClient;
import com.deloitte.platform.api.hr.retireRehiring.param.RetirePersonQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RetirePersonForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description :   RetirePerson feign客户端
 * @Modified :
 */
@FeignClient(name = "retirePerson-service", fallbackFactory = RetirePersonFeignService.HystrixRetirePersonService.class,primary = false)
public interface RetirePersonFeignService extends RetirePersonClient {

    @Component
    @Slf4j
    class HystrixRetirePersonService implements FallbackFactory<RetirePersonFeignService> {

        @Override
        public RetirePersonFeignService create(Throwable throwable) {
            return new RetirePersonFeignService() {
                @Override
                public Result add(@Valid @RequestBody RetirePersonForm retirePersonForm) {
                    log.error("RetirePersonService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("RetirePersonService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody RetirePersonForm retirePersonForm) {
                    log.error("RetirePersonService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("RetirePersonService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody RetirePersonQueryForm retirePersonQueryForm) {
                    log.error("RetirePersonService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody RetirePersonQueryForm retirePersonQueryForm) {
                    log.error("RetirePersonService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}