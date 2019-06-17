package com.deloitte.platform.api.hr.employee.feign;


import com.deloitte.platform.api.hr.employee.client.HrarchivesinfoApplyClient;
import com.deloitte.platform.api.hr.employee.param.HrarchivesinfoApplyQueryForm;
import com.deloitte.platform.api.hr.employee.vo.HrarchivesinfoApplyForm;
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
 * @Author : LJH
 * @Date : Create in 2019-05-20
 * @Description :   HrarchivesinfoApply feign客户端
 * @Modified :
 */
@FeignClient(name = "hrarchivesinfoApply-service", fallbackFactory = HrarchivesinfoApplyFeignService.HystrixHrarchivesinfoApplyService.class,primary = false)
public interface HrarchivesinfoApplyFeignService extends HrarchivesinfoApplyClient {

    @Component
    @Slf4j
    class HystrixHrarchivesinfoApplyService implements FallbackFactory<HrarchivesinfoApplyFeignService> {

        @Override
        public HrarchivesinfoApplyFeignService create(Throwable throwable) {
            return new HrarchivesinfoApplyFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrarchivesinfoApplyForm hrarchivesinfoApplyForm) {
                    log.error("HrarchivesinfoApplyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrarchivesinfoApplyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrarchivesinfoApplyForm hrarchivesinfoApplyForm) {
                    log.error("HrarchivesinfoApplyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrarchivesinfoApplyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrarchivesinfoApplyQueryForm hrarchivesinfoApplyQueryForm) {
                    log.error("HrarchivesinfoApplyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrarchivesinfoApplyQueryForm hrarchivesinfoApplyQueryForm) {
                    log.error("HrarchivesinfoApplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}