package com.deloitte.platform.api.isump.feign;


import com.deloitte.platform.api.isump.UserClient;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.GdcPage;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   User feign客户端
 * @Modified :
 */
@FeignClient(name = "service-isump", fallbackFactory = UserFeignService.HystrixUserService.class,primary = false)
public interface UserFeignService extends UserClient {

    @Component
    @Slf4j
    class HystrixUserService implements FallbackFactory<UserFeignService> {

        @Override
        public UserFeignService create(Throwable throwable) {
            return new UserFeignService() {
                @Override
                public Result add(@Valid @RequestBody UserForm userForm) {
                    log.error("UserService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("UserService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody UserForm userForm) {
                    log.error("UserService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("UserService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody UserQueryForm userQueryForm) {
                    log.error("UserService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }


                @Override
                public Result search(@Valid @RequestBody UserQueryForm userQueryForm) {
                    log.error("UserService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<GdcPage<UserVo>> search2(@Valid @RequestBody UserQueryForm userQueryForm) {
                    log.error("UserService search2服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result randSpecialist(@Valid @RequestBody UserQueryForm userQueryForm){
                    log.error("UserService randSpecialist服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getLoginUser(@PathVariable String token){
                    log.error("UserService getLoginUser服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result hasLogin(@PathVariable String token){
                    log.error("UserService hasLogin服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getByEmpNo(@PathVariable String empNo) {
                    log.error("UserService getByEmpNo服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result accountEnabled(@PathVariable long id){
                    log.error("UserService accountEnabled服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<OrganizationVo>> getUserVOByEmpNoDept(@PathVariable(value = "empNo") String empNo){
                    log.error("UserService getUserVOByEmpNoDept服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<UserVo>> getByOrgCode(@PathVariable(value = "orgCode") String orgCode) {
                    log.error("UserService getByOrgCode服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<UserVo>> getByOrgCodeList(@PathVariable(value = "orgCode") String orgCode) {
                    log.error("UserService getByOrgCodeList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<GdcPage<UserVo>> getByOrgCodeListPage(UserQueryForm userQueryForm) {
                    log.error("UserService getByOrgCodeListPage服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<UserVo>> getByRoleCode(@RequestParam(value = "systemCode") String systemCode,
                                                          @RequestParam(value = "roleCode") String roleCode,
                                                          @RequestParam(value = "deptList") List<String> deptList) {
                    log.error("UserService getByRoleCode服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<UserInterfaceVo> getByToken(@PathVariable(value = "token") String token) {
                    log.error("UserService getByToken服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<UserVo> getUserById(@PathVariable(value = "id") String id) {
                    log.error("UserService getUserById服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result updateUserSelfHelp(@PathVariable(value = "id") long id, @Valid @RequestBody userSelfHelpFormVo userSelfHelpFormVo) {
                    log.error("UserService updateUserSelfHelp服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<UserBpmVo>> getBpmUser(@RequestBody UserBpmForm userBpmForm) {
                    log.error("UserService getBpmUser服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<GdcPage<UserBpmVo>> getBpmUserListPage(@RequestBody UserBpmForm userBpmForm) {
                    log.error("UserService getBpmUserListPage服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addList(@Valid @RequestBody List<UserEBSForm> listUserEBSForm) {
                    log.error("UserService addList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<UserBpmVo>> getBpmUserByRole(UserBpmForm userBpmForm) {
                    log.error("UserService getBpmUserByRole服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}