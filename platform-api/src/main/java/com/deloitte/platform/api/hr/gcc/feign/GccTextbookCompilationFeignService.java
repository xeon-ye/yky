package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccTextbookCompilationClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccTextbookCompilationQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTextbookCompilationForm;
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
 * @Description :   GccTextbookCompilation feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccTextbookCompilationFeignService.HystrixGccTextbookCompilationService.class,primary = false)
public interface GccTextbookCompilationFeignService extends GccTextbookCompilationClient {

    @Component
    @Slf4j
    class HystrixGccTextbookCompilationService implements FallbackFactory<GccTextbookCompilationFeignService> {

        @Override
        public GccTextbookCompilationFeignService create(Throwable throwable) {
            return new GccTextbookCompilationFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccTextbookCompilationForm gccTextbookCompilationForm) {
                    log.error("GccTextbookCompilationService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccTextbookCompilationService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccTextbookCompilationForm gccTextbookCompilationForm) {
                    log.error("GccTextbookCompilationService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccTextbookCompilationService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccTextbookCompilationQueryForm gccTextbookCompilationQueryForm) {
                    log.error("GccTextbookCompilationService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccTextbookCompilationQueryForm gccTextbookCompilationQueryForm) {
                    log.error("GccTextbookCompilationService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addOrUpdateList(List<GccTextbookCompilationForm> gccTextbookCompilationForms) {
                    log.error("GccTextbookCompilationService addOrUpdateList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(GccBaseBatchForm form) {
                    log.error("GccTextbookCompilationService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}