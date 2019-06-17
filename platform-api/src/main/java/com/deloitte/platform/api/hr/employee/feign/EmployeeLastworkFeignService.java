package com.deloitte.platform.api.hr.employee.feign;


import com.deloitte.platform.api.hr.employee.client.EmployeeLastworkClient;
import com.deloitte.platform.api.hr.employee.param.EmployeeLastworkQueryForm;
import com.deloitte.platform.api.hr.employee.vo.EmployeeLastworkForm;
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
 * @Date : Create in 2019-04-03
 * @Description :   EmployeeLastwork feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = EmployeeLastworkFeignService.HystrixEmployeeLastworkService.class,primary = false)
public interface EmployeeLastworkFeignService extends EmployeeLastworkClient {

    @Component
    @Slf4j
    class HystrixEmployeeLastworkService implements FallbackFactory<EmployeeLastworkFeignService> {

        @Override
        public EmployeeLastworkFeignService create(Throwable throwable) {
            return new EmployeeLastworkFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeLastworkForm employeeLastworkForm) {
                    log.error("EmployeeLastworkService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeLastworkService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeLastworkForm employeeLastworkForm) {
                    log.error("EmployeeLastworkService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeLastworkService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeLastworkQueryForm employeeLastworkQueryForm) {
                    log.error("EmployeeLastworkService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeLastworkQueryForm employeeLastworkQueryForm) {
                    log.error("EmployeeLastworkService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}