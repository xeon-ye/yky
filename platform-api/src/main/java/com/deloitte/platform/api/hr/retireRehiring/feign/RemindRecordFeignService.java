package com.deloitte.platform.api.hr.retireRehiring.feign;


import com.deloitte.platform.api.hr.retireRehiring.client.RemindRecordClient;
import com.deloitte.platform.api.hr.retireRehiring.param.RemindRecordQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RemindRecordForm;
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
 * @Date : Create in 2019-05-15
 * @Description :   RetireRemindRecord feign客户端
 * @Modified :
 */
@FeignClient(name = "retireRemindRecord-service", fallbackFactory = RemindRecordFeignService.HystrixRetireRemindRecordService.class,primary = false)
public interface RemindRecordFeignService extends RemindRecordClient {

    @Component
    @Slf4j
    class HystrixRetireRemindRecordService implements FallbackFactory<RemindRecordFeignService> {

        @Override
        public RemindRecordFeignService create(Throwable throwable) {
            return new RemindRecordFeignService() {
                @Override
                public Result add(@Valid @RequestBody RemindRecordForm retireRemindRecordForm) {
                    log.error("RetireRemindRecordService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("RetireRemindRecordService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody RemindRecordForm retireRemindRecordForm) {
                    log.error("RetireRemindRecordService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("RetireRemindRecordService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody RemindRecordQueryForm retireRemindRecordQueryForm) {
                    log.error("RetireRemindRecordService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody RemindRecordQueryForm retireRemindRecordQueryForm) {
                    log.error("RetireRemindRecordService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}