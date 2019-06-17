package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccReviewPlatformClient;
import com.deloitte.platform.api.hr.gcc.param.GccReviewPlatformQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccReviewPlatformForm;
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
 * @Author : liangjinghai
 * @Date : Create in 2019-04-13
 * @Description :   GccReviewPlatform feign客户端
 * @Modified :
 */
@FeignClient(name = "gccReviewPlatform-service", fallbackFactory = GccReviewPlatformFeignService.HystrixGccReviewPlatformService.class,primary = false)
public interface GccReviewPlatformFeignService extends GccReviewPlatformClient {

    @Component
    @Slf4j
    class HystrixGccReviewPlatformService implements FallbackFactory<GccReviewPlatformFeignService> {

        @Override
        public GccReviewPlatformFeignService create(Throwable throwable) {
            return new GccReviewPlatformFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccReviewPlatformForm gccReviewPlatformForm) {
                    log.error("GccReviewPlatformService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccReviewPlatformService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccReviewPlatformForm gccReviewPlatformForm) {
                    log.error("GccReviewPlatformService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccReviewPlatformService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccReviewPlatformQueryForm gccReviewPlatformQueryForm) {
                    log.error("GccReviewPlatformService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccReviewPlatformQueryForm gccReviewPlatformQueryForm) {
                    log.error("GccReviewPlatformService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
              /*  @Override
                public Result<List<GccReviewOptionBygroupVo>> listByGroup(GccBaseQueryForm queryForm) {
                    log.error("GccReviewOptionService listByGroup服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<GccReviewOptionBygroupVo>> searchByGroup(GccBaseQueryForm queryForm) {
                    log.error("GccReviewOptionService searchByGroup服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                @Override
                public Result export(GccBaseQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccReviewOptionService export服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportResult(GccBaseQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("GccReviewOptionService exportResult服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result importExcel(MultipartFile file) throws IOException {
                    log.error("GccReviewOptionService importExcel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }*/
            };
        }
    }
}