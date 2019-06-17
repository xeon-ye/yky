package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.HrZpcpEmployTypeClient;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpEmployTypeQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpEmployTypeForm;
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
 * @Date : Create in 2019-04-01
 * @Description :   HrZpcpEmployType feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = HrZpcpEmployTypeFeignService.HystrixHrZpcpEmployTypeService.class,primary = false)
public interface HrZpcpEmployTypeFeignService extends HrZpcpEmployTypeClient {

    @Component
    @Slf4j
    class HystrixHrZpcpEmployTypeService implements FallbackFactory<HrZpcpEmployTypeFeignService> {

        @Override
        public HrZpcpEmployTypeFeignService create(Throwable throwable) {
            return new HrZpcpEmployTypeFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrZpcpEmployTypeForm hrZpcpEmployTypeForm) {
                    log.error("HrZpcpEmployTypeService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrZpcpEmployTypeService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrZpcpEmployTypeForm hrZpcpEmployTypeForm) {
                    log.error("HrZpcpEmployTypeService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrZpcpEmployTypeService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrZpcpEmployTypeQueryForm hrZpcpEmployTypeQueryForm) {
                    log.error("HrZpcpEmployTypeService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrZpcpEmployTypeQueryForm hrZpcpEmployTypeQueryForm) {
                    log.error("HrZpcpEmployTypeService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(DeleteForm deleteForm) {
                    log.error("HrZpcpEmployTypeService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}