package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccProjectReviewClient;
import com.deloitte.platform.api.hr.gcc.param.GccProjectReviewQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccProjectReviewForm;
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
 * @Description :   GccProjectReview feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccProjectReviewFeignService.HystrixGccProjectReviewService.class,primary = false)
public interface GccProjectReviewFeignService extends GccProjectReviewClient {

    @Component
    @Slf4j
    class HystrixGccProjectReviewService implements FallbackFactory<GccProjectReviewFeignService> {

        @Override
        public GccProjectReviewFeignService create(Throwable throwable) {
            return new GccProjectReviewFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccProjectReviewForm gccProjectReviewForm) {
                    log.error("GccProjectReviewService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccProjectReviewService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccProjectReviewForm gccProjectReviewForm) {
                    log.error("GccProjectReviewService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccProjectReviewService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccProjectReviewQueryForm gccProjectReviewQueryForm) {
                    log.error("GccProjectReviewService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccProjectReviewQueryForm gccProjectReviewQueryForm) {
                    log.error("GccProjectReviewService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}