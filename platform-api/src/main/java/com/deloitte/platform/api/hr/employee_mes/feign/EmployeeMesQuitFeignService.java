package com.deloitte.platform.api.hr.employee_mes.feign;


import com.deloitte.platform.api.hr.employee_mes.client.EmployeeMesQuitClient;
import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesQuitQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesQuitForm;
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
 * @Date : Create in 2019-06-04
 * @Description :   EmployeeMesQuit feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeMesQuit-service", fallbackFactory = EmployeeMesQuitFeignService.HystrixEmployeeMesQuitService.class,primary = false)
public interface EmployeeMesQuitFeignService extends EmployeeMesQuitClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesQuitService implements FallbackFactory<EmployeeMesQuitFeignService> {

        @Override
        public EmployeeMesQuitFeignService create(Throwable throwable) {
            return new EmployeeMesQuitFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeMesQuitForm employeeMesQuitForm) {
                    log.error("EmployeeMesQuitService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeMesQuitService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeMesQuitForm employeeMesQuitForm) {
                    log.error("EmployeeMesQuitService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeMesQuitService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeMesQuitQueryForm employeeMesQuitQueryForm) {
                    log.error("EmployeeMesQuitService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeMesQuitQueryForm employeeMesQuitQueryForm) {
                    log.error("EmployeeMesQuitService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}