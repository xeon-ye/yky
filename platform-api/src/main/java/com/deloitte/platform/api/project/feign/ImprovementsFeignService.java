package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ImprovementsClient;
import com.deloitte.platform.api.project.param.ImprovementsQueryForm;
import com.deloitte.platform.api.project.vo.ImprovementsForm;
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
 * @Description :   Improvements feign客户端
 * @Modified :
 */
@FeignClient(name = "improvements-service", fallbackFactory = ImprovementsFeignService.HystrixImprovementsService.class,primary = false)
public interface ImprovementsFeignService extends ImprovementsClient {

    @Component
    @Slf4j
    class HystrixImprovementsService implements FallbackFactory<ImprovementsFeignService> {

        @Override
        public ImprovementsFeignService create(Throwable throwable) {
            return new ImprovementsFeignService() {
                @Override
                public Result add(@Valid @RequestBody ImprovementsForm improvementsForm) {
                    log.error("ImprovementsService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ImprovementsService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ImprovementsForm improvementsForm) {
                    log.error("ImprovementsService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ImprovementsService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ImprovementsQueryForm improvementsQueryForm) {
                    log.error("ImprovementsService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ImprovementsQueryForm improvementsQueryForm) {
                    log.error("ImprovementsService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}