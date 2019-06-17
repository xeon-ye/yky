package com.deloitte.platform.api.hr.staffAllotment.feign;


import com.deloitte.platform.api.hr.staffAllotment.client.HrExternalTransferClient;
import com.deloitte.platform.api.hr.staffAllotment.param.HrExternalTransferQueryForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrExternalTransferForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrExternalTransferVo;
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
 * @Description :   HrExternalTransfer feign客户端
 * @Modified :
 */
@FeignClient(name = "hrExternalTransfer-service", fallbackFactory = HrExternalTransferFeignService.HystrixHrExternalTransferService.class,primary = false)
public interface HrExternalTransferFeignService extends HrExternalTransferClient {

    @Component
    @Slf4j
    class HystrixHrExternalTransferService implements FallbackFactory<HrExternalTransferFeignService> {

        @Override
        public HrExternalTransferFeignService create(Throwable throwable) {
            return new HrExternalTransferFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrExternalTransferForm hrExternalTransferForm) {
                    log.error("HrExternalTransferService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrExternalTransferService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrExternalTransferForm hrExternalTransferForm) {
                    log.error("HrExternalTransferService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrExternalTransferService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrExternalTransferQueryForm hrExternalTransferQueryForm) {
                    log.error("HrExternalTransferService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrExternalTransferQueryForm hrExternalTransferQueryForm) {
                    log.error("HrExternalTransferService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result save(@Valid @RequestBody HrExternalTransferForm hrExternalTransferForm) {
                    log.error("HrExternalTransferService save服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result submit(@Valid @RequestBody HrExternalTransferForm hrExternalTransferForm) {
                    log.error("HrExternalTransferService submit服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<HrExternalTransferVo> selectByCode(HrExternalTransferVo hrExternalTransferForm) {
                    log.error("HrExternalTransferService selectByCode服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<HrExternalTransferVo> getByCode(HrExternalTransferVo hrExternalTransferForm) {
                    log.error("HrExternalTransferService getByCode服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result updateDate(HrExternalTransferForm hrExternalTransferForm) {
                    log.error("HrExternalTransferService updateDate服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}