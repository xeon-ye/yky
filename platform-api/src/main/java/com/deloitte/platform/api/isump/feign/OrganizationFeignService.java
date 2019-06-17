package com.deloitte.platform.api.isump.feign;


import com.deloitte.platform.api.isump.OrganizationClient;
import com.deloitte.platform.api.isump.param.OrganizationQueryForm;
import com.deloitte.platform.api.isump.vo.OrganizationEBSForm;
import com.deloitte.platform.api.isump.vo.OrganizationForm;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.GdcPage;
import java.util.HashMap;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   Organization feign客户端
 * @Modified :
 */
@FeignClient(name = "service-isump", fallback = OrganizationFeignService.HystrixOrganizationService.class)
public interface OrganizationFeignService extends OrganizationClient {

    @Component
    @Slf4j
    class HystrixOrganizationService implements OrganizationFeignService {

        @Override
        public Result add(@Valid @RequestBody OrganizationForm organizationForm){
            log.error("OrganizationService add服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrganizationService的add方法不可用");
        }

        @Override
        public Result delete(@PathVariable long id){
            log.error("OrganizationService delete服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrganizationService的delete方法不可用");
        }

        @Override
        public Result update(@PathVariable long id, @Valid @RequestBody OrganizationForm organizationForm){
            log.error("OrganizationService update服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrganizationService的update方法不可用");
        }

        @Override
        public Result get(@PathVariable long id){
            log.error("OrganizationService get服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrganizationService的get方法不可用");
        }

        @Override
        public Result getByCode(@PathVariable String code){
            log.error("OrganizationService getByCode服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrganizationService的getByCode方法不可用");
        }

        @Override
        public Result list(@Valid @RequestBody  OrganizationQueryForm organizationQueryForm){
            log.error("OrganizationService list服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrganizationService的list方法不可用");
        }

        @Override
        public Result search(@Valid @RequestBody OrganizationQueryForm organizationQueryForm){
            log.error("OrganizationService search服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrganizationService的search方法不可用");
        }

        @Override
        public Result<GdcPage<OrganizationVo>> search2(OrganizationQueryForm organizationQueryForm) {
            log.error("OrganizationService search2服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrganizationService的search2方法不可用");
        }

        @Override
        public Result<HashMap<String, String>> searchCodeAndName(
                OrganizationQueryForm organizationQueryForm) {
            log.error("OrganizationService searchCodeAndName服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrganizationService的searchCodeAndName方法不可用");
        }

        @Override
        public Result<List<OrganizationVo>> getOrgTreeByCode(@PathVariable(value = "orgCode") String orgCode) {
            log.error("OrganizationService getOrgTreeByCode服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrganizationService的getOrgTreeByCode方法不可用");
        }

        @Override
        public Result<List<OrganizationVo>> getOrgFinctionByCode(@PathVariable(value = "orgCode") String orgCode) {
            log.error("OrganizationService getOrgFinctionByCode服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrganizationService的getOrgFinctionByCode方法不可用");
        }

        @Override
        public Result<List<OrganizationVo>> getOrgDeptByCode(@PathVariable(value = "orgCode") String orgCode) {
            log.error("OrganizationService getOrgDeptByCode服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrganizationService的getOrgDeptByCode方法不可用");
        }

        @Override
        public Result addList(@Valid @RequestBody List<OrganizationEBSForm> organizationEBSForm) {
            log.error("OrganizationService addList服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"OrganizationService的addList方法不可用");
        }
    }
}