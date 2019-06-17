package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.EnclosureClient;
import com.deloitte.platform.api.project.param.EnclosureQueryForm;
import com.deloitte.platform.api.project.vo.EnclosureForm;
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
 * @Date : Create in 2019-06-10
 * @Description :   Enclosure feign客户端
 * @Modified :
 */
@FeignClient(name = "enclosure-service", fallbackFactory = EnclosureFeignService.HystrixEnclosureService.class,primary = false)
public interface EnclosureFeignService extends EnclosureClient {

    @Component
    @Slf4j
    class HystrixEnclosureService implements FallbackFactory<EnclosureFeignService> {

        @Override
        public EnclosureFeignService create(Throwable throwable) {
            return new EnclosureFeignService() {
                @Override
                public Result add(@Valid @RequestBody EnclosureForm enclosureForm) {
                    log.error("EnclosureService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EnclosureService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EnclosureForm enclosureForm) {
                    log.error("EnclosureService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EnclosureService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EnclosureQueryForm enclosureQueryForm) {
                    log.error("EnclosureService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EnclosureQueryForm enclosureQueryForm) {
                    log.error("EnclosureService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}