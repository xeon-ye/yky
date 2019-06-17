package com.deloitte.platform.api.hr.employ.feign;


import com.deloitte.platform.api.hr.employ.client.RecruitDemandClient;
import com.deloitte.platform.api.hr.employ.param.RecruitDemandQueryForm;
import com.deloitte.platform.api.hr.employ.vo.RecruitDemandForm;
import com.deloitte.platform.api.hr.employ.vo.RecruitEmpForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-09
 * @Description :   RecruitDemand feign客户端
 * @Modified :
 */
@FeignClient(name = "recruitDemand-service", fallbackFactory = RecruitDemandFeignService.HystrixRecruitDemandService.class,primary = false)
public interface RecruitDemandFeignService extends RecruitDemandClient {

    @Component
    @Slf4j
    class HystrixRecruitDemandService implements FallbackFactory<RecruitDemandFeignService> {

        @Override
        public RecruitDemandFeignService create(Throwable throwable) {
            return new RecruitDemandFeignService() {
                @Override
                public void RecruitDemandExport(RecruitDemandQueryForm recruitDemandQueryForm, HttpServletRequest request, HttpServletResponse response) {

                }

                @Override
                public Result add(@Valid @RequestBody RecruitEmpForm recruitDemandForm) {
                    log.error("RecruitDemandService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("RecruitDemandService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody RecruitEmpForm recruitDemandForm) {
                    log.error("RecruitDemandService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("RecruitDemandService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody RecruitDemandQueryForm recruitDemandQueryForm) {
                    log.error("RecruitDemandService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody RecruitDemandQueryForm recruitDemandQueryForm) {
                    log.error("RecruitDemandService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}