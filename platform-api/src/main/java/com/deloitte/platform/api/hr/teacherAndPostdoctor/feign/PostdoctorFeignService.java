package com.deloitte.platform.api.hr.teacherAndPostdoctor.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.client.PostdoctorClient;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorQueryForm;
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

import java.util.HashMap;
import java.util.List;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   Postdoctor feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = PostdoctorFeignService.HystrixPostdoctorService.class,primary = false)
public interface PostdoctorFeignService extends PostdoctorClient {

    @Component
    @Slf4j
    class HystrixPostdoctorService implements FallbackFactory<PostdoctorFeignService> {

        @Override
        public PostdoctorFeignService create(Throwable throwable) {
            return new PostdoctorFeignService() {
                @Override
                public Result add(MultipartFile file) {
                    log.error("PostdoctorService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("PostdoctorService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result getPostdoctorInfo(@Valid @RequestBody PostdoctorSearchForm postdoctorSearchForm) {
                    log.error("PostdoctorService getPostdoctorInfo服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result updatePull(@PathVariable long id, @Valid @RequestBody PostdoctorForm postdoctorForm) {
                    log.error("PostdoctorService updatePull服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result updatePush(@PathVariable long id, @Valid @RequestBody PostdoctorPushForm postdoctorPushForm,@RequestParam Integer type) {
                    log.error("PostdoctorService updatePush服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result updateDelay(@PathVariable long id, @Valid @RequestBody PostdoctorDelayForm postdoctorDelayForm) {
                    log.error("PostdoctorService updateDelay服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("PostdoctorService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result getPostdoctorByCode(@RequestParam String code,@RequestParam Integer status) {
                    log.error("PostdoctorService getPostdoctorByCode服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result getPostdoctorList(@Valid @RequestBody PostdoctorQueryForm postdoctorQueryForm,@RequestParam Integer type) {
                    log.error("PostdoctorService getPostdoctorList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public void exportPostdoctorList(@ModelAttribute PostdoctorExportForm postdoctorExportForm, HttpServletRequest request, HttpServletResponse response,@RequestParam Integer type) {
                    log.error("PostdoctorService exportPostdoctorList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result<IPage<PostdoctorExpireVo>> getPostdoctorExpireList(@RequestBody PostdoctorQueryForm postdoctorQueryForm) {
                    log.error("PostdoctorService getPostdoctorExpireList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public void exportPostdoctorExpireList(@ModelAttribute PostdoctorExportForm postdoctorExportForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("PostdoctorService exportPostdoctorList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public  Result sendExpireToUser(@RequestBody PostdoctorSearchForm msg){
                    log.error("PostdoctorService sendExpireToUser服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public  Result<HashMap<String,Object>> getInitTermSearch(){
                    log.error("PostdoctorService getInitTermSearch服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public void export(@ModelAttribute PostdoctorExportForm postdoctorExportForm, HttpServletRequest request, HttpServletResponse response, @RequestParam Integer type){
                    log.error("PostdoctorService export服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result<PostdoctorSelfVo> getPostdoctorByUserCode(String userCode,@RequestParam Integer type) {
                    log.error("PostdoctorService getPostdoctorByUserCode服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}