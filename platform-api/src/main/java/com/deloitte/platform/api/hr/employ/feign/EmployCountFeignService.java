package com.deloitte.platform.api.hr.employ.feign;


import com.deloitte.platform.api.hr.employ.client.EmployCountClient;
import com.deloitte.platform.api.hr.employ.param.EmployCountQueryForm;
import com.deloitte.platform.api.hr.employ.vo.EmployCountForm;
import com.deloitte.platform.api.hr.employ.vo.ListEmployCountForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.param.HrApplyingFlowQueryForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrApplyingFlowVo;
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

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-08
 * @Description :   EmployCount feign客户端
 * @Modified :
 */
@FeignClient(name = "employCount-service", fallbackFactory = EmployCountFeignService.HystrixEmployCountService.class,primary = false)
public interface EmployCountFeignService extends EmployCountClient {

    @Component
    @Slf4j
    class HystrixEmployCountService implements FallbackFactory<EmployCountFeignService> {

        @Override
        public EmployCountFeignService create(Throwable throwable) {
            return new EmployCountFeignService() {


                @Override
                public Result clickExecute(ListEmployCountForm listEmployCountForm) {
                    return null;
                }

                @Override
                public Result<List<HrApplyingFlowVo>> getApplyingFlow(long id) {
                    return null;
                }

                @Override
                public void exportEmployList(HrApplyingFlowQueryForm hrApplyingFlowQueryForm, HttpServletRequest request, HttpServletResponse response) {
                }

                @Override
                public Result add(@Valid @RequestBody EmployCountForm employCountForm) {
                    log.error("EmployCountService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployCountService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployCountForm employCountForm) {
                    log.error("EmployCountService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployCountService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployCountQueryForm employCountQueryForm) {
                    log.error("EmployCountService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployCountQueryForm employCountQueryForm) {
                    log.error("EmployCountService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}