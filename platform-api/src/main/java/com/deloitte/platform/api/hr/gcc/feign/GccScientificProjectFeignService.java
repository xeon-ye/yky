package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccScientificProjectClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccScientificProjectQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccScientificProjectForm;
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
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccScientificProject feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccScientificProjectFeignService.HystrixGccScientificProjectService.class,primary = false)
public interface GccScientificProjectFeignService extends GccScientificProjectClient {

    @Component
    @Slf4j
    class HystrixGccScientificProjectService implements FallbackFactory<GccScientificProjectFeignService> {

        @Override
        public GccScientificProjectFeignService create(Throwable throwable) {
            return new GccScientificProjectFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccScientificProjectForm gccScientificProjectForm) {
                    log.error("GccScientificProjectService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccScientificProjectService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccScientificProjectForm gccScientificProjectForm) {
                    log.error("GccScientificProjectService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccScientificProjectService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccScientificProjectQueryForm gccScientificProjectQueryForm) {
                    log.error("GccScientificProjectService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccScientificProjectQueryForm gccScientificProjectQueryForm) {
                    log.error("GccScientificProjectService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addOrUpdateList(List<GccScientificProjectForm> forms) {
                    return null;
                }

                @Override
                public Result deleteList(GccBaseBatchForm form) {
                    return null;
                }
            };
        }
    }
}