package com.deloitte.platform.api.hr.ebs.feign;

import com.deloitte.platform.api.hr.ebs.client.EbsEmployeeMesCheckClient;
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
 * @Title: EbsEmployeeMesCheckClient
 * @ProjectName platform
 * @Description: TODO
 * @date 14:27  2019/6/4
 */
@FeignClient(name = "hr-service", fallbackFactory = EbsEmployeeMesCheckFeignService.HystrixEbsEmployeeMesCheckFeignService.class,primary = false)
public interface EbsEmployeeMesCheckFeignService extends EbsEmployeeMesCheckClient {

    @Component
    @Slf4j
    class HystrixEbsEmployeeMesCheckFeignService implements FallbackFactory<EbsEmployeeMesCheckFeignService> {

        @Override
        public EbsEmployeeMesCheckFeignService create(Throwable throwable) {
            return new EbsEmployeeMesCheckFeignService() {

            };
        }
    }
}
