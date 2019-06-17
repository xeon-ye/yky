package com.deloitte.platform.api.isump.feign;


import com.deloitte.platform.api.isump.ProCategoryClient;
import com.deloitte.platform.api.isump.param.ProCategoryQueryForm;
import com.deloitte.platform.api.isump.vo.ProCategoryForm;
import com.deloitte.platform.api.isump.vo.ProCategoryVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-22
 * @Description :   ProCategory feign客户端
 * @Modified :
 */
@FeignClient(name = "service-isump", fallbackFactory = ProCategoryFeignService.HystrixProCategoryService.class,primary = false)
public interface ProCategoryFeignService extends ProCategoryClient {

    @Component
    @Slf4j
    class HystrixProCategoryService implements FallbackFactory<ProCategoryFeignService> {

        @Override
        public ProCategoryFeignService create(Throwable throwable) {
            return new ProCategoryFeignService() {
                @Override
                public Result add(@Valid @RequestBody ProCategoryForm proCategoryForm) {
                    log.error("ProCategoryService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                @Override
                public Result getByDeputyAccountId(@PathVariable long id){
                    log.error("ProCategoryService getByDeputyAccountId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ProCategoryService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ProCategoryForm proCategoryForm) {
                    log.error("ProCategoryService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ProCategoryService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ProCategoryQueryForm proCategoryQueryForm) {
                    log.error("ProCategoryService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ProCategoryQueryForm proCategoryQueryForm) {
                    log.error("ProCategoryService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}