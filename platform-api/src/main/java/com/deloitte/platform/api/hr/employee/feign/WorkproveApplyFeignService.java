package com.deloitte.platform.api.hr.employee.feign;


import com.deloitte.platform.api.hr.employee.client.WorkproveApplyClient;
import com.deloitte.platform.api.hr.employee.param.WorkproveApplyQueryForm;
import com.deloitte.platform.api.hr.employee.vo.WorkproveApplyForm;
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
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-08
 * @Description :   WorkproveApply feign客户端
 * @Modified :
 */
@FeignClient(name = "workproveApply-service", fallbackFactory = WorkproveApplyFeignService.HystrixWorkproveApplyService.class,primary = false)
public interface WorkproveApplyFeignService extends WorkproveApplyClient {

    @Component
    @Slf4j
    class HystrixWorkproveApplyService implements FallbackFactory<WorkproveApplyFeignService> {

        @Override
        public WorkproveApplyFeignService create(Throwable throwable) {
            return new WorkproveApplyFeignService() {
                @Override
                public Result add(@Valid @RequestBody WorkproveApplyForm workproveApplyForm) {
                    log.error("WorkproveApplyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("WorkproveApplyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody WorkproveApplyForm workproveApplyForm) {
                    log.error("WorkproveApplyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("WorkproveApplyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody WorkproveApplyQueryForm workproveApplyQueryForm) {
                    log.error("WorkproveApplyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody WorkproveApplyQueryForm workproveApplyQueryForm) {
                    log.error("WorkproveApplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}