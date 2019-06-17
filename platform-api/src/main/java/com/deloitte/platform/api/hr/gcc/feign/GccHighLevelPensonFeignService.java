package com.deloitte.platform.api.hr.gcc.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.client.GccHighLevelPensonClient;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccHighLevelPensonQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccHighLevelPensonQueryForm2;
import com.deloitte.platform.api.hr.gcc.vo.GccHighLevelFundsVo;
import com.deloitte.platform.api.hr.gcc.vo.GccHighLevelPensonForm;
import com.deloitte.platform.api.hr.gcc.vo.GccHighLevelPensonHighVo;
import com.deloitte.platform.api.hr.gcc.vo.GccHighLevelPensonUnitVo;
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
 * @Author : liangjinghai
 * @Date : Create in 2019-04-09
 * @Description :   GccHighLevelPenson feign客户端
 * @Modified :
 */
@FeignClient(name = "gccHighLevelPenson-service", fallbackFactory = GccHighLevelPensonFeignService.HystrixGccHighLevelPensonService.class,primary = false)
public interface GccHighLevelPensonFeignService extends GccHighLevelPensonClient {

    @Component
    @Slf4j
    class HystrixGccHighLevelPensonService implements FallbackFactory<GccHighLevelPensonFeignService> {

        @Override
        public GccHighLevelPensonFeignService create(Throwable throwable) {
            return new GccHighLevelPensonFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccHighLevelPensonForm gccHighLevelPensonForm) {
                    log.error("GccHighLevelPensonService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccHighLevelPensonService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccHighLevelPensonForm gccHighLevelPensonForm) {
                    log.error("GccHighLevelPensonService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccHighLevelPensonService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccHighLevelPensonQueryForm gccHighLevelPensonQueryForm) {
                    log.error("GccHighLevelPensonService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccHighLevelPensonQueryForm gccHighLevelPensonQueryForm) {
                    log.error("GccHighLevelPensonService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result importExcel(MultipartFile file) throws IOException {
                    log.error("GccHighLevelPensonService importExcel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                @Override
                public Result exportExcel(GccHighLevelPensonQueryForm gccHighLevelPensonQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccHighLevelPensonService exportExcel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                @Override
                public Result<List<GccHighLevelPensonHighVo>> listHigh(GccHighLevelPensonQueryForm2 gccHighLevelPensonQueryForm) {
                    log.error("GccHighLevelPensonService listHigh服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                @Override
                public Result<IPage<GccHighLevelPensonHighVo>> searchHigh(GccHighLevelPensonQueryForm2 gccHighLevelPensonQueryForm) {
                    log.error("GccHighLevelPensonService searchHigh服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportHighExcel(GccHighLevelPensonQueryForm2 gccHighLevelPensonQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccHighLevelPensonService exportHighExcel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportHighNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccHighLevelPensonService exportHighNumber服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public List<GccHighLevelPensonUnitVo> exportYearUnit(HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccHighLevelPensonService exportYearUnit服务不可用......");
                    throwable.printStackTrace();
                    return (List<GccHighLevelPensonUnitVo>) Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<GccHighLevelFundsVo>> searchHighFunds(GccHighLevelPensonQueryForm gccHighLevelPensonQueryForm) {
                    log.error("GccHighLevelPensonService searchHighFunds服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportHighFunds(GccHighLevelPensonQueryForm gccHighLevelPensonQueryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("GccHighLevelPensonService exportHighFunds服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result importExcelFunds(MultipartFile file) throws IOException {
                    log.error("GccHighLevelPensonService importExcelFunds服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(GccBaseBatchForm form) {
                    return null;
                }
            };
        }
    }
}