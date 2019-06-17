package com.deloitte.platform.api.srpmp.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.srpmp.project.budget.SrpmsProjectBudgetAccountClient;
import com.deloitte.platform.api.srpmp.project.budget.param.SrpmsProjectBudgetAccountQueryForm;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetAccountForm;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetAccountVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author:LIJUN
 * Date:14/05/2019
 * Description:
 */
@FeignClient(name = "service-srpmp", fallbackFactory = SrpmsProjectBudgetAccountFeignService.HystrixSrpmsProjectBudgetAccountService.class, primary = false)
public interface SrpmsProjectBudgetAccountFeignService extends SrpmsProjectBudgetAccountClient {

    @Component
    @Slf4j
    class HystrixSrpmsProjectBudgetAccountService implements FallbackFactory<SrpmsProjectBudgetAccountFeignService> {
        @Override
        public SrpmsProjectBudgetAccountFeignService create(Throwable throwable) {
            return new SrpmsProjectBudgetAccountFeignService() {
                @Override
                public Result saveOrUpdate(SrpmsProjectBudgetAccountForm srpmsProjectBudgetAccountForm) {
                    log.error("SrpmsProjectBudgetAccountFeignService saveOrUpdate服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(long id) {
                    log.error("SrpmsProjectBudgetAccountFeignService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<SrpmsProjectBudgetAccountVo> get(long id) {
                    log.error("SrpmsProjectBudgetAccountFeignService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<SrpmsProjectBudgetAccountVo>> list(SrpmsProjectBudgetAccountQueryForm srpmsProjectBudgetAccountQueryForm) {
                    log.error("SrpmsProjectBudgetAccountFeignService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<SrpmsProjectBudgetAccountVo>> search(SrpmsProjectBudgetAccountQueryForm srpmsProjectBudgetAccountQueryForm) {
                    log.error("SrpmsProjectBudgetAccountFeignService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}
