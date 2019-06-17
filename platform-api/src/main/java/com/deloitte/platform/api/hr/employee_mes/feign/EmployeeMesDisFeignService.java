package com.deloitte.platform.api.hr.employee_mes.feign;


import com.deloitte.platform.api.hr.employee_mes.client.EmployeeMesDisClient;
import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesDisQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesDisForm;
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
 * @Date : Create in 2019-05-08
 * @Description :   EmployeeMesDis feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeMesDis-service", fallbackFactory = EmployeeMesDisFeignService.HystrixEmployeeMesDisService.class,primary = false)
public interface EmployeeMesDisFeignService extends EmployeeMesDisClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesDisService implements FallbackFactory<EmployeeMesDisFeignService> {

        @Override
        public EmployeeMesDisFeignService create(Throwable throwable) {
            return new EmployeeMesDisFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeMesDisForm employeeMesDisForm) {
                    log.error("EmployeeMesDisService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeMesDisService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeMesDisForm employeeMesDisForm) {
                    log.error("EmployeeMesDisService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeMesDisService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeMesDisQueryForm employeeMesDisQueryForm) {
                    log.error("EmployeeMesDisService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeMesDisQueryForm employeeMesDisQueryForm) {
                    log.error("EmployeeMesDisService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}