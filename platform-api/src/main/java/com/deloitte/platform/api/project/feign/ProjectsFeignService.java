package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ProjectsClient;
import com.deloitte.platform.api.project.param.ProjectsQueryForm;
import com.deloitte.platform.api.project.vo.ProjectsForm;
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
 * @Author : zhengchun
 * @Date : Create in 2019-05-24
 * @Description :   Projects feign客户端
 * @Modified :
 */
@FeignClient(name = "projects-service", fallbackFactory = ProjectsFeignService.HystrixProjectsService.class,primary = false)
public interface ProjectsFeignService extends ProjectsClient {

    @Component
    @Slf4j
    class HystrixProjectsService implements FallbackFactory<ProjectsFeignService> {

        @Override
        public ProjectsFeignService create(Throwable throwable) {
            return new ProjectsFeignService() {
                @Override
                public Result add(@Valid @RequestBody ProjectsForm projectsForm) {
                    log.error("ProjectsService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ProjectsService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ProjectsForm projectsForm) {
                    log.error("ProjectsService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ProjectsService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ProjectsQueryForm projectsQueryForm) {
                    log.error("ProjectsService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ProjectsQueryForm projectsQueryForm) {
                    log.error("ProjectsService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}