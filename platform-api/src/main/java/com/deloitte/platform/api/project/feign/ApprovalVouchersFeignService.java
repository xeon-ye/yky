package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ApprovalVouchersClient;
import com.deloitte.platform.api.project.param.ApprovalVouchersQueryForm;
import com.deloitte.platform.api.project.vo.ApprovalVouchersForm;
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
 * @Date : Create in 2019-06-11
 * @Description :   ApprovalVouchers feign客户端
 * @Modified :
 */
@FeignClient(name = "approvalVouchers-service", fallbackFactory = ApprovalVouchersFeignService.HystrixApprovalVouchersService.class,primary = false)
public interface ApprovalVouchersFeignService extends ApprovalVouchersClient {

    @Component
    @Slf4j
    class HystrixApprovalVouchersService implements FallbackFactory<ApprovalVouchersFeignService> {

        @Override
        public ApprovalVouchersFeignService create(Throwable throwable) {
            return new ApprovalVouchersFeignService() {
                @Override
                public Result add(@Valid @RequestBody ApprovalVouchersForm approvalVouchersForm) {
                    log.error("ApprovalVouchersService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ApprovalVouchersService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ApprovalVouchersForm approvalVouchersForm) {
                    log.error("ApprovalVouchersService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ApprovalVouchersService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ApprovalVouchersQueryForm approvalVouchersQueryForm) {
                    log.error("ApprovalVouchersService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ApprovalVouchersQueryForm approvalVouchersQueryForm) {
                    log.error("ApprovalVouchersService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}