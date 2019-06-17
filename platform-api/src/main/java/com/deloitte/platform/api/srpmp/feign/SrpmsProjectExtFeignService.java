package com.deloitte.platform.api.srpmp.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.srpmp.project.base.SrpmsProjectExtClient;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.GdcPage;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author:LIJUN
 * Date:29/04/2019
 * Description:
 */
@FeignClient(name = "service-srpmp", fallbackFactory = SrpmsProjectExtFeignService.HystrixSrpmsProjectExtService.class, primary = false)
public interface SrpmsProjectExtFeignService extends SrpmsProjectExtClient {

    @Component
    @Slf4j
    class HystrixSrpmsProjectExtService implements FallbackFactory<SrpmsProjectExtFeignService> {

        @Override
        public SrpmsProjectExtFeignService create(Throwable throwable) {
            return new SrpmsProjectExtFeignService() {
                @Override
                public Result<GdcPage<SrpmsProjectVo>> searchNoUser(SrpmsProjectQueryForm srpmsProjectQueryForm) {
                    log.error("SrpmsProjectExtFeignService searchNoUser服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<SrpmsProjectVo>> listByProjectIds(String projectIds) {
                    log.error("SrpmsProjectExtFeignService listByProjectIds服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }

}
