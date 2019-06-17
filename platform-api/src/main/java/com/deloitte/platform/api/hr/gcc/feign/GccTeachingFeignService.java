package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccTeachingClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccTeachingQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTeachingForm;
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
 * @Description :   GccTeaching feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccTeachingFeignService.HystrixGccTeachingService.class,primary = false)
public interface GccTeachingFeignService extends GccTeachingClient {

    @Component
    @Slf4j
    class HystrixGccTeachingService implements FallbackFactory<GccTeachingFeignService> {

        @Override
        public GccTeachingFeignService create(Throwable throwable) {
            return new GccTeachingFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccTeachingForm gccTeachingForm) {
                    log.error("GccTeachingService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccTeachingService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccTeachingForm gccTeachingForm) {
                    log.error("GccTeachingService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccTeachingService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccTeachingQueryForm gccTeachingQueryForm) {
                    log.error("GccTeachingService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccTeachingQueryForm gccTeachingQueryForm) {
                    log.error("GccTeachingService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addOrUpdateList(List<GccTeachingForm> gccWorkExperienceForms) {
                    log.error("GccTeachingService addOrUpdateList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(GccBaseBatchForm form) {
                    log.error("GccTeachingService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}