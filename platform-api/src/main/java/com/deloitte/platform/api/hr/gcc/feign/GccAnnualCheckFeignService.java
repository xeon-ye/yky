package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccAnnualCheckClient;
import com.deloitte.platform.api.hr.gcc.param.GccAnnualCheckQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccAnnualCheckForm;
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
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccAnnualCheck feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccAnnualCheckFeignService.HystrixGccAnnualCheckService.class,primary = false)
public interface GccAnnualCheckFeignService extends GccAnnualCheckClient {

    @Component
    @Slf4j
    class HystrixGccAnnualCheckService implements FallbackFactory<GccAnnualCheckFeignService> {

        @Override
        public GccAnnualCheckFeignService create(Throwable throwable) {
            return new GccAnnualCheckFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccAnnualCheckForm gccAnnualCheckForm) {
                    log.error("GccAnnualCheckService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccAnnualCheckService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccAnnualCheckForm gccAnnualCheckForm) {
                    log.error("GccAnnualCheckService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccAnnualCheckService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccAnnualCheckQueryForm gccAnnualCheckQueryForm) {
                    log.error("GccAnnualCheckService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccAnnualCheckQueryForm gccAnnualCheckQueryForm) {
                    log.error("GccAnnualCheckService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}