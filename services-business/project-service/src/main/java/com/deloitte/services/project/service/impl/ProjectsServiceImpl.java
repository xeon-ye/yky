package com.deloitte.services.project.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.project.param.ProjectsQueryForm;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.services.project.common.enums.ProjectErrorType;
import com.deloitte.services.project.common.enums.ValueEnums;
import com.deloitte.services.project.common.enums.VoucherTypeEnums;
import com.deloitte.services.project.common.util.AssertUtils;
import com.deloitte.services.project.common.util.ExportExcelUtils;
import com.deloitte.services.project.entity.Application;
import com.deloitte.services.project.entity.Enclosure;
import com.deloitte.services.project.entity.ProjectLibrary;
import com.deloitte.services.project.entity.Projects;
import com.deloitte.services.project.mapper.ProjectsMapper;
import com.deloitte.services.project.service.*;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-26
 * @Description :  Projects服务实现类
 * @Modified :
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ProjectsServiceImpl extends ServiceImpl<ProjectsMapper, Projects> implements IProjectsService {


    @Autowired
    private ProjectsMapper projectsMapper;
    @Autowired
    private IProjectLibraryService libraryService;
    @Autowired
    private ICommonService commonService;
    @Autowired
    private IApplicationService applicationService;
    @Autowired
    private IEnclosureService enclosureService;
    @Autowired
    private IServiceNumService serviceNumService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveCancelProject(CancelProjectFrom cancelProjectFrom) {
        AssertUtils.asserts(Objects.isNull(cancelProjectFrom), ProjectErrorType.No_FROM);
        if (Objects.nonNull(cancelProjectFrom.getApplicationId())) {
            String projectId = null;
            QueryWrapper<Application> applicationQueryWrapper = new QueryWrapper<>();
            applicationQueryWrapper.eq(Application.APPLICATION_ID, cancelProjectFrom.getApplicationId());
            Application application = applicationService.getOne(applicationQueryWrapper);
            if (Objects.nonNull(application)) {
                QueryWrapper<Projects> projectsQueryWrapper = new QueryWrapper<>();
                projectsQueryWrapper.eq(Projects.PROJECT_ID, application.getProjectId());
                Projects projects = projectsMapper.selectOne(projectsQueryWrapper);
                if (Objects.nonNull(projects)) {
                    projectId = projects.getProjectId();
                    projects.setProCancelDes(cancelProjectFrom.getProCancelDes());
                    projectsMapper.update(projects, projectsQueryWrapper);
                }
            }

            List<FileVo> fileVoList = cancelProjectFrom.getEnclosureFormList();
            List<Enclosure> enclosureList = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(fileVoList)) {
                for (FileVo fileVo : fileVoList) {
                    Enclosure enclosure = new Enclosure();
                    enclosure.setFileId(fileVo.getId());
                    enclosure.setEnclosureType(fileVo.getFileExt());
                    enclosure.setEnclosureName(fileVo.getFileName());
                    enclosure.setEnclosureUrl(fileVo.getFileUrl());
                    enclosure.setProjectId(projectId);
                    enclosureList.add(enclosure);
                }
                if (CollectionUtils.isNotEmpty(enclosureList)) {
                    enclosureService.saveBatch(enclosureList);
                    for (Enclosure enclosure : enclosureList) {
                        enclosure.setEnclosureId(String.valueOf(enclosure.getId()));
                    }
                    enclosureService.saveOrUpdateBatch(enclosureList);
                }
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void submitCancelProject(CancelProjectFrom cancelProjectFrom) {
        AssertUtils.asserts(Objects.isNull(cancelProjectFrom), ProjectErrorType.No_FROM);
        if (Objects.nonNull(cancelProjectFrom.getApplicationId())) {

            String projectId = null;
            QueryWrapper<Application> applicationQueryWrapper = new QueryWrapper<>();
            applicationQueryWrapper.eq(Application.APPLICATION_ID, cancelProjectFrom.getApplicationId());
            Application application = applicationService.getOne(applicationQueryWrapper);
            if (Objects.nonNull(application)) {
                QueryWrapper<Projects> projectsQueryWrapper = new QueryWrapper<>();
                projectsQueryWrapper.eq(Projects.PROJECT_ID, application.getProjectId());
                Projects projects = projectsMapper.selectOne(projectsQueryWrapper);
                if (Objects.nonNull(projects)) {
                    projectId = projects.getProjectId();
                    projects.setProCancelDes(cancelProjectFrom.getProCancelDes());
                    projectsMapper.update(projects, projectsQueryWrapper);
                }
            }

            List<FileVo> fileVoList = cancelProjectFrom.getEnclosureFormList();
            List<Enclosure> enclosureList = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(fileVoList)) {
                for (FileVo fileVo : fileVoList) {
                    Enclosure enclosure = new Enclosure();
                    enclosure.setFileId(fileVo.getId());
                    enclosure.setEnclosureType(fileVo.getFileExt());
                    enclosure.setEnclosureName(fileVo.getFileName());
                    enclosure.setEnclosureUrl(fileVo.getFileUrl());
                    enclosure.setProjectId(projectId);
                    enclosureList.add(enclosure);
                }
                if (CollectionUtils.isNotEmpty(enclosureList)) {
                    enclosureService.saveBatch(enclosureList);
                    for (Enclosure enclosure : enclosureList) {
                        enclosure.setEnclosureId(String.valueOf(enclosure.getId()));
                    }
                    enclosureService.saveOrUpdateBatch(enclosureList);
                }
            }

            Map<String, String> processVariables = new HashMap<>();
            processVariables.put("projectType",cancelProjectFrom.getProjectTypeCode());
            Result result = commonService.startAuditProcess(cancelProjectFrom.getApplicationId(),processVariables, VoucherTypeEnums.PROJECT_APPLY_PROCESS);
            if(result.isSuccess()){

            }
        }
    }

    @Override
    public JSONObject exportExcelForProLib(ProjectLibraryForm queryForm, HttpServletRequest request, HttpServletResponse response) {
        List<ProjectLibrary> libraryList = libraryService.selectProjectLibraryList(queryForm);
        if (CollectionUtils.isNotEmpty(libraryList)) {
            // 根据模板名获取模板文件路径
            String templateFilePath = ExportExcelUtils.getTemplatePath("template_project_library");

            Map<String, Object> dataMap = Maps.newHashMap();
            List<Map<Integer, Object>> datalist = Lists.newArrayList();
            Map<Integer, Object> data = null;
            OutputStream os = null;

            try {
                dataMap.put("B1", "项目名称：" + queryForm.getProjectName());
                ExportExcelUtils.writeData(templateFilePath, dataMap, 0);
                for (int i = 0; i < libraryList.size(); i++) {
                    data = Maps.newHashMap();
                    data.put(1, i + 1);
                    data.put(2, libraryList.get(i).getPlanYear());
                    data.put(3, "预算年度");
                    data.put(4, "一级项目");
                    data.put(5, libraryList.get(i).getOperationOu());
                    data.put(6, libraryList.get(i).getProjectName());
                    data.put(7, libraryList.get(i).getProjectTypeName());
                    data.put(8, libraryList);
                    data.put(9, libraryList.get(i).getSchoolPriority());
                    data.put(10, libraryList.get(i).getSchoolPriority());
                    data.put(11, "财政（申报）");
                    data.put(12, "主管（申报）");
                    data.put(13, "其它（申报）");
                    data.put(14, "财政（审核）");
                    data.put(15, "财政（审核）");
                    data.put(16, "财政（审核）");
                    datalist.add(data);
                }

                String[] heads = new String[] { "B5", "C5", "D5", "E5", "F5", "G5", "H5", "I5", "J5", "K5", "L5", "M5", "N5", "O5", "P5", "Q5" };
                ExportExcelUtils.writeDataList(templateFilePath, heads, datalist, 0);
                // 获取项目根目录
                String rootPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
                File tempDir = new File(rootPath + File.separator + "proExcelTempDir");
                if (!tempDir.exists()) {
                    tempDir.mkdirs();
                }
                String nonceDate = DateFormatUtils.format(new Date(), "yyyyMMdd");
                String targetFileUrl = tempDir + File.separator + "项目库导出_" + nonceDate + ".xlsx";
                os = new FileOutputStream(targetFileUrl);
                //写到输出流并移除资源
                ExportExcelUtils.writeAndClose(templateFilePath, os);
                String sourceFileUrl = tempDir.getAbsolutePath() + File.separator + "项目库导出_" + nonceDate + ".xlsx";
                os.flush();

                //this.exportExcel(sourceFileUrl, response);
                // 上传文件服务器
                FileInfoVo fileVo = commonService.uploadFile(sourceFileUrl);
                JSONObject jsonObject = new JSONObject();
                if (Objects.nonNull(fileVo)) {
                    jsonObject.put("fileId", fileVo.getId());
                    jsonObject.put("fileUrl", fileVo.getFileUrl());
                }
                return jsonObject;
            } catch (Exception e) {
                log.info("项目库数据Excel导出异常！", e);
                throw new ServiceException(ProjectErrorType.FILE_DOWNLOAD_FIAL, e.getMessage(), e.getCause());
            } finally {
                IOUtils.closeQuietly(os);
            }
        }
        return null;
    }

    @Override
    public JSONArray exchangeProjectStatus(String projectIds, String projectTypeCode) {
        if (Objects.nonNull(projectIds) && Objects.nonNull(projectTypeCode)) {
            JSONArray jsonArray = new JSONArray();
            String[] projectIdStr = projectIds.split(",");
            for (String sid : projectIdStr) {
                JSONObject jsonObject = new JSONObject();
                if ("7005".equals(projectTypeCode)) {
                    QueryWrapper<Projects> projectsQueryWrapper = new QueryWrapper<>();
                    projectsQueryWrapper.eq(Projects.ID, NumberUtils.toLong(sid));
                    Projects projects = this.getOne(projectsQueryWrapper);
                    if (Objects.nonNull(projects)) {
                        // 7005 已结项 --> 7004 已立项
                        if (ValueEnums.PROJECT_COMPLETED.getCode().equals(projects.getProjectTypeCode())) {
                            projects.setProjectTypeCode(ValueEnums.PROJECT_ESTABLISHED.getCode());
                            this.update(projects, projectsQueryWrapper);
                            jsonObject.put(projects.getProjectStatusCode(), projects.getProjectStatusName());
                            jsonArray.add(jsonObject);
                        }
                    }
                }
                if ("7004".equals(projectTypeCode)) {
                    QueryWrapper<Projects> projectsQueryWrapper = new QueryWrapper<>();
                    projectsQueryWrapper.eq(Projects.ID, NumberUtils.toLong(sid));
                    Projects projects = this.getOne(projectsQueryWrapper);
                    if (Objects.nonNull(projects)) {
                        // 7004 已立项 --> 7005 已结项
                        if (ValueEnums.PROJECT_ESTABLISHED.getCode().equals(projects.getProjectTypeCode())) {
                            projects.setProjectTypeCode(ValueEnums.PROJECT_ESTABLISHED.getCode());
                            this.update(projects, projectsQueryWrapper);
                            jsonObject.put(projects.getProjectStatusCode(), projects.getProjectStatusName());
                            jsonArray.add(jsonObject);
                        }
                    }
                }

            }
            return jsonArray;
        }
        return null;
    }

    /**
     * 项目库数据Excel导出
     * @param fileUrl
     * @param response
     */
    private void exportExcel(String fileUrl, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");

        byte[] buff = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream out = null;

        try {
            String fileName = "项目库导出_" + DateFormatUtils.format(new Date(), "yyyyMMdd") + ".xlsx";
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            is = new FileInputStream(fileUrl);
            bis = new BufferedInputStream(is);
            out = response.getOutputStream();
            int i = bis.read(buff);
            while (i != -1) {
                out.write(buff, 0, i);
                i = bis.read(buff);
            }
            out.flush();
        } catch (Exception e) {
            log.info("项目库数据Excel导出异常！", e);
            throw new ServiceException(ProjectErrorType.FILE_DOWNLOAD_FIAL, e.getMessage(), e.getCause());
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(out);
            // 删除临时文件
            this.deleteFile(fileUrl);
        }
    }

    /**
     * 删除文件
     * @param fileUrl
     */
    private void deleteFile(String fileUrl) {
        File file = new File(fileUrl);
        if (!file.isDirectory()) {
            if (file.isFile()) {
                file.delete();
            }
        }
    }


    @Override
    public IPage<Projects> selectPage(ProjectsQueryForm queryForm ) {
        QueryWrapper<Projects> queryWrapper = new QueryWrapper <Projects>();
        //getQueryWrapper(queryWrapper,queryForm);
        return projectsMapper.selectPage(new Page<Projects>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Projects> selectList(ProjectsQueryForm queryForm) {
        QueryWrapper<Projects> queryWrapper = new QueryWrapper <Projects>();
        //getQueryWrapper(queryWrapper,queryForm);
        return projectsMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Projects> getQueryWrapper(QueryWrapper<Projects> queryWrapper,ProjectsQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(Projects.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectCode())){
            queryWrapper.eq(Projects.PROJECT_CODE,queryForm.getProjectCode());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectName())){
            queryWrapper.eq(Projects.PROJECT_NAME,queryForm.getProjectName());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectAttribute())){
            queryWrapper.eq(Projects.PROJECT_ATTRIBUTE,queryForm.getProjectAttribute());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectTypeCode())){
            queryWrapper.eq(Projects.PROJECT_TYPE_CODE,queryForm.getProjectTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectTypeName())){
            queryWrapper.eq(Projects.PROJECT_TYPE_NAME,queryForm.getProjectTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getSubprojectTypeCode())){
            queryWrapper.eq(Projects.SUBPROJECT_TYPE_CODE,queryForm.getSubprojectTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getSubprojectTypeName())){
            queryWrapper.eq(Projects.SUBPROJECT_TYPE_NAME,queryForm.getSubprojectTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getPlanYear())){
            queryWrapper.eq(Projects.PLAN_YEAR,queryForm.getPlanYear());
        }
        if(StringUtils.isNotBlank(queryForm.getCycle())){
            queryWrapper.eq(Projects.CYCLE,queryForm.getCycle());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyNewYear())){
            queryWrapper.eq(Projects.REPLY_NEW_YEAR,queryForm.getReplyNewYear());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectHeaderId())){
            queryWrapper.eq(Projects.PROJECT_HEADER_ID,queryForm.getProjectHeaderId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectHeaderName())){
            queryWrapper.eq(Projects.PROJECT_HEADER_NAME,queryForm.getProjectHeaderName());
        }
        if(StringUtils.isNotBlank(queryForm.getFinHeaderId())){
            queryWrapper.eq(Projects.FIN_HEADER_ID,queryForm.getFinHeaderId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectConnectStaffId())){
            queryWrapper.eq(Projects.PROJECT_CONNECT_STAFF_ID,queryForm.getProjectConnectStaffId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrganizationId())){
            queryWrapper.eq(Projects.ORGANIZATION_ID,queryForm.getOrganizationId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrganizationName())){
            queryWrapper.eq(Projects.ORGANIZATION_NAME,queryForm.getOrganizationName());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectCatgory())){
            queryWrapper.eq(Projects.PROJECT_CATGORY,queryForm.getProjectCatgory());
        }
        if(StringUtils.isNotBlank(queryForm.getProCancelDes())){
            queryWrapper.eq(Projects.PRO_CANCEL_DES,queryForm.getProCancelDes());
        }
        if(StringUtils.isNotBlank(queryForm.getProChange())){
            queryWrapper.eq(Projects.PRO_CHANGE,queryForm.getProChange());
        }
        if(StringUtils.isNotBlank(queryForm.getPriority())){
            queryWrapper.eq(Projects.PRIORITY,queryForm.getPriority());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Projects.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Projects.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Projects.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Projects.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Projects.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Projects.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Projects.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Projects.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(Projects.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Projects.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Projects.ORG_PATH,queryForm.getOrgPath());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectStatusCode())){
            queryWrapper.eq(Projects.PROJECT_STATUS_CODE,queryForm.getProjectStatusCode());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectStatusName())){
            queryWrapper.eq(Projects.PROJECT_STATUS_NAME,queryForm.getProjectStatusName());
        }
        if(StringUtils.isNotBlank(queryForm.getEntrustId())){
            queryWrapper.eq(Projects.ENTRUST_ID,queryForm.getEntrustId());
        }
        if(StringUtils.isNotBlank(queryForm.getAssumeId())){
            queryWrapper.eq(Projects.ASSUME_ID,queryForm.getAssumeId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectMark())){
            queryWrapper.eq(Projects.PROJECT_MARK,queryForm.getProjectMark());
        }
        if(StringUtils.isNotBlank(queryForm.getFinHeaderName())){
            queryWrapper.eq(Projects.FIN_HEADER_NAME,queryForm.getFinHeaderName());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectConnectStaffName())){
            queryWrapper.eq(Projects.PROJECT_CONNECT_STAFF_NAME,queryForm.getProjectConnectStaffName());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectCatgoryCode())){
            queryWrapper.eq(Projects.PROJECT_CATGORY_CODE,queryForm.getProjectCatgoryCode());
        }
        if(StringUtils.isNotBlank(queryForm.getEntrustName())){
            queryWrapper.eq(Projects.ENTRUST_NAME,queryForm.getEntrustName());
        }
        if(StringUtils.isNotBlank(queryForm.getAssumeName())){
            queryWrapper.eq(Projects.ASSUME_NAME,queryForm.getAssumeName());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationMark())){
            queryWrapper.eq(Projects.APPLICATION_MARK,queryForm.getApplicationMark());
        }
        if(StringUtils.isNotBlank(queryForm.getZipCode())){
            queryWrapper.eq(Projects.ZIP_CODE,queryForm.getZipCode());
        }
        if(StringUtils.isNotBlank(queryForm.getOuChargeStaffId())){
            queryWrapper.eq(Projects.OU_CHARGE_STAFF_ID,queryForm.getOuChargeStaffId());
        }
        if(StringUtils.isNotBlank(queryForm.getOuChargeStaffName())){
            queryWrapper.eq(Projects.OU_CHARGE_STAFF_NAME,queryForm.getOuChargeStaffName());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectHeaderPost())){
            queryWrapper.eq(Projects.PROJECT_HEADER_POST,queryForm.getProjectHeaderPost());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectHeaderTel())){
            queryWrapper.eq(Projects.PROJECT_HEADER_TEL,queryForm.getProjectHeaderTel());
        }
        if(StringUtils.isNotBlank(queryForm.getProConnectStaffPost())){
            queryWrapper.eq(Projects.PRO_CONNECT_STAFF_POST,queryForm.getProConnectStaffPost());
        }
        if(StringUtils.isNotBlank(queryForm.getProConnectStaffTel())){
            queryWrapper.eq(Projects.PRO_CONNECT_STAFF_TEL,queryForm.getProConnectStaffTel());
        }
        if(StringUtils.isNotBlank(queryForm.getAdress())){
            queryWrapper.eq(Projects.ADRESS,queryForm.getAdress());
        }
        if(StringUtils.isNotBlank(queryForm.getDepartment())){
            queryWrapper.eq(Projects.DEPARTMENT,queryForm.getDepartment());
        }
        if(StringUtils.isNotBlank(queryForm.getDepartmentCode())){
            queryWrapper.eq(Projects.DEPARTMENT_CODE,queryForm.getDepartmentCode());
        }
        if(StringUtils.isNotBlank(queryForm.getAppOpartionOu())){
            queryWrapper.eq(Projects.APP_OPARTION_OU,queryForm.getAppOpartionOu());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyNewMark())){
            queryWrapper.eq(Projects.REPLY_NEW_MARK,queryForm.getReplyNewMark());
        }
        if(StringUtils.isNotBlank(queryForm.getSubactCatagoryCode())){
            queryWrapper.eq(Projects.SUBACT_CATAGORY_CODE,queryForm.getSubactCatagoryCode());
        }
        if(StringUtils.isNotBlank(queryForm.getSubactCatagoryName())){
            queryWrapper.eq(Projects.SUBACT_CATAGORY_NAME,queryForm.getSubactCatagoryName());
        }
        return queryWrapper;
    }
     */
}

