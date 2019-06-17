package com.deloitte.platform.api.isump.feign;


import com.deloitte.platform.api.isump.DeptClient;
import com.deloitte.platform.api.isump.param.DeptQueryForm;
import com.deloitte.platform.api.isump.vo.DeptForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.GdcPage;
import java.util.HashMap;
import java.util.List;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-01
 * @Description :   Dept feign客户端
 * @Modified :
 */
@FeignClient(name = "service-isump",fallbackFactory =DeptFeignService.HystrixDeptService.class)
public interface DeptFeignService extends DeptClient {

    @Component
    @Slf4j
    class HystrixDeptService implements FallbackFactory<DeptFeignService> {
        @Override
        public DeptFeignService create(Throwable throwable) {
            return new DeptFeignService() {

                @Override
                public Result add(@Valid @RequestBody DeptForm deptForm) {
                    log.error("DeptService add服务不可用......");
                    return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE, "DeptService的add方法不可用");
                }

                @Override
                public Result deptEnabled(@PathVariable long id) {
                    log.error("DeptService deptEnabled服务不可用......");
                    return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE, "DeptService的deptEnabled方法不可用");
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("DeptService delete服务不可用......");
                    return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE, "DeptService的delete方法不可用");
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody DeptForm deptForm) {
                    log.error("DeptService update服务不可用......");
                    return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE, "DeptService的update方法不可用");
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("DeptService get服务不可用......");
                    return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE, "DeptService的get方法不可用");
                }

                @Override
                public Result getByCode(@PathVariable(value = "code") String code) {
                    log.error("DeptService byCode服务不可用......");
                    return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE, "DictService的get方法不可用");
                }

                @Override
                public Result getDeptByName(@PathVariable(value = "name") String code) {
                    log.error("DeptService byName服务不可用......");
                    return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE, "DictService的getDeptByName方法不可用");
                }

                @Override
                public Result list(@Valid @RequestBody DeptQueryForm deptQueryForm) {
                    log.error("DeptService list服务不可用......");
                    return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE, "DeptService的list方法不可用");
                }

                @Override
                public Result search(@Valid @RequestBody DeptQueryForm deptQueryForm) {
                    log.error("DeptService search服务不可用......");
                    return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE, "DeptService的search方法不可用");
                }

                @Override
                public Result<GdcPage<DeptVo>> search2(DeptQueryForm deptQueryForm) {
                    log.error("DeptService search2服务不可用......");
                    return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE, "DeptService的search2方法不可用");
                }

                @Override
                public Result<HashMap<String, String>> searchNameByCodes(List<String> codeList) {
                    log.error("DeptService searchNameByCodes服务不可用......");
                    return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE, "DeptService的searchNameByCodes方法不可用");
                }

                @Override
                public Result<HashMap<String, String>> searchCodeAndName(@Valid DeptQueryForm deptQueryForm) {
                    log.error("DeptService searchCodeAndName服务不可用......");
                    return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE, "DeptService的searchCodeAndName方法不可用");
                }
            };
        }
    }
}