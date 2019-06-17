package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.EquipmentClient;
import com.deloitte.platform.api.project.param.EquipmentQueryForm;
import com.deloitte.platform.api.project.vo.EquipmentForm;
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
 * @Date : Create in 2019-04-24
 * @Description :   Equipment feign客户端
 * @Modified :
 */
@FeignClient(name = "equipment-service", fallbackFactory = EquipmentFeignService.HystrixEquipmentService.class,primary = false)
public interface EquipmentFeignService extends EquipmentClient {

    @Component
    @Slf4j
    class HystrixEquipmentService implements FallbackFactory<EquipmentFeignService> {

        @Override
        public EquipmentFeignService create(Throwable throwable) {
            return new EquipmentFeignService() {
                @Override
                public Result add(@Valid @RequestBody EquipmentForm equipmentForm) {
                    log.error("EquipmentService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EquipmentService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EquipmentForm equipmentForm) {
                    log.error("EquipmentService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EquipmentService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EquipmentQueryForm equipmentQueryForm) {
                    log.error("EquipmentService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EquipmentQueryForm equipmentQueryForm) {
                    log.error("EquipmentService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}