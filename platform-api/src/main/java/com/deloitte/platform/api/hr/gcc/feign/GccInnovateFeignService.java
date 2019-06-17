package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccInnovateClient;
import com.deloitte.platform.api.hr.gcc.param.GccInnovateQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccInnovateForm;
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
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccInnovate feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccInnovateFeignService.HystrixGccInnovateService.class,primary = false)
public interface GccInnovateFeignService extends GccInnovateClient {

    @Component
    @Slf4j
    class HystrixGccInnovateService implements FallbackFactory<GccInnovateFeignService> {

        @Override
        public GccInnovateFeignService create(Throwable throwable) {
            return new GccInnovateFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccInnovateForm gccInnovateForm) {
                    log.error("GccInnovateService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccInnovateService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccInnovateForm gccInnovateForm) {
                    log.error("GccInnovateService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccInnovateService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccInnovateQueryForm gccInnovateQueryForm) {
                    log.error("GccInnovateService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccInnovateQueryForm gccInnovateQueryForm) {
                    log.error("GccInnovateService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}