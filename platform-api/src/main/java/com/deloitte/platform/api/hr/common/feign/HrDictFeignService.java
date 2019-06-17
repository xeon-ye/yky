package com.deloitte.platform.api.hr.common.feign;


import com.deloitte.platform.api.hr.common.client.DictClient;
import com.deloitte.platform.api.hr.common.param.DictQueryForm;
import com.deloitte.platform.api.hr.common.vo.DictForm;
import com.deloitte.platform.api.hr.common.vo.DictVo;
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
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   Dict feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = HrDictFeignService.HystrixHrDictService.class,primary = false)
public interface HrDictFeignService extends DictClient {

    @Component("hrHystrixDictService")
    @Slf4j
    class HystrixHrDictService implements FallbackFactory<HrDictFeignService> {

        @Override
        public HrDictFeignService create(Throwable throwable) {
            return new HrDictFeignService() {
                @Override
                public Result add(@Valid @RequestBody DictForm dictForm) {
                    log.error("DictService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("DictService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody DictForm dictForm) {
                    log.error("DictService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("DictService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody DictQueryForm dictQueryForm) {
                    log.error("DictService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<DictVo>> getList(DictQueryForm dictQueryForm) {
                    return null;
                }

                @Override
                public Result search(@Valid @RequestBody DictQueryForm dictQueryForm) {
                    log.error("DictService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<DictVo>> getByParentCode(String parentCode) {
                    log.error("DictService getByParentCode服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<DictVo>> getListById(String  id) {
                    log.error("DictService getListById服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<DictVo>> getChildByParentCode(String parentCode) {
                    log.error("DictService getChildByParentCode服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}