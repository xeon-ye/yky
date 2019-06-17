package com.deloitte.platform.api.hr.employee_mes.feign;


import com.deloitte.platform.api.hr.employee_mes.client.EmployeeMesPilitClient;
import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesPilitQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesPilitForm;
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
 * @Description :   EmployeeMesPilit feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeMesPilit-service", fallbackFactory = EmployeeMesPilitFeignService.HystrixEmployeeMesPilitService.class,primary = false)
public interface EmployeeMesPilitFeignService extends EmployeeMesPilitClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesPilitService implements FallbackFactory<EmployeeMesPilitFeignService> {

        @Override
        public EmployeeMesPilitFeignService create(Throwable throwable) {
            return new EmployeeMesPilitFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeMesPilitForm employeeMesPilitForm) {
                    log.error("EmployeeMesPilitService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeMesPilitService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeMesPilitForm employeeMesPilitForm) {
                    log.error("EmployeeMesPilitService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeMesPilitService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeMesPilitQueryForm employeeMesPilitQueryForm) {
                    log.error("EmployeeMesPilitService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeMesPilitQueryForm employeeMesPilitQueryForm) {
                    log.error("EmployeeMesPilitService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}