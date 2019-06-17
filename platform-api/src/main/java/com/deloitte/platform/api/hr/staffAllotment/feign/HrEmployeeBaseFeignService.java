package com.deloitte.platform.api.hr.staffAllotment.feign;


import com.deloitte.platform.api.hr.staffAllotment.client.HrEmployeeBaseClient;
import com.deloitte.platform.api.hr.staffAllotment.param.HrEmployeeBaseQueryForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrEmployeeBaseForm;
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
 * @Date : Create in 2019-04-03
 * @Description :   HrEmployeeBase feign客户端
 * @Modified :
 */
@FeignClient(name = "hrEmployeeBase-service", fallbackFactory = HrEmployeeBaseFeignService.HystrixHrEmployeeBaseService.class,primary = false)
public interface HrEmployeeBaseFeignService extends HrEmployeeBaseClient {

    @Component
    @Slf4j
    class HystrixHrEmployeeBaseService implements FallbackFactory<HrEmployeeBaseFeignService> {

        @Override
        public HrEmployeeBaseFeignService create(Throwable throwable) {
            return new HrEmployeeBaseFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrEmployeeBaseForm hrEmployeeBaseForm) {
                    log.error("HrEmployeeBaseService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrEmployeeBaseService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrEmployeeBaseForm hrEmployeeBaseForm) {
                    log.error("HrEmployeeBaseService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrEmployeeBaseService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrEmployeeBaseQueryForm hrEmployeeBaseQueryForm) {
                    log.error("HrEmployeeBaseService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrEmployeeBaseQueryForm hrEmployeeBaseQueryForm) {
                    log.error("HrEmployeeBaseService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}