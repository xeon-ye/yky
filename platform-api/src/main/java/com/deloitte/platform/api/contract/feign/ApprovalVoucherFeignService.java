package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.ApprovalVoucherClient;
import com.deloitte.platform.api.contract.param.ApprovalVoucherQueryForm;
import com.deloitte.platform.api.contract.vo.ApprovalVoucherForm;
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
 * @Date : Create in 2019-03-27
 * @Description :   ApprovalVoucher feign客户端
 * @Modified :
 */
@FeignClient(name = "approvalVoucher-service", fallbackFactory = ApprovalVoucherFeignService.HystrixApprovalVoucherService.class,primary = false)
public interface ApprovalVoucherFeignService extends ApprovalVoucherClient {

    @Component
    @Slf4j
    class HystrixApprovalVoucherService implements FallbackFactory<ApprovalVoucherFeignService> {

        @Override
        public ApprovalVoucherFeignService create(Throwable throwable) {
            return new ApprovalVoucherFeignService() {
                @Override
                public Result add(@Valid @RequestBody ApprovalVoucherForm approvalVoucherForm) {
                    log.error("ApprovalVoucherService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ApprovalVoucherService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ApprovalVoucherForm approvalVoucherForm) {
                    log.error("ApprovalVoucherService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ApprovalVoucherService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ApprovalVoucherQueryForm approvalVoucherQueryForm) {
                    log.error("ApprovalVoucherService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ApprovalVoucherQueryForm approvalVoucherQueryForm) {
                    log.error("ApprovalVoucherService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}