package com.deloitte.platform.api.isump.feign;


import com.deloitte.platform.api.isump.DeputyAccountRoleClient;
import com.deloitte.platform.api.isump.param.DeputyAccountRoleQueryForm;
import com.deloitte.platform.api.isump.vo.DeputyAccountRoleForm;
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
 * @Description :   DeputyAccountRole feign客户端
 * @Modified :
 */
@FeignClient(name = "service-isump", fallback = DeputyAccountRoleFeignService.HystrixDeputyAccountRoleService.class)
public interface DeputyAccountRoleFeignService extends DeputyAccountRoleClient {

    @Component
    @Slf4j
    class HystrixDeputyAccountRoleService implements DeputyAccountRoleFeignService {

        @Override
        public Result add(@Valid @RequestBody DeputyAccountRoleForm deputyAccountRoleForm){
            log.error("DeputyAccountRoleService add服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeputyAccountRoleService的add方法不可用");
        }

        @Override
        public Result delete(@PathVariable long id){
            log.error("DeputyAccountRoleService delete服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeputyAccountRoleService的delete方法不可用");
        }

        @Override
        public Result update(@PathVariable long id, @Valid @RequestBody DeputyAccountRoleForm deputyAccountRoleForm){
            log.error("DeputyAccountRoleService update服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeputyAccountRoleService的update方法不可用");
        }

        @Override
        public Result get(@PathVariable long id){
            log.error("DeputyAccountRoleService get服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeputyAccountRoleService的get方法不可用");
        }

        @Override
        public Result list(@Valid @RequestBody  DeputyAccountRoleQueryForm deputyAccountRoleQueryForm){
            log.error("DeputyAccountRoleService list服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeputyAccountRoleService的list方法不可用");
        }

        @Override
        public Result search(@Valid @RequestBody DeputyAccountRoleQueryForm deputyAccountRoleQueryForm){
            log.error("DeputyAccountRoleService search服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeputyAccountRoleService的search方法不可用");
        }
    }
}