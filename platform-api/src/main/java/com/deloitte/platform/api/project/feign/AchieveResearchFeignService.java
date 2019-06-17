package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.AchieveResearchClient;
import com.deloitte.platform.api.project.param.AchieveResearchQueryForm;
import com.deloitte.platform.api.project.vo.AchieveResearchForm;
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
 * @Author : zhengchun
 * @Date : Create in 2019-05-31
 * @Description :   AchieveResearch feign客户端
 * @Modified :
 */
@FeignClient(name = "achieveResearch-service", fallbackFactory = AchieveResearchFeignService.HystrixAchieveResearchService.class,primary = false)
public interface AchieveResearchFeignService extends AchieveResearchClient {

    @Component
    @Slf4j
    class HystrixAchieveResearchService implements FallbackFactory<AchieveResearchFeignService> {

        @Override
        public AchieveResearchFeignService create(Throwable throwable) {
            return new AchieveResearchFeignService() {
                @Override
                public Result add(@Valid @RequestBody AchieveResearchForm achieveResearchForm) {
                    log.error("AchieveResearchService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("AchieveResearchService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody AchieveResearchForm achieveResearchForm) {
                    log.error("AchieveResearchService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("AchieveResearchService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody AchieveResearchQueryForm achieveResearchQueryForm) {
                    log.error("AchieveResearchService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody AchieveResearchQueryForm achieveResearchQueryForm) {
                    log.error("AchieveResearchService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}