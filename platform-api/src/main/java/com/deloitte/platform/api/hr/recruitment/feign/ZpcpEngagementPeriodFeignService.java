package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.ZpcpEngagementPeriodClient;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpEngagementPeriodQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpEngagementPeriodForm;
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
 * @Date : Create in 2019-04-23
 * @Description :   ZpcpEngagementPeriod feign客户端
 * @Modified :
 */
@FeignClient(name = "zpcpEngagementPeriod-service", fallbackFactory = ZpcpEngagementPeriodFeignService.HystrixZpcpEngagementPeriodService.class,primary = false)
public interface ZpcpEngagementPeriodFeignService extends ZpcpEngagementPeriodClient {

    @Component
    @Slf4j
    class HystrixZpcpEngagementPeriodService implements FallbackFactory<ZpcpEngagementPeriodFeignService> {

        @Override
        public ZpcpEngagementPeriodFeignService create(Throwable throwable) {
            return new ZpcpEngagementPeriodFeignService() {
                @Override
                public Result add(@Valid @RequestBody ZpcpEngagementPeriodForm zpcpEngagementPeriodForm) {
                    log.error("ZpcpEngagementPeriodService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ZpcpEngagementPeriodService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ZpcpEngagementPeriodForm zpcpEngagementPeriodForm) {
                    log.error("ZpcpEngagementPeriodService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ZpcpEngagementPeriodService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ZpcpEngagementPeriodQueryForm zpcpEngagementPeriodQueryForm) {
                    log.error("ZpcpEngagementPeriodService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ZpcpEngagementPeriodQueryForm zpcpEngagementPeriodQueryForm) {
                    log.error("ZpcpEngagementPeriodService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(DeleteForm deleteForm) {
                    log.error("ZpcpEngagementPeriodService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportEngagementPeriodList(ZpcpEngagementPeriodQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("ZpcpEngagementPeriodService exportEngagementPeriodList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}