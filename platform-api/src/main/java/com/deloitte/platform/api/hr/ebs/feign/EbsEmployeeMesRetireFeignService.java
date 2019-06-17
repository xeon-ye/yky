package com.deloitte.platform.api.hr.ebs.feign;

import com.deloitte.platform.api.hr.ebs.client.EbsEmployeeMesRetireClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author woo
 * @Title: EbsEmployeeMesRetireClient
 * @ProjectName platform
 * @Description: TODO
 * @date 14:27  2019/6/4
 */
@FeignClient(name = "hr-service", fallbackFactory = EbsEmployeeMesRetireFeignService.HystrixEbsEmployeeMesRetireFeignService.class,primary = false)
public interface EbsEmployeeMesRetireFeignService extends EbsEmployeeMesRetireClient {

    @Component
    @Slf4j
    class HystrixEbsEmployeeMesRetireFeignService implements FallbackFactory<EbsEmployeeMesRetireFeignService> {

        @Override
        public EbsEmployeeMesRetireFeignService create(Throwable throwable) {
            return new EbsEmployeeMesRetireFeignService() {

            };
        }
    }
}
