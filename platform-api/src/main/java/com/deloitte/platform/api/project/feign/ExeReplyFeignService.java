package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ExeReplyClient;
import com.deloitte.platform.api.project.param.ExeReplyQueryForm;
import com.deloitte.platform.api.project.vo.ExeReplyForm;
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
 * @Author : zhengchun
 * @Date : Create in 2019-06-05
 * @Description :   ExeReply feign客户端
 * @Modified :
 */
@FeignClient(name = "exeReply-service", fallbackFactory = ExeReplyFeignService.HystrixExeReplyService.class,primary = false)
public interface ExeReplyFeignService extends ExeReplyClient {

    @Component
    @Slf4j
    class HystrixExeReplyService implements FallbackFactory<ExeReplyFeignService> {

        @Override
        public ExeReplyFeignService create(Throwable throwable) {
            return new ExeReplyFeignService() {
                @Override
                public Result add(@Valid @RequestBody ExeReplyForm exeReplyForm) {
                    log.error("ExeReplyService add服务不可用......");

                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ExeReplyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ExeReplyForm exeReplyForm) {
                    log.error("ExeReplyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ExeReplyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ExeReplyQueryForm exeReplyQueryForm) {
                    log.error("ExeReplyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ExeReplyQueryForm exeReplyQueryForm) {
                    log.error("ExeReplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}