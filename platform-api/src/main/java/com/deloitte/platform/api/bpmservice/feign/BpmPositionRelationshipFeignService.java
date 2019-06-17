package com.deloitte.platform.api.bpmservice.feign;


import com.deloitte.platform.api.bpmservice.clinet.BpmPositionRelationshipClient;
import com.deloitte.platform.api.bpmservice.param.BpmPositionRelationshipQueryForm;
import com.deloitte.platform.api.bpmservice.vo.BpmPositionRelationshipForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : jackliu
 * @Date : Create in 2019-02-18
 * @Description :   BpmPositionRelationship feign客户端
 * @Modified :
 */
@FeignClient(name = "bpm-service", fallback = BpmPositionRelationshipFeignService.HystrixBpmPositionRelationshipService.class)
public interface BpmPositionRelationshipFeignService extends BpmPositionRelationshipClient {

    @Component
    @Slf4j
    class HystrixBpmPositionRelationshipService implements BpmPositionRelationshipFeignService {

        @Override
        public Result add(@Valid @RequestBody BpmPositionRelationshipForm bpmPositionRelationshipForm){
            log.error("BpmPositionRelationshipService add服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BpmPositionRelationshipService的add方法不可用");
        }

        @Override
        public Result delete(@PathVariable long id){
            log.error("BpmPositionRelationshipService delete服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BpmPositionRelationshipService的delete方法不可用");
        }

        @Override
        public Result update(@PathVariable long id, @Valid @RequestBody BpmPositionRelationshipForm bpmPositionRelationshipForm){
            log.error("BpmPositionRelationshipService update服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BpmPositionRelationshipService的update方法不可用");
        }

        @Override
        public Result get(@PathVariable long id){
            log.error("BpmPositionRelationshipService get服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BpmPositionRelationshipService的get方法不可用");
        }

        @Override
        public Result list(@Valid @RequestBody  BpmPositionRelationshipQueryForm bpmPositionRelationshipQueryForm){
            log.error("BpmPositionRelationshipService list服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BpmPositionRelationshipService的list方法不可用");
        }

        @Override
        public Result search(@Valid @RequestBody BpmPositionRelationshipQueryForm bpmPositionRelationshipQueryForm){
            log.error("BpmPositionRelationshipService search服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"BpmPositionRelationshipService的search方法不可用");
        }
    }
}