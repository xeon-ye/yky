package com.deloitte.platform.api.hr.registrationResultEnquiry.feign;


import com.deloitte.platform.api.hr.registrationResultEnquiry.client.HrAccountEnquiryClient;
import com.deloitte.platform.api.hr.registrationResultEnquiry.param.HrAccountEnquiryQueryForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrAccountEnquiryForm;
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
 * @Description :   HrAccount feign客户端
 * @Modified :
 */
@FeignClient(name = "hrAccount-service", fallbackFactory = HrAccountEnquiryFeignService.HystrixHrAccountService.class,primary = false)
public interface HrAccountEnquiryFeignService extends HrAccountEnquiryClient {

    @Component
    @Slf4j
    class HystrixHrAccountService implements FallbackFactory<HrAccountEnquiryFeignService> {

        @Override
        public HrAccountEnquiryFeignService create(Throwable throwable) {
            return new HrAccountEnquiryFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrAccountEnquiryForm hrAccountEnquiryForm) {
                    log.error("HrAccountService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrAccountService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrAccountEnquiryForm hrAccountEnquiryForm) {
                    log.error("HrAccountService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrAccountService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrAccountEnquiryQueryForm hrAccountEnquiryQueryForm) {
                    log.error("HrAccountService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrAccountEnquiryQueryForm hrAccountEnquiryQueryForm) {
                    log.error("HrAccountService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}