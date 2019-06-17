package com.deloitte.platform.api.hr.check.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.client.CheckRelationClient;
import com.deloitte.platform.api.hr.check.param.CheckRelationApprovalQueryFrom;
import com.deloitte.platform.api.hr.check.param.CheckRelationQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckRelationApprovalVo;
import com.deloitte.platform.api.hr.check.vo.CheckRelationForm;
import com.deloitte.platform.api.hr.check.vo.CheckRelationInfoVo;
import com.deloitte.platform.api.hr.check.vo.CommonProcessForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :   CheckRelation feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckRelationFeignService.HystrixCheckRelationService.class,primary = false)
public interface CheckRelationFeignService extends CheckRelationClient {

    @Component
    @Slf4j
    class HystrixCheckRelationService implements FallbackFactory<CheckRelationFeignService> {

        @Override
        public CheckRelationFeignService create(Throwable throwable) {
            return new CheckRelationFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckRelationForm checkRelationForm) {
                    log.error("CheckRelationService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckRelationService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckRelationForm checkRelationForm) {
                    log.error("CheckRelationService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckRelationService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckRelationQueryForm checkRelationQueryForm) {
                    log.error("CheckRelationService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckRelationQueryForm checkRelationQueryForm) {
                    log.error("CheckRelationService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<CheckRelationInfoVo>> getCheckRelationInfoVo(CheckRelationQueryForm checkRelationQueryForm) {
                    log.error("CheckRelationService getCheckRelationInfoVo服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result batchDelete( @Valid @RequestBody List<String> ids) {
                    log.error("CheckRelationService batchDelete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<CheckRelationApprovalVo>> getCheckRelationApproval( @Valid @RequestBody CheckRelationApprovalQueryFrom checkRelationApprovalQueryFrom) {
                    log.error("CheckRelationService getCheckRelationApproval服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportCheckRelationApproval(@Valid @RequestBody  CheckRelationApprovalQueryFrom checkRelationApprovalQueryFrom, HttpServletRequest request, HttpServletResponse response) {
                    log.error("CheckRelationService exportCheckRelationApproval服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public Result approveCheckRelation(@Valid @RequestBody CheckRelationApprovalQueryFrom checkRelationApprovalQueryFrom) {
                    log.error("CheckRelationService approveCheckRelation服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result dealApprove(@Valid @RequestBody CommonProcessForm form) {
                    log.error("CheckRelationService dealApprove服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}