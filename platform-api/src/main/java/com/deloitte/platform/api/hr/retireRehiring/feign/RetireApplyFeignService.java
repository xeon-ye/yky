package com.deloitte.platform.api.hr.retireRehiring.feign;


import com.deloitte.platform.api.hr.retireRehiring.client.RetireApplyClient;
import com.deloitte.platform.api.hr.retireRehiring.param.RetireApplyQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.param.RetireRehiringQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RemindVo;
import com.deloitte.platform.api.hr.retireRehiring.vo.RetireApplyForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RetireApplyProcessForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description :   RetireApply feign客户端
 * @Modified :
 */
@FeignClient(name = "retireApply-service", fallbackFactory = RetireApplyFeignService.HystrixRetireApplyService.class,primary = false)
public interface RetireApplyFeignService extends RetireApplyClient {

    @Component
    @Slf4j
    class HystrixRetireApplyService implements FallbackFactory<RetireApplyFeignService> {

        @Override
        public RetireApplyFeignService create(Throwable throwable) {
            return new RetireApplyFeignService() {
                @Override
                public Result addOrUpdate(@Valid @RequestBody RetireApplyForm retireApplyForm) {
                    log.error("RetireApplyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("RetireApplyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody RetireApplyForm retireApplyForm) {
                    log.error("RetireApplyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("RetireApplyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody RetireApplyQueryForm retireApplyQueryForm) {
                    log.error("RetireApplyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody RetireApplyQueryForm retireApplyQueryForm) {
                    log.error("RetireApplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<RemindVo> getReminder(RetireRehiringQueryForm queryForm) {
                    log.error("RetireApplyService getInstitutionYearRetire服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result retireApproveProcess(@Valid @ModelAttribute RetireApplyProcessForm retireApplyProcessForm) {
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}