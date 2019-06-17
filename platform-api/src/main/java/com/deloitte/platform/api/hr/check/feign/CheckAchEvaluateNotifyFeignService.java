package com.deloitte.platform.api.hr.check.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.client.CheckAchEvaluateNotifyClient;
import com.deloitte.platform.api.hr.check.param.CheckAchEvaluateNotifyQueryForm;
import com.deloitte.platform.api.hr.check.param.CheckAchEvaluateUserListQueryForm;
import com.deloitte.platform.api.hr.check.param.CheckAchEvaluateUserQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchEvaluateInfoNotifyVo;
import com.deloitte.platform.api.hr.check.vo.CheckAchEvaluateNotifyForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchEvaluateUserListVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :   CheckAchEvaluateNotify feign客户端
 * @Modified :
 */
@FeignClient(name = "checkAchEvaluateNotify-service", fallbackFactory = CheckAchEvaluateNotifyFeignService.HystrixCheckAchEvaluateNotifyService.class,primary = false)
public interface CheckAchEvaluateNotifyFeignService extends CheckAchEvaluateNotifyClient {

    @Component
    @Slf4j
    class HystrixCheckAchEvaluateNotifyService implements FallbackFactory<CheckAchEvaluateNotifyFeignService> {

        @Override
        public CheckAchEvaluateNotifyFeignService create(Throwable throwable) {
            return new CheckAchEvaluateNotifyFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckAchEvaluateNotifyForm checkAchEvaluateNotifyForm) {
                    log.error("CheckAchEvaluateNotifyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckAchEvaluateNotifyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckAchEvaluateNotifyForm checkAchEvaluateNotifyForm) {
                    log.error("CheckAchEvaluateNotifyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckAchEvaluateNotifyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckAchEvaluateNotifyQueryForm checkAchEvaluateNotifyQueryForm) {
                    log.error("CheckAchEvaluateNotifyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckAchEvaluateNotifyQueryForm checkAchEvaluateNotifyQueryForm) {
                    log.error("CheckAchEvaluateNotifyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<CheckAchEvaluateInfoNotifyVo>> getEvaluateNotifyInfo(@Valid @RequestBody CheckAchEvaluateUserQueryForm checkAchEvaluateUserQueryForm) {
                    log.error("CheckAchEvaluateNotifyService getEvaluateNotifyInfo服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result publishEvaluateNotify(@Valid @RequestBody CheckAchEvaluateNotifyForm checkAchEvaluateNotifyForm) {
                    log.error("CheckAchEvaluateNotifyService publishEvaluateNotify服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<CheckAchEvaluateUserListVo>> getEvaluateUserList(@Valid @RequestBody CheckAchEvaluateUserListQueryForm checkAchEvaluateUserListQueryForm) {
                    log.error("CheckAchEvaluateNotifyService getEvaluateUserList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportEvaluateUserList(@Valid @RequestBody  CheckAchEvaluateUserListQueryForm checkAchEvaluateUserListQueryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("checkAchEvaluateUserListQueryForm checkAchEvaluateUserListQueryForm服务不可用......");
                    throwable.printStackTrace();
                }
            };
        }
    }
}