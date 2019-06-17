package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccIncludeProjectClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccIncludeProjectQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccIncludeProjectForm;
import com.deloitte.platform.api.hr.gcc.vo.GccIncludeProjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccIncludeProject feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccIncludeProjectFeignService.HystrixGccIncludeProjectService.class,primary = false)
public interface GccIncludeProjectFeignService extends GccIncludeProjectClient {

    @Component
    @Slf4j
    class HystrixGccIncludeProjectService implements FallbackFactory<GccIncludeProjectFeignService> {

        @Override
        public GccIncludeProjectFeignService create(Throwable throwable) {
            return new GccIncludeProjectFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccIncludeProjectForm gccIncludeProjectForm) {
                    log.error("GccIncludeProjectService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccIncludeProjectService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccIncludeProjectForm gccIncludeProjectForm) {
                    log.error("GccIncludeProjectService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccIncludeProjectService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccIncludeProjectQueryForm gccIncludeProjectQueryForm) {
                    log.error("GccIncludeProjectService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccIncludeProjectQueryForm gccIncludeProjectQueryForm) {
                    log.error("GccIncludeProjectService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<GccIncludeProjectVo>> getListByUserId(Long userId) {
                    log.error("GccIncludeProjectService getListByUserId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(GccBaseBatchForm form) {
                    return null;
                }

                @Override
                public Result addOrUpdateList(List<GccIncludeProjectForm> talentProjectForms) {
                    return null;
                }
            };
        }
    }
}