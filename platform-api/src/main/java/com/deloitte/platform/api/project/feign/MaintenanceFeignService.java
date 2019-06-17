package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.MaintenanceClient;
import com.deloitte.platform.api.project.param.MaintenanceQueryForm;
import com.deloitte.platform.api.project.vo.MaintenanceForm;
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
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :   Maintenance feign客户端
 * @Modified :
 */
@FeignClient(name = "maintenance-service", fallbackFactory = MaintenanceFeignService.HystrixMaintenanceService.class,primary = false)
public interface MaintenanceFeignService extends MaintenanceClient {

    @Component
    @Slf4j
    class HystrixMaintenanceService implements FallbackFactory<MaintenanceFeignService> {

        @Override
        public MaintenanceFeignService create(Throwable throwable) {
            return new MaintenanceFeignService() {
                @Override
                public Result add(@Valid @RequestBody MaintenanceForm maintenanceForm) {
                    log.error("MaintenanceService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("MaintenanceService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody MaintenanceForm maintenanceForm) {
                    log.error("MaintenanceService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("MaintenanceService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody MaintenanceQueryForm maintenanceQueryForm) {
                    log.error("MaintenanceService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody MaintenanceQueryForm maintenanceQueryForm) {
                    log.error("MaintenanceService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}