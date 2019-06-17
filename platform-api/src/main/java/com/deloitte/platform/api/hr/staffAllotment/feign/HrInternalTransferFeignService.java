package com.deloitte.platform.api.hr.staffAllotment.feign;


import com.deloitte.platform.api.hr.staffAllotment.client.HrInternalTransferClient;
import com.deloitte.platform.api.hr.staffAllotment.param.HrInternalTransferQueryForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrInternalTransferForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrInternalTransferVo;
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
 * @Description :   HrInternalTransfer feign客户端
 * @Modified :
 */
@FeignClient(name = "hrInternalTransfer-service", fallbackFactory = HrInternalTransferFeignService.HystrixHrInternalTransferService.class,primary = false)
public interface HrInternalTransferFeignService extends HrInternalTransferClient {

    @Component
    @Slf4j
    class HystrixHrInternalTransferService implements FallbackFactory<HrInternalTransferFeignService> {

        @Override
        public HrInternalTransferFeignService create(Throwable throwable) {
            return new HrInternalTransferFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrInternalTransferForm hrInternalTransferForm) {
                    log.error("HrInternalTransferService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrInternalTransferService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrInternalTransferForm hrInternalTransferForm) {
                    log.error("HrInternalTransferService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrInternalTransferService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrInternalTransferQueryForm hrInternalTransferQueryForm) {
                    log.error("HrInternalTransferService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrInternalTransferQueryForm hrInternalTransferQueryForm) {
                    log.error("HrInternalTransferService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result save(HrInternalTransferForm hrInternalTransferForm) {
                    log.error("HrInternalTransferService save服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result submit(HrInternalTransferForm hrInternalTransferForm) {
                    log.error("HrInternalTransferService submit服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<HrInternalTransferVo> selectByCode(HrInternalTransferVo hrInternalTransferVo) {
                    log.error("HrInternalTransferService selectByCode服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<HrInternalTransferVo> getByCode(HrInternalTransferVo hrInternalTransferVo) {
                    log.error("HrInternalTransferService getByCode服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result updateDate(HrInternalTransferForm hrInternalTransferForm) {
                    log.error("HrInternalTransferService updateDate服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}