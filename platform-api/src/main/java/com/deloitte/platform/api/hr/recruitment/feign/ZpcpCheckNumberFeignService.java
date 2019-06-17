package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.ZpcpCheckNumberClient;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpCheckNumberQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpCheckNumberForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpCheckNumberVo;
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
 * @Date : Create in 2019-04-19
 * @Description :   ZpcpCheckNumber feign客户端
 * @Modified :
 */
@FeignClient(name = "zpcpCheckNumber-service", fallbackFactory = ZpcpCheckNumberFeignService.HystrixZpcpCheckNumberService.class,primary = false)
public interface ZpcpCheckNumberFeignService extends ZpcpCheckNumberClient {

    @Component
    @Slf4j
    class HystrixZpcpCheckNumberService implements FallbackFactory<ZpcpCheckNumberFeignService> {

        @Override
        public ZpcpCheckNumberFeignService create(Throwable throwable) {
            return new ZpcpCheckNumberFeignService() {
                @Override
                public Result add(@Valid @RequestBody ZpcpCheckNumberForm zpcpCheckNumberForm) {
                    log.error("ZpcpCheckNumberService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ZpcpCheckNumberService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ZpcpCheckNumberForm zpcpCheckNumberForm) {
                    log.error("ZpcpCheckNumberService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ZpcpCheckNumberService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ZpcpCheckNumberQueryForm zpcpCheckNumberQueryForm) {
                    log.error("ZpcpCheckNumberService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ZpcpCheckNumberQueryForm zpcpCheckNumberQueryForm) {
                    log.error("ZpcpCheckNumberService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addOrUpdate(ZpcpCheckNumberForm zpcpCheckNumberForm) {
                    log.error("ZpcpCheckNumberService addOrUpdate服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<ZpcpCheckNumberVo> getCheckNumberByNoticeIdAndCheckType(ZpcpCheckNumberQueryForm queryForm) {
                    log.error("ZpcpCheckNumberService getCheckNumberByNoticeIdAndCheckType服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}