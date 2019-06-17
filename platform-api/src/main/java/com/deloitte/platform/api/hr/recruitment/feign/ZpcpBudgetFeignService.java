package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.ZpcpBudgetClient;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpBudgetQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpBudgetForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpEmployerInfoVo;
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
 * @Author : tankui
 * @Date : Create in 2019-04-09
 * @Description :   ZpcpBudget feign客户端
 * @Modified :
 */
@FeignClient(name = "zpcpBudget-service", fallbackFactory = ZpcpBudgetFeignService.HystrixZpcpBudgetService.class,primary = false)
public interface ZpcpBudgetFeignService extends ZpcpBudgetClient {

    @Component
    @Slf4j
    class HystrixZpcpBudgetService implements FallbackFactory<ZpcpBudgetFeignService> {

        @Override
        public ZpcpBudgetFeignService create(Throwable throwable) {
            return new ZpcpBudgetFeignService() {
                @Override
                public Result add(@Valid @RequestBody ZpcpBudgetForm zpcpBudgetForm) {
                    log.error("ZpcpBudgetService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ZpcpBudgetService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ZpcpBudgetForm zpcpBudgetForm) {
                    log.error("ZpcpBudgetService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ZpcpBudgetService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ZpcpBudgetQueryForm zpcpBudgetQueryForm) {
                    log.error("ZpcpBudgetService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ZpcpBudgetQueryForm zpcpBudgetQueryForm) {
                    log.error("ZpcpBudgetService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportBudgetList(ZpcpBudgetQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("ZpcpBudgetService exportBudgetList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result<List<ZpcpEmployerInfoVo>> getBudgetEmployer(ZpcpBudgetQueryForm zpcpBudgetQueryForm) {
                    log.error("ZpcpBudgetService egetBudgetEmployer服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}