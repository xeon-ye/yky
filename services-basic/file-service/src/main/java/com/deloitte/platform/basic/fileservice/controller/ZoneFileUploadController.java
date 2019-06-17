package com.deloitte.platform.basic.fileservice.controller;

import com.deloitte.platform.common.core.common.Const;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.basic.fileservice.service.impl.UploadZoneFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: UploadFileController
 * @Description:
 * @Auther: wangyanyun
 * @Date: 2019-01-18 13:29
 */
@Api("分块文件操作接口")
@RestController
@RequestMapping("/fileservice/ZonefileOperator")
public class ZoneFileUploadController {

    @Autowired
    private UploadZoneFileService uploadFileService;


    @PostMapping("/register")
    @ApiOperation("文件上传注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileMd5",value = "文件MD5",required=true,paramType="query",dataType="String"),
            @ApiImplicitParam(name="fileName",value = "文件名",required=true,paramType="query",dataType="String"),
            @ApiImplicitParam(name="fileSize",value = "文件大小",required=true,paramType="query",dataType="Long"),
            @ApiImplicitParam(name="mimetype",value = "文件mimetype",required=true,paramType="query",dataType="String"),
            @ApiImplicitParam(name="fileExt",value = "文件扩展名",required=true,paramType="query",dataType="String")
    })
    public Result register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {
         uploadFileService.register(fileMd5,fileName,fileSize,mimetype,fileExt);
         return Result.success();
    }

    @PostMapping("/checkchunk")
    @ApiOperation("校验分块文件是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileMd5",value = "文件MD5",required=true,paramType="query",dataType="String"),
            @ApiImplicitParam(name="chunk",value = "块下标",required=true,paramType="query",dataType="Integer"),
            @ApiImplicitParam(name="chunkSize",value = "分块大小",required=true,paramType="query",dataType="Integer"),
    })
    public Result checkchunk(String fileMd5, Integer chunk, Integer chunkSize) {
         boolean exist= uploadFileService.checkchunk(fileMd5,chunk,chunkSize);
         if(exist){
             return Result.success(Const.FILECONST.BLOCK_FILE_EXIST);
         }
         return Result.success(Const.FILECONST.BLOCK_FILE_NOT_EXIST);
    }

    @PostMapping("/uploadchunk")
    @ApiOperation("上传分块")
    @ApiImplicitParams({
            @ApiImplicitParam(name="file",value = "文件",required=true,paramType="query",dataType="MultipartFile"),
            @ApiImplicitParam(name="fileMd5",value = "文件MD5",required=true,paramType="query",dataType="String"),
            @ApiImplicitParam(name="chunk",value = "块下标",required=true,paramType="query",dataType="Integer"),
    })
    public Result uploadchunk(MultipartFile file, String fileMd5, Integer chunk) {
         uploadFileService.uploadchunk(file,fileMd5,chunk);
         return Result.success();
    }

    @PostMapping("/mergechunks")
    @ApiOperation("合并分块")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileMd5",value = "文件MD5",required=true,paramType="query",dataType="String"),
            @ApiImplicitParam(name="fileName",value = "文件名",required=true,paramType="query",dataType="String"),
            @ApiImplicitParam(name="fileSize",value = "文件大小",required=true,paramType="query",dataType="Long"),
            @ApiImplicitParam(name="mimetype",value = "文件mimetype",required=true,paramType="query",dataType="String"),
            @ApiImplicitParam(name="fileExt",value = "文件扩展名",required=true,paramType="query",dataType="String")
    })
    public Result mergechunks(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {
         uploadFileService.mergechunks(fileMd5,fileName,fileSize,mimetype,fileExt);
         return Result.success();
    }
}
