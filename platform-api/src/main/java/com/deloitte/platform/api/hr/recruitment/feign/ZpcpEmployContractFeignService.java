package com.deloitte.platform.api.hr.recruitment.feign;

import com.deloitte.platform.api.hr.recruitment.client.ZpcpEmployContractClient;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpEmployContractQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpEmployContractForm;
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

/**
 * @Author : tankui
 * @Date : Create in 2019-04-10
 * @Description :   ZpcpEmployContract feign客户端
 * @Modified :
 */
@FeignClient(name = "zpcpEmployContract-service", fallbackFactory = ZpcpEmployContractFeignService.HystrixZpcpEmployContractService.class,primary = false)
public interface ZpcpEmployContractFeignService extends ZpcpEmployContractClient {

    @Component
    @Slf4j
    class HystrixZpcpEmployContractService implements FallbackFactory<ZpcpEmployContractFeignService> {

        @Override
        public ZpcpEmployContractFeignService create(Throwable throwable) {
            return new ZpcpEmployContractFeignService() {
                @Override
                public Result add(@Valid @RequestBody ZpcpEmployContractForm zpcpEmployContractForm) {
                    log.error("ZpcpEmployContractService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ZpcpEmployContractService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ZpcpEmployContractForm zpcpEmployContractForm) {
                    log.error("ZpcpEmployContractService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ZpcpEmployContractService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ZpcpEmployContractQueryForm zpcpEmployContractQueryForm) {
                    log.error("ZpcpEmployContractService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ZpcpEmployContractQueryForm zpcpEmployContractQueryForm) {
                    log.error("ZpcpEmployContractService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportEmployContractList(ZpcpEmployContractQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("ZpcpEmployContractService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}