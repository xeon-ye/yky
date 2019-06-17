package com.deloitte.platform.basic.bpmservice.controller.manager;

import com.deloitte.platform.api.bpmservice.vo.BpmPositionVo;
import com.deloitte.platform.basic.bpmservice.controller.manager.model.Status;
import com.deloitte.platform.basic.bpmservice.controller.manager.model.ToWeb;
import com.deloitte.platform.basic.bpmservice.exception.BpmErrorType;
import com.deloitte.platform.basic.bpmservice.util.Utils;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.explorer.util.XmlUtil;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程模型操作
 *
 *
 */
@RestController
@RequestMapping("/models")
public class ModelerController implements RestServiceController<Model, String>
{

	@Autowired
	RepositoryService repositoryService;

	@Autowired
	ObjectMapper objectMapper;

	/**
	 * 新建一个空模型
	 *
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@PostMapping("newModel")
	public Object newModel() throws UnsupportedEncodingException
	{
		// 初始化一个空模型
		Model model = repositoryService.newModel();

		// 设置一些默认信息
		String name = Utils.covertToUTF("new_process");
		String description = "";
		int revision = 1;
		String key = "process";

		ObjectNode modelNode = objectMapper.createObjectNode();
		modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
		modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
		modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

		model.setName(name);
		model.setKey(key);
		model.setMetaInfo(modelNode.toString());

		repositoryService.saveModel(model);
		String id = model.getId();

		// 完善ModelEditorSource
		ObjectNode editorNode = objectMapper.createObjectNode();
		editorNode.put("id", "canvas");
		editorNode.put("resourceId", "canvas");
		ObjectNode stencilSetNode = objectMapper.createObjectNode();
		stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
		editorNode.put("stencilset", stencilSetNode);
		repositoryService.addModelEditorSource(id, editorNode.toString().getBytes("utf-8"));
		return ToWeb.buildResult().redirectUrl("/modeler.html?modelId=" + id);
	}

	/**
	 * 发布模型为流程定义
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("{id}/deployment")
	public Object deploy(@PathVariable("id") String id) throws Exception
	{

		// 获取模型
		Model modelData = repositoryService.getModel(id);
		byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());

		if (bytes == null)
		{
			return ToWeb.buildResult().status(Status.FAIL).msg("模型数据为空，请先设计流程并成功保存，再进行发布。");
		}

		JsonNode modelNode = new ObjectMapper().readTree(bytes);

		BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
		if (model.getProcesses().size() == 0)
		{
			return ToWeb.buildResult().status(Status.FAIL).msg("数据模型不符要求，请至少设计一条主线流程。");
		}
		byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

		// 发布流程
		String processName = modelData.getName() + ".bpmn20.xml";
		Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes, "UTF-8")).deploy();
		modelData.setDeploymentId(deployment.getId());
		repositoryService.saveModel(modelData);

		return ToWeb.buildResult().refresh();
	}

	@GetMapping("{id}/export")
	public void export(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		BufferedOutputStream bos = null;

		try
		{
			// 获取模型
			Model modelData = repositoryService.getModel(id);
			byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());

			JsonNode modelNode = new ObjectMapper().readTree(bytes);

			BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);

			byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

			// 封装输出流
			bos = new BufferedOutputStream(response.getOutputStream());
			bos.write(bpmnBytes);// 写入流

			String filename = encodeDownloadFilename(modelData.getName() + ".bpmn20.xml", request.getHeader("User-Agent"));
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			response.flushBuffer();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			bos.flush();
			bos.close();
		}

	}

	@GetMapping("{id}/approve")
	public Object configApproveMatrix(@PathVariable("id") String id){
		// 初始化一个空模型
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Model model = repositoryService.createModelQuery().modelId(id).singleResult();
		if(model.getDeploymentId()==null||"".equals(model.getDeploymentId())){
			return ToWeb.buildResult().setObjData("请发布流程后，再配置审批矩阵").setRows(ToWeb.Rows.buildRows().setList(list));
		}
//		try{
//			byte[] bytes = repositoryService.getModelEditorSource(model.getId());
//			String processDefineKey = "";
//			JsonNode modelNode = new ObjectMapper().readTree(bytes);
//			processDefineKey = modelNode.get("properties").get("process_id").asText();
//			//ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefineKey).latestVersion().singleResult();
//
			ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(model.getDeploymentId()).singleResult();
			ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(pd.getId());

			//筛选出人工任务
			if (CollectionUtils.isNotEmpty(processDefinition.getActivities()))
			{
				for(ActivityImpl activityImpl : processDefinition.getActivities())
				{
					//提交人的环节需要排除掉
					if("userTask".equals(activityImpl.getProperty("type")) && !"start".equals(activityImpl.getId()))
					{
						TaskDefinition taskDefinition =(TaskDefinition) activityImpl.getProperty("taskDefinition");
						String activityId  = activityImpl.getId();
						String activityName = taskDefinition.getNameExpression().getExpressionText();
						Map<String,String> map = new HashMap<String,String>();
						map.put("activityId",activityId);
						map.put("activityName",activityName);
						list.add(map);
					}
				}
			}
//		}catch (IOException e){
//			e.printStackTrace();
//		}
		model.setKey(pd.getKey());
		return ToWeb.buildResult().setRows(ToWeb.Rows.buildRows().setList(list)).setObjData(model);
	}

	private static String encodeDownloadFilename(String filename, String agent) throws IOException
	{
		if (agent == null)
		{
			return "can't support";
		}
		if (agent.contains("Firefox"))
		{ // 火狐浏览器
			filename = "=?UTF-8?B?" + Base64.encodeBase64String(filename.getBytes("utf-8")) + "?="; // codec中提供的编码方式
			filename = filename.replaceAll("\r\n", "");
		}
		else
		{ // IE及其他浏览器
			filename = URLEncoder.encode(filename, "utf-8");
			filename = filename.replace("+", " ");
		}
		return filename;
	}

	@Override
	public Object getOne(@PathVariable("id") String id)
	{
		Model model = repositoryService.createModelQuery().modelId(id).singleResult();
		return ToWeb.buildResult().setObjData(model);
	}

	@GetMapping("/query/list")
	public Object query(@RequestParam(value = "modelName", required = false) String modelName,@RequestParam(value = "rowSize", defaultValue = "1000", required = false) Integer rowSize, @RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
		String name = modelName;
		if(name == null){
			name = "%%";
		}else{
			name = "%"+modelName+"%";
		}
		List<Model> list = repositoryService.createModelQuery().modelNameLike(name).listPage(rowSize * (page - 1), rowSize);
		long count = repositoryService.createModelQuery().modelNameLike(name).count();
		return ToWeb.buildResult().setRows(ToWeb.Rows.buildRows().setCurrent(page).setTotalPages((int) (count / rowSize + 1)).setTotalRows(count).setList(list).setRowSize(rowSize));
	}

	@Override
	public Object getList(@RequestParam(value = "rowSize", defaultValue = "1000", required = false) Integer rowSize, @RequestParam(value = "page", defaultValue = "1", required = false) Integer page)
	{
		List<Model> list = repositoryService.createModelQuery().listPage(rowSize * (page - 1), rowSize);
		long count = repositoryService.createModelQuery().count();

		return ToWeb.buildResult().setRows(ToWeb.Rows.buildRows().setCurrent(page).setTotalPages((int) (count / rowSize + 1)).setTotalRows(count).setList(list).setRowSize(rowSize));
	}

	@Override
	public Object deleteOne(@PathVariable("id") String id)
	{
		repositoryService.deleteModel(id);
		return ToWeb.buildResult().refresh();
	}

	@Override
	public Object postOne(@RequestBody Model entity)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Object putOne(@PathVariable("id") String s, @RequestBody Model entity)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Object patchOne(@PathVariable("id") String s, @RequestBody Model entity)
	{
		throw new UnsupportedOperationException();
	}

	@PostMapping("upload")
	public void deployUploadedFile(@RequestParam("file") MultipartFile uploadfile, MultipartHttpServletRequest request, HttpServletResponse response)
	{
		List<MultipartFile> list1 = request.getFiles("file");
		list1.stream().forEach((f) ->
		{
			uploadFile(f);
		});
		try
		{
			response.sendRedirect("/index.html");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void uploadFile(MultipartFile uploadfile)
	{
		InputStreamReader in = null;
		try
		{
			try
			{
				boolean validFile = false;
				String fileName = uploadfile.getOriginalFilename();
				if (fileName.endsWith(".bpmn20.xml") || fileName.endsWith(".bpmn"))
				{
					validFile = true;

					XMLInputFactory xif = XmlUtil.createSafeXmlInputFactory();
					in = new InputStreamReader(new ByteArrayInputStream(uploadfile.getBytes()), "UTF-8");
					XMLStreamReader xtr = xif.createXMLStreamReader(in);
					BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

					if (bpmnModel.getMainProcess() == null || bpmnModel.getMainProcess().getId() == null)
					{
						// notificationManager.showErrorNotification(Messages.MODEL_IMPORT_FAILED,
						// i18nManager.getMessage(Messages.MODEL_IMPORT_INVALID_BPMN_EXPLANATION));
						System.out.println("err1");
					}
					else
					{

						if (bpmnModel.getLocationMap().isEmpty())
						{
							// notificationManager.showErrorNotification(Messages.MODEL_IMPORT_INVALID_BPMNDI,
							// i18nManager.getMessage(Messages.MODEL_IMPORT_INVALID_BPMNDI_EXPLANATION));
							System.out.println("err2");
						}
						else
						{

							String processName = null;
							if (StringUtils.isNotEmpty(bpmnModel.getMainProcess().getName()))
							{
								processName = bpmnModel.getMainProcess().getName();
							}
							else
							{
								processName = bpmnModel.getMainProcess().getId();
							}
							Model modelData;
							modelData = repositoryService.newModel();
							ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
							modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processName);
							modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
							modelData.setMetaInfo(modelObjectNode.toString());
							modelData.setName(processName);

							repositoryService.saveModel(modelData);

							BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
							ObjectNode editorNode = jsonConverter.convertToJson(bpmnModel);

							repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
						}
					}
				}
				else
				{
					// notificationManager.showErrorNotification(Messages.MODEL_IMPORT_INVALID_FILE,
					// i18nManager.getMessage(Messages.MODEL_IMPORT_INVALID_FILE_EXPLANATION));
					System.out.println("err3");
				}
			}
			catch (Exception e)
			{
				String errorMsg = e.getMessage().replace(System.getProperty("line.separator"), "<br/>");
				// notificationManager.showErrorNotification(Messages.MODEL_IMPORT_FAILED,
				// errorMsg);
				System.out.println("err4");
			}
		}
		finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				}
				catch (IOException e)
				{
					// notificationManager.showErrorNotification("Server-side
					// error",
					// e.getMessage());
					System.out.println("err5");
				}
			}
		}
	}



}
