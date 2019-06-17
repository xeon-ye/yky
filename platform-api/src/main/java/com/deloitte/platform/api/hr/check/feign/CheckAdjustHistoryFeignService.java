package com.deloitte.platform.api.hr.check.feign;


import com.deloitte.platform.api.hr.check.client.CheckAdjustHistoryClient;
import com.deloitte.platform.api.hr.check.param.CheckAdjustHistoryQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAdjustHistoryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAdjustHistoryInfoVo;
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
 * @Description :   CheckAdjustHistory feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckAdjustHistoryFeignService.HystrixCheckAdjustHistoryService.class,primary = false)
public interface CheckAdjustHistoryFeignService extends CheckAdjustHistoryClient {

    @Component
    @Slf4j
    class HystrixCheckAdjustHistoryService implements FallbackFactory<CheckAdjustHistoryFeignService> {

        @Override
        public CheckAdjustHistoryFeignService create(Throwable throwable) {
            return new CheckAdjustHistoryFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckAdjustHistoryForm checkAdjustHistoryForm) {
                    log.error("CheckAdjustHistoryService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckAdjustHistoryService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckAdjustHistoryForm checkAdjustHistoryForm) {
                    log.error("CheckAdjustHistoryService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckAdjustHistoryService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckAdjustHistoryQueryForm checkAdjustHistoryQueryForm) {
                    log.error("CheckAdjustHistoryService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckAdjustHistoryQueryForm checkAdjustHistoryQueryForm) {
                    log.error("CheckAdjustHistoryService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<CheckAdjustHistoryInfoVo>> getCheckAdjustHistoryInfo(@Valid @RequestBody CheckAdjustHistoryQueryForm queryForm) {
                    log.error("CheckAdjustHistoryService getCheckAdjustHistoryInfo服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchSave(@Valid @RequestBody List<CheckAdjustHistoryQueryForm> checkAdjustHistoryQueryFormList) {
                    log.error("CheckAdjustHistoryService batchSave服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}