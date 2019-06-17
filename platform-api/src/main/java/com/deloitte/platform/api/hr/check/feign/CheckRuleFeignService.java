package com.deloitte.platform.api.hr.check.feign;

import com.deloitte.platform.api.hr.check.client.CheckRuleClient;
import com.deloitte.platform.api.hr.check.param.CheckRuleQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckPracticalRuleVo;
import com.deloitte.platform.api.hr.check.vo.CheckRuleForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :   CheckRule feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckRuleFeignService.HystrixCheckRuleService.class,primary = false)
public interface CheckRuleFeignService extends CheckRuleClient {

    @Component
    @Slf4j
    class HystrixCheckRuleService implements FallbackFactory<CheckRuleFeignService> {

        @Override
        public CheckRuleFeignService create(Throwable throwable) {
            return new CheckRuleFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckRuleForm checkRuleForm) {
                    log.error("CheckRuleService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckRuleService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckRuleForm checkRuleForm) {
                    log.error("CheckRuleService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckRuleService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<CheckPracticalRuleVo> getRuleAndRuleContent(long id) {
                    log.error("CheckRuleService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckRuleQueryForm checkRuleQueryForm) {
                    log.error("CheckRuleService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckRuleQueryForm checkRuleQueryForm) {
                    log.error("CheckRuleService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchDelete( @Valid @RequestBody List<String> ids) {
                    log.error("CheckTimeService batchDelete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}