package com.deloitte.platform.api.fssc.basecontract.feign;

import com.deloitte.platform.api.contract.vo.BasicInfoVoToFssc;
import com.deloitte.platform.api.fssc.basecontract.client.BaseContractInfoClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "service-fssc",fallbackFactory =FsscBaseContractInfoFeignService.HystrixFsscBaseContractInfoFeignService.class )
public interface FsscBaseContractInfoFeignService extends BaseContractInfoClient {

    @Component
    @Slf4j
    class HystrixFsscBaseContractInfoFeignService  implements FallbackFactory<FsscBaseContractInfoFeignService> {


        @Override
        public FsscBaseContractInfoFeignService create(Throwable throwable) {
            return new FsscBaseContractInfoFeignService() {
                @Override
                public Result receive(@Valid @RequestBody
                                                      @ApiParam(name = "BasicInfoVo", value = "BasicInfoVo", required = true)
                                                              List<BasicInfoVoToFssc> basicInfoVos) {
                    log.error("FsscBaseContractInfoFeignService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result receiveFinance(@Valid @RequestBody
                                                             @ApiParam(name = "BasicInfoVo", value = "BasicInfoVo", required = true)
                                                                     BasicInfoVoToFssc basicInfoVo) {
                    log.error("FsscBaseContractInfoFeignService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<Boolean> canModifyContract(
                                                @RequestParam("financeId") Long financeId) {
                    log.error("FsscBaseContractInfoFeignService 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }


}
