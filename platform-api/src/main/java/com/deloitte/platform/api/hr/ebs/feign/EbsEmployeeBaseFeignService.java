package com.deloitte.platform.api.hr.ebs.feign;

import com.deloitte.platform.api.hr.ebs.client.EbsEmployeeBaseClient;
import com.deloitte.platform.api.hr.employee.vo.EmployeeBaseForm;
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
 * @Title: EbsEmployeeBaseClient
 * @ProjectName platform
 * @Description: TODO
 * @date 14:27  2019/6/4
 */
@FeignClient(name = "hr-service", fallbackFactory = EbsEmployeeBaseFeignService.HystrixEbsEmployeeBaseFeignService.class,primary = false)
public interface EbsEmployeeBaseFeignService extends EbsEmployeeBaseClient {

    @Component
    @Slf4j
    class HystrixEbsEmployeeBaseFeignService implements FallbackFactory<EbsEmployeeBaseFeignService> {

        @Override
        public EbsEmployeeBaseFeignService create(Throwable throwable) {
            return new EbsEmployeeBaseFeignService() {
                @Override
                public Result saveOrUpdate(@Valid @RequestBody EmployeeBaseForm EmployeeBaseForm) {
                    log.error("EbsEmployeeBaseFeignService saveOrUpdate服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result publishToEbs(@Valid @RequestBody EmployeeBaseForm employeeBaseForm) {
                    log.error("EbsEmployeeBaseFeignService publishToEbs服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}
