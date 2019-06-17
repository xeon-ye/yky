package com.deloitte.platform.api.hr.employee.feign;


import com.deloitte.platform.api.hr.employee.client.EmployeeWagesClient;
import com.deloitte.platform.api.hr.employee.param.EmployeeWagesQueryForm;
import com.deloitte.platform.api.hr.employee.vo.EmployeeWagesForm;
import com.deloitte.platform.api.hr.employee.vo.EmployeeWagesInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author : woo
 * @Date : Create in 2019-05-20
 * @Description :   EmployeeWages feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeWages-service", fallbackFactory = EmployeeWagesFeignService.HystrixEmployeeWagesService.class,primary = false)
public interface EmployeeWagesFeignService extends EmployeeWagesClient {

    @Component
    @Slf4j
    class HystrixEmployeeWagesService implements FallbackFactory<EmployeeWagesFeignService> {

        @Override
        public EmployeeWagesFeignService create(Throwable throwable) {
            return new EmployeeWagesFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeWagesForm employeeWagesForm) {
                    log.error("EmployeeWagesService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeWagesService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeWagesForm employeeWagesForm) {
                    log.error("EmployeeWagesService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeWagesService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeWagesQueryForm employeeWagesQueryForm) {
                    log.error("EmployeeWagesService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeWagesQueryForm employeeWagesQueryForm) {
                    log.error("EmployeeWagesService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<EmployeeWagesInfoVo> getUserWages(@Valid @RequestBody  EmployeeWagesQueryForm employeeWagesQueryForm) {
                    log.error("EmployeeWagesService getUserWages服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}