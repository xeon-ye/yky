package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ChangeNoteClient;
import com.deloitte.platform.api.project.param.ChangeNoteQueryForm;
import com.deloitte.platform.api.project.vo.ChangeNoteForm;
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
 * @Description :   ChangeNote feign客户端
 * @Modified :
 */
@FeignClient(name = "changeNote-service", fallbackFactory = ChangeNoteFeignService.HystrixChangeNoteService.class,primary = false)
public interface ChangeNoteFeignService extends ChangeNoteClient {

    @Component
    @Slf4j
    class HystrixChangeNoteService implements FallbackFactory<ChangeNoteFeignService> {

        @Override
        public ChangeNoteFeignService create(Throwable throwable) {
            return new ChangeNoteFeignService() {
                @Override
                public Result add(@Valid @RequestBody ChangeNoteForm changeNoteForm) {
                    log.error("ChangeNoteService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ChangeNoteService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ChangeNoteForm changeNoteForm) {
                    log.error("ChangeNoteService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ChangeNoteService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ChangeNoteQueryForm changeNoteQueryForm) {
                    log.error("ChangeNoteService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ChangeNoteQueryForm changeNoteQueryForm) {
                    log.error("ChangeNoteService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}