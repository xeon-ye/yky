package com.deloitte.platform.api.hr.retireRehiring.feign;


import com.deloitte.platform.api.hr.retireRehiring.client.RemindClient;
import com.deloitte.platform.api.hr.retireRehiring.param.RemindQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RemindForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RemindProcessForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RemindRecordForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-15
 * @Description :   RetireRemind feign客户端
 * @Modified :
 */
@FeignClient(name = "retireRemind-service", fallbackFactory = RemindFeignService.HystrixRetireRemindService.class, primary = false)
public interface RemindFeignService extends RemindClient {

    @Component
    @Slf4j
    class HystrixRetireRemindService implements FallbackFactory<RemindFeignService> {

        @Override
        public RemindFeignService create(Throwable throwable) {
            return new RemindFeignService() {
                @Override
                public Result add(@Valid @RequestBody RemindForm retireRemindForm) {
                    log.error("RetireRemindService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("RetireRemindService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody RemindForm retireRemindForm) {
                    log.error("RetireRemindService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("RetireRemindService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody RemindQueryForm retireRemindQueryForm) {
                    log.error("RetireRemindService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody RemindQueryForm retireRemindQueryForm) {
                    log.error("RetireRemindService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result remindDeptMouth(List<RemindRecordForm> forms) {
                    log.error("RetireRemindService remindDeptMouth服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result remindApproveProcess(RemindProcessForm remindProcessForm) {
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public void exportExcel(String id, HttpServletRequest request, HttpServletResponse response) {
                    log.error("RetireRemindService exportExcel服务不可用......");
                    throwable.printStackTrace();
                }
            };
        }
    }
}