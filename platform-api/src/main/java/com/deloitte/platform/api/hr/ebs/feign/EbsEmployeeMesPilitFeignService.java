package com.deloitte.platform.api.hr.ebs.feign;

import com.deloitte.platform.api.hr.ebs.client.EbsEmployeeMesPilitClient;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesPilitForm;
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
 * @Title: EbsEmployeeMesPilitClient
 * @ProjectName platform
 * @Description: TODO
 * @date 14:27  2019/6/4
 */
@FeignClient(name = "hr-service", fallbackFactory = EbsEmployeeMesPilitFeignService.HystrixEbsEmployeeMesPilitFeignService.class,primary = false)
public interface EbsEmployeeMesPilitFeignService extends EbsEmployeeMesPilitClient {

    @Component
    @Slf4j
    class HystrixEbsEmployeeMesPilitFeignService implements FallbackFactory<EbsEmployeeMesPilitFeignService> {

        @Override
        public EbsEmployeeMesPilitFeignService create(Throwable throwable) {
            return new EbsEmployeeMesPilitFeignService() {
                @Override
                public Result saveOrUpdate(@Valid @RequestBody EmployeeMesPilitForm employeeMesPilitForm) {
                    log.error("EbsEmployeeMesPilitFeignService saveOrUpdate服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}
