package com.deloitte.services.project.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryWrapper;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.contract.param.SignInfoQueryForm;
import com.deloitte.platform.api.contract.vo.*;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.ResourceVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskForm;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.project.common.enums.ProjectErrorType;
import com.deloitte.services.project.common.enums.ValueEnums;
import com.deloitte.services.project.common.enums.VoucherTypeEnums;
import com.deloitte.services.project.common.util.AssertUtils;
import com.deloitte.services.project.common.util.HttpUtils;
import com.deloitte.services.project.entity.Application;
import com.deloitte.services.project.entity.ApprovalProcess;
import com.deloitte.services.project.entity.ApprovalVouchers;
import com.deloitte.services.project.entity.Projects;
import com.deloitte.services.project.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  bpm服务实现类
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class CommonServiceImpl implements ICommonService {


    @Value("${project.audit.url:www.baidu.com}")
    private String PROJECT_AUDIT_URL;
    @Autowired
    private ICarbonCopyService carbonCopyService;

    @Autowired
    private IApprovalVouchersService approvalVoucherService;

    @Autowired
    private FileOperatorFeignService fileOperatorFeignService;

    @Autowired
    private IApplicationService applicationService;

    @Autowired
    private IProjectsService projectsService;
    @Autowired
    private IApprovalProcessService approvalProcessService;
    @Autowired
    private IApprovalVouchersService approvalVouchersService;
    @Autowired
    public IBPMService bpmService;

    /**
     * 用户当前用户的单位
     * @return
     */
    @Override
    public UserVo getCurrentUser() {
        return UserUtil.getUserVo();
    }

    /**
     * 获取当前用户的单位
     * @return
     */
    @Override
    public DeptVo getCurrentDept() {
        return UserUtil.getDept();
    }

    /**
     * 获取当前用户的菜单
     * @return
     */
    @Override
    public ResourceVo getMenu() {
        return UserUtil.getMenu();
    }

    /**
     * 获取当前用户的部门
     * @return
     */
    @Override
    public OrganizationVo getOrganization() {
        return UserUtil.getOrganization();
    }

    @Override
    public Result sendProcessTask(UserVo userVo, String businessId, VoucherTypeEnums typeEnums){
        UserVo currentUserVo = getCurrentUser();
        SendProcessTaskForm sendProcessTaskForm = new SendProcessTaskForm();
        //给某个id 发送一条待阅
        //新建一个单据
        ApprovalVouchers voucher = approvalVoucherService.generateNewVoucher(businessId, typeEnums);
        sendProcessTaskForm.setApproverAcount(userVo.getId());
        sendProcessTaskForm.setApproverName(userVo.getName());
        sendProcessTaskForm.setApproverStation(userVo.getHonor());
        sendProcessTaskForm.setObjectType( typeEnums.getCode());
        sendProcessTaskForm.setObjectId(voucher.getId()+"");
        sendProcessTaskForm.setObjectDescription(voucher.getVoucherName()+"");
        sendProcessTaskForm.setCreateByName(currentUserVo.getName()+"");
        sendProcessTaskForm.setSubmitterCode(currentUserVo.getId()+"");
        sendProcessTaskForm.setSubmitterName(currentUserVo.getName()+"");
        sendProcessTaskForm.setObjectUrl(PROJECT_AUDIT_URL+"?returnSystem=project");
        sendProcessTaskForm.setTaskStauts("待阅");
        Result result = carbonCopyService.add(sendProcessTaskForm);
        return result;
    }

    @Override
    public FileInfoVo uploadFile(String fileUrl) {
        try {
            File file = new File(fileUrl);
            FileInputStream fileInputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file", file.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
            Result<FileInfoVo> result = fileOperatorFeignService.uploadFile(multipartFile);
            if (result.isSuccess()){
                file.delete();
            }else {
                log.error("上传文件服务器失败。rsp:{}", JSONObject.toJSONString(result));
                throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "上传文件服务器失败");
            }
            return result.getData();
        } catch (IOException e) {
            log.error("上传文件服务器异常.", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        }
    }

    @Override
    public FileInfoVo uploadFile(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file", file.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
            Result<FileInfoVo> result = fileOperatorFeignService.uploadFile(multipartFile);
            if (result.isSuccess()){
                file.delete();
            }else {
                log.error("上传文件服务器失败。rsp:{}", JSONObject.toJSONString(result));
                throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "上传文件服务器失败");
            }
            return result.getData();
        } catch (IOException e) {
            log.error("上传文件服务器异常.", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        }
    }

    @Override
    public void updateProjectAndApplicationStatusWithApplicationId(String applicationId) {
        if (Objects.nonNull(applicationId)) {
            QueryWrapper<Application> applicationQueryWrapper = new QueryWrapper<>();
            applicationQueryWrapper.eq(Application.APPLICATION_ID, applicationId);
            Application application = applicationService.getOne(applicationQueryWrapper);
            if (Objects.nonNull(application)) {
                application.setAppStateCode(ValueEnums.APPLICATION_APPROVED.getCode());
                application.setAppStateName(ValueEnums.APPLICATION_APPROVED.getValue());
                applicationService.update(application, applicationQueryWrapper);

                QueryWrapper<Projects> projectsQueryWrapper = new QueryWrapper<>();
                projectsQueryWrapper.eq(Projects.PROJECT_ID, application.getProjectId());
                Projects projects = projectsService.getOne(projectsQueryWrapper);
                if (Objects.nonNull(projects)) {
                    //projects.setProjectStatusCode(ValueEnums.P);
                    //projects.setProjectStatusName();
                }

            }
        }
    }
    /**
     *@Description: CommonServiceImpl
     *@Param: [businessId 业务id, processVariables 参数, typeEnums 流程枚举]
     *@return: com.deloitte.platform.common.core.entity.vo.Result
     *@Author: zhengchun
     *@date: 2019/6/12 14:53
     */
    @Override
    public Result startAuditProcess(String  businessId,  Map<String, String> processVariables,VoucherTypeEnums typeEnums) {
        UserVo userVo = getCurrentUser();
        OrganizationVo organizationVo = getOrganization();
        //获取流程信息
        QueryWrapper<ApprovalProcess> processQueryWrapper = new QueryWrapper<>();
        processQueryWrapper.eq(ApprovalProcess.PROCESS_TYPE,typeEnums.getCode());
        ApprovalProcess approvalProcess = approvalProcessService.getOne(processQueryWrapper);
        AssertUtils.asserts(null == approvalProcess , ProjectErrorType.PROCESS_IS_NULL);
        //生成单据
        ApprovalVouchers voucher = approvalVouchersService.generateNewVoucher(businessId, typeEnums);
        // 提交流程
        Result result = bpmService.startAuditProcess(voucher, userVo, organizationVo,approvalProcess.getProcessDefineKey(), processVariables);
        return result;
    }

    /**
     *@Description: CommonServiceImpl
     *@Param: [businessId 业务id, processVariables 参数, typeEnums 流程枚举,acceptVo 接收人信息]
     *@return: com.deloitte.platform.common.core.entity.vo.Result
     *@Author: zhengchun
     *@date: 2019/6/12 14:53
     */
    @Override
    public Result startAuditProcessByAccept(String  businessId,  Map<String, String> processVariables,VoucherTypeEnums typeEnums, UserVo acceptVo ) {
        UserVo userVo = getCurrentUser();
        OrganizationVo organizationVo = getOrganization();
        //获取流程信息
        QueryWrapper<ApprovalProcess> processQueryWrapper = new QueryWrapper<>();
        processQueryWrapper.eq(ApprovalProcess.PROCESS_TYPE,typeEnums.getCode());
        ApprovalProcess approvalProcess = approvalProcessService.getOne(processQueryWrapper);
        AssertUtils.asserts(null == approvalProcess , ProjectErrorType.PROCESS_IS_NULL);
        //生成单据
        ApprovalVouchers voucher = approvalVouchersService.generateNewVoucher(businessId, typeEnums);
        // 提交流程
        Result result = bpmService.autoStartAuditProcess(voucher, userVo, organizationVo,acceptVo,approvalProcess.getProcessDefineKey(), processVariables);
        return result;
    }

}

