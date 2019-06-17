package com.deloitte.platform.api.bpmservice.feign;


import com.deloitte.platform.api.bpmservice.clinet.BpmPositionClient;
import com.deloitte.platform.api.bpmservice.param.BpmPositionQueryForm;
import com.deloitte.platform.api.bpmservice.vo.BpmPositionForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author : jackliu
 * @Date : Create in 2019-02-18
 * @Description :   BpmPosition feign客户端
 * @Modified :
 */
@FeignClient(name = "bpm-service", fallback = BpmPositionFeignService.HystrixBpmPositionService.class)
public interface BpmPositionFeignService extends BpmPositionClient {

    @Component
    @Slf4j
    class HystrixBpmPositionService implements BpmPositionFeignService {

        @Override
        public Result add(@Valid @RequestBody BpmPositionForm bpmPositionForm){
            log.error("BpmPositionService add服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BpmPositionService的add方法不可用");
        }

        @Override
        public Result delete(@PathVariable long id){
            log.error("BpmPositionService delete服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BpmPositionService的delete方法不可用");
        }

        @Override
        public Result update(@PathVariable long id, @Valid @RequestBody BpmPositionForm bpmPositionForm){
            log.error("BpmPositionService update服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BpmPositionService的update方法不可用");
        }

        @Override
        public Result get(@PathVariable long id){
            log.error("BpmPositionService get服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BpmPositionService的get方法不可用");
        }

        @Override
        public Result list(@Valid @RequestBody  BpmPositionQueryForm bpmPositionQueryForm){
            log.error("BpmPositionService list服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BpmPositionService的list方法不可用");
        }

        @Override
        public Result search(@Valid @RequestBody BpmPositionQueryForm bpmPositionQueryForm){
            log.error("BpmPositionService search服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BpmPositionService的search方法不可用");
        }
    }
}