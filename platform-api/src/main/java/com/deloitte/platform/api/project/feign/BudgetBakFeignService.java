package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.BudgetBakClient;
import com.deloitte.platform.api.project.param.BudgetBakQueryForm;
import com.deloitte.platform.api.project.vo.BudgetBakForm;
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
 * @Description :   BudgetBak feign客户端
 * @Modified :
 */
@FeignClient(name = "budgetBak-service", fallbackFactory = BudgetBakFeignService.HystrixBudgetBakService.class,primary = false)
public interface BudgetBakFeignService extends BudgetBakClient {

    @Component
    @Slf4j
    class HystrixBudgetBakService implements FallbackFactory<BudgetBakFeignService> {

        @Override
        public BudgetBakFeignService create(Throwable throwable) {
            return new BudgetBakFeignService() {
                @Override
                public Result add(@Valid @RequestBody BudgetBakForm budgetBakForm) {
                    log.error("BudgetBakService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("BudgetBakService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody BudgetBakForm budgetBakForm) {
                    log.error("BudgetBakService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("BudgetBakService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody BudgetBakQueryForm budgetBakQueryForm) {
                    log.error("BudgetBakService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody BudgetBakQueryForm budgetBakQueryForm) {
                    log.error("BudgetBakService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}