package com.deloitte.platform.api.hr.teacherAndPostdoctor.feign;


import com.deloitte.platform.api.hr.teacherAndPostdoctor.client.PostdoctorCheckRecordClient;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorCheckRecordQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorCheckRecordForm;
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

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   PostdoctorCheckRecord feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = PostdoctorCheckRecordFeignService.HystrixPostdoctorCheckRecordService.class,primary = false)
public interface PostdoctorCheckRecordFeignService extends PostdoctorCheckRecordClient {

    @Component
    @Slf4j
    class HystrixPostdoctorCheckRecordService implements FallbackFactory<PostdoctorCheckRecordFeignService> {

        @Override
        public PostdoctorCheckRecordFeignService create(Throwable throwable) {
            return new PostdoctorCheckRecordFeignService() {
                @Override
                public Result add(@Valid @RequestBody PostdoctorCheckRecordForm postdoctorCheckRecordForm) {
                    log.error("PostdoctorCheckRecordService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("PostdoctorCheckRecordService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody PostdoctorCheckRecordForm postdoctorCheckRecordForm) {
                    log.error("PostdoctorCheckRecordService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("PostdoctorCheckRecordService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }


                @Override
                public Result getCheckRecordList(@Valid @RequestBody PostdoctorQueryForm postdoctorQueryForm) {
                    log.error("PostdoctorCheckRecordService getCheckRecordList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public  void exportCheckRecordList(@ModelAttribute PostdoctorExportForm postdoctorExportForm, HttpServletRequest request, HttpServletResponse response){
                    log.error("PostdoctorCheckRecordService exportCheckRecordList服务不可用......");
                    throwable.printStackTrace();
                }

            };
        }
    }
}