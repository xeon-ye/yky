package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccUnitRecommedClient;
import com.deloitte.platform.api.hr.gcc.param.GccUnitRecommedQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccUnitRecommedForm;
import com.deloitte.platform.api.hr.gcc.vo.GccUnitRecommedVo;
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
 * @Description :   GccUnitRecommed feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccUnitRecommedFeignService.HystrixGccUnitRecommedService.class,primary = false)
public interface GccUnitRecommedFeignService extends GccUnitRecommedClient {

    @Component
    @Slf4j
    class HystrixGccUnitRecommedService implements FallbackFactory<GccUnitRecommedFeignService> {

        @Override
        public GccUnitRecommedFeignService create(Throwable throwable) {
            return new GccUnitRecommedFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccUnitRecommedForm gccUnitRecommedForm) {
                    log.error("GccUnitRecommedService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccUnitRecommedService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccUnitRecommedForm gccUnitRecommedForm) {
                    log.error("GccUnitRecommedService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccUnitRecommedService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccUnitRecommedQueryForm gccUnitRecommedQueryForm) {
                    log.error("GccUnitRecommedService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccUnitRecommedQueryForm gccUnitRecommedQueryForm) {
                    log.error("GccUnitRecommedService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<GccUnitRecommedVo> getByUserId(long userId) {
                    log.error("GccUnitRecommedService getByUserId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result saveOrUpdateByUserId(GccUnitRecommedForm gccUnitRecommedForm) {
                    log.error("GccUnitRecommedService saveOrUpdateByUserId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}