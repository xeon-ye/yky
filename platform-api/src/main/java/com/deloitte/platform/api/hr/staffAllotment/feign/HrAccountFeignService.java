package com.deloitte.platform.api.hr.staffAllotment.feign;


import com.deloitte.platform.api.hr.registrationLogin.param.HrAccountLoginQueryForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.param.HrAccountEnquiryQueryForm;
import com.deloitte.platform.api.hr.staffAllotment.client.HrAccountClient;
import com.deloitte.platform.api.hr.staffAllotment.param.HrAccountQueryForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrAccountForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-03
 * @Description :   HrAccount feign客户端
 * @Modified :
 */
@FeignClient(name = "hrAccount-service", fallbackFactory = HrAccountFeignService.HystrixHrAccountService.class,primary = false)
public interface HrAccountFeignService extends HrAccountClient {

    @Component
    @Slf4j
    class HystrixHrAccountService implements FallbackFactory<HrAccountFeignService> {

        @Override
        public HrAccountFeignService create(Throwable throwable) {
            return new HrAccountFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrAccountForm hrAccountForm) {
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
                public Result update(@PathVariable long id, @Valid @RequestBody HrAccountForm hrAccountForm) {
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
                public Result list(@Valid @RequestBody HrAccountQueryForm hrAccountQueryForm) {
                    log.error("HrAccountService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrAccountQueryForm hrAccountQueryForm) {
                    log.error("HrAccountService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}