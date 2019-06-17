package com.deloitte.platform.api.oaservice.notice.feign;

import com.deloitte.platform.api.oaservice.notice.client.OaDzggInterfaceTempClient;
import com.deloitte.platform.api.oaservice.notice.param.OaDzggInterfaceTempQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaDzggInterfaceTempForm;
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
 * @Author : jianghaixun
 * @Date : Create in 2019-04-16
 * @Description :   OaDzggInterfaceTemp feign客户端
 * @Modified :
 */
@FeignClient(name = "oaDzggInterfaceTemp-service", fallbackFactory = OaDzggInterfaceTempFeignService.HystrixOaDzggInterfaceTempService.class,primary = false)
public interface OaDzggInterfaceTempFeignService extends OaDzggInterfaceTempClient {

    @Component
    @Slf4j
    class HystrixOaDzggInterfaceTempService implements FallbackFactory<OaDzggInterfaceTempFeignService> {

        @Override
        public OaDzggInterfaceTempFeignService create(Throwable throwable) {
            return new OaDzggInterfaceTempFeignService() {
                @Override
                public Result add(@Valid @RequestBody OaDzggInterfaceTempForm oaDzggInterfaceTempForm) {
                    log.error("OaDzggInterfaceTempService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OaDzggInterfaceTempService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OaDzggInterfaceTempForm oaDzggInterfaceTempForm) {
                    log.error("OaDzggInterfaceTempService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OaDzggInterfaceTempService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaDzggInterfaceTempQueryForm oaDzggInterfaceTempQueryForm) {
                    log.error("OaDzggInterfaceTempService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaDzggInterfaceTempQueryForm oaDzggInterfaceTempQueryForm) {
                    log.error("OaDzggInterfaceTempService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}