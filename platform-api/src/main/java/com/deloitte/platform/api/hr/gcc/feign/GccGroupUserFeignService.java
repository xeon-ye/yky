package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccGroupUserClient;
import com.deloitte.platform.api.hr.gcc.param.GccGroupUserQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.vo.GccGroupUserForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-08
 * @Description :   GccGroupUser feign客户端
 * @Modified :
 */
@FeignClient(name = "gccGroupUser-service", fallbackFactory = GccGroupUserFeignService.HystrixGccGroupUserService.class,primary = false)
public interface GccGroupUserFeignService extends GccGroupUserClient {

    @Component
    @Slf4j
    class HystrixGccGroupUserService implements FallbackFactory<GccGroupUserFeignService> {

        @Override
        public GccGroupUserFeignService create(Throwable throwable) {
            return new GccGroupUserFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccGroupUserForm gccGroupUserForm) {
                    log.error("GccGroupUserService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccGroupUserService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccGroupUserForm gccGroupUserForm) {
                    log.error("GccGroupUserService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccGroupUserService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccGroupUserQueryForm gccGroupUserQueryForm) {
                    log.error("GccGroupUserService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccGroupUserQueryForm gccGroupUserQueryForm) {
                    log.error("GccGroupUserService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchDelete(GccBaseBatchForm gcctGroupUserDeleteForm) {
                    log.error("GccGroupUserService batchdelete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addList(List<GccGroupUserForm> gccGroupUserForms) {
                    log.error("GccGroupUserService addList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}