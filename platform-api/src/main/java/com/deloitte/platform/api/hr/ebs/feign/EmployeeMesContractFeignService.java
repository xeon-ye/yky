package com.deloitte.platform.api.hr.ebs.feign;


import com.deloitte.platform.api.hr.ebs.client.EmployeeMesContractClient;
import com.deloitte.platform.api.hr.ebs.param.EmployeeMesContractQueryForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesContractForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesContractVo;
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
 * @Description :   EmployeeMesContract feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeMesContract-service", fallbackFactory = EmployeeMesContractFeignService.HystrixEmployeeMesContractService.class,primary = false)
public interface EmployeeMesContractFeignService extends EmployeeMesContractClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesContractService implements FallbackFactory<EmployeeMesContractFeignService> {

        @Override
        public EmployeeMesContractFeignService create(Throwable throwable) {
            return new EmployeeMesContractFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeMesContractForm employeeMesContractForm) {
                    log.error("EmployeeMesContractService add服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeMesContractService delete服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeMesContractForm employeeMesContractForm) {
                    log.error("EmployeeMesContractService update服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeMesContractService get服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<EmployeeMesContractVo> getEmployeeMesContractByEmpCode(String userCode) {
                    log.error("EmployeeMesContractService getEmployeeMesContractByEmpCode服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}