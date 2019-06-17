package com.deloitte.platform.api.hr.vacation.feign;


import com.deloitte.platform.api.hr.vacation.client.VacationTrainCountClient;
import com.deloitte.platform.api.hr.vacation.param.VacationTrainCountQueryForm;
import com.deloitte.platform.api.hr.vacation.vo.VacationTrainCountForm;
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
 * @Description :   VacationTrainCount feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = VacationTrainCountFeignService.HystrixVacationTrainCountService.class,primary = false)
public interface VacationTrainCountFeignService extends VacationTrainCountClient {

    @Component
    @Slf4j
    class HystrixVacationTrainCountService implements FallbackFactory<VacationTrainCountFeignService> {

        @Override
        public VacationTrainCountFeignService create(Throwable throwable) {
            return new VacationTrainCountFeignService() {
                @Override
                public Result add(@Valid @RequestBody VacationTrainCountForm vacationTrainCountForm) {
                    log.error("VacationTrainCountService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("VacationTrainCountService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody VacationTrainCountForm vacationTrainCountForm) {
                    log.error("VacationTrainCountService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("VacationTrainCountService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody VacationTrainCountQueryForm vacationTrainCountQueryForm) {
                    log.error("VacationTrainCountService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody VacationTrainCountQueryForm vacationTrainCountQueryForm) {
                    log.error("VacationTrainCountService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}