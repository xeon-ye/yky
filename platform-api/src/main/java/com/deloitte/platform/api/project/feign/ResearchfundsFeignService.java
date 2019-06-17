package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ResearchfundsClient;
import com.deloitte.platform.api.project.param.ResearchfundsQueryForm;
import com.deloitte.platform.api.project.vo.ResearchfundsForm;
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
 * @Description :   Researchfunds feign客户端
 * @Modified :
 */
@FeignClient(name = "researchfunds-service", fallbackFactory = ResearchfundsFeignService.HystrixResearchfundsService.class,primary = false)
public interface ResearchfundsFeignService extends ResearchfundsClient {

    @Component
    @Slf4j
    class HystrixResearchfundsService implements FallbackFactory<ResearchfundsFeignService> {

        @Override
        public ResearchfundsFeignService create(Throwable throwable) {
            return new ResearchfundsFeignService() {
                @Override
                public Result add(@Valid @RequestBody ResearchfundsForm researchfundsForm) {
                    log.error("ResearchfundsService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ResearchfundsService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ResearchfundsForm researchfundsForm) {
                    log.error("ResearchfundsService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ResearchfundsService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ResearchfundsQueryForm researchfundsQueryForm) {
                    log.error("ResearchfundsService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ResearchfundsQueryForm researchfundsQueryForm) {
                    log.error("ResearchfundsService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}