package com.deloitte.platform.api.oaservice.rulesystem.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.rulesystem.client.OaRuleSystemClient;
import com.deloitte.platform.api.oaservice.rulesystem.param.OaRuleSystemQueryForm;
import com.deloitte.platform.api.oaservice.rulesystem.vo.OaRuleSystemForm;
import com.deloitte.platform.api.oaservice.rulesystem.vo.OaRuleSystemVo;
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
 * @Author : fuqiao
 * @Date : Create in 2019-04-15
 * @Description :   OaRuleSystem feign客户端
 * @Modified :
 */
@FeignClient(name = "oaRuleSystem-service", fallbackFactory = OaRuleSystemFeignService.HystrixOaRuleSystemService.class,primary = false)
public interface OaRuleSystemFeignService extends OaRuleSystemClient {

    @Component
    @Slf4j
    class HystrixOaRuleSystemService implements FallbackFactory<OaRuleSystemFeignService> {

        @Override
        public OaRuleSystemFeignService create(Throwable throwable) {
            return new OaRuleSystemFeignService() {
                @Override
                public Result add(@Valid @RequestBody OaRuleSystemForm oaRuleSystemForm) {
                    log.error("OaRuleSystemService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OaRuleSystemService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OaRuleSystemForm oaRuleSystemForm) {
                    log.error("OaRuleSystemService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OaRuleSystemService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaRuleSystemQueryForm oaRuleSystemQueryForm) {
                    log.error("OaRuleSystemService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaRuleSystemQueryForm oaRuleSystemQueryForm, String token) {
                    log.error("OaRuleSystemService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<OaRuleSystemVo>> searchWithPermission(OaRuleSystemQueryForm oaRuleSystemQueryForm, String token) {
                    log.error("OaRuleSystemService search with permission服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<OaRuleSystemVo>> homeList(Integer num, String token) {
                    log.error("OaRuleSystemService homelist服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}