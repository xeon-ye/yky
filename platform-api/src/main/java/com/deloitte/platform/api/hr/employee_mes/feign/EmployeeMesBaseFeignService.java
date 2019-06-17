package com.deloitte.platform.api.hr.employee_mes.feign;


import com.deloitte.platform.api.hr.employee_mes.client.EmployeeMesBaseClient;
import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesBaseQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesBaseForm;
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
 * @Description :   EmployeeMesBase feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeMesBase-service", fallbackFactory = EmployeeMesBaseFeignService.HystrixEmployeeMesBaseService.class,primary = false)
public interface EmployeeMesBaseFeignService extends EmployeeMesBaseClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesBaseService implements FallbackFactory<EmployeeMesBaseFeignService> {

        @Override
        public EmployeeMesBaseFeignService create(Throwable throwable) {
            return new EmployeeMesBaseFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeMesBaseForm employeeMesBaseForm) {
                    log.error("EmployeeMesBaseService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeMesBaseService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeMesBaseForm employeeMesBaseForm) {
                    log.error("EmployeeMesBaseService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable String id) {
                    log.error("EmployeeMesBaseService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeMesBaseQueryForm employeeMesBaseQueryForm) {
                    log.error("EmployeeMesBaseService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeMesBaseQueryForm employeeMesBaseQueryForm) {
                    log.error("EmployeeMesBaseService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}