package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.HrZpcpWorkgroupClient;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpWorkgroupQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpWorkgroupForm;
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
 * @Description :   HrZpcpWorkgroup feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = HrZpcpWorkgroupFeignService.HystrixHrZpcpWorkgroupService.class,primary = false)
public interface HrZpcpWorkgroupFeignService extends HrZpcpWorkgroupClient {

    @Component
    @Slf4j
    class HystrixHrZpcpWorkgroupService implements FallbackFactory<HrZpcpWorkgroupFeignService> {

        @Override
        public HrZpcpWorkgroupFeignService create(Throwable throwable) {
            return new HrZpcpWorkgroupFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrZpcpWorkgroupForm hrZpcpWorkgroupForm) {
                    log.error("HrZpcpWorkgroupService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrZpcpWorkgroupService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrZpcpWorkgroupForm hrZpcpWorkgroupForm) {
                    log.error("HrZpcpWorkgroupService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrZpcpWorkgroupService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrZpcpWorkgroupQueryForm hrZpcpWorkgroupQueryForm) {
                    log.error("HrZpcpWorkgroupService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrZpcpWorkgroupQueryForm hrZpcpWorkgroupQueryForm) {
                    log.error("HrZpcpWorkgroupService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportGroupList(HrZpcpWorkgroupQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("HrZpcpWorkgroupService search服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result deleteList(DeleteForm deleteForm) {
                    log.error("HrZpcpWorkgroupService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}