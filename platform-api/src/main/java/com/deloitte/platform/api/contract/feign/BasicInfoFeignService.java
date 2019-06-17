package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.BasicInfoClient;
import com.deloitte.platform.api.contract.param.BasicInfoQueryForm;
import com.deloitte.platform.api.contract.vo.BasicInfoForm;
import com.deloitte.platform.api.contract.vo.BasicInfoVo;
import com.deloitte.platform.api.contract.vo.FinanceInfoVo;
import com.deloitte.platform.api.contract.vo.FinanceInfoVoToFssc;
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
 * @Author : zhengchun
 * @Date : Create in 2019-04-03
 * @Description :   BasicInfo feign客户端
 * @Modified :
 */
@FeignClient(name = "service-contract", fallbackFactory = BasicInfoFeignService.HystrixBasicInfoService.class,primary = false)
public interface BasicInfoFeignService extends BasicInfoClient {

    @Component
    @Slf4j
    class HystrixBasicInfoService implements FallbackFactory<BasicInfoFeignService> {

        @Override
        public BasicInfoFeignService create(Throwable throwable) {
            return new BasicInfoFeignService() {
                @Override
                public Result add(@Valid @RequestBody BasicInfoForm basicInfoForm) {
                    log.error("BasicInfoService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("BasicInfoService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody BasicInfoForm basicInfoForm) {
                    log.error("BasicInfoService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("BasicInfoService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody BasicInfoQueryForm basicInfoQueryForm) {
                    log.error("BasicInfoService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody BasicInfoQueryForm basicInfoQueryForm) {
                    log.error("BasicInfoService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result saveImprint(@Valid @RequestBody BasicInfoForm basicInfoForm) {
                    log.error("BasicInfoService saveImprint服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result saveFinanceInfo(@Valid @RequestBody List<FinanceInfoVoToFssc> listFinanceInfoVo) {
                    log.error("BasicInfoService saveFinanceInfo服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}