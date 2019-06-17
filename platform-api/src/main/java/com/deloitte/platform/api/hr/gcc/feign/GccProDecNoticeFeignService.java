package com.deloitte.platform.api.hr.gcc.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.client.GccProDecNoticeClient;
import com.deloitte.platform.api.hr.gcc.param.GccProDecNoticeQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccProDecNoticeSelfForm;
import com.deloitte.platform.api.hr.gcc.param.ListProjectForNotice;
import com.deloitte.platform.api.hr.gcc.vo.GccProDecNoticeForm;
import com.deloitte.platform.api.hr.gcc.vo.GccProDecNoticeSelfVo;
import com.deloitte.platform.api.hr.gcc.vo.NoticeForProjectVo;
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
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccProDecNotice feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccProDecNoticeFeignService.HystrixGccProDecNoticeService.class,primary = false)
public interface GccProDecNoticeFeignService extends GccProDecNoticeClient {

    @Component
    @Slf4j
    class HystrixGccProDecNoticeService implements FallbackFactory<GccProDecNoticeFeignService> {

        @Override
        public GccProDecNoticeFeignService create(Throwable throwable) {
            return new GccProDecNoticeFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccProDecNoticeForm gccProDecNoticeForm) {
                    log.error("GccProDecNoticeService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccProDecNoticeService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccProDecNoticeForm gccProDecNoticeForm) {
                    log.error("GccProDecNoticeService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccProDecNoticeService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccProDecNoticeQueryForm gccProDecNoticeQueryForm) {
                    log.error("GccProDecNoticeService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccProDecNoticeQueryForm gccProDecNoticeQueryForm) {
                    log.error("GccProDecNoticeService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportExcel(GccProDecNoticeQueryForm gccProDecNoticeQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
                    log.error("GccProDecNoticeService exportExcel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());

                }

                @Override
                public Result setStart(long id) {
                    log.error("GccProDecNoticeService setStart服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());

                }

                @Override
                public Result<IPage<GccProDecNoticeSelfVo>> searchSelf(GccProDecNoticeSelfForm queryForm) {
                    log.error("GccProDecNoticeService searchSelf服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<NoticeForProjectVo>> listProject(ListProjectForNotice listProjectForNotice) {
                    log.error("GccProDecNoticeService listProject服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}