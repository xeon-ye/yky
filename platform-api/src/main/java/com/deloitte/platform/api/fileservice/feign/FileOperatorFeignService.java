package com.deloitte.platform.api.fileservice.feign;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.fileservice.FileOperatorClient;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.fileservice.vo.ResultImageFile;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.web.feign.FeignMultipartSupportConfig;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * @Author : jackliu
 * @Date : Create in 2019-02-15
 * @Description :   FilesInfo feign客户端
 * @Modified :
 */
@FeignClient(name = "file-service", fallbackFactory = FileOperatorFeignService.HystrixOperatorService.class,primary = false ,configuration=FeignMultipartSupportConfig.class)
public interface FileOperatorFeignService extends FileOperatorClient {

    @Component
    @Slf4j
     class HystrixOperatorService implements FallbackFactory<FileOperatorFeignService> {
        @Override
        public FileOperatorFeignService create(Throwable throwable) {
            return new FileOperatorFeignService(){
                @Override
                public Result<FileInfoVo> uploadFile(MultipartFile file) {
                    log.error("FileOperatorFeignService uploadFile服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public ResultImageFile uploadImage(String action, MultipartFile file) {
                    log.error("FileOperatorFeignService uploadImage服务不可用......");
                    throwable.printStackTrace();
                    ResultImageFile r = new ResultImageFile();
                    r.setState("FileOperatorFeignService uploadImage服务不可用......");
                    return r;
                }

                @Override
                public void uploadImageConfig(String action, String callback) {
                    log.error("FileOperatorFeignService uploadImage服务不可用......");
                    throwable.printStackTrace();
                    ResultImageFile r = new ResultImageFile();
                    r.setState("FileOperatorFeignService uploadImage服务不可用......");
//                    return Result.fail(r);
                }

                @Override
                public Result<List<FileInfoVo>> uploadFiles(@RequestPart("files") MultipartFile[] files){
                    log.error("FileOperatorFeignService uploadFiles服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result downloadFile(long fileId) {
                    log.error("FileOperatorFeignService downloadFile服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable);
                }

                @Override
                public Result deleteFile(long fileId) {
                    log.error("FileOperatorFeignService deleteFile服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable);
                }

            } ;
        }
    }
}