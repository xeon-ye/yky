package com.deloitte.platform.basic.fileservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.fileservice.FileOperatorClient;
import com.deloitte.platform.api.fileservice.vo.FileInfoForm;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.fileservice.vo.ResultImageFile;
import com.deloitte.platform.basic.fileservice.entity.FileInfo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.basic.fileservice.service.IFileOperatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 22:07 17/02/2019
 * @Description :
 * @Modified :
 */
@Api(tags="文件操作接口")
@Slf4j
@RestController
public class FileOperatorController implements FileOperatorClient {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private IFileOperatorService fileOperatorService;

    @Override
    @ApiOperation("文件上传")
    public Result<FileInfoVo> uploadFile(@RequestPart("file") MultipartFile file) {

        FileInfo filesInfoResult=fileOperatorService.upload(file);
        FileInfoVo filesInfoVo=new BeanUtils<FileInfoVo>().copyObj(filesInfoResult,FileInfoVo.class);
        return new Result<FileInfoVo>().sucess(filesInfoVo);
    }

    @Override
    @ApiOperation("图片上传")
    public ResultImageFile uploadImage(@RequestParam("action")String action, @RequestPart("upfile") MultipartFile file) {
//        if (action.equals("config")){
//            return JSONObject.parseObject("{\"videoMaxSize\":102400000,\"videoActionName\":\"uploadvideo\",\"fileActionName\":\"uploadfile\",\"fileManagerListPath\":\"/ueditor/jsp/upload/file/\",\"imageCompressBorder\":1600,\"imageManagerAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\"],\"imageManagerListPath\":\"/ueditor/jsp/upload/image/\",\"fileMaxSize\":51200000,\"fileManagerAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\",\".flv\",\".swf\",\".mkv\",\".avi\",\".rm\",\".rmvb\",\".mpeg\",\".mpg\",\".ogg\",\".ogv\",\".mov\",\".wmv\",\".mp4\",\".webm\",\".mp3\",\".wav\",\".mid\",\".rar\",\".zip\",\".tar\",\".gz\",\".7z\",\".bz2\",\".cab\",\".iso\",\".doc\",\".docx\",\".xls\",\".xlsx\",\".ppt\",\".pptx\",\".pdf\",\".txt\",\".md\",\".xml\"],\"fileManagerActionName\":\"listfile\",\"snapscreenInsertAlign\":\"none\",\"scrawlActionName\":\"uploadscrawl\",\"videoFieldName\":\"upfile\",\"imageCompressEnable\":true,\"videoUrlPrefix\":\"\",\"fileManagerUrlPrefix\":\"\",\"catcherAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\"],\"imageManagerActionName\":\"listimage\",\"snapscreenPathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"scrawlPathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"scrawlMaxSize\":2048000,\"imageInsertAlign\":\"none\",\"catcherPathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"catcherMaxSize\":2048000,\"snapscreenUrlPrefix\":\"\",\"imagePathFormat\":\"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\"imageManagerUrlPrefix\":\"\",\"scrawlUrlPrefix\":\"\",\"scrawlFieldName\":\"upfile\",\"imageMaxSize\":2048000,\"imageAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\"],\"snapscreenActionName\":\"uploadimage\",\"catcherActionName\":\"catchimage\",\"fileFieldName\":\"upfile\",\"fileUrlPrefix\":\"\",\"imageManagerInsertAlign\":\"none\",\"catcherLocalDomain\":[\"127.0.0.1\",\"localhost\",\"img.baidu.com\"],\"filePathFormat\":\"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\",\"videoPathFormat\":\"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}\",\"fileManagerListSize\":20,\"imageActionName\":\"uploadimage\",\"imageFieldName\":\"upfile\",\"imageUrlPrefix\":\"/web\",\"scrawlInsertAlign\":\"none\",\"fileAllowFiles\":[\".png\",\".jpg\",\".jpeg\",\".gif\",\".bmp\",\".flv\",\".swf\",\".mkv\",\".avi\",\".rm\",\".rmvb\",\".mpeg\",\".mpg\",\".ogg\",\".ogv\",\".mov\",\".wmv\",\".mp4\",\".webm\",\".mp3\",\".wav\",\".mid\",\".rar\",\".zip\",\".tar\",\".gz\",\".7z\",\".bz2\",\".cab\",\".iso\",\".doc\",\".docx\",\".xls\",\".xlsx\",\".ppt\",\".pptx\",\".pdf\",\".txt\",\".md\",\".xml\"],\"catcherUrlPrefix\":\"\",\"imageManagerListSize\":20,\"catcherFieldName\":\"source\",\"videoAllowFiles\":[\".flv\",\".swf\",\".mkv\",\".avi\",\".rm\",\".rmvb\",\".mpeg\",\".mpg\",\".ogg\",\".ogv\",\".mov\",\".wmv\",\".mp4\",\".webm\",\".mp3\",\".wav\",\".mid\"]}").toJSONString();
//        }
        FileInfo filesInfoResult=fileOperatorService.upload(file);
        FileInfoVo filesInfoVo=new BeanUtils<FileInfoVo>().copyObj(filesInfoResult,FileInfoVo.class);
//        response.setContentType("text/html;charset=UTF-8");
        ResultImageFile r = new ResultImageFile();
        r.setUrl(filesInfoVo.getFileUrl());
        r.setOriginal(filesInfoVo.getFileName());
        r.setTitle(filesInfoVo.getFileName());
        r.setState("SUCCESS");
        r.setSize(filesInfoVo.getFileSize());
        r.setType(filesInfoVo.getFileType());
        return r;
    }

    @Override
    @ApiOperation("图片上传")
    public void uploadImageConfig(@RequestParam("action")String action, @RequestParam("callback")String callback) {

        System.out.println("action: " + action);
        String config = "{" +
                "    \"videoMaxSize\": 102400," +
                "    \"videoActionName\": \"uploadvideo\"," +
                "    \"fileActionName\": \"uploadfile\"," +
                "    \"fileManagerListPath\": \"/ueditor/jsp/upload/file/\"," +
                "    \"imageCompressBorder\": 1600," +
                "    \"imageManagerAllowFiles\": [" +
                "        \".png\"," +
                "        \".jpg\"," +
                "        \".jpeg\"," +
                "        \".gif\"," +
                "        \".bmp\"" +
                "    ]," +
                "    \"imageManagerListPath\": \"/ueditor/jsp/upload/image/\"," +
                "    \"fileMaxSize\": 51200," +
                "    \"fileManagerAllowFiles\": [" +
                "        \".png\"," +
                "        \".jpg\"," +
                "        \".jpeg\"," +
                "        \".gif\"," +
                "        \".bmp\"," +
                "        \".flv\"," +
                "        \".swf\"," +
                "        \".mkv\"," +
                "        \".avi\"," +
                "        \".rm\"," +
                "        \".rmvb\"," +
                "        \".mpeg\"," +
                "        \".mpg\"," +
                "        \".ogg\"," +
                "        \".ogv\"," +
                "        \".mov\"," +
                "        \".wmv\"," +
                "        \".mp4\"," +
                "        \".webm\"," +
                "        \".mp3\"," +
                "        \".wav\"," +
                "        \".mid\"," +
                "        \".rar\"," +
                "        \".zip\"," +
                "        \".tar\"," +
                "        \".gz\"," +
                "        \".7z\"," +
                "        \".bz2\"," +
                "        \".cab\"," +
                "        \".iso\"," +
                "        \".doc\"," +
                "        \".docx\"," +
                "        \".xls\"," +
                "        \".xlsx\"," +
                "        \".ppt\"," +
                "        \".pptx\"," +
                "        \".pdf\"," +
                "        \".txt\"," +
                "        \".md\"," +
                "        \".xml\"" +
                "    ]," +
                "    \"fileManagerActionName\": \"listfile\"," +
                "    \"snapscreenInsertAlign\": \"none\"," +
                "    \"scrawlActionName\": \"uploadscrawl\"," +
                "    \"videoFieldName\": \"upfile\"," +
                "    \"imageCompressEnable\": true," +
                "    \"videoUrlPrefix\": \"\"," +
                "    \"fileManagerUrlPrefix\": \"\"," +
                "    \"catcherAllowFiles\": [" +
                "        \".png\"," +
                "        \".jpg\"," +
                "        \".jpeg\"," +
                "        \".gif\"," +
                "        \".bmp\"" +
                "    ]," +
                "    \"imageManagerActionName\": \"listimage\"," +
                "    \"snapscreenPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\"," +
                "    \"scrawlPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\"," +
                "    \"scrawlMaxSize\": 2048000," +
                "    \"imageInsertAlign\": \"none\"," +
                "    \"catcherPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\"," +
                "    \"catcherMaxSize\": 2048000," +
                "    \"snapscreenUrlPrefix\": \"\"," +
                "    \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\"," +
                "    \"imageManagerUrlPrefix\": \"\"," +
                "    \"scrawlUrlPrefix\": \"\"," +
                "    \"scrawlFieldName\": \"upfile\"," +
                "    \"imageMaxSize\": 2048000," +
                "    \"imageAllowFiles\": [" +
                "        \".png\"," +
                "        \".jpg\"," +
                "        \".jpeg\"," +
                "        \".gif\"," +
                "        \".bmp\"" +
                "    ]," +
                "    \"snapscreenActionName\": \"uploadimage\"," +
                "    \"catcherActionName\": \"catchimage\"," +
                "    \"fileFieldName\": \"upfile\"," +
                "    \"fileUrlPrefix\": \"\"," +
                "    \"imageManagerInsertAlign\": \"none\"," +
                "    \"catcherLocalDomain\": [" +
                "        \"127.0.0.1\"," +
                "        \"localhost\"," +
                "        \"img.baidu.com\"" +
                "    ]," +
                "    \"filePathFormat\": \"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\"," +
                "    \"videoPathFormat\": \"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}\"," +
                "    \"fileManagerListSize\": 20," +
                "    \"imageActionName\": \"uploadimage\"," +
                "    \"imageFieldName\": \"upfile\"," +
                "    \"imageUrlPrefix\": \"\"," +
                "    \"scrawlInsertAlign\": \"none\"," +
                "    \"fileAllowFiles\": [" +
                "        \".png\"," +
                "        \".jpg\"," +
                "        \".jpeg\"," +
                "        \".gif\"," +
                "        \".bmp\"," +
                "        \".flv\"," +
                "        \".swf\"," +
                "        \".mkv\"," +
                "        \".avi\"," +
                "        \".rm\"," +
                "        \".rmvb\"," +
                "        \".mpeg\"," +
                "        \".mpg\"," +
                "        \".ogg\"," +
                "        \".ogv\"," +
                "        \".mov\"," +
                "        \".wmv\"," +
                "        \".mp4\"," +
                "        \".webm\"," +
                "        \".mp3\"," +
                "        \".wav\"," +
                "        \".mid\"," +
                "        \".rar\"," +
                "        \".zip\"," +
                "        \".tar\"," +
                "        \".gz\"," +
                "        \".7z\"," +
                "        \".bz2\"," +
                "        \".cab\"," +
                "        \".iso\"," +
                "        \".doc\"," +
                "        \".docx\"," +
                "        \".xls\"," +
                "        \".xlsx\"," +
                "        \".ppt\"," +
                "        \".pptx\"," +
                "        \".pdf\"," +
                "        \".txt\"," +
                "        \".md\"," +
                "        \".xml\"" +
                "    ]," +
                "    \"catcherUrlPrefix\": \"\"," +
                "    \"imageManagerListSize\": 20," +
                "    \"catcherFieldName\": \"source\"," +
                "    \"videoAllowFiles\": [" +
                "        \".flv\"," +
                "        \".swf\"," +
                "        \".mkv\"," +
                "        \".avi\"," +
                "        \".rm\"," +
                "        \".rmvb\"," +
                "        \".mpeg\"," +
                "        \".mpg\"," +
                "        \".ogg\"," +
                "        \".ogv\"," +
                "        \".mov\"," +
                "        \".wmv\"," +
                "        \".mp4\"," +
                "        \".webm\"," +
                "        \".mp3\"," +
                "        \".wav\"," +
                "        \".mid\"" +
                "    ]" +
                "}";
//        JSONObject obj = JSONObject.parseObject(config);
        String result = callback + "("+config+")";
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(result);
        } catch (IOException e) {
            Result.fail();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    @Override
    @ApiOperation("批量文件上传")
    public Result<List<FileInfoVo>> uploadFiles(@RequestPart("files") MultipartFile[] files){
        List<FileInfo> fileInfos=fileOperatorService.uploads(files);
        List<FileInfoVo> fileInfoVos=new BeanUtils<FileInfoVo>().copyObjs(fileInfos,FileInfoVo.class);
        return new Result<List<FileInfoVo>>().sucess(fileInfoVos);
    }



    @Override
    @ApiOperation("文件下载")
    @ApiImplicitParam(name="id",value = "文件ID",required=true,paramType="path",dataType="long")
    public Result downloadFile(@PathVariable(value="id") long fileId) {

        fileOperatorService.downloadFile(fileId,request,response);
        return Result.success();
}

    @Override
    @ApiOperation("文件删除")
    @ApiImplicitParam(name="id",value = "文件ID",required=true,paramType="path",dataType="long")
    public Result deleteFile(@PathVariable(value="id") long fileId){
        fileOperatorService.deleteFile(fileId);
        return Result.success();
    }
}
