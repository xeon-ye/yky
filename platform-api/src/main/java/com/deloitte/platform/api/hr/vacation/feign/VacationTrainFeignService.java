package com.deloitte.platform.api.hr.vacation.feign;


import com.deloitte.platform.api.hr.vacation.client.VacationTrainClient;
import com.deloitte.platform.api.hr.vacation.param.VacationTrainQueryForm;
import com.deloitte.platform.api.hr.vacation.vo.VacationTrainForm;
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
 * @Description :   VacationTrain feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = VacationTrainFeignService.HystrixVacationTrainService.class,primary = false)
public interface VacationTrainFeignService extends VacationTrainClient {

    @Component
    @Slf4j
    class HystrixVacationTrainService implements FallbackFactory<VacationTrainFeignService> {

        @Override
        public VacationTrainFeignService create(Throwable throwable) {
            return new VacationTrainFeignService() {
                @Override
                public Result add(@Valid @RequestBody VacationTrainForm vacationTrainForm) {
                    log.error("VacationTrainService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("VacationTrainService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody VacationTrainForm vacationTrainForm) {
                    log.error("VacationTrainService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("VacationTrainService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody VacationTrainQueryForm vacationTrainQueryForm) {
                    log.error("VacationTrainService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody VacationTrainQueryForm vacationTrainQueryForm) {
                    log.error("VacationTrainService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}