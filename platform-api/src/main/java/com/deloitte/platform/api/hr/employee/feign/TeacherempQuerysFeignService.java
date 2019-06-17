package com.deloitte.platform.api.hr.employee.feign;


import com.deloitte.platform.api.hr.employee.client.TeacherempQuerysClient;
import com.deloitte.platform.api.hr.employee.param.TeacherempQuerysQueryForm;
import com.deloitte.platform.api.hr.employee.vo.TeacherempQuerysForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : woo
 * @Date : Create in 2019-05-20
 * @Description :   TeacherempQuerys feign客户端
 * @Modified :
 */
@FeignClient(name = "teacherempQuerys-service", fallbackFactory = TeacherempQuerysFeignService.HystrixTeacherempQuerysService.class,primary = false)
public interface TeacherempQuerysFeignService extends TeacherempQuerysClient {

    @Component
    @Slf4j
    class HystrixTeacherempQuerysService implements FallbackFactory<TeacherempQuerysFeignService> {

        @Override
        public TeacherempQuerysFeignService create(Throwable throwable) {
            return new TeacherempQuerysFeignService() {
                @Override
                public Result add(@Valid @RequestBody TeacherempQuerysForm teacherempQuerysForm) {
                    log.error("TeacherempQuerysService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("TeacherempQuerysService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody TeacherempQuerysForm teacherempQuerysForm) {
                    log.error("TeacherempQuerysService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("TeacherempQuerysService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody TeacherempQuerysQueryForm teacherempQuerysQueryForm) {
                    log.error("TeacherempQuerysService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody TeacherempQuerysQueryForm teacherempQuerysQueryForm) {
                    log.error("TeacherempQuerysService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}