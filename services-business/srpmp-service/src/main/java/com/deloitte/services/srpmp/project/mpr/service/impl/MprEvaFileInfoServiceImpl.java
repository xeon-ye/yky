package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaFileInfoCompressDownForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaFileInfoFieldForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaFileInfoSaveOrUpdateForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaFileInfoVoExt;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.impl.WordExportServiceImpl;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.common.util.ZipUtil;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaFileInfo;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaFileInfoMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaFileInfoService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.io.*;
import java.net.HttpURLConnection;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-01
 * @Description :  MprEvaFileInfo服务实现类
 * @Modified :
 */
@Slf4j
@Service
@Transactional
public class MprEvaFileInfoServiceImpl extends ServiceImpl<MprEvaFileInfoMapper, MprEvaFileInfo> implements IMprEvaFileInfoService {


    @Autowired
    private MprEvaFileInfoMapper mprEvaFileInfoMapper;

    @Autowired
    private ISrpmsProjectService projectService;

    @Autowired
    private WordExportServiceImpl wordExportService;

    @Autowired
    private FileOperatorFeignService fileOperatorFeignService;

    @Override
    public MprEvaFileInfoVoExt getByProjectId(Long projectId) {
        if (null == projectId || projectId == 0L) {
            throw new BaseException(SrpmsErrorType.PROJECT_ID_NULL);
        }
        MprEvaFileInfo fileInfo = mprEvaFileInfoMapper.selectById(projectId);
        MprEvaFileInfoVoExt voExt = new MprEvaFileInfoVoExt();
        if (null == fileInfo) {
            return voExt;
        }
        voExt.setId(fileInfo.getId());
        if (StringUtils.isNotBlank(fileInfo.getAnnexOne())) {
            voExt.setAnnexOne(JSONObject.parseObject(fileInfo.getAnnexOne(), MprEvaFileInfoFieldForm.class));
        }
        if (StringUtils.isNotBlank(fileInfo.getAnnexTwo())) {
            voExt.setAnnexTwo(JSONArray.parseArray(fileInfo.getAnnexTwo(), MprEvaFileInfoFieldForm.class));
        }
        if (StringUtils.isNotBlank(fileInfo.getAnnexThree())) {
            voExt.setAnnexThree(JSONObject.parseObject(fileInfo.getAnnexThree(), MprEvaFileInfoFieldForm.class));
        }
        if (StringUtils.isNotBlank(fileInfo.getAnnexFour())) {
            voExt.setAnnexFour(JSONObject.parseObject(fileInfo.getAnnexFour(), MprEvaFileInfoFieldForm.class));
        }
        if (StringUtils.isNotBlank(fileInfo.getAnnexFive())) {
            voExt.setAnnexFive(JSONObject.parseObject(fileInfo.getAnnexFive(), MprEvaFileInfoFieldForm.class));
        }
        if (StringUtils.isNotBlank(fileInfo.getAnnexSix())) {
            voExt.setAnnexSix(JSONObject.parseObject(fileInfo.getAnnexSix(), MprEvaFileInfoFieldForm.class));
        }
        if (StringUtils.isNotBlank(fileInfo.getAnnexSeven())) {
            voExt.setAnnexSeven(JSONObject.parseObject(fileInfo.getAnnexSeven(), MprEvaFileInfoFieldForm.class));
        }
        if (StringUtils.isNotBlank(fileInfo.getAnnexEight())) {
            voExt.setAnnexEight(JSONObject.parseObject(fileInfo.getAnnexEight(), MprEvaFileInfoFieldForm.class));
        }
        if (StringUtils.isNotBlank(fileInfo.getAnnexTen())) {
            voExt.setAnnexTen(JSONObject.parseObject(fileInfo.getAnnexTen(), MprEvaFileInfoFieldForm.class));
        }
        if (StringUtils.isNotBlank(fileInfo.getAnnexEleven())) {
            voExt.setAnnexEleven(JSONObject.parseObject(fileInfo.getAnnexEleven(), MprEvaFileInfoFieldForm.class));
        }
        if (StringUtils.isNotBlank(fileInfo.getAnnexTwelve())) {
            voExt.setAnnexTwelve(JSONObject.parseObject(fileInfo.getAnnexTwelve(), MprEvaFileInfoFieldForm.class));
        }
        if (StringUtils.isNotBlank(fileInfo.getAnnexOther())) {
            voExt.setAnnexOther(JSONArray.parseArray(fileInfo.getAnnexOther(), MprEvaFileInfoFieldForm.class));
        }
        return voExt;
    }

    @Override
    @Transactional
    public void saveOrUpdateFileInfo(MprEvaFileInfoSaveOrUpdateForm saveOrUpdateForm) {
        if (null == saveOrUpdateForm) {
            throw new BaseException(SrpmsErrorType.PARAM_NULL);
        }
        MprEvaFileInfo fileInfo = this.getById(saveOrUpdateForm.getId());
        if (null == fileInfo) {
            fileInfo = new MprEvaFileInfo();
            fileInfo.setId(saveOrUpdateForm.getId());
        }
        if (null != saveOrUpdateForm.getAnnexTwo()) {
            fileInfo.setAnnexTwo(JSON.toJSONString(saveOrUpdateForm.getAnnexTwo()));
        }
        if (null != saveOrUpdateForm.getAnnexThree()) {
            fileInfo.setAnnexThree(JSON.toJSONString(saveOrUpdateForm.getAnnexThree()));
        }
        if (null != saveOrUpdateForm.getAnnexFour()) {
            fileInfo.setAnnexFour(JSON.toJSONString(saveOrUpdateForm.getAnnexFour()));
        }
        if (null != saveOrUpdateForm.getAnnexFive()) {
            fileInfo.setAnnexFive(JSON.toJSONString(saveOrUpdateForm.getAnnexFive()));
        }
        if (null != saveOrUpdateForm.getAnnexSeven()) {
            fileInfo.setAnnexSeven(JSON.toJSONString(saveOrUpdateForm.getAnnexSeven()));
        }
        if (null != saveOrUpdateForm.getAnnexEight()) {
            fileInfo.setAnnexEight(JSON.toJSONString(saveOrUpdateForm.getAnnexEight()));
        }
        if (null != saveOrUpdateForm.getAnnexTen()) {
            fileInfo.setAnnexTen(JSON.toJSONString(saveOrUpdateForm.getAnnexTen()));
        }
        if (null != saveOrUpdateForm.getAnnexEleven()) {
            fileInfo.setAnnexEleven(JSON.toJSONString(saveOrUpdateForm.getAnnexEleven()));
        }
        if (null != saveOrUpdateForm.getAnnexTwelve()) {
            fileInfo.setAnnexTwelve(JSON.toJSONString(saveOrUpdateForm.getAnnexTwelve()));
        }
        if (null != saveOrUpdateForm.getAnnexOther()) {
            fileInfo.setAnnexOther(JSON.toJSONString(saveOrUpdateForm.getAnnexOther()));
        }
        //保存或者更新
        this.removeById(saveOrUpdateForm.getId());
        fileInfo.setId(saveOrUpdateForm.getId());
        this.save(fileInfo);
    }

    @Override
    public String exportTemplate(Long id, Long projectId, UserVo userVo, DeptVo deptVo) {
        List<Long> annexList = Lists.newArrayList(2L,3L, 4L, 5L, 7L, 8L, 10L, 11L, 12L);
        if (null == id) {
            throw new BaseException(SrpmsErrorType.PARAM_NULL, "id");
        }
        if (!annexList.contains(id)) {
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "不存在此附件");
        }
        DateTimeFormatter formatterLess = DateTimeFormatter.ofPattern("yyyy年MM月");
        Map<String, Object> data = Maps.newHashMap();
        SrpmsProject project = projectService.getById(projectId);
        data.put("project", project);
        data.put("leadDept", JSONObject.parseObject(project.getLeadDept()));
        data.put("expertPerson", JSONObject.parseObject(project.getBothTopExpertPerson()));
        data.put("projectActionDateStart", project.getProjectActionDateStart() == null?"": project.getProjectActionDateStart().format(formatterLess));
        data.put("projectActionDateEnd", project.getProjectActionDateEnd() == null?"": project.getProjectActionDateEnd().format(formatterLess));

        //项目类别
        String projectCategory = project.getProjectType();
        if (StringUtils.isNotBlank(projectCategory)) {
            List<String> projectCategoryList = Lists.newArrayList(projectCategory);
            Map<String, String> dictMap = Maps.newHashMap();
            dictMap.put(ProjectCategoryEnums.INNOVATE_COO.getHeader(), ProjectCategoryEnums.INNOVATE_COO.getDesc());
            dictMap.put(ProjectCategoryEnums.INNOVATE_BCOO.getHeader(), ProjectCategoryEnums.INNOVATE_BCOO.getDesc());
            dictMap.put(ProjectCategoryEnums.INNOVATE_PRE.getHeader(), ProjectCategoryEnums.INNOVATE_PRE.getDesc());
            dictMap.put(ProjectCategoryEnums.INNOVATE_YOU.getHeader(), ProjectCategoryEnums.INNOVATE_YOU.getDesc());
            List<String> projectCategoryNameList = WordConvertUtil.parseCodeListToText(dictMap, projectCategoryList);
            data.put("projectCategory", projectCategoryNameList);
        }


        try {
            String templateName = MapUtils.getString(getTemplateNameFileName(id), "templateName");
            String fileName = MapUtils.getString(getTemplateNameFileName(id), "fileName");
            String fullFileName = fileName + ".docx";
            return wordExportService.exportWord(templateName, data, fullFileName);
        } catch (Exception e) {
            log.error("导出word异常。", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        }
    }

    @Override
    public Map<String, String> getTemplateNameFileName(Long id) {
        Map<String, String> result = Maps.newHashMap();
        String templateName = "";
        String fileName = "";
        if (id == 2) {
            templateName = "template_annex_2";
            fileName = "中国医学科学院医学与健康科技创新工程项目中期绩效考评任务自评报告";
        }
        if (id == 3) {
            templateName = "template_annex_3";
            fileName = "中国医学科学院医学与健康科技创新工程中期绩效考评项目自评（任务评价）专家个人意见表";
        }
        if (id == 4) {
            templateName = "template_annex_4";
            fileName = "中国医学科学院医学与健康科技创新工程项目中期绩效考评项目自评（岗位评价）专家个人意见表";
        }
        if (id == 5) {
            templateName = "template_annex_5";
            fileName = "中国医学科学院医学与健康科技创新工程中期绩效考评项目自评（任务评价）专家组意见表";
        }
        if (id == 7) {
            templateName = "template_annex_7";
            fileName = "中国医学科学院医学与健康科技创新工程中期绩效考评项目自评（任务评价）排序结果";
        }
        if (id == 8) {
            templateName = "template_annex_8";
            fileName = "中国医学科学院医学与健康科技创新工程中期绩效考评项目自评（岗位评价）排序结果";
        }
        if (id == 10) {
            templateName = "template_annex_10";
            fileName = "中国医学科学院医学与健康科技创新工程承担单位中期绩效考评专家评分表";
        }
        if (id == 11) {
            templateName = "template_annex_11";
            fileName = "中国医学科学院医学与健康科技创新工程项目中期绩效考评专家个人意见表";
        }
        if (id == 12) {
            templateName = "template_annex_12";
            fileName = "中国医学科学院医学与健康科技创新工程项目中期绩效考评专家组意见表";
        }
        result.put("templateName", templateName);
        result.put("fileName", fileName);
        return result;
    }

    @Override
    public List<File> compressTemplateDownload(String ids, String projectIds, UserVo userVo, DeptVo deptVo) {
        List<File> files = new ArrayList<>();
        File file = null;
        String filePath = null;

        if (Objects.isNull(ids) && Objects.isNull(projectIds)) {
            throw new BaseException(PlatformErrorType.NULL_POINT_ERROR, "请求参数为空！");
        }
        String[] idStrs = ids.split(",");
        for (int i = 0; i < idStrs.length; i++) {
            filePath = exportTemplate(Long.parseLong(idStrs[i]), Long.parseLong(projectIds), userVo, deptVo);
            file = new File(filePath);
            files.add(file);
        }
        return files;
    }

    @Override
    public String downloadZip(MprEvaFileInfoCompressDownForm mprEvaFileInfoCompressDownForm) {
        if (null == mprEvaFileInfoCompressDownForm || CollectionUtils.isEmpty(mprEvaFileInfoCompressDownForm.getMprEvaFileInfoFieldForms())) {
            throw new BaseException(SrpmsErrorType.PARAM_NULL);
        }

        String returnUrl = "";
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        try {
            File rootPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile();
            File tempDir = new File(rootPath + File.separator + "mprcompress" + File.separator + "tempfile" + File.separator + uuid);
            File zipDir = new File(rootPath + File.separator + "mprcompress" + File.separator + "zipfile");
            if (!tempDir.exists()) {
                tempDir.mkdirs();
            }
            if (!zipDir.exists()) {
                zipDir.mkdirs();
            }
            List<MprEvaFileInfoFieldForm> mprEvaFileInfoFieldFormList = mprEvaFileInfoCompressDownForm.getMprEvaFileInfoFieldForms();
            for (MprEvaFileInfoFieldForm form : mprEvaFileInfoFieldFormList){
                URL url = new URL(form.getFileUrl());
//                String suffix = form.getFileName().substring(form.getFileName().lastIndexOf('.') + 1);
//                String fileName = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + RandomUtils.nextInt(0, 9999) + "." +suffix;
                File file = new File(tempDir.getAbsolutePath() + File.separator + form.getFileName());
                FileUtils.copyURLToFile(url, file);
            }
            String zipPath = zipDir +  File.separator + uuid + ".zip";
            ZipUtil.zip(tempDir.getAbsolutePath(), zipPath);

            FileInfoVo fileInfo = uploadFile(zipPath);
            returnUrl =  fileInfo.getFileUrl();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return returnUrl;
    }

    private FileInfoVo uploadFile(String fileUrl) {
        try {
            File file = new File(fileUrl);
            FileInputStream fileInputStream = new FileInputStream(file);
            MultipartFile annexOneMultipartFile = new MockMultipartFile("file", file.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
            Result<FileInfoVo> result = fileOperatorFeignService.uploadFile(annexOneMultipartFile);
            if (result.isSuccess()){
                file.delete();
            }else {
                log.error("打包附件上传文件服务器失败。rsp:{}", JSONObject.toJSONString(result));
                throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "打包附件上传文件服务器失败");
            }
            return result.getData();
        } catch (IOException e) {
            log.error("上传中期绩效报告到文件服务器异常.", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        }
    }

    @Override
    public List<File> compressEnclosureDownload(MprEvaFileInfoCompressDownForm mprEvaFileInfoCompressDownForm) {
        List<File> files = new ArrayList<>();

        File file = null;
        String rootPath = null;
        File tempPath = null;
        String targetFilePath = null;

        URL url = null;
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;

        if (Objects.isNull(mprEvaFileInfoCompressDownForm)) {
            throw new BaseException(PlatformErrorType.NULL_POINT_ERROR, "请求参数为空");
        }
        try {
            rootPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
            tempPath = new File(rootPath + File.separator + "tempDir");
            if (!tempPath.exists()) {
                tempPath.mkdirs();
            }

            List<MprEvaFileInfoFieldForm> mprEvaFileInfoFieldForms = mprEvaFileInfoCompressDownForm.getMprEvaFileInfoFieldForms();

            for (MprEvaFileInfoFieldForm evaFileInfoFieldForm : mprEvaFileInfoFieldForms) {
                // 拼接本地存放文件字符串路径
                targetFilePath = tempPath.getAbsolutePath()+File.separator+evaFileInfoFieldForm.getFileName();

                url = new URL(evaFileInfoFieldForm.getFileUrl());
                connection = (HttpURLConnection) url.openConnection();

                // 得到输入流
                inputStream = connection.getInputStream();
                // 获取字节数组
                byte[] bytes = this.readInputStream(inputStream);
                // 输出保存本地
                fileOutputStream = new FileOutputStream(targetFilePath);
                fileOutputStream.write(bytes);
                // 通过将保存本地文件的路径名字符串转换为抽象路径名来创建新的文件实例
                file = new File(targetFilePath);
                files.add(file);
                // 释放资源
                inputStream.close();
                fileOutputStream.close();
                connection.disconnect();
            }
        } catch (IOException e) {
            log.error("远程文件下载异常！", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "下载失败！");
        } finally {

        }
        return files;
    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    private byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();

        return bos.toByteArray();
    }


}

