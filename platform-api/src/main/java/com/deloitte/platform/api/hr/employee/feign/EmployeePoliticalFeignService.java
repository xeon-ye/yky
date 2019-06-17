package com.deloitte.platform.api.hr.employee.feign;


import com.deloitte.platform.api.hr.employee.client.EmployeePoliticalClient;
import com.deloitte.platform.api.hr.employee.param.EmployeePoliticalQueryForm;
import com.deloitte.platform.api.hr.employee.vo.EmployeePoliticalForm;
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
 * @Description :   EmployeePolitical feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = EmployeePoliticalFeignService.HystrixEmployeePoliticalService.class,primary = false)
public interface EmployeePoliticalFeignService extends EmployeePoliticalClient {

    @Component
    @Slf4j
    class HystrixEmployeePoliticalService implements FallbackFactory<EmployeePoliticalFeignService> {

        @Override
        public EmployeePoliticalFeignService create(Throwable throwable) {
            return new EmployeePoliticalFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeePoliticalForm employeePoliticalForm) {
                    log.error("EmployeePoliticalService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeePoliticalService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeePoliticalForm employeePoliticalForm) {
                    log.error("EmployeePoliticalService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeePoliticalService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeePoliticalQueryForm employeePoliticalQueryForm) {
                    log.error("EmployeePoliticalService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeePoliticalQueryForm employeePoliticalQueryForm) {
                    log.error("EmployeePoliticalService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}