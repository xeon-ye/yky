package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.DeclareBaseClient;
import com.deloitte.platform.api.hr.recruitment.param.DeclareBaseQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeclareBaseForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-04
 * @Description :   DeclareBase feign客户端
 * @Modified :
 */
@FeignClient(name = "declareBase-service", fallbackFactory = DeclareBaseFeignService.HystrixDeclareBaseService.class,primary = false)
public interface DeclareBaseFeignService extends DeclareBaseClient {

    @Component
    @Slf4j
    class HystrixDeclareBaseService implements FallbackFactory<DeclareBaseFeignService> {

        @Override
        public DeclareBaseFeignService create(Throwable throwable) {
            return new DeclareBaseFeignService() {
                @Override
                public Result add(@Valid @RequestBody DeclareBaseForm declareBaseForm) {
                    log.error("DeclareBaseService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("DeclareBaseService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody DeclareBaseForm declareBaseForm) {
                    log.error("DeclareBaseService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("DeclareBaseService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody DeclareBaseQueryForm declareBaseQueryForm) {
                    log.error("DeclareBaseService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody DeclareBaseQueryForm declareBaseQueryForm) {
                    log.error("DeclareBaseService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result submit(DeclareBaseForm declareBaseForm) {
                    log.error("DeclareBaseService submit服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addByUserId(DeclareBaseForm declareBaseForm) {
                    log.error("DeclareBaseService addByUserId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}