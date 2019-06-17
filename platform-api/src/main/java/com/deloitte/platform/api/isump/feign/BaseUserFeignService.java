package com.deloitte.platform.api.isump.feign;


import com.deloitte.platform.api.isump.BaseUserClient;
import com.deloitte.platform.api.isump.param.BaseUserQueryForm;
import com.deloitte.platform.api.isump.vo.BaseUserForm;
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
 * @Description :   BaseUser feign客户端
 * @Modified :
 */
@FeignClient(name = "service-isump", fallback = BaseUserFeignService.HystrixBaseUserService.class)
public interface BaseUserFeignService extends BaseUserClient {

    @Component
    @Slf4j
    class HystrixBaseUserService implements BaseUserFeignService {

        @Override
        public Result add(@Valid @RequestBody BaseUserForm baseUserForm){
            log.error("BaseUserService add服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BaseUserService的add方法不可用");
        }

        @Override
        public Result delete(@PathVariable long id){
            log.error("BaseUserService delete服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BaseUserService的delete方法不可用");
        }

        @Override
        public Result update(@PathVariable long id, @Valid @RequestBody BaseUserForm baseUserForm){
            log.error("BaseUserService update服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BaseUserService的update方法不可用");
        }

        @Override
        public Result get(@PathVariable long id){
            log.error("BaseUserService get服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BaseUserService的get方法不可用");
        }

        @Override
        public Result list(@Valid @RequestBody  BaseUserQueryForm baseUserQueryForm){
            log.error("BaseUserService list服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BaseUserService的list方法不可用");
        }

        @Override
        public Result search(@Valid @RequestBody BaseUserQueryForm baseUserQueryForm){
            log.error("BaseUserService search服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BaseUserService的search方法不可用");
        }
    }
}