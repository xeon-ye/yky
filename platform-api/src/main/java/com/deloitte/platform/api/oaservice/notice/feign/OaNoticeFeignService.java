package com.deloitte.platform.api.oaservice.notice.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.notice.client.OaNoticeClient;
import com.deloitte.platform.api.oaservice.notice.param.OaNoticeQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeBpmProcessTaskForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-15
 * @Description :   OaNotice feign客户端
 * @Modified :
 */
@FeignClient(name = "oaNotice-service", fallbackFactory = OaNoticeFeignService.HystrixOaNoticeService.class,primary = false)
public interface OaNoticeFeignService extends OaNoticeClient {

    @Component
    @Slf4j
    class HystrixOaNoticeService implements FallbackFactory<OaNoticeFeignService> {

        @Override
        public OaNoticeFeignService create(Throwable throwable) {
            return new OaNoticeFeignService() {
                @Override
                public Result add(@Valid @RequestBody OaNoticeForm oaNoticeForm) {
                    log.error("OaNoticeService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getNextNodeParamVos(Map<String, Object> processVariables) {
                    log.error("OaNoticeService getNextNodeParamVos服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result submitStart(OaNoticeForm oaNoticeForm, String token) {
                    log.error("OaNoticeService submit application服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result submit(OaNoticeBpmProcessTaskForm bpmProcessTaskForm) {
                    log.error("OaNoticeService submit application服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result reSubmit(OaNoticeBpmProcessTaskForm bpmProcessTaskForm) {
                    log.error("OaNoticeService resubmit application服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result reply(OaNoticeBpmProcessTaskForm bpmProcessTaskForm) {
                    log.error("OaNoticeService submit reply application服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("OaNoticeService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody OaNoticeForm oaNoticeForm) {
                    log.error("OaNoticeService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("OaNoticeService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody OaNoticeQueryForm oaNoticeQueryForm) {
                    log.error("OaNoticeService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody OaNoticeQueryForm oaNoticeQueryForm, String token) {
                    log.error("OaNoticeService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<OaNoticeVo>> homeList(Integer num, String noticeTypeCode, String token) {
                    log.error("OaNoticeService homelist服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<OaNoticeVo>> listWithPermission(OaNoticeQueryForm oaNoticeQueryForm, String token) {
                    log.error("OaNoticeService list permission 服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}