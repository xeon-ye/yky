package com.deloitte.platform.api.hr.employee_mes.feign;


import com.deloitte.platform.api.hr.employee_mes.client.EmployeeMesSkillClient;
import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesSkillQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesSkillForm;
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
 * @Date : Create in 2019-05-14
 * @Description :   EmployeeMesSkill feign客户端
 * @Modified :
 */
@FeignClient(name = "employeeMesSkill-service", fallbackFactory = EmployeeMesSkillFeignService.HystrixEmployeeMesSkillService.class,primary = false)
public interface EmployeeMesSkillFeignService extends EmployeeMesSkillClient {

    @Component
    @Slf4j
    class HystrixEmployeeMesSkillService implements FallbackFactory<EmployeeMesSkillFeignService> {

        @Override
        public EmployeeMesSkillFeignService create(Throwable throwable) {
            return new EmployeeMesSkillFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeMesSkillForm employeeMesSkillForm) {
                    log.error("EmployeeMesSkillService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeMesSkillService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeMesSkillForm employeeMesSkillForm) {
                    log.error("EmployeeMesSkillService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeMesSkillService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeMesSkillQueryForm employeeMesSkillQueryForm) {
                    log.error("EmployeeMesSkillService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeMesSkillQueryForm employeeMesSkillQueryForm) {
                    log.error("EmployeeMesSkillService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}