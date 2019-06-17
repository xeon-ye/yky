package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccPostgraduateGuidanceClient;
import com.deloitte.platform.api.hr.gcc.param.GccPostgraduateGuidanceQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccPostgraduateGuidanceForm;
import com.deloitte.platform.api.hr.gcc.vo.GccPostgraduateGuidanceVo;
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
 * @Description :   GccPostgraduateGuidance feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccPostgraduateGuidanceFeignService.HystrixGccPostgraduateGuidanceService.class,primary = false)
public interface GccPostgraduateGuidanceFeignService extends GccPostgraduateGuidanceClient {

    @Component
    @Slf4j
    class HystrixGccPostgraduateGuidanceService implements FallbackFactory<GccPostgraduateGuidanceFeignService> {

        @Override
        public GccPostgraduateGuidanceFeignService create(Throwable throwable) {
            return new GccPostgraduateGuidanceFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccPostgraduateGuidanceForm gccPostgraduateGuidanceForm) {
                    log.error("GccPostgraduateGuidanceService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccPostgraduateGuidanceService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccPostgraduateGuidanceForm gccPostgraduateGuidanceForm) {
                    log.error("GccPostgraduateGuidanceService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccPostgraduateGuidanceService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccPostgraduateGuidanceQueryForm gccPostgraduateGuidanceQueryForm) {
                    log.error("GccPostgraduateGuidanceService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }


                @Override
                public Result saveOrUpdate(List<GccPostgraduateGuidanceForm> gccAcademicInnovationForm) {
                    return null;
                }

                @Override
                public Result<GccPostgraduateGuidanceVo> getByUserId(long userId) {
                    return null;
                }

                @Override
                public Result search(@Valid @RequestBody GccPostgraduateGuidanceQueryForm gccPostgraduateGuidanceQueryForm) {
                    log.error("GccPostgraduateGuidanceService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}