package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.ZpcpGuidStandardClient;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpGuidStandardQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpGuidStandardForm;
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
 * @Description :   ZpcpGuidStandard feign客户端
 * @Modified :
 */
@FeignClient(name = "zpcpGuidStandard-service", fallbackFactory = ZpcpGuidStandardFeignService.HystrixZpcpGuidStandardService.class,primary = false)
public interface ZpcpGuidStandardFeignService extends ZpcpGuidStandardClient {

    @Component
    @Slf4j
    class HystrixZpcpGuidStandardService implements FallbackFactory<ZpcpGuidStandardFeignService> {

        @Override
        public ZpcpGuidStandardFeignService create(Throwable throwable) {
            return new ZpcpGuidStandardFeignService() {
                @Override
                public Result add(@Valid @RequestBody ZpcpGuidStandardForm zpcpGuidStandardForm) {
                    log.error("ZpcpGuidStandardService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ZpcpGuidStandardService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ZpcpGuidStandardForm zpcpGuidStandardForm) {
                    log.error("ZpcpGuidStandardService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ZpcpGuidStandardService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ZpcpGuidStandardQueryForm zpcpGuidStandardQueryForm) {
                    log.error("ZpcpGuidStandardService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ZpcpGuidStandardQueryForm zpcpGuidStandardQueryForm) {
                    log.error("ZpcpGuidStandardService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addOrUpdateList(List<ZpcpGuidStandardForm> guidStandardForms) {
                    log.error("ZpcpGuidStandardService search服务不可用......");
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