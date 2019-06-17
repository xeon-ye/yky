package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccSpecialistGroupClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccSpecialistGroupQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccSpecialistGroupForm;
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
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccSpecialistGroup feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccSpecialistGroupFeignService.HystrixGccSpecialistGroupService.class,primary = false)
public interface GccSpecialistGroupFeignService extends GccSpecialistGroupClient {

    @Component
    @Slf4j
    class HystrixGccSpecialistGroupService implements FallbackFactory<GccSpecialistGroupFeignService> {

        @Override
        public GccSpecialistGroupFeignService create(Throwable throwable) {
            return new GccSpecialistGroupFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccSpecialistGroupForm gccSpecialistGroupForm) {
                    log.error("GccSpecialistGroupService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccSpecialistGroupService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccSpecialistGroupForm gccSpecialistGroupForm) {
                    log.error("GccSpecialistGroupService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccSpecialistGroupService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccSpecialistGroupQueryForm gccSpecialistGroupQueryForm) {
                    log.error("GccSpecialistGroupService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccSpecialistGroupQueryForm gccSpecialistGroupQueryForm) {
                    log.error("GccSpecialistGroupService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportExcel(GccSpecialistGroupQueryForm gccSpecialistGroupQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccSpecialistGroupService exportExcel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchdelete(GccBaseBatchForm gccSpecialistGroupDeleteForm) {
                    log.error("GccSpecialistGroupService batchdelete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}