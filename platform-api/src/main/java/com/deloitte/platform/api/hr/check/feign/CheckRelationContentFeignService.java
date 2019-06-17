package com.deloitte.platform.api.hr.check.feign;


import com.deloitte.platform.api.hr.check.client.CheckRelationContentClient;
import com.deloitte.platform.api.hr.check.param.CheckRelationContentQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckRelationContentForm;
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
 * @Date : Create in 2019-04-08
 * @Description :   CheckRelationContentForm feign客户端
 * @Modified :
 */
@FeignClient(name = "checkRelationContent-service", fallbackFactory = CheckRelationContentFeignService.HystrixCheckRelationContentService.class,primary = false)
public interface CheckRelationContentFeignService extends CheckRelationContentClient {

    @Component
    @Slf4j
    class HystrixCheckRelationContentService implements FallbackFactory<CheckRelationContentFeignService> {

        @Override
        public CheckRelationContentFeignService create(Throwable throwable) {
            return new CheckRelationContentFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckRelationContentForm CheckRelationContentForm) {
                    log.error("CheckRelationContentService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckRelationContentService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckRelationContentForm CheckRelationContentForm) {
                    log.error("CheckRelationContentService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckRelationContentService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckRelationContentQueryForm checkRelationContentQueryForm) {
                    log.error("CheckRelationContentService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckRelationContentQueryForm checkRelationContentQueryForm) {
                    log.error("CheckRelationContentService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchDeleteByRelationId(@Valid @RequestBody List<String> ids) {
                    log.error("CheckRelationContentService batchDeleteByRelationId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}