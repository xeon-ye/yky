package com.deloitte.platform.api.hr.recruitment.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.client.ZpcpFundsUserClient;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpFundsUserQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpFundsUserForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpFundsUserVo;
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

/**
 * @Author : tankui
 * @Date : Create in 2019-04-09
 * @Description :   ZpcpFundsUser feign客户端
 * @Modified :
 */
@FeignClient(name = "zpcpFundsUser-service", fallbackFactory = ZpcpFundsUserFeignService.HystrixZpcpFundsUserService.class,primary = false)
public interface ZpcpFundsUserFeignService extends ZpcpFundsUserClient {

    @Component
    @Slf4j
    class HystrixZpcpFundsUserService implements FallbackFactory<ZpcpFundsUserFeignService> {

        @Override
        public ZpcpFundsUserFeignService create(Throwable throwable) {
            return new ZpcpFundsUserFeignService() {
                @Override
                public Result add(@Valid @RequestBody ZpcpFundsUserForm zpcpFundsUserForm) {
                    log.error("ZpcpFundsUserService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ZpcpFundsUserService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ZpcpFundsUserForm zpcpFundsUserForm) {
                    log.error("ZpcpFundsUserService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ZpcpFundsUserService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ZpcpFundsUserQueryForm zpcpFundsUserQueryForm) {
                    log.error("ZpcpFundsUserService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ZpcpFundsUserQueryForm zpcpFundsUserQueryForm) {
                    log.error("ZpcpFundsUserService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportFundsUserList(Long infoId , HttpServletRequest request, HttpServletResponse response) {
                    log.error("ZpcpFundsUserService exportFundsUserList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result<IPage<ZpcpFundsUserVo>> searchPage(ZpcpFundsUserQueryForm zpcpFundsUserQueryForm) {
                    log.error("ZpcpFundsUserService searchPage服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}