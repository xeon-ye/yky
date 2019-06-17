package com.deloitte.platform.api.hr.employee.feign;


import com.deloitte.platform.api.hr.employee.client.HrarchivesApplyClient;
import com.deloitte.platform.api.hr.employee.param.HrarchivesApplyQueryForm;
import com.deloitte.platform.api.hr.employee.vo.HrarchivesApplyForm;
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
 * @Date : Create in 2019-05-21
 * @Description :   HrarchivesApply feign客户端
 * @Modified :
 */
@FeignClient(name = "hrarchivesApply-service", fallbackFactory = HrarchivesApplyFeignService.HystrixHrarchivesApplyService.class,primary = false)
public interface HrarchivesApplyFeignService extends HrarchivesApplyClient {

    @Component
    @Slf4j
    class HystrixHrarchivesApplyService implements FallbackFactory<HrarchivesApplyFeignService> {

        @Override
        public HrarchivesApplyFeignService create(Throwable throwable) {
            return new HrarchivesApplyFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrarchivesApplyForm hrarchivesApplyForm) {
                    log.error("HrarchivesApplyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrarchivesApplyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrarchivesApplyForm hrarchivesApplyForm) {
                    log.error("HrarchivesApplyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrarchivesApplyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrarchivesApplyQueryForm hrarchivesApplyQueryForm) {
                    log.error("HrarchivesApplyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrarchivesApplyQueryForm hrarchivesApplyQueryForm) {
                    log.error("HrarchivesApplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}