package com.deloitte.platform.api.hr.gcc.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.client.GccEmployContractClient;
import com.deloitte.platform.api.hr.gcc.param.GccEmployContractQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccBaseQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccEmployContractAllVo;
import com.deloitte.platform.api.hr.gcc.vo.GccEmployContractForm;
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
import java.io.IOException;
import java.util.List;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-12
 * @Description :   GccEmployContract feign客户端
 * @Modified :
 */
@FeignClient(name = "gccEmployContract-service", fallbackFactory = GccEmployContractFeignService.HystrixGccEmployContractService.class,primary = false)
public interface GccEmployContractFeignService extends GccEmployContractClient {

    @Component
    @Slf4j
    class HystrixGccEmployContractService implements FallbackFactory<GccEmployContractFeignService> {

        @Override
        public GccEmployContractFeignService create(Throwable throwable) {
            return new GccEmployContractFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccEmployContractForm gccEmployContractForm) {
                    log.error("GccEmployContractService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccEmployContractService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccEmployContractForm gccEmployContractForm) {
                    log.error("GccEmployContractService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccEmployContractService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccEmployContractQueryForm gccEmployContractQueryForm) {
                    log.error("GccEmployContractService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccEmployContractQueryForm gccEmployContractQueryForm) {
                    log.error("GccEmployContractService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<GccEmployContractAllVo>> searchAll(GccBaseQueryForm gccOnjobInformationQueryAllForm) {
                    log.error("GccEmployContractService searchAll服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<GccEmployContractAllVo>> listAll(GccBaseQueryForm gccOnjobInformationQueryAllForm) {
                    log.error("GccEmployContractService listAll服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportExcel(GccBaseQueryForm gccOnjobInformationQueryAllForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccEmployContractService lexportExcel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}