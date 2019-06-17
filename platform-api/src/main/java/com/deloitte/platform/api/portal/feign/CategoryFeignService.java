package com.deloitte.platform.api.portal.feign;


import com.deloitte.platform.api.portal.client.CategoryClient;
import com.deloitte.platform.api.portal.param.CategoryQueryForm;
import com.deloitte.platform.api.portal.vo.CategoryForm;
import com.deloitte.platform.api.portal.vo.CategoryVo;
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
 * @Author : pengchao
 * @Date : Create in 2019-04-03
 * @Description :   Category feign客户端
 * @Modified :
 */
@FeignClient(name = "category-service", fallbackFactory = CategoryFeignService.HystrixCategoryService.class,primary = false)
public interface CategoryFeignService extends CategoryClient {

    @Component
    @Slf4j
    class HystrixCategoryService implements FallbackFactory<CategoryFeignService> {

        @Override
        public CategoryFeignService create(Throwable throwable) {
            return new CategoryFeignService() {
                @Override
                public Result add(@Valid @RequestBody CategoryForm categoryForm) {
                    log.error("CategoryService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CategoryService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CategoryForm categoryForm) {
                    log.error("CategoryService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CategoryService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CategoryQueryForm categoryQueryForm) {
                    log.error("CategoryService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CategoryQueryForm categoryQueryForm) {
                    log.error("CategoryService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<CategoryVo>> selectHomeCategories() {
                    log.error("CategoryService home categories服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}