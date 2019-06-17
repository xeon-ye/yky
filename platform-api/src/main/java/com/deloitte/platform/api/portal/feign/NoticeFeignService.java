package com.deloitte.platform.api.portal.feign;


import com.deloitte.platform.api.portal.client.NoticeClient;
import com.deloitte.platform.api.portal.param.NoticeQueryForm;
import com.deloitte.platform.api.portal.vo.NoticeForm;
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
 * @Date : Create in 2019-04-03
 * @Description :   Notice feign客户端
 * @Modified :
 */
@FeignClient(name = "notice-service", fallbackFactory = NoticeFeignService.HystrixNoticeService.class, primary = false)
public interface NoticeFeignService extends NoticeClient {

    @Component
    @Slf4j
    class HystrixNoticeService implements FallbackFactory<NoticeFeignService> {

        @Override
        public NoticeFeignService create(Throwable throwable) {
            return new NoticeFeignService() {
                @Override
                public Result add(@Valid @RequestBody NoticeForm noticeForm) {
                    log.error("NoticeService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("NoticeService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody NoticeForm noticeForm) {
                    log.error("NoticeService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("NoticeService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody NoticeQueryForm noticeQueryForm) {
                    log.error("NoticeService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody NoticeQueryForm noticeQueryForm) {
                    log.error("NoticeService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result getHomeList(@PathVariable int num) {
                    log.error("NoticeService getHomeList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}