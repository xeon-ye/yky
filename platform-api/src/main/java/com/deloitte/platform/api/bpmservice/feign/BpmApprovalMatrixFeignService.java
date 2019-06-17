package com.deloitte.platform.api.bpmservice.feign;


import com.deloitte.platform.api.bpmservice.clinet.BpmApprovalMatrixClient;
import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryFormForApproval;
import com.deloitte.platform.api.bpmservice.vo.BpmApprovalMatrixForm;
import com.deloitte.platform.api.bpmservice.vo.BpmApprovalMatrixVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.GdcPage;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 2019-03-16
 * @Description :   BpmApprovalMatrix feign客户端
 * @Modified :
 */
@FeignClient(name = "bpm-service", fallbackFactory = BpmApprovalMatrixFeignService.HystrixBpmApprovalMatrixService.class,primary = false)
public interface BpmApprovalMatrixFeignService extends BpmApprovalMatrixClient {

    @Component
    @Slf4j
    class HystrixBpmApprovalMatrixService implements FallbackFactory<BpmApprovalMatrixFeignService> {

        @Override
        public BpmApprovalMatrixFeignService create(Throwable throwable) {
            return new BpmApprovalMatrixFeignService() {
                @Override
                public Result add(@Valid @RequestBody BpmApprovalMatrixForm bpmApprovalMatrixForm) {
                    log.error("BpmApprovalMatrixService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result save(@Valid @RequestBody BpmApprovalMatrixForm[] bpmApprovalMatrixForms){
                    log.error("BpmApprovalMatrixService save 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("BpmApprovalMatrixService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody BpmApprovalMatrixForm bpmApprovalMatrixForm) {
                    log.error("BpmApprovalMatrixService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("BpmApprovalMatrixService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody BpmApprovalMatrixQueryForm bpmApprovalMatrixQueryForm) {
                    log.error("BpmApprovalMatrixService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody BpmApprovalMatrixQueryForm bpmApprovalMatrixQueryForm) {
                    log.error("BpmApprovalMatrixService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }


                @Override
                public Result<List<BpmApprovalMatrixVo>> findNextApprover(BpmApprovalMatrixQueryFormForApproval bpmApprovalMatrixForm) {
                    log.error("BpmApprovalMatrixService findNextApprover服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<BpmApprovalMatrixVo>> findThisApprover(BpmApprovalMatrixQueryFormForApproval bpmApprovalMatrixForm) {
                    log.error("BpmApprovalMatrixService findThisApprover服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<GdcPage<BpmApprovalMatrixVo>> findNextApproverPage(BpmApprovalMatrixQueryFormForApproval bpmApprovalMatrixForm) {
                    log.error("BpmApprovalMatrixService ffindNextApproverPage服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<GdcPage<BpmApprovalMatrixVo>> findThisApproverPage(BpmApprovalMatrixQueryFormForApproval bpmApprovalMatrixForm) {
                    log.error("BpmApprovalMatrixService findThisApproverPage服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}