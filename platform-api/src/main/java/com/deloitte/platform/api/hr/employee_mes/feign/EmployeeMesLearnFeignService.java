package com.deloitte.platform.api.hr.employee_mes.feign;


import com.deloitte.platform.api.hr.employee_mes.client.EmployeeMesLearnClient;
import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesLearnQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesLearnForm;
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
 * @Description :   EmployeeMesLearn feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeMesLearn-service", fallbackFactory = EmployeeMesLearnFeignService.HystrixEmployeeMesLearnService.class,primary = false)
public interface EmployeeMesLearnFeignService extends EmployeeMesLearnClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesLearnService implements FallbackFactory<EmployeeMesLearnFeignService> {

        @Override
        public EmployeeMesLearnFeignService create(Throwable throwable) {
            return new EmployeeMesLearnFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeMesLearnForm employeeMesLearnForm) {
                    log.error("EmployeeMesLearnService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeMesLearnService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeMesLearnForm employeeMesLearnForm) {
                    log.error("EmployeeMesLearnService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeMesLearnService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeMesLearnQueryForm employeeMesLearnQueryForm) {
                    log.error("EmployeeMesLearnService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeMesLearnQueryForm employeeMesLearnQueryForm) {
                    log.error("EmployeeMesLearnService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}