package com.deloitte.platform.api.hr.registrationResultEnquiry.feign;


import com.deloitte.platform.api.hr.registrationResultEnquiry.client.HrEducationExperienceClient;
import com.deloitte.platform.api.hr.registrationResultEnquiry.param.HrEducationExperienceQueryForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrEducationExperienceForm;
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
 * @Description :   HrEducationExperience feign客户端
 * @Modified :
 */
@FeignClient(name = "hrEducationExperience-service", fallbackFactory = HrEducationExperienceFeignService.HystrixHrEducationExperienceService.class,primary = false)
public interface HrEducationExperienceFeignService extends HrEducationExperienceClient {

    @Component
    @Slf4j
    class HystrixHrEducationExperienceService implements FallbackFactory<HrEducationExperienceFeignService> {

        @Override
        public HrEducationExperienceFeignService create(Throwable throwable) {
            return new HrEducationExperienceFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrEducationExperienceForm hrEducationExperienceForm) {
                    log.error("HrEducationExperienceService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrEducationExperienceService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrEducationExperienceForm hrEducationExperienceForm) {
                    log.error("HrEducationExperienceService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrEducationExperienceService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrEducationExperienceQueryForm hrEducationExperienceQueryForm) {
                    log.error("HrEducationExperienceService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrEducationExperienceQueryForm hrEducationExperienceQueryForm) {
                    log.error("HrEducationExperienceService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}