package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccAcademicSocietiesClient;
import com.deloitte.platform.api.hr.gcc.param.GccAcademicSocietiesQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.vo.GccAcademicSocietiesForm;
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
 * @Description :   GccAcademicSocieties feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccAcademicSocietiesFeignService.HystrixGccAcademicSocietiesService.class,primary = false)
public interface GccAcademicSocietiesFeignService extends GccAcademicSocietiesClient {

    @Component
    @Slf4j
    class HystrixGccAcademicSocietiesService implements FallbackFactory<GccAcademicSocietiesFeignService> {

        @Override
        public GccAcademicSocietiesFeignService create(Throwable throwable) {
            return new GccAcademicSocietiesFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccAcademicSocietiesForm gccAcademicSocietiesForm) {
                    log.error("GccAcademicSocietiesService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccAcademicSocietiesService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccAcademicSocietiesForm gccAcademicSocietiesForm) {
                    log.error("GccAcademicSocietiesService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccAcademicSocietiesService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccAcademicSocietiesQueryForm gccAcademicSocietiesQueryForm) {
                    log.error("GccAcademicSocietiesService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccAcademicSocietiesQueryForm gccAcademicSocietiesQueryForm) {
                    log.error("GccAcademicSocietiesService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addOrUpdateList(List<GccAcademicSocietiesForm> gccAcademicSocietiesForms) {
                    log.error("GccAcademicSocietiesService addOrUpdateList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(GccBaseBatchForm form) {
                    log.error("GccAcademicSocietiesService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}