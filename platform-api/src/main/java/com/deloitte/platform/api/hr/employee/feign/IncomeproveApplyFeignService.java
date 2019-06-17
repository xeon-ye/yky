package com.deloitte.platform.api.hr.employee.feign;


import com.deloitte.platform.api.hr.employee.client.IncomeproveApplyClient;
import com.deloitte.platform.api.hr.employee.param.IncomeproveApplyQueryForm;
import com.deloitte.platform.api.hr.employee.vo.IncomeproveApplyForm;
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
 * @Date : Create in 2019-04-08
 * @Description :   IncomeproveApply feign客户端
 * @Modified :
 */
@FeignClient(name = "incomeproveApply-service", fallbackFactory = IncomeproveApplyFeignService.HystrixIncomeproveApplyService.class,primary = false)
public interface IncomeproveApplyFeignService extends IncomeproveApplyClient {

    @Component
    @Slf4j
    class HystrixIncomeproveApplyService implements FallbackFactory<IncomeproveApplyFeignService> {

        @Override
        public IncomeproveApplyFeignService create(Throwable throwable) {
            return new IncomeproveApplyFeignService() {
                @Override
                public Result add(@Valid @RequestBody IncomeproveApplyForm incomeproveApplyForm) {
                    log.error("IncomeproveApplyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("IncomeproveApplyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody IncomeproveApplyForm incomeproveApplyForm) {
                    log.error("IncomeproveApplyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("IncomeproveApplyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody IncomeproveApplyQueryForm incomeproveApplyQueryForm) {
                    log.error("IncomeproveApplyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody IncomeproveApplyQueryForm incomeproveApplyQueryForm) {
                    log.error("IncomeproveApplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}