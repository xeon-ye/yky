package com.deloitte.platform.api.hr.teacherAndPostdoctor.feign;


import com.deloitte.platform.api.hr.teacherAndPostdoctor.client.TeacherTrainGainClient;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherTrainGainQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.TeacherTrainGainForm;
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
 * @Author : jetvae
 * @Date : Create in 2019-04-22
 * @Description :   TeacherTrainGain feign客户端
 * @Modified :
 */
@FeignClient(name = "teacherTrainGain-service", fallbackFactory = TeacherTrainGainFeignService.HystrixTeacherTrainGainService.class,primary = false)
public interface TeacherTrainGainFeignService extends TeacherTrainGainClient {

    @Component
    @Slf4j
    class HystrixTeacherTrainGainService implements FallbackFactory<TeacherTrainGainFeignService> {

        @Override
        public TeacherTrainGainFeignService create(Throwable throwable) {
            return new TeacherTrainGainFeignService() {
                @Override
                public Result add(@Valid @RequestBody TeacherTrainGainForm teacherTrainGainForm) {
                    log.error("TeacherTrainGainService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("TeacherTrainGainService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody TeacherTrainGainForm teacherTrainGainForm) {
                    log.error("TeacherTrainGainService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("TeacherTrainGainService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody TeacherTrainGainQueryForm teacherTrainGainQueryForm) {
                    log.error("TeacherTrainGainService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody TeacherTrainGainQueryForm teacherTrainGainQueryForm) {
                    log.error("TeacherTrainGainService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}