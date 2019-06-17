package com.deloitte.platform.api.hr.reportManagement.feign;


import com.deloitte.platform.api.hr.reportManagement.client.HrAccountReportClient;
import com.deloitte.platform.api.hr.reportManagement.param.HrAccountReportQueryForm;
import com.deloitte.platform.api.hr.reportManagement.vo.HrAccountReportForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrEmployeeBaseVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-08
 * @Description :   HrAccount feign客户端
 * @Modified :
 */
@FeignClient(name = "hrAccount-service", fallbackFactory = HrAccountReportFeignService.HystrixHrAccountService.class,primary = false)
public interface HrAccountReportFeignService extends HrAccountReportClient {

    @Component
    @Slf4j
    class HystrixHrAccountService implements FallbackFactory<HrAccountReportFeignService> {

        @Override
        public HrAccountReportFeignService create(Throwable throwable) {
            return new HrAccountReportFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrAccountReportForm hrAccountReportForm) {
                    log.error("HrAccountService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrAccountService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrAccountReportForm hrAccountReportForm) {
                    log.error("HrAccountService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrAccountService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrAccountReportQueryForm hrAccountReportQueryForm) {
                    log.error("HrAccountService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrAccountReportQueryForm hrAccountReportQueryForm) {
                    log.error("HrAccountService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<HrEmployeeBaseVo> getEmployee(long id) {
                    log.error("HrAccountService getEmployee服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getInformation(long id) {
                    log.error("HrAccountService getInformation服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }


            };
        }
    }
}