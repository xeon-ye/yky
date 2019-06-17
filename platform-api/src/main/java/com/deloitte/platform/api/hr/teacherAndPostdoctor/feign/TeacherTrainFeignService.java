package com.deloitte.platform.api.hr.teacherAndPostdoctor.feign;


import com.deloitte.platform.api.hr.teacherAndPostdoctor.client.TeacherTrainClient;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherTrainExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherTrainQueryForm;
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

import java.util.HashMap;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-19
 * @Description :   TeacherTrain feign客户端
 * @Modified :
 */
@FeignClient(name = "teacherTrain-service", fallbackFactory = TeacherTrainFeignService.HystrixTeacherTrainService.class,primary = false)
public interface TeacherTrainFeignService extends TeacherTrainClient {

    @Component
    @Slf4j
    class HystrixTeacherTrainService implements FallbackFactory<TeacherTrainFeignService> {

        @Override
        public TeacherTrainFeignService create(Throwable throwable) {
            return new TeacherTrainFeignService() {
                @Override
                public Result add(@RequestParam String fileUrl) {
                    log.error("TeacherTrainService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getTeacherTrainList(@Valid @RequestBody TeacherTrainQueryForm teacherTrainQueryForm) {
                    log.error("TeacherTrainService getTeacherTrainList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delTeacherByIds(@RequestParam String[] ids){
                    log.error("TeacherService delTeacherByIds服务不可用.....");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result submitTeacherTrain(String tidList, @RequestParam Integer subType) {
                    log.error("TeacherTrainService submitTeacherTrain服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportTeacherTrainList(@Valid @ModelAttribute TeacherTrainExportForm teacherTrainExportForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("TeacherTrainService exportTeacherTrainList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result getTeacherInitTermSearch(){
                    log.error("TeacherTrainService getTeacherInitTermSearch服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}