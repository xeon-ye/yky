package com.deloitte.platform.api.hr.employee.feign;


import com.deloitte.platform.api.hr.employee.client.EmployeeTeachClient;
import com.deloitte.platform.api.hr.employee.param.EmployeeTeachQueryForm;
import com.deloitte.platform.api.hr.employee.vo.EmployeeTeachForm;
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
 * @Description :   EmployeeTeach feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = EmployeeTeachFeignService.HystrixEmployeeTeachService.class,primary = false)
public interface EmployeeTeachFeignService extends EmployeeTeachClient {

    @Component
    @Slf4j
    class HystrixEmployeeTeachService implements FallbackFactory<EmployeeTeachFeignService> {

        @Override
        public EmployeeTeachFeignService create(Throwable throwable) {
            return new EmployeeTeachFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeTeachForm employeeTeachForm) {
                    log.error("EmployeeTeachService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeTeachService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeTeachForm employeeTeachForm) {
                    log.error("EmployeeTeachService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeTeachService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeTeachQueryForm employeeTeachQueryForm) {
                    log.error("EmployeeTeachService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeTeachQueryForm employeeTeachQueryForm) {
                    log.error("EmployeeTeachService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}