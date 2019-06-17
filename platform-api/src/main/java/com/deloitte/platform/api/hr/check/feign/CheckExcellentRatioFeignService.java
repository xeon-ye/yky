package com.deloitte.platform.api.hr.check.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.client.CheckExcellentRatioClient;
import com.deloitte.platform.api.hr.check.param.CheckExcellentRatioQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckExcellentRatioForm;
import com.deloitte.platform.api.hr.check.vo.CheckExcellentRatioInfoVo;
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

;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :   CheckExcellentRatio feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckExcellentRatioFeignService.HystrixCheckExcellentRatioService.class,primary = false)
public interface CheckExcellentRatioFeignService extends CheckExcellentRatioClient {

    @Component
    @Slf4j
    class HystrixCheckExcellentRatioService implements FallbackFactory<CheckExcellentRatioFeignService> {

        @Override
        public CheckExcellentRatioFeignService create(Throwable throwable) {
            return new CheckExcellentRatioFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckExcellentRatioForm checkExcellentRatioForm) {
                    log.error("CheckExcellentRatioService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckExcellentRatioService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckExcellentRatioForm checkExcellentRatioForm) {
                    log.error("CheckExcellentRatioService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckExcellentRatioService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckExcellentRatioQueryForm checkExcellentRatioQueryForm) {
                    log.error("CheckExcellentRatioService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckExcellentRatioQueryForm checkExcellentRatioQueryForm) {
                    log.error("CheckExcellentRatioService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<CheckExcellentRatioInfoVo>> getCheckExellentRatioInfo(@Valid @RequestBody CheckExcellentRatioQueryForm checkExcellentRatioQueryForm) {
                    log.error("CheckExcellentRatioService getCheckExellentRatioInfo服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchDelete( @Valid @RequestBody List<String> ids) {
                    log.error("CheckTimeService batchDelete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}