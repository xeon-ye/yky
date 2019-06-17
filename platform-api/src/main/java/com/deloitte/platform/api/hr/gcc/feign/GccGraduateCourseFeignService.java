package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccGraduateCourseClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccGraduateCourseQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccGraduateCourseForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccGraduateCourse feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccGraduateCourseFeignService.HystrixGccGraduateCourseService.class,primary = false)
public interface GccGraduateCourseFeignService extends GccGraduateCourseClient {

    @Component
    @Slf4j
    class HystrixGccGraduateCourseService implements FallbackFactory<GccGraduateCourseFeignService> {

        @Override
        public GccGraduateCourseFeignService create(Throwable throwable) {
            return new GccGraduateCourseFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccGraduateCourseForm gccGraduateCourseForm) {
                    log.error("GccGraduateCourseService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccGraduateCourseService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccGraduateCourseForm gccGraduateCourseForm) {
                    log.error("GccGraduateCourseService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccGraduateCourseService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccGraduateCourseQueryForm gccGraduateCourseQueryForm) {
                    log.error("GccGraduateCourseService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccGraduateCourseQueryForm gccGraduateCourseQueryForm) {
                    log.error("GccGraduateCourseService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addOrUpdateList(List<GccGraduateCourseForm> gccGraduateCourseForms) {
                    log.error("GccGraduateCourseService addOrUpdateList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(GccBaseBatchForm form) {
                    log.error("GccGraduateCourseService  deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}