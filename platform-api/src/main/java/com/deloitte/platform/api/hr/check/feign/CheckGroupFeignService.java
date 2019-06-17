package com.deloitte.platform.api.hr.check.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.client.CheckGroupClient;
import com.deloitte.platform.api.hr.check.param.CheckGroupQueryForm;
import com.deloitte.platform.api.hr.check.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :   CheckGroup feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckGroupFeignService.HystrixCheckGroupService.class,primary = false)
public interface CheckGroupFeignService extends CheckGroupClient {

    @Component
    @Slf4j
    class HystrixCheckGroupService implements FallbackFactory<CheckGroupFeignService> {

        @Override
        public CheckGroupFeignService create(Throwable throwable) {
            return new CheckGroupFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckGroupForm checkGroupForm) {
                    log.error("CheckGroupService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckGroupService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckGroupForm checkGroupForm) {
                    log.error("CheckGroupService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckGroupService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckGroupQueryForm checkGroupQueryForm) {
                    log.error("CheckGroupService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckGroupQueryForm checkGroupQueryForm) {
                    log.error("CheckGroupService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<CheckGroupInfoVo>> getCheckGroupInfo(@Valid @RequestBody CheckGroupQueryForm checkGroupQueryForm) {
                    log.error("CheckGroupService getCheckGroupInfo服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchDelete( @Valid @RequestBody List<Long> ids) {
                    log.error("CheckGroupService batchDelete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<CheckGroupAndUserVo>> getCheckGroupAndUser(@Valid @RequestBody CheckGroupQueryForm checkGroupQueryForm) {
                    log.error("CheckGroupService getCheckGroupAndUser服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportCheckGroupList( @Valid @RequestBody CheckGroupExportForm checkGroupExportFor, HttpServletRequest request, HttpServletResponse response) {
                    log.error("CheckGroupService exportCheckGroupList服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public void exportRoster(@Valid @RequestBody  CheckGroupQueryForm checkGroupQueryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("CheckGroupService exportRoster服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result<List<CheckGroupUserCountVo>> getCheckGroupUserNumber(@Valid @RequestBody CheckGroupUserCountForm checkGroupUserCountForm) {
                    log.error("CheckGroupService getCheckGroupUserNumber服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result importCheckGroupAndUser(@RequestPart(value = "file") MultipartFile file, @RequestBody  CheckGroupForm checkGroupForm) {
                    log.error("CheckGroupService importCheckGroupAndUser服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportCheckGroupTempate(HttpServletRequest request, HttpServletResponse response) {
                    log.error("CheckGroupService exportRoster服务不可用......");
                    throwable.printStackTrace();
                }
            };
        }
    }
}