package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ReviewNoteClient;
import com.deloitte.platform.api.project.param.ReviewNoteQueryForm;
import com.deloitte.platform.api.project.vo.ReviewNoteForm;
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
 * @Date : Create in 2019-04-28
 * @Description :   ReviewNote feign客户端
 * @Modified :
 */
@FeignClient(name = "reviewNote-service", fallbackFactory = ReviewNoteFeignService.HystrixReviewNoteService.class,primary = false)
public interface ReviewNoteFeignService extends ReviewNoteClient {

    @Component
    @Slf4j
    class HystrixReviewNoteService implements FallbackFactory<ReviewNoteFeignService> {

        @Override
        public ReviewNoteFeignService create(Throwable throwable) {
            return new ReviewNoteFeignService() {
                @Override
                public Result add(@Valid @RequestBody ReviewNoteForm reviewNoteForm) {
                    log.error("ReviewNoteService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ReviewNoteService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ReviewNoteForm reviewNoteForm) {
                    log.error("ReviewNoteService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ReviewNoteService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ReviewNoteQueryForm reviewNoteQueryForm) {
                    log.error("ReviewNoteService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ReviewNoteQueryForm reviewNoteQueryForm) {
                    log.error("ReviewNoteService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}