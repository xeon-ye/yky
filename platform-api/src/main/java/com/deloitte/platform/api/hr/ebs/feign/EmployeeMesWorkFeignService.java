package com.deloitte.platform.api.hr.ebs.feign;


import com.deloitte.platform.api.hr.ebs.client.EmployeeMesWorkClient;
import com.deloitte.platform.api.hr.ebs.param.EmployeeMesWorkQueryForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesWorkForm;
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
 * @Description :   EmployeeMesWork feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeMesWork-service", fallbackFactory = EmployeeMesWorkFeignService.HystrixEmployeeMesWorkService.class,primary = false)
public interface EmployeeMesWorkFeignService extends EmployeeMesWorkClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesWorkService implements FallbackFactory<EmployeeMesWorkFeignService> {

        @Override
        public EmployeeMesWorkFeignService create(Throwable throwable) {
            return new EmployeeMesWorkFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeMesWorkForm employeeMesWorkForm) {
                    log.error("EmployeeMesWorkService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeMesWorkService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeMesWorkForm employeeMesWorkForm) {
                    log.error("EmployeeMesWorkService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeMesWorkService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeMesWorkQueryForm employeeMesWorkQueryForm) {
                    log.error("EmployeeMesWorkService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeMesWorkQueryForm employeeMesWorkQueryForm) {
                    log.error("EmployeeMesWorkService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result saveOrUpdate(EmployeeMesWorkForm employeeMesWorkForm) {
                    return null;
                }

                @Override
                public Result publishToEbs(EmployeeMesWorkForm employeeMesWorkForm) {
                    return null;
                }
            };
        }
    }
}