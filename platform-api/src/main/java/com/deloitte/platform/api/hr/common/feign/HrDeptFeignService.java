package com.deloitte.platform.api.hr.common.feign;


import com.deloitte.platform.api.hr.common.client.DeptClient;
import com.deloitte.platform.api.hr.common.param.HrDeptQueryForm;
import com.deloitte.platform.api.hr.common.vo.HrDeptForm;
import com.deloitte.platform.api.hr.common.vo.HrDeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.GdcPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-01
 * @Description :   Dept feign客户端
 * @Modified :
 */
@FeignClient(name = "service-hr", fallback = HrDeptFeignService.HystrixHrDeptService.class)
public interface HrDeptFeignService extends DeptClient {

    @Component
    @Slf4j
    class HystrixHrDeptService implements HrDeptFeignService {

        @Override
        public Result add(@Valid @RequestBody HrDeptForm hrDeptForm){
            log.error("DeptService add服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeptService的add方法不可用");
        }
        @Override
        public Result deptEnabled(@PathVariable long id){
            log.error("DeptService deptEnabled服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeptService的deptEnabled方法不可用");
        }
        @Override
        public Result delete(@PathVariable long id){
            log.error("DeptService delete服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeptService的delete方法不可用");
        }

        @Override
        public Result update(@PathVariable long id, @Valid @RequestBody HrDeptForm hrDeptForm){
            log.error("DeptService update服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeptService的update方法不可用");
        }

        @Override
        public Result get(@PathVariable long id){
            log.error("DeptService get服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeptService的get方法不可用");
        }

        @Override
        public Result getByCode(@PathVariable(value = "code") String code){
            log.error("DictService byCode服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DictService的get方法不可用");
        }

        @Override
        public Result getDeptByName(@PathVariable(value = "name") String code){
            log.error("DictService byName服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DictService的getDeptByName方法不可用");
        }

        @Override
        public Result list(@Valid @RequestBody HrDeptQueryForm hrDeptQueryForm){
            log.error("DeptService list服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeptService的list方法不可用");
        }

        @Override
        public Result search(@Valid @RequestBody HrDeptQueryForm hrDeptQueryForm){
            log.error("DeptService search服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeptService的search方法不可用");
        }

        @Override
        public Result<GdcPage<HrDeptVo>> search2(HrDeptQueryForm hrDeptQueryForm) {
            log.error("DeptService search2服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeptService的search2方法不可用");
        }

        @Override
        public Result<HashMap<String,String>> searchNameByCodes(List<String> codeList) {
            log.error("DeptService searchNameByCodes服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DeptService的searchNameByCodes方法不可用");
        }
    }
}