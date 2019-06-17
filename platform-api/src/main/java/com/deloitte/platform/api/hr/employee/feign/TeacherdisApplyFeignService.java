package com.deloitte.platform.api.hr.employee.feign;


import com.deloitte.platform.api.hr.employee.client.TeacherdisApplyClient;
import com.deloitte.platform.api.hr.employee.param.TeacherdisApplyQueryForm;
import com.deloitte.platform.api.hr.employee.vo.TeacherdisApplyForm;
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
 * @Author : woo
 * @Date : Create in 2019-05-18
 * @Description :   TeacherdisApply feign客户端
 * @Modified :
 */
@FeignClient(name = "teacherdisApply-service", fallbackFactory = TeacherdisApplyFeignService.HystrixTeacherdisApplyService.class,primary = false)
public interface TeacherdisApplyFeignService extends TeacherdisApplyClient {

    @Component
    @Slf4j
    class HystrixTeacherdisApplyService implements FallbackFactory<TeacherdisApplyFeignService> {

        @Override
        public TeacherdisApplyFeignService create(Throwable throwable) {
            return new TeacherdisApplyFeignService() {
                @Override
                public Result add(@Valid @RequestBody TeacherdisApplyForm teacherdisApplyForm) {
                    log.error("TeacherdisApplyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("TeacherdisApplyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody TeacherdisApplyForm teacherdisApplyForm) {
                    log.error("TeacherdisApplyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("TeacherdisApplyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody TeacherdisApplyQueryForm teacherdisApplyQueryForm) {
                    log.error("TeacherdisApplyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody TeacherdisApplyQueryForm teacherdisApplyQueryForm) {
                    log.error("TeacherdisApplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}