package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccCommitteeRecommendClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccCommitteeRecommendQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccCommitteeRecommendForm;
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
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccCommitteeRecommend feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccCommitteeRecommendFeignService.HystrixGccCommitteeRecommendService.class,primary = false)
public interface GccCommitteeRecommendFeignService extends GccCommitteeRecommendClient {

    @Component
    @Slf4j
    class HystrixGccCommitteeRecommendService implements FallbackFactory<GccCommitteeRecommendFeignService> {

        @Override
        public GccCommitteeRecommendFeignService create(Throwable throwable) {
            return new GccCommitteeRecommendFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccCommitteeRecommendForm gccCommitteeRecommendForm) {
                    log.error("GccCommitteeRecommendService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccCommitteeRecommendService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccCommitteeRecommendForm gccCommitteeRecommendForm) {
                    log.error("GccCommitteeRecommendService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccCommitteeRecommendService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccCommitteeRecommendQueryForm gccCommitteeRecommendQueryForm) {
                    log.error("GccCommitteeRecommendService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccCommitteeRecommendQueryForm gccCommitteeRecommendQueryForm) {
                    log.error("GccCommitteeRecommendService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addOrUpdateList(List<GccCommitteeRecommendForm> gccCommitteeRecommendForms) {
                    log.error("GccCommitteeRecommendService addOrUpdateList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(GccBaseBatchForm form) {
                    log.error("GccCommitteeRecommendService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}