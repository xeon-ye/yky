package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.ZpcpEmployerInfoClient;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpEmployerInfoQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpEmployerInfoForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-10
 * @Description :   ZpcpEmployerInfo feign客户端
 * @Modified :
 */
@FeignClient(name = "zpcpEmployerInfo-service", fallbackFactory = ZpcpEmployerInfoFeignService.HystrixZpcpEmployerInfoService.class,primary = false)
public interface ZpcpEmployerInfoFeignService extends ZpcpEmployerInfoClient {

    @Component
    @Slf4j
    class HystrixZpcpEmployerInfoService implements FallbackFactory<ZpcpEmployerInfoFeignService> {

        @Override
        public ZpcpEmployerInfoFeignService create(Throwable throwable) {
            return new ZpcpEmployerInfoFeignService() {
                @Override
                public Result add(@Valid @RequestBody ZpcpEmployerInfoForm zpcpEmployerInfoForm) {
                    log.error("ZpcpEmployerInfoService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ZpcpEmployerInfoService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ZpcpEmployerInfoForm zpcpEmployerInfoForm) {
                    log.error("ZpcpEmployerInfoService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ZpcpEmployerInfoService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ZpcpEmployerInfoQueryForm zpcpEmployerInfoQueryForm) {
                    log.error("ZpcpEmployerInfoService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ZpcpEmployerInfoQueryForm zpcpEmployerInfoQueryForm) {
                    log.error("ZpcpEmployerInfoService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportEmployerInfoList(ZpcpEmployerInfoQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("ZpcpEmployerInfoService exportEmployerInfoList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}