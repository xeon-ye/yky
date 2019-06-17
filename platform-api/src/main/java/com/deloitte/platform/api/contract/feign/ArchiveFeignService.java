package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.ArchiveClient;
import com.deloitte.platform.api.contract.param.ArchiveQueryForm;
import com.deloitte.platform.api.contract.vo.ArchiveForm;
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
 * @Author : yangyq
 * @Date : Create in 2019-06-10
 * @Description :   Archive feign客户端
 * @Modified :
 */
@FeignClient(name = "archive-service", fallbackFactory = ArchiveFeignService.HystrixArchiveService.class,primary = false)
public interface ArchiveFeignService extends ArchiveClient {

    @Component
    @Slf4j
    class HystrixArchiveService implements FallbackFactory<ArchiveFeignService> {

        @Override
        public ArchiveFeignService create(Throwable throwable) {
            return new ArchiveFeignService() {
                @Override
                public Result add(@Valid @RequestBody ArchiveForm archiveForm) {
                    log.error("ArchiveService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ArchiveService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ArchiveForm archiveForm) {
                    log.error("ArchiveService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ArchiveService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ArchiveQueryForm archiveQueryForm) {
                    log.error("ArchiveService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ArchiveQueryForm archiveQueryForm) {
                    log.error("ArchiveService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}