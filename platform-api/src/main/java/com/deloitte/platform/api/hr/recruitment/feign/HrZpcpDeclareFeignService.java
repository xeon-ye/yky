package com.deloitte.platform.api.hr.recruitment.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.client.HrZpcpDeclareClient;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpDeclareQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :   HrZpcpDeclare feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = HrZpcpDeclareFeignService.HystrixHrZpcpDeclareService.class,primary = false)
public interface HrZpcpDeclareFeignService extends HrZpcpDeclareClient {

    @Component
    @Slf4j
    class HystrixHrZpcpDeclareService implements FallbackFactory<HrZpcpDeclareFeignService> {

        @Override
        public HrZpcpDeclareFeignService create(Throwable throwable) {
            return new HrZpcpDeclareFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrZpcpDeclareForm hrZpcpDeclareForm) {
                    log.error("HrZpcpDeclareService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrZpcpDeclareService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrZpcpDeclareForm hrZpcpDeclareForm) {
                    log.error("HrZpcpDeclareService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrZpcpDeclareService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrZpcpDeclareQueryForm hrZpcpDeclareQueryForm) {
                    log.error("HrZpcpDeclareService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrZpcpDeclareQueryForm hrZpcpDeclareQueryForm) {
                    log.error("HrZpcpDeclareService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<ZpcpDeclareNoticeVo>> getDeclareNoticeList(HrZpcpDeclareQueryForm hrZpcpDeclareQueryForm) {
                    log.error("HrZpcpDeclareService getDeclareNoticeList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<HrZpcpDeclareVo>> getDeclarList(HrZpcpDeclareQueryForm hrZpcpDeclareQueryForm) {
                    log.error("HrZpcpDeclareService getDeclarList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportDeclareList(HrZpcpDeclareQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("HrZpcpDeclareService getDeclarList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result exportDeclareDetail(Long id, HttpServletRequest request, HttpServletResponse response) {
                    log.error("HrZpcpDeclareService exportDeclareDetail服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<HrZpcpDeclareVo>> getDeclarDetailList(Long noticeId) {
                    log.error("HrZpcpDeclareService getDeclarDetailList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<DeclareBaseVo> getDeclareDetail(Long noticeId,String empCode) {
                    log.error("HrZpcpDeclareService getDeclarDetailList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<DeclareBaseVo> getDeclareDetailWhenCheck(Long noticeId, String empCode) {
                    log.error("HrZpcpDeclareService getDeclarDetailList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }


                @Override
                public Result getIdType() {
                    log.error("HrZpcpDeclareService getDeclareDetail服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getDegree() {
                    log.error("HrZpcpDeclareService getDeclareDetail服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getEducation() {
                    log.error("HrZpcpDeclareService getDeclareDetail服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<SpecTechVo>> getSpectech() {
                    log.error("HrZpcpDeclareService getDeclareDetail服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<HrZpcpDeclareVo> getDeclareByNoticeId(long noticeId,String empCode) {
                    log.error("HrZpcpDeclareService getDeclareByNoticeId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<HrZpcpDeclareVo> getImpactDescriptionByNoticeId(long noticeId,String empCode) {
                    log.error("HrZpcpDeclareService getImpactDescriptionByNoticeId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}