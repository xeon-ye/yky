package com.deloitte.platform.api.hr.teacherAndPostdoctor.feign;


import com.deloitte.platform.api.hr.teacherAndPostdoctor.client.PostdoctorApplyInfoClient;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorApplyInfoQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorApplyInfoForm;
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
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   PostdoctorApplyInfo feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = PostdoctorApplyInfoFeignService.HystrixPostdoctorApplyInfoService.class,primary = false)
public interface PostdoctorApplyInfoFeignService extends PostdoctorApplyInfoClient {

    @Component
    @Slf4j
    class HystrixPostdoctorApplyInfoService implements FallbackFactory<PostdoctorApplyInfoFeignService> {

        @Override
        public PostdoctorApplyInfoFeignService create(Throwable throwable) {
            return new PostdoctorApplyInfoFeignService() {
                @Override
                public Result add(@Valid @RequestBody PostdoctorApplyInfoForm postdoctorApplyInfoForm) {
                    log.error("PostdoctorApplyInfoService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("PostdoctorApplyInfoService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody PostdoctorApplyInfoForm postdoctorApplyInfoForm) {
                    log.error("PostdoctorApplyInfoService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("PostdoctorApplyInfoService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody PostdoctorApplyInfoQueryForm postdoctorApplyInfoQueryForm) {
                    log.error("PostdoctorApplyInfoService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody PostdoctorApplyInfoQueryForm postdoctorApplyInfoQueryForm) {
                    log.error("PostdoctorApplyInfoService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}