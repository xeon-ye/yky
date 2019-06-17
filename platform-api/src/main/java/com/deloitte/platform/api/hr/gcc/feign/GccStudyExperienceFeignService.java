package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccStudyExperienceClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccStudyExperienceQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccStudyExperienceForm;
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
 * @Description :   GccStudyExperience feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccStudyExperienceFeignService.HystrixGccStudyExperienceService.class,primary = false)
public interface GccStudyExperienceFeignService extends GccStudyExperienceClient {

    @Component
    @Slf4j
    class HystrixGccStudyExperienceService implements FallbackFactory<GccStudyExperienceFeignService> {

        @Override
        public GccStudyExperienceFeignService create(Throwable throwable) {
            return new GccStudyExperienceFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccStudyExperienceForm gccStudyExperienceForm) {
                    log.error("GccStudyExperienceService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccStudyExperienceService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccStudyExperienceForm gccStudyExperienceForm) {
                    log.error("GccStudyExperienceService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccStudyExperienceService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccStudyExperienceQueryForm gccStudyExperienceQueryForm) {
                    log.error("GccStudyExperienceService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccStudyExperienceQueryForm gccStudyExperienceQueryForm) {
                    log.error("GccStudyExperienceService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addOrUpdateList(List<GccStudyExperienceForm> forms) {
                    log.error("GccStudyExperienceService addOrUpdateList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(GccBaseBatchForm form) {
                    log.error("GccStudyExperienceService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}