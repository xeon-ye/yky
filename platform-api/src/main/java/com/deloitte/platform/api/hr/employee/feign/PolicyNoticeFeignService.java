package com.deloitte.platform.api.hr.employee.feign;


import com.deloitte.platform.api.hr.employee.client.PolicyNoticeClient;
import com.deloitte.platform.api.hr.employee.param.PolicyNoticeQueryForm;
import com.deloitte.platform.api.hr.employee.vo.PolicyNoticeForm;
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
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-04
 * @Description :   PolicyNotice feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = PolicyNoticeFeignService.HystrixPolicyNoticeService.class,primary = false)
public interface PolicyNoticeFeignService extends PolicyNoticeClient {

    @Component
    @Slf4j
    class HystrixPolicyNoticeService implements FallbackFactory<PolicyNoticeFeignService> {

        @Override
        public PolicyNoticeFeignService create(Throwable throwable) {
            return new PolicyNoticeFeignService() {
                @Override
                public Result add(@Valid @RequestBody PolicyNoticeForm policyNoticeForm) {
                    log.error("PolicyNoticeService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("PolicyNoticeService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody PolicyNoticeForm policyNoticeForm) {
                    log.error("PolicyNoticeService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("PolicyNoticeService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody PolicyNoticeQueryForm policyNoticeQueryForm) {
                    log.error("PolicyNoticeService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody PolicyNoticeQueryForm policyNoticeQueryForm) {
                    log.error("PolicyNoticeService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}