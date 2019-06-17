package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.EvaluateClient;
import com.deloitte.platform.api.contract.param.EvaluateQueryForm;
import com.deloitte.platform.api.contract.vo.EvaluateForm;
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
 * @Author : hemingzheng
 * @Date : Create in 2019-04-25
 * @Description :   Evaluate feign客户端
 * @Modified :
 */
@FeignClient(name = "evaluate-service", fallbackFactory = EvaluateFeignService.HystrixEvaluateService.class,primary = false)
public interface EvaluateFeignService extends EvaluateClient {

    @Component
    @Slf4j
    class HystrixEvaluateService implements FallbackFactory<EvaluateFeignService> {

        @Override
        public EvaluateFeignService create(Throwable throwable) {
            return new EvaluateFeignService() {
                @Override
                public Result add(@Valid @RequestBody EvaluateForm evaluateForm) {
                    log.error("EvaluateService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EvaluateService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EvaluateForm evaluateForm) {
                    log.error("EvaluateService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EvaluateService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EvaluateQueryForm evaluateQueryForm) {
                    log.error("EvaluateService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EvaluateQueryForm evaluateQueryForm) {
                    log.error("EvaluateService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}