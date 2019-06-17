package com.deloitte.platform.api.isump.feign;


import com.deloitte.platform.api.isump.ResourceClient;
import com.deloitte.platform.api.isump.param.ResourceQueryForm;
import com.deloitte.platform.api.isump.vo.ResourceForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   Resource feign客户端
 * @Modified :
 */
@FeignClient(name = "service-isump", fallback = ResourceFeignService.HystrixResourceService.class)
public interface ResourceFeignService extends ResourceClient {

    @Component
    @Slf4j
    class HystrixResourceService implements ResourceFeignService {

        @Override
        public Result add(@Valid @RequestBody ResourceForm resourceForm){
            log.error("ResourceService add服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"ResourceService的add方法不可用");
        }

        @Override
        public Result delete(@PathVariable long id){
            log.error("ResourceService delete服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"ResourceService的delete方法不可用");
        }

        @Override
        public Result update(@PathVariable long id, @Valid @RequestBody ResourceForm resourceForm){
            log.error("ResourceService update服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"ResourceService的update方法不可用");
        }

        @Override
        public Result get(@PathVariable long id){
            log.error("ResourceService get服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"ResourceService的get方法不可用");
        }

        @Override
        public Result list(@Valid @RequestBody  ResourceQueryForm resourceQueryForm){
            log.error("ResourceService list服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"ResourceService的list方法不可用");
        }

        @Override
        public Result search(@Valid @RequestBody ResourceQueryForm resourceQueryForm){
            log.error("ResourceService search服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"ResourceService的search方法不可用");
        }

        @Override
        public Result tree(@RequestParam("deputyAccountId") Long deputyAccountId,@RequestParam("sysCode") String sysCode) {
            log.error("ResourceService tree服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"ResourceService的tree方法不可用");
        }
    }
}