package com.deloitte.platform.api.hr.gcc.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.client.GccProjectApplicationClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchStatusForm;
import com.deloitte.platform.api.hr.gcc.param.GccBaseQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccProjectApplicationQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccProjectApplication2Vo;
import com.deloitte.platform.api.hr.gcc.vo.GccProjectApplicationForm;
import com.deloitte.platform.api.hr.gcc.vo.GccReviewOptionBygroupVo;
import com.deloitte.platform.api.hr.recruitment.vo.DeclareBaseVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccProjectApplication feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccProjectApplicationFeignService.HystrixGccProjectApplicationService.class,primary = false)
public interface GccProjectApplicationFeignService extends GccProjectApplicationClient {

    @Component
    @Slf4j
    class HystrixGccProjectApplicationService implements FallbackFactory<GccProjectApplicationFeignService> {

        @Override
        public GccProjectApplicationFeignService create(Throwable throwable) {
            return new GccProjectApplicationFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccProjectApplicationForm gccProjectApplicationForm) {
                    log.error("GccProjectApplicationService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccProjectApplicationService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccProjectApplicationForm gccProjectApplicationForm) {
                    log.error("GccProjectApplicationService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccProjectApplicationService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccProjectApplicationQueryForm gccProjectApplicationQueryForm) {
                    log.error("GccProjectApplicationService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccProjectApplicationQueryForm gccProjectApplicationQueryForm) {
                    log.error("GccProjectApplicationService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<GccProjectApplication2Vo>> listApplication(GccProjectApplicationQueryForm gccProjectApplicationQueryForm) {
                    log.error("GccProjectApplicationService listApplication服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<GccProjectApplication2Vo>> searchApplication(GccProjectApplicationQueryForm gccProjectApplicationQueryForm) {
                    log.error("GccProjectApplicationService searchApplication服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportExcel(GccProjectApplicationQueryForm gccProjectApplicationQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccProjectApplicationService exportExcel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result updateByStatus(long id, String status) {
                    log.error("GccProjectApplicationService updateByStatus服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportDeclareDetail(Long id, HttpServletRequest request, HttpServletResponse response) {
                    log.error("GccProjectApplicationService exportDeclareDetail服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<DeclareBaseVo> getDeclareDetail(Long  userId) {
                    log.error("GccProjectApplicationService getDeclareDetail服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<GccReviewOptionBygroupVo>> listByGroup(GccBaseQueryForm queryForm) {
                    log.error("GccProjectApplicationService listByGroup服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<GccReviewOptionBygroupVo>> searchByGroup(GccBaseQueryForm queryForm) {
                    log.error("GccProjectApplicationService searchByGroup服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result updateListByStatus(GccBaseBatchStatusForm form) {
                    log.error("GccProjectApplicationService updateListByStatus服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result export(GccBaseQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccProjectApplicationService export服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportResult(GccBaseQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccProjectApplicationService exportResult服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportReview(GccBaseQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccProjectApplicationService exportReview服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<GccReviewOptionBygroupVo> getDeclareDetails(GccProjectApplicationQueryForm queryForm) {
                    log.error("GccProjectApplicationService getDeclareDetails服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result importExcel(MultipartFile file) throws IOException {
                    return null;
                }
            };
        }
    }
}