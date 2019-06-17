package com.deloitte.services.srpmp.project.update.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.entity.SrpmsCommonNclob;
import com.deloitte.services.srpmp.common.enums.SysDictEnums;
import com.deloitte.services.srpmp.common.service.ISrpmsCommonNclobService;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.service.impl.WordExportServiceImpl;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskTranLongService;
import com.deloitte.services.srpmp.project.update.entity.SrpmsProjectUpdate;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-01
 * @Description : SrpmsProjectUpdate服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsProjectUpdateFileServiceImpl {

	@Autowired
	private ISrpmsProjectService projectService;

	@Autowired
	private ISysDictService sysDictService;

	@Autowired
	private SrpmsProjectUpdateBudgetServiceImpl projectUpdateBudgetService;

	@Autowired
	private WordExportServiceImpl wordExportService;

	@Autowired
	private FileOperatorFeignService fileOperatorFeignService;

	@Autowired
	ISrpmsProjectTaskTranLongService tranLongService;

	@Autowired
	SrpmsProjectUpdateMenberServiceImpl updateMenberService;

	@Autowired
	ISrpmsCommonNclobService nclobService;

	public void generateApproveFile(SrpmsProjectUpdate updateEntity) {

		String fileName = null;
		switch (updateEntity.getUpdateType()) {
		case SrpmsConstant.MODIFY_MODIFY_TYPE_10:
			fileName = "/项目变更_人员变更批复.pdf";
			break;
		case SrpmsConstant.MODIFY_MODIFY_TYPE_20:
			fileName = "/项目变更_负责人变更批复.pdf";
			break;
		case SrpmsConstant.MODIFY_MODIFY_TYPE_30:
			fileName = "/项目变更_内容变更批复.pdf";
			break;
		case SrpmsConstant.MODIFY_MODIFY_TYPE_40:
			fileName = "/项目变更_预算变更批复.pdf";
			break;
		case SrpmsConstant.MODIFY_MODIFY_TYPE_50:
			fileName = "/项目变更_状态变更批复.pdf";
			break;
		default:
			log.error("项目变更类型不存在");
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		}
		// 生成word文件
		File pdfFile = null;
		FileInputStream fileInputStream = null;
		try {
			// 生成申请书pdf文档
			String docxFilePath = this.createApproveWordFile(updateEntity);
			String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
			String pdfFilePath = jarPath + fileName;
			WordConvertUtil.docx2PDF(docxFilePath, pdfFilePath);
			pdfFile = new File(pdfFilePath);
			fileInputStream = new FileInputStream(pdfFile);
			MultipartFile multipartFile = new MockMultipartFile("file", pdfFile.getName(),
					ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);

			Result<FileInfoVo> result = fileOperatorFeignService.uploadFile(multipartFile);
			if (result.isSuccess()) {
				FileInfoVo fileInfoVo = result.getData();
				if (fileInfoVo != null) {
					updateEntity.setFileId(NumberUtils.toLong(fileInfoVo.getId()));
					updateEntity.setFileName(fileInfoVo.getFileName());
					updateEntity.setFileUrl(fileInfoVo.getFileUrl());
					updateEntity.updateById();
				}
			} else {
				log.error("项目变更批复件批复上传文件服务器失败。");
				throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
			}
		} catch (Exception e) {
			log.error("生成项目变更批复件异常.", e);
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		} finally {
			IOUtils.closeQuietly(fileInputStream);
			if (pdfFile != null && pdfFile.exists()) {
				pdfFile.delete();
			}
		}
	}

	private String createApproveWordFile(SrpmsProjectUpdate updateEntity) {

		String fileUrl = null;

		SrpmsProject projectEntity = projectService.getById(updateEntity.getProjectId());
		// 1.构造数据
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("applyYear", projectEntity.getApplyYear());
		dataMap.put("deptName", JSONObject.parseObject(projectEntity.getLeadDept()).getString("deptName"));
		dataMap.put("projectName", projectEntity.getProjectName());
		dataMap.put("projectNum", projectEntity.getProjectNum());
		dataMap.put("leadPersonName", JSONObject.parseObject(projectEntity.getLeadPerson()).getString("name"));
		dataMap.put("taskName", updateEntity.getProjectName());
		dataMap.put("now", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));

		switch (updateEntity.getUpdateType()) {
		case SrpmsConstant.MODIFY_MODIFY_TYPE_10:
			fileUrl = cerateWordMember(dataMap, updateEntity, projectEntity);
			break;
		case SrpmsConstant.MODIFY_MODIFY_TYPE_20:
			fileUrl = cerateWordLeadPerson(dataMap, updateEntity, projectEntity);
			break;
		case SrpmsConstant.MODIFY_MODIFY_TYPE_30:
			fileUrl = cerateWordContent(dataMap, updateEntity, projectEntity);
			break;
		case SrpmsConstant.MODIFY_MODIFY_TYPE_40:
			fileUrl = cerateWordBudget(dataMap, updateEntity, projectEntity);
			break;
		case SrpmsConstant.MODIFY_MODIFY_TYPE_50:
			fileUrl = cerateWordStatus(dataMap, updateEntity, projectEntity);
			break;
		default:
			log.error("项目变更类型不存在");
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		}

		return fileUrl;
	}

	private String cerateWordStatus(Map<String, Object> dataMap, SrpmsProjectUpdate updateEntity,
			SrpmsProject projectEntity) {

		String fileUrl = null;

		try {

			dataMap.put("oldValue",
					sysDictService.selectValueByCode(SysDictEnums.PRO_STAT, updateEntity.getOldValue()));

			dataMap.put("newValue",
					sysDictService.selectValueByCode(SysDictEnums.PRO_STAT, updateEntity.getNewValue()));

			// 2.生成word文件
			String filename = "项目变更_状态变更批复_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
					+ RandomUtils.nextInt(0, 9999) + ".docx";
			fileUrl = wordExportService.exportWord("template_update_approve_status", dataMap, filename);
		} catch (Exception e) {
			log.error("生成批复件异常。", e);
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		}
		return fileUrl;
	}

	private String cerateWordLeadPerson(Map<String, Object> dataMap, SrpmsProjectUpdate updateEntity,
			SrpmsProject projectEntity) {

		String fileUrl = null;

		long oldClobId = Long.parseLong(updateEntity.getOldValue());
		SrpmsCommonNclob oldClobEntity = nclobService.getById(oldClobId);
		JSONArray oldValue = JSONArray.parseArray(oldClobEntity.getContent());

		long clobId = Long.parseLong(updateEntity.getNewValue());
		SrpmsCommonNclob clobEntity = nclobService.getById(clobId);
		JSONArray newValue = JSONArray.parseArray(clobEntity.getContent());

		try {

			dataMap.put("oldName", oldValue.getJSONObject(0).getString("name"));

			dataMap.put("newName", newValue.getJSONObject(0).getString("name"));

			// 2.生成word文件
			String filename = "项目变更_负责人变更批复_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
					+ RandomUtils.nextInt(0, 9999) + ".docx";
			fileUrl = wordExportService.exportWord("template_update_approve_leadPerson", dataMap, filename);
		} catch (Exception e) {
			log.error("生成批复件异常。", e);
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		}
		return fileUrl;
	}

	private String cerateWordMember(Map<String, Object> dataMap, SrpmsProjectUpdate update,
			SrpmsProject projectEntity) {

		String fileUrl = null;

		try {

			long oldClobId = Long.parseLong(update.getOldValue());
			SrpmsCommonNclob oldClobEntity = nclobService.getById(oldClobId);
			JSONObject oldValue = JSONObject.parseObject(oldClobEntity.getContent());

			long clobId = Long.parseLong(update.getNewValue());
			SrpmsCommonNclob clobEntity = nclobService.getById(clobId);
			JSONObject newValue = JSONObject.parseObject(clobEntity.getContent());

			JSONArray changedList = updateMenberService.getMemberChangedList(oldValue, newValue, projectEntity);

			dataMap.put("changedList", changedList);

			// 2.生成word文件
			String filename = "项目变更_参与人员变更批复_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
					+ RandomUtils.nextInt(0, 9999) + ".docx";
			fileUrl = wordExportService.exportWord("template_update_approve_person", dataMap, filename);
		} catch (Exception e) {
			log.error("生成批复件异常。", e);
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		}
		return fileUrl;
	}

	private String cerateWordContent(Map<String, Object> dataMap, SrpmsProjectUpdate updateEntity,
			SrpmsProject projectEntity) {

		String fileUrl = null;

		long oldClobId = Long.parseLong(updateEntity.getOldValue());
		SrpmsCommonNclob oldClobEntity = nclobService.getById(oldClobId);
		JSONArray oldContentList = JSONArray.parseArray(oldClobEntity.getContent());

		long newClobId = Long.parseLong(updateEntity.getNewValue());
		SrpmsCommonNclob newClobEntity = nclobService.getById(newClobId);
		JSONArray contentList = JSONArray.parseArray(newClobEntity.getContent());

		try {

			JSONArray newContentList = new JSONArray();
			for (int i = 0; i < contentList.size(); i++) {
				JSONObject item = contentList.getJSONObject(i);
				if (item.getString("updateFlg") != null && "1".equals(item.getString("updateFlg"))) {

					JSONObject newItem = new JSONObject();

					for (int j = 0 ; j  < oldContentList.size(); j ++) {
						if (oldContentList.getJSONObject(j).getString("key").equals(item.getString("key"))) {
							newItem.put("oldValue", WordConvertUtil.htmlToText(oldContentList.getJSONObject(j).getString("value")));
						}
					}
					newItem.put("value", WordConvertUtil.htmlToText(item.getString("value")));
					newItem.put("name", WordConvertUtil.htmlToText(item.getString("name")));
					newContentList.add(newItem);
				}

			}
			log.info("内容变更数据为" + newContentList.toJSONString());
			dataMap.put("contentList", newContentList);

			log.info("文件导出数据为" + newContentList.toJSONString());
			// 2.生成word文件
			String filename = "项目变更_内容变更批复_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
					+ RandomUtils.nextInt(0, 9999) + ".docx";
			fileUrl = wordExportService.exportWord("template_update_approve_content", dataMap, filename);
		} catch (Exception e) {
			log.error("生成批复件异常。", e);
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		}
		return fileUrl;
	}

	private String cerateWordBudget(Map<String, Object> dataMap, SrpmsProjectUpdate updateEntity,
			SrpmsProject projectEntity) {

		String fileUrl = null;

		JSONArray newArray = projectUpdateBudgetService.select(projectEntity);

		try {

			if ("".equals(projectEntity.getProjectFlag())) {
				dataMap.put("budgetActionDuring", "");
			} else {
				StringBuffer sb = new StringBuffer();
				sb.append("预算执行周期：");
				sb.append(newArray.getJSONObject(0).getString("budgetActionDateStart"));
				sb.append("  至  ");
				sb.append(newArray.getJSONObject(0).getString("budgetActionDateEnd"));
				dataMap.put("budgetActionDuring", sb.toString());
			}

			dataMap.put("budgetList",
					projectUpdateBudgetService.getChangedbudgetList(updateEntity, projectEntity));
			// 2.生成word文件
			String filename = "项目变更_预算变更批复_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
					+ RandomUtils.nextInt(0, 9999) + ".docx";
			fileUrl = wordExportService.exportWord("template_update_approve_budget", dataMap, filename);

		} catch (Exception e) {
			log.error("生成批复件异常。", e);
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		}
		return fileUrl;
	}
}