package com.deloitte.platform.api.isump.feign;


import com.deloitte.platform.api.isump.SubordinateAccountClient;
import com.deloitte.platform.api.isump.param.SubordinateAccountQueryForm;
import com.deloitte.platform.api.isump.vo.SubordinateAccountForm;
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
 * @Description :   SubordinateAccount feign客户端
 * @Modified :
 */
@FeignClient(name = "service-isump", fallback = SubordinateAccountFeignService.HystrixSubordinateAccountService.class)
public interface SubordinateAccountFeignService extends SubordinateAccountClient {

    @Component
    @Slf4j
    class HystrixSubordinateAccountService implements SubordinateAccountFeignService {

        @Override
        public Result add(@Valid @RequestBody SubordinateAccountForm subordinateAccountForm){
            log.error("SubordinateAccountService add服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"SubordinateAccountService的add方法不可用");
        }

        @Override
        public Result delete(@PathVariable long id){
            log.error("SubordinateAccountService delete服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"SubordinateAccountService的delete方法不可用");
        }

        @Override
        public Result update(@PathVariable long id, @Valid @RequestBody SubordinateAccountForm subordinateAccountForm){
            log.error("SubordinateAccountService update服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"SubordinateAccountService的update方法不可用");
        }

        @Override
        public Result get(@PathVariable long id){
            log.error("SubordinateAccountService get服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"SubordinateAccountService的get方法不可用");
        }

        @Override
        public Result list(@Valid @RequestBody  SubordinateAccountQueryForm subordinateAccountQueryForm){
            log.error("SubordinateAccountService list服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"SubordinateAccountService的list方法不可用");
        }

        @Override
        public Result search(@Valid @RequestBody SubordinateAccountQueryForm subordinateAccountQueryForm){
            log.error("SubordinateAccountService search服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"SubordinateAccountService的search方法不可用");
        }
    }
}