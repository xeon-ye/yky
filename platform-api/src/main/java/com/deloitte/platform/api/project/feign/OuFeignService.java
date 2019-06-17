package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.OuClient;
import com.deloitte.platform.api.project.param.OuQueryForm;
import com.deloitte.platform.api.project.vo.OuForm;
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
 * @Description :   Ou feign客户端
 * @Modified :
 */
@FeignClient(name = "ou-service", fallbackFactory = OuFeignService.HystrixOuService.class,primary = false)
public interface OuFeignService extends OuClient {

    @Component
    @Slf4j
    class HystrixOuService implements FallbackFactory<OuFeignService> {

        @Override
        public OuFeignService create(Throwable throwable) {
            return new OuFeignService() {
                @Override
                public Result add(@Valid @RequestBody OuForm ouForm) {
                    log.error("OuService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OuService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OuForm ouForm) {
                    log.error("OuService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OuService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OuQueryForm ouQueryForm) {
                    log.error("OuService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OuQueryForm ouQueryForm) {
                    log.error("OuService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}