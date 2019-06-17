package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.EquipmentTransformClient;
import com.deloitte.platform.api.project.param.EquipmentTransformQueryForm;
import com.deloitte.platform.api.project.vo.EquipmentTransformForm;
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
 * @Description :   EquipmentTransform feign客户端
 * @Modified :
 */
@FeignClient(name = "equipmentTransform-service", fallbackFactory = EquipmentTransformFeignService.HystrixEquipmentTransformService.class,primary = false)
public interface EquipmentTransformFeignService extends EquipmentTransformClient {

    @Component
    @Slf4j
    class HystrixEquipmentTransformService implements FallbackFactory<EquipmentTransformFeignService> {

        @Override
        public EquipmentTransformFeignService create(Throwable throwable) {
            return new EquipmentTransformFeignService() {
                @Override
                public Result add(@Valid @RequestBody EquipmentTransformForm equipmentTransformForm) {
                    log.error("EquipmentTransformService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EquipmentTransformService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EquipmentTransformForm equipmentTransformForm) {
                    log.error("EquipmentTransformService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EquipmentTransformService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EquipmentTransformQueryForm equipmentTransformQueryForm) {
                    log.error("EquipmentTransformService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EquipmentTransformQueryForm equipmentTransformQueryForm) {
                    log.error("EquipmentTransformService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}