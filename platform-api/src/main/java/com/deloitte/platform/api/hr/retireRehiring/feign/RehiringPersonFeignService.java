package com.deloitte.platform.api.hr.retireRehiring.feign;


import com.deloitte.platform.api.hr.retireRehiring.client.RehiringPersonClient;
import com.deloitte.platform.api.hr.retireRehiring.param.RehiringPersonQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RehiringPersonForm;
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
 * @Description :   RetireRehiringPerson feign客户端
 * @Modified :
 */
@FeignClient(name = "retireRehiringPerson-service", fallbackFactory = RehiringPersonFeignService.HystrixRetireRehiringPersonService.class,primary = false)
public interface RehiringPersonFeignService extends RehiringPersonClient {

    @Component
    @Slf4j
    class HystrixRetireRehiringPersonService implements FallbackFactory<RehiringPersonFeignService> {

        @Override
        public RehiringPersonFeignService create(Throwable throwable) {
            return new RehiringPersonFeignService() {
                @Override
                public Result add(@Valid @RequestBody RehiringPersonForm retireRehiringPersonForm) {
                    log.error("RetireRehiringPersonService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("RetireRehiringPersonService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody RehiringPersonForm retireRehiringPersonForm) {
                    log.error("RetireRehiringPersonService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("RetireRehiringPersonService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody RehiringPersonQueryForm retireRehiringPersonQueryForm) {
                    log.error("RetireRehiringPersonService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody RehiringPersonQueryForm retireRehiringPersonQueryForm) {
                    log.error("RetireRehiringPersonService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}