package com.deloitte.platform.api.hr.retireRehiring.feign;


import com.deloitte.platform.api.hr.retireRehiring.client.RehiringApplyClient;
import com.deloitte.platform.api.hr.retireRehiring.param.RehiringApplyQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.param.RehiringQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RecordVo;
import com.deloitte.platform.api.hr.retireRehiring.vo.RehiringApplyForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RetireApplyProcessForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description :   RetireRehiringApply feign客户端
 * @Modified :
 */
@FeignClient(name = "retireRehiringApply-service", fallbackFactory = RehiringApplyFeignService.HystrixRetireRehiringApplyService.class, primary = false)
public interface RehiringApplyFeignService extends RehiringApplyClient {

    @Component
    @Slf4j
    class HystrixRetireRehiringApplyService implements FallbackFactory<RehiringApplyFeignService> {

        @Override
        public RehiringApplyFeignService create(Throwable throwable) {
            return new RehiringApplyFeignService() {
                @Override
                public Result addOrUpdate(@Valid @RequestBody RehiringApplyForm retireRehiringApplyForm) {
                    log.error("RetireRehiringApplyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("RetireRehiringApplyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody RehiringApplyForm retireRehiringApplyForm) {
                    log.error("RetireRehiringApplyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("RetireRehiringApplyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody RehiringApplyQueryForm retireRehiringApplyQueryForm) {
                    log.error("RetireRehiringApplyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody RehiringApplyQueryForm retireRehiringApplyQueryForm) {
                    log.error("RetireRehiringApplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result<List<RecordVo>> getPersonList(RehiringQueryForm queryForm) {
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result approveProcess(@Valid @RequestBody RetireApplyProcessForm retireDelayApplyQueryForm) {
                    log.error("RetireRehiringApplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public void exportPersonList(@Valid @ModelAttribute RehiringQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) {
                    log.error("RetireRehiringApplyService exportPersonList服务不可用......");
                    throwable.printStackTrace();
                }
            };
        }
    }
}