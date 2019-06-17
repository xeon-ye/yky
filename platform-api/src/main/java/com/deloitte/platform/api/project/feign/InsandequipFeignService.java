package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.InsandequipClient;
import com.deloitte.platform.api.project.param.InsandequipQueryForm;
import com.deloitte.platform.api.project.vo.InsandequipForm;
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
 * @Date : Create in 2019-05-31
 * @Description :   Insandequip feign客户端
 * @Modified :
 */
@FeignClient(name = "insandequip-service", fallbackFactory = InsandequipFeignService.HystrixInsandequipService.class,primary = false)
public interface InsandequipFeignService extends InsandequipClient {

    @Component
    @Slf4j
    class HystrixInsandequipService implements FallbackFactory<InsandequipFeignService> {

        @Override
        public InsandequipFeignService create(Throwable throwable) {
            return new InsandequipFeignService() {
                @Override
                public Result add(@Valid @RequestBody InsandequipForm insandequipForm) {
                    log.error("InsandequipService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("InsandequipService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody InsandequipForm insandequipForm) {
                    log.error("InsandequipService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("InsandequipService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody InsandequipQueryForm insandequipQueryForm) {
                    log.error("InsandequipService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody InsandequipQueryForm insandequipQueryForm) {
                    log.error("InsandequipService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}