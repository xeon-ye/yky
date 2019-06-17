package com.deloitte.platform.api.hr.check.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.client.CheckGroupUserClient;
import com.deloitte.platform.api.hr.check.param.CheckGroupUserQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckGroupUserForm;
import com.deloitte.platform.api.hr.check.vo.CheckGroupUserInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :   CheckGroupUser feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckGroupUserFeignService.HystrixCheckGroupUserService.class,primary = false)
public interface CheckGroupUserFeignService extends CheckGroupUserClient {

    @Component
    @Slf4j
    class HystrixCheckGroupUserService implements FallbackFactory<CheckGroupUserFeignService> {

        @Override
        public CheckGroupUserFeignService create(Throwable throwable) {
            return new CheckGroupUserFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckGroupUserForm checkGroupUserForm) {
                    log.error("CheckGroupUserService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckGroupUserService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckGroupUserForm checkGroupUserForm) {
                    log.error("CheckGroupUserService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckGroupUserService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckGroupUserQueryForm checkGroupUserQueryForm) {
                    log.error("CheckGroupUserService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckGroupUserQueryForm checkGroupUserQueryForm) {
                    log.error("CheckGroupUserService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<CheckGroupUserInfoVo>> getCheckGroupUserInfo(@Valid @RequestBody CheckGroupUserQueryForm checkGroupUserQueryForm) {
                    log.error("CheckGroupUserService getCheckGroupUserInfo服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}