package com.deloitte.platform.api.bpmservice.feign;

import com.deloitte.platform.api.bpmservice.clinet.ProcessOperatorClient;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessOperatorApprove;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessOperatorFormStart;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskFormApprove;
import com.deloitte.platform.api.bpmservice.vo.BpmTaskNextNodeForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author : jackliu
 * @Date : Create in 15:45 11/03/2019
 * @Description :
 * @Modified :
 */
@FeignClient(name = "BPM-SERVICE", fallbackFactory = BpmOperatorFeignService.HystrixOperatorService.class,primary = false )
public interface BpmOperatorFeignService extends ProcessOperatorClient{

    @Component
    @Slf4j
    class HystrixOperatorService implements FallbackFactory<BpmOperatorFeignService> {
        @Override
        public BpmOperatorFeignService create(Throwable throwable) {
            return new BpmOperatorFeignService() {
                @Override
                public Result startProcess(BpmProcessOperatorFormStart bpmProcessParamForm) {
                    log.error("BpmOperatorFeignService startProcess服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result approveProcess(BpmProcessOperatorApprove bpmProcessParamForm) {
                    log.error("BpmOperatorFeignService approveProcess服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result approveProcessToNode(BpmProcessOperatorApprove bpmProcessParamForm) {
                    log.error("BpmOperatorFeignService approveProcessToNode服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result signTask(@PathVariable(value="id") long id){
                    log.error("BpmOperatorFeignService signTask服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result rejectToFirstProcess(BpmProcessOperatorApprove bpmProcessParamForm) {
                    log.error("BpmOperatorFeignService rejectToFirstProcess服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result rejectProcess(@PathVariable(value = "destTaskKey") String destTaskKey, @Valid @RequestBody BpmProcessOperatorApprove bpmProcessParamForm) {
                    log.error("BpmOperatorFeignService rejectProcess服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result turnToProcess(BpmProcessOperatorApprove bpmProcessParamForm) {
                    log.error("BpmOperatorFeignService turnToProcess服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result stopProcess(BpmProcessTaskFormApprove bpmProcessTask) {
                    log.error("BpmOperatorFeignService stopProcess服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result rollBackProcess(BpmProcessOperatorApprove bpmProcessTask) {
                    log.error("BpmOperatorFeignService rollBackProcess服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result findNextNodeList(@Valid @RequestBody BpmTaskNextNodeForm bpmTaskNextNodeForm){
                    log.error("BpmOperatorFeignService findNextNodeList 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}
