package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.HrZpcpAssessmentClient;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpAssessmentQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpAssessmentForm;
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
 * @Date : Create in 2019-04-01
 * @Description :   HrZpcpAssessment feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = HrZpcpAssessmentFeignService.HystrixHrZpcpAssessmentService.class,primary = false)
public interface HrZpcpAssessmentFeignService extends HrZpcpAssessmentClient {

    @Component
    @Slf4j
    class HystrixHrZpcpAssessmentService implements FallbackFactory<HrZpcpAssessmentFeignService> {

        @Override
        public HrZpcpAssessmentFeignService create(Throwable throwable) {
            return new HrZpcpAssessmentFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrZpcpAssessmentForm hrZpcpAssessmentForm) {
                    log.error("HrZpcpAssessmentService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrZpcpAssessmentService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrZpcpAssessmentForm hrZpcpAssessmentForm) {
                    log.error("HrZpcpAssessmentService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrZpcpAssessmentService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrZpcpAssessmentQueryForm hrZpcpAssessmentQueryForm) {
                    log.error("HrZpcpAssessmentService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrZpcpAssessmentQueryForm hrZpcpAssessmentQueryForm) {
                    log.error("HrZpcpAssessmentService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportAssessmentList(HrZpcpAssessmentQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("HrZpcpAssessmentService exportAssessmentList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}