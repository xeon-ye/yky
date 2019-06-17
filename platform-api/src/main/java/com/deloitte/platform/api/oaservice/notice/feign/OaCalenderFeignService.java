package com.deloitte.platform.api.oaservice.notice.feign;

import com.deloitte.platform.api.oaservice.notice.client.OaCalenderClient;
import com.deloitte.platform.api.oaservice.notice.param.OaCalenderQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaCalenderForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaCalenderVo;
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
 * @Description :   OaCalender feign客户端
 * @Modified :
 */
@FeignClient(name = "oaCalender-service", fallbackFactory = OaCalenderFeignService.HystrixOaCalenderService.class,primary = false)
public interface OaCalenderFeignService extends OaCalenderClient {

    @Component
    @Slf4j
    class HystrixOaCalenderService implements FallbackFactory<OaCalenderFeignService> {

        @Override
        public OaCalenderFeignService create(Throwable throwable) {
            return new OaCalenderFeignService() {
                @Override
                public Result add(@Valid @RequestBody OaCalenderForm oaCalenderForm) {
                    log.error("OaCalenderService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OaCalenderService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OaCalenderForm oaCalenderForm) {
                    log.error("OaCalenderService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OaCalenderService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaCalenderQueryForm oaCalenderQueryForm) {
                    log.error("OaCalenderService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaCalenderQueryForm oaCalenderQueryForm) {
                    log.error("OaCalenderService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<OaCalenderVo>> home(Integer num) {
                    log.error("OaCalenderService home list 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}