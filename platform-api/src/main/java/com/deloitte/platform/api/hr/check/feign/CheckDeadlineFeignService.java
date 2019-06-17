package com.deloitte.platform.api.hr.check.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.client.CheckDeadlineClient;
import com.deloitte.platform.api.hr.check.param.CheckDeadlineQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckDeadlineForm;
import com.deloitte.platform.api.hr.check.vo.CheckDeadlineInfoVo;
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
 * @Description :   CheckDeadline feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckDeadlineFeignService.HystrixCheckDeadlineService.class,primary = false)
public interface CheckDeadlineFeignService extends CheckDeadlineClient {

    @Component
    @Slf4j
    class HystrixCheckDeadlineService implements FallbackFactory<CheckDeadlineFeignService> {

        @Override
        public CheckDeadlineFeignService create(Throwable throwable) {
            return new CheckDeadlineFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckDeadlineForm checkDeadlineForm) {
                    log.error("CheckDeadlineService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckDeadlineService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckDeadlineForm checkDeadlineForm) {
                    log.error("CheckDeadlineService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckDeadlineService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckDeadlineQueryForm checkDeadlineQueryForm) {
                    log.error("CheckDeadlineService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckDeadlineQueryForm checkDeadlineQueryForm) {
                    log.error("CheckDeadlineService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<CheckDeadlineInfoVo>> getCheckDeadlineInfo(@Valid @RequestBody CheckDeadlineQueryForm checkDeadlineQueryForm) {
                    log.error("CheckDeadlineService getCheckDeadlineInfo服务不可用......");
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