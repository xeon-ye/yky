package com.deloitte.platform.api.hr.ebs.feign;


import com.deloitte.platform.api.hr.ebs.client.EmployeeFamilyClient;
import com.deloitte.platform.api.hr.ebs.vo.EMployeeFamilyCheckForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :   EmployeeFamily feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeFamily-service", fallbackFactory = EmployeeFamilyFeignService.HystrixEmployeeFamilyService.class,primary = false)
public interface EmployeeFamilyFeignService extends EmployeeFamilyClient {

    @Component
    @Slf4j
    class HystrixEmployeeFamilyService implements FallbackFactory<EmployeeFamilyFeignService> {

        @Override
        public EmployeeFamilyFeignService create(Throwable throwable) {
            return new EmployeeFamilyFeignService() {
                @Override
                public Result add(@RequestParam String formList,@RequestParam Integer type ) {
                    log.error("EmployeeFamilyService add服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }


                @Override
                public Result getEmployeeFamilyByEmpCode(@RequestParam(required = false) String userCode) {
                    log.error("EmployeeFamilyService getEmployeeFamilyByEmpCode服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkApplyById(@Valid @RequestBody EMployeeFamilyCheckForm eMployeeFamilyCheckForm) {
                    log.error("EmployeeFamilyService checkApplyById服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}