package com.deloitte.platform.api.hr.registrationResultEnquiry.feign;


import com.deloitte.platform.api.hr.registrationResultEnquiry.client.HrGraduateInformationClient;
import com.deloitte.platform.api.hr.registrationResultEnquiry.param.HrGraduateInformationQueryForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrGraduateInformationForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrGraduateInformationVo;
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
 * @Description :   HrGraduateInformation feign客户端
 * @Modified :
 */
@FeignClient(name = "hrGraduateInformation-service", fallbackFactory = HrGraduateInformationFeignService.HystrixHrGraduateInformationService.class,primary = false)
public interface HrGraduateInformationFeignService extends HrGraduateInformationClient {

    @Component
    @Slf4j
    class HystrixHrGraduateInformationService implements FallbackFactory<HrGraduateInformationFeignService> {

        @Override
        public HrGraduateInformationFeignService create(Throwable throwable) {
            return new HrGraduateInformationFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrGraduateInformationForm hrGraduateInformationForm) {
                    log.error("HrGraduateInformationService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrGraduateInformationService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrGraduateInformationForm hrGraduateInformationForm) {
                    log.error("HrGraduateInformationService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrGraduateInformationService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<HrGraduateInformationVo> getByUserId(long id) {
                    log.error("HrGraduateInformationService getByUserId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrGraduateInformationQueryForm hrGraduateInformationQueryForm) {
                    log.error("HrGraduateInformationService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrGraduateInformationQueryForm hrGraduateInformationQueryForm) {
                    log.error("HrGraduateInformationService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result save(@Valid @RequestBody HrGraduateInformationForm hrGraduateInformationForm) {
                    log.error("HrGraduateInformationService save服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result submit(@Valid @RequestBody HrGraduateInformationForm hrGraduateInformationForm) {
                    log.error("HrGraduateInformationService submit服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}