package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.ZpcpExperienceClient;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpExperienceQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpExperienceForm;
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
 * @Description :   ZpcpExperience feign客户端
 * @Modified :
 */
@FeignClient(name = "zpcpExperience-service", fallbackFactory = ZpcpExperienceFeignService.HystrixZpcpExperienceService.class,primary = false)
public interface ZpcpExperienceFeignService extends ZpcpExperienceClient {

    @Component
    @Slf4j
    class HystrixZpcpExperienceService implements FallbackFactory<ZpcpExperienceFeignService> {

        @Override
        public ZpcpExperienceFeignService create(Throwable throwable) {
            return new ZpcpExperienceFeignService() {
                @Override
                public Result add(@Valid @RequestBody ZpcpExperienceForm zpcpExperienceForm) {
                    log.error("ZpcpExperienceService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ZpcpExperienceService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ZpcpExperienceForm zpcpExperienceForm) {
                    log.error("ZpcpExperienceService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ZpcpExperienceService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ZpcpExperienceQueryForm zpcpExperienceQueryForm) {
                    log.error("ZpcpExperienceService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ZpcpExperienceQueryForm zpcpExperienceQueryForm) {
                    log.error("ZpcpExperienceService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addOrUpdateList(List<ZpcpExperienceForm> experienceForms) {
                    log.error("ZpcpExperienceService search服务不可用......");
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