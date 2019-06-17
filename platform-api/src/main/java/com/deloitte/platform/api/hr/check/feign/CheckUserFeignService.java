package com.deloitte.platform.api.hr.check.feign;

import com.deloitte.platform.api.hr.check.client.CheckUserClient;
import com.deloitte.platform.api.hr.check.param.CheckUserInfoQueryForm;
import com.deloitte.platform.api.hr.check.param.CheckUserQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckUserAddForm;
import com.deloitte.platform.api.hr.check.vo.CheckUserForm;
import com.deloitte.platform.api.hr.check.vo.CheckUserInfoVo;
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
 * @Date : Create in 2019-04-01
 * @Description :   CheckUser feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckUserFeignService.HystrixCheckUserService.class,primary = false)
public interface CheckUserFeignService extends CheckUserClient {

    @Component
    @Slf4j
    class HystrixCheckUserService implements FallbackFactory<CheckUserFeignService> {

        @Override
        public CheckUserFeignService create(Throwable throwable) {
            return new CheckUserFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckUserForm checkUserForm) {
                    log.error("CheckUserService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckUserService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckUserForm checkUserForm) {
                    log.error("CheckUserService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckUserService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckUserQueryForm checkUserQueryForm) {
                    log.error("CheckUserService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckUserQueryForm checkUserQueryForm) {
                    log.error("CheckUserService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<CheckUserInfoVo>> getCheckUserInfo(@Valid @RequestBody CheckUserInfoQueryForm checkUserInfoQueryForm) {
                    log.error("CheckUserService getCheckUserinfo服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result beatchSaveOrUpdate(@Valid @RequestBody  CheckUserAddForm checkUserAddForm) {
                    log.error("CheckUserService beatchSaveOrUpdate服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result beatchDelete(@Valid @RequestBody  CheckUserAddForm checkUserAddForm) {
                    log.error("CheckUserService beatchDelete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportCheckuserInfo(@Valid @RequestBody CheckUserInfoQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("CheckUserService exportCheckuserInfo服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result importCheckUser(@RequestPart(value = "file") MultipartFile file, @RequestBody CheckUserAddForm checkImportForm) {
                    log.error("CheckUserService importCheckUser服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportCheckUserTempate(HttpServletRequest request, HttpServletResponse response) {
                    log.error("CheckUserService exportCheckUserTempate服务不可用......");
                    throwable.printStackTrace();
                }
            };
        }
    }
}