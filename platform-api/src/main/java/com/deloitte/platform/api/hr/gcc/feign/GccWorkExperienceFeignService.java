package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccWorkExperienceClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccWorkExperienceQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccWorkExperienceForm;
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
 * @Description :   GccWorkExperience feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccWorkExperienceFeignService.HystrixGccWorkExperienceService.class,primary = false)
public interface GccWorkExperienceFeignService extends GccWorkExperienceClient {

    @Component
    @Slf4j
    class HystrixGccWorkExperienceService implements FallbackFactory<GccWorkExperienceFeignService> {

        @Override
        public GccWorkExperienceFeignService create(Throwable throwable) {
            return new GccWorkExperienceFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccWorkExperienceForm gccWorkExperienceForm) {
                    log.error("GccWorkExperienceService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccWorkExperienceService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccWorkExperienceForm gccWorkExperienceForm) {
                    log.error("GccWorkExperienceService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccWorkExperienceService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccWorkExperienceQueryForm gccWorkExperienceQueryForm) {
                    log.error("GccWorkExperienceService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccWorkExperienceQueryForm gccWorkExperienceQueryForm) {
                    log.error("GccWorkExperienceService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addOrUpdateList(List<GccWorkExperienceForm> gccWorkExperienceForms) {
                    log.error("GccWorkExperienceService addOrUpdateList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(GccBaseBatchForm form) {
                    log.error("GccWorkExperienceService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}