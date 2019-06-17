package com.deloitte.platform.api.oaservice.notice.feign;

import com.deloitte.platform.api.oaservice.notice.client.OaMeetingRecordClient;
import com.deloitte.platform.api.oaservice.notice.param.OaMeetingRecordQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaMeetingRecordForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaMeetingRecordVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description :   OaMeetingRecord feign客户端
 * @Modified :
 */
@FeignClient(name = "oaMeetingRecord-service", fallbackFactory = OaMeetingRecordFeignService.HystrixOaMeetingRecordService.class,primary = false)
public interface OaMeetingRecordFeignService extends OaMeetingRecordClient {

    @Component
    @Slf4j
    class HystrixOaMeetingRecordService implements FallbackFactory<OaMeetingRecordFeignService> {

        @Override
        public OaMeetingRecordFeignService create(Throwable throwable) {
            return new OaMeetingRecordFeignService() {
                @Override
                public Result add(@Valid @RequestBody OaMeetingRecordForm oaMeetingRecordForm) {
                    log.error("OaMeetingRecordService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OaMeetingRecordService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OaMeetingRecordForm oaMeetingRecordForm) {
                    log.error("OaMeetingRecordService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OaMeetingRecordService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaMeetingRecordQueryForm oaMeetingRecordQueryForm) {
                    log.error("OaMeetingRecordService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaMeetingRecordQueryForm oaMeetingRecordQueryForm) {
                    log.error("OaMeetingRecordService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<OaMeetingRecordVo>> home(Integer num) {
                    log.error("OaMeetingRecordService home list 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}