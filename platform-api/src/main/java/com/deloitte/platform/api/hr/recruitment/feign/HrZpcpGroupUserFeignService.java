package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.HrZpcpGroupUserClient;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpGroupUserQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpGroupUserForm;
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
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :   HrZpcpGroupUser feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = HrZpcpGroupUserFeignService.HystrixHrZpcpGroupUserService.class,primary = false)
public interface HrZpcpGroupUserFeignService extends HrZpcpGroupUserClient {

    @Component
    @Slf4j
    class HystrixHrZpcpGroupUserService implements FallbackFactory<HrZpcpGroupUserFeignService> {

        @Override
        public HrZpcpGroupUserFeignService create(Throwable throwable) {
            return new HrZpcpGroupUserFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrZpcpGroupUserForm hrZpcpGroupUserForm) {
                    log.error("HrZpcpGroupUserService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrZpcpGroupUserService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrZpcpGroupUserForm hrZpcpGroupUserForm) {
                    log.error("HrZpcpGroupUserService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrZpcpGroupUserService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrZpcpGroupUserQueryForm hrZpcpGroupUserQueryForm) {
                    log.error("HrZpcpGroupUserService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrZpcpGroupUserQueryForm hrZpcpGroupUserQueryForm) {
                    log.error("HrZpcpGroupUserService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportGroupUserList(HrZpcpGroupUserQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("exportGroupUserList list服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result addList(List<HrZpcpGroupUserForm> hrZpcpGroupUserForms) {
                    log.error("HrZpcpGroupUserService addList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(DeleteForm deleteForm) {
                    log.error("HrZpcpGroupUserService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}