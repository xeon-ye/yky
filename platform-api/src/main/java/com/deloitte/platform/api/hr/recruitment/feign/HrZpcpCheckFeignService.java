package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.HrZpcpCheckClient;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpCheckQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpCheckForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpCheckAndNumberVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :   HrZpcpCheck feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = HrZpcpCheckFeignService.HystrixHrZpcpCheckService.class, primary = false)
public interface HrZpcpCheckFeignService extends HrZpcpCheckClient {

    @Component
    @Slf4j
    class HystrixHrZpcpCheckService implements FallbackFactory<HrZpcpCheckFeignService> {

        @Override
        public HrZpcpCheckFeignService create(Throwable throwable) {
            return new HrZpcpCheckFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrZpcpCheckForm hrZpcpCheckForm) {
                    log.error("HrZpcpCheckService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrZpcpCheckService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrZpcpCheckForm hrZpcpCheckForm) {
                    log.error("HrZpcpCheckService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrZpcpCheckService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrZpcpCheckQueryForm hrZpcpCheckQueryForm) {
                    log.error("HrZpcpCheckService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrZpcpCheckQueryForm hrZpcpCheckQueryForm) {
                    log.error("HrZpcpCheckService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result getCheckByDeclareIdAndCheckType(HrZpcpCheckQueryForm queryForm) {
                    log.error("HrZpcpCheckService getCheckByDeclareIdAndCheckType服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result addOrUpdate(HrZpcpCheckForm hrZpcpCheckForm) {
                    log.error("HrZpcpCheckService addOrUpdate服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result importChcekLearning(MultipartFile file) {
                    log.error("HrZpcpCheckService importChcekLearning服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result importChcekProfessor(MultipartFile file) {
                    log.error("HrZpcpCheckService importChcekProfessor服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result importChcekTeaching(MultipartFile file) {
                    log.error("HrZpcpCheckService importChcekTeaching服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result<ZpcpCheckAndNumberVo> getNumberAndCheck(HrZpcpCheckQueryForm hrZpcpCheckQueryForm) {
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}