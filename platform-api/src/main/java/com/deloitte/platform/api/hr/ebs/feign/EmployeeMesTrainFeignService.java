package com.deloitte.platform.api.hr.ebs.feign;


import com.deloitte.platform.api.hr.ebs.client.EmployeeMesTrainClient;
import com.deloitte.platform.api.hr.ebs.param.EmployeeMesTrainQueryForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesTrainForm;
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
 * @Description :   EmployeeMesTrain feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeMesTrain-service", fallbackFactory = EmployeeMesTrainFeignService.HystrixEmployeeMesTrainService.class,primary = false)
public interface EmployeeMesTrainFeignService extends EmployeeMesTrainClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesTrainService implements FallbackFactory<EmployeeMesTrainFeignService> {

        @Override
        public EmployeeMesTrainFeignService create(Throwable throwable) {
            return new EmployeeMesTrainFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeMesTrainForm employeeMesTrainForm) {
                    log.error("EmployeeMesTrainService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeMesTrainService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeMesTrainForm employeeMesTrainForm) {
                    log.error("EmployeeMesTrainService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeMesTrainService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeMesTrainQueryForm employeeMesTrainQueryForm) {
                    log.error("EmployeeMesTrainService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeMesTrainQueryForm employeeMesTrainQueryForm) {
                    log.error("EmployeeMesTrainService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result saveOrUpdate(EmployeeMesTrainForm employeeMesTrainForm) {
                    return null;
                }

                @Override
                public Result publishToEbs(EmployeeMesTrainForm employeeMesTrainForm) {
                    return null;
                }
            };
        }
    }
}