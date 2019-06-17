package com.deloitte.platform.api.portal.feign;


import com.deloitte.platform.api.portal.client.ArticleClient;
import com.deloitte.platform.api.portal.param.ArticleQueryForm;
import com.deloitte.platform.api.portal.vo.ArticleForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-02
 * @Description :   Article feign客户端
 * @Modified :
 */
@FeignClient(name = "article-service", fallbackFactory = ArticleFeignService.HystrixArticleService.class, primary = false)
public interface ArticleFeignService extends ArticleClient {

    @Component
    @Slf4j
    class HystrixArticleService implements FallbackFactory<ArticleFeignService> {

        @Override
        public ArticleFeignService create(Throwable throwable) {
            return new ArticleFeignService() {
                @Override
                public Result add(@Valid @RequestBody ArticleForm articleForm) {
                    log.error("ArticleService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ArticleService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ArticleForm articleForm) {
                    log.error("ArticleService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ArticleService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ArticleQueryForm articleQueryForm) {
                    log.error("ArticleService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ArticleQueryForm articleQueryForm) {
                    log.error("ArticleService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result getHomeList(@PathVariable int num) {
                    log.error("ArticleService getHomeList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}