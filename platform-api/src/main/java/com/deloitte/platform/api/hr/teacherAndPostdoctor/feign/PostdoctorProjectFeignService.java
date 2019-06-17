package com.deloitte.platform.api.hr.teacherAndPostdoctor.feign;


import com.deloitte.platform.api.hr.teacherAndPostdoctor.client.PostdoctorProjectClient;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorProjectExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorProjectQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorProjectForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   PostdoctorProject feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = PostdoctorProjectFeignService.HystrixPostdoctorProjectService.class,primary = false)
public interface PostdoctorProjectFeignService extends PostdoctorProjectClient {

    @Component
    @Slf4j
    class HystrixPostdoctorProjectService implements FallbackFactory<PostdoctorProjectFeignService> {

        @Override
        public PostdoctorProjectFeignService create(Throwable throwable) {
            return new PostdoctorProjectFeignService() {
                @Override
                public Result add(@Valid @RequestBody PostdoctorProjectForm postdoctorProjectForm) {
                    log.error("PostdoctorProjectService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@RequestParam String[] ids) {
                    log.error("PostdoctorProjectService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody PostdoctorProjectForm postdoctorProjectForm) {
                    log.error("PostdoctorProjectService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("PostdoctorProjectService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getInitTermSearch() {
                    log.error("PostdoctorProjectService getInitTermSearch服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportProjectList(@ModelAttribute PostdoctorProjectExportForm postdoctorProjectExportForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("PostdoctorProjectService exportProjectList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result getProjectList(@Valid @RequestBody PostdoctorProjectQueryForm postdoctorProjectQueryForm,@RequestParam Integer type) {
                    log.error("PostdoctorProjectService getProjectList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}