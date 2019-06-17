package com.deloitte.platform.api.oaservice.feign;


import com.deloitte.platform.api.oaservice.client.OaMenuFavoritesClient;
import com.deloitte.platform.api.oaservice.param.OaMenuFavoritesQueryForm;
import com.deloitte.platform.api.oaservice.vo.OaMenuFavoritesForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-05-31
 * @Description :   OaMenuFavorites feign客户端
 * @Modified :
 */
@FeignClient(name = "service-oa", fallbackFactory = OaMenuFavoritesFeignService.HystrixOaMenuFavoritesService.class,primary = false)
public interface OaMenuFavoritesFeignService extends OaMenuFavoritesClient {

    @Component
    @Slf4j
    class HystrixOaMenuFavoritesService implements FallbackFactory<OaMenuFavoritesFeignService> {

        @Override
        public OaMenuFavoritesFeignService create(Throwable throwable) {
            return new OaMenuFavoritesFeignService() {
                @Override
                public Result add(@Valid @RequestBody OaMenuFavoritesForm oaMenuFavoritesForm) {
                    log.error("OaMenuFavoritesService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OaMenuFavoritesService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OaMenuFavoritesForm oaMenuFavoritesForm) {
                    log.error("OaMenuFavoritesService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OaMenuFavoritesService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaMenuFavoritesQueryForm oaMenuFavoritesQueryForm) {
                    log.error("OaMenuFavoritesService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaMenuFavoritesQueryForm oaMenuFavoritesQueryForm) {
                    log.error("OaMenuFavoritesService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}