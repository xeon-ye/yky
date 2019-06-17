package com.deloitte.platform.api.hr.employee_mes.feign;


import com.deloitte.platform.api.hr.employee_mes.client.EmployeeMesBankClient;
import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesBankQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesBankForm;
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
 * @Description :   EmployeeMesBank feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeMesBank-service", fallbackFactory = EmployeeMesBankFeignService.HystrixEmployeeMesBankService.class,primary = false)
public interface EmployeeMesBankFeignService extends EmployeeMesBankClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesBankService implements FallbackFactory<EmployeeMesBankFeignService> {

        @Override
        public EmployeeMesBankFeignService create(Throwable throwable) {
            return new EmployeeMesBankFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeMesBankForm employeeMesBankForm) {
                    log.error("EmployeeMesBankService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeMesBankService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeMesBankForm employeeMesBankForm) {
                    log.error("EmployeeMesBankService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeMesBankService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeMesBankQueryForm employeeMesBankQueryForm) {
                    log.error("EmployeeMesBankService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeMesBankQueryForm employeeMesBankQueryForm) {
                    log.error("EmployeeMesBankService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}