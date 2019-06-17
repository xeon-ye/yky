package com.deloitte.platform.api.oaservice.notice.feign;

import com.deloitte.platform.api.oaservice.notice.client.OaInfochangeClient;
import com.deloitte.platform.api.oaservice.notice.param.OaInfochangeQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaInfochangeForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaInfochangeVo;
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
 * @Description :   OaInfochange feign客户端
 * @Modified :
 */
@FeignClient(name = "oaInfochange-service", fallbackFactory = OaInfochangeFeignService.HystrixOaInfochangeService.class,primary = false)
public interface OaInfochangeFeignService extends OaInfochangeClient {

    @Component
    @Slf4j
    class HystrixOaInfochangeService implements FallbackFactory<OaInfochangeFeignService> {

        @Override
        public OaInfochangeFeignService create(Throwable throwable) {
            return new OaInfochangeFeignService() {
                @Override
                public Result add(@Valid @RequestBody OaInfochangeForm oaInfochangeForm) {
                    log.error("OaInfochangeService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OaInfochangeService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OaInfochangeForm oaInfochangeForm) {
                    log.error("OaInfochangeService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OaInfochangeService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaInfochangeQueryForm oaInfochangeQueryForm) {
                    log.error("OaInfochangeService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaInfochangeQueryForm oaInfochangeQueryForm) {
                    log.error("OaInfochangeService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<OaInfochangeVo>> home(Integer num) {
                    log.error("OaInfochangeService home list 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}