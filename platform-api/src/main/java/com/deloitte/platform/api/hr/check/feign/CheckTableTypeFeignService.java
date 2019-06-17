package com.deloitte.platform.api.hr.check.feign;

import com.deloitte.platform.api.hr.check.client.CheckTableTypeClient;
import com.deloitte.platform.api.hr.check.param.CheckTableTypeQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckTableTypeForm;
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
 * @Description :   CheckTableType feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckTableTypeFeignService.HystrixCheckTableTypeService.class,primary = false)
public interface CheckTableTypeFeignService extends CheckTableTypeClient {

    @Component
    @Slf4j
    class HystrixCheckTableTypeService implements FallbackFactory<CheckTableTypeFeignService> {

        @Override
        public CheckTableTypeFeignService create(Throwable throwable) {
            return new CheckTableTypeFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckTableTypeForm checkTableTypeForm) {
                    log.error("CheckTableTypeService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckTableTypeService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckTableTypeForm checkTableTypeForm) {
                    log.error("CheckTableTypeService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckTableTypeService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckTableTypeQueryForm checkTableTypeQueryForm) {
                    log.error("CheckTableTypeService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckTableTypeQueryForm checkTableTypeQueryForm) {
                    log.error("CheckTableTypeService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchDelete(@Valid @RequestBody List<String> ids) {
                    log.error("CheckTableTypeService batchDelete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}