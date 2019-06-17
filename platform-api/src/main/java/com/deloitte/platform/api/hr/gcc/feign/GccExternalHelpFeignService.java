package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccExternalHelpClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccExternalHelpQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccExternalReportQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccExternalHelpForm;
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
 * @Description :   GccExternalHelp feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccExternalHelpFeignService.HystrixGccExternalHelpService.class,primary = false)
public interface GccExternalHelpFeignService extends GccExternalHelpClient {

    @Component
    @Slf4j
    class HystrixGccExternalHelpService implements FallbackFactory<GccExternalHelpFeignService> {

        @Override
        public GccExternalHelpFeignService create(Throwable throwable) {
            return new GccExternalHelpFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccExternalHelpForm gccExternalHelpForm) {
                    log.error("GccExternalHelpService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccExternalHelpService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccExternalHelpForm gccExternalHelpForm) {
                    log.error("GccExternalHelpService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccExternalHelpService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccExternalHelpQueryForm gccExternalHelpQueryForm) {
                    log.error("GccExternalHelpService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccExternalHelpQueryForm gccExternalHelpQueryForm) {
                    log.error("GccExternalHelpService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result importExcel(@Valid @RequestBody MultipartFile file) throws IOException {
                    log.error("GccExternalHelpService importExcel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result importExcelYj(MultipartFile file) {
                    log.error("GccExternalHelpService importExcelYj服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result importExcelBs(MultipartFile file) {
                    log.error("GccExternalHelpService importExcelBs服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportExcel(GccExternalHelpQueryForm gccExternalHelpQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccExternalHelpService exportExcel服务不可用......");
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<Object> reportForms(GccExternalReportQueryForm gccExternalReportQueryForm) {
                    log.error("GccExternalHelpService reportForms服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(GccBaseBatchForm form) {
                    return null;
                }
            };
        }
    }
}