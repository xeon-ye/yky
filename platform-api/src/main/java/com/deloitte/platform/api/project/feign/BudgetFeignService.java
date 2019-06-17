package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.BudgetClient;
import com.deloitte.platform.api.project.param.BudgetQueryForm;
import com.deloitte.platform.api.project.vo.BudgetForm;
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
 * @Author : zhengchun
 * @Date : Create in 2019-05-09
 * @Description :   Budget feign客户端
 * @Modified :
 */
@FeignClient(name = "budget-service", fallbackFactory = BudgetFeignService.HystrixBudgetService.class,primary = false)
public interface BudgetFeignService extends BudgetClient {

    @Component
    @Slf4j
    class HystrixBudgetService implements FallbackFactory<BudgetFeignService> {

        @Override
        public BudgetFeignService create(Throwable throwable) {
            return new BudgetFeignService() {
                @Override
                public Result add(@Valid @RequestBody BudgetForm budgetForm) {
                    log.error("BudgetService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("BudgetService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody BudgetForm budgetForm) {
                    log.error("BudgetService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable String applicationId) {
                    log.error("BudgetService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody BudgetQueryForm budgetQueryForm) {
                    log.error("BudgetService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody BudgetQueryForm budgetQueryForm) {
                    log.error("BudgetService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}