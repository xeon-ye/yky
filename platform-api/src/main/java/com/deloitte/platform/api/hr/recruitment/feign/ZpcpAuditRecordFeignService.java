package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.ZpcpAuditRecordClient;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpAuditRecordQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpAuditRecordForm;
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
 * @Date : Create in 2019-04-26
 * @Description :   ZpcpAuditRecord feign客户端
 * @Modified :
 */
@FeignClient(name = "zpcpAuditRecord-service", fallbackFactory = ZpcpAuditRecordFeignService.HystrixZpcpAuditRecordService.class,primary = false)
public interface ZpcpAuditRecordFeignService extends ZpcpAuditRecordClient {

    @Component
    @Slf4j
    class HystrixZpcpAuditRecordService implements FallbackFactory<ZpcpAuditRecordFeignService> {

        @Override
        public ZpcpAuditRecordFeignService create(Throwable throwable) {
            return new ZpcpAuditRecordFeignService() {
                @Override
                public Result add(@Valid @RequestBody ZpcpAuditRecordForm zpcpAuditRecordForm) {
                    log.error("ZpcpAuditRecordService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ZpcpAuditRecordService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ZpcpAuditRecordForm zpcpAuditRecordForm) {
                    log.error("ZpcpAuditRecordService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ZpcpAuditRecordService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ZpcpAuditRecordQueryForm zpcpAuditRecordQueryForm) {
                    log.error("ZpcpAuditRecordService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ZpcpAuditRecordQueryForm zpcpAuditRecordQueryForm) {
                    log.error("ZpcpAuditRecordService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkDeclare(ZpcpAuditRecordForm zpcpAuditRecordForm) {
                    log.error("ZpcpAuditRecordService checkDeclare服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}