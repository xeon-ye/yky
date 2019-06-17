package com.deloitte.services.srpmp.project.base.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentVo;
import com.deloitte.platform.common.core.util.UUIDUtil;
import com.deloitte.services.srpmp.common.enums.AttachmentCategoryEnums;
import com.deloitte.services.srpmp.common.util.PDFUtils;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;

import java.util.Iterator;
import java.util.List;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectAttachment;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectAttachmentService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lixin on 10/04/2019.
 */
@Service
@Slf4j
@Transactional
public class PdfMergeServiceImpl {

    @Autowired
    private ISrpmsProjectAttachmentService attachmentService;

    @Autowired
    private FileOperatorFeignService fileOperatorFeignService;

    public void mergeAttachmentApply(String firstPdfFile, Long projectId, String targetPdfFilePath) throws Exception {
        List<String> pdfFilePathList = new ArrayList<>();
        pdfFilePathList.add(firstPdfFile);
        JSONObject attachmentJson = attachmentService.queryAttachmentListApply(projectId);
        SrpmsProjectAttachment att1 = attachmentJson.getObject("attachmentCommittee", SrpmsProjectAttachment.class);
        if (att1 != null && att1.getFileUrl() != null) {
            pdfFilePathList.add(att1.getFileUrl());
        }
        SrpmsProjectAttachment att2 = attachmentJson.getObject("attachmentAudit", SrpmsProjectAttachment.class);
        if (att2 != null && att2.getFileUrl() != null) {
            pdfFilePathList.add(att2.getFileUrl());
        }
        SrpmsProjectAttachment att3 = attachmentJson.getObject("attachmentStatement", SrpmsProjectAttachment.class);
        if (att3 != null && att3.getFileUrl() != null) {
            pdfFilePathList.add(att3.getFileUrl());
        }
        SrpmsProjectAttachment att4 = attachmentJson.getObject("attachmentDept", SrpmsProjectAttachment.class);
        if (att4 != null && att4.getFileUrl() != null) {
            pdfFilePathList.add(att4.getFileUrl());
        }
        SrpmsProjectAttachment att5 = attachmentJson.getObject("attachmentSchool", SrpmsProjectAttachment.class);
        if (att5 != null && att5.getFileUrl() != null) {
            pdfFilePathList.add(att5.getFileUrl());
        }
        PDFUtils.mergePdfSupportImage(pdfFilePathList, targetPdfFilePath);
    }

    /**
     * 创新工程
     * @param firstPdfFile
     * @param projectId
     * @param targetPdfFilePath
     * @throws Exception
     */
    public void mergeAttachmentTaskType1(String firstPdfFile, Long projectId, String targetPdfFilePath) throws Exception {
        List<String> pdfFilePathList = new ArrayList<>();
        pdfFilePathList.add(firstPdfFile);
        JSONObject attachmentJson = attachmentService.queryAttachmentListTaskType1(projectId);
        SrpmsProjectAttachment att1 = attachmentJson.getObject("attachmentAudit", SrpmsProjectAttachment.class);
        if (att1 != null && att1.getFileUrl() != null) {
            pdfFilePathList.add(att1.getFileUrl());
        }
        SrpmsProjectAttachment att2 = attachmentJson.getObject("attachmentStatement", SrpmsProjectAttachment.class);
        if (att2 != null && att2.getFileUrl() != null) {
            pdfFilePathList.add(att2.getFileUrl());
        }
        SrpmsProjectAttachment att3 = attachmentJson.getObject("opinionsAndSignatures", SrpmsProjectAttachment.class);
        if (att3 != null && att3.getFileUrl() != null) {
            pdfFilePathList.add(att3.getFileUrl());
        }
        SrpmsProjectAttachment att4 = attachmentJson.getObject("attachmentCommittee", SrpmsProjectAttachment.class);
        if (att4 != null && att4.getFileUrl() != null) {
            pdfFilePathList.add(att4.getFileUrl());
        }
        PDFUtils.mergePdfSupportImage(pdfFilePathList, targetPdfFilePath);
    }

    /**
     * 创新工程
     * @param firstPdfFile
     * @param projectId
     * @param targetPdfFilePath
     * @throws Exception
     */
    public void mergeAttachmentTaskType2(String firstPdfFile, Long projectId, String targetPdfFilePath) throws Exception {
        List<String> pdfFilePathList = new ArrayList<>();
        pdfFilePathList.add(firstPdfFile);
        JSONObject attachmentJson = attachmentService.queryAttachmentListTaskType2(projectId);
        SrpmsProjectAttachment att1 = attachmentJson.getObject("attachmentCommittee", SrpmsProjectAttachment.class);
        if (att1 != null && att1.getFileUrl() != null) {
            pdfFilePathList.add(att1.getFileUrl());
        }
        SrpmsProjectAttachment att2 = attachmentJson.getObject("attachmentAudit", SrpmsProjectAttachment.class);
        if (att2 != null && att2.getFileUrl() != null) {
            pdfFilePathList.add(att2.getFileUrl());
        }
        SrpmsProjectAttachment att3 = attachmentJson.getObject("attachmentStatement", SrpmsProjectAttachment.class);
        if (att3 != null && att3.getFileUrl() != null) {
            pdfFilePathList.add(att3.getFileUrl());
        }
        PDFUtils.mergePdfSupportImage(pdfFilePathList, targetPdfFilePath);
    }

    /**
     * 生成pdf文件与验收报告附件拼接
     *
     * @param firstPdfFile
     * @param id
     * @param targetPdfFilePath
     * @throws Exception
     */
    public void mergeAttachmentAccept(String firstPdfFile, Long id, String targetPdfFilePath) throws Exception {
        List<String> pdfFilePathList = new ArrayList<>();
        pdfFilePathList.add(firstPdfFile);
        List<SrpmsProjectAttachmentVo> list = attachmentService.queryAttachmentListAccept(id);
        if (list != null && list.size() > 0) {
            SrpmsProjectAttachmentVo attachmentVo;
            for (Iterator e = list.iterator(); e.hasNext(); ) {
                attachmentVo = (SrpmsProjectAttachmentVo) e.next();
                pdfFilePathList.add(attachmentVo.getFileUrl());
            }
        }
        PDFUtils.mergePdfSupportImage(pdfFilePathList, targetPdfFilePath);
    }

    public String wordToPdf(String docxFilePath) throws Exception {
        String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
        String pdfFilePath = jarPath+ "/word/pdf/Export_"+ UUIDUtil.getRandom32PK() +".pdf";
        File parentFile = new File(pdfFilePath).getParentFile();
        if (!parentFile.exists()){
            parentFile.mkdirs();
        }
        WordConvertUtil.docx2PDF(docxFilePath, pdfFilePath);
        return pdfFilePath;
    }


    public static void main(String[] args) {
        PDFUtils.imgToPdf("http://www.51itstudy.com/web/UploadFiles/Document/201206/21/20120621195513532437.jpg");
    }

}
