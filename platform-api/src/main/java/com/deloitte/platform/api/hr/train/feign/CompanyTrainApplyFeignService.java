package com.deloitte.platform.api.hr.train.feign;


import com.deloitte.platform.api.hr.train.client.CompanyTrainApplyClient;
import com.deloitte.platform.api.hr.train.param.CompanyTrainApplyQueryForm;
import com.deloitte.platform.api.hr.train.vo.CompanyTrainApplyForm;
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
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-02
 * @Description :   CompanyTrainApply feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CompanyTrainApplyFeignService.HystrixCompanyTrainApplyService.class,primary = false)
public interface CompanyTrainApplyFeignService extends CompanyTrainApplyClient {

    @Component
    @Slf4j
    class HystrixCompanyTrainApplyService implements FallbackFactory<CompanyTrainApplyFeignService> {

        @Override
        public CompanyTrainApplyFeignService create(Throwable throwable) {
            return new CompanyTrainApplyFeignService() {
                @Override
                public Result add(@Valid @RequestBody CompanyTrainApplyForm companyTrainApplyForm) {
                    log.error("CompanyTrainApplyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CompanyTrainApplyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CompanyTrainApplyForm companyTrainApplyForm) {
                    log.error("CompanyTrainApplyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CompanyTrainApplyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CompanyTrainApplyQueryForm companyTrainApplyQueryForm) {
                    log.error("CompanyTrainApplyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CompanyTrainApplyQueryForm companyTrainApplyQueryForm) {
                    log.error("CompanyTrainApplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}