package com.deloitte.platform.api.hr.retireRehiring.feign;


import com.deloitte.platform.api.hr.employee.vo.EmployeeBaseVo;
import com.deloitte.platform.api.hr.retireRehiring.client.DelayApplyClient;
import com.deloitte.platform.api.hr.retireRehiring.param.DelayApplyQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.DelayApplyForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RemindProcessForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RetireApplyProcessForm;
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
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description :   RetireDelayApply feign客户端
 * @Modified :
 */
@FeignClient(name = "retireDelayApply-service", fallbackFactory = DelayApplyFeignService.HystrixRetireDelayApplyService.class, primary = false)
public interface DelayApplyFeignService extends DelayApplyClient {

    @Component
    @Slf4j
    class HystrixRetireDelayApplyService implements FallbackFactory<DelayApplyFeignService> {

        @Override
        public DelayApplyFeignService create(Throwable throwable) {
            return new DelayApplyFeignService() {
                @Override
                public Result addOrUpdate(@Valid @RequestBody DelayApplyForm retireDelayApplyForm) {
                    log.error("RetireDelayApplyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("RetireDelayApplyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody DelayApplyForm retireDelayApplyForm) {
                    log.error("RetireDelayApplyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("RetireDelayApplyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody DelayApplyQueryForm retireDelayApplyQueryForm) {
                    log.error("RetireDelayApplyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody DelayApplyQueryForm retireDelayApplyQueryForm) {
                    log.error("RetireDelayApplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result<EmployeeBaseVo> getLoginUser() {
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result approveProcess(@Valid @RequestBody RetireApplyProcessForm retireDelayApplyQueryForm) {
                    log.error("RetireDelayApplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}