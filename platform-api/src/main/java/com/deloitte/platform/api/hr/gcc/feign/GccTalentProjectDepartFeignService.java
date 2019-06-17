package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccTalentProjectDepartClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccTalentProjectDepartQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTalentProjectDepartForm;
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
 * @Description :   GccTalentProjectDepart feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccTalentProjectDepartFeignService.HystrixGccTalentProjectDepartService.class,primary = false)
public interface GccTalentProjectDepartFeignService extends GccTalentProjectDepartClient {

    @Component
    @Slf4j
    class HystrixGccTalentProjectDepartService implements FallbackFactory<GccTalentProjectDepartFeignService> {

        @Override
        public GccTalentProjectDepartFeignService create(Throwable throwable) {
            return new GccTalentProjectDepartFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccTalentProjectDepartForm gccTalentProjectDepartForm) {
                    log.error("GccTalentProjectDepartService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccTalentProjectDepartService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(GccBaseBatchForm form) {
                    log.error("GccTalentProjectDepartService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccTalentProjectDepartForm gccTalentProjectDepartForm) {
                    log.error("GccTalentProjectDepartService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccTalentProjectDepartService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccTalentProjectDepartQueryForm gccTalentProjectDepartQueryForm) {
                    log.error("GccTalentProjectDepartService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccTalentProjectDepartQueryForm gccTalentProjectDepartQueryForm) {
                    log.error("GccTalentProjectDepartService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportExcel(GccTalentProjectDepartQueryForm gccTalentProjectDepartQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccTalentProjectDepartService exportExcel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}