package com.deloitte.platform.api.hr.registrationResultEnquiry.feign;


import com.deloitte.platform.api.hr.registrationResultEnquiry.client.HrWorkExperienceClient;
import com.deloitte.platform.api.hr.registrationResultEnquiry.param.HrWorkExperienceQueryForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrWorkExperienceForm;
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
 * @Description :   HrWorkExperience feign客户端
 * @Modified :
 */
@FeignClient(name = "hrWorkExperience-service", fallbackFactory = HrWorkExperienceFeignService.HystrixHrWorkExperienceService.class,primary = false)
public interface HrWorkExperienceFeignService extends HrWorkExperienceClient {

    @Component
    @Slf4j
    class HystrixHrWorkExperienceService implements FallbackFactory<HrWorkExperienceFeignService> {

        @Override
        public HrWorkExperienceFeignService create(Throwable throwable) {
            return new HrWorkExperienceFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrWorkExperienceForm hrWorkExperienceForm) {
                    log.error("HrWorkExperienceService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrWorkExperienceService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrWorkExperienceForm hrWorkExperienceForm) {
                    log.error("HrWorkExperienceService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrWorkExperienceService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrWorkExperienceQueryForm hrWorkExperienceQueryForm) {
                    log.error("HrWorkExperienceService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrWorkExperienceQueryForm hrWorkExperienceQueryForm) {
                    log.error("HrWorkExperienceService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}