package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.HrZpcpSalaryWelfareClient;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpSalaryWelfareQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpSalaryWelfareForm;
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

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :   HrZpcpSalaryWelfare feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = HrZpcpSalaryWelfareFeignService.HystrixHrZpcpSalaryWelfareService.class,primary = false)
public interface HrZpcpSalaryWelfareFeignService extends HrZpcpSalaryWelfareClient {

    @Component
    @Slf4j
    class HystrixHrZpcpSalaryWelfareService implements FallbackFactory<HrZpcpSalaryWelfareFeignService> {

        @Override
        public HrZpcpSalaryWelfareFeignService create(Throwable throwable) {
            return new HrZpcpSalaryWelfareFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrZpcpSalaryWelfareForm hrZpcpSalaryWelfareForm) {
                    log.error("HrZpcpSalaryWelfareService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrZpcpSalaryWelfareService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrZpcpSalaryWelfareForm hrZpcpSalaryWelfareForm) {
                    log.error("HrZpcpSalaryWelfareService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrZpcpSalaryWelfareService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrZpcpSalaryWelfareQueryForm hrZpcpSalaryWelfareQueryForm) {
                    log.error("HrZpcpSalaryWelfareService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrZpcpSalaryWelfareQueryForm hrZpcpSalaryWelfareQueryForm) {
                    log.error("HrZpcpSalaryWelfareService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportEmploywWelfareList(HrZpcpSalaryWelfareQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("HrZpcpSalaryWelfareService exportEmploywWelfareList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}