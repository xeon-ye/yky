package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.ZpcpSecondLevelSubjectClient;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpSecondLevelSubjectQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpSecondLevelSubjectForm;
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
 * @Description :   ZpcpSecondLevelSubject feign客户端
 * @Modified :
 */
@FeignClient(name = "zpcpSecondLevelSubject-service", fallbackFactory = ZpcpSecondLevelSubjectFeignService.HystrixZpcpSecondLevelSubjectService.class,primary = false)
public interface ZpcpSecondLevelSubjectFeignService extends ZpcpSecondLevelSubjectClient {

    @Component
    @Slf4j
    class HystrixZpcpSecondLevelSubjectService implements FallbackFactory<ZpcpSecondLevelSubjectFeignService> {

        @Override
        public ZpcpSecondLevelSubjectFeignService create(Throwable throwable) {
            return new ZpcpSecondLevelSubjectFeignService() {
                @Override
                public Result add(@Valid @RequestBody ZpcpSecondLevelSubjectForm zpcpSecondLevelSubjectForm) {
                    log.error("ZpcpSecondLevelSubjectService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ZpcpSecondLevelSubjectService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ZpcpSecondLevelSubjectForm zpcpSecondLevelSubjectForm) {
                    log.error("ZpcpSecondLevelSubjectService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ZpcpSecondLevelSubjectService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ZpcpSecondLevelSubjectQueryForm zpcpSecondLevelSubjectQueryForm) {
                    log.error("ZpcpSecondLevelSubjectService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ZpcpSecondLevelSubjectQueryForm zpcpSecondLevelSubjectQueryForm) {
                    log.error("ZpcpSecondLevelSubjectService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}