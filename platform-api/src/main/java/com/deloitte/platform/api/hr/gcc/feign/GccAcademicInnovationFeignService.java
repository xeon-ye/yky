package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccAcademicInnovationClient;
import com.deloitte.platform.api.hr.gcc.param.GccAcademicInnovationQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchUpdateForm;
import com.deloitte.platform.api.hr.gcc.vo.GccAcademicInnovationForm;
import com.deloitte.platform.api.hr.gcc.vo.GccAcademicInnovationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccAcademicInnovation feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccAcademicInnovationFeignService.HystrixGccAcademicInnovationService.class,primary = false)
public interface GccAcademicInnovationFeignService extends GccAcademicInnovationClient {

    @Component
    @Slf4j
    class HystrixGccAcademicInnovationService implements FallbackFactory<GccAcademicInnovationFeignService> {

        @Override
        public GccAcademicInnovationFeignService create(Throwable throwable) {
            return new GccAcademicInnovationFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccAcademicInnovationForm gccAcademicInnovationForm) {
                    log.error("GccAcademicInnovationService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccAcademicInnovationService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccAcademicInnovationForm gccAcademicInnovationForm) {
                    log.error("GccAcademicInnovationService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccAcademicInnovationService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<GccAcademicInnovationVo> getByUserId(long userId) {
                    log.error("GccAcademicInnovationService getByUserId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccAcademicInnovationQueryForm gccAcademicInnovationQueryForm) {
                    log.error("GccAcademicInnovationService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccAcademicInnovationQueryForm gccAcademicInnovationQueryForm) {
                    log.error("GccAcademicInnovationService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result updateList(GccBaseBatchUpdateForm form) {
                    log.error("GccAcademicInnovationService updateList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result saveOrUpdateByUserId(GccAcademicInnovationForm gccAcademicInnovationForm) {
                    log.error("GccAcademicInnovationService saveOrUpdateByUserId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}