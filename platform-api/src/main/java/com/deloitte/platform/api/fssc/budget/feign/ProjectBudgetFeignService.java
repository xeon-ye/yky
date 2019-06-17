package com.deloitte.platform.api.fssc.budget.feign;


import com.deloitte.platform.api.fssc.budget.client.BudgetProjectBudgetClient;
import com.deloitte.platform.api.fssc.budget.vo.ProjectBudgetSummaryVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-08
 * @Description :   PerformanceIndex feign客户端
 * @Modified :
 */
@FeignClient(name = "service-fssc", fallbackFactory = ProjectBudgetFeignService.ProjectBudgetService.class,primary = false)
public interface ProjectBudgetFeignService extends BudgetProjectBudgetClient {

    @Component
    @Slf4j
    class ProjectBudgetService implements FallbackFactory<ProjectBudgetFeignService> {

        @Override
        public ProjectBudgetFeignService create(Throwable throwable) {
            return new ProjectBudgetFeignService() {
                @Override
                public Result<ProjectBudgetSummaryVo> getProjectBudgetSummary(String projectCode, String taskCode, String budgetAnnual) {
                    log.error("ProjectBudgetService getProjectBudgetSummary服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}