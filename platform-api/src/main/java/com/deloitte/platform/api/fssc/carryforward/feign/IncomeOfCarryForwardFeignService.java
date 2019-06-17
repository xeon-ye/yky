package com.deloitte.platform.api.fssc.carryforward.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.carryforward.client.IncomeOfCarryForwardClient;
import com.deloitte.platform.api.fssc.carryforward.param.IncomeOfCarryForwardQueryForm;
import com.deloitte.platform.api.fssc.carryforward.vo.IncomeOfCarryForwardForm;
import com.deloitte.platform.api.fssc.carryforward.vo.IncomeOfCarryForwardVo;
import com.deloitte.platform.api.fssc.performance.client.PerformanceIndexClient;
import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexVo;
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
import java.util.List;

@FeignClient(name = "service-fssc", fallbackFactory = com.deloitte.platform.api.fssc.carryforward.feign.IncomeOfCarryForwardFeignService.HystrixPerformanceIndexService.class,primary = false)
public interface IncomeOfCarryForwardFeignService extends IncomeOfCarryForwardClient {

    @Component
    @Slf4j
    class HystrixPerformanceIndexService implements FallbackFactory<com.deloitte.platform.api.fssc.carryforward.feign.IncomeOfCarryForwardFeignService> {

        @Override
        public com.deloitte.platform.api.fssc.carryforward.feign.IncomeOfCarryForwardFeignService create(Throwable throwable) {
            return new  com.deloitte.platform.api.fssc.carryforward.feign.IncomeOfCarryForwardFeignService() {

                @Override
                public Result add(@Valid @ModelAttribute IncomeOfCarryForwardForm incomeOfCarryForwardForm) {
                    log.error("IncomeOfCarryForwardFeignService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("IncomeOfCarryForwardFeignService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable(value="id") long id, @Valid @RequestBody IncomeOfCarryForwardForm incomeOfCarryForwardForm) {
                    log.error("IncomeOfCarryForwardFeignService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }



                @Override
                public Result<List<IncomeOfCarryForwardVo>> list(@Valid @RequestBody IncomeOfCarryForwardQueryForm incomeOfCarryForwardQueryForm) {
                    log.error("IncomeOfCarryForwardFeignService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                public  Result delete(@PathVariable(value="id") long id){
                    log.error("IncomeOfCarryForwardFeignService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                public  Result<IPage<IncomeOfCarryForwardVo>> search(@Valid @RequestBody IncomeOfCarryForwardQueryForm incomeOfCarryForwardQueryForm){
                    log.error("IncomeOfCarryForwardFeignService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}
