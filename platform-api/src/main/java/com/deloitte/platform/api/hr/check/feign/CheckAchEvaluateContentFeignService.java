package com.deloitte.platform.api.hr.check.feign;


import com.deloitte.platform.api.hr.check.client.CheckAchEvaluateContentClient;
import com.deloitte.platform.api.hr.check.param.CheckAchEvaluateContentQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchEvaluateContentBatchForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchEvaluateContentForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :   CheckAchEvaluateContent feign客户端
 * @Modified :
 */
@FeignClient(name = "checkAchEvaluateContent-service", fallbackFactory = CheckAchEvaluateContentFeignService.HystrixCheckAchEvaluateContentService.class,primary = false)
public interface CheckAchEvaluateContentFeignService extends CheckAchEvaluateContentClient {

    @Component
    @Slf4j
    class HystrixCheckAchEvaluateContentService implements FallbackFactory<CheckAchEvaluateContentFeignService> {

        @Override
        public CheckAchEvaluateContentFeignService create(Throwable throwable) {
            return new CheckAchEvaluateContentFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckAchEvaluateContentForm checkAchEvaluateContentForm) {
                    log.error("CheckAchEvaluateContentService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckAchEvaluateContentService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckAchEvaluateContentForm checkAchEvaluateContentForm) {
                    log.error("CheckAchEvaluateContentService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckAchEvaluateContentService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckAchEvaluateContentQueryForm checkAchEvaluateContentQueryForm) {
                    log.error("CheckAchEvaluateContentService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckAchEvaluateContentQueryForm checkAchEvaluateContentQueryForm) {
                    log.error("CheckAchEvaluateContentService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchSave(@Valid @RequestBody CheckAchEvaluateContentBatchForm checkAchEvaluateContentBatchForm) {
                    log.error("CheckAchEvaluateContentService batchSave服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}