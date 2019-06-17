package com.deloitte.platform.api.hr.teacherAndPostdoctor.feign;


import com.deloitte.platform.api.hr.teacherAndPostdoctor.client.PostdoctorHarvestClient;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorHarvestQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorHarvestForm;
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
 * @Description :   PostdoctorHarvest feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = PostdoctorHarvestFeignService.HystrixPostdoctorHarvestService.class,primary = false)
public interface PostdoctorHarvestFeignService extends PostdoctorHarvestClient {

    @Component
    @Slf4j
    class HystrixPostdoctorHarvestService implements FallbackFactory<PostdoctorHarvestFeignService> {

        @Override
        public PostdoctorHarvestFeignService create(Throwable throwable) {
            return new PostdoctorHarvestFeignService() {
                @Override
                public Result add(@Valid @RequestBody PostdoctorHarvestForm postdoctorHarvestForm) {
                    log.error("PostdoctorHarvestService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("PostdoctorHarvestService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody PostdoctorHarvestForm postdoctorHarvestForm) {
                    log.error("PostdoctorHarvestService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("PostdoctorHarvestService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody PostdoctorHarvestQueryForm postdoctorHarvestQueryForm) {
                    log.error("PostdoctorHarvestService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody PostdoctorHarvestQueryForm postdoctorHarvestQueryForm) {
                    log.error("PostdoctorHarvestService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}