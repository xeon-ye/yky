package com.deloitte.platform.api.oaservice.feign;


import com.deloitte.platform.api.oaservice.client.OaScheduleHistoryClient;
import com.deloitte.platform.api.oaservice.param.OaScheduleHistoryQueryForm;
import com.deloitte.platform.api.oaservice.vo.OaScheduleHistoryForm;
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
 * @Date : Create in 2019-03-26
 * @Description :   OaScheduleHistory feign客户端
 * @Modified :
 */
@FeignClient(name = "service-oa", fallbackFactory = OaScheduleHistoryFeignService.HystrixOaScheduleHistoryService.class,primary = false)
public interface OaScheduleHistoryFeignService extends OaScheduleHistoryClient {

    @Component
    @Slf4j
    class HystrixOaScheduleHistoryService implements FallbackFactory<OaScheduleHistoryFeignService> {

        @Override
        public OaScheduleHistoryFeignService create(Throwable throwable) {
            return new OaScheduleHistoryFeignService() {
                @Override
                public Result add(@Valid @RequestBody OaScheduleHistoryForm oaScheduleHistoryForm) {
                    log.error("OaScheduleHistoryService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable String id) {
                    log.error("OaScheduleHistoryService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable String id, @Valid @RequestBody OaScheduleHistoryForm oaScheduleHistoryForm) {
                    log.error("OaScheduleHistoryService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable String id) {
                    log.error("OaScheduleHistoryService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }


                @Override
                public Result list(@Valid @RequestBody OaScheduleHistoryQueryForm oaScheduleHistoryQueryForm) {
                    log.error("OaScheduleHistoryService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaScheduleHistoryQueryForm oaScheduleHistoryQueryForm) {
                    log.error("OaScheduleHistoryService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}