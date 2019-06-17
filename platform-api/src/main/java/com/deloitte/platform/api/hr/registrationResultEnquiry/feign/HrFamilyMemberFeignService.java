package com.deloitte.platform.api.hr.registrationResultEnquiry.feign;


import com.deloitte.platform.api.hr.registrationResultEnquiry.client.HrFamilyMemberClient;
import com.deloitte.platform.api.hr.registrationResultEnquiry.param.HrFamilyMemberQueryForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrFamilyMemberForm;
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
 * @Description :   HrFamilyMember feign客户端
 * @Modified :
 */
@FeignClient(name = "hrFamilyMember-service", fallbackFactory = HrFamilyMemberFeignService.HystrixHrFamilyMemberService.class,primary = false)
public interface HrFamilyMemberFeignService extends HrFamilyMemberClient {

    @Component
    @Slf4j
    class HystrixHrFamilyMemberService implements FallbackFactory<HrFamilyMemberFeignService> {

        @Override
        public HrFamilyMemberFeignService create(Throwable throwable) {
            return new HrFamilyMemberFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrFamilyMemberForm hrFamilyMemberForm) {
                    log.error("HrFamilyMemberService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrFamilyMemberService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrFamilyMemberForm hrFamilyMemberForm) {
                    log.error("HrFamilyMemberService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrFamilyMemberService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrFamilyMemberQueryForm hrFamilyMemberQueryForm) {
                    log.error("HrFamilyMemberService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrFamilyMemberQueryForm hrFamilyMemberQueryForm) {
                    log.error("HrFamilyMemberService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}