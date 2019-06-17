package com.deloitte.platform.api.hr.employee_mes.feign;


import com.deloitte.platform.api.hr.employee_mes.client.EmployeeMesScienceClient;
import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesScienceQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesScienceForm;
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
 * @Author : woo
 * @Date : Create in 2019-06-05
 * @Description :   EmployeeMesScience feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeMesScience-service", fallbackFactory = EmployeeMesScienceFeignService.HystrixEmployeeMesScienceService.class,primary = false)
public interface EmployeeMesScienceFeignService extends EmployeeMesScienceClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesScienceService implements FallbackFactory<EmployeeMesScienceFeignService> {

        @Override
        public EmployeeMesScienceFeignService create(Throwable throwable) {
            return new EmployeeMesScienceFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeMesScienceForm employeeMesScienceForm) {
                    log.error("EmployeeMesScienceService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeMesScienceService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeMesScienceForm employeeMesScienceForm) {
                    log.error("EmployeeMesScienceService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeMesScienceService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeMesScienceQueryForm employeeMesScienceQueryForm) {
                    log.error("EmployeeMesScienceService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeMesScienceQueryForm employeeMesScienceQueryForm) {
                    log.error("EmployeeMesScienceService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}