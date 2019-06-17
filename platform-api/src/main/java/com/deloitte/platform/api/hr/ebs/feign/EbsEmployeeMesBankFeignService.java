package com.deloitte.platform.api.hr.ebs.feign;

import com.deloitte.platform.api.hr.ebs.client.EbsEmployeeMesBankClient;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesBankForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author woo
 * @Title: EmployeeMesBankClient
 * @ProjectName platform
 * @Description: TODO
 * @date 14:27  2019/6/4
 */
@FeignClient(name = "hr-service", fallbackFactory = EbsEmployeeMesBankFeignService.HystrixEmployeeMesBankFeignService.class,primary = false)
public interface EbsEmployeeMesBankFeignService extends EbsEmployeeMesBankClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesBankFeignService implements FallbackFactory<EbsEmployeeMesBankFeignService> {

        @Override
        public EbsEmployeeMesBankFeignService create(Throwable throwable) {
            return new EbsEmployeeMesBankFeignService() {
                @Override
                public Result saveOrUpdate(@Valid @RequestBody EmployeeMesBankForm employeeMesBankForm) {
                    log.error("EmployeeMesBankFeignService saveOrUpdate服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}
