package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ExpenseClient;
import com.deloitte.platform.api.project.param.ExpenseQueryForm;
import com.deloitte.platform.api.project.vo.ExpenseForm;
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
 * @Date : Create in 2019-04-24
 * @Description :   Expense feign客户端
 * @Modified :
 */
@FeignClient(name = "expense-service", fallbackFactory = ExpenseFeignService.HystrixExpenseService.class,primary = false)
public interface ExpenseFeignService extends ExpenseClient {

    @Component
    @Slf4j
    class HystrixExpenseService implements FallbackFactory<ExpenseFeignService> {

        @Override
        public ExpenseFeignService create(Throwable throwable) {
            return new ExpenseFeignService() {
                @Override
                public Result add(@Valid @RequestBody ExpenseForm expenseForm) {
                    log.error("ExpenseService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ExpenseService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ExpenseForm expenseForm) {
                    log.error("ExpenseService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ExpenseService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ExpenseQueryForm expenseQueryForm) {
                    log.error("ExpenseService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ExpenseQueryForm expenseQueryForm) {
                    log.error("ExpenseService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}