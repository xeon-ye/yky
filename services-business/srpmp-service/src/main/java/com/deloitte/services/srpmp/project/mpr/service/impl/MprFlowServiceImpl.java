package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaBaseInfoVo;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaFileInfoFieldForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaFileInfoVoExt;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprUnitEvaInfoForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.enums.EnumMprProcessStatus;
import com.deloitte.services.srpmp.common.enums.ReportTypeEnum;
import com.deloitte.services.srpmp.common.enums.VoucherAuditModeEnums;
import com.deloitte.services.srpmp.common.enums.VoucherStatusEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsVoucher;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsVoucherService;
import com.deloitte.services.srpmp.project.base.service.impl.SrpmsProjectBpmServiceImpl;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaBaseInfo;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaFileInfo;
import com.deloitte.services.srpmp.project.mpr.entity.MprUnitEvaInfo;
import com.deloitte.services.srpmp.project.mpr.service.*;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author:LIJUN
 * Date:02/04/2019
 * Description:
 */
@Slf4j
@Service
@Transactional
public class MprFlowServiceImpl implements IMprFlowService {

    @Autowired
    private ISrpmsProjectService projectService;

    @Autowired
    private ISrpmsVoucherService voucherService;

    @Autowired
    private SrpmsProjectBpmServiceImpl bpmService;

    @Autowired
    private IMprEvaBaseInfoService baseInfoService;

    @Autowired
    private FileOperatorFeignService fileOperatorFeignService;

    @Autowired
    private IMprEvaFileInfoService mprEvaFileInfoService;

    @Autowired
    private IMprProjEvaInfoService mprProjEvaInfoService;

    @Autowired
    private IMprUnitEvaInfoService mprUnitEvaInfoService;

    @Autowired
    private ISrpmsProjectFlowService flowService;

    @Override
    public void submitMprA(Long projectId, UserVo userVo, DeptVo deptVo) {
        //基本情况信息
        MprEvaBaseInfo baseInfo = baseInfoService.getMprBaseInfo(projectId);
        if (null == baseInfo) {
            throw new BaseException(SrpmsErrorType.EVA_BASE_NOT_COMPLETE);
        }

        if (StringUtils.equals(baseInfo.getProcessStatus(), EnumMprProcessStatus.MPR_APPROVED.getCode())
                || StringUtils.equals(baseInfo.getProcessStatus(), EnumMprProcessStatus.MPR_AUDIT.getCode())
                || StringUtils.equals(baseInfo.getProcessStatus(), EnumMprProcessStatus.MPR_REFUSE.getCode())) {
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "请勿重复提交");
        }

        //文件信息
        MprEvaFileInfoVoExt fileInfoVoExt = mprEvaFileInfoService.getByProjectId(projectId);
        MprEvaFileInfo evaFileInfo;
        if (null == fileInfoVoExt) {
            evaFileInfo = new MprEvaFileInfo();
            evaFileInfo.setId(projectId);
        } else {
            evaFileInfo = new BeanUtils<MprEvaFileInfo>().copyObj(fileInfoVoExt, MprEvaFileInfo.class);
        }

        //上传附件到文件服务器
        //附件一
        String annexOneUrl = baseInfoService.exportAnnexOneExcel(projectId);
        FileInfoVo annexOneFileInfo = uploadFile(annexOneUrl, "中国医学与健康科技创新工程项目中期绩效考评信息表" + SrpmsConstant.EXT_EXCEL_XLSX);
        if (null == annexOneFileInfo) {
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "上传文件服务器失败");
        }
        MprEvaFileInfoFieldForm annexOneField = new MprEvaFileInfoFieldForm();
        annexOneField.setFileId(annexOneFileInfo.getId());
        //方便前端展示，手动设置名字
        annexOneField.setFileName(annexOneFileInfo.getFileName());
        annexOneField.setFileUrl(annexOneFileInfo.getFileUrl());
        evaFileInfo.setAnnexOne(JSON.toJSONString(annexOneField));

        //附件六
        String annexSixUrl = mprProjEvaInfoService.exportWord(projectId);
        if (StringUtils.isNotBlank(annexSixUrl)) {
            FileInfoVo annexSixFileInfo = uploadFile(annexSixUrl, "中国医学科学院医学与健康科技创新工程项目中期绩效考评自评报告" + SrpmsConstant.EXT_WORD_DOCX);
            if (null == annexSixFileInfo) {
                throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "上传文件服务器失败");
            }
            MprEvaFileInfoFieldForm annexSixField = new MprEvaFileInfoFieldForm();
            annexSixField.setFileId(annexSixFileInfo.getId());
            annexSixField.setFileName(annexSixFileInfo.getFileName());
            annexSixField.setFileUrl(annexSixFileInfo.getFileUrl());
            evaFileInfo.setAnnexSix(JSON.toJSONString(annexSixField));
        }
        mprEvaFileInfoService.saveOrUpdate(evaFileInfo);

        //更新中期绩效报告状态
        baseInfo.setProcessStatus(EnumMprProcessStatus.MPR_AUDIT.getCode());
        QueryWrapper<MprEvaBaseInfo> wrapper = new QueryWrapper<>();
        wrapper.eq(MprEvaBaseInfo.ID, baseInfo.getId());
        wrapper.eq(MprEvaBaseInfo.REPORT_TYPE, ReportTypeEnum.MPR_REPORT.getCode());
        baseInfoService.update(baseInfo, wrapper);

        //保存单据信息
        SrpmsProject project = projectService.getById(projectId);
        SrpmsVoucher voucher = voucherService.getSrpmsVoucherByReject(project.getId());// 查询项目是否进行驳回操作
        if (voucher != null ) {
            flowService.againSubmit(voucher, deptVo, false);
        } else {
            // 新建一个单据
            voucher = new SrpmsVoucher();
            String deptCode = JSONObject.parseObject(project.getLeadDept()).getString("deptCode");
            voucher.setBizNumber("MPRA" + project.getId());
            voucher.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
            voucher.setStartTime(LocalDateTime.now());
            voucher.setVoucherType(VoucherTypeEnums.MPR_EVA_A.getCode());
            voucher.setPersonName(userVo.getName());
            voucher.setUserId(Long.parseLong(userVo.getId()));
            voucher.setProjectId(project.getId());
            voucher.setVoucherName(project.getProjectName() + "中期绩效报告审核");
            voucher.setLeadDeptCode(deptCode);
            voucher.setAuditMode(VoucherAuditModeEnums.ALL.getCode());
            voucherService.save(voucher);

            //开始流程
            bpmService.startProcess(voucher, userVo, deptVo, null);
        }

    }

    @Override
    public void submitMprB(MprUnitEvaInfoForm mprUnitEvaInfoForm, UserVo userVo, DeptVo deptVo) {
        //保存
        Long id = mprUnitEvaInfoService.saveAndUpdate(mprUnitEvaInfoForm, userVo, deptVo);

        //附件九信息
        MprUnitEvaInfo unitEvaInfo = mprUnitEvaInfoService.getById(id);
        if (null == unitEvaInfo) {
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "请先保存");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        unitEvaInfo.getCreateTime().format(formatter);
        if (StringUtils.equals(unitEvaInfo.getProcessStatus(), EnumMprProcessStatus.MPR_APPROVED.getCode())
                || StringUtils.equals(unitEvaInfo.getProcessStatus(), EnumMprProcessStatus.MPR_AUDIT.getCode())
                || StringUtils.equals(unitEvaInfo.getProcessStatus(), EnumMprProcessStatus.MPR_REFUSE.getCode())) {
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "请勿重复提交");
        }

        //附件九
        //上传附件到文件服务器
        String fileUrl = mprUnitEvaInfoService.exportWord(id);
        String fileName = "中国医学科学院医学与健康科技创新工程承担单位中期绩效考评自评报告（" + unitEvaInfo.getYear() + "）" + SrpmsConstant.EXT_WORD_DOCX;
        FileInfoVo fileInfo = uploadFile(fileUrl, fileName);
        if (null == fileInfo) {
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "上传文件服务器失败");
        }
        MprEvaFileInfoFieldForm fieldForm = new MprEvaFileInfoFieldForm();
        fieldForm.setFileId(fileInfo.getId());
        fieldForm.setFileName(fileInfo.getFileName());
        fieldForm.setFileUrl(fileInfo.getFileUrl());
        unitEvaInfo.setFileInfo(JSON.toJSONString(fieldForm));

        //更新附件九
        unitEvaInfo.setProcessStatus(EnumMprProcessStatus.MPR_AUDIT.getCode());
        mprUnitEvaInfoService.updateById(unitEvaInfo);

        //保存单据信息
        SrpmsVoucher voucher = voucherService.getSrpmsVoucherByReject(unitEvaInfo.getId());// 查询项目是否进行驳回操作
        if (voucher != null ) {
            flowService.againSubmit(voucher, deptVo, false);
        } else {
            // 新建一个单据
            voucher = new SrpmsVoucher();
            String deptCode = projectService.getLeadDeptCodeByProjectId(unitEvaInfo.getId());
            voucher.setBizNumber(String.valueOf(unitEvaInfo.getId()));
            voucher.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
            voucher.setStartTime(LocalDateTime.now());
            voucher.setVoucherType(VoucherTypeEnums.MPR_EVA_B.getCode());
            voucher.setPersonName(userVo.getName());
            voucher.setUserId(Long.parseLong(userVo.getId()));
            voucher.setProjectId(unitEvaInfo.getId());// 新增的时候将自评ID保存到单据projectId字段
            voucher.setVoucherName("承担单位中期绩效考评自评报告审核" + unitEvaInfo.getId());
            voucher.setLeadDeptCode(deptCode);
            voucher.setAuditMode(VoucherAuditModeEnums.ALL.getCode());
            voucherService.save(voucher);

            //开始流程
            bpmService.startProcess(voucher, userVo, deptVo, null);
        }
    }

    private FileInfoVo uploadFile(String fileUrl, String fileName) {
        try {
            File file = new File(fileUrl);
            FileInputStream fileInputStream = new FileInputStream(file);
            MultipartFile annexOneMultipartFile = new MockMultipartFile("file", fileName,
                    ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
            Result<FileInfoVo> result = fileOperatorFeignService.uploadFile(annexOneMultipartFile);
            if (result.isSuccess()){
                CommonUtil.deleteFile(fileUrl);
            }else {
                log.error("中期绩效报告附件上传文件服务器失败。rsp:{}", JSONObject.toJSONString(result));
            }
            return result.getData();
        } catch (IOException e) {
            log.error("上传中期绩效报告到文件服务器异常.", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        }
    }

    @Override
    public void agreeMpr(TaskNodeActionVO actionVO, DeptVo deptVo) {
        boolean hasEnd = bpmService.auditApprove(actionVO, deptVo);
        SrpmsVoucher voucher = voucherService.getById(actionVO.getObjectId());

        // 审批后更新单据撤回标识
        voucher.setRecallFlag(1);

        if (hasEnd) {
            //流程A
            if (StringUtils.equals(voucher.getVoucherType(), VoucherTypeEnums.MPR_EVA_A.getCode())) {
                MprEvaBaseInfo baseInfo = baseInfoService.getMprBaseInfo(voucher.getProjectId());
                baseInfo.setProcessStatus(EnumMprProcessStatus.MPR_APPROVED.getCode());
                QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq(MprEvaBaseInfo.ID, baseInfo.getId());
                queryWrapper.eq(MprEvaBaseInfo.REPORT_TYPE, ReportTypeEnum.MPR_REPORT.getCode());
                baseInfoService.update(baseInfo, queryWrapper);
            } else if (StringUtils.equals(voucher.getVoucherType(), VoucherTypeEnums.MPR_EVA_B.getCode())){
                MprUnitEvaInfo unitEvaInfo = mprUnitEvaInfoService.getById(Long.parseLong(voucher.getBizNumber()));
                unitEvaInfo.setProcessStatus(EnumMprProcessStatus.MPR_APPROVED.getCode());
                mprUnitEvaInfoService.updateById(unitEvaInfo);
            }
            //更新单据状态
            voucher.setVoucherStatus(VoucherStatusEnums.APPROVED.getCode());
            voucherService.saveOrUpdate(voucher);
        }
    }

    @Override
    @Transactional
    public void refuseMpr(TaskNodeActionVO actionVO) {
        bpmService.auditRefuse(actionVO);

        SrpmsVoucher voucher = voucherService.getById(actionVO.getObjectId());

        if (StringUtils.equals(voucher.getVoucherType(), VoucherTypeEnums.MPR_EVA_A.getCode())) {
            MprEvaBaseInfo baseInfo = baseInfoService.getMprBaseInfo(voucher.getProjectId());
            baseInfo.setProcessStatus(EnumMprProcessStatus.MPR_REFUSE.getCode());
            QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(MprEvaBaseInfo.ID, baseInfo.getId());
            queryWrapper.eq(MprEvaBaseInfo.REPORT_TYPE, ReportTypeEnum.MPR_REPORT.getCode());
            baseInfoService.update(baseInfo, queryWrapper);
//            baseInfoService.delAnnexOneInfo(voucher.getProjectId());
//            mprProjEvaInfoService.removeById(voucher.getProjectId());
        } else if (StringUtils.equals(voucher.getVoucherType(), VoucherTypeEnums.MPR_EVA_B.getCode())){
            MprUnitEvaInfo unitEvaInfo = mprUnitEvaInfoService.getById(voucher.getProjectId());
            unitEvaInfo.setProcessStatus(EnumMprProcessStatus.MPR_REFUSE.getCode());
            mprUnitEvaInfoService.updateById(unitEvaInfo);
        }

        voucher.setRecallFlag(1);
        // 更新单据状态
        voucher.setVoucherStatus(VoucherStatusEnums.REFUSED.getCode());
        voucherService.saveOrUpdate(voucher);
//		voucherService.updateStatus(CommonUtil.getLongValue(actionVO.getObjectId()), VoucherStatusEnums.REFUSED);
    }

    @Override
    public void rejectMprToFirst(TaskNodeActionVO actionVO) {
        SrpmsVoucher voucher = voucherService.getById(actionVO.getObjectId());
        if (null == voucher) {
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "单据不存在");
        }
        bpmService.auditBackToFirst(actionVO, voucher);

        if (StringUtils.equals(voucher.getVoucherType(), VoucherTypeEnums.MPR_EVA_A.getCode())) {
            MprEvaBaseInfo baseInfo = baseInfoService.getMprBaseInfo(voucher.getProjectId());
            baseInfo.setProcessStatus(EnumMprProcessStatus.MPR_REJECT.getCode());
            QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(MprEvaBaseInfo.ID, baseInfo.getId());
            queryWrapper.eq(MprEvaBaseInfo.REPORT_TYPE, ReportTypeEnum.MPR_REPORT.getCode());
            baseInfoService.update(baseInfo, queryWrapper);
        } else if (StringUtils.equals(voucher.getVoucherType(), VoucherTypeEnums.MPR_EVA_B.getCode())) {
            MprUnitEvaInfo unitEvaInfo = mprUnitEvaInfoService.getById(voucher.getProjectId());
            unitEvaInfo.setProcessStatus(EnumMprProcessStatus.MPR_REJECT.getCode());
            mprUnitEvaInfoService.updateById(unitEvaInfo);
        }
        //更新单据状态
        voucherService.updateStatus(Long.parseLong(actionVO.getObjectId()), VoucherStatusEnums.REJECT);
    }
}
