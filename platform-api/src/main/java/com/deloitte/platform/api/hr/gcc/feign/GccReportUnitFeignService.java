package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccReportUnitClient;
import com.deloitte.platform.api.hr.gcc.param.GccReportUnitQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccReportUnitForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @Author : LJH
 * @Date : Create in 2019-05-11
 * @Description :   GccReportUnit feign客户端
 * @Modified :
 */
@FeignClient(name = "gccReportUnit-service", fallbackFactory = GccReportUnitFeignService.HystrixGccReportUnitService.class,primary = false)
public interface GccReportUnitFeignService extends GccReportUnitClient {

    @Component
    @Slf4j
    class HystrixGccReportUnitService implements FallbackFactory<GccReportUnitFeignService> {

        @Override
        public GccReportUnitFeignService create(Throwable throwable) {
            return new GccReportUnitFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccReportUnitForm gccReportUnitForm) {
                    log.error("GccReportUnitService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccReportUnitService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccReportUnitForm gccReportUnitForm) {
                    log.error("GccReportUnitService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccReportUnitService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccReportUnitQueryForm gccReportUnitQueryForm) {
                    log.error("GccReportUnitService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccReportUnitQueryForm gccReportUnitQueryForm) {
                    log.error("GccReportUnitService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result export(GccReportUnitQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccReportUnitService export服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}