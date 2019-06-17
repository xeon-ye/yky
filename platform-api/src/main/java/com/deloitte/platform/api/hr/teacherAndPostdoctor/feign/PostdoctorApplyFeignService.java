package com.deloitte.platform.api.hr.teacherAndPostdoctor.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.client.PostdoctorApplyClient;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorApplyQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorHarvestExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorHarvestQueryForm;
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

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   PostdoctorApply feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = PostdoctorApplyFeignService.HystrixPostdoctorApplyService.class,primary = false)
public interface PostdoctorApplyFeignService extends PostdoctorApplyClient {

    @Component
    @Slf4j
    class HystrixPostdoctorApplyService implements FallbackFactory<PostdoctorApplyFeignService> {

        @Override
        public PostdoctorApplyFeignService create(Throwable throwable) {
            return new PostdoctorApplyFeignService() {
                @Override
                public Result add(@Valid @RequestBody PostdoctorApplyForm postdoctorApplyForm) {
                    log.error("PostdoctorApplyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getUserInfo() {
                    log.error("PostdoctorApplyService getUserInfo服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public  Result<IPage<PostdoctorApplyVo>> getApplyList(@Valid @RequestBody PostdoctorApplyQueryForm postdoctorApplyQueryForm) {
                    log.error("PostdoctorApplyService getApplyList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addHarvestApply(PostdoctorHForm postdoctorHForm) {
                    log.error("PostdoctorApplyService addHarvestApply服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getApplyById(@PathVariable long id){
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public  Result<IPage<PostdoctorApplyCheckVo>> getHarvestCheckList(@Valid @RequestBody PostdoctorHarvestQueryForm postdoctorHarvestQueryForm) {
                    log.error("PostdoctorApplyService getHarvestCheckList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public  void exportHarvestCheckList(@ModelAttribute PostdoctorHarvestExportForm postdoctorHarvestExportForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("PostdoctorApplyService exportHarvestCheckList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result checkApply(String aidList, @RequestParam Integer checkType) {
                    log.error("PostdoctorApplyService checkApply服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result checkApplyById(@RequestBody PostdoctorCheckForm postdoctorCheckForm) {
                    log.error("PostdoctorApplyService checkApplyById服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public  void exportHarvestInfo(@RequestParam String appId, HttpServletRequest request, HttpServletResponse response) {
                    log.error("PostdoctorApplyService exportHarvestInfo服务不可用......");
                    throwable.printStackTrace();
                }
            };
        }
    }
}