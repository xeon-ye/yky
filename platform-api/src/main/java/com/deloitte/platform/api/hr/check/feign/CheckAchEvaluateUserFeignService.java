package com.deloitte.platform.api.hr.check.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.client.CheckAchEvaluateUserClient;
import com.deloitte.platform.api.hr.check.param.CheckAchEvaluateUserQueryForm;
import com.deloitte.platform.api.hr.check.param.CheckResultRelationQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchEvaluateUserForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchEvaluateUserModifyForm;
import com.deloitte.platform.api.hr.check.vo.CheckRelationResultVo;
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
 * @Description :   CheckAchEvaluateUser feign客户端
 * @Modified :
 */
@FeignClient(name = "checkAchEvaluateUser-service", fallbackFactory = CheckAchEvaluateUserFeignService.HystrixCheckAchEvaluateUserService.class,primary = false)
public interface CheckAchEvaluateUserFeignService extends CheckAchEvaluateUserClient {

    @Component
    @Slf4j
    class HystrixCheckAchEvaluateUserService implements FallbackFactory<CheckAchEvaluateUserFeignService> {

        @Override
        public CheckAchEvaluateUserFeignService create(Throwable throwable) {
            return new CheckAchEvaluateUserFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckAchEvaluateUserForm checkAchEvaluateUserForm) {
                    log.error("CheckAchEvaluateUserService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckAchEvaluateUserService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckAchEvaluateUserForm checkAchEvaluateUserForm) {
                    log.error("CheckAchEvaluateUserService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckAchEvaluateUserService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckAchEvaluateUserQueryForm checkAchEvaluateUserQueryForm) {
                    log.error("CheckAchEvaluateUserService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckAchEvaluateUserQueryForm checkAchEvaluateUserQueryForm) {
                    log.error("CheckAchEvaluateUserService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchModifyStatus(@Valid @RequestBody CheckAchEvaluateUserModifyForm checkAchEvaluateUserModifyForm) {
                    log.error("CheckAchEvaluateUserService batchModifyStatus服务不可用......");
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
                public Result<IPage<CheckRelationResultVo>> getResultRelation(@Valid @RequestBody  CheckResultRelationQueryForm queryForm) {
                    log.error("CheckTimeService getResultRelation服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}