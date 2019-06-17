package com.deloitte.platform.api.hr.ebs.feign;


import com.deloitte.platform.api.hr.ebs.client.EmployeeAbroadClient;
import com.deloitte.platform.api.hr.ebs.param.EmployeeAbroadQueryForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeAbroadForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeAbroadVo;
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
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :   EmployeeAbroad feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeAbroad-service", fallbackFactory = EmployeeAbroadFeignService.HystrixEmployeeAbroadService.class,primary = false)
public interface EmployeeAbroadFeignService extends EmployeeAbroadClient {

    @Component
    @Slf4j
    class HystrixEmployeeAbroadService implements FallbackFactory<EmployeeAbroadFeignService> {

        @Override
        public EmployeeAbroadFeignService create(Throwable throwable) {
            return new EmployeeAbroadFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeAbroadForm employeeAbroadForm) {
                    log.error("EmployeeAbroadService add服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeAbroadService delete服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeAbroadForm employeeAbroadForm) {
                    log.error("EmployeeAbroadService update服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeAbroadService get服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeAbroadQueryForm employeeAbroadQueryForm) {
                    log.error("EmployeeAbroadService list服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeAbroadQueryForm employeeAbroadQueryForm) {
                    log.error("EmployeeAbroadService search服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<EmployeeAbroadVo> getEmployeeAbroadByEmpCode(String userCode) {
                    log.error("EmployeeAbroadService getEmployeeAbroadByEmpCode服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}