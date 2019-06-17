package com.deloitte.platform.api.hr.vacation.feign;


import com.deloitte.platform.api.hr.vacation.client.VacationtypeExplainClient;
import com.deloitte.platform.api.hr.vacation.param.VacationtypeExplainQueryForm;
import com.deloitte.platform.api.hr.vacation.vo.VacationtypeExplainForm;
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
 * @Date : Create in 2019-04-17
 * @Description :   VacationtypeExplain feign客户端
 * @Modified :
 */
@FeignClient(name = "vacationtypeExplain-service", fallbackFactory = VacationtypeExplainFeignService.HystrixVacationtypeExplainService.class,primary = false)
public interface VacationtypeExplainFeignService extends VacationtypeExplainClient {

    @Component
    @Slf4j
    class HystrixVacationtypeExplainService implements FallbackFactory<VacationtypeExplainFeignService> {

        @Override
        public VacationtypeExplainFeignService create(Throwable throwable) {
            return new VacationtypeExplainFeignService() {
                @Override
                public Result add(@Valid @RequestBody VacationtypeExplainForm vacationtypeExplainForm) {
                    log.error("VacationtypeExplainService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("VacationtypeExplainService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody VacationtypeExplainForm vacationtypeExplainForm) {
                    log.error("VacationtypeExplainService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("VacationtypeExplainService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody VacationtypeExplainQueryForm vacationtypeExplainQueryForm) {
                    log.error("VacationtypeExplainService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody VacationtypeExplainQueryForm vacationtypeExplainQueryForm) {
                    log.error("VacationtypeExplainService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}