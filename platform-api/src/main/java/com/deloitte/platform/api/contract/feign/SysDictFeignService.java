package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.SysDictClient;
import com.deloitte.platform.api.contract.param.SysDictQueryForm;
import com.deloitte.platform.api.contract.vo.SysDictForm;
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
 * @Description :   SysDict feign客户端
 * @Modified :
 */
@FeignClient(name = "sysDict-service", fallbackFactory = SysDictFeignService.HystrixSysDictService.class,primary = false)
public interface SysDictFeignService extends SysDictClient {

    @Component
    @Slf4j
    class HystrixSysDictService implements FallbackFactory<SysDictFeignService> {

        @Override
        public SysDictFeignService create(Throwable throwable) {
            return new SysDictFeignService() {
                @Override
                public Result add(@Valid @RequestBody SysDictForm sysDictForm) {
                    log.error("SysDictService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("SysDictService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody SysDictForm sysDictForm) {
                    log.error("SysDictService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("SysDictService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody SysDictQueryForm sysDictQueryForm) {
                    log.error("SysDictService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody SysDictQueryForm sysDictQueryForm) {
                    log.error("SysDictService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}