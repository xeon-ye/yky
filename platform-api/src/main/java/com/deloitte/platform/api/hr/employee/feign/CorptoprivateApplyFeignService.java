package com.deloitte.platform.api.hr.employee.feign;


import com.deloitte.platform.api.hr.employee.client.CorptoprivateApplyClient;
import com.deloitte.platform.api.hr.employee.param.CorptoprivateApplyQueryForm;
import com.deloitte.platform.api.hr.employee.vo.CorptoprivateApplyForm;
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
 * @Description :   CorptoprivateApply feign客户端
 * @Modified :
 */
@FeignClient(name = "corptoprivateApply-service", fallbackFactory = CorptoprivateApplyFeignService.HystrixCorptoprivateApplyService.class,primary = false)
public interface CorptoprivateApplyFeignService extends CorptoprivateApplyClient {

    @Component
    @Slf4j
    class HystrixCorptoprivateApplyService implements FallbackFactory<CorptoprivateApplyFeignService> {

        @Override
        public CorptoprivateApplyFeignService create(Throwable throwable) {
            return new CorptoprivateApplyFeignService() {
                @Override
                public Result add(@Valid @RequestBody CorptoprivateApplyForm corptoprivateApplyForm) {
                    log.error("CorptoprivateApplyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CorptoprivateApplyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CorptoprivateApplyForm corptoprivateApplyForm) {
                    log.error("CorptoprivateApplyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CorptoprivateApplyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CorptoprivateApplyQueryForm corptoprivateApplyQueryForm) {
                    log.error("CorptoprivateApplyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CorptoprivateApplyQueryForm corptoprivateApplyQueryForm) {
                    log.error("CorptoprivateApplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}