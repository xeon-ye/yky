package com.deloitte.platform.api.hr.train.feign;


import com.deloitte.platform.api.hr.train.client.PersonTrainApplyClient;
import com.deloitte.platform.api.hr.train.param.PersonTrainApplyQueryForm;
import com.deloitte.platform.api.hr.train.vo.PersonTrainApplyForm;
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
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-02
 * @Description :   PersonTrainApply feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = PersonTrainApplyFeignService.HystrixPersonTrainApplyService.class,primary = false)
public interface PersonTrainApplyFeignService extends PersonTrainApplyClient {

    @Component
    @Slf4j
    class HystrixPersonTrainApplyService implements FallbackFactory<PersonTrainApplyFeignService> {

        @Override
        public PersonTrainApplyFeignService create(Throwable throwable) {
            return new PersonTrainApplyFeignService() {
                @Override
                public Result add(@Valid @RequestBody PersonTrainApplyForm personTrainApplyForm) {
                    log.error("PersonTrainApplyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("PersonTrainApplyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody PersonTrainApplyForm personTrainApplyForm) {
                    log.error("PersonTrainApplyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("PersonTrainApplyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody PersonTrainApplyQueryForm personTrainApplyQueryForm) {
                    log.error("PersonTrainApplyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody PersonTrainApplyQueryForm personTrainApplyQueryForm) {
                    log.error("PersonTrainApplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}