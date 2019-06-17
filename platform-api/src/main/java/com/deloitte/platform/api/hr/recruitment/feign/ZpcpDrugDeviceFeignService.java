package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.ZpcpDrugDeviceClient;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpDrugDeviceQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpDrugDeviceForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :   ZpcpDrugDevice feign客户端
 * @Modified :
 */
@FeignClient(name = "zpcpDrugDevice-service", fallbackFactory = ZpcpDrugDeviceFeignService.HystrixZpcpDrugDeviceService.class,primary = false)
public interface ZpcpDrugDeviceFeignService extends ZpcpDrugDeviceClient {

    @Component
    @Slf4j
    class HystrixZpcpDrugDeviceService implements FallbackFactory<ZpcpDrugDeviceFeignService> {

        @Override
        public ZpcpDrugDeviceFeignService create(Throwable throwable) {
            return new ZpcpDrugDeviceFeignService() {
                @Override
                public Result add(@Valid @RequestBody ZpcpDrugDeviceForm zpcpDrugDeviceForm) {
                    log.error("ZpcpDrugDeviceService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ZpcpDrugDeviceService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ZpcpDrugDeviceForm zpcpDrugDeviceForm) {
                    log.error("ZpcpDrugDeviceService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ZpcpDrugDeviceService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ZpcpDrugDeviceQueryForm zpcpDrugDeviceQueryForm) {
                    log.error("ZpcpDrugDeviceService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ZpcpDrugDeviceQueryForm zpcpDrugDeviceQueryForm) {
                    log.error("ZpcpDrugDeviceService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addOrUpdateList(List<ZpcpDrugDeviceForm> drugDeviceForms) {
                    log.error("ZpcpDrugDeviceService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(DeleteForm deleteForm) {
                    log.error("ZpcpDrugDeviceService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}