package com.deloitte.platform.api.hr.technology.feign;

import com.deloitte.platform.api.hr.technology.client.RecruitTechnologyClient;
import com.deloitte.platform.api.hr.technology.param.RecruitTechnologyQueryForm;
import com.deloitte.platform.api.hr.technology.vo.RecruitTechnologyForm;
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
 * @Date : Create in 2019-04-15
 * @Description :   RecruitTechnology feign客户端
 * @Modified :
 */
@FeignClient(name = "recruitTechnology-service", fallbackFactory = RecruitTechnologyFeignService.HystrixRecruitTechnologyService.class,primary = false)
public interface RecruitTechnologyFeignService extends RecruitTechnologyClient {

    @Component
    @Slf4j
    class HystrixRecruitTechnologyService implements FallbackFactory<RecruitTechnologyFeignService> {

        @Override
        public RecruitTechnologyFeignService create(Throwable throwable) {
            return new RecruitTechnologyFeignService() {
                @Override
                public Result add(@Valid @RequestBody RecruitTechnologyForm recruitTechnologyForm) {
                    log.error("RecruitTechnologyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("RecruitTechnologyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody RecruitTechnologyForm recruitTechnologyForm) {
                    log.error("RecruitTechnologyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("RecruitTechnologyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody RecruitTechnologyQueryForm recruitTechnologyQueryForm) {
                    log.error("RecruitTechnologyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody RecruitTechnologyQueryForm recruitTechnologyQueryForm) {
                    log.error("RecruitTechnologyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}