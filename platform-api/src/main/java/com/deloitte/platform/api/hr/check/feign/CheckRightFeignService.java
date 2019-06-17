package com.deloitte.platform.api.hr.check.feign;

import com.deloitte.platform.api.hr.check.client.CheckRightClient;
import com.deloitte.platform.api.hr.check.param.CheckRightQueryForm;
import com.deloitte.platform.api.hr.check.param.CheckRightQueryInfoForm;
import com.deloitte.platform.api.hr.check.vo.CheckRightForm;
import com.deloitte.platform.api.hr.check.vo.CheckRightInfoVo;
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
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :   CheckRight feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckRightFeignService.HystrixCheckRightService.class,primary = false)
public interface CheckRightFeignService extends CheckRightClient {

    @Component
    @Slf4j
    class HystrixCheckRightService implements FallbackFactory<CheckRightFeignService> {

        @Override
        public CheckRightFeignService create(Throwable throwable) {
            return new CheckRightFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckRightForm checkRightForm) {
                    log.error("CheckRightService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckRightService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckRightForm checkRightForm) {
                    log.error("CheckRightService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckRightService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckRightQueryForm checkRightQueryForm) {
                    log.error("CheckRightService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckRightQueryForm checkRightQueryForm) {
                    log.error("CheckRightService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportCheckRightQueryInfoForm(@Valid @RequestBody CheckRightQueryInfoForm checkRightQueryInfoForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("CheckRightService exportCheckRightQueryInfoForm服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result<List<CheckRightInfoVo>> getCheckRightInfo(@Valid @RequestBody CheckRightQueryInfoForm checkRightQueryInfoForm) {
                    log.error("CheckRightService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchDelete( @Valid @RequestBody List<String> ids) {
                    log.error("CheckTimeService batchDelete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<String>> getAllOrgCodeByRight(@Valid @RequestBody CheckRightQueryForm queryForm) {
                    log.error("CheckRightService getAllOrgCodeByRight服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}