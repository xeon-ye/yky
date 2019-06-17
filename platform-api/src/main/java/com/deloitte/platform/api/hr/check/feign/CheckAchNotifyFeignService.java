package com.deloitte.platform.api.hr.check.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.client.CheckAchNotifyClient;
import com.deloitte.platform.api.hr.check.param.CheckAchNotifyQueryForm;
import com.deloitte.platform.api.hr.check.param.CheckAchUserListVoQueryForm;
import com.deloitte.platform.api.hr.check.param.CheckAchUserQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchNeedDealtNotifyVo;
import com.deloitte.platform.api.hr.check.vo.CheckAchNotifyForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchUserListVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :   CheckAchNotify feign客户端
 * @Modified :
 */
@FeignClient(name = "checkAchNotify-service", fallbackFactory = CheckAchNotifyFeignService.HystrixCheckAchNotifyService.class,primary = false)
public interface CheckAchNotifyFeignService extends CheckAchNotifyClient {

    @Component
    @Slf4j
    class HystrixCheckAchNotifyService implements FallbackFactory<CheckAchNotifyFeignService> {

        @Override
        public CheckAchNotifyFeignService create(Throwable throwable) {
            return new CheckAchNotifyFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckAchNotifyForm checkAchNotifyForm) {
                    log.error("CheckAchNotifyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckAchNotifyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckAchNotifyForm checkAchNotifyForm) {
                    log.error("CheckAchNotifyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckAchNotifyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckAchNotifyQueryForm checkAchNotifyQueryForm) {
                    log.error("CheckAchNotifyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckAchNotifyQueryForm checkAchNotifyQueryForm) {
                    log.error("CheckAchNotifyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result publishNotify(@Valid @RequestBody CheckAchNotifyForm checkAchNotifyForm) {
                    log.error("CheckAchNotifyService publishNotify服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<CheckAchNeedDealtNotifyVo>> getNeedDealtNotify(@Valid @RequestBody CheckAchUserQueryForm checkAchUserQueryForm) {
                    log.error("CheckAchNotifyService getNeedDealtNotify服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<CheckAchUserListVo>> getAchUserList(@Valid @RequestBody CheckAchUserListVoQueryForm checkAchUserListVoQueryForm) {
                    log.error("CheckAchNotifyService getAchUserList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportAchUserList(@Valid @RequestBody  CheckAchUserListVoQueryForm checkAchUserListVoQueryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("CheckAchNotifyService exportAchUserList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public void exportAchImportTempate(@Valid @RequestBody CheckAchUserListVoQueryForm checkAchUserListVoQueryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("CheckAchNotifyService exportAchImportTempate服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result importCheckAch(@RequestPart(value = "file") MultipartFile file, @RequestBody  CheckAchUserListVoQueryForm checkAchUserListVoQueryForm) {
                    log.error("CheckAchNotifyService importCheckAch服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}