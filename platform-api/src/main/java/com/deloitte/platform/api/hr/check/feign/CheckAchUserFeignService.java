package com.deloitte.platform.api.hr.check.feign;


import com.deloitte.platform.api.hr.check.client.CheckAchUserClient;
import com.deloitte.platform.api.hr.check.param.CheckAchUserListVoQueryForm;
import com.deloitte.platform.api.hr.check.param.CheckAchUserQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchUserForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchUserListVo;
import com.deloitte.platform.api.hr.check.vo.CheckAchUserModifyForm;
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

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :   CheckAchUser feign客户端
 * @Modified :
 */
@FeignClient(name = "checkAchUser-service", fallbackFactory = CheckAchUserFeignService.HystrixCheckAchUserService.class,primary = false)
public interface CheckAchUserFeignService extends CheckAchUserClient {

    @Component
    @Slf4j
    class HystrixCheckAchUserService implements FallbackFactory<CheckAchUserFeignService> {

        @Override
        public CheckAchUserFeignService create(Throwable throwable) {
            return new CheckAchUserFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckAchUserForm checkAchUserForm) {
                    log.error("CheckAchUserService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckAchUserService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckAchUserForm checkAchUserForm) {
                    log.error("CheckAchUserService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckAchUserService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckAchUserQueryForm checkAchUserQueryForm) {
                    log.error("CheckAchUserService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckAchUserQueryForm checkAchUserQueryForm) {
                    log.error("CheckAchUserService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchModifyStatus(@Valid @RequestBody CheckAchUserModifyForm checkAchUserModifyForm) {
                    log.error("CheckAchUserService batchModifyStatus服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchDelete( @Valid @RequestBody List<String> ids) {
                    log.error("CheckTimeService batchDelete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<CheckAchUserListVo>>  getEvaluateUserList(@Valid @RequestBody  CheckAchUserListVoQueryForm checkAchUserListVoQueryForm) {
                    log.error("CheckTimeService getEvaluateUserList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}