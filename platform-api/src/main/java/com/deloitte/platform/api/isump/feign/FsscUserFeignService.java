package com.deloitte.platform.api.isump.feign;


import com.deloitte.platform.api.isump.FsscUserClient;
import com.deloitte.platform.api.isump.vo.FsscUserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.GdcPage;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author : zhangdi
 * @Date : Create in 2019-04-15
 * @Description :   FsscUser feign客户端
 * @Modified :
 */
@FeignClient(name = "service-isump", fallbackFactory = FsscUserFeignService.HystrixFsscUserService.class,primary = false)
public interface FsscUserFeignService extends FsscUserClient {

    @Component
    @Slf4j
    class HystrixFsscUserService implements FallbackFactory<FsscUserFeignService> {

        @Override
        public FsscUserFeignService create(Throwable throwable) {
            return new FsscUserFeignService() {

                @Override
                public Result<List<FsscUserVo>> queryFsscUserInfo(@RequestParam(value = "empNo",required = false) String empNo,
                                                                  @RequestParam(value  ="userName",required = false) String userName) {
                    log.error("FsscUserService queryFsscUserInfo服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<GdcPage<FsscUserVo>> queryFsscUserInfoPage(@RequestParam(value = "empNo",required = false) String empNo,
                                                                         @RequestParam(value = "userName",required = false) String userName,
                                                                         @RequestParam(value = "country",required = false) String country,
                                                                         @RequestParam(value = "idCard",required = false) String idCard,
                                                                         @RequestParam(value = "payeeType",required = false) List<String> payeeType,
                                                                         @RequestParam(value = "currentPage",defaultValue = "0") int currentPage,
                                                                         @RequestParam(value = "pageSize",defaultValue = "15") int pageSize) {
                    log.error("FsscUserService queryFsscUserInfoPage服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}