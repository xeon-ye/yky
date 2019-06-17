package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.ZpcpNoticeStationsClient;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpNoticeStationsQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpNoticeStationsForm;
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
 * @Date : Create in 2019-04-18
 * @Description :   ZpcpNoticeStations feign客户端
 * @Modified :
 */
@FeignClient(name = "zpcpNoticeStations-service", fallbackFactory = ZpcpNoticeStationsFeignService.HystrixZpcpNoticeStationsService.class,primary = false)
public interface ZpcpNoticeStationsFeignService extends ZpcpNoticeStationsClient {

    @Component
    @Slf4j
    class HystrixZpcpNoticeStationsService implements FallbackFactory<ZpcpNoticeStationsFeignService> {

        @Override
        public ZpcpNoticeStationsFeignService create(Throwable throwable) {
            return new ZpcpNoticeStationsFeignService() {
                @Override
                public Result add(@Valid @RequestBody ZpcpNoticeStationsForm zpcpNoticeStationsForm) {
                    log.error("ZpcpNoticeStationsService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ZpcpNoticeStationsService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ZpcpNoticeStationsForm zpcpNoticeStationsForm) {
                    log.error("ZpcpNoticeStationsService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ZpcpNoticeStationsService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ZpcpNoticeStationsQueryForm zpcpNoticeStationsQueryForm) {
                    log.error("ZpcpNoticeStationsService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ZpcpNoticeStationsQueryForm zpcpNoticeStationsQueryForm) {
                    log.error("ZpcpNoticeStationsService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}