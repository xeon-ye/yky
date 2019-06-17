package com.deloitte.platform.api.hr.check.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.client.CheckResultClient;
import com.deloitte.platform.api.hr.check.param.*;
import com.deloitte.platform.api.hr.check.vo.*;
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
 * @Description :   CheckResult feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = CheckResultFeignService.HystrixCheckResultService.class,primary = false)
public interface CheckResultFeignService extends CheckResultClient {

    @Component
    @Slf4j
    class HystrixCheckResultService implements FallbackFactory<CheckResultFeignService> {

        @Override
        public CheckResultFeignService create(Throwable throwable) {
            return new CheckResultFeignService() {
                @Override
                public Result add(@Valid @RequestBody CheckResultForm checkResultForm) {
                    log.error("CheckResultService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CheckResultService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CheckResultForm checkResultForm) {
                    log.error("CheckResultService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CheckResultService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CheckResultQueryForm checkResultQueryForm) {
                    log.error("CheckResultService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CheckResultQueryForm checkResultQueryForm) {
                    log.error("CheckResultService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<CheckRelationComputeVo>> getCheckRelationComputeList(@Valid @RequestBody CheckRelationComputeQueryForm checkRelationComputeQueryForm) {
                    log.error("CheckResultService getCheckRelationComputeList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result computingResult(@RequestBody List<CheckResultComputeForm>  checkResultComputeFormList) {
                    log.error("CheckResultService computingResult服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result computeCheckLevel(@RequestBody List<CheckComputeCheckLevelFrom> checkComputeCheckLevelFromList) {
                    log.error("CheckResultService computeCheckLevel服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result computeCheckRank(@Valid @RequestBody CheckComputeCheckRankFrom checkComputeCheckRankFrom) {
                    log.error("CheckResultService computeCheckRank服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<CheckResultStatisticsVo>> getCheckResultStatistics(@Valid @RequestBody CheckResultInfoQueryForm checkResultInfoQueryForm) {
                    log.error("CheckResultService getCheckResultStatistics服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result publishResult(@Valid @RequestBody CheckCommonModifyForm checkCommonModifyForm) {
                    log.error("CheckResultService publishResult服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<CheckResultInfoVo>> getCheckResultInfoVo(@Valid @RequestBody CheckResultInfoQueryForm queryForm) {
                    log.error("CheckResultService getCheckResultInfoVo服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result updatePartInfo(@Valid @RequestBody CheckResultUpdateForm checkResultUpdateForm) {
                    log.error("CheckResultService updatePartInfo服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public void exportResultScore(@Valid @RequestBody  CheckResultInfoQueryForm checkResultInfoQueryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("CheckResultService checkResultInfoQueryForm......");
                    throwable.printStackTrace();
                }

                @Override
                public void exportResultLevel(@Valid @RequestBody  CheckResultInfoQueryForm checkResultInfoQueryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("CheckResultService checkResultInfoQueryForm服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public void exportCheckChat(@Valid @RequestBody CheckResultInfoQueryForm checkResultInfoQueryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("CheckResultService exportCheckChat服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public void exportCheckResultStatistics(@Valid @RequestBody CheckResultInfoQueryForm checkResultInfoQueryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("CheckResultService exportCheckResultStatistics服务不可用......");
                    throwable.printStackTrace();
                }

                @Override
                public void exportCheckResultHistory(@Valid @RequestBody CheckResultInfoQueryForm checkResultInfoQueryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("CheckResultService exportCheckResultHistory服务不可用......");
                    throwable.printStackTrace();
                }
            };
        }
    }
}