package com.deloitte.platform.api.hr.ebs.feign;


import com.deloitte.platform.api.hr.ebs.client.EmployeeMesZpcpClient;
import com.deloitte.platform.api.hr.ebs.param.EmployeeMesZpcpQueryForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesZpcpForm;
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
 * @Description :   EmployeeMesZpcp feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeMesZpcp-service", fallbackFactory = EmployeeMesZpcpFeignService.HystrixEmployeeMesZpcpService.class,primary = false)
public interface EmployeeMesZpcpFeignService extends EmployeeMesZpcpClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesZpcpService implements FallbackFactory<EmployeeMesZpcpFeignService> {

        @Override
        public EmployeeMesZpcpFeignService create(Throwable throwable) {
            return new EmployeeMesZpcpFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeMesZpcpForm employeeMesZpcpForm) {
                    log.error("EmployeeMesZpcpService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeMesZpcpService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeMesZpcpForm employeeMesZpcpForm) {
                    log.error("EmployeeMesZpcpService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeMesZpcpService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeMesZpcpQueryForm employeeMesZpcpQueryForm) {
                    log.error("EmployeeMesZpcpService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeMesZpcpQueryForm employeeMesZpcpQueryForm) {
                    log.error("EmployeeMesZpcpService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result saveOrUpdate(EmployeeMesZpcpForm employeeMesZpcpForm) {
                    return null;
                }

                @Override
                public Result publishToEbs(EmployeeMesZpcpForm employeeMesZpcpForm) {
                    return null;
                }
            };
        }
    }
}