package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccReviewOptionClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccReviewOptionQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccReOpBatch;
import com.deloitte.platform.api.hr.gcc.vo.GccReviewOptionForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-13
 * @Description :   GccReviewOption feign客户端
 * @Modified :
 */
@FeignClient(name = "gccReviewOption-service", fallbackFactory = GccReviewOptionFeignService.HystrixGccReviewOptionService.class,primary = false)
public interface GccReviewOptionFeignService extends GccReviewOptionClient {

    @Component
    @Slf4j
    class HystrixGccReviewOptionService implements FallbackFactory<GccReviewOptionFeignService> {

        @Override
        public GccReviewOptionFeignService create(Throwable throwable) {
            return new GccReviewOptionFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccReviewOptionForm gccReviewOptionForm) {
                    log.error("GccReviewOptionService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccReviewOptionService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccReviewOptionForm gccReviewOptionForm) {
                    log.error("GccReviewOptionService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccReviewOptionService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccReviewOptionQueryForm gccReviewOptionQueryForm) {
                    log.error("GccReviewOptionService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccReviewOptionQueryForm gccReviewOptionQueryForm) {
                    log.error("GccReviewOptionService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result gatchAdd(String types, GccReOpBatch gccReOpBatch) {
                    log.error("GccReviewOptionService gatchAdd服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}