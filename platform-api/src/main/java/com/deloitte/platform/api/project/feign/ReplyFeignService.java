package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.isump.vo.DeptForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserForm;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.project.client.ReplyClient;
import com.deloitte.platform.api.project.param.ReplyQueryForm;
import com.deloitte.platform.api.project.vo.ProjectReplyVo;
import com.deloitte.platform.api.project.vo.ReplyForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-22
 * @Description :   Reply feign客户端
 * @Modified :
 */
@FeignClient(name = "reply-service", fallbackFactory = ReplyFeignService.HystrixReplyService.class,primary = false)
public interface ReplyFeignService extends ReplyClient {

    @Component
    @Slf4j
    class HystrixReplyService implements FallbackFactory<ReplyFeignService> {

        @Override
        public ReplyFeignService create(Throwable throwable) {
            return new ReplyFeignService() {
                @Override
                public Result add(@Valid @RequestBody ReplyForm replyForm) {
                    log.error("ReplyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ReplyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ReplyForm replyForm) {
                    log.error("ReplyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ReplyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ReplyQueryForm replyQueryForm) {
                    log.error("ReplyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ReplyQueryForm replyQueryForm) {
                    log.error("ReplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result exportPDF(String urlPath, HttpServletRequest request, HttpServletResponse response, UserForm userForm, DeptForm deptForm) {
                    log.error("ReplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result export(Long applicationId, HttpServletRequest request, HttpServletResponse response, UserForm userForm, DeptForm deptForm) {
                    log.error("ReplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<ProjectReplyVo> getProRep(String applicationId) {
                    log.error("ReplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<ProjectReplyVo> toSave(ProjectReplyVo projectReplyVo) {
                    log.error("ReplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<ProjectReplyVo> toSubmit(ProjectReplyVo projectReplyVo) {
                    log.error("ReplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<ProjectReplyVo> toFindReply(String replyId) {
                    log.error("ReplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result toRemove(String replyId) {
                    log.error("ReplyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}