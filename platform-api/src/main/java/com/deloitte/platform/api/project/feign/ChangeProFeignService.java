package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ChangeProClient;
import com.deloitte.platform.api.project.param.ChangeProQueryForm;
import com.deloitte.platform.api.project.vo.ChangeProForm;
import com.deloitte.platform.api.project.vo.ProjectChangeVo;
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
 * @Date : Create in 2019-05-24
 * @Description :   ChangePro feign客户端
 * @Modified :
 */
@FeignClient(name = "changePro-service", fallbackFactory = ChangeProFeignService.HystrixChangeProService.class,primary = false)
public interface ChangeProFeignService extends ChangeProClient {

    @Component
    @Slf4j
    class HystrixChangeProService implements FallbackFactory<ChangeProFeignService> {

        @Override
        public ChangeProFeignService create(Throwable throwable) {
            return new ChangeProFeignService() {
                @Override
                public Result add(@Valid @RequestBody ChangeProForm changeProForm) {
                    log.error("ChangeProService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ChangeProService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ChangeProForm changeProForm) {
                    log.error("ChangeProService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ChangeProService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ChangeProQueryForm changeProQueryForm) {
                    log.error("ChangeProService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ChangeProQueryForm changeProQueryForm) {
                    log.error("ChangeProService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<ProjectChangeVo> getOneChange(String projectId) {
                    log.error("ChangeProService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}