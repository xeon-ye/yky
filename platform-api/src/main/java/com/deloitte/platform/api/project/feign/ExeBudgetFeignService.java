package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ExeBudgetClient;
import com.deloitte.platform.api.project.param.ExeBudgetQueryForm;
import com.deloitte.platform.api.project.vo.ExeBudgetForm;
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
 * @Description :   ExeBudget feign客户端
 * @Modified :
 */
@FeignClient(name = "exeBudget-service", fallbackFactory = ExeBudgetFeignService.HystrixExeBudgetService.class,primary = false)
public interface ExeBudgetFeignService extends ExeBudgetClient {

    @Component
    @Slf4j
    class HystrixExeBudgetService implements FallbackFactory<ExeBudgetFeignService> {

        @Override
        public ExeBudgetFeignService create(Throwable throwable) {
            return new ExeBudgetFeignService() {
                @Override
                public Result add(@Valid @RequestBody ExeBudgetForm exeBudgetForm) {
                    log.error("ExeBudgetService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ExeBudgetService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ExeBudgetForm exeBudgetForm) {
                    log.error("ExeBudgetService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ExeBudgetService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ExeBudgetQueryForm exeBudgetQueryForm) {
                    log.error("ExeBudgetService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ExeBudgetQueryForm exeBudgetQueryForm) {
                    log.error("ExeBudgetService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}