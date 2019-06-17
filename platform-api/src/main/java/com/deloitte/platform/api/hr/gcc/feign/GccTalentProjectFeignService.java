package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccTalentProjectClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccTalentProjectQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTalentProjectForm;
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
 * @Description :   GccTalentProject feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccTalentProjectFeignService.HystrixGccTalentProjectService.class,primary = false)
public interface GccTalentProjectFeignService extends GccTalentProjectClient {

    @Component
    @Slf4j
    class HystrixGccTalentProjectService implements FallbackFactory<GccTalentProjectFeignService> {

        @Override
        public GccTalentProjectFeignService create(Throwable throwable) {
            return new GccTalentProjectFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccTalentProjectForm gccTalentProjectForm) {
                    log.error("GccTalentProjectService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccTalentProjectService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccTalentProjectForm gccTalentProjectForm) {
                    log.error("GccTalentProjectService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccTalentProjectService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccTalentProjectQueryForm gccTalentProjectQueryForm) {
                    log.error("GccTalentProjectService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccTalentProjectQueryForm gccTalentProjectQueryForm) {
                    log.error("GccTalentProjectService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportExcel(GccTalentProjectQueryForm gccTalentProjectQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccTalentProjectService exportExcel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(GccBaseBatchForm form) {
                    log.error("GccTalentProjectService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}