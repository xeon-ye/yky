package com.deloitte.services.oaservice.controller;

import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.attachment.entity.OaAttachment;
import com.deloitte.services.attachment.service.IOaAttachmentService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLEncoder;

@Api(tags = "Oa文件操作接口操作接口")
@Slf4j
@RestController
public class OaFileOperatorController {

    public static final String path="/fileOperator";

    @Autowired
    private IOaAttachmentService oaAttachmentService;

    @GetMapping(value = path+"/downloadFile/{id}")
    public Result downloadFile(@PathVariable(value="id") long fileId, HttpServletResponse response) {
        Result re = new Result();
        OaAttachment oaAttachment = oaAttachmentService.getById(fileId);
        OutputStream outputStream = null;
        InputStream inputStream = null;
        if(null != oaAttachment && null != oaAttachment.getAttachUrl()) {
            try {
                String url = oaAttachment.getAttachUrl();
                HttpClient httpClient = HttpClientBuilder.create().build();
                HttpGet httpGet = new HttpGet();
                httpGet.setURI(new URI(url));
                HttpEntity entity = httpClient.execute(httpGet).getEntity();
                inputStream = entity.getContent();
                String fileName = oaAttachment.getAttachName();
                if(fileName.lastIndexOf(".") < 0 ) {
                    try {
                        fileName = fileName + url.substring(url.lastIndexOf("."));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                response.setContentType("application/octet-stream;charset=UTF-8");
                response.addHeader("Content-Disposition","attachment;fileName=" + URLEncoder.encode(fileName, "utf-8"));
                response.addHeader("Content-Length", "" + entity.getContentLength());
                outputStream = response.getOutputStream();
                byte[] buf = new byte[2048];
                int len;
                while ((len = inputStream.read(buf)) > -1){
                    outputStream.write(buf, 0, len);
                }
                outputStream.flush();
                outputStream.close();
                //IOUtils.copy(inputStream, outputStream);
                response.flushBuffer();
                re = Result.success();
            } catch (Exception e){
                re = Result.fail(e.getMessage());
            } finally {
                if(null != outputStream) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(null != inputStream) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            re = Result.fail("未找到文件");
        }

        return re;
    }

}
