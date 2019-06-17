package com.deloitte.platform.api.management.feign;


import com.deloitte.platform.api.management.client.SysAccountClient;
import com.deloitte.platform.api.management.param.SysAccountQueryForm;
import com.deloitte.platform.api.management.vo.AccountInfoVo;
import com.deloitte.platform.api.management.vo.SysAccountForm;
import com.deloitte.platform.api.management.vo.SysAccountVo;
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
 * @Author : jack
 * @Date : Create in 2019-04-18
 * @Description :   SysAccount feign客户端
 * @Modified :
 */
@FeignClient(name = "service-management", fallbackFactory = SysAccountFeignService.HystrixSysAccountService.class,primary = false)
public interface SysAccountFeignService extends SysAccountClient {

    @Component
    @Slf4j
    class HystrixSysAccountService implements FallbackFactory<SysAccountFeignService> {

        @Override
        public SysAccountFeignService create(Throwable throwable) {
            return new SysAccountFeignService() {
                @Override
                public Result add(@Valid @RequestBody SysAccountForm sysAccountForm) {
                    log.error("SysAccountService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("SysAccountService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody SysAccountForm sysAccountForm) {
                    log.error("SysAccountService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("SysAccountService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<AccountInfoVo> getByName(@PathVariable(value="name") String  name){
                    log.error("SysAccountService getByName服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody SysAccountQueryForm sysAccountQueryForm) {
                    log.error("SysAccountService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody SysAccountQueryForm sysAccountQueryForm) {
                    log.error("SysAccountService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}