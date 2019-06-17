package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.MaintBudgetClient;
import com.deloitte.platform.api.project.param.MaintBudgetQueryForm;
import com.deloitte.platform.api.project.vo.MaintBudgetForm;
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
 * @Date : Create in 2019-05-20
 * @Description :   MaintBudget feign客户端
 * @Modified :
 */
@FeignClient(name = "maintBudget-service", fallbackFactory = MaintBudgetFeignService.HystrixMaintBudgetService.class,primary = false)
public interface MaintBudgetFeignService extends MaintBudgetClient {

    @Component
    @Slf4j
    class HystrixMaintBudgetService implements FallbackFactory<MaintBudgetFeignService> {

        @Override
        public MaintBudgetFeignService create(Throwable throwable) {
            return new MaintBudgetFeignService() {
                @Override
                public Result add(@Valid @RequestBody MaintBudgetForm maintBudgetForm) {
                    log.error("MaintBudgetService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("MaintBudgetService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody MaintBudgetForm maintBudgetForm) {
                    log.error("MaintBudgetService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("MaintBudgetService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody MaintBudgetQueryForm maintBudgetQueryForm) {
                    log.error("MaintBudgetService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody MaintBudgetQueryForm maintBudgetQueryForm) {
                    log.error("MaintBudgetService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}