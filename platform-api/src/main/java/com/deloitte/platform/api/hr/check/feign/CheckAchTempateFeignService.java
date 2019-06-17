package com.deloitte.platform.api.hr.check.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.client.CheckAchTempateClient;
import com.deloitte.platform.api.hr.check.param.CheckAchTempateQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchTempateForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchTempateInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :   CheckAchTempate feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckAchTempateFeignService.HystrixCheckAchTempateService.class,primary = false)
public interface CheckAchTempateFeignService extends CheckAchTempateClient {

    @Component
    @Slf4j
    class HystrixCheckAchTempateService implements FallbackFactory<CheckAchTempateFeignService> {

        @Override
        public CheckAchTempateFeignService create(Throwable throwable) {
            return new CheckAchTempateFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckAchTempateForm checkAchTempateForm) {
                    log.error("CheckAchTempateService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckAchTempateService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckAchTempateForm checkAchTempateForm) {
                    log.error("CheckAchTempateService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckAchTempateService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckAchTempateQueryForm checkAchTempateQueryForm) {
                    log.error("CheckAchTempateService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckAchTempateQueryForm checkAchTempateQueryForm) {
                    log.error("CheckAchTempateService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<CheckAchTempateInfoVo>> getCheckAchTempate(@Valid @RequestBody CheckAchTempateQueryForm checkAchTempateQueryForm) {
                    log.error("CheckAchTempateService getCheckAchTempate服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchDelete(@Valid @RequestBody List<String> ids) {
                    log.error("CheckAchTempateService batchDelete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}