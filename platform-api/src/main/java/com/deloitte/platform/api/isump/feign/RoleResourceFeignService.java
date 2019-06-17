package com.deloitte.platform.api.isump.feign;


import com.deloitte.platform.api.isump.RoleResourceClient;
import com.deloitte.platform.api.isump.param.RoleResourceQueryForm;
import com.deloitte.platform.api.isump.vo.RoleResourceForm;
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
 * @Description :   RoleResource feign客户端
 * @Modified :
 */
@FeignClient(name = "service-isump", fallback = RoleResourceFeignService.HystrixRoleResourceService.class)
public interface RoleResourceFeignService extends RoleResourceClient {

    @Component
    @Slf4j
    class HystrixRoleResourceService implements RoleResourceFeignService {

        @Override
        public Result add(@Valid @RequestBody RoleResourceForm roleResourceForm){
            log.error("RoleResourceService add服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"RoleResourceService的add方法不可用");
        }

        @Override
        public Result delete(@PathVariable long id){
            log.error("RoleResourceService delete服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"RoleResourceService的delete方法不可用");
        }

        @Override
        public Result update(@PathVariable long id, @Valid @RequestBody RoleResourceForm roleResourceForm){
            log.error("RoleResourceService update服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"RoleResourceService的update方法不可用");
        }

        @Override
        public Result get(@PathVariable long id){
            log.error("RoleResourceService get服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"RoleResourceService的get方法不可用");
        }

        @Override
        public Result list(@Valid @RequestBody  RoleResourceQueryForm roleResourceQueryForm){
            log.error("RoleResourceService list服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"RoleResourceService的list方法不可用");
        }

        @Override
        public Result search(@Valid @RequestBody RoleResourceQueryForm roleResourceQueryForm){
            log.error("RoleResourceService search服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"RoleResourceService的search方法不可用");
        }
    }
}