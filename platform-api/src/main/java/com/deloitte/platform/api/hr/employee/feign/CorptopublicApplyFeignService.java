package com.deloitte.platform.api.hr.employee.feign;


import com.deloitte.platform.api.hr.employee.client.CorptopublicApplyClient;
import com.deloitte.platform.api.hr.employee.param.CorptopublicApplyQueryForm;
import com.deloitte.platform.api.hr.employee.vo.CorptopublicApplyForm;
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
 * @Description :   CorptopublicApply feign客户端
 * @Modified :
 */
@FeignClient(name = "corptopublicApply-service", fallbackFactory = CorptopublicApplyFeignService.HystrixCorptopublicApplyService.class,primary = false)
public interface CorptopublicApplyFeignService extends CorptopublicApplyClient {

    @Component
    @Slf4j
    class HystrixCorptopublicApplyService implements FallbackFactory<CorptopublicApplyFeignService> {

        @Override
        public CorptopublicApplyFeignService create(Throwable throwable) {
            return new CorptopublicApplyFeignService() {
                @Override
                public Result add(@Valid @RequestBody CorptopublicApplyForm corptopublicApplyForm) {
                    log.error("CorptopublicApplyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CorptopublicApplyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CorptopublicApplyForm corptopublicApplyForm) {
                    log.error("CorptopublicApplyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CorptopublicApplyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CorptopublicApplyQueryForm corptopublicApplyQueryForm) {
                    log.error("CorptopublicApplyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CorptopublicApplyQueryForm corptopublicApplyQueryForm) {
                    log.error("CorptopublicApplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}