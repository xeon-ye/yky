package com.deloitte.platform.api.hr.ebs.feign;

import com.deloitte.platform.api.hr.common.vo.HrDeptForm;
import com.deloitte.platform.api.hr.ebs.client.EbsCompanyClient;
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
 * @Title: EbsCompanyFeignService
 * @ProjectName platform
 * @Description: TODO
 * @date 9:25  2019/6/4
 */
@FeignClient(name = "hr-service", fallbackFactory = EbsCompanyFeignService.HystrixEbsCompanyFeignService.class,primary = false)
public interface EbsCompanyFeignService extends EbsCompanyClient {


    @Component
    @Slf4j
    class HystrixEbsCompanyFeignService implements FallbackFactory<EbsCompanyFeignService> {

        @Override
        public EbsCompanyFeignService create(Throwable throwable) {
            return new EbsCompanyFeignService() {
                @Override
                public Result saveOrUpdate(@Valid @RequestBody  HrDeptForm hrDeptForm) {
                    log.error("EbsCompanyFeignService saveOrUpdate服务不可用...");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }

}
