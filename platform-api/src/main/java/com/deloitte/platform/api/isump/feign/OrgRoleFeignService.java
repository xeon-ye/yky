package com.deloitte.platform.api.isump.feign;


import com.deloitte.platform.api.isump.OrgRoleClient;
import com.deloitte.platform.api.isump.param.OrgRoleQueryForm;
import com.deloitte.platform.api.isump.vo.OrgRoleForm;
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
 * @Description :   OrgRole feign客户端
 * @Modified :
 */
@FeignClient(name = "service-isump", fallback = OrgRoleFeignService.HystrixOrgRoleService.class)
public interface OrgRoleFeignService extends OrgRoleClient {

    @Component
    @Slf4j
    class HystrixOrgRoleService implements OrgRoleFeignService {

        @Override
        public Result add(@Valid @RequestBody OrgRoleForm orgRoleForm){
            log.error("OrgRoleService add服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrgRoleService的add方法不可用");
        }

        @Override
        public Result delete(@PathVariable long id){
            log.error("OrgRoleService delete服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrgRoleService的delete方法不可用");
        }

        @Override
        public Result update(@PathVariable long id, @Valid @RequestBody OrgRoleForm orgRoleForm){
            log.error("OrgRoleService update服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrgRoleService的update方法不可用");
        }

        @Override
        public Result get(@PathVariable long id){
            log.error("OrgRoleService get服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrgRoleService的get方法不可用");
        }

        @Override
        public Result list(@Valid @RequestBody  OrgRoleQueryForm orgRoleQueryForm){
            log.error("OrgRoleService list服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrgRoleService的list方法不可用");
        }

        @Override
        public Result search(@Valid @RequestBody OrgRoleQueryForm orgRoleQueryForm){
            log.error("OrgRoleService search服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrgRoleService的search方法不可用");
        }
    }
}