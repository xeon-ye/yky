package com.deloitte.platform.api.hr.ebs.feign;


import com.deloitte.platform.api.hr.ebs.client.EmployeeMesAwardClient;
import com.deloitte.platform.api.hr.ebs.param.EmployeeMesAwardQueryForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesAwardForm;
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
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :   EmployeeMesAward feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeMesAward-service", fallbackFactory = EmployeeMesAwardFeignService.HystrixEmployeeMesAwardService.class,primary = false)
public interface EmployeeMesAwardFeignService extends EmployeeMesAwardClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesAwardService implements FallbackFactory<EmployeeMesAwardFeignService> {

        @Override
        public EmployeeMesAwardFeignService create(Throwable throwable) {
            return new EmployeeMesAwardFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeMesAwardForm employeeMesAwardForm) {
                    log.error("EmployeeMesAwardService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeMesAwardService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeMesAwardForm employeeMesAwardForm) {
                    log.error("EmployeeMesAwardService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeMesAwardService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeMesAwardQueryForm employeeMesAwardQueryForm) {
                    log.error("EmployeeMesAwardService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeMesAwardQueryForm employeeMesAwardQueryForm) {
                    log.error("EmployeeMesAwardService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result saveOrUpdate(EmployeeMesAwardForm employeeMesAwardForm) {
                    return null;
                }

                @Override
                public Result publishToEbs(EmployeeMesAwardForm employeeMesAwardForm) {
                    return null;
                }
            };
        }
    }
}