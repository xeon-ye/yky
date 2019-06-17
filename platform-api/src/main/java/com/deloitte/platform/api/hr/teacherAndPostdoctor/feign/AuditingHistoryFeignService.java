package com.deloitte.platform.api.hr.teacherAndPostdoctor.feign;


import com.deloitte.platform.api.hr.teacherAndPostdoctor.client.AuditingHistoryClient;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.AuditingHistoryQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.AuditingHistoryForm;
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
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   AuditingHistory feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = AuditingHistoryFeignService.HystrixAuditingHistoryService.class,primary = false)
public interface AuditingHistoryFeignService extends AuditingHistoryClient {

    @Component
    @Slf4j
    class HystrixAuditingHistoryService implements FallbackFactory<AuditingHistoryFeignService> {

        @Override
        public AuditingHistoryFeignService create(Throwable throwable) {
            return new AuditingHistoryFeignService() {
                @Override
                public Result add(@Valid @RequestBody AuditingHistoryForm auditingHistoryForm) {
                    log.error("AuditingHistoryService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("AuditingHistoryService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody AuditingHistoryForm auditingHistoryForm) {
                    log.error("AuditingHistoryService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("AuditingHistoryService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody AuditingHistoryQueryForm auditingHistoryQueryForm) {
                    log.error("AuditingHistoryService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody AuditingHistoryQueryForm auditingHistoryQueryForm) {
                    log.error("AuditingHistoryService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}