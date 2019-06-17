package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccProducesResultsClient;
import com.deloitte.platform.api.hr.gcc.param.GccProducesResultsQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccProducesResultsForm;
import com.deloitte.platform.api.hr.gcc.vo.GccProducesResultsVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccProducesResults feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccProducesResultsFeignService.HystrixGccProducesResultsService.class,primary = false)
public interface GccProducesResultsFeignService extends GccProducesResultsClient {

    @Component
    @Slf4j
    class HystrixGccProducesResultsService implements FallbackFactory<GccProducesResultsFeignService> {

        @Override
        public GccProducesResultsFeignService create(Throwable throwable) {
            return new GccProducesResultsFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccProducesResultsForm gccProducesResultsForm) {
                    log.error("GccProducesResultsService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccProducesResultsService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccProducesResultsForm gccProducesResultsForm) {
                    log.error("GccProducesResultsService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccProducesResultsService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccProducesResultsQueryForm gccProducesResultsQueryForm) {
                    log.error("GccProducesResultsService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccProducesResultsQueryForm gccProducesResultsQueryForm) {
                    log.error("GccProducesResultsService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addByUserId(GccProducesResultsForm gccProducesResultsForm) {
                    log.error("GccProducesResultsService addByUserId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<GccProducesResultsVo> getByUserId(long userId) {
                    log.error("GccProducesResultsService addByUserId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}