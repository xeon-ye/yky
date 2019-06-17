package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccAnnualCheckUserClient;
import com.deloitte.platform.api.hr.gcc.param.GccAnnualCheckUserQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccAnnualCheckUserForm;
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
 * @Description :   GccAnnualCheckUser feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccAnnualCheckUserFeignService.HystrixGccAnnualCheckUserService.class,primary = false)
public interface GccAnnualCheckUserFeignService extends GccAnnualCheckUserClient {

    @Component
    @Slf4j
    class HystrixGccAnnualCheckUserService implements FallbackFactory<GccAnnualCheckUserFeignService> {

        @Override
        public GccAnnualCheckUserFeignService create(Throwable throwable) {
            return new GccAnnualCheckUserFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccAnnualCheckUserForm gccAnnualCheckUserForm) {
                    log.error("GccAnnualCheckUserService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccAnnualCheckUserService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccAnnualCheckUserForm gccAnnualCheckUserForm) {
                    log.error("GccAnnualCheckUserService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccAnnualCheckUserService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccAnnualCheckUserQueryForm gccAnnualCheckUserQueryForm) {
                    log.error("GccAnnualCheckUserService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccAnnualCheckUserQueryForm gccAnnualCheckUserQueryForm) {
                    log.error("GccAnnualCheckUserService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}