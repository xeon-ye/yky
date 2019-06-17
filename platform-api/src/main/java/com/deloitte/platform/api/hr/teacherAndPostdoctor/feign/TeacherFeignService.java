package com.deloitte.platform.api.hr.teacherAndPostdoctor.feign;


import com.deloitte.platform.api.hr.teacherAndPostdoctor.client.TeacherClient;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.TeacherAddForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherApplyForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.TeacherApplyInfoForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.TeacherForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import io.swagger.annotations.ApiParam;
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
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   Teacher feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = TeacherFeignService.HystrixTeacherService.class,primary = false)
public interface TeacherFeignService extends TeacherClient {

    @Component
    @Slf4j
    class HystrixTeacherService implements FallbackFactory<TeacherFeignService> {

        @Override
        public TeacherFeignService create(Throwable throwable) {
            return new TeacherFeignService() {
                @Override
                public Result addTeacherByFileUrl(@RequestParam String fileUrl) {
                    log.error("TeacherService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("TeacherService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody TeacherForm teacherForm) {
                    log.error("TeacherService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }


                @Override
                public Result list(@Valid @RequestBody TeacherQueryForm teacherQueryForm,@RequestParam Integer type) {
                    log.error("TeacherService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("TeacherService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getUploadCert(@RequestParam long tid){
                    log.error("TeacherService getUploadCert服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportTeacherList(@Valid @ModelAttribute TeacherExportForm teacherExportForm, HttpServletRequest request, HttpServletResponse response,@RequestParam Integer type) {
                    log.error("TeacherService exportTeacherList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result teacherApplyList(@Valid @RequestBody TeacherApplyForm teacherApplyForm) {
                    log.error("TeacherService teacherApplyList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result uploadCert(@Valid @RequestBody TeacherApplyInfoForm teacherApplyInfoForm,Integer isSelfHelp) {
                    log.error("TeacherService uploadCert服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkApplyInfo(long tid,@RequestParam Integer isAdopt) {
                    log.error("TeacherService checkApplyInfo服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkAndSubApply(String tidList,@RequestParam  Integer subType) {
                    log.error("TeacherService checkAndSubApply服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result importFile(MultipartFile file) {
                    log.error("TeacherService importFile服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportTeacherCert(HttpServletRequest request, HttpServletResponse response) {
                    log.error("TeacherService exportTeacherCert服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result getTeacherInit() {
                    log.error("TeacherService getTeacherInit服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delTeacherByIds(@RequestParam String[] ids){
                    log.error("TeacherService delTeacherByIds服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}