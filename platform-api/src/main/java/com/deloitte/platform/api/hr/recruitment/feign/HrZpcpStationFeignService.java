package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.HrZpcpStationClient;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpStationQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpStationForm;
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
 * @Date : Create in 2019-04-01
 * @Description :   HrZpcpStation feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = HrZpcpStationFeignService.HystrixHrZpcpStationService.class,primary = false)
public interface HrZpcpStationFeignService extends HrZpcpStationClient {

    @Component
    @Slf4j
    class HystrixHrZpcpStationService implements FallbackFactory<HrZpcpStationFeignService> {

        @Override
        public HrZpcpStationFeignService create(Throwable throwable) {
            return new HrZpcpStationFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrZpcpStationForm hrZpcpStationForm) {
                    log.error("HrZpcpStationService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrZpcpStationService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrZpcpStationForm hrZpcpStationForm) {
                    log.error("HrZpcpStationService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrZpcpStationService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrZpcpStationQueryForm hrZpcpStationQueryForm) {
                    log.error("HrZpcpStationService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrZpcpStationQueryForm hrZpcpStationQueryForm) {
                    log.error("HrZpcpStationService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportStationList(HrZpcpStationQueryForm HrZpcpStationQueryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("HrZpcpStationService exportStationList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result deleteList(DeleteForm deleteForm) {
                    log.error("HrZpcpStationService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}