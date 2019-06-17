package com.deloitte.platform.api.hr.employee.feign;


import com.deloitte.platform.api.hr.employee.client.IncumproveApplyClient;
import com.deloitte.platform.api.hr.employee.param.IncumproveApplyQueryForm;
import com.deloitte.platform.api.hr.employee.vo.IncumproveApplyForm;
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
 * @Description :   IncumproveApply feign客户端
 * @Modified :
 */
@FeignClient(name = "incumproveApply-service", fallbackFactory = IncumproveApplyFeignService.HystrixIncumproveApplyService.class,primary = false)
public interface IncumproveApplyFeignService extends IncumproveApplyClient {

    @Component
    @Slf4j
    class HystrixIncumproveApplyService implements FallbackFactory<IncumproveApplyFeignService> {

        @Override
        public IncumproveApplyFeignService create(Throwable throwable) {
            return new IncumproveApplyFeignService() {
                @Override
                public Result add(@Valid @RequestBody IncumproveApplyForm incumproveApplyForm) {
                    log.error("IncumproveApplyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("IncumproveApplyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody IncumproveApplyForm incumproveApplyForm) {
                    log.error("IncumproveApplyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("IncumproveApplyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody IncumproveApplyQueryForm incumproveApplyQueryForm) {
                    log.error("IncumproveApplyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody IncumproveApplyQueryForm incumproveApplyQueryForm) {
                    log.error("IncumproveApplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}