package com.deloitte.platform.api.hr.ebs.feign;

import com.deloitte.platform.api.hr.ebs.client.EbsEmployeeMesLearnClient;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesLearnForm;
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
 * @Title: EmployeeMesLearnClient
 * @ProjectName platform
 * @Description: TODO
 * @date 14:27  2019/6/4
 */
@FeignClient(name = "hr-service", fallbackFactory = EbsEmployeeMesLearnFeignService.HystrixEmployeeMesLearnFeignService.class,primary = false)
public interface EbsEmployeeMesLearnFeignService extends EbsEmployeeMesLearnClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesLearnFeignService implements FallbackFactory<EbsEmployeeMesLearnFeignService> {

        @Override
        public EbsEmployeeMesLearnFeignService create(Throwable throwable) {
            return new EbsEmployeeMesLearnFeignService() {
                @Override
                public Result saveOrUpdate(@Valid @RequestBody EmployeeMesLearnForm employeeMesLearnForm) {
                    log.error("EmployeeMesLearnFeignService saveOrUpdate服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}
