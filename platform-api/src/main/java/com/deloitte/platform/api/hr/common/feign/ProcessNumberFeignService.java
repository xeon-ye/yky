package com.deloitte.platform.api.hr.common.feign;


import com.deloitte.platform.api.hr.common.client.ProcessNumberClient;
import com.deloitte.platform.api.hr.common.param.ProcessNumberQueryForm;
import com.deloitte.platform.api.hr.common.vo.ProcessNumberForm;
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
 * @Date : Create in 2019-04-15
 * @Description :   ProcessNumber feign客户端
 * @Modified :
 */
@FeignClient(name = "processNumber-service", fallbackFactory = ProcessNumberFeignService.HystrixProcessNumberService.class,primary = false)
public interface ProcessNumberFeignService extends ProcessNumberClient {

    @Component("hrHystrixProcessNumberService")
    @Slf4j
    class HystrixProcessNumberService implements FallbackFactory<ProcessNumberFeignService> {

        @Override
        public ProcessNumberFeignService create(Throwable throwable) {
            return new ProcessNumberFeignService() {
                @Override
                public Result add(@Valid @RequestBody ProcessNumberForm processNumberForm) {
                    log.error("ProcessNumberService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ProcessNumberService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ProcessNumberForm processNumberForm) {
                    log.error("ProcessNumberService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ProcessNumberService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ProcessNumberQueryForm processNumberQueryForm) {
                    log.error("ProcessNumberService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ProcessNumberQueryForm processNumberQueryForm) {
                    log.error("ProcessNumberService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}