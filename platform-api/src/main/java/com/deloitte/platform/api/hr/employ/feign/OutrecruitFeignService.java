package com.deloitte.platform.api.hr.employ.feign;


import com.deloitte.platform.api.hr.employ.client.OutrecruitClient;
import com.deloitte.platform.api.hr.employ.param.OutrecruitQueryForm;
import com.deloitte.platform.api.hr.employ.vo.OutrecruitForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-10
 * @Description :   Outrecruit feign客户端
 * @Modified :
 */
@FeignClient(name = "outrecruit-service", fallbackFactory = OutrecruitFeignService.HystrixOutrecruitService.class,primary = false)
public interface OutrecruitFeignService extends OutrecruitClient {

    @Component
    @Slf4j
    class HystrixOutrecruitService implements FallbackFactory<OutrecruitFeignService> {

        @Override
        public OutrecruitFeignService create(Throwable throwable) {
            return new OutrecruitFeignService() {
                @Override
                public Result add(@Valid @RequestBody OutrecruitForm outrecruitForm) {
                    log.error("OutrecruitService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OutrecruitService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OutrecruitForm outrecruitForm) {
                    log.error("OutrecruitService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OutrecruitService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OutrecruitQueryForm outrecruitQueryForm) {
                    log.error("OutrecruitService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OutrecruitQueryForm outrecruitQueryForm) {
                    log.error("OutrecruitService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}