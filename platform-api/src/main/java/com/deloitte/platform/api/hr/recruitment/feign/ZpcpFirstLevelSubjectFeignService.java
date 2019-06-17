package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.ZpcpFirstLevelSubjectClient;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpFirstLevelSubjectQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpFirstLevelSubjectForm;
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
 * @Author : tankui
 * @Date : Create in 2019-04-10
 * @Description :   ZpcpFirstLevelSubject feign客户端
 * @Modified :
 */
@FeignClient(name = "zpcpFirstLevelSubject-service", fallbackFactory = ZpcpFirstLevelSubjectFeignService.HystrixZpcpFirstLevelSubjectService.class,primary = false)
public interface ZpcpFirstLevelSubjectFeignService extends ZpcpFirstLevelSubjectClient {

    @Component
    @Slf4j
    class HystrixZpcpFirstLevelSubjectService implements FallbackFactory<ZpcpFirstLevelSubjectFeignService> {

        @Override
        public ZpcpFirstLevelSubjectFeignService create(Throwable throwable) {
            return new ZpcpFirstLevelSubjectFeignService() {
                @Override
                public Result add(@Valid @RequestBody ZpcpFirstLevelSubjectForm zpcpFirstLevelSubjectForm) {
                    log.error("ZpcpFirstLevelSubjectService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ZpcpFirstLevelSubjectService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ZpcpFirstLevelSubjectForm zpcpFirstLevelSubjectForm) {
                    log.error("ZpcpFirstLevelSubjectService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ZpcpFirstLevelSubjectService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list() {
                    log.error("ZpcpFirstLevelSubjectService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ZpcpFirstLevelSubjectQueryForm zpcpFirstLevelSubjectQueryForm) {
                    log.error("ZpcpFirstLevelSubjectService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}