package com.deloitte.platform.api.hr.employee_mes.feign;


import com.deloitte.platform.api.hr.employee_mes.client.EmployeeMesStrationClient;
import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesStrationQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesStrationForm;
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
 * @Date : Create in 2019-05-14
 * @Description :   EmployeeMesStration feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeMesStration-service", fallbackFactory = EmployeeMesStrationFeignService.HystrixEmployeeMesStrationService.class,primary = false)
public interface EmployeeMesStrationFeignService extends EmployeeMesStrationClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesStrationService implements FallbackFactory<EmployeeMesStrationFeignService> {

        @Override
        public EmployeeMesStrationFeignService create(Throwable throwable) {
            return new EmployeeMesStrationFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeMesStrationForm employeeMesStrationForm) {
                    log.error("EmployeeMesStrationService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeMesStrationService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeMesStrationForm employeeMesStrationForm) {
                    log.error("EmployeeMesStrationService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeMesStrationService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeMesStrationQueryForm employeeMesStrationQueryForm) {
                    log.error("EmployeeMesStrationService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeMesStrationQueryForm employeeMesStrationQueryForm) {
                    log.error("EmployeeMesStrationService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}