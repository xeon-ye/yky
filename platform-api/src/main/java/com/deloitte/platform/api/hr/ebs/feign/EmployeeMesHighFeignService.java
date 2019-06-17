package com.deloitte.platform.api.hr.ebs.feign;


import com.deloitte.platform.api.hr.ebs.client.EmployeeMesHighClient;
import com.deloitte.platform.api.hr.ebs.param.EmployeeMesHighQueryForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesHighForm;
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
 * @Description :   EmployeeMesHigh feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeMesHigh-service", fallbackFactory = EmployeeMesHighFeignService.HystrixEmployeeMesHighService.class,primary = false)
public interface EmployeeMesHighFeignService extends EmployeeMesHighClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesHighService implements FallbackFactory<EmployeeMesHighFeignService> {

        @Override
        public EmployeeMesHighFeignService create(Throwable throwable) {
            return new EmployeeMesHighFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeMesHighForm employeeMesHighForm) {
                    log.error("EmployeeMesHighService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeMesHighService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeMesHighForm employeeMesHighForm) {
                    log.error("EmployeeMesHighService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeMesHighService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeMesHighQueryForm employeeMesHighQueryForm) {
                    log.error("EmployeeMesHighService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeMesHighQueryForm employeeMesHighQueryForm) {
                    log.error("EmployeeMesHighService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result saveOrUpdate(EmployeeMesHighForm employeeMesHighForm) {
                    return null;
                }

                @Override
                public Result publishToEbs(EmployeeMesHighForm employeeMesHighForm) {
                    return null;
                }
            };
        }
    }
}