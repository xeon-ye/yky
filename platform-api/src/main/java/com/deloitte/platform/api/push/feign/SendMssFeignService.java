package com.deloitte.platform.api.push.feign;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.push.client.SendMssClient;
import com.deloitte.platform.api.push.form.SendMssInfoForm;
import com.deloitte.platform.api.push.param.PushMssSendInfoForm;
import com.deloitte.platform.api.push.param.PushMssSendInfoVO;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-03
 * @Description :   SendMssFeignService feign客户端
 * @Modified :
 */
@FeignClient(name = "service-push", fallbackFactory = SendMssFeignService.HystrixOaAssistantMappingService.class,primary = false)
public interface SendMssFeignService extends SendMssClient {

    @Component
    @Slf4j
    class HystrixOaAssistantMappingService implements FallbackFactory<SendMssFeignService> {

        @Override
        public SendMssFeignService create(Throwable throwable) {
            return new SendMssFeignService() {
                @Override
                public  Result send(@RequestParam("phone") String phone, @RequestParam("content") String content){
                    log.error("SendMssFeignService send服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result sendMss(@Valid @RequestBody SendMssInfoForm sendMssInfoForm) {
                    log.error("SendMssFeignService sendMss服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<Long> getPushMssTotal(@RequestParam(name = "date") String date){
                    log.error("SendMssFeignService getMssTotal服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<IPage<PushMssSendInfoVO>> search(@Valid @RequestBody PushMssSendInfoForm queryForm){
                    log.error("SendMssFeignService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result sendMail(@RequestParam("mail") String mail,@RequestParam("title") String title,@RequestParam("content") String content){
                    log.error("SendMssFeignService sendMail服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

            };
        }
    }
}