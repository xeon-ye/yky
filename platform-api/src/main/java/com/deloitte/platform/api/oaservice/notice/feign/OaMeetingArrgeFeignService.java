package com.deloitte.platform.api.oaservice.notice.feign;

import com.deloitte.platform.api.oaservice.notice.client.OaMeetingArrgeClient;
import com.deloitte.platform.api.oaservice.notice.param.OaMeetingArrgeQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaMeetingArrgeForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaMeetingArrgeVo;
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
 * @Description :   OaMeetingArrge feign客户端
 * @Modified :
 */
@FeignClient(name = "oaMeetingArrge-service", fallbackFactory = OaMeetingArrgeFeignService.HystrixOaMeetingArrgeService.class,primary = false)
public interface OaMeetingArrgeFeignService extends OaMeetingArrgeClient {

    @Component
    @Slf4j
    class HystrixOaMeetingArrgeService implements FallbackFactory<OaMeetingArrgeFeignService> {

        @Override
        public OaMeetingArrgeFeignService create(Throwable throwable) {
            return new OaMeetingArrgeFeignService() {
                @Override
                public Result add(@Valid @RequestBody OaMeetingArrgeForm oaMeetingArrgeForm) {
                    log.error("OaMeetingArrgeService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OaMeetingArrgeService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OaMeetingArrgeForm oaMeetingArrgeForm) {
                    log.error("OaMeetingArrgeService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OaMeetingArrgeService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaMeetingArrgeQueryForm oaMeetingArrgeQueryForm) {
                    log.error("OaMeetingArrgeService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaMeetingArrgeQueryForm oaMeetingArrgeQueryForm) {
                    log.error("OaMeetingArrgeService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<OaMeetingArrgeVo>> home(Integer num) {
                    log.error("OaMeetingArrgeService home list 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}