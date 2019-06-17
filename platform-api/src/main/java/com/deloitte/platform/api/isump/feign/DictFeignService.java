package com.deloitte.platform.api.isump.feign;


import com.deloitte.platform.api.isump.DictClient;
import com.deloitte.platform.api.isump.param.DictQueryForm;
import com.deloitte.platform.api.isump.vo.DictForm;
import com.deloitte.platform.api.isump.vo.DictVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.GdcPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   Dict feign客户端
 * @Modified :
 */
@FeignClient(name = "service-isump", fallback = DictFeignService.HystrixDictService.class)
public interface DictFeignService extends DictClient {

    @Component
    @Slf4j
    class HystrixDictService implements DictFeignService {

        @Override
        public Result add(@Valid @RequestBody DictForm dictForm){
            log.error("DictService add服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DictService的add方法不可用");
        }

        @Override
        public Result delete(@PathVariable long id){
            log.error("DictService delete服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DictService的delete方法不可用");
        }

        @Override
        public Result update(@PathVariable long id, @Valid @RequestBody DictForm dictForm){
            log.error("DictService update服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DictService的update方法不可用");
        }

        @Override
        public Result get(@PathVariable long id){
            log.error("DictService get服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DictService的get方法不可用");
        }

        @Override
        public Result getDictMap(@PathVariable String code){
            log.error("DictService getDictMap服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DictService的getDictMap方法不可用");
        }

        @Override
        public Result list(@Valid @RequestBody  DictQueryForm dictQueryForm){
            log.error("DictService list服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DictService的list方法不可用");
        }

        @Override
        public Result search(@Valid @RequestBody DictQueryForm dictQueryForm){
            log.error("DictService search服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DictService的search方法不可用");
        }

        @Override
        public Result<GdcPage<DictVo>> searchForFeign(DictQueryForm dictQueryForm) {
            log.error("DictService searchForFeign服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"DictService的searchForFeign方法不可用");
        }
    }
}