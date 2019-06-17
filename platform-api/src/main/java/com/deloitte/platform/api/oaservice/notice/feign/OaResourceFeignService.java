package com.deloitte.platform.api.oaservice.notice.feign;

import com.deloitte.platform.api.oaservice.notice.client.OaResourceClient;
import com.deloitte.platform.api.oaservice.notice.param.OaResourceQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaResourceForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaResourceVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description :   OaResource feign客户端
 * @Modified :
 */
@FeignClient(name = "oaResource-service", fallbackFactory = OaResourceFeignService.HystrixOaResourceService.class,primary = false)
public interface OaResourceFeignService extends OaResourceClient {

    @Component
    @Slf4j
    class HystrixOaResourceService implements FallbackFactory<OaResourceFeignService> {

        @Override
        public OaResourceFeignService create(Throwable throwable) {
            return new OaResourceFeignService() {
                @Override
                public Result add(@Valid @RequestBody OaResourceForm oaResourceForm) {
                    log.error("OaResourceService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OaResourceService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OaResourceForm oaResourceForm) {
                    log.error("OaResourceService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OaResourceService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaResourceQueryForm oaResourceQueryForm) {
                    log.error("OaResourceService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaResourceQueryForm oaResourceQueryForm) {
                    log.error("OaResourceService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<OaResourceVo>> home(Integer num) {
                    log.error("OaResourceService home list 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}