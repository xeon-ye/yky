package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccFundsProjectClient;
import com.deloitte.platform.api.hr.gcc.param.GccFundsProjectQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccFundsProjectForm;
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
 * @Author : LJH
 * @Date : Create in 2019-05-13
 * @Description :   GccFundsProject feign客户端
 * @Modified :
 */
@FeignClient(name = "gccFundsProject-service", fallbackFactory = GccFundsProjectFeignService.HystrixGccFundsProjectService.class,primary = false)
public interface GccFundsProjectFeignService extends GccFundsProjectClient {

    @Component
    @Slf4j
    class HystrixGccFundsProjectService implements FallbackFactory<GccFundsProjectFeignService> {

        @Override
        public GccFundsProjectFeignService create(Throwable throwable) {
            return new GccFundsProjectFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccFundsProjectForm gccFundsProjectForm) {
                    log.error("GccFundsProjectService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccFundsProjectService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccFundsProjectForm gccFundsProjectForm) {
                    log.error("GccFundsProjectService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccFundsProjectService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccFundsProjectQueryForm gccFundsProjectQueryForm) {
                    log.error("GccFundsProjectService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccFundsProjectQueryForm gccFundsProjectQueryForm) {
                    log.error("GccFundsProjectService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result importExcel(MultipartFile file) throws IOException {
                    log.error("GccFundsProjectService importExcel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportExcel(GccFundsProjectQueryForm gccFundsProjectQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccFundsProjectService exportExcel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}