package com.deloitte.platform.api.hr.teacherAndPostdoctor.feign;


import com.deloitte.platform.api.hr.teacherAndPostdoctor.client.TeacherTrainAttClient;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherTrainAttExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherTrainAttQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.TeacherTrainAttForm;
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
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-20
 * @Description :   TeacherTrainAtt feign客户端
 * @Modified :
 */
@FeignClient(name = "teacherTrainAtt-service", fallbackFactory = TeacherTrainAttFeignService.HystrixTeacherTrainAttService.class,primary = false)
public interface TeacherTrainAttFeignService extends TeacherTrainAttClient {

    @Component
    @Slf4j
    class HystrixTeacherTrainAttService implements FallbackFactory<TeacherTrainAttFeignService> {

        @Override
        public TeacherTrainAttFeignService create(Throwable throwable) {
            return new TeacherTrainAttFeignService() {
                @Override
                public Result add(@Valid @RequestBody TeacherTrainAttForm teacherTrainAttForm) {
                    log.error("TeacherTrainAttService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public  void exportTeacherTrainAttList(@Valid @ModelAttribute TeacherTrainAttExportForm teacherTrainAttExportForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("TeacherTrainAttService exportTeacherTrainAttList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result getTeacherTrainAttList(@Valid @RequestBody TeacherTrainAttQueryForm teacherTrainAttQueryForm) {
                    log.error("TeacherTrainAttService getTeacherTrainAttList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getTeacherTrain(@RequestParam long tid){
                    log.error("TeacherTrainAttService getTeacherTrain服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result sendTrainAtt(@RequestParam String ids){
                    log.error("TeacherTrainAttService sendTrainAtt服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result importAtt(MultipartFile file){
                    log.error("TeacherTrainAttService importAtt服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}