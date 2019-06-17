package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ApprovalProcessClient;
import com.deloitte.platform.api.project.param.ApprovalProcessQueryForm;
import com.deloitte.platform.api.project.vo.ApprovalProcessForm;
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
 * @Date : Create in 2019-05-22
 * @Description :   ApprovalProcess feign客户端
 * @Modified :
 */
@FeignClient(name = "approvalProcess-service", fallbackFactory = ApprovalProcessFeignService.HystrixApprovalProcessService.class,primary = false)
public interface ApprovalProcessFeignService extends ApprovalProcessClient {

    @Component
    @Slf4j
    class HystrixApprovalProcessService implements FallbackFactory<ApprovalProcessFeignService> {

        @Override
        public ApprovalProcessFeignService create(Throwable throwable) {
            return new ApprovalProcessFeignService() {
                @Override
                public Result add(@Valid @RequestBody ApprovalProcessForm approvalProcessForm) {
                    log.error("ApprovalProcessService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ApprovalProcessService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ApprovalProcessForm approvalProcessForm) {
                    log.error("ApprovalProcessService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ApprovalProcessService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ApprovalProcessQueryForm approvalProcessQueryForm) {
                    log.error("ApprovalProcessService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ApprovalProcessQueryForm approvalProcessQueryForm) {
                    log.error("ApprovalProcessService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}