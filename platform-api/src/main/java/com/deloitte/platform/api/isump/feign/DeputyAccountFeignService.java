package com.deloitte.platform.api.isump.feign;


import com.deloitte.platform.api.isump.DeputyAccountClient;
import com.deloitte.platform.api.isump.param.DeputyAccountQueryForm;
import com.deloitte.platform.api.isump.vo.DeputyAccountForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   DeputyAccount feign客户端
 * @Modified :
 */
@FeignClient(name = "service-isump", fallback = DeputyAccountFeignService.HystrixDeputyAccountService.class)
public interface DeputyAccountFeignService extends DeputyAccountClient {

    @Component
    @Slf4j
    class HystrixDeputyAccountService implements DeputyAccountFeignService {

        @Override
        public Result add(@Valid @RequestBody DeputyAccountForm deputyAccountForm){
            log.error("DeputyAccountService add服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeputyAccountService的add方法不可用");
        }

        @Override
        public Result delete(@PathVariable long id){
            log.error("DeputyAccountService delete服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeputyAccountService的delete方法不可用");
        }

        @Override
        public Result update(@PathVariable long id, @Valid @RequestBody DeputyAccountForm deputyAccountForm){
            log.error("DeputyAccountService update服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeputyAccountService的update方法不可用");
        }

        @Override
        public Result get(@PathVariable long id){
            log.error("DeputyAccountService get服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeputyAccountService的get方法不可用");
        }

        @Override
        public Result list(@Valid @RequestBody  DeputyAccountQueryForm deputyAccountQueryForm){
            log.error("DeputyAccountService list服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeputyAccountService的list方法不可用");
        }

        @Override
        public Result search(@Valid @RequestBody DeputyAccountQueryForm deputyAccountQueryForm){
            log.error("DeputyAccountService search服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeputyAccountService的search方法不可用");
        }
    }
}