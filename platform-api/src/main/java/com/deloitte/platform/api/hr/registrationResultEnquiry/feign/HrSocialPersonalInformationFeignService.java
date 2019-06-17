package com.deloitte.platform.api.hr.registrationResultEnquiry.feign;


import com.deloitte.platform.api.hr.registrationResultEnquiry.client.HrSocialPersonalInformationClient;
import com.deloitte.platform.api.hr.registrationResultEnquiry.param.HrSocialPersonalInformationQueryForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrSocialPersonalInformationForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrSocialPersonalInformationVo;
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
 * @Description :   HrSocialPersonalInformation feign客户端
 * @Modified :
 */
@FeignClient(name = "hrSocialPersonalInformation-service", fallbackFactory = HrSocialPersonalInformationFeignService.HystrixHrSocialPersonalInformationService.class,primary = false)
public interface HrSocialPersonalInformationFeignService extends HrSocialPersonalInformationClient {

    @Component
    @Slf4j
    class HystrixHrSocialPersonalInformationService implements FallbackFactory<HrSocialPersonalInformationFeignService> {

        @Override
        public HrSocialPersonalInformationFeignService create(Throwable throwable) {
            return new HrSocialPersonalInformationFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrSocialPersonalInformationForm hrSocialPersonalInformationForm) {
                    log.error("HrSocialPersonalInformationService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrSocialPersonalInformationService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrSocialPersonalInformationForm hrSocialPersonalInformationForm) {
                    log.error("HrSocialPersonalInformationService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrSocialPersonalInformationService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<HrSocialPersonalInformationVo> getByUserId(long id) {
                    log.error("HrSocialPersonalInformationService getByUserId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrSocialPersonalInformationQueryForm hrSocialPersonalInformationQueryForm) {
                    log.error("HrSocialPersonalInformationService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrSocialPersonalInformationQueryForm hrSocialPersonalInformationQueryForm) {
                    log.error("HrSocialPersonalInformationService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result save(@Valid @RequestBody HrSocialPersonalInformationForm hrSocialPersonalInformationForm) {
                    log.error("HrSocialPersonalInformationService save服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result submit(@Valid @RequestBody HrSocialPersonalInformationForm hrSocialPersonalInformationForm) {
                    log.error("HrSocialPersonalInformationService submit服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}