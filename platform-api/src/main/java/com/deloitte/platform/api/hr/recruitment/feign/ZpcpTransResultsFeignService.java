package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.ZpcpTransResultsClient;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpTransResultsQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpTransResultsDTO;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpTransResultsForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :   ZpcpTransResults feign客户端
 * @Modified :
 */
@FeignClient(name = "zpcpTransResults-service", fallbackFactory = ZpcpTransResultsFeignService.HystrixZpcpTransResultsService.class,primary = false)
public interface ZpcpTransResultsFeignService extends ZpcpTransResultsClient {

    @Component
    @Slf4j
    class HystrixZpcpTransResultsService implements FallbackFactory<ZpcpTransResultsFeignService> {

        @Override
        public ZpcpTransResultsFeignService create(Throwable throwable) {
            return new ZpcpTransResultsFeignService() {
                @Override
                public Result add(@Valid @RequestBody ZpcpTransResultsForm zpcpTransResultsForm) {
                    log.error("ZpcpTransResultsService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ZpcpTransResultsService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ZpcpTransResultsForm zpcpTransResultsForm) {
                    log.error("ZpcpTransResultsService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ZpcpTransResultsService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ZpcpTransResultsQueryForm zpcpTransResultsQueryForm) {
                    log.error("ZpcpTransResultsService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ZpcpTransResultsQueryForm zpcpTransResultsQueryForm) {
                    log.error("ZpcpTransResultsService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addOrUpdateList(ZpcpTransResultsDTO zpcpTransResultsDTO) {
                    log.error("ZpcpTransResultsService addOrUpdateList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
                @Override
                public Result deleteList(DeleteForm deleteForm) {
                    log.error("ZpcpDrugDeviceService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}