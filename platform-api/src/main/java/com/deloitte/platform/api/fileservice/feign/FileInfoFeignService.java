package com.deloitte.platform.api.fileservice.feign;


import com.deloitte.platform.api.fileservice.FileInfoClient;
import com.deloitte.platform.api.fileservice.param.FileInfoQueryForm;
import com.deloitte.platform.api.fileservice.vo.FileInfoForm;
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
 * @Date : Create in 2019-02-19
 * @Description :   FileInfo feign客户端
 * @Modified :
 */
@FeignClient(name = "fileInfo-service", fallback = FileInfoFeignService.HystrixFileInfoService.class)
public interface FileInfoFeignService extends FileInfoClient {

    @Component
    @Slf4j
    class HystrixFileInfoService implements FileInfoFeignService {

        @Override
        public Result add(@Valid @RequestBody FileInfoForm fileInfoForm){
            log.error("FileInfoService add服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"FileInfoService的add方法不可用");
        }

        @Override
        public Result delete(@PathVariable long id){
            log.error("FileInfoService delete服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"FileInfoService的delete方法不可用");
        }

        @Override
        public Result update(@PathVariable long id, @Valid @RequestBody FileInfoForm fileInfoForm){
            log.error("FileInfoService update服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"FileInfoService的update方法不可用");
        }

        @Override
        public Result get(@PathVariable long id){
            log.error("FileInfoService get服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"FileInfoService的get方法不可用");
        }

        @Override
        public Result list(@Valid @RequestBody  FileInfoQueryForm fileInfoQueryForm){
            log.error("FileInfoService list服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"FileInfoService的list方法不可用");
        }

        @Override
        public Result search(@Valid @RequestBody FileInfoQueryForm fileInfoQueryForm){
            log.error("FileInfoService search服务不可用......");
            return new Result(PlatformErrorType.GATEWAY_SERVICE_UNABLE,"FileInfoService的search方法不可用");
        }
    }
}