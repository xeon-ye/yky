package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.MaintReplyClient;
import com.deloitte.platform.api.project.param.MaintReplyQueryForm;
import com.deloitte.platform.api.project.vo.MaintReplyForm;
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
 * @Date : Create in 2019-05-20
 * @Description :   MaintReply feign客户端
 * @Modified :
 */
@FeignClient(name = "maintReply-service", fallbackFactory = MaintReplyFeignService.HystrixMaintReplyService.class,primary = false)
public interface MaintReplyFeignService extends MaintReplyClient {

    @Component
    @Slf4j
    class HystrixMaintReplyService implements FallbackFactory<MaintReplyFeignService> {

        @Override
        public MaintReplyFeignService create(Throwable throwable) {
            return new MaintReplyFeignService() {
                @Override
                public Result add(@Valid @RequestBody MaintReplyForm maintReplyForm) {
                    log.error("MaintReplyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("MaintReplyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody MaintReplyForm maintReplyForm) {
                    log.error("MaintReplyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("MaintReplyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody MaintReplyQueryForm maintReplyQueryForm) {
                    log.error("MaintReplyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody MaintReplyQueryForm maintReplyQueryForm) {
                    log.error("MaintReplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}