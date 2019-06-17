package com.deloitte.platform.api.hr.gcc.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.client.GccOnjobInformationClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccOnjobInformationQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccOnjobInformationAllVo;
import com.deloitte.platform.api.hr.gcc.vo.GccOnjobInformationForm;
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
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccOnjobInformation feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccOnjobInformationFeignService.HystrixGccOnjobInformationService.class,primary = false)
public interface GccOnjobInformationFeignService extends GccOnjobInformationClient {

    @Component
    @Slf4j
    class HystrixGccOnjobInformationService implements FallbackFactory<GccOnjobInformationFeignService> {

        @Override
        public GccOnjobInformationFeignService create(Throwable throwable) {
            return new GccOnjobInformationFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccOnjobInformationForm gccOnjobInformationForm) {
                    log.error("GccOnjobInformationService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccOnjobInformationService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccOnjobInformationForm gccOnjobInformationForm) {
                    log.error("GccOnjobInformationService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccOnjobInformationService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                @Override
                public Result list(@Valid @RequestBody GccOnjobInformationQueryForm gccOnjobInformationQueryForm) {
                    log.error("GccOnjobInformationService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                @Override
                public Result search(@Valid @RequestBody GccOnjobInformationQueryForm gccOnjobInformationQueryForm) {
                    log.error("GccOnjobInformationService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                @Override
                public Result<IPage<GccOnjobInformationAllVo>> searchAll(GccBaseQueryForm gccOnjobInformationQueryAllForm) {
                    log.error("GccOnjobInformationService searchAll服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                @Override
                public Result<List<GccOnjobInformationAllVo>> listAll(GccBaseQueryForm gccOnjobInformationQueryAllForm) {
                    log.error("GccOnjobInformationService listAll服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                @Override
                public Result exportExcel(GccBaseQueryForm gccOnjobInformationQueryAllForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccOnjobInformationService exportExcel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}