package com.deloitte.platform.api.oaservice.notice.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.notice.client.OaNoticeOtherClient;
import com.deloitte.platform.api.oaservice.notice.param.OaNoticeOtherQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeOtherForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeOtherVo;
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
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description :   OaNoticeOther feign客户端
 * @Modified :
 */
@FeignClient(name = "oaNoticeOther-service", fallbackFactory = OaNoticeOtherFeignService.HystrixOaNoticeOtherService.class,primary = false)
public interface OaNoticeOtherFeignService extends OaNoticeOtherClient {

    @Component
    @Slf4j
    class HystrixOaNoticeOtherService implements FallbackFactory<OaNoticeOtherFeignService> {

        @Override
        public OaNoticeOtherFeignService create(Throwable throwable) {
            return new OaNoticeOtherFeignService() {
                @Override
                public Result add(@Valid @RequestBody OaNoticeOtherForm oaNoticeOtherForm) {
                    log.error("OaNoticeOtherService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OaNoticeOtherService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OaNoticeOtherForm oaNoticeOtherForm) {
                    log.error("OaNoticeOtherService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OaNoticeOtherService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaNoticeOtherQueryForm oaNoticeOtherQueryForm) {
                    log.error("OaNoticeOtherService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaNoticeOtherQueryForm oaNoticeOtherQueryForm, String token) {
                    log.error("OaNoticeOtherService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<OaNoticeOtherVo>> searchWithPermission(OaNoticeOtherQueryForm oaNoticeOtherQueryForm, String token) {
                    log.error("OaNoticeOtherService search with permission 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<OaNoticeOtherVo>> home(Integer num, String typeCode, String token) {
                    log.error("OaNoticeOtherService home list 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}