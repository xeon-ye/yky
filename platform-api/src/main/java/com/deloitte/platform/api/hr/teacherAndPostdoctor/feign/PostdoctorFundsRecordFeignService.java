package com.deloitte.platform.api.hr.teacherAndPostdoctor.feign;


import com.deloitte.platform.api.hr.teacherAndPostdoctor.client.PostdoctorFundsRecordClient;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorFundsRecordQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorFundsRecordForm;
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
 * @Description :   PostdoctorFundsRecord feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = PostdoctorFundsRecordFeignService.HystrixPostdoctorFundsRecordService.class,primary = false)
public interface PostdoctorFundsRecordFeignService extends PostdoctorFundsRecordClient {

    @Component
    @Slf4j
    class HystrixPostdoctorFundsRecordService implements FallbackFactory<PostdoctorFundsRecordFeignService> {

        @Override
        public PostdoctorFundsRecordFeignService create(Throwable throwable) {
            return new PostdoctorFundsRecordFeignService() {
                @Override
                public Result add(@Valid @RequestBody PostdoctorFundsRecordForm postdoctorFundsRecordForm) {
                    log.error("PostdoctorFundsRecordService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("PostdoctorFundsRecordService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody PostdoctorFundsRecordForm postdoctorFundsRecordForm) {
                    log.error("PostdoctorFundsRecordService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("PostdoctorFundsRecordService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody PostdoctorFundsRecordQueryForm postdoctorFundsRecordQueryForm) {
                    log.error("PostdoctorFundsRecordService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody PostdoctorFundsRecordQueryForm postdoctorFundsRecordQueryForm) {
                    log.error("PostdoctorFundsRecordService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}