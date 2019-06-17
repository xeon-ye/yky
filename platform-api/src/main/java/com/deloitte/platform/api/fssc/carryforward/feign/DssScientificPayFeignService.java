package com.deloitte.platform.api.fssc.carryforward.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.carryforward.client.DssScientificPayClient;
import com.deloitte.platform.api.fssc.carryforward.param.DssScientificPayQueryForm;
import com.deloitte.platform.api.fssc.carryforward.vo.DssScientificPayForm;
import com.deloitte.platform.api.fssc.carryforward.vo.DssScientificPayVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import java.util.List;

@FeignClient(name = "service-fssc", fallbackFactory = DssScientificPayFeignService.HystrixDssScientificPayService.class,primary = false)
public interface DssScientificPayFeignService extends DssScientificPayClient {

    @Component
    @Slf4j
    class HystrixDssScientificPayService implements FallbackFactory<DssScientificPayFeignService> {
        @Override
        public DssScientificPayFeignService create(Throwable throwable) {
            return new DssScientificPayFeignService() {
                @Override
                public Result add(DssScientificPayForm dssScientificPayForm) {
                    log.error("DssScientificPayFeignService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result delete(long id) {
                    log.error("DssScientificPayFeignService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result update(long id, DssScientificPayForm dssScientificPayForm) {
                    log.error("DssScientificPayFeignService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result<DssScientificPayVo> get(long id) {
                    log.error("DssScientificPayFeignService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result<List<DssScientificPayVo>> list(DssScientificPayQueryForm dssScientificPayQueryForm) {
                    log.error("DssScientificPayFeignService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result<IPage<DssScientificPayVo>> search(DssScientificPayQueryForm dssScientificPayQueryForm) {
                    log.error("DssScientificPayFeignService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}
