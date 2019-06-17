package com.deloitte.platform.api.hr.employee_mes.feign;


import com.deloitte.platform.api.hr.employee_mes.client.EmployeeMesLogyClient;
import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesLogyQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesLogyForm;
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
 * @Date : Create in 2019-05-18
 * @Description :   EmployeeMesLogy feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeMesLogy-service", fallbackFactory = EmployeeMesLogyFeignService.HystrixEmployeeMesLogyService.class,primary = false)
public interface EmployeeMesLogyFeignService extends EmployeeMesLogyClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesLogyService implements FallbackFactory<EmployeeMesLogyFeignService> {

        @Override
        public EmployeeMesLogyFeignService create(Throwable throwable) {
            return new EmployeeMesLogyFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeMesLogyForm employeeMesLogyForm) {
                    log.error("EmployeeMesLogyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeMesLogyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeMesLogyForm employeeMesLogyForm) {
                    log.error("EmployeeMesLogyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeMesLogyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeMesLogyQueryForm employeeMesLogyQueryForm) {
                    log.error("EmployeeMesLogyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeMesLogyQueryForm employeeMesLogyQueryForm) {
                    log.error("EmployeeMesLogyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result saveOrUpdate(EmployeeMesLogyForm employeeMesLogyForm) {
                    return null;
                }

                @Override
                public Result publishToEbs(EmployeeMesLogyForm employeeMesLogyForm) {
                    return null;
                }
            };
        }
    }
}