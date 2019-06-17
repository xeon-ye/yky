package com.deloitte.platform.api.oaservice.feign;


import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.client.OaScheduleShareConfigClient;
import com.deloitte.platform.api.oaservice.param.OaScheduleShareConfigQueryForm;
import com.deloitte.platform.api.oaservice.vo.OaScheduleShareConfigForm;
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
 * @Date : Create in 2019-04-23
 * @Description :   OaScheduleShareConfig feign客户端
 * @Modified :
 */
@FeignClient(name = "service-oa", fallbackFactory = OaScheduleShareConfigFeignService.HystrixOaScheduleShareConfigService.class,primary = false)
public interface OaScheduleShareConfigFeignService extends OaScheduleShareConfigClient {

    @Component
    @Slf4j
    class HystrixOaScheduleShareConfigService implements FallbackFactory<OaScheduleShareConfigFeignService> {

        @Override
        public OaScheduleShareConfigFeignService create(Throwable throwable) {
            return new OaScheduleShareConfigFeignService() {

                @Override
                public Result save(@Valid @RequestBody OaScheduleShareConfigForm[] oaScheduleShareConfigForm){
                    log.error("OaScheduleShareConfigService save服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result add(@Valid @RequestBody OaScheduleShareConfigForm oaScheduleShareConfigForm) {
                    log.error("OaScheduleShareConfigService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OaScheduleShareConfigService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OaScheduleShareConfigForm oaScheduleShareConfigForm) {
                    log.error("OaScheduleShareConfigService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OaScheduleShareConfigService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaScheduleShareConfigQueryForm oaScheduleShareConfigQueryForm) {
                    log.error("OaScheduleShareConfigService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaScheduleShareConfigQueryForm oaScheduleShareConfigQueryForm) {
                    log.error("OaScheduleShareConfigService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}