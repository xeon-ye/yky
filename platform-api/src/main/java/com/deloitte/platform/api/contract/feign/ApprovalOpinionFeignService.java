package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.ApprovalOpinionClient;
import com.deloitte.platform.api.contract.param.ApprovalOpinionQueryForm;
import com.deloitte.platform.api.contract.vo.ApprovalOpinionForm;
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
 * @Date : Create in 2019-04-02
 * @Description :   ApprovalOpinion feign客户端
 * @Modified :
 */
@FeignClient(name = "approvalOpinion-service", fallbackFactory = ApprovalOpinionFeignService.HystrixApprovalOpinionService.class,primary = false)
public interface ApprovalOpinionFeignService extends ApprovalOpinionClient {

    @Component
    @Slf4j
    class HystrixApprovalOpinionService implements FallbackFactory<ApprovalOpinionFeignService> {

        @Override
        public ApprovalOpinionFeignService create(Throwable throwable) {
            return new ApprovalOpinionFeignService() {
                @Override
                public Result add(@Valid @RequestBody ApprovalOpinionForm approvalOpinionForm) {
                    log.error("ApprovalOpinionService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ApprovalOpinionService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ApprovalOpinionForm approvalOpinionForm) {
                    log.error("ApprovalOpinionService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ApprovalOpinionService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ApprovalOpinionQueryForm approvalOpinionQueryForm) {
                    log.error("ApprovalOpinionService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ApprovalOpinionQueryForm approvalOpinionQueryForm) {
                    log.error("ApprovalOpinionService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}