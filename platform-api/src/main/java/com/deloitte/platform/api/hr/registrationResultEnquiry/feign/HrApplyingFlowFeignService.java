package com.deloitte.platform.api.hr.registrationResultEnquiry.feign;


import com.deloitte.platform.api.hr.registrationResultEnquiry.client.HrApplyingFlowClient;
import com.deloitte.platform.api.hr.registrationResultEnquiry.param.HrApplyingFlowQueryForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrApplyingFlowForm;
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
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description :   HrApplyingFlow feign客户端
 * @Modified :
 */
@FeignClient(name = "hrApplyingFlow-service", fallbackFactory = HrApplyingFlowFeignService.HystrixHrApplyingFlowService.class,primary = false)
public interface HrApplyingFlowFeignService extends HrApplyingFlowClient {

    @Component
    @Slf4j
    class HystrixHrApplyingFlowService implements FallbackFactory<HrApplyingFlowFeignService> {

        @Override
        public HrApplyingFlowFeignService create(Throwable throwable) {
            return new HrApplyingFlowFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrApplyingFlowForm hrApplyingFlowForm) {
                    log.error("HrApplyingFlowService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrApplyingFlowService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrApplyingFlowForm hrApplyingFlowForm) {
                    log.error("HrApplyingFlowService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrApplyingFlowService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrApplyingFlowQueryForm hrApplyingFlowQueryForm) {
                    log.error("HrApplyingFlowService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrApplyingFlowQueryForm hrApplyingFlowQueryForm) {
                    log.error("HrApplyingFlowService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}