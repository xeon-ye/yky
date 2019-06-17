package com.deloitte.platform.api.isump.feign;


import com.deloitte.platform.api.isump.RoleClient;
import com.deloitte.platform.api.isump.param.RoleQueryForm;
import com.deloitte.platform.api.isump.vo.RoleForm;
import com.deloitte.platform.api.isump.vo.RoleVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-22
 * @Description :   Role feign客户端
 * @Modified :
 */
@FeignClient(name = "service-isump", fallbackFactory = RoleFeignService.HystrixRoleService.class,primary = false)
public interface RoleFeignService extends RoleClient {

    @Component
    @Slf4j
    class HystrixRoleService implements FallbackFactory<RoleFeignService> {

        @Override
        public RoleFeignService create(Throwable throwable) {
            return new RoleFeignService() {
                @Override
                public Result add(@Valid @RequestBody RoleForm roleForm) {
                    log.error("RoleService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("RoleService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody RoleForm roleForm) {
                    log.error("RoleService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("RoleService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody RoleQueryForm roleQueryForm) {
                    log.error("RoleService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody RoleQueryForm roleQueryForm) {
                    log.error("RoleService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                @Override
                public Result<List<RoleVo>> getByDeputyAccountId(@RequestParam long id, @RequestParam String type, @RequestParam String service){
                    log.error("RoleService getByDeputyAccountId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}