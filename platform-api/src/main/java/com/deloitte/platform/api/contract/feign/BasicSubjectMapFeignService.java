package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.BasicSubjectMapClient;
import com.deloitte.platform.api.contract.param.BasicSubjectMapQueryForm;
import com.deloitte.platform.api.contract.vo.BasicSubjectMapForm;
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
 * @Date : Create in 2019-03-26
 * @Description :   BasicSubjectMap feign客户端
 * @Modified :
 */
@FeignClient(name = "basicSubjectMap-service", fallbackFactory = BasicSubjectMapFeignService.HystrixBasicSubjectMapService.class,primary = false)
public interface BasicSubjectMapFeignService extends BasicSubjectMapClient {

    @Component
    @Slf4j
    class HystrixBasicSubjectMapService implements FallbackFactory<BasicSubjectMapFeignService> {

        @Override
        public BasicSubjectMapFeignService create(Throwable throwable) {
            return new BasicSubjectMapFeignService() {
                @Override
                public Result add(@Valid @RequestBody BasicSubjectMapForm basicSubjectMapForm) {
                    log.error("BasicSubjectMapService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("BasicSubjectMapService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody BasicSubjectMapForm basicSubjectMapForm) {
                    log.error("BasicSubjectMapService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("BasicSubjectMapService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody BasicSubjectMapQueryForm basicSubjectMapQueryForm) {
                    log.error("BasicSubjectMapService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody BasicSubjectMapQueryForm basicSubjectMapQueryForm) {
                    log.error("BasicSubjectMapService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}