package com.deloitte.platform.api.hr.ebs.feign;

import com.deloitte.platform.api.hr.ebs.client.EbsOrganizationClient;
import com.deloitte.platform.api.isump.vo.OrganizationForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author woo
 * @Title: EbsOrganizationClient
 * @ProjectName platform
 * @Description: TODO
 * @date 14:27  2019/6/4
 */
@FeignClient(name = "hr-service", fallbackFactory = EbsOrganizationFeignService.HystrixEbsOrganizationFeignService.class,primary = false)
public interface EbsOrganizationFeignService extends EbsOrganizationClient {

    @Component
    @Slf4j
    class HystrixEbsOrganizationFeignService implements FallbackFactory<EbsOrganizationFeignService> {

        @Override
        public EbsOrganizationFeignService create(Throwable throwable) {
            return new EbsOrganizationFeignService() {
                @Override
                public Result saveOrUpdate(@Valid @RequestBody OrganizationForm organizationForm) {
                    log.error("EbsOrganizationFeignService saveOrUpdate服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}
