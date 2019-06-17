package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccTalentProjectCategoryClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccTalentProjectCategoryQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTalentProjectCategoryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTalentProjectCategoryVo;
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
 * @Description :   GccTalentProjectCategory feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccTalentProjectCategoryFeignService.HystrixGccTalentProjectCategoryService.class,primary = false)
public interface GccTalentProjectCategoryFeignService extends GccTalentProjectCategoryClient {

    @Component
    @Slf4j
    class HystrixGccTalentProjectCategoryService implements FallbackFactory<GccTalentProjectCategoryFeignService> {

        @Override
        public GccTalentProjectCategoryFeignService create(Throwable throwable) {
            return new GccTalentProjectCategoryFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccTalentProjectCategoryForm gccTalentProjectCategoryForm) {
                    log.error("GccTalentProjectCategoryService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccTalentProjectCategoryService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccTalentProjectCategoryForm gccTalentProjectCategoryForm) {
                    log.error("GccTalentProjectCategoryService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccTalentProjectCategoryService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccTalentProjectCategoryQueryForm gccTalentProjectCategoryQueryForm) {
                    log.error("GccTalentProjectCategoryService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccTalentProjectCategoryQueryForm gccTalentProjectCategoryQueryForm) {
                    log.error("GccTalentProjectCategoryService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportExcel(GccTalentProjectCategoryQueryForm gccTalentProjectCategoryQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccTalentProjectCategoryService exportExcel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(GccBaseBatchForm form) {
                    log.error("GccTalentProjectCategoryService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<GccTalentProjectCategoryVo>> getListByProjectCode(Long projectCode) {
                    log.error("GccTalentProjectCategoryService getListByProjectCode服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}