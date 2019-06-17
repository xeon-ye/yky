package com.deloitte.platform.api.hr.employee_mes.feign;

import com.deloitte.platform.api.hr.employee_mes.client.EmployeeMesTeachClient;
import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesTeachQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesTeachForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesTeachResultForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-08
 * @Description :   EmployeeMesTeach feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeMesTeach-service", fallbackFactory = EmployeeMesTeachFeignService.HystrixEmployeeMesTeachService.class,primary = false)
public interface EmployeeMesTeachFeignService extends EmployeeMesTeachClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesTeachService implements FallbackFactory<EmployeeMesTeachFeignService> {

        @Override
        public EmployeeMesTeachFeignService create(Throwable throwable) {
            return new EmployeeMesTeachFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeMesTeachForm employeeMesTeachForm) {
                    log.error("EmployeeMesTeachService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeMesTeachService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable String id, @Valid @RequestBody EmployeeMesTeachResultForm teachResultForm) {
                    log.error("EmployeeMesTeachService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeMesTeachService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeMesTeachQueryForm employeeMesTeachQueryForm) {
                    log.error("EmployeeMesTeachService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeMesTeachQueryForm employeeMesTeachQueryForm) {
                    log.error("EmployeeMesTeachService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result saveOrUpdate(EmployeeMesTeachForm employeeMesTeachForm) {
                    return null;
                }

                @Override
                public Result publishToEbs(EmployeeMesTeachForm employeeMesTeachForm) {
                    return null;
                }
            };
        }
    }
}