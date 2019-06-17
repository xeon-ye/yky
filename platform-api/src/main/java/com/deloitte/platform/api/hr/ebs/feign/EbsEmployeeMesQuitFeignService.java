package com.deloitte.platform.api.hr.ebs.feign;

import com.deloitte.platform.api.hr.ebs.client.EbsEmployeeMesQuitClient;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesQuitForm;
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
 * @Title: EbsEmployeeMesQuitClient
 * @ProjectName platform
 * @Description: TODO
 * @date 14:27  2019/6/4
 */
@FeignClient(name = "hr-service", fallbackFactory = EbsEmployeeMesQuitFeignService.HystrixEbsEmployeeMesQuitFeignService.class,primary = false)
public interface EbsEmployeeMesQuitFeignService extends EbsEmployeeMesQuitClient {

    @Component
    @Slf4j
    class HystrixEbsEmployeeMesQuitFeignService implements FallbackFactory<EbsEmployeeMesQuitFeignService> {

        @Override
        public EbsEmployeeMesQuitFeignService create(Throwable throwable) {
            return new EbsEmployeeMesQuitFeignService() {
                @Override
                public Result saveOrUpdate(@Valid @RequestBody EmployeeMesQuitForm employeeMesQuitForm) {
                    log.error("EbsEmployeeMesQuitFeignService saveOrUpdate服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}
