package com.deloitte.platform.api.oaservice.feign;


import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.client.OaScheduleTableClient;
import com.deloitte.platform.api.oaservice.param.OaScheduleTableQueryForm;
import com.deloitte.platform.api.oaservice.param.OaScheduleTableQueryParam;
import com.deloitte.platform.api.oaservice.param.OaWorkflowParamForm;
import com.deloitte.platform.api.oaservice.vo.OaScheduleTableForm;
import com.deloitte.platform.api.oaservice.vo.OaScheduleTableLeadForm;
import com.deloitte.platform.api.oaservice.vo.OaScheduleTableLeadVO;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-03-26
 * @Description :   OaScheduleTable feign客户端
 * @Modified :
 */
@FeignClient(name = "service-oa", fallbackFactory = OaScheduleTableFeignService.HystrixOaScheduleTableService.class,primary = false)
public interface OaScheduleTableFeignService extends OaScheduleTableClient {

    @Component
    @Slf4j
    class HystrixOaScheduleTableService implements FallbackFactory<OaScheduleTableFeignService> {

        @Override
        public OaScheduleTableFeignService create(Throwable throwable) {
            return new OaScheduleTableFeignService() {
                @Override
                public Result addFormOtherSys(@Valid @RequestBody OaScheduleTableForm oaScheduleTableForm) {
                    log.error("OaScheduleTableService FormOtherSys服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result add(@Valid @RequestBody OaScheduleTableForm oaScheduleTableForm) {
                    log.error("OaScheduleTableService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addLeaderSchedule(OaScheduleTableLeadForm[] oaScheduleTableLeadForms) {
                    log.error("OaScheduleTableService addLeaderSchedule服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public
                Result updateMonthSchedule(@Valid @RequestBody @ApiParam(name="oaScheduleTableForms",value="更新月视图OaScheduleTable的form表单",required=true) OaScheduleTableForm[] oaScheduleTableForms){
                    log.error("OaScheduleTableService updateMonthSchedule服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable String id) {
                    log.error("OaScheduleTableService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteRow(@PathVariable String businessId, @PathVariable int rowNum){
                    log.error("OaScheduleTableService deleteRow服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable String id, @Valid @RequestBody OaScheduleTableForm oaScheduleTableForm) {
                    log.error("OaScheduleTableService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable String id) {
                    log.error("OaScheduleTableService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }


                @Override
                public Result getScheduleByBusinessId(@PathVariable String businessId,@RequestBody OaScheduleTableQueryParam param) {
                    log.error("OaScheduleTableService getScheduleByBusinessId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<OaScheduleTableLeadVO>> getWeekViewScheduleByBusinessId(String businessId,@RequestBody OaScheduleTableQueryParam param) {
                    log.error("OaScheduleTableService getLeaderScheduleByBusinessId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaScheduleTableQueryForm oaScheduleTableQueryForm) {
                    log.error("OaScheduleTableService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaScheduleTableQueryForm oaScheduleTableQueryForm) {
                    log.error("OaScheduleTableService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
               public Result submit(@Valid @RequestBody OaWorkflowParamForm taskForm){
                    log.error("OaScheduleTableService submit服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}