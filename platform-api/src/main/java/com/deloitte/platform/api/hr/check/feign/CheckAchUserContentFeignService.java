package com.deloitte.platform.api.hr.check.feign;


import com.deloitte.platform.api.hr.check.client.CheckAchUserContentClient;
import com.deloitte.platform.api.hr.check.param.CheckAchUserContentQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchUserContentAppealForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchUserContentForm;
import com.deloitte.platform.api.hr.check.vo.CommonProcessForm;
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
 * @Description :   CheckAchUserContent feign客户端
 * @Modified :
 */
@FeignClient(name = "checkAchUserContent-service", fallbackFactory = CheckAchUserContentFeignService.HystrixCheckAchUserContentService.class,primary = false)
public interface CheckAchUserContentFeignService extends CheckAchUserContentClient {

    @Component
    @Slf4j
    class HystrixCheckAchUserContentService implements FallbackFactory<CheckAchUserContentFeignService> {

        @Override
        public CheckAchUserContentFeignService create(Throwable throwable) {
            return new CheckAchUserContentFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckAchUserContentForm checkAchUserContentForm) {
                    log.error("CheckAchUserContentService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckAchUserContentService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckAchUserContentForm checkAchUserContentForm) {
                    log.error("CheckAchUserContentService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckAchUserContentService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckAchUserContentQueryForm checkAchUserContentQueryForm) {
                    log.error("CheckAchUserContentService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckAchUserContentQueryForm checkAchUserContentQueryForm) {
                    log.error("CheckAchUserContentService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchDelete(@Valid @RequestBody List<String> ids) {
                    log.error("CheckAchUserContentService batchDelete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result submitApprove(@Valid @RequestBody CheckAchUserContentAppealForm checkAchUserContentAppealForm) {
                    log.error("CheckAchUserContentService submitApprove服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result dealApprove(@Valid @RequestBody  CommonProcessForm form) {
                    log.error("CheckAchUserContentService dealApprove服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}