package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.FinanceInfoClient;
import com.deloitte.platform.api.contract.param.FinanceInfoQueryForm;
import com.deloitte.platform.api.contract.vo.FinanceInfoForm;
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
 * @Author : yangyq
 * @Date : Create in 2019-04-17
 * @Description :   FinanceInfo feign客户端
 * @Modified :
 */
@FeignClient(name = "financeInfo-service", fallbackFactory = FinanceInfoFeignService.HystrixFinanceInfoService.class,primary = false)
public interface FinanceInfoFeignService extends FinanceInfoClient {

    @Component
    @Slf4j
    class HystrixFinanceInfoService implements FallbackFactory<FinanceInfoFeignService> {

        @Override
        public FinanceInfoFeignService create(Throwable throwable) {
            return new FinanceInfoFeignService() {
                @Override
                public Result add(@Valid @RequestBody FinanceInfoForm financeInfoForm) {
                    log.error("FinanceInfoService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("FinanceInfoService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody FinanceInfoForm financeInfoForm) {
                    log.error("FinanceInfoService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("FinanceInfoService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody FinanceInfoQueryForm financeInfoQueryForm) {
                    log.error("FinanceInfoService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody FinanceInfoQueryForm financeInfoQueryForm) {
                    log.error("FinanceInfoService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}