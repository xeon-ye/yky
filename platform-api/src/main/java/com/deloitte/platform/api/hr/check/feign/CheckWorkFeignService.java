package com.deloitte.platform.api.hr.check.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.client.CheckWorkClient;
import com.deloitte.platform.api.hr.check.param.CheckWorkQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckWorkForm;
import com.deloitte.platform.api.hr.check.vo.CheckWorkInfoVo;
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
 * @Description :   CheckWork feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckWorkFeignService.HystrixCheckWorkService.class,primary = false)
public interface CheckWorkFeignService extends CheckWorkClient {

    @Component
    @Slf4j
    class HystrixCheckWorkService implements FallbackFactory<CheckWorkFeignService> {

        @Override
        public CheckWorkFeignService create(Throwable throwable) {
            return new CheckWorkFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckWorkForm checkWorkForm) {
                    log.error("CheckWorkService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckWorkService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckWorkForm checkWorkForm) {
                    log.error("CheckWorkService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckWorkService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckWorkQueryForm checkWorkQueryForm) {
                    log.error("CheckWorkService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckWorkQueryForm checkWorkQueryForm) {
                    log.error("CheckWorkService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<CheckWorkInfoVo>> getCheckWorkInfo(@Valid @RequestBody CheckWorkQueryForm checkWorkQueryForm) {
                    log.error("CheckWorkService getCheckWorkInfo服务不可用......");
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