package com.deloitte.platform.api.hr.employee.feign;


import com.deloitte.platform.api.hr.employee.client.EmployeeBaseClient;
import com.deloitte.platform.api.hr.employee.param.EmployeeBaseQueryForm;
import com.deloitte.platform.api.hr.employee.vo.EmployeeBaseForm;
import com.deloitte.platform.api.hr.employee.vo.EmployeeBaseVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-03
 * @Description :   EmployeeBase feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = EmployeeBaseFeignService.HystrixEmployeeBaseService.class,primary = false)
public interface EmployeeBaseFeignService extends EmployeeBaseClient {

    @Component
    @Slf4j
    class HystrixEmployeeBaseService implements FallbackFactory<EmployeeBaseFeignService> {

        @Override
        public EmployeeBaseFeignService create(Throwable throwable) {
            return new EmployeeBaseFeignService() {
                @Override
                public Result add(@Valid @RequestBody EmployeeBaseForm employeeBaseForm) {
                    log.error("EmployeeBaseService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("EmployeeBaseService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody EmployeeBaseForm employeeBaseForm) {
                    log.error("EmployeeBaseService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("EmployeeBaseService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody EmployeeBaseQueryForm employeeBaseQueryForm) {
                    log.error("EmployeeBaseService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody EmployeeBaseQueryForm employeeBaseQueryForm) {
                    log.error("EmployeeBaseService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<EmployeeBaseVo>> selectEmployeeListBaseByLikes(EmployeeBaseQueryForm employeeBaseQueryForm) {
                    log.error("EmployeeBaseService selectEmployeeListBaseByLike服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result selectEmployeeListBaseByLike(EmployeeBaseQueryForm employeeBaseQueryForm) {
                    log.error("EmployeeBaseService selectEmployeeListBaseByLike服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<EmployeeBaseVo> getEmployeeByEmpCode(@Valid @RequestBody String code) {
                    log.error("EmployeeBaseService getEmployeeByEmpCode服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<EmployeeBaseVo> selectByAccountId(String id) {
                    log.error("EmployeeBaseService selectByAccountId服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}