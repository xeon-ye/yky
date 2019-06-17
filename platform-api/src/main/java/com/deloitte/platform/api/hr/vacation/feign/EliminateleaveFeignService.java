package com.deloitte.platform.api.hr.vacation.feign;

import com.deloitte.platform.api.hr.vacation.client.EliminateleaveClient;
import com.deloitte.platform.api.hr.vacation.param.EliminateleaveQueryForm;
import com.deloitte.platform.api.hr.vacation.vo.EliminateleaveForm;
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
 * @Date : Create in 2019-04-01
 * @Description :   Eliminateleave feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = EliminateleaveFeignService.HystrixEliminateleaveService.class,primary = false)
public interface EliminateleaveFeignService extends EliminateleaveClient {

    @Component
    @Slf4j
    class HystrixEliminateleaveService implements FallbackFactory<EliminateleaveFeignService> {

        @Override
        public EliminateleaveFeignService create(Throwable throwable) {
            return new EliminateleaveFeignService() {
                @Override
                public Result add(@Valid @RequestBody EliminateleaveForm eliminateleaveForm) {
                    log.error("EliminateleaveService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EliminateleaveService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EliminateleaveForm eliminateleaveForm) {
                    log.error("EliminateleaveService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EliminateleaveService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EliminateleaveQueryForm eliminateleaveQueryForm) {
                    log.error("EliminateleaveService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EliminateleaveQueryForm eliminateleaveQueryForm) {
                    log.error("EliminateleaveService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}