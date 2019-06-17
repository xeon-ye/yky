package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.MaintReplyNoteClient;
import com.deloitte.platform.api.project.param.MaintReplyNoteQueryForm;
import com.deloitte.platform.api.project.vo.MaintReplyNoteForm;
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
 * @Description :   MaintReplyNote feign客户端
 * @Modified :
 */
@FeignClient(name = "maintReplyNote-service", fallbackFactory = MaintReplyNoteFeignService.HystrixMaintReplyNoteService.class,primary = false)
public interface MaintReplyNoteFeignService extends MaintReplyNoteClient {

    @Component
    @Slf4j
    class HystrixMaintReplyNoteService implements FallbackFactory<MaintReplyNoteFeignService> {

        @Override
        public MaintReplyNoteFeignService create(Throwable throwable) {
            return new MaintReplyNoteFeignService() {
                @Override
                public Result add(@Valid @RequestBody MaintReplyNoteForm maintReplyNoteForm) {
                    log.error("MaintReplyNoteService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("MaintReplyNoteService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody MaintReplyNoteForm maintReplyNoteForm) {
                    log.error("MaintReplyNoteService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("MaintReplyNoteService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody MaintReplyNoteQueryForm maintReplyNoteQueryForm) {
                    log.error("MaintReplyNoteService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody MaintReplyNoteQueryForm maintReplyNoteQueryForm) {
                    log.error("MaintReplyNoteService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}