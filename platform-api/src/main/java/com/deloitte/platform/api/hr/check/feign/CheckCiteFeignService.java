package com.deloitte.platform.api.hr.check.feign;


import com.deloitte.platform.api.hr.check.client.CheckCiteClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :   CheckCite feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckCiteFeignService.HystrixCheckCiteService.class,primary = false)
public interface CheckCiteFeignService extends CheckCiteClient {

    @Component
    @Slf4j
    class HystrixCheckCiteService implements FallbackFactory<CheckCiteFeignService> {

        @Override
        public CheckCiteFeignService create(Throwable throwable) {
            return new CheckCiteFeignService() {
                @Override
                public Result checkTimeCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkTableTypeCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkemplateCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkEvaluateModeCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkAchTempateCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkAchTempateContentCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkRuleCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkRuleContentCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkExcellentRatiCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkDeadlineCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkCheckWorkCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkRightCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkUserCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkGroupCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkRelationCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkRelationContentCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkGroupUserCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkAchNotifyCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkAchUserCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkAchUserContentCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkAchUserApprovalCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkAchEvaluateNotifyCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkAchEvaluateUserCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkAchEvaluateContentCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkResultCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkAdjustHistoryCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkAchChatCite(@Valid @RequestBody Long id) {
                    log.error("checkCiteService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}