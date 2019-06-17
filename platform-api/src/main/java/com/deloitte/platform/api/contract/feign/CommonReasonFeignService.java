package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.CommonReasonClient;
import com.deloitte.platform.api.contract.param.CommonReasonQueryForm;
import com.deloitte.platform.api.contract.vo.CommonReasonForm;
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
 * @Author : zhengchun
 * @Date : Create in 2019-04-09
 * @Description :   CommonReason feign客户端
 * @Modified :
 */
@FeignClient(name = "commonReason-service", fallbackFactory = CommonReasonFeignService.HystrixCommonReasonService.class,primary = false)
public interface CommonReasonFeignService extends CommonReasonClient {

    @Component
    @Slf4j
    class HystrixCommonReasonService implements FallbackFactory<CommonReasonFeignService> {

        @Override
        public CommonReasonFeignService create(Throwable throwable) {
            return new CommonReasonFeignService() {
                @Override
                public Result add(@Valid @RequestBody CommonReasonForm commonReasonForm) {
                    log.error("CommonReasonService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CommonReasonService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CommonReasonForm commonReasonForm) {
                    log.error("CommonReasonService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CommonReasonService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CommonReasonQueryForm commonReasonQueryForm) {
                    log.error("CommonReasonService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CommonReasonQueryForm commonReasonQueryForm) {
                    log.error("CommonReasonService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}