package com.deloitte.platform.api.hr.common.feign;


import com.deloitte.platform.api.hr.common.client.OrganizationClient;
import com.deloitte.platform.api.hr.common.param.HrOrganizationQueryForm;
import com.deloitte.platform.api.hr.common.vo.HrOrganizationForm;
import com.deloitte.platform.api.hr.common.vo.HrOrganizationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-18
 * @Description :   Organization feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = HrOrganizationFeignService.HystrixHrOrganizationService.class,primary = false)
public interface HrOrganizationFeignService extends OrganizationClient {

    @Component("hrHystrixOrganizationService")
    @Slf4j
    class HystrixHrOrganizationService implements FallbackFactory<HrOrganizationFeignService> {

        @Override
        public HrOrganizationFeignService create(Throwable throwable) {
            return new HrOrganizationFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrOrganizationForm hrOrganizationForm) {
                    log.error("OrganizationService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OrganizationService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrOrganizationForm hrOrganizationForm) {
                    log.error("OrganizationService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OrganizationService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrOrganizationQueryForm hrOrganizationQueryForm) {
                    log.error("OrganizationService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrOrganizationQueryForm hrOrganizationQueryForm) {
                    log.error("OrganizationService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<HrOrganizationVo> getTree() {
                    log.error("OrganizationService getTree服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}