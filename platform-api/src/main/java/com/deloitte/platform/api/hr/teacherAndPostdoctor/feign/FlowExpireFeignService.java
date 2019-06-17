package com.deloitte.platform.api.hr.teacherAndPostdoctor.feign;


import com.deloitte.platform.api.hr.teacherAndPostdoctor.client.FlowExpireClient;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.FlowExpireQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.FlowExpireForm;
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
 * @Author : jetvae
 * @Date : Create in 2019-05-14
 * @Description :   FlowExpire feign客户端
 * @Modified :
 */
@FeignClient(name = "flowExpire-service", fallbackFactory = FlowExpireFeignService.HystrixFlowExpireService.class,primary = false)
public interface FlowExpireFeignService extends FlowExpireClient {

    @Component
    @Slf4j
    class HystrixFlowExpireService implements FallbackFactory<FlowExpireFeignService> {

        @Override
        public FlowExpireFeignService create(Throwable throwable) {
            return new FlowExpireFeignService() {
                @Override
                public Result add(@Valid @RequestBody FlowExpireForm flowExpireForm) {
                    log.error("FlowExpireService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("FlowExpireService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody FlowExpireForm flowExpireForm) {
                    log.error("FlowExpireService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("FlowExpireService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody FlowExpireQueryForm flowExpireQueryForm) {
                    log.error("FlowExpireService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody FlowExpireQueryForm flowExpireQueryForm) {
                    log.error("FlowExpireService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}