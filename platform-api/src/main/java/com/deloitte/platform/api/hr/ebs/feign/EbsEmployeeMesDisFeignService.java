package com.deloitte.platform.api.hr.ebs.feign;

import com.deloitte.platform.api.hr.ebs.client.EbsEmployeeMesDisClient;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesDisForm;
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
 * @Title: EbsEmployeeMesDisClient
 * @ProjectName platform
 * @Description: TODO
 * @date 14:27  2019/6/4
 */
@FeignClient(name = "hr-service", fallbackFactory = EbsEmployeeMesDisFeignService.HystrixEbsEmployeeMesDisFeignService.class,primary = false)
public interface EbsEmployeeMesDisFeignService extends EbsEmployeeMesDisClient {

    @Component
    @Slf4j
    class HystrixEbsEmployeeMesDisFeignService implements FallbackFactory<EbsEmployeeMesDisFeignService> {

        @Override
        public EbsEmployeeMesDisFeignService create(Throwable throwable) {
            return new EbsEmployeeMesDisFeignService() {
                @Override
                public Result saveOrUpdate(@Valid @RequestBody EmployeeMesDisForm employeeMesDisForm) {
                    log.error("EbsEmployeeMesDisFeignService saveOrUpdate服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}
