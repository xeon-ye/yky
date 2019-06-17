package com.deloitte.platform.api.oaservice.meeting.feign;


import com.deloitte.platform.api.oaservice.meeting.client.OaMeetingMembersClient;
import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingMembersQueryForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingMembersForm;
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
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :   OaMeetingMembers feign客户端
 * @Modified :
 */
@FeignClient(name = "oaMeetingMembers-service", fallbackFactory = OaMeetingMembersFeignService.HystrixOaMeetingMembersService.class,primary = false)
public interface OaMeetingMembersFeignService extends OaMeetingMembersClient {

    @Component
    @Slf4j
    class HystrixOaMeetingMembersService implements FallbackFactory<OaMeetingMembersFeignService> {

        @Override
        public OaMeetingMembersFeignService create(Throwable throwable) {
            return new OaMeetingMembersFeignService() {
                @Override
                public Result add(@Valid @RequestBody OaMeetingMembersForm oaMeetingMembersForm) {
                    log.error("OaMeetingMembersService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OaMeetingMembersService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OaMeetingMembersForm oaMeetingMembersForm) {
                    log.error("OaMeetingMembersService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OaMeetingMembersService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaMeetingMembersQueryForm oaMeetingMembersQueryForm) {
                    log.error("OaMeetingMembersService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaMeetingMembersQueryForm oaMeetingMembersQueryForm) {
                    log.error("OaMeetingMembersService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}