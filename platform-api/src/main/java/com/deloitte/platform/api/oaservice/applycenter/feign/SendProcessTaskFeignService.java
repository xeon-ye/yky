package com.deloitte.platform.api.oaservice.applycenter.feign;


import com.deloitte.platform.api.oaservice.applycenter.client.SendProcessTaskClient;
import com.deloitte.platform.api.oaservice.applycenter.param.SendProcessTaskQueryForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskByBpmForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.GdcPage;
import feign.hystrix.FallbackFactory;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-05-09
 * @Description :   SendProcessTask feign客户端
 * @Modified :
 */
@FeignClient(name = "service-oa", fallbackFactory = SendProcessTaskFeignService.HystrixSendProcessTaskService.class, primary = false)
public interface SendProcessTaskFeignService extends SendProcessTaskClient {

    @Component
    @Slf4j
    class HystrixSendProcessTaskService implements FallbackFactory<SendProcessTaskFeignService> {

        @Override
        public SendProcessTaskFeignService create(Throwable throwable) {
            return new SendProcessTaskFeignService() {
                @Override
                public Result add(@Valid @RequestBody SendProcessTaskForm sendProcessTaskForm) {
                    log.error("SendProcessTaskService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result addByBpm(@Valid @RequestBody @ApiParam(name = "sendProcessTaskByBpmForm", value = "通过BPM生成待阅信息", required = true) SendProcessTaskByBpmForm sendProcessTaskByBpmForm){
                    log.error("SendProcessTaskService addByBpm服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("SendProcessTaskService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody SendProcessTaskForm sendProcessTaskForm) {
                    log.error("SendProcessTaskService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result updateById(@RequestParam(name="id")long id) {
                    log.error("SendProcessTaskService updateById服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("SendProcessTaskService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody SendProcessTaskQueryForm sendProcessTaskQueryForm) {
                    log.error("SendProcessTaskService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result<GdcPage<SendProcessTaskVo>> search(@Valid @RequestBody SendProcessTaskQueryForm sendProcessTaskQueryForm) {
                    log.error("SendProcessTaskService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result syncData(@Valid @RequestBody SendProcessTaskQueryForm sendProcessTaskQueryForm) {
                    log.error("SendProcessTaskService syncData服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result searchByFrom(@Valid @RequestBody SendProcessTaskQueryForm sendProcessTaskQueryForm) {
                    log.error("SendProcessTaskService searchByFrom服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}