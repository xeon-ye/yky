package com.deloitte.platform.api.hr.check.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.client.CheckAchChatClient;
import com.deloitte.platform.api.hr.check.param.CheckAchChatQueryForm;
import com.deloitte.platform.api.hr.check.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :   CheckAchChat feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckAchChatFeignService.HystrixCheckAchChatService.class,primary = false)
public interface CheckAchChatFeignService extends CheckAchChatClient {

    @Component
    @Slf4j
    class HystrixCheckAchChatService implements FallbackFactory<CheckAchChatFeignService> {

        @Override
        public CheckAchChatFeignService create(Throwable throwable) {
            return new CheckAchChatFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckAchChatForm checkAchChatForm) {
                    log.error("CheckAchChatService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckAchChatService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckAchChatForm checkAchChatForm) {
                    log.error("CheckAchChatService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckAchChatService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckAchChatQueryForm checkAchChatQueryForm) {
                    log.error("CheckAchChatService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckAchChatQueryForm checkAchChatQueryForm) {
                    log.error("CheckAchChatService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result publishChatNotify(@Valid @RequestBody CheckAchChatNotifyForm checkAchChatNotifyForm) {
                    log.error("CheckAchChatService publishChatNotify服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<CheckResultChatInfoVo>> getCheckResultChatInfo(@Valid @RequestBody CheckAchChatQueryForm checkAchChatQueryForm) {
                    log.error("CheckAchChatService getCheckResultChatInf服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result saveCheckChatAppeal(@Valid @RequestBody CheckAchChatAppealForm checkAchChatAppealForm) {
                    log.error("CheckAchChatService saveCheckChatAppeal服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportYearCheckTable(@ModelAttribute CheckAchChatQueryForm checkAchChatQueryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("CheckAchChatService exportYearCheckTable服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result updateCheckChat(@Valid @RequestBody CheckAchChatUpadteForm checkAchChatUpadteForm) {
                    log.error("CheckAchChatService updateCheckChat服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}