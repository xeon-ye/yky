package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.isump.vo.DeptForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserForm;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskForm;
import com.deloitte.platform.api.project.client.ReviewClient;
import com.deloitte.platform.api.project.param.ReviewQueryForm;
import com.deloitte.platform.api.project.vo.ProjectReviewVo;
import com.deloitte.platform.api.project.vo.ReviewForm;
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
 * @Date : Create in 2019-05-23
 * @Description :   Review feign客户端
 * @Modified :
 */
@FeignClient(name = "review-service", fallbackFactory = ReviewFeignService.HystrixReviewService.class,primary = false)
public interface ReviewFeignService extends ReviewClient {

    @Component
    @Slf4j
    class HystrixReviewService implements FallbackFactory<ReviewFeignService> {

        @Override
        public ReviewFeignService create(Throwable throwable) {
            return new ReviewFeignService() {
                @Override
                public Result add(@Valid @RequestBody ReviewForm reviewForm) {
                    log.error("ReviewService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ReviewService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ReviewForm reviewForm) {
                    log.error("ReviewService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ReviewService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ReviewQueryForm reviewQueryForm) {
                    log.error("ReviewService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ReviewQueryForm reviewQueryForm) {
                    log.error("ReviewService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<ProjectReviewVo> getOneApp(String applicationId) {
                    log.error("ReviewService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result toAdjust(ProjectReviewVo projectReviewVo) {
                    log.error("ReviewService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result toRefused(ProjectReviewVo projectReviewVo) {
                    log.error("ReviewService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<ProjectReviewVo> toSubmit(ProjectReviewVo projectReviewVo, SendProcessTaskForm sendProcessTaskForm) {
                    return null;
                }

                @Override
                public Result toSave(ProjectReviewVo projectReviewVo) {
                    return null;
                }
            };
        }
    }
}