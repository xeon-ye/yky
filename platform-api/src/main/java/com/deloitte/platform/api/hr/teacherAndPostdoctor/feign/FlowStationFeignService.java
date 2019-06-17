package com.deloitte.platform.api.hr.teacherAndPostdoctor.feign;


import com.deloitte.platform.api.hr.teacherAndPostdoctor.client.FlowStationClient;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.FlowStationExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.FlowStationQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.FlowExpireForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.FlowStationForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   FlowStation feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = FlowStationFeignService.HystrixFlowStationService.class,primary = false)
public interface FlowStationFeignService extends FlowStationClient {

    @Component
    @Slf4j
    class HystrixFlowStationService implements FallbackFactory<FlowStationFeignService> {

        @Override
        public FlowStationFeignService create(Throwable throwable) {
            return new FlowStationFeignService() {
                @Override
                public Result add(@Valid @RequestBody FlowStationForm flowStationForm) {
                    log.error("FlowStationService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("FlowStationService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result updateExpireUser(@RequestBody @Valid FlowExpireForm flowExpireForm) {
                    log.error("FlowStationService updateExpireUser服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getDiscussMajorByPid(@RequestParam String code) {
                    log.error("FlowStationService getDiscussMajorByPid服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getExpire(@PathVariable long id) {
                    log.error("FlowStationServicegetExpire服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getFlowStation(){
                    log.error("FlowStationService getFlowStation服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody FlowStationForm flowStationForm) {
                    log.error("FlowStationService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("FlowStationService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getTree() {
                    log.error("FlowStationService getTree服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getFlowStationList(@Valid @RequestBody FlowStationQueryForm flowStationQueryForm) {
                    log.error("FlowStationService getFlowStationList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportFlowStationList(@ModelAttribute FlowStationExportForm flowStationExportForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("FlowStationService exportFlowStationList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public  Result addExpireUser(@RequestParam String userCode, @RequestParam String stationCode,String effectiveDate,Integer isValid) {
                    log.error("FlowStationService addExpireUser服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getExpireFlowStationList(@Valid @RequestBody FlowStationQueryForm flowStationQueryForm) {
                    log.error("FlowStationService getExpireFlowStationList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportExpireFlowStationList(@ModelAttribute FlowStationExportForm flowStationExportForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("FlowStationService exportExpireFlowStationList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result deleteExpire(@PathVariable long id) {
                    log.error("FlowStationService deleteExpire服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}