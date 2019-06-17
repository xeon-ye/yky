package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.ZpcpTechRewardClient;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpTechRewardQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpTechRewardForm;
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
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :   ZpcpTechReward feign客户端
 * @Modified :
 */
@FeignClient(name = "zpcpTechReward-service", fallbackFactory = ZpcpTechRewardFeignService.HystrixZpcpTechRewardService.class,primary = false)
public interface ZpcpTechRewardFeignService extends ZpcpTechRewardClient {

    @Component
    @Slf4j
    class HystrixZpcpTechRewardService implements FallbackFactory<ZpcpTechRewardFeignService> {

        @Override
        public ZpcpTechRewardFeignService create(Throwable throwable) {
            return new ZpcpTechRewardFeignService() {
                @Override
                public Result add(@Valid @RequestBody ZpcpTechRewardForm zpcpTechRewardForm) {
                    log.error("ZpcpTechRewardService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ZpcpTechRewardService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ZpcpTechRewardForm zpcpTechRewardForm) {
                    log.error("ZpcpTechRewardService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ZpcpTechRewardService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ZpcpTechRewardQueryForm zpcpTechRewardQueryForm) {
                    log.error("ZpcpTechRewardService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ZpcpTechRewardQueryForm zpcpTechRewardQueryForm) {
                    log.error("ZpcpTechRewardService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addOrUpdateList(List<ZpcpTechRewardForm> techRewardForms) {
                    log.error("ZpcpTechRewardService addOrUpdateList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(DeleteForm deleteForm) {
                    log.error("ZpcpDrugDeviceService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}