package com.deloitte.platform.api.hr.teacherAndPostdoctor.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.client.PostdoctorFundsClient;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorFundsExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorFundsQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorFundsRecordQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorFundsForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorFundsInfoVo;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorFundsRecordForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorFundsRecordVo;
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
 * @Description :   PostdoctorFunds feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = PostdoctorFundsFeignService.HystrixPostdoctorFundsService.class,primary = false)
public interface PostdoctorFundsFeignService extends PostdoctorFundsClient {

    @Component
    @Slf4j
    class HystrixPostdoctorFundsService implements FallbackFactory<PostdoctorFundsFeignService> {

        @Override
        public PostdoctorFundsFeignService create(Throwable throwable) {
            return new PostdoctorFundsFeignService() {
                @Override
                public Result add(@Valid @RequestBody PostdoctorFundsForm postdoctorFundsForm) {
                    log.error("PostdoctorFundsService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@RequestParam String[] ids,@RequestParam Integer type){
                    log.error("PostdoctorFundsService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody PostdoctorFundsForm postdoctorFundsForm) {
                    log.error("PostdoctorFundsService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("PostdoctorFundsService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getRecodeById(@PathVariable long id) {
                    log.error("PostdoctorFundsService getRecodeById服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getPostdoctorByFundId(@PathVariable long id) {
                    log.error("PostdoctorFundsService getPostdoctorByFundId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportPostdoctorFundsList(@ModelAttribute PostdoctorFundsExportForm postdoctorFundsExportForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("PostdoctorFundsService exportPostdoctorFundsList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result getPostdoctorFundsList(@Valid @RequestBody PostdoctorFundsQueryForm postdoctorFundsQueryForm) {
                    log.error("PostdoctorFundsService getPostdoctorFundsList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addRecord(@Valid @RequestBody PostdoctorFundsRecordForm postdoctorFundsRecordForm){
                    log.error("PostdoctorFundsService addRecord服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteRecord(@PathVariable long id) {
                    log.error("PostdoctorFundsService deleteRecord服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result updateRecord(@PathVariable long id, @Valid @RequestBody PostdoctorFundsRecordForm postdoctorFundsRecordForm) {
                    log.error("PostdoctorFundsService updateRecord服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getPostdoctorFundsSurplusList(@Valid @RequestBody PostdoctorFundsQueryForm postdoctorFundsQueryForm) {
                    log.error("PostdoctorFundsService getPostdoctorFundsSurplusList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportPostdoctorFundsSurplusList(@ModelAttribute PostdoctorFundsExportForm postdoctorFundsExportForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("PostdoctorFundsService exportPostdoctorFundsSurplusList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result<IPage<PostdoctorFundsRecordVo>> getPostdoctorFundsRecordList(@Valid @RequestBody PostdoctorFundsRecordQueryForm postdoctorFundsRecordQueryForm) {
                    log.error("PostdoctorFundsService getPostdoctorFundsRecordList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportPostdoctorFundsRecordList(@ModelAttribute PostdoctorFundsExportForm postdoctorFundsExportForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("PostdoctorFundsService exportPostdoctorFundsRecordList服务不可用......");
                    throwable.printStackTrace();
                }


            };
        }
    }
}