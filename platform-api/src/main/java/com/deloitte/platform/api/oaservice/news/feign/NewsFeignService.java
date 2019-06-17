package com.deloitte.platform.api.oaservice.news.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.news.client.NewsClient;
import com.deloitte.platform.api.oaservice.news.param.NewsQueryForm;
import com.deloitte.platform.api.oaservice.news.vo.NewsForm;
import com.deloitte.platform.api.oaservice.news.vo.NewsProcessForm;
import com.deloitte.platform.api.oaservice.news.vo.NewsVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.GdcPage;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-12
 * @Description :   News feign客户端
 * @Modified :
 */
@FeignClient(name = "news-service", fallbackFactory = NewsFeignService.HystrixNewsService.class, primary = false)
public interface NewsFeignService extends NewsClient {

    @Component
    @Slf4j
    class HystrixNewsService implements FallbackFactory<NewsFeignService> {

        @Override
        public NewsFeignService create(Throwable throwable) {
            return new NewsFeignService() {
                @Override
                public Result add(@Valid @RequestBody NewsForm newsForm) {
                    log.error("NewsService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("NewsService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody NewsForm newsForm) {
                    log.error("NewsService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable String id) {
                    log.error("NewsService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

//                @Override
//                public Result submitProcess(@Valid @RequestBody NewsForm newsForm) {
//                    log.error("NewsService get服务不可用......");
//                    throwable.printStackTrace();
//                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
//                }
//
//                @Override
//                public Result approval(@Valid @RequestBody NewsProcessForm newsProcessForm) {
//                    log.error("NewsService get服务不可用......");
//                    throwable.printStackTrace();
//                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
//                }

                @Override
                public Result list(@Valid @RequestBody NewsQueryForm newsQueryForm) {
                    log.error("NewsService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody NewsQueryForm newsQueryForm, @RequestParam int currentPage, @RequestParam int pageSize) {
                    log.error("NewsService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result homeList(@PathVariable Integer num, @PathVariable String newsTypeCode) {
                    log.error("NewsService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result<GdcPage<NewsVo>> manageSearch(NewsQueryForm newsQueryForm, int currentPage, int pageSize) {
                    log.error("NewsService manageSearch服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result<IPage<NewsVo>> searchWithPermission(NewsQueryForm newsQueryForm, String token) {
                    log.error("NewsService searchWithPermission服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}