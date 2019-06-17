package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.HrZpcpEmployNoticeClient;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpEmployNoticeQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.BatchObj;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpEmployNoticeForm;
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
 * @Description :   HrZpcpEmployNotice feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = HrZpcpEmployNoticeFeignService.HystrixHrZpcpEmployNoticeService.class,primary = false)
public interface HrZpcpEmployNoticeFeignService extends HrZpcpEmployNoticeClient {

    @Component
    @Slf4j
    class HystrixHrZpcpEmployNoticeService implements FallbackFactory<HrZpcpEmployNoticeFeignService> {

        @Override
        public HrZpcpEmployNoticeFeignService create(Throwable throwable) {
            return new HrZpcpEmployNoticeFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrZpcpEmployNoticeForm hrZpcpEmployNoticeForm) {
                    log.error("HrZpcpEmployNoticeService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrZpcpEmployNoticeService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrZpcpEmployNoticeForm hrZpcpEmployNoticeForm) {
                    log.error("HrZpcpEmployNoticeService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrZpcpEmployNoticeService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrZpcpEmployNoticeQueryForm hrZpcpEmployNoticeQueryForm) {
                    log.error("HrZpcpEmployNoticeService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrZpcpEmployNoticeQueryForm hrZpcpEmployNoticeQueryForm) {
                    log.error("HrZpcpEmployNoticeService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result releaseList(BatchObj batchObj) {
                    log.error("HrZpcpEmployNoticeService releaseList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(DeleteForm deleteForm) {
                    log.error("HrZpcpEmployNoticeService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}