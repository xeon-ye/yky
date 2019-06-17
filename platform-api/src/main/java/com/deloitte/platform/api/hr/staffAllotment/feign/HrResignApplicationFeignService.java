package com.deloitte.platform.api.hr.staffAllotment.feign;


import com.deloitte.platform.api.hr.staffAllotment.client.HrResignApplicationClient;
import com.deloitte.platform.api.hr.staffAllotment.param.HrResignApplicationQueryForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrResignApplicationForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrResignApplicationVo;
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
 * @Description :   HrResignApplication feign客户端
 * @Modified :
 */
@FeignClient(name = "hrResignApplication-service", fallbackFactory = HrResignApplicationFeignService.HystrixHrResignApplicationService.class,primary = false)
public interface HrResignApplicationFeignService extends HrResignApplicationClient {

    @Component
    @Slf4j
    class HystrixHrResignApplicationService implements FallbackFactory<HrResignApplicationFeignService> {

        @Override
        public HrResignApplicationFeignService create(Throwable throwable) {
            return new HrResignApplicationFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrResignApplicationForm hrResignApplicationForm) {
                    log.error("HrResignApplicationService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrResignApplicationService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrResignApplicationForm hrResignApplicationForm) {
                    log.error("HrResignApplicationService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrResignApplicationService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrResignApplicationQueryForm hrResignApplicationQueryForm) {
                    log.error("HrResignApplicationService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrResignApplicationQueryForm hrResignApplicationQueryForm) {
                    log.error("HrResignApplicationService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result save(@Valid @RequestBody HrResignApplicationForm hrResignApplicationForm) {
                    log.error("HrResignApplicationService save服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result submit(@Valid @RequestBody HrResignApplicationForm hrResignApplicationForm) {
                    log.error("HrResignApplicationService submit服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<HrResignApplicationVo> selectByCode(HrResignApplicationVo hrResignApplicationVo) {
                    log.error("HrResignApplicationService selectByCode服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<HrResignApplicationVo> getByCode(HrResignApplicationVo hrResignApplicationVo) {
                    log.error("HrResignApplicationService getByCode服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result updateDate(HrResignApplicationForm hrResignApplicationForm) {
                    log.error("HrResignApplicationService updateDate服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}