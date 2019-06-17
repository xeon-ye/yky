package com.deloitte.platform.api.hr.teacherAndPostdoctor.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.client.TeacherTrainTaskClient;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherTrainTaskExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherTrainTaskQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherTrainTaskSubQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.*;
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
 * @Date : Create in 2019-04-22
 * @Description :   TeacherTrainTask feign客户端
 * @Modified :
 */
@FeignClient(name = "teacherTrainTask-service", fallbackFactory = TeacherTrainTaskFeignService.HystrixTeacherTrainTaskService.class,primary = false)
public interface TeacherTrainTaskFeignService extends TeacherTrainTaskClient {

    @Component
    @Slf4j
    class HystrixTeacherTrainTaskService implements FallbackFactory<TeacherTrainTaskFeignService> {

        @Override
        public TeacherTrainTaskFeignService create(Throwable throwable) {
            return new TeacherTrainTaskFeignService() {
                @Override
                public Result add(@Valid @RequestBody TeacherTrainTaskForm teacherTrainTaskForm) {
                    log.error("TeacherTrainTaskService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public  Result importTask(MultipartFile file){
                    log.error("TeacherTrainTaskService importTask服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("TeacherTrainTaskService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result readMe(@PathVariable long id) {
                    log.error("TeacherTrainTaskService readMe服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getTeacherTrainTaskList(@Valid @RequestBody TeacherTrainTaskQueryForm teacherTrainTaskQueryForm) {
                    log.error("TeacherTrainTaskService getTeacherTrainTaskList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportTeacherTrainTaskList(@ModelAttribute TeacherTrainTaskExportForm teacherTrainTaskExportForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("TeacherTrainTaskService exportTeacherTrainTaskList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public  Result computeScale(){
                    log.error("TeacherTrainTaskService computeScale服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public  Result importGain(MultipartFile file){
                    log.error("TeacherTrainTaskService importGain服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getTeacherTrainTaskSubList(@Valid @RequestBody TeacherTrainTaskSubQueryForm teacherTrainTaskSubQueryForm){
                    log.error("TeacherTrainTaskService getTeacherTrainTaskSubList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public  Result updateTask(@Valid @RequestBody TeacherTrainTaskSubForm teacherTrainTaskSubForm){
                    log.error("TeacherTrainTaskService updateTask服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public  Result getTask(@RequestParam long teacherTrainId){
                    log.error("TeacherTrainTaskService getTask服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }


                @Override
                public   Result computeMark(@Valid @RequestBody TeacherTrainTaskMarkForm teacherTrainTaskMarkForm){
                    log.error("TeacherTrainTaskService computeMark服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportTaskGain(@ModelAttribute TeacherTrainTaskExportForm teacherTrainTaskExportForm, HttpServletRequest request, HttpServletResponse response){
                    log.error("TeacherTrainTaskServiceexportTaskGain服务不可用......");
                    throwable.printStackTrace();
                }
            };
        }
    }
}