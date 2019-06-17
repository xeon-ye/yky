package com.deloitte.platform.api.hr.ebs.feign;

import com.deloitte.platform.api.hr.ebs.client.EbsEmployeeMesCadreClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author woo
 * @Title: EbsEmployeeMesCadreClient
 * @ProjectName platform
 * @Description: TODO
 * @date 14:27  2019/6/4
 */
@FeignClient(name = "hr-service", fallbackFactory = EbsEmployeeMesCadreFeignService.HystrixEbsEmployeeMesCadreFeignService.class,primary = false)
public interface EbsEmployeeMesCadreFeignService extends EbsEmployeeMesCadreClient {

    @Component
    @Slf4j
    class HystrixEbsEmployeeMesCadreFeignService implements FallbackFactory<EbsEmployeeMesCadreFeignService> {

        @Override
        public EbsEmployeeMesCadreFeignService create(Throwable throwable) {
            return new EbsEmployeeMesCadreFeignService() {

            };
        }
    }
}
