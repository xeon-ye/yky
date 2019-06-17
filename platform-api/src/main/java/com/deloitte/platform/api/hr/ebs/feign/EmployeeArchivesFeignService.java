package com.deloitte.platform.api.hr.ebs.feign;


import com.deloitte.platform.api.hr.ebs.client.EmployeeArchivesClient;
import com.deloitte.platform.api.hr.ebs.param.EmployeeArchivesQueryForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeArchivesForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :   EmployeeArchives feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeArchives-service", fallbackFactory = EmployeeArchivesFeignService.HystrixEmployeeArchivesService.class,primary = false)
public interface EmployeeArchivesFeignService extends EmployeeArchivesClient {

    @Component
    @Slf4j
    class HystrixEmployeeArchivesService implements FallbackFactory<EmployeeArchivesFeignService> {

        @Override
        public EmployeeArchivesFeignService create(Throwable throwable) {
            return new EmployeeArchivesFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeArchivesForm employeeArchivesForm) {
                    log.error("EmployeeArchivesService add服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeArchivesService delete服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeArchivesForm employeeArchivesForm) {
                    log.error("EmployeeArchivesService update服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeArchivesService get服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeArchivesQueryForm employeeArchivesQueryForm) {
                    log.error("EmployeeArchivesService list服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeArchivesQueryForm employeeArchivesQueryForm) {
                    log.error("EmployeeArchivesService search服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}