package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccTechnocracyClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccTechnocracyExportForm;
import com.deloitte.platform.api.hr.gcc.param.GccTechnocracyQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTechnocracyForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccTechnocracy feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccTechnocracyFeignService.HystrixGccTechnocracyService.class,primary = false)
public interface GccTechnocracyFeignService extends GccTechnocracyClient {

    @Component
    @Slf4j
    class HystrixGccTechnocracyService implements FallbackFactory<GccTechnocracyFeignService> {

        @Override
        public GccTechnocracyFeignService create(Throwable throwable) {
            return new GccTechnocracyFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccTechnocracyForm gccTechnocracyForm) {
                    log.error("GccTechnocracyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccTechnocracyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccTechnocracyForm gccTechnocracyForm) {
                    log.error("GccTechnocracyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccTechnocracyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccTechnocracyQueryForm gccTechnocracyQueryForm) {
                    log.error("GccTechnocracyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccTechnocracyQueryForm gccTechnocracyQueryForm) {
                    log.error("GccTechnocracyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result importExcel(MultipartFile file, String type) throws IOException {
                    log.error("GccTechnocracyService importExcel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                @Override
                public Result exportExcel(GccTechnocracyExportForm gccTechnocracyExportForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("GccTechnocracyService exportExcel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                @Override
                public Result deleteList(GccBaseBatchForm form) {
                    log.error("GccTechnocracyService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                @Override
                public Result addOrUpdate(GccTechnocracyForm gccTechnocracyForm) {
                    return null;
                }

            };
        }
    }
}