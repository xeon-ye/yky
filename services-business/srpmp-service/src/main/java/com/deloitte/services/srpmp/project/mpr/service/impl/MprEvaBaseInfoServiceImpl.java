package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.*;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaBaseInfoQueryForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.*;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.srpmp.common.constant.MprConstant;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.enums.*;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.util.JSONConvert;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyInnBcoo;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnBcooService;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectAttachment;
import com.deloitte.services.srpmp.project.base.entity.SrpmsVoucher;
import com.deloitte.services.srpmp.project.base.service.*;
import com.deloitte.services.srpmp.project.base.service.impl.SrpmsProjectBpmServiceImpl;
import com.deloitte.services.srpmp.project.mpr.entity.*;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaBaseInfoMapper;
import com.deloitte.services.srpmp.project.mpr.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskInn;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskInnService;
import com.deloitte.services.srpmp.project.update.service.ISrpmsProjectUpdateService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;

import java.io.*;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprEvaBaseInfo服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class MprEvaBaseInfoServiceImpl extends ServiceImpl<MprEvaBaseInfoMapper, MprEvaBaseInfo> implements IMprEvaBaseInfoService {

    @Autowired
    private IMprEvaFundsBudgetService fundsBudgetService;

    @Autowired
    private IMprEvaTaskIndicatorService taskIndicatorService;

    @Autowired
    private IMprEvaOutcomeService outcomeService;

    @Autowired
    private IMprEvaTheoTechProTableService theoTechProTableService;

    @Autowired
    private IMprEvaTransTableService transTableService;

    @Autowired
    private IMprEvaPromotionTableService promotionTableService;

    @Autowired
    private IMprEvaTreaPopTableService treaPopTableService;

    @Autowired
    private IMprEvaTreaTableService treaTableService;

    @Autowired
    private IMprEvaMonoTableService monoTableService;

    @Autowired
    private IMprEvaPatentTableService patentTableService;

    @Autowired
    private IMprEvaMedicineTableService medicineTableService;

    @Autowired
    private IMprEvaTechStanTableService techStanTableService;

    @Autowired
    private IMprEvaTechRewardTableService techRewardTableService;

    @Autowired
    private IMprEvaResearchTaskTableService researchTaskTableService;

    @Autowired
    private IMprEvaAcadConfTableService acadConfTableService;

    @Autowired
    private IMprAcadPostTableService acadPostTableService;

    @Autowired
    private IMprJouPostTableService jouPostTableService;

    @Autowired
    private IMprEvaHighLevelTableService highLevelTableService;

    @Autowired
    private IMprEvaCoopResultTableService coopResultTableService;

    @Autowired
    private ExcelExportImpl excelExport;

    @Autowired
    private ISrpmsProjectTaskService taskService;

    @Autowired
    private ISrpmsProjectService projectService;

    @Autowired
    private ISrpmsProjectDeptJoinService deptJoinService;

    @Autowired
    private ISrpmsProjectUpdateService srpmsProjectUpdateService;

    @Autowired
    private ISysDictService dictService;

    @Autowired
    private ISrpmsProjectTaskInnService projectTaskInnService;

    @Autowired
    private ISrpmsProjectApplyInnBcooService projectApplyInnBcooService;

    @Autowired
    private ISrpmsProjectPersonJoinService personJoinService;

    @Autowired
    private ISrpmsProjectAttachmentService attachmentService;

    @Autowired
    private ISrpmsVoucherService voucherService;

    @Autowired
    private SrpmsProjectBpmServiceImpl bpmService;

    @Autowired
    private MprEvaBaseInfoMapper mprEvaBaseInfoMapper;

    @Autowired
    private ISrpmsProjectFlowService flowService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdateAnnexOne(MprSaveOrUpdateAnnexOneForm saveOrUpdateAnnexOneForm, UserVo user, DeptVo deptVo) {
        if (saveOrUpdateAnnexOneForm == null || saveOrUpdateAnnexOneForm.getBaseInfoForm() == null) {
            throw new BaseException(SrpmsErrorType.PARAM_NULL);
        }
        Long projectId = saveOrUpdateAnnexOneForm.getBaseInfoForm().getId();
        if (null == projectId) {
            throw new BaseException(SrpmsErrorType.PROJECT_ID_NULL);
        }
        SrpmsProject project = projectService.getById(projectId);
        if (null == project) {
            throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
        }
        MprEvaBaseInfo baseInfo = this.getMprBaseInfo(projectId);
        //删除之前的数据
        String processStatus = EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode();
        if (null != baseInfo) {
            processStatus = baseInfo.getProcessStatus();
            delAnnexOneInfo(projectId);
        }
        //保存数据
        saveAnnexOneInfo(saveOrUpdateAnnexOneForm, user, deptVo, processStatus, project);
    }

    private MprEvaBaseInfo copyProject2BaseInfo(SrpmsProject project) {
        if (null == project) {
            return null;
        }
        MprEvaBaseInfo baseInfo = new MprEvaBaseInfo();
        baseInfo.setId(project.getId());
        baseInfo.setProjectNo(project.getProjectNum());
        baseInfo.setProjectName(project.getProjectName());
        baseInfo.setExecutionStartTime(project.getProjectActionDateStart());
        baseInfo.setExecutionEndTime(project.getProjectActionDateEnd());
        baseInfo.setProcessStatus(EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
        for (int j = 0; j < ProjectCategoryEnums.values().length; j ++) {
            if (StringUtils.equals(project.getProjectCategory(), ProjectCategoryEnums.values()[j].getCode())) {
                baseInfo.setProjectCategory(ProjectCategoryEnums.values()[j].getDesc());
            }
        }
        // 国际合作单位信息
        List<SrpmsProjectDeptJoinVo> deptJoinVoList = deptJoinService.queryDeptJoinListByJoinWay(DeptJoinWayEnums.TASK_INNOVATE_COPPDEPT, project.getId());
        // 联合申请单位
        List<SrpmsProjectDeptJoinVo> unitDeptJoinVoList = deptJoinService.queryDeptJoinListByJoinWay(DeptJoinWayEnums.TASK_INNOVATE_UNIT, project.getId());
        deptJoinVoList.addAll(unitDeptJoinVoList);
        if (CollectionUtils.isNotEmpty(deptJoinVoList)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (SrpmsProjectDeptJoinVo deptJoinVo : deptJoinVoList) {
                stringBuilder.append(deptJoinVo.getDeptName());
                stringBuilder.append(" ");
            }
            String deptJoinStr = StringUtils.substring(stringBuilder.toString(), 0, stringBuilder.length() - 1);
            baseInfo.setTakeUnit(deptJoinStr);
        }

        JSONObject leadPersonJson = JSONObject.parseObject(project.getLeadPerson());
        JSONObject bothTopExpertPerson = JSONObject.parseObject(project.getBothTopExpertPerson());
        JSONObject leadDept = JSONObject.parseObject(project.getLeadDept());
        if (null != leadDept) {
            baseInfo.setLeadUnit(leadDept.getString("deptName"));
        }
        if (null != leadPersonJson) {
            baseInfo.setChiefSpecialist(leadPersonJson.getString("name"));
        }
        if (null != bothTopExpertPerson) {
            baseInfo.setJointChiefSpecialist(bothTopExpertPerson.getString("name"));
        }
        return baseInfo;
    }

    @Override
    public MprSaveOrUpdateAnnexOneVo getAnnexOne(Long projectId) {
        MprSaveOrUpdateAnnexOneVo annexOneVo = new MprSaveOrUpdateAnnexOneVo();

        //基本情况信息
        MprEvaBaseInfo baseInfo = this.getMprBaseInfo(projectId);
        if (null == baseInfo) {
            SrpmsProject project = projectService.getById(projectId);
            baseInfo = copyProject2BaseInfo(project);
        }
        MprEvaBaseInfoVo baseInfoVo = new BeanUtils<MprEvaBaseInfoVo>().copyObj(baseInfo, MprEvaBaseInfoVo.class);
        SrpmsProjectApplyInnBcoo bcoo = projectApplyInnBcooService.getById(projectId);
        if (null != bcoo && StringUtils.isNotBlank(bcoo.getActiveType())) {
            List<String> codeList = JSON.parseArray(bcoo.getActiveType(), String.class);
            List<String> codeDesc = Lists.newArrayList();
            for (String code : codeList) {
                String activeTypeName = dictService.selectValueByCode(SysDictEnums.PRO_ACTIVE_TYPE, code);
                codeDesc.add(activeTypeName);
            }
            baseInfoVo.setProjectClassification(JSON.toJSONString(codeDesc));
        }

        //目标
        SrpmsProjectTaskInn taskInn = projectTaskInnService.getById(projectId);
        if (null != taskInn && StringUtils.isNotBlank(taskInn.getTaskMasterMethod())) {
            baseInfoVo.setProjectObjectives(taskInn.getTaskMasterMethod());
        }

        //任务列表
        List<SrpmsProjectTaskVo> projectTaskVoList = taskService.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_TASK_DECOMPOSITION, projectId);
        if (CollectionUtils.isNotEmpty(projectTaskVoList)) {
            annexOneVo.setProjectTaskVoList(projectTaskVoList);
        }

        //主要参与人员
        int memberAllCount = 0; //总人数
//        List<SrpmsProjectUpdateMenber> updateMenberList = srpmsProjectUpdateMenberService.selectLastest(projectId);
//        List<SrpmsProjectPersonJoinVo> personJoinList = Lists.newArrayList();
//        for (SrpmsProjectUpdateMenber menber : updateMenberList) {
//            memberAllCount ++;
//            SrpmsProjectPersonJoinVo personJoinVo = JSONObject.parseObject(menber.getJoinPerson(), SrpmsProjectPersonJoinVo.class);
//            if (null != personJoinVo.getBirthDate()) {
//                personJoinVo.setBirthDateString(personJoinVo.getBirthDate().format(formatter));
//            }
//            QueryWrapper<SrpmsProjectUpdate> updateQueryWrapper = new QueryWrapper<>();
//            updateQueryWrapper.eq(SrpmsProjectUpdate.UPDATE_NUMBER, menber.getUpdateNumber());
//            SrpmsProjectUpdate projectUpdate = srpmsProjectUpdateService.getOne(updateQueryWrapper);
//            if (null != projectUpdate) {
//                personJoinVo.setIsUpdate("是");
//                personJoinVo.setUpdateReason(projectUpdate.getUpdateReason());
//            } else {
//                personJoinVo.setIsUpdate("否");
//                personJoinVo.setUpdateReason("");
//            }
//            personJoinList.add(personJoinVo);
//        }

        annexOneVo.setBaseInfoVo(baseInfoVo);

        List<SrpmsProjectPersonJoinVo> personJoinList = personJoinService.queryPersonJoinListByJoinWay(PersonJoinWayEnums.TASK_MAIN_MEMBER, projectId);
        if (CollectionUtils.isNotEmpty(personJoinList)) {
            for (SrpmsProjectPersonJoinVo vo : personJoinList) {
                if (null != vo.getBirthDate()) {
                    vo.setBirthDateString(vo.getBirthDate());
                }
                if (StringUtils.equals("1", vo.getChangeFlag())) {
                    vo.setIsUpdate("是");
                    vo.setUpdateReason(vo.getChangeReason());
                } else {
                    vo.setIsUpdate("否");
                }
            }
            annexOneVo.setPersonJoinVoList(personJoinList);
            memberAllCount = personJoinList.size();
        }
        baseInfoVo.setJobQuantity(String.valueOf(memberAllCount));

        //经费使用情况
        QueryWrapper<MprEvaFundsBudget> fundsBudgetQueryWrapper = new QueryWrapper<>();
        fundsBudgetQueryWrapper.eq(MprEvaFundsBudget.ID, projectId);
        List<MprEvaFundsBudget> fundsBudgetList = fundsBudgetService.list(fundsBudgetQueryWrapper);
        List<MprEvaFundsBudgetVo> fundsBudgetVoList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(fundsBudgetList)) {
            fundsBudgetVoList = new BeanUtils<MprEvaFundsBudgetVo>().copyObjs(fundsBudgetList, MprEvaFundsBudgetVo.class);

        } else {
            //未保存过取任务书
            for (int beginYear = 2016; beginYear <= 2018; beginYear++) {
                for (SrpmsProjectTaskVo taskVo : projectTaskVoList) {
                    MprEvaFundsBudgetVo budgetVo = new MprEvaFundsBudgetVo();
                    budgetVo.setYear(String.valueOf(beginYear));
                    budgetVo.setId(taskVo.getProjectId());
                    budgetVo.setTaskName(taskVo.getTaskName());
                    fundsBudgetVoList.add(budgetVo);
                }
            }
        }
        annexOneVo.setFundsBudgetVoList(fundsBudgetVoList);

        //考核指标完成情况信息
        QueryWrapper<MprEvaTaskIndicator> taskIndicatorQueryWrapper = new QueryWrapper<>();
        taskIndicatorQueryWrapper.eq(MprEvaTaskIndicator.ID, projectId);
        List<MprEvaTaskIndicator> taskIndicatorList = taskIndicatorService.list(taskIndicatorQueryWrapper);
        if (CollectionUtils.isEmpty(taskIndicatorList)) {
            if (CollectionUtils.isNotEmpty(projectTaskVoList)) {
                List<MprEvaTaskIndicatorVo> taskDetailVoList = Lists.newArrayList();
                for (SrpmsProjectTaskVo taskVo : projectTaskVoList) {
                    MprEvaTaskIndicatorVo vo = new MprEvaTaskIndicatorVo();
                    vo.setTaskName(taskVo.getTaskName());
                    vo.setIndicatorName(taskVo.getMediumTarget());
                    taskDetailVoList.add(vo);
                }
                annexOneVo.setTaskIndicatorVoList(taskDetailVoList);
            }
        } else {
            List<MprEvaTaskIndicatorVo> taskDetailVoList = new BeanUtils<MprEvaTaskIndicatorVo>().copyObjs(taskIndicatorList, MprEvaTaskIndicatorVo.class);
            annexOneVo.setTaskIndicatorVoList(taskDetailVoList);
        }

        //代表性成果
        QueryWrapper<MprEvaOutcome> outcomeQueryWrapper = new QueryWrapper<>();
        outcomeQueryWrapper.eq(MprEvaOutcome.ID, projectId);
        List<MprEvaOutcome> outcomeList = outcomeService.list(outcomeQueryWrapper);
        if (CollectionUtils.isNotEmpty(outcomeList)) {
            List<MprEvaOutcomeVo> outcomeVoList = new BeanUtils<MprEvaOutcomeVo>().copyObjs(outcomeList, MprEvaOutcomeVo.class);
            annexOneVo.setOutcomeVoList(outcomeVoList);
        }

        //经济社会效益
        //查询新理论、新技术、新产品等情况信息
        QueryWrapper<MprEvaTheoTechProTable> theoTechProTableQueryWrapper = new QueryWrapper<>();
        theoTechProTableQueryWrapper.eq(MprEvaTheoTechProTable.ID, projectId);
        List<MprEvaTheoTechProTable> theoTechProTableList = theoTechProTableService.list(theoTechProTableQueryWrapper);
        if (CollectionUtils.isNotEmpty(theoTechProTableList)) {
            List<MprEvaTheoTechProTableVo> theoTechProTableVoList = new BeanUtils<MprEvaTheoTechProTableVo>().copyObjs(theoTechProTableList, MprEvaTheoTechProTableVo.class);
            annexOneVo.setTheoTechProTableVoList(theoTechProTableVoList);
        }

        //查询成果转化情况信息
        QueryWrapper<MprEvaTransTable> transTableQueryWrapper = new QueryWrapper<>();
        transTableQueryWrapper.eq(MprEvaTransTable.ID, projectId);
        List<MprEvaTransTable> transTableList = transTableService.list(transTableQueryWrapper);
        if (CollectionUtils.isNotEmpty(transTableList)) {
            List<MprEvaTransTableVo> transTableVoList = new BeanUtils<MprEvaTransTableVo>().copyObjs(transTableList, MprEvaTransTableVo.class);
            annexOneVo.setTransTableVoList(transTableVoList);
        }

        //查询示范推广服务情况信息
        QueryWrapper<MprEvaPromotionTable> promotionTableQueryWrapper = new QueryWrapper<>();
        promotionTableQueryWrapper.eq(MprEvaPromotionTable.ID, projectId);
        List<MprEvaPromotionTable> promotionTableList = promotionTableService.list(promotionTableQueryWrapper);
        if (CollectionUtils.isNotEmpty(promotionTableList)) {
            List<MprEvaPromotionTableVo> promotionTableVoList = Lists.newArrayList();
            for (MprEvaPromotionTable table : promotionTableList) {
                MprEvaPromotionTableVo vo = new BeanUtils<MprEvaPromotionTableVo>().copyObj(table, MprEvaPromotionTableVo.class);
                if (StringUtils.isNotBlank(table.getPromotionTimes())) {
                    String[] arr = table.getPromotionTimes().split(",");
                    if (arr.length > 0) {
                        vo.setPromotionTimes(arr[0]);
                    }
                    if (arr.length > 1) {
                        vo.setPromotionExt(arr[1]);
                    }
                }
                promotionTableVoList.add(vo);
            }
            annexOneVo.setPromotionTableVoList(promotionTableVoList);
        }

        //查询论文发表情况表-科普信息
        QueryWrapper<MprEvaTreaPopTable> treaPopTableQueryWrapper = new QueryWrapper<>();
        treaPopTableQueryWrapper.eq(MprEvaTreaPopTable.ID, projectId);
        List<MprEvaTreaPopTable> treaPopTableList = treaPopTableService.list(treaPopTableQueryWrapper);
        if (CollectionUtils.isNotEmpty(treaPopTableList)) {
            List<MprEvaTreaPopTableVo> treaPopTableVoList = new BeanUtils<MprEvaTreaPopTableVo>().copyObjs(treaPopTableList, MprEvaTreaPopTableVo.class);
            annexOneVo.setTreaPopTableVoList(treaPopTableVoList);
        }

        //查询论文发表情况信息
        QueryWrapper<MprEvaTreaTable> treaTableQueryWrapper = new QueryWrapper<>();
        treaTableQueryWrapper.eq(MprEvaTreaTable.ID, projectId);
        List<MprEvaTreaTable> treaTableList = treaTableService.list(treaTableQueryWrapper);
        if (CollectionUtils.isNotEmpty(treaTableList)) {
            List<MprEvaTreaTableVo> treaTableVoList = new BeanUtils<MprEvaTreaTableVo>().copyObjs(treaTableList, MprEvaTreaTableVo.class);
            annexOneVo.setTreaTableVoList(treaTableVoList);
        }

        //查询专著/教材发表情况信息
        QueryWrapper<MprEvaMonoTable> monoTableQueryWrapper = new QueryWrapper<>();
        monoTableQueryWrapper.eq(MprEvaMonoTable.ID, projectId);
        List<MprEvaMonoTable> monoTableList = monoTableService.list(monoTableQueryWrapper);
        if (CollectionUtils.isNotEmpty(monoTableList)) {
            List<MprEvaMonoTableVo> monoTableVoList = new BeanUtils<MprEvaMonoTableVo>().copyObjs(monoTableList, MprEvaMonoTableVo.class);
            for (int i = 0; i < monoTableVoList.size(); i++) {
                if (JSONObject.isValidArray(monoTableList.get(i).getCompletePerson())) {
                    monoTableVoList.get(i).setCompletePerson(JSONArray.parseArray(monoTableList.get(i).getCompletePerson()));
                } else {
                    JSONArray nameArray = new JSONArray();
                    nameArray.add(monoTableList.get(i).getCompletePerson());
                    monoTableVoList.get(i).setCompletePerson(nameArray);
                }
            }
            annexOneVo.setMonoTableVoList(monoTableVoList);
        }

        //查询专利申请授权情况信息
        QueryWrapper<MprEvaPatentTable> patentTableQueryWrapper = new QueryWrapper<>();
        patentTableQueryWrapper.eq(MprEvaPatentTable.ID, projectId);
        List<MprEvaPatentTable> patentTableList = patentTableService.list(patentTableQueryWrapper);
        if (CollectionUtils.isNotEmpty(patentTableList)) {
            List<MprEvaPatentTableVo> patentTableVoList = new BeanUtils<MprEvaPatentTableVo>().copyObjs(patentTableList, MprEvaPatentTableVo.class);
            for (int i = 0; i < patentTableVoList.size(); i++) {
                if (JSONObject.isValidArray(patentTableList.get(i).getCompletePerson())) {
                    patentTableVoList.get(i).setCompletePerson(JSONArray.parseArray(patentTableList.get(i).getCompletePerson()));
                } else {
                    JSONArray nameArray = new JSONArray();
                    nameArray.add(patentTableList.get(i).getCompletePerson());
                    patentTableVoList.get(i).setCompletePerson(nameArray);
                }
            }
            annexOneVo.setPatentTableVoList(patentTableVoList);
        }

        //查询获得新药、疫苗、医疗器械证书数、临床批件数情况信息
        QueryWrapper<MprEvaMedicineTable> medicineTableQueryWrapper = new QueryWrapper<>();
        medicineTableQueryWrapper.eq(MprEvaMedicineTable.ID, projectId);
        List<MprEvaMedicineTable> medicineTableList = medicineTableService.list(medicineTableQueryWrapper);
        if (CollectionUtils.isNotEmpty(medicineTableList)) {
            List<MprEvaMedicineTableVo> medicineTableVoList = new BeanUtils<MprEvaMedicineTableVo>().copyObjs(medicineTableList, MprEvaMedicineTableVo.class);
            for (int i = 0; i < medicineTableVoList.size(); i++) {
                if (JSONObject.isValidArray(medicineTableList.get(i).getJoinPerson())) {
                    medicineTableVoList.get(i).setJoinPerson(JSONArray.parseArray(medicineTableList.get(i).getJoinPerson()));
                } else {
                    JSONArray nameArray = new JSONArray();
                    nameArray.add(medicineTableList.get(i).getJoinPerson());
                    medicineTableVoList.get(i).setJoinPerson(nameArray);
                }
            }
            annexOneVo.setMedicineTableVoList(medicineTableVoList);
        }

        //查询技术标准获批情况信息
        QueryWrapper<MprEvaTechStanTable> techStanTableQueryWrapper = new QueryWrapper<>();
        techStanTableQueryWrapper.eq(MprEvaTechStanTable.ID, projectId);
        List<MprEvaTechStanTable> techStanTableList = techStanTableService.list(techStanTableQueryWrapper);
        if (CollectionUtils.isNotEmpty(techStanTableList)) {
            List<MprEvaTechStanTableVo> techStanTableVoList = new BeanUtils<MprEvaTechStanTableVo>().copyObjs(techStanTableList, MprEvaTechStanTableVo.class);
            for (int i = 0; i < techStanTableVoList.size(); i++) {
                if (JSONObject.isValidArray(techStanTableList.get(i).getCompletePerson())) {
                    techStanTableVoList.get(i).setCompletePerson(JSONArray.parseArray(techStanTableList.get(i).getCompletePerson()));
                } else {
                    JSONArray nameArray = new JSONArray();
                    nameArray.add(techStanTableList.get(i).getCompletePerson());
                    techStanTableVoList.get(i).setCompletePerson(nameArray);
                }
            }
            annexOneVo.setTechStanTableVoList(techStanTableVoList);
        }

        //查询科技奖励信息
        QueryWrapper<MprEvaTechRewardTable> techRewardTableQueryWrapper = new QueryWrapper<>();
        techRewardTableQueryWrapper.eq(MprEvaTechRewardTable.ID, projectId);
        List<MprEvaTechRewardTable> techRewardTableList = techRewardTableService.list(techRewardTableQueryWrapper);
        if (CollectionUtils.isNotEmpty(techRewardTableList)) {
            List<MprEvaTechRewardTableVo> techRewardTableVoList = new BeanUtils<MprEvaTechRewardTableVo>().copyObjs(techRewardTableList, MprEvaTechRewardTableVo.class);
            annexOneVo.setTechRewardTableVoLists(techRewardTableVoList);
        }

        //承担国家重大科研任务情况

        //查询项目内人员牵头国家重大科研任务情况信息
        QueryWrapper<MprEvaResearchTaskTable> researchTaskTableQueryWrapper = new QueryWrapper<>();
        researchTaskTableQueryWrapper.eq(MprEvaResearchTaskTable.ID, projectId);
        List<MprEvaResearchTaskTable> researchTaskTableList = researchTaskTableService.list(researchTaskTableQueryWrapper);
        if (CollectionUtils.isNotEmpty(researchTaskTableList)) {
            List<MprEvaResearchTaskTableVo> researchTaskTableVoList = new BeanUtils<MprEvaResearchTaskTableVo>().copyObjs(researchTaskTableList, MprEvaResearchTaskTableVo.class);
            annexOneVo.setResearchTaskTableVoList(researchTaskTableVoList);
        }

        //学术影响力
        //查询举办学术会议情况信息
        QueryWrapper<MprEvaAcadConfTable> acadConfTableQueryWrapper = new QueryWrapper<>();
        acadConfTableQueryWrapper.eq(MprEvaAcadConfTable.ID, projectId);
        List<MprEvaAcadConfTable> acadConfTableList = acadConfTableService.list(acadConfTableQueryWrapper);
        if (CollectionUtils.isNotEmpty(acadConfTableList)) {
            List<MprEvaAcadConfTableVo> acadConfTableVoList = new BeanUtils<MprEvaAcadConfTableVo>().copyObjs(acadConfTableList, MprEvaAcadConfTableVo.class);
            annexOneVo.setAcadConfTableVoList(acadConfTableVoList);
        }

        //查询项目内人员重要学术组织任职情况信息
        QueryWrapper<MprAcadPostTable> acadPostTableQueryWrapper = new QueryWrapper<>();
        acadPostTableQueryWrapper.eq(MprAcadPostTable.ID, projectId);
        List<MprAcadPostTable> acadPostTableList = acadPostTableService.list(acadPostTableQueryWrapper);
        if (CollectionUtils.isNotEmpty(acadPostTableList)) {
            List<MprAcadPostTableVo> acadPostTableVoList = new BeanUtils<MprAcadPostTableVo>().copyObjs(acadPostTableList, MprAcadPostTableVo.class);
            annexOneVo.setAcadPostTableVoList(acadPostTableVoList);
        }

        //查询项目内人员重要期刊任职情况信息
        QueryWrapper<MprJouPostTable> jouPostTableQueryWrapper = new QueryWrapper<>();
        jouPostTableQueryWrapper.eq(MprJouPostTable.ID, projectId);
        List<MprJouPostTable> jouPostTableList = jouPostTableService.list(jouPostTableQueryWrapper);
        if (CollectionUtils.isNotEmpty(jouPostTableList)) {
            List<MprJouPostTableVo> jouPostTableVoList = new BeanUtils<MprJouPostTableVo>().copyObjs(jouPostTableList, MprJouPostTableVo.class);
            annexOneVo.setJouPostTableVoList(jouPostTableVoList);
        }

        //人才培养及团队建设
        //查询高层次人才培养情况信息
        QueryWrapper<MprEvaHighLevelTable> highLevelTableQueryWrapper = new QueryWrapper<>();
        highLevelTableQueryWrapper.eq(MprEvaHighLevelTable.ID, projectId);
        List<MprEvaHighLevelTable> highLevelTableList = highLevelTableService.list(highLevelTableQueryWrapper);
        if (CollectionUtils.isNotEmpty(highLevelTableList)) {
            List<MprEvaHighLevelTableVo> highLevelTableVoList = new BeanUtils<MprEvaHighLevelTableVo>().copyObjs(highLevelTableList, MprEvaHighLevelTableVo.class);
            annexOneVo.setHighLevelTableVoList(highLevelTableVoList);
        }

        //查询团队各任务间协作成果信息
        QueryWrapper<MprEvaCoopResultTable> coopResultTableQueryWrapper = new QueryWrapper<>();
        coopResultTableQueryWrapper.eq(MprEvaCoopResultTable.ID, projectId);
        List<MprEvaCoopResultTable> coopResultTableList = coopResultTableService.list(coopResultTableQueryWrapper);
        if (CollectionUtils.isNotEmpty(coopResultTableList)) {
            List<MprEvaCoopResultTableVo> coopResultTableVoList = new BeanUtils<MprEvaCoopResultTableVo>().copyObjs(coopResultTableList, MprEvaCoopResultTableVo.class);
            for (int i = 0; i < coopResultTableVoList.size(); i++) {
                if (JSONObject.isValidArray(coopResultTableList.get(i).getCoopPerson())) {
                    coopResultTableVoList.get(i).setCoopPerson(JSONArray.parseArray(coopResultTableList.get(i).getCoopPerson()));
                } else {
                    JSONArray nameArray = new JSONArray();
                    nameArray.add(coopResultTableList.get(i).getCoopPerson());
                    coopResultTableVoList.get(i).setCoopPerson(nameArray);
                }
            }
            annexOneVo.setCoopResultTableVoList(coopResultTableVoList);
        }

        return annexOneVo;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    protected void saveAnnexOneInfo(MprSaveOrUpdateAnnexOneForm saveOrUpdateAnnexOneForm, UserVo user, DeptVo deptVo, String processStatus, SrpmsProject project) {
        //保存基本情况信息
        MprEvaBaseInfoForm baseInfoForm = saveOrUpdateAnnexOneForm.getBaseInfoForm();
        Long projectId = project.getId();
        MprEvaBaseInfo baseInfo = new BeanUtils<MprEvaBaseInfo>().copyObj(baseInfoForm,MprEvaBaseInfo.class);
        baseInfo.setId(projectId);
        baseInfo.setCreateBy(user.getId());
        baseInfo.setAnnexStat(saveOrUpdateAnnexOneForm.getAnnexStat());
        baseInfo.setProcessStatus(processStatus);
        baseInfo.setApplyDeptCode(deptVo.getDeptCode());
        baseInfo.setReportYear((long)LocalDateTime.now().getYear());
        baseInfo.setReportType(ReportTypeEnum.MPR_REPORT.getCode());
        baseInfo.setProjectTypeCode(project.getProjectType());
        //不保存目标，值太长
        baseInfo.setProjectObjectives("");
        this.save(baseInfo);

        //经费使用情况
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getFundsBudgetFormList())) {
            List<MprEvaFundsBudget> fundsBudgetList = new BeanUtils<MprEvaFundsBudget>().copyObjs(saveOrUpdateAnnexOneForm.getFundsBudgetFormList(), MprEvaFundsBudget.class);
            for (MprEvaFundsBudget obj : fundsBudgetList) {
                obj.setId(projectId);
            }
            fundsBudgetService.saveBatch(fundsBudgetList);
        }

        //保存考核指标完成情况信息
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getTaskIndicatorFormList())) {
            List<MprEvaTaskIndicator> taskIndicatorList = new BeanUtils<MprEvaTaskIndicator>().copyObjs(saveOrUpdateAnnexOneForm.getTaskIndicatorFormList(), MprEvaTaskIndicator.class);
            for (MprEvaTaskIndicator obj : taskIndicatorList) {
                obj.setId(projectId);
            }
            taskIndicatorService.saveBatch(taskIndicatorList);
        }

        //代表性成果
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getOutcomeFormList())) {
            List<MprEvaOutcome> outcomeList = new BeanUtils<MprEvaOutcome>().copyObjs(saveOrUpdateAnnexOneForm.getOutcomeFormList(), MprEvaOutcome.class);
            for (MprEvaOutcome obj : outcomeList) {
                obj.setId(projectId);
            }
            outcomeService.saveBatch(outcomeList);
        }

        //保存新理论、新技术、新产品等情况信息
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getTheoTechProTableFormList())) {
            List<MprEvaTheoTechProTable> theoTechProTableList = new BeanUtils<MprEvaTheoTechProTable>().copyObjs(saveOrUpdateAnnexOneForm.getTheoTechProTableFormList(), MprEvaTheoTechProTable.class);
            for (MprEvaTheoTechProTable obj : theoTechProTableList) {
                obj.setId(projectId);
            }
            theoTechProTableService.saveBatch(theoTechProTableList);
        }
        //保存成果转化情况信息
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getTransTableFormList())) {
            List<MprEvaTransTable> transTableList = new BeanUtils<MprEvaTransTable>().copyObjs(saveOrUpdateAnnexOneForm.getTransTableFormList(), MprEvaTransTable.class);
            for (MprEvaTransTable obj : transTableList) {
                obj.setId(projectId);
            }
            transTableService.saveBatch(transTableList);
        }
        //保存示范推广服务情况信息
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getPromotionTableFormList())) {
            List<MprEvaPromotionTable> promotionTableList = Lists.newArrayList();

            for (MprEvaPromotionTableForm obj : saveOrUpdateAnnexOneForm.getPromotionTableFormList()) {
                MprEvaPromotionTable table = new BeanUtils<MprEvaPromotionTable>().copyObj(obj, MprEvaPromotionTable.class);
                table.setId(projectId);
                if (StringUtils.isNotBlank(obj.getPromotionExt())) {
                    table.setPromotionTimes(obj.getPromotionTimes() + "," + obj.getPromotionExt());
                }
                promotionTableList.add(table);
            }
            promotionTableService.saveBatch(promotionTableList);
        }
        //保存论文发表情况表-科普信息
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getTreaPopTableFormList())) {
            List<MprEvaTreaPopTable> treaPopTableList = new BeanUtils<MprEvaTreaPopTable>().copyObjs(saveOrUpdateAnnexOneForm.getTreaPopTableFormList(), MprEvaTreaPopTable.class);
            for (MprEvaTreaPopTable obj : treaPopTableList) {
                obj.setId(projectId);
            }
            treaPopTableService.saveBatch(treaPopTableList);
        }
        //保存论文发表情况信息
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getTreaTableFormList())) {
            List<MprEvaTreaTable> treaTableList = new BeanUtils<MprEvaTreaTable>().copyObjs(saveOrUpdateAnnexOneForm.getTreaTableFormList(), MprEvaTreaTable.class);
            for (MprEvaTreaTable obj : treaTableList) {
                obj.setId(projectId);
            }
            treaTableService.saveBatch(treaTableList);
        }
        //保存专著/教材发表情况信息
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getMonoTableFormList())) {
            List<MprEvaMonoTable> monoTableList = new BeanUtils<MprEvaMonoTable>().copyObjs(saveOrUpdateAnnexOneForm.getMonoTableFormList(), MprEvaMonoTable.class);
            for (int i = 0; i < monoTableList.size(); i++) {
                monoTableList.get(i).setId(projectId);
                List<String> personIds = Lists.newArrayList();
                if (null != saveOrUpdateAnnexOneForm.getMonoTableFormList().get(i).getCompletePerson()) {
                    personIds = saveOrUpdateAnnexOneForm.getMonoTableFormList().get(i).getCompletePerson().toJavaList(String.class);
                }
                monoTableList.get(i).setCompletePerson(JSON.toJSONString(personIds));
            }
            monoTableService.saveBatch(monoTableList);
        }
        //保存专利申请授权情况信息
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getPatentTableFormList())) {
            List<MprEvaPatentTable> patentTableList = new BeanUtils<MprEvaPatentTable>().copyObjs(saveOrUpdateAnnexOneForm.getPatentTableFormList(), MprEvaPatentTable.class);
            for (int i = 0; i < patentTableList.size(); i++) {
                patentTableList.get(i).setId(projectId);
                List<String> personIds = Lists.newArrayList();
                if (null != saveOrUpdateAnnexOneForm.getPatentTableFormList().get(i).getCompletePerson()) {
                    personIds = saveOrUpdateAnnexOneForm.getPatentTableFormList().get(i).getCompletePerson().toJavaList(String.class);
                }
                patentTableList.get(i).setCompletePerson(JSON.toJSONString(personIds));
            }
            patentTableService.saveBatch(patentTableList);
        }
        //保存获得新药、疫苗、医疗器械证书数、临床批件数情况信息
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getMedicineTableFormList())) {
            List<MprEvaMedicineTable> medicineTableList = new BeanUtils<MprEvaMedicineTable>().copyObjs(saveOrUpdateAnnexOneForm.getMedicineTableFormList(), MprEvaMedicineTable.class);
            for (int i = 0; i < medicineTableList.size(); i++) {
                medicineTableList.get(i).setId(projectId);
                List<String> personIds = Lists.newArrayList();
                if (null != saveOrUpdateAnnexOneForm.getMedicineTableFormList().get(i).getJoinPerson()) {
                    personIds = saveOrUpdateAnnexOneForm.getMedicineTableFormList().get(i).getJoinPerson().toJavaList(String.class);
                }
                medicineTableList.get(i).setJoinPerson(JSON.toJSONString(personIds));
            }
            medicineTableService.saveBatch(medicineTableList);
        }
        //保存技术标准获批情况信息
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getTechStanTableFormList())) {
            List<MprEvaTechStanTable> techStanTableList = new BeanUtils<MprEvaTechStanTable>().copyObjs(saveOrUpdateAnnexOneForm.getTechStanTableFormList(), MprEvaTechStanTable.class);
            for (int i = 0; i < techStanTableList.size(); i++) {
                techStanTableList.get(i).setId(projectId);
                List<String> personIds = Lists.newArrayList();
                if (null != saveOrUpdateAnnexOneForm.getTechStanTableFormList().get(i).getCompletePerson()) {
                    personIds = saveOrUpdateAnnexOneForm.getTechStanTableFormList().get(i).getCompletePerson().toJavaList(String.class);
                }
                techStanTableList.get(i).setCompletePerson(JSON.toJSONString(personIds));
            }
            techStanTableService.saveBatch(techStanTableList);
        }
        //保存科技奖励信息
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getTechRewardTableFormLists())) {
            List<MprEvaTechRewardTable> techRewardTableList = new BeanUtils<MprEvaTechRewardTable>().copyObjs(saveOrUpdateAnnexOneForm.getTechRewardTableFormLists(), MprEvaTechRewardTable.class);
            for (MprEvaTechRewardTable obj : techRewardTableList) {
                obj.setId(projectId);
            }
            techRewardTableService.saveBatch(techRewardTableList);
        }
        //保存项目内人员牵头国家重大科研任务情况信息
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getResearchTaskTableFormList())) {
            List<MprEvaResearchTaskTable> researchTaskTableList = new BeanUtils<MprEvaResearchTaskTable>().copyObjs(saveOrUpdateAnnexOneForm.getResearchTaskTableFormList(), MprEvaResearchTaskTable.class);
            for (MprEvaResearchTaskTable obj : researchTaskTableList) {
                obj.setId(projectId);
            }
            researchTaskTableService.saveBatch(researchTaskTableList);
        }
        //保存举办学术会议情况信息
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getAcadConfTableFormList())) {
            List<MprEvaAcadConfTable> acadConfTableList = new BeanUtils<MprEvaAcadConfTable>().copyObjs(saveOrUpdateAnnexOneForm.getAcadConfTableFormList(), MprEvaAcadConfTable.class);
            for (MprEvaAcadConfTable obj : acadConfTableList) {
                obj.setId(projectId);
            }
            acadConfTableService.saveBatch(acadConfTableList);
        }
        //保存项目内人员重要学术组织任职情况信息
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getAcadPostTableFormList())) {
            List<MprAcadPostTable> acadPostTableList = new BeanUtils<MprAcadPostTable>().copyObjs(saveOrUpdateAnnexOneForm.getAcadPostTableFormList(), MprAcadPostTable.class);
            for (MprAcadPostTable obj : acadPostTableList) {
                obj.setId(projectId);
            }
            acadPostTableService.saveBatch(acadPostTableList);
        }
        //保存项目内人员重要期刊任职情况信息
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getJouPostTableFormList())) {
            List<MprJouPostTable> jouPostTableList = new BeanUtils<MprJouPostTable>().copyObjs(saveOrUpdateAnnexOneForm.getJouPostTableFormList(), MprJouPostTable.class);
            for (MprJouPostTable obj : jouPostTableList) {
                obj.setId(projectId);
            }
            jouPostTableService.saveBatch(jouPostTableList);
        }
        //保存高层次人才培养情况信息
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getHighLevelTableFormList())) {
            List<MprEvaHighLevelTable> highLevelTableList = new BeanUtils<MprEvaHighLevelTable>().copyObjs(saveOrUpdateAnnexOneForm.getHighLevelTableFormList(), MprEvaHighLevelTable.class);
            for (MprEvaHighLevelTable obj : highLevelTableList) {
                obj.setId(projectId);
            }
            highLevelTableService.saveBatch(highLevelTableList);
        }
        //保存团队各任务间协作成果信息
        if (CollectionUtils.isNotEmpty(saveOrUpdateAnnexOneForm.getCoopResultTableFormList())) {
            List<MprEvaCoopResultTable> coopResultTableList = new BeanUtils<MprEvaCoopResultTable>().copyObjs(saveOrUpdateAnnexOneForm.getCoopResultTableFormList(), MprEvaCoopResultTable.class);
            for (int i = 0; i < coopResultTableList.size(); i++) {
                coopResultTableList.get(i).setId(projectId);
                List<String> personIds = Lists.newArrayList();
                if (null != saveOrUpdateAnnexOneForm.getCoopResultTableFormList().get(i).getCoopPerson()) {
                    personIds = saveOrUpdateAnnexOneForm.getCoopResultTableFormList().get(i).getCoopPerson().toJavaList(String.class);
                }
                coopResultTableList.get(i).setCoopPerson(JSON.toJSONString(personIds));
            }
            coopResultTableService.saveBatch(coopResultTableList);
        }
    }

    private void removeMprBaseInfo(Long projectId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(MprEvaBaseInfo.ID, projectId);
        map.put(MprEvaBaseInfo.REPORT_TYPE, ReportTypeEnum.MPR_REPORT.getCode());
        this.removeByMap(map);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delAnnexOneInfo(Long projectId) {
        //删除基本情况信息
        removeMprBaseInfo(projectId);
        //删除经费使用情况
        fundsBudgetService.removeById(projectId);
        //删除考核指标完成情况信息
        taskIndicatorService.removeById(projectId);
        //删除代表性成果
        outcomeService.removeById(projectId);
        //删除新理论、新技术、新产品等情况信息
        theoTechProTableService.removeById(projectId);
        //删除成果转化情况信息
        transTableService.removeById(projectId);
        //删除示范推广服务情况信息
        promotionTableService.removeById(projectId);
        //删除论文发表情况表-科普信息
        treaPopTableService.removeById(projectId);
        //删除论文发表情况信息
        treaTableService.removeById(projectId);
        //删除专著/教材发表情况信息
        monoTableService.removeById(projectId);
        //删除专利申请授权情况信息
        patentTableService.removeById(projectId);
        //删除获得新药、疫苗、医疗器械证书数、临床批件数情况信息
        medicineTableService.removeById(projectId);
        //删除技术标准获批情况信息
        techStanTableService.removeById(projectId);
        //删除科技奖励信息
        techRewardTableService.removeById(projectId);
        //删除项目内人员牵头国家重大科研任务情况信息
        researchTaskTableService.removeById(projectId);
        //删除举办学术会议情况信息
        acadConfTableService.removeById(projectId);
        //删除项目内人员重要学术组织任职情况信息
        acadPostTableService.removeById(projectId);
        //删除项目内人员重要期刊任职情况信息
        jouPostTableService.removeById(projectId);
        //删除高层次人才培养情况信息
        highLevelTableService.removeById(projectId);
        //删除团队各任务间协作成果信息
        coopResultTableService.removeById(projectId);
    }

    @Override
    public MprEvaBaseInfo getMprBaseInfo(Long projectId) {
        QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MprEvaBaseInfo.ID, projectId);
        queryWrapper.eq(MprEvaBaseInfo.REPORT_TYPE, ReportTypeEnum.MPR_REPORT.getCode());
        return this.getOne(queryWrapper);
    }

    @Override
    public String exportAnnexOneExcel(Long projectId) {
        //取需要导出的数据
        MprSaveOrUpdateAnnexOneVo annexOneVo = getAnnexOne(projectId);
        //复制模板
        String tempFilePath = excelExport.copyTemplateFile("template_mpr_annex_one");
        Workbook workbook = null;
        FileOutputStream outputStream = null;
        FileInputStream inputStream = null;
        try {
            File file = new File(tempFilePath);
            inputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(inputStream);

            //构造数据
            fillAnnexOneWorkbook(workbook, annexOneVo);

            outputStream = new FileOutputStream(tempFilePath);
            workbook.write(outputStream);
        } catch (Exception e) {
            log.error("生成workbook异常", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
        } finally {
            try {
                workbook.close();
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                log.error("关闭流异常", e);
                throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
            }
        }
        return tempFilePath;
    }

    private Map<String, String> getTaskNameMap(List<SrpmsProjectTaskVo> taskVoList) {
        Map<String, String> map = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(taskVoList)) {
            for (SrpmsProjectTaskVo vo : taskVoList) {
                map.put(String.valueOf(vo.getId()), vo.getTaskName());
            }
        }
        return map;
    }

    private String getNameByIds(Map<String, String> personMap, JSONArray ids) {
        if (null == ids || MapUtils.isEmpty(personMap)) {
            return "";
        }
        List<Object> personIdList = ids.toJavaList(Object.class);
        List<String> personNameList = Lists.newArrayList();
        for (Object id : personIdList) {
            personNameList.add(MapUtils.getString(personMap, id));
        }
        return StringUtils.join(personNameList, ",");
    }

    //填充附件一数据
    private void fillAnnexOneWorkbook(Workbook workbook, MprSaveOrUpdateAnnexOneVo annexOneVo) {
        Sheet sheet = workbook.getSheetAt(0);
        //获取中期绩效报告附件一的Key
        List<String> keys = Lists.newArrayList();
        Field[] fields = MprConstant.class.getDeclaredFields();
        for (Field field : fields) {
            keys.add(field.getName());
        }

        Map<String, Object> baseInfoMap = beanToMap(annexOneVo.getBaseInfoVo());

        //给有Key的单元格填值
        for (String key : keys) {
            Name name = workbook.getName(key);
            if (name != null) {
                CellReference ref = new CellReference(name.getRefersToFormula());
                Row row = sheet.getRow(ref.getRow());
                Cell cell = row.getCell(ref.getCol());
                cell.setCellValue(MapUtils.getString(baseInfoMap, key));
            }
        }

        //任务列表
        List<SrpmsProjectTaskVo> projectTaskVoList = annexOneVo.getProjectTaskVoList();
        Map<String, String> taskMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(projectTaskVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, projectTaskVoList.size() - 1, "projectTask");
            for (int i = 0; i < projectTaskVoList.size(); i++) {
                taskMap.put(String.valueOf(projectTaskVoList.get(i).getId()), projectTaskVoList.get(i).getTaskName());
                Row insertRow = sheet.getRow(startRow + i);
                //任务名称
                Cell insertCell = insertRow.getCell(2);
                insertCell.setCellValue(projectTaskVoList.get(i).getTaskName());
                //任务负责人
                Cell insertCell1 = insertRow.getCell(5);
                insertCell1.setCellValue(projectTaskVoList.get(i).getLeadPersonName());
                //任务参与人
                String name = projectTaskVoList.get(i).getJoinPersonNameStr();
                Cell insertCell2 = insertRow.getCell(6);
                insertCell2.setCellValue(name);

                // 合并单元格
                if (i != projectTaskVoList.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 0, 1);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 2, 4);
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 6, 12);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2);
                }
            }
        }

        //项目人员情况
        List<SrpmsProjectPersonJoinVo> personJoinVoList = annexOneVo.getPersonJoinVoList();
        Map<String, String> personJoinMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(personJoinVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, personJoinVoList.size() - 1, "personJoin");

            for (int i = 0; i < personJoinVoList.size(); i++) {
                personJoinMap.put(String.valueOf(personJoinVoList.get(i).getPersonId()), personJoinVoList.get(i).getPersonName());
                Row insertRow = sheet.getRow(startRow + i);
                //序号
                Cell insertCell = insertRow.getCell(0);
                insertCell.setCellValue(i + 1);
                //姓名
                Cell insertCell1 = insertRow.getCell(1);
                insertCell1.setCellValue(personJoinVoList.get(i).getPersonName());
                //出生年月
                Cell insertCell2 = insertRow.getCell(2);
                insertCell2.setCellValue(personJoinVoList.get(i).getBirthDateString());
                //专业技术职称
                Cell insertCell3 = insertRow.getCell(3);
                insertCell3.setCellValue(personJoinVoList.get(i).getPositionTitle());
                //学位
                Cell insertCell4 = insertRow.getCell(4);
                insertCell4.setCellValue(personJoinVoList.get(i).getDegree());
                //专业方向
                Cell insertCell5 = insertRow.getCell(5);
                insertCell5.setCellValue(personJoinVoList.get(i).getMajor());
                //分工
                Cell insertCell6 = insertRow.getCell(6);
                insertCell6.setCellValue(personJoinVoList.get(i).getBelongTask());
                //所在单位
                Cell insertCell7 = insertRow.getCell(7);
                insertCell7.setCellValue(personJoinVoList.get(i).getDeptName());
                //电话
                Cell insertCell8 = insertRow.getCell(8);
                insertCell8.setCellValue(personJoinVoList.get(i).getPhone());
                //是否发生变更
                Cell insertCell9 = insertRow.getCell(9);
                insertCell9.setCellValue(personJoinVoList.get(i).getIsUpdate());
                //变更原因
                Cell insertCell10 = insertRow.getCell(11);
                insertCell10.setCellValue(personJoinVoList.get(i).getUpdateReason());
                // 合并单元格
                if (i != personJoinVoList.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 9, 10);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 11, 12);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                }
            }
        }

        //项目中期绩效目标及考核指标完成情况
        List<MprEvaTaskIndicatorVo> taskIndicatorVoList = annexOneVo.getTaskIndicatorVoList();
        if (CollectionUtils.isNotEmpty(taskIndicatorVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, taskIndicatorVoList.size() - 1, "taskIndicator");
            for (int i = 0; i < taskIndicatorVoList.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //项目中期预期目标
                if (StringUtils.isNotBlank(annexOneVo.getBaseInfoVo().getProjectObjectives())) {
                    Cell insertCell0 = insertRow.getCell(0);
                    insertCell0.setCellValue(WordConvertUtil.htmlToText(annexOneVo.getBaseInfoVo().getProjectObjectives()));
                    insertCell0.getCellStyle().setAlignment(HSSFCellStyle.ALIGN_CENTER);
                }
                //任务名称
                Cell insertCell = insertRow.getCell(3);
                insertCell.setCellValue(taskIndicatorVoList.get(i).getTaskName());
                //中期考核指标
                Cell insertCell1 = insertRow.getCell(6);
                insertCell1.setCellValue(taskIndicatorVoList.get(i).getIndicatorName());
                //指标实际完成情况
                Cell insertCell2 = insertRow.getCell(10);
                insertCell2.setCellValue(taskIndicatorVoList.get(i).getIndicatorCompleteStatus());

                // 合并单元格
                if (i != taskIndicatorVoList.size() - 1) {
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 3, 5);
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 6, 9);
                    CellRangeAddress region3 = new CellRangeAddress(startRow + i, startRow + i, 10, 12);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2);
                    sheet.addMergedRegion(region3);
                }
            }
            CellRangeAddress region = new CellRangeAddress(startRow, startRow + taskIndicatorVoList.size() - 1, 0, 2);
            sheet.addMergedRegion(region);
        }

        //代表性成果
        List<MprEvaOutcomeVo> outcomeVoList = annexOneVo.getOutcomeVoList();
        if (CollectionUtils.isNotEmpty(outcomeVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, outcomeVoList.size() - 1, "proTaskInfo");
            Map<String, String> taskNameMap = getTaskNameMap(projectTaskVoList);
            for (int i = 0; i < outcomeVoList.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //代表性成果名称
                Cell insertCell = insertRow.getCell(0);
                insertCell.setCellValue(outcomeVoList.get(i).getRepOutcomeName());
                //成果类型
                Cell insertCell1 = insertRow.getCell(3);
                insertCell1.setCellValue(outcomeVoList.get(i).getOutcomeType());
                //对应的任务
                Cell insertCell2 = insertRow.getCell(6);
                insertCell2.setCellValue(MapUtils.getString(taskNameMap, outcomeVoList.get(i).getCorrespondTask(), ""));
                //成果水平
                Cell insertCell3 = insertRow.getCell(10);
                insertCell3.setCellValue(outcomeVoList.get(i).getOutcomeLevel());

                // 合并单元格
                if (i != outcomeVoList.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 0, 2);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 3, 5);
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 6, 9);
                    CellRangeAddress region3 = new CellRangeAddress(startRow + i, startRow + i, 10, 12);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2);
                    sheet.addMergedRegion(region3);
                }
            }
        }

        //经费使用情况
        double budgetTotal = 0;
        double expensesTotal = 0;
        List<MprEvaFundsBudgetVo> fundsBudgetVoList = annexOneVo.getFundsBudgetVoList();

        if (CollectionUtils.isNotEmpty(fundsBudgetVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, fundsBudgetVoList.size() - 1, "fundsBudget");
            String tempName = "";
            List<Integer> sizeList = Lists.newArrayList();
            for (int i = 0; i < fundsBudgetVoList.size(); i++) {
                if (!StringUtils.equals(tempName, fundsBudgetVoList.get(i).getTaskName())) {
                    tempName = fundsBudgetVoList.get(i).getTaskName();
                    sizeList.add(i);
                }
                Row insertRow = sheet.getRow(startRow + i);
                //任务
                Cell insertCell = insertRow.getCell(0);
                insertCell.setCellValue(fundsBudgetVoList.get(i).getTaskName());
                //年份
                Cell insertCell1 = insertRow.getCell(3);
                insertCell1.setCellValue(fundsBudgetVoList.get(i).getYear());
                //预算
                Cell insertCell2 = insertRow.getCell(4);
                String budget = fundsBudgetVoList.get(i).getBudget();
                insertCell2.setCellValue(budget);
                budgetTotal += NumberUtils.toDouble(budget, 0d);
                //支出
                Cell insertCell3 = insertRow.getCell(7);
                String expenses = fundsBudgetVoList.get(i).getExpenses();
                insertCell3.setCellValue(expenses);
                expensesTotal += NumberUtils.toDouble(expenses, 0d);
                //执行率
                Cell insertCell4 = insertRow.getCell(10);
                insertCell4.setCellValue(fundsBudgetVoList.get(i).getExacutiveRate());

                // 合并单元格
                if (i != fundsBudgetVoList.size() - 1) {
//                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 0, 2);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 4, 6);
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 7, 9);
                    CellRangeAddress region3 = new CellRangeAddress(startRow + i, startRow + i, 10, 12);

//                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2);
                    sheet.addMergedRegion(region3);
                }

            }
            sizeList.add(fundsBudgetVoList.size());
            int tempRow = startRow;
            for (int i = 1; i < sizeList.size(); i++) {
                CellRangeAddress region = new CellRangeAddress(tempRow, startRow + sizeList.get(i) - 1, 0, 2);
                sheet.addMergedRegion(region);
                tempRow  = startRow + sizeList.get(i);
            }
        }

        Name name = workbook.getName("fundsBudgetTotal");
        CellReference refBudgetTotal = new CellReference(name.getRefersToFormula());
        Sheet sheetBudgetTotal = workbook.getSheet(refBudgetTotal.getSheetName());
        Row rowBudgetTotal = sheetBudgetTotal.getRow(refBudgetTotal.getRow());
        Cell insertCellBudgetTotal1 = rowBudgetTotal.getCell(4);
        insertCellBudgetTotal1.setCellValue(budgetTotal);
        Cell insertCellBudgetTotal2 = rowBudgetTotal.getCell(7);
        insertCellBudgetTotal2.setCellValue(expensesTotal);

        // 设置精确到小数点后2位
        if (budgetTotal != 0) {
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(2);
            String result = numberFormat.format(expensesTotal / budgetTotal * 100);
            Cell insertCellBudgetTotal3 = rowBudgetTotal.getCell(10);
            insertCellBudgetTotal3.setCellValue(result + "%");
        }

        //经济社会效益
        //新理论、新技术、新产品等情况
        List<MprEvaTheoTechProTableVo> theoTechProTableVoList = annexOneVo.getTheoTechProTableVoList();
        if (CollectionUtils.isNotEmpty(theoTechProTableVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, theoTechProTableVoList.size() - 1, "TheoTech");

            for (int i = 0; i < theoTechProTableVoList.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //产出类型
                Cell insertCell = insertRow.getCell(2);
                insertCell.setCellValue(theoTechProTableVoList.get(i).getOutputType());
                //产出名称
                Cell insertCell1 = insertRow.getCell(5);
                insertCell1.setCellValue(theoTechProTableVoList.get(i).getOutputName());
                //产生意义
                Cell insertCell2 = insertRow.getCell(9);
                insertCell2.setCellValue(theoTechProTableVoList.get(i).getBringMean());

                // 合并单元格
                if (i != theoTechProTableVoList.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 2, 4);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 5, 8);
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 9, 12);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2);
                }
            }
        }

        //成果转化情况
        List<MprEvaTransTableVo> transTableVoList = annexOneVo.getTransTableVoList();
        if (CollectionUtils.isNotEmpty(transTableVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, transTableVoList.size() - 1, "transTable");

            for (int i = 0; i < transTableVoList.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //序号
                Cell insertCell = insertRow.getCell(2);
                insertCell.setCellValue(i + 1);
                //成果名称
                Cell insertCell1 = insertRow.getCell(3);
                insertCell1.setCellValue(transTableVoList.get(i).getOutcomeName());
                //成果转换形式
                Cell insertCell2 = insertRow.getCell(6);
                insertCell2.setCellValue(transTableVoList.get(i).getTechTrans());
                //合同签订年份
                String contractSignYear = transTableVoList.get(i).getContractSignYear();
                if (StringUtils.isNotBlank(contractSignYear)) {
                    Cell insertCell5 = insertRow.getCell(9);
                    insertCell5.setCellValue(contractSignYear.split("-")[0]);
                }
                //合同金额（万元）
                Cell insertCell6 = insertRow.getCell(11);
                insertCell6.setCellValue(transTableVoList.get(i).getContractAmount());

                // 合并单元格
                if (i != transTableVoList.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 3, 5);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 9, 10);
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 11, 12);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2);
                }
            }
        }

        //论文发表情况表-科普
        List<MprEvaTreaPopTableVo> treaPopTableVoList = annexOneVo.getTreaPopTableVoList();
        if (CollectionUtils.isNotEmpty(treaPopTableVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, treaPopTableVoList.size() - 1, "treaPopTable");

            for (int i = 0; i < treaPopTableVoList.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //科普基地名称
                Cell insertCell = insertRow.getCell(2);
                insertCell.setCellValue(treaPopTableVoList.get(i).getPopularBaseName());
                //科普内容
                Cell insertCell1 = insertRow.getCell(5);
                insertCell1.setCellValue(treaPopTableVoList.get(i).getPopularScienceContent());
                //成立年份
                String yeaEstab = treaPopTableVoList.get(i).getYearEstablished();
                if (StringUtils.isNotBlank(yeaEstab)) {
                    Cell insertCell2 = insertRow.getCell(9);
                    insertCell2.setCellValue(yeaEstab.split("-")[0]);
                }
                //接待人次（千人）
                Cell insertCell3 = insertRow.getCell(11);
                insertCell3.setCellValue(treaPopTableVoList.get(i).getReceptionNumber());

                // 合并单元格
                if (i != treaPopTableVoList.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 2, 4);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 5, 8);
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 9, 10);
                    CellRangeAddress region3 = new CellRangeAddress(startRow + i, startRow + i, 11, 12);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2);
                    sheet.addMergedRegion(region3);
                }
            }
        }

        //示范推广服务情况
        List<MprEvaPromotionTableVo> promotionTableVoList = annexOneVo.getPromotionTableVoList();
        if (CollectionUtils.isNotEmpty(promotionTableVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, promotionTableVoList.size() - 1, "promotionTable");

            for (int i = 0; i < promotionTableVoList.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //示范培训/推广地点
                Cell insertCell = insertRow.getCell(2);
                insertCell.setCellValue(promotionTableVoList.get(i).getPromotionArea());
                //示范培训/推广内容
                Cell insertCell1 = insertRow.getCell(5);
                insertCell1.setCellValue(promotionTableVoList.get(i).getPromotionContent());
                //示范培训/推广人次（千人）/推广面积（亩）

                Cell insertCell2 = insertRow.getCell(11);
                insertCell2.setCellValue(promotionTableVoList.get(i).getPromotionTimes() + "/" + promotionTableVoList.get(i).getPromotionExt());

                // 合并单元格
                if (i != promotionTableVoList.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 2, 4);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 5, 10);
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 11, 12);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2);
                }
            }
        }

        //论文发表情况
        List<MprEvaTreaTableVo> treaTableVoList = annexOneVo.getTreaTableVoList();
        if (CollectionUtils.isNotEmpty(treaTableVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, treaTableVoList.size() - 1, "treaTable");

            for (int i = 0; i < treaTableVoList.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //论文题目
                Cell insertCell = insertRow.getCell(2);
                insertCell.setCellValue(treaTableVoList.get(i).getTreaTopic());
                //期刊名称
                Cell insertCell1 = insertRow.getCell(3);
                insertCell1.setCellValue(treaTableVoList.get(i).getJournalName());
                //作者
                Cell insertCell2 = insertRow.getCell(4);
                insertCell2.setCellValue(MapUtils.getString(personJoinMap, treaTableVoList.get(i).getAuthor()));
                //作者类别
                Cell insertCell22 = insertRow.getCell(5);
                insertCell22.setCellValue(treaTableVoList.get(i).getAuthorType());
                //发表时间
                String issuTime = treaTableVoList.get(i).getIssuTime();
                if (StringUtils.isNotBlank(issuTime)) {
                    Cell insertCell3 = insertRow.getCell(6);
                    insertCell3.setCellValue(issuTime.split(" ")[0]);
                }
                //年
                if (StringUtils.isNotBlank(treaTableVoList.get(i).getYear())) {
                    Cell insertCell4 = insertRow.getCell(7);
                    insertCell4.setCellValue(treaTableVoList.get(i).getYear().split("-")[0]);
                }
                //卷（期）
                Cell insertCell44 = insertRow.getCell(8);
                insertCell44.setCellValue(treaTableVoList.get(i).getVolume());
                //页
                Cell insertCell444 = insertRow.getCell(9);
                insertCell444.setCellValue(treaTableVoList.get(i).getPage());
                //期刊影响因子
                Cell insertCell5 = insertRow.getCell(10);
                insertCell5.setCellValue(treaTableVoList.get(i).getImpactFactor());
                //引用频次
                Cell insertCell6 = insertRow.getCell(11);
                insertCell6.setCellValue(treaTableVoList.get(i).getRefeFreq());
                //收录来源
                Cell insertCell7 = insertRow.getCell(12);
                insertCell7.setCellValue(treaTableVoList.get(i).getSourceInclusion());
            }
        }

        //专著/教材发表情况
        List<MprEvaMonoTableVo> monoTableVoList = annexOneVo.getMonoTableVoList();
        if (CollectionUtils.isNotEmpty(monoTableVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, monoTableVoList.size() - 1, "monoTable");

            for (int i = 0; i < monoTableVoList.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //专著/教材名称
                Cell insertCell = insertRow.getCell(2);
                insertCell.setCellValue(monoTableVoList.get(i).getMonographTeachName());
                //完成人
                Cell insertCell1 = insertRow.getCell(4);
                insertCell1.setCellValue(getNameByIds(personJoinMap, monoTableVoList.get(i).getCompletePerson()));
                //主编/副主编/编委
                Cell insertCell2 = insertRow.getCell(5);
                insertCell2.setCellValue(monoTableVoList.get(i).getPositionLevel());
                //ISBN
                Cell insertCell3 = insertRow.getCell(6);
                insertCell3.setCellValue(monoTableVoList.get(i).getIsbn());
                //专著类别
                Cell insertCell4 = insertRow.getCell(8);
                insertCell4.setCellValue(monoTableVoList.get(i).getMonographType());
                //出版社
                Cell insertCell5 = insertRow.getCell(9);
                insertCell5.setCellValue(monoTableVoList.get(i).getPubHouse());
                //出版时间
                Cell insertCell6 = insertRow.getCell(11);
                insertCell6.setCellValue(monoTableVoList.get(i).getPubDate());
                //字数（万字）
                Cell insertCell7 = insertRow.getCell(12);
                insertCell7.setCellValue(monoTableVoList.get(i).getWordCount());
                // 合并单元格
                if (i != monoTableVoList.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 2, 3);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 6, 7);
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 9, 10);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2);
                }
            }
        }

        //专利申请授权情况
        List<MprEvaPatentTableVo> patentTableVoList = annexOneVo.getPatentTableVoList();
        if (CollectionUtils.isNotEmpty(patentTableVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, patentTableVoList.size() - 1, "patentTable");

            for (int i = 0; i < patentTableVoList.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //专利名称
                Cell insertCell = insertRow.getCell(2);
                insertCell.setCellValue(patentTableVoList.get(i).getPatentName());
                //状态
                Cell insertCell6 = insertRow.getCell(5);
                insertCell6.setCellValue(patentTableVoList.get(i).getPatentStatus());
                //申请号/授权号
                Cell insertCell1 = insertRow.getCell(6);
                insertCell1.setCellValue(patentTableVoList.get(i).getApplyAuthNum());
                //申请/批准
                Cell insertCell2 = insertRow.getCell(7);
                insertCell2.setCellValue(patentTableVoList.get(i).getApplyApprovalCountry());
                //专利类别
                Cell insertCell3 = insertRow.getCell(8);
                insertCell3.setCellValue(patentTableVoList.get(i).getPatentType());
                //完成人
                Cell insertCell4 = insertRow.getCell(10);
                insertCell4.setCellValue(getNameByIds(personJoinMap, patentTableVoList.get(i).getCompletePerson()));
                //完成时间
                String completeDate = patentTableVoList.get(i).getCompleteDate();
                if (StringUtils.isNotBlank(completeDate)) {
                    Cell insertCell5 = insertRow.getCell(11);
                    insertCell5.setCellValue(completeDate.split(" ")[0]);
                }

                //是否应用
                Cell insertCell7 = insertRow.getCell(12);
                insertCell7.setCellValue(patentTableVoList.get(i).getIsUse());
                // 合并单元格
                if (i != patentTableVoList.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 2, 4);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 8, 9);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                }
            }
        }

        //获得新药、疫苗、医疗器械证书数、临床批件数
        List<MprEvaMedicineTableVo> medicineTableVoList = annexOneVo.getMedicineTableVoList();
        if (CollectionUtils.isNotEmpty(medicineTableVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, medicineTableVoList.size() - 1, "medicineTable");

            for (int i = 0; i < medicineTableVoList.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //产品名称
                Cell insertCell = insertRow.getCell(2);
                insertCell.setCellValue(medicineTableVoList.get(i).getProductName());
                //申报、注册分类
                if (StringUtils.isNotBlank(medicineTableVoList.get(i).getRegistrationType())) {
                    String[] RegistrationType = medicineTableVoList.get(i).getRegistrationType().split("/");
                    if (RegistrationType.length > 0) {
                        Cell insertCell1 = insertRow.getCell(5);
                        insertCell1.setCellValue(RegistrationType[0]);
                    }

                }
                //产品类别
                Cell insertCell1 = insertRow.getCell(9);
                insertCell1.setCellValue(medicineTableVoList.get(i).getProductType());

                //申请号/证书号
                Cell insertCell2 = insertRow.getCell(7);
                insertCell2.setCellValue(medicineTableVoList.get(i).getCertNo());

                //参与人员
                Cell insertCell4 = insertRow.getCell(11);
                insertCell4.setCellValue(getNameByIds(personJoinMap, medicineTableVoList.get(i).getJoinPerson()));
                //申报/批准时间
                String approvalDate = medicineTableVoList.get(i).getApprovalDate();
                if (StringUtils.isNotBlank(approvalDate)) {
                    Cell insertCell5 = insertRow.getCell(12);
                    insertCell5.setCellValue(approvalDate.split(" ")[0]);
                }
                // 合并单元格
                if (i != medicineTableVoList.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 2, 4);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 5, 6);
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 7, 8);
                    CellRangeAddress region3 = new CellRangeAddress(startRow + i, startRow + i, 9, 10);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2);
                    sheet.addMergedRegion(region3);
                }
            }
        }

        //技术标准获批情况
        List<MprEvaTechStanTableVo> techStanTableVoList = annexOneVo.getTechStanTableVoList();
        if (CollectionUtils.isNotEmpty(techStanTableVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, techStanTableVoList.size() - 1, "techStanTable");

            for (int i = 0; i < techStanTableVoList.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //获得技术标准名称
                Cell insertCell = insertRow.getCell(2);
                insertCell.setCellValue(techStanTableVoList.get(i).getObtTechStanName());
                //标准类型
                Cell insertCell1 = insertRow.getCell(5);
                insertCell1.setCellValue(techStanTableVoList.get(i).getStanType());
                //标准号
                Cell insertCell2 = insertRow.getCell(7);
                insertCell2.setCellValue(techStanTableVoList.get(i).getStanNum());
                //完成人
                Cell insertCell3 = insertRow.getCell(9);
                insertCell3.setCellValue(getNameByIds(personJoinMap, techStanTableVoList.get(i).getCompletePerson()));
                //发布时间
                String postDate = techStanTableVoList.get(i).getPostDate();
                if (StringUtils.isNotBlank(postDate)) {
                    Cell insertCell4 = insertRow.getCell(11);
                    insertCell4.setCellValue(postDate.split(" ")[0]);
                }
                // 合并单元格
                if (i != techStanTableVoList.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 2, 4);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 5, 6);
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 7, 8);
                    CellRangeAddress region3 = new CellRangeAddress(startRow + i, startRow + i, 9, 10);
                    CellRangeAddress region4 = new CellRangeAddress(startRow + i, startRow + i, 11, 12);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2);
                    sheet.addMergedRegion(region3);
                    sheet.addMergedRegion(region4);
                }
            }
        }

        //科技奖励信息
        List<MprEvaTechRewardTableVo> techRewardTableVoLists = annexOneVo.getTechRewardTableVoLists();
        if (CollectionUtils.isNotEmpty(techRewardTableVoLists)) {
            int startRow = excelExport.insertBlankRow(workbook, techRewardTableVoLists.size() - 1, "techRewardTable");

            for (int i = 0; i < techRewardTableVoLists.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //成果名称
                Cell insertCell = insertRow.getCell(2);
                insertCell.setCellValue(techRewardTableVoLists.get(i).getAchieveName());
                //获奖人
                Cell insertCell1 = insertRow.getCell(6);
                insertCell1.setCellValue(MapUtils.getString(personJoinMap, techRewardTableVoLists.get(i).getObtainPerson()));
                //获奖类别
                Cell insertCell2 = insertRow.getCell(7);
                insertCell2.setCellValue(techRewardTableVoLists.get(i).getAwardCategory());
                //奖项名称
                Cell insertCell3 = insertRow.getCell(9);
                insertCell3.setCellValue(techRewardTableVoLists.get(i).getAwardName());
                //获奖等级
                Cell insertCell4 = insertRow.getCell(10);
                insertCell4.setCellValue(techRewardTableVoLists.get(i).getAwardLevel());
                //获奖年度
                String year = techRewardTableVoLists.get(i).getAwardYear();
                if (StringUtils.isNotBlank(year)) {
                    Cell insertCell5 = insertRow.getCell(11);
                    insertCell5.setCellValue(year.split("-")[0]);
                }

                //证书编号
                Cell insertCell6 = insertRow.getCell(12);
                insertCell6.setCellValue(techRewardTableVoLists.get(i).getCertNum());
                // 合并单元格
                if (i != techRewardTableVoLists.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 2, 5);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 7, 8);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                }
            }
        }

        //承担国家重大科研任务情况
        //项目内人员牵头国家重大科研任务情况
        List<MprEvaResearchTaskTableVo> researchTaskTableVoList = annexOneVo.getResearchTaskTableVoList();
        if (CollectionUtils.isNotEmpty(researchTaskTableVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, researchTaskTableVoList.size() - 1, "researchTaskTable");

            for (int i = 0; i < researchTaskTableVoList.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //项目名称
                Cell insertCell = insertRow.getCell(2);
                insertCell.setCellValue(researchTaskTableVoList.get(i).getProjectName());
                //项目编号
                Cell insertCell1 = insertRow.getCell(5);
                insertCell1.setCellValue(researchTaskTableVoList.get(i).getProjectNo());
                //项目负责人
                Cell insertCell2 = insertRow.getCell(6);
                insertCell2.setCellValue(MapUtils.getString(personJoinMap, researchTaskTableVoList.get(i).getProjectManager()));
                //项目来源
                Cell insertCell3 = insertRow.getCell(7);
                insertCell3.setCellValue(researchTaskTableVoList.get(i).getProjectSource());

                //开始时间
                Cell insertCell4 = insertRow.getCell(9);
                String beginTime = researchTaskTableVoList.get(i).getBeginTime();
                if (StringUtils.isNotBlank(beginTime)) {
                    insertCell4.setCellValue(beginTime.split(" ")[0]);
                }
                //结束时间
                Cell insertCell5 = insertRow.getCell(10);
                String endTime = researchTaskTableVoList.get(i).getEndTime();
                if (StringUtils.isNotBlank(endTime)) {
                    insertCell5.setCellValue(endTime.split(" ")[0]);
                }

                //实际获得经费   （万元）
                Cell insertCell6 = insertRow.getCell(11);
                insertCell6.setCellValue(researchTaskTableVoList.get(i).getActualFund());
                // 合并单元格
                if (i != researchTaskTableVoList.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 2, 4);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 7, 8);
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 11, 12);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2   );
                }
            }
        }

        //学术影响力
        //举办学术会议情况
        List<MprEvaAcadConfTableVo> acadConfTableVoList = annexOneVo.getAcadConfTableVoList();
        if (CollectionUtils.isNotEmpty(acadConfTableVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, acadConfTableVoList.size() - 1, "acadConfTable");

            for (int i = 0; i < acadConfTableVoList.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //会议名称
                Cell insertCell = insertRow.getCell (2);
                insertCell.setCellValue(acadConfTableVoList.get(i).getConferenceName());
                //会议类型
                Cell insertCell1 = insertRow.getCell(5);
                insertCell1.setCellValue(acadConfTableVoList.get(i).getConferenceType());
                //国外代表人数
                Cell insertCell2 = insertRow.getCell(6);
                insertCell2.setCellValue(acadConfTableVoList.get(i).getForeReprNum());
                //国内代表人数
                Cell insertCell3 = insertRow.getCell(7);
                insertCell3.setCellValue(acadConfTableVoList.get(i).getDomeReprNum());
                //会议地点
                Cell insertCell4 = insertRow.getCell(8);
                insertCell4.setCellValue(acadConfTableVoList.get(i).getConferencePlace());
                //举办时间
                String holdingTime = acadConfTableVoList.get(i).getHoldingTime();
                if (StringUtils.isNotBlank(holdingTime)) {
                    Cell insertCell5 = insertRow.getCell(11);
                    insertCell5.setCellValue(holdingTime.split(" ")[0]);
                }

                // 合并单元格
                if (i != acadConfTableVoList.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 2, 4);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 8, 10);
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 11, 12);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2);
                }
            }
        }

        //项目内人员重要学术组织任职情况
        List<MprAcadPostTableVo> acadPostTableVoList = annexOneVo.getAcadPostTableVoList();
        if (CollectionUtils.isNotEmpty(acadPostTableVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, acadPostTableVoList.size() - 1, "acadPostTable");
            for (int i = 0; i < acadPostTableVoList.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //学术任职组织/期刊名称
                Cell insertCell = insertRow.getCell (2);
                insertCell.setCellValue(acadPostTableVoList.get(i).getAcadEmpOrg());
                //组织/期刊级别
                Cell insertCell1 = insertRow.getCell(5);
                insertCell1.setCellValue(acadPostTableVoList.get(i).getDomesticForeign());
                //姓名
                Cell insertCell11 = insertRow.getCell(7);
                insertCell11.setCellValue(MapUtils.getString(personJoinMap, acadPostTableVoList.get(i).getName()));
                //任职职务
                Cell insertCell2 = insertRow.getCell(10);
                insertCell2.setCellValue(acadPostTableVoList.get(i).getPosition());
                // 合并单元格
                if (i != acadPostTableVoList.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 2, 4);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 5, 6);
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 7, 9);
                    CellRangeAddress region3 = new CellRangeAddress(startRow + i, startRow + i, 10, 12);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2);
                    sheet.addMergedRegion(region3);
                }
            }
        }
        List<MprJouPostTableVo> jouPostTableVoList = annexOneVo.getJouPostTableVoList();
        if (CollectionUtils.isNotEmpty(jouPostTableVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, jouPostTableVoList.size() - 1, "jouPostTable");

            for (int i = 0; i < jouPostTableVoList.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //学术任职组织/期刊名称
                Cell insertCell = insertRow.getCell (2);
                insertCell.setCellValue(jouPostTableVoList.get(i).getJouName());
                //组织/期刊级别
                Cell insertCell1 = insertRow.getCell(7);
                insertCell1.setCellValue(jouPostTableVoList.get(i).getJouLevel());
                //任职职务
                Cell insertCell2 = insertRow.getCell(10);
                insertCell2.setCellValue(jouPostTableVoList.get(i).getPosition());
                // 合并单元格
                if (i != jouPostTableVoList.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 2, 6);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 7, 9);
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 10, 12);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2);
                }
            }
        }

        //人才培养及团队建设
        //高层次人才培养情况
        List<MprEvaHighLevelTableVo> highLevelTableVoList = annexOneVo.getHighLevelTableVoList();
        if (CollectionUtils.isNotEmpty(highLevelTableVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, highLevelTableVoList.size() - 1, "highLevelTable");
            for (int i = 0; i < highLevelTableVoList.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //姓名
                Cell insertCell = insertRow.getCell (2);
                insertCell.setCellValue(MapUtils.getString(personJoinMap, highLevelTableVoList.get(i).getName()));
                //人才类型
                Cell insertCell1 = insertRow.getCell(3);
                insertCell1.setCellValue(highLevelTableVoList.get(i).getTalentType());
                //批准编号
                Cell insertCell2 = insertRow.getCell(6);
                insertCell2.setCellValue(highLevelTableVoList.get(i).getApprovalNumber());
                //批次
                Cell insertCell3 = insertRow.getCell(8);
                insertCell3.setCellValue(highLevelTableVoList.get(i).getBatch());
                //当选时间
                String electedDate = highLevelTableVoList.get(i).getElectedDate();
                if (StringUtils.isNotBlank(electedDate)) {
                    Cell insertCell4 = insertRow.getCell(10);
                    insertCell4.setCellValue(electedDate.split("-")[0]);
                }
                //单位
                Cell insertCell5 = insertRow.getCell(11);
                insertCell5.setCellValue(highLevelTableVoList.get(i).getUnit());
                // 合并单元格
                if (i != highLevelTableVoList.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 3, 5);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 6, 7);
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 8, 9);
                    CellRangeAddress region3 = new CellRangeAddress(startRow + i, startRow + i, 11, 12);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2);
                    sheet.addMergedRegion(region3);
                }
            }
        }

        //团队各任务间协作成果
        List<MprEvaCoopResultTableVo> coopResultTableVoList = annexOneVo.getCoopResultTableVoList();
        if (CollectionUtils.isNotEmpty(coopResultTableVoList)) {
            int startRow = excelExport.insertBlankRow(workbook, coopResultTableVoList.size() - 1, "coopResultTable");
            for (int i = 0; i < coopResultTableVoList.size(); i++) {
                Row insertRow = sheet.getRow(startRow + i);
                //成果名称
                Cell insertCell = insertRow.getCell (2);
                insertCell.setCellValue(coopResultTableVoList.get(i).getResultName());
                //成果类型
                Cell insertCell1 = insertRow.getCell(5);
                insertCell1.setCellValue(coopResultTableVoList.get(i).getResultType());
                //协作单位
                Cell insertCell2 = insertRow.getCell(6);
                insertCell2.setCellValue(coopResultTableVoList.get(i).getCoopUnit());
                //协作人
                Cell insertCell3 = insertRow.getCell(8);
                insertCell3.setCellValue(getNameByIds(personJoinMap, coopResultTableVoList.get(i).getCoopPerson()));
                //完成人员所在的任务
                Cell insertCell4 = insertRow.getCell(10);
                insertCell4.setCellValue(MapUtils.getString(taskMap, coopResultTableVoList.get(i).getCompletePersonTask(), ""));
                // 合并单元格
                if (i != coopResultTableVoList.size() - 1) {
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 2, 4);
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 6, 7);
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 8, 9);
                    CellRangeAddress region3 = new CellRangeAddress(startRow + i, startRow + i, 10, 12);
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2);
                    sheet.addMergedRegion(region3);
                }
            }
        }
    }

    private Map<String, Object> beanToMap(MprEvaBaseInfoVo baseInfoVo) {
        Map<String, Object> map = Maps.newHashMap();
        Class clazz = baseInfoVo.getClass();
        Field[] voFields = clazz.getDeclaredFields();
        try {
            for (Field field : voFields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(baseInfoVo));
            }

            if (StringUtils.equals(baseInfoVo.getProjectCategory(), ProjectCategoryEnums.INNOVATE_PRE.getDesc())) {
                map.put("takeUnit", "");
            }

            // 项目类型
            List<String> typeList = Lists.newArrayList(baseInfoVo.getProjectType());
            Map<String, String> typeMap = new HashMap<>();
            typeMap.put("重大项目-前沿研究", "重大项目-前沿研究");
            typeMap.put("重大项目-平台建设与基础性科技工作", "重大项目-平台建设与基础性科技工作");
            typeMap.put("协同创新团队项目", "协同创新团队项目");
            typeMap.put("青年团队项目", "青年团队项目");
            typeMap.put("一带一路", "一带一路");
            typeMap.put("人工智能", "人工智能");
            typeMap.put("健康长寿", "健康长寿");
            List<String> projectTypeList = WordConvertUtil.parseCodeListToText(typeMap, typeList);
            StringBuilder typeStr = new StringBuilder();
            for (String s : projectTypeList) {
                typeStr.append(" ").append(s);
            }
            map.put("projectType", typeStr.toString());

            // 项目分类
            String classification = baseInfoVo.getProjectClassification();
            if (StringUtils.isNotBlank(classification)) {
                List<String> classificationList = JSON.parseArray(classification, String.class);
                if (CollectionUtils.isNotEmpty(classificationList)) {
                    Map<String, String> classificationMap = new HashMap<>();
                    classificationMap.put("药械研发", "药械研发");
                    classificationMap.put("疾病防治", "疾病防治");
                    classificationMap.put("基础性科技工作", "基础性科技工作");
                    classificationMap.put("技术支撑", "技术支撑");
                    classificationMap.put("政策支撑", "政策支撑");
                    classificationMap.put("先导专项", "先导专项");
                    classificationMap.put("基础前沿", "基础前沿");
                    List<String> projectClassificationListList = WordConvertUtil.parseCodeListToText(classificationMap, classificationList);
                    StringBuilder classificationStr = new StringBuilder();
                    for (String s : projectClassificationListList) {
                        classificationStr.append(" ").append(s);
                    }
                    map.put("projectClassification", classificationStr.toString());
                }
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String beginTime = "";
            String endTime = "";
            if (null != baseInfoVo.getExecutionStartTime()) {
                beginTime = baseInfoVo.getExecutionStartTime().format(formatter);
            }
            if (null != baseInfoVo.getExecutionEndTime()) {
                endTime = baseInfoVo.getExecutionEndTime().format(formatter);
            }
            map.put("executionTime", beginTime + " 至 " + endTime);
            if (StringUtils.isNotBlank(baseInfoVo.getOtherCase())) {
                map.put("otherCase", StringEscapeUtils.unescapeHtml4(WordConvertUtil.htmlToText(baseInfoVo.getOtherCase())));
            }
            if (StringUtils.isNotBlank(baseInfoVo.getAnnexStat())) {
                JSONObject annexStatObj = JSONObject.parseObject(baseInfoVo.getAnnexStat());
                JSONArray personnelStatisticsArray = annexStatObj.getJSONArray("personnelStatistics");
                Map<String, Object> personnelStatisticsMap = (Map<String, Object>) personnelStatisticsArray.get(0);
                if (MapUtils.isNotEmpty(personnelStatisticsMap)) {
                    map.put("type1", "研究人员 " + MapUtils.getString(personnelStatisticsMap, "type1", "__") + " 人");
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "senior1"))) {
                        map.put("senior1", MapUtils.getString(personnelStatisticsMap, "senior1"));
                    }
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "subSenior1"))) {
                        map.put("subSenior1", MapUtils.getString(personnelStatisticsMap, "subSenior1"));
                    }
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "middle1"))) {
                        map.put("middle1", MapUtils.getString(personnelStatisticsMap, "middle1"));
                    }
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "primary1"))) {
                        map.put("primary1", MapUtils.getString(personnelStatisticsMap, "primary1"));
                    }
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "others1"))) {
                        map.put("others1", MapUtils.getString(personnelStatisticsMap, "others1"));
                    }
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "doctor1"))) {
                        map.put("doctor1", MapUtils.getString(personnelStatisticsMap, "doctor1"));
                    }
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "master1"))) {
                        map.put("master1", MapUtils.getString(personnelStatisticsMap, "master1"));
                    }
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "bachelor1"))) {
                        map.put("bachelor1", MapUtils.getString(personnelStatisticsMap, "bachelor1"));
                    }
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "otherDegrees1"))) {
                        map.put("otherDegrees1", MapUtils.getString(personnelStatisticsMap, "otherDegrees1"));
                    }

                    map.put("type2", "技术人员 " + MapUtils.getString(personnelStatisticsMap, "type2", "__") + " 人");
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "senior2"))) {
                        map.put("senior2", MapUtils.getString(personnelStatisticsMap, "senior2"));
                    }
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "subSenior2"))) {
                        map.put("subSenior2", MapUtils.getString(personnelStatisticsMap, "subSenior2"));
                    }
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "middle2"))) {
                        map.put("middle2", MapUtils.getString(personnelStatisticsMap, "middle2"));
                    }
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "primary2"))) {
                        map.put("primary2", MapUtils.getString(personnelStatisticsMap, "primary2"));
                    }
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "others2"))) {
                        map.put("others2", MapUtils.getString(personnelStatisticsMap, "others2"));
                    }
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "doctor2"))) {
                        map.put("doctor2", MapUtils.getString(personnelStatisticsMap, "doctor2"));
                    }
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "master2"))) {
                        map.put("master2", MapUtils.getString(personnelStatisticsMap, "master2"));
                    }
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "bachelor2"))) {
                        map.put("bachelor2", MapUtils.getString(personnelStatisticsMap, "bachelor2"));
                    }
                    if (StringUtils.isNotBlank(MapUtils.getString(personnelStatisticsMap, "otherDegrees1"))) {
                        map.put("otherDegrees2", MapUtils.getString(personnelStatisticsMap, "otherDegrees2"));
                    }

                    map.put("type3", "博士后人员 " + MapUtils.getString(personnelStatisticsMap, "type3", "__") + " 人");
                    map.put("type0", "总人数 " + MapUtils.getString(personnelStatisticsMap, "type0", "__") + " 人");
                }

                //新理论、新技术、新产品等情况
                Map<String, Object> theoTechProStatisticsMap = (Map<String, Object>) annexStatObj.getJSONArray("theoTechProStatistics").get(0);
                map.put("theoTech1", MapUtils.getString(theoTechProStatisticsMap, "item1"));
                map.put("theoTech2", MapUtils.getString(theoTechProStatisticsMap, "item4"));
                map.put("theoTech3", MapUtils.getString(theoTechProStatisticsMap, "item8"));
                map.put("theoTech4", MapUtils.getString(theoTechProStatisticsMap, "item2"));
                map.put("theoTech5", MapUtils.getString(theoTechProStatisticsMap, "item5"));
                map.put("theoTech6", MapUtils.getString(theoTechProStatisticsMap, "item9"));
                map.put("theoTech7", MapUtils.getString(theoTechProStatisticsMap, "item10"));
                map.put("theoTech8", MapUtils.getString(theoTechProStatisticsMap, "item6"));
                map.put("theoTech9", MapUtils.getString(theoTechProStatisticsMap, "item11"));
                map.put("theoTech10", MapUtils.getString(theoTechProStatisticsMap, "item3"));
                map.put("theoTech11", MapUtils.getString(theoTechProStatisticsMap, "item7"));
                map.put("theoTech12", MapUtils.getString(theoTechProStatisticsMap, "item12"));

                //成果转化情况
                Map<String, Object> achievementStatisticsMap = (Map<String, Object>) annexStatObj.getJSONArray("achievementStatistics").get(0);
                map.put("achievement1", MapUtils.getString(achievementStatisticsMap, "num"));
                map.put("achievement2", MapUtils.getString(achievementStatisticsMap, "totalmoney"));

                //示范推广服务情况
                Map<String, Object> demonstrationStatisticsMap = (Map<String, Object>) annexStatObj.getJSONArray("demonstrationStatistics").get(0);
                map.put("demonstration1", MapUtils.getString(demonstrationStatisticsMap, "baseNumber"));
                map.put("demonstration2", MapUtils.getString(demonstrationStatisticsMap, "area"));
                map.put("demonstration3", MapUtils.getString(demonstrationStatisticsMap, "times"));

                //论文发表情况
                Map<String, Object> paperStatisticsMap = (Map<String, Object>) annexStatObj.getJSONArray("paperStatistics").get(0);
                map.put("paperStatistics1", MapUtils.getString(paperStatisticsMap, "num"));
                map.put("paperStatistics2", MapUtils.getString(paperStatisticsMap, "fnum"));
                map.put("paperStatistics3", MapUtils.getString(paperStatisticsMap, "scinum"));
                map.put("paperStatistics4", MapUtils.getString(paperStatisticsMap, "einum"));
                map.put("paperStatistics5", MapUtils.getString(paperStatisticsMap, "sscinum"));
                map.put("paperStatistics6", MapUtils.getString(paperStatisticsMap, "csscinum"));
                map.put("paperStatistics7", MapUtils.getString(paperStatisticsMap, "cscdnum"));
                map.put("paperStatistics8", MapUtils.getString(paperStatisticsMap, "inum"));
                map.put("paperStatistics9", MapUtils.getString(paperStatisticsMap, "iinum"));

                //专著、教材发表情况
                Map<String, Object> monographStatisticsMap = (Map<String, Object>) annexStatObj.getJSONArray("monographStatistics").get(0);
                map.put("monograph1", MapUtils.getString(monographStatisticsMap, "monographLevel1"));
                map.put("monograph2", MapUtils.getString(monographStatisticsMap, "monographLevel2"));
                map.put("monograph3", MapUtils.getString(monographStatisticsMap, "monographLevel3"));
                map.put("monograph4", MapUtils.getString(monographStatisticsMap, "compileLevel1"));
                map.put("monograph5", MapUtils.getString(monographStatisticsMap, "compileLevel2"));
                map.put("monograph6", MapUtils.getString(monographStatisticsMap, "compileLevel3"));
                map.put("monograph7", MapUtils.getString(monographStatisticsMap, "translatedLevel1"));
                map.put("monograph8", MapUtils.getString(monographStatisticsMap, "translatedLevel2"));
                map.put("monograph9", MapUtils.getString(monographStatisticsMap, "translatedLevel3"));
                map.put("monograph10", MapUtils.getString(monographStatisticsMap, "teachMonographLevel1"));
                map.put("monograph11", MapUtils.getString(monographStatisticsMap, "teachMonographLevel2"));
                map.put("monograph12", MapUtils.getString(monographStatisticsMap, "teachMonographLevel3"));
                map.put("monograph13", MapUtils.getString(monographStatisticsMap, "teachCompileLevel1"));
                map.put("monograph14", MapUtils.getString(monographStatisticsMap, "teachCompileLevel2"));
                map.put("monograph15", MapUtils.getString(monographStatisticsMap, "teachCompileLevel3"));
                map.put("monograph16", MapUtils.getString(monographStatisticsMap, "teachTranslatedLevel1"));
                map.put("monograph17", MapUtils.getString(monographStatisticsMap, "teachTranslatedLevel2"));
                map.put("monograph18", MapUtils.getString(monographStatisticsMap, "teachTranslatedLevel3"));

                //专利申请授权情况
                Map<String, Object> patentStatisticsMap = (Map<String, Object>) annexStatObj.getJSONArray("patentStatistics").get(0);
                map.put("patentStatistics1", MapUtils.getString(patentStatisticsMap, "international"));
                map.put("patentStatistics2", MapUtils.getString(patentStatisticsMap, "domestic"));
                map.put("patentStatistics3", MapUtils.getString(patentStatisticsMap, "international1"));
                map.put("patentStatistics4", MapUtils.getString(patentStatisticsMap, "domestic1"));
                map.put("patentStatistics5", MapUtils.getString(patentStatisticsMap, "inventInternational"));
                map.put("patentStatistics6", MapUtils.getString(patentStatisticsMap, "inventDomestic"));
                map.put("patentStatistics7", MapUtils.getString(patentStatisticsMap, "inventInternational1"));
                map.put("patentStatistics8", MapUtils.getString(patentStatisticsMap, "inventDomestic1"));

                //获得新药、疫苗、医疗器械证书数、临床批件数
                Map<String, Object> newMedicineStatisticsMap = (Map<String, Object>) annexStatObj.getJSONArray("newMedicineStatistics").get(0);
                map.put("newMedicine1", MapUtils.getString(newMedicineStatisticsMap, "numberTest"));
                map.put("newMedicine2", MapUtils.getString(newMedicineStatisticsMap, "total"));
                map.put("newMedicine3", MapUtils.getString(newMedicineStatisticsMap, "numberBatch"));
                map.put("newMedicine4", MapUtils.getString(newMedicineStatisticsMap, "classTotal"));
                map.put("newMedicine5", MapUtils.getString(newMedicineStatisticsMap, "number1"));
                map.put("newMedicine6", MapUtils.getString(newMedicineStatisticsMap, "number2"));
                map.put("newMedicine7", MapUtils.getString(newMedicineStatisticsMap, "number3"));
                map.put("newMedicine8", MapUtils.getString(newMedicineStatisticsMap, "total"));
                map.put("newMedicine9", MapUtils.getString(newMedicineStatisticsMap, "class1"));
                map.put("newMedicine10", MapUtils.getString(newMedicineStatisticsMap, "class2"));
                map.put("newMedicine11", MapUtils.getString(newMedicineStatisticsMap, "class3"));
                map.put("newMedicine12", MapUtils.getString(newMedicineStatisticsMap, "class4"));
                map.put("newMedicine13", MapUtils.getString(newMedicineStatisticsMap, "chemistryMedicine1"));
                map.put("newMedicine14", MapUtils.getString(newMedicineStatisticsMap, "chemistryMedicine2"));
                map.put("newMedicine15", MapUtils.getString(newMedicineStatisticsMap, "chemistryMedicine3"));
                map.put("newMedicine16", MapUtils.getString(newMedicineStatisticsMap, "chineseMedicine1"));
                map.put("newMedicine17", MapUtils.getString(newMedicineStatisticsMap, "chineseMedicine2"));
                map.put("newMedicine18", MapUtils.getString(newMedicineStatisticsMap, "chineseMedicine3"));
                map.put("newMedicine19", MapUtils.getString(newMedicineStatisticsMap, "chineseMedicine4"));
                map.put("newMedicine20", MapUtils.getString(newMedicineStatisticsMap, "biologyMedicine1"));
                map.put("newMedicine21", MapUtils.getString(newMedicineStatisticsMap, "biologyMedicine2"));
                map.put("newMedicine22", MapUtils.getString(newMedicineStatisticsMap, "biologyMedicine3"));

                //技术标准获批情况
                Map<String, Object> skillStatisticsMap = (Map<String, Object>) annexStatObj.getJSONArray("skillStatistics").get(0);
                map.put("skillStatistics1", MapUtils.getString(skillStatisticsMap, "international"));
                map.put("skillStatistics2", MapUtils.getString(skillStatisticsMap, "domestic"));
                map.put("skillStatistics3", MapUtils.getString(skillStatisticsMap, "trade"));
                map.put("skillStatistics4", MapUtils.getString(skillStatisticsMap, "other"));

                //科技奖励
                Map<String, Object> scienceAwardStatisticsMap = (Map<String, Object>) annexStatObj.getJSONArray("scienceAwardStatistics").get(0);
                map.put("scienceAward1", MapUtils.getString(scienceAwardStatisticsMap, "national1"));
                map.put("scienceAward2", MapUtils.getString(scienceAwardStatisticsMap, "national2"));
                map.put("scienceAward3", MapUtils.getString(scienceAwardStatisticsMap, "national3"));
                map.put("scienceAward4", MapUtils.getString(scienceAwardStatisticsMap, "national4"));
                map.put("scienceAward5", MapUtils.getString(scienceAwardStatisticsMap, "national5"));
                map.put("scienceAward6", MapUtils.getString(scienceAwardStatisticsMap, "national6"));
                map.put("scienceAward7", MapUtils.getString(scienceAwardStatisticsMap, "national7"));
                map.put("scienceAward8", MapUtils.getString(scienceAwardStatisticsMap, "national8"));
                map.put("scienceAward9", MapUtils.getString(scienceAwardStatisticsMap, "provincial1"));
                map.put("scienceAward10", MapUtils.getString(scienceAwardStatisticsMap, "provincial2"));
                map.put("scienceAward11", MapUtils.getString(scienceAwardStatisticsMap, "provincial3"));
                map.put("scienceAward12", MapUtils.getString(scienceAwardStatisticsMap, "provincial4"));
                map.put("scienceAward13", MapUtils.getString(scienceAwardStatisticsMap, "provincial5"));
                map.put("scienceAward14", MapUtils.getString(scienceAwardStatisticsMap, "provincial6"));
                map.put("scienceAward15", MapUtils.getString(scienceAwardStatisticsMap, "provincial7"));
                map.put("scienceAward16", MapUtils.getString(scienceAwardStatisticsMap, "provincial8"));
                map.put("scienceAward17", MapUtils.getString(scienceAwardStatisticsMap, "provincial9"));
                map.put("scienceAward18", MapUtils.getString(scienceAwardStatisticsMap, "provincial10"));
                map.put("scienceAward19", MapUtils.getString(scienceAwardStatisticsMap, "medicine1"));
                map.put("scienceAward20", MapUtils.getString(scienceAwardStatisticsMap, "medicine2"));
                map.put("scienceAward21", MapUtils.getString(scienceAwardStatisticsMap, "medicine3"));

                //项目内人员牵头国家重大科研任务情况
                JSONArray esearchTaskArray = annexStatObj.getJSONArray("esearchTask");
                Map<String, Object> esearchTaskMap1 = (Map<String, Object>) esearchTaskArray.get(0);
                Map<String, Object> esearchTaskMap2 = (Map<String, Object>) esearchTaskArray.get(1);
                map.put("esearchTask1", MapUtils.getString(esearchTaskMap1, "item1"));
                map.put("esearchTask2", MapUtils.getString(esearchTaskMap2, "item1"));
                map.put("esearchTask3", MapUtils.getString(esearchTaskMap1, "item3"));
                map.put("esearchTask4", MapUtils.getString(esearchTaskMap2, "item3"));
                map.put("esearchTask5", MapUtils.getString(esearchTaskMap1, "item5"));
                map.put("esearchTask6", MapUtils.getString(esearchTaskMap2, "item5"));
                map.put("esearchTask7", MapUtils.getString(esearchTaskMap1, "item7"));
                map.put("esearchTask8", MapUtils.getString(esearchTaskMap2, "item7"));
                map.put("esearchTask9", MapUtils.getString(esearchTaskMap1, "item9"));
                map.put("esearchTask10", MapUtils.getString(esearchTaskMap2, "item9"));
                map.put("esearchTask11", MapUtils.getString(esearchTaskMap1, "item11"));
                map.put("esearchTask12", MapUtils.getString(esearchTaskMap2, "item11"));
                map.put("esearchTask13", MapUtils.getString(esearchTaskMap1, "item13"));
                map.put("esearchTask14", MapUtils.getString(esearchTaskMap2, "item13"));
                map.put("esearchTask15", MapUtils.getString(esearchTaskMap1, "item15"));
                map.put("esearchTask16", MapUtils.getString(esearchTaskMap2, "item15"));
                map.put("esearchTask17", MapUtils.getString(esearchTaskMap1, "item2"));
                map.put("esearchTask18", MapUtils.getString(esearchTaskMap2, "item2"));
                map.put("esearchTask19", MapUtils.getString(esearchTaskMap1, "item4"));
                map.put("esearchTask20", MapUtils.getString(esearchTaskMap2, "item4"));
                map.put("esearchTask21", MapUtils.getString(esearchTaskMap1, "item6"));
                map.put("esearchTask22", MapUtils.getString(esearchTaskMap2, "item6"));
                map.put("esearchTask23", MapUtils.getString(esearchTaskMap1, "item8"));
                map.put("esearchTask24", MapUtils.getString(esearchTaskMap2, "item8"));
                map.put("esearchTask25", MapUtils.getString(esearchTaskMap1, "item10"));
                map.put("esearchTask26", MapUtils.getString(esearchTaskMap2, "item10"));
                map.put("esearchTask27", MapUtils.getString(esearchTaskMap1, "item12"));
                map.put("esearchTask28", MapUtils.getString(esearchTaskMap2, "item12"));
                map.put("esearchTask29", MapUtils.getString(esearchTaskMap1, "item14"));
                map.put("esearchTask30", MapUtils.getString(esearchTaskMap2, "item14"));
                map.put("esearchTask31", MapUtils.getString(esearchTaskMap1, "item16"));
                map.put("esearchTask32", MapUtils.getString(esearchTaskMap2, "item16"));
                map.put("esearchTask33", MapUtils.getString(esearchTaskMap1, "item17"));
                map.put("esearchTask34", MapUtils.getString(esearchTaskMap1, "item18"));

                //举办学术会议情况
                Map<String, Object> academicConStatisticsMap = (Map<String, Object>) annexStatObj.getJSONArray("academicConStatistics").get(0);
                map.put("academicCon1", MapUtils.getString(academicConStatisticsMap, "international"));
                map.put("academicCon2", MapUtils.getString(academicConStatisticsMap, "domestic"));
                map.put("academicCon3", MapUtils.getString(academicConStatisticsMap, "area"));

                //项目内人员重要学术组织或期刊任职情况
                Map<String, Object> organizeStatistics1Map = (Map<String, Object>) annexStatObj.getJSONArray("organizeStatistics1").get(0);
                map.put("organizeStatistics1", MapUtils.getString(organizeStatistics1Map, "director"));
                map.put("organizeStatistics2", MapUtils.getString(organizeStatistics1Map, "viceDirector"));
                map.put("organizeStatistics3", MapUtils.getString(organizeStatistics1Map, "secretary"));
                map.put("organizeStatistics4", MapUtils.getString(organizeStatistics1Map, "chiefEdito1"));
                map.put("organizeStatistics5", MapUtils.getString(organizeStatistics1Map, "chiefEdito2"));
                map.put("organizeStatistics6", MapUtils.getString(organizeStatistics1Map, "vicechiefEdito1"));
                map.put("organizeStatistics7", MapUtils.getString(organizeStatistics1Map, "vicechiefEdito2"));
                map.put("organizeStatistics8", MapUtils.getString(organizeStatistics1Map, "edito1"));
                map.put("organizeStatistics9", MapUtils.getString(organizeStatistics1Map, "edito2"));

                JSONArray internalLetterStatistics1Array = annexStatObj.getJSONArray("internalLetterStatistics1");
                for (int i = 0; i < internalLetterStatistics1Array.size(); i++) {
                    Map<String, Object> internalLetterStatistics1Map = (Map<String, Object>) internalLetterStatistics1Array.get(i);
                    int temp = i * 3;
                    map.put("internalLetter" + (temp + 1), MapUtils.getString(internalLetterStatistics1Map, "director"));
                    map.put("internalLetter" + (temp + 2), MapUtils.getString(internalLetterStatistics1Map, "viceDirector"));
                    map.put("internalLetter" + (temp + 3), MapUtils.getString(internalLetterStatistics1Map, "secretary"));
                }
                JSONArray internalLetterStatistics2Array = annexStatObj.getJSONArray("internalLetterStatistics2");
                for (int i = 0; i < internalLetterStatistics2Array.size(); i++) {
                    Map<String, Object> internalLetterStatistics2Map = (Map<String, Object>) internalLetterStatistics2Array.get(i);
                    int temp = i * 3;
                    map.put("internalLetterr" + (temp + 1), MapUtils.getString(internalLetterStatistics2Map, "director"));
                    map.put("internalLetterr" + (temp + 2), MapUtils.getString(internalLetterStatistics2Map, "viceDirector"));
                    map.put("internalLetterr" + (temp + 3), MapUtils.getString(internalLetterStatistics2Map, "secretary"));
                }

                //高层次人才
                Map<String, Object> talentStatisticsMap = (Map<String, Object>) annexStatObj.getJSONArray("talentStatistics").get(0);
                map.put("talentStatistics1", MapUtils.getString(talentStatisticsMap, "talent1"));
                map.put("talentStatistics2", MapUtils.getString(talentStatisticsMap, "talent2"));
                map.put("talentStatistics3", MapUtils.getString(talentStatisticsMap, "talent3"));
                map.put("talentStatistics4", MapUtils.getString(talentStatisticsMap, "talent4"));
                map.put("talentStatistics5", MapUtils.getString(talentStatisticsMap, "talent5"));
                map.put("talentStatistics6", MapUtils.getString(talentStatisticsMap, "talent6"));
                map.put("talentStatistics7", MapUtils.getString(talentStatisticsMap, "talent7"));
                map.put("talentStatistics8", MapUtils.getString(talentStatisticsMap, "talent8"));
                map.put("talentStatistics9", MapUtils.getString(talentStatisticsMap, "talent9"));
                map.put("talentStatistics10", MapUtils.getString(talentStatisticsMap, "talent10"));

            }

        } catch (IllegalAccessException e) {
            log.error("转换错误：非法访问", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        }
        return map;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<MprEvaBaseInfo> getQueryWrapper(QueryWrapper<MprEvaBaseInfo> queryWrapper,MprEvaBaseInfoQueryForm queryForm){
        //条件拼接
        if(null!=queryForm.getId() && 0L != queryForm.getId()){
            // 是PROJECT_ID
            queryWrapper.eq(MprEvaBaseInfo.ID,queryForm.getId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.like(MprEvaBaseInfo.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectName())){
            queryWrapper.like(MprEvaBaseInfo.PROJECT_NAME,queryForm.getProjectName());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectObjectives())){
            queryWrapper.eq(MprEvaBaseInfo.PROJECT_OBJECTIVES,queryForm.getProjectObjectives());
        }
        if(StringUtils.isNotBlank(queryForm.getJobQuantity())){
            queryWrapper.eq(MprEvaBaseInfo.JOB_QUANTITY,queryForm.getJobQuantity());
        }
        if(StringUtils.isNotBlank(queryForm.getBudget())){
            queryWrapper.eq(MprEvaBaseInfo.BUDGET,queryForm.getBudget());
        }
        if (null != queryForm.getExecutionStartTime()) {
            queryWrapper.eq(MprEvaBaseInfo.EXECUTION_START_TIME, queryForm.getExecutionStartTime());
        }
        if (null != queryForm.getExecutionEndTime()) {
            queryWrapper.eq(MprEvaBaseInfo.EXECUTION_END_TIME, queryForm.getExecutionEndTime());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectCategory())){
            queryWrapper.eq(MprEvaBaseInfo.PROJECT_CATEGORY,queryForm.getProjectCategory());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectClassification())){
            queryWrapper.eq(MprEvaBaseInfo.PROJECT_CLASSIFICATION,queryForm.getProjectClassification());
        }
        if(StringUtils.isNotBlank(queryForm.getLeadUnit())){
            queryWrapper.eq(MprEvaBaseInfo.LEAD_UNIT,queryForm.getLeadUnit());
        }
        if(StringUtils.isNotBlank(queryForm.getTakeUnit())){
            queryWrapper.eq(MprEvaBaseInfo.TAKE_UNIT,queryForm.getTakeUnit());
        }
        if(StringUtils.isNotBlank(queryForm.getChiefSpecialist())){
            queryWrapper.eq(MprEvaBaseInfo.CHIEF_SPECIALIST,queryForm.getChiefSpecialist());
        }
        if(StringUtils.isNotBlank(queryForm.getJointChiefSpecialist())){
            queryWrapper.eq(MprEvaBaseInfo.JOINT_CHIEF_SPECIALIST,queryForm.getJointChiefSpecialist());
        }
        if(StringUtils.isNotBlank(queryForm.getProgressState())){
            queryWrapper.eq(MprEvaBaseInfo.PROGRESS_STATE,queryForm.getProgressState());
        }
        if(StringUtils.isNotBlank(queryForm.getWorkState())){
            queryWrapper.eq(MprEvaBaseInfo.WORK_STATE,queryForm.getWorkState());
        }
        if(StringUtils.isNotBlank(queryForm.getPostdoctoralNum())){
            queryWrapper.eq(MprEvaBaseInfo.POSTDOCTORAL_NUM,queryForm.getPostdoctoralNum());
        }
        if(StringUtils.isNotBlank(queryForm.getDoctoralNum())){
            queryWrapper.eq(MprEvaBaseInfo.DOCTORAL_NUM,queryForm.getDoctoralNum());
        }
        if(StringUtils.isNotBlank(queryForm.getMasterNum())){
            queryWrapper.eq(MprEvaBaseInfo.MASTER_NUM,queryForm.getMasterNum());
        }
        if(StringUtils.isNotBlank(queryForm.getOtherCase())){
            queryWrapper.eq(MprEvaBaseInfo.OTHER_CASE,queryForm.getOtherCase());
        }
        if (null != queryForm.getCreateTime()) {
            queryWrapper.eq(MprEvaBaseInfo.CREATE_TIME, queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaBaseInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if (null != queryForm.getUpdateTime()) {
            queryWrapper.eq(MprEvaBaseInfo.UPDATE_TIME, queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaBaseInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessStatus())){
            queryWrapper.eq(MprEvaBaseInfo.PROCESS_STATUS,queryForm.getProcessStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectTypeCode())){
            queryWrapper.eq(MprEvaBaseInfo.PROJECT_TYPE_CODE,queryForm.getProjectTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectType())){
            queryWrapper.eq(MprEvaBaseInfo.PROJECT_TYPE,queryForm.getProjectType());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyDeptCode())){
            queryWrapper.eq(MprEvaBaseInfo.APPLY_DEPT_CODE,queryForm.getApplyDeptCode());
        }
        if(null!=queryForm.getReportId() && 0L != queryForm.getReportId()){
            queryWrapper.eq(MprEvaBaseInfo.REPORT_ID,queryForm.getReportId());
        }
        if(StringUtils.isNotBlank(queryForm.getReportType())){
            queryWrapper.eq(MprEvaBaseInfo.REPORT_TYPE,queryForm.getReportType());
        }
        if(null!=queryForm.getReportYear() && 0L != queryForm.getReportYear()){
            queryWrapper.eq(MprEvaBaseInfo.REPORT_YEAR,queryForm.getReportYear());
        }
        if(StringUtils.isNotBlank(queryForm.getRepOtherType())){
            queryWrapper.eq(MprEvaBaseInfo.REP_OTHER_TYPE,queryForm.getRepOtherType());
        }
        return queryWrapper;
    }

//    @Override
//    public IPage<MprEvaBaseInfo> selectPage(MprEvaBaseInfoQueryForm queryForm, UserVo user, DeptVo dept) {
//        String roleCode = user.getHonor();
//        String deptCode = dept.getDeptCode();
//        String proType = user.getRemark();
//
//        if (SrpmsConstant.SRPMS_ADMIN.equals(roleCode)) {
//            if (SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode) && StringUtils.isBlank(queryForm.getProjectTypeCode())) {
//                queryForm.setProjectTypeCode(proType);
//                queryForm.setLeadDeptId(null);
//            } else {
//                queryForm.setLeadDeptId(new Long(dept.getDeptId()));
//            }
//        } else if (SrpmsConstant.SRPMS_LEADER.equals(roleCode)) {
//            if (!SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
//                queryForm.setLeadDeptId(new Long(dept.getDeptId()));
//            } else {
//                queryForm.setLeadDeptId(null);
//            }
//        } else {
//            queryForm.setLeadPersonId(new Long(user.getId()));
//        }
//
//        QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper <>();
//        getQueryWrapper(queryWrapper, queryForm);
//
//        return mprEvaBaseInfoMapper.selectPage(new Page<>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
//
//    }

    @Override
    public IPage<MprEvaBaseInfoVo> selectList(MprEvaBaseInfoQueryForm queryForm, UserVo user, DeptVo dept) {
        QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper <>();
        getQueryWrapper(queryWrapper, queryForm);
        if (StringUtils.equals("1", queryForm.getQueryType())) {
            //我的申请（查询未提交报告）
            queryWrapper.eq(MprEvaBaseInfo.PROCESS_STATUS, EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
            queryWrapper.eq(MprEvaBaseInfo.CREATE_BY, user.getId());
        } else if (StringUtils.equals("2", queryForm.getQueryType())) {
            //我的报告（查询审批通过报告）
            queryWrapper.eq(MprEvaBaseInfo.PROCESS_STATUS, EnumMprProcessStatus.MPR_APPROVED.getCode());
            queryWrapper.eq(MprEvaBaseInfo.CREATE_BY, user.getId());
        } else if (StringUtils.equals("3", queryForm.getQueryType())) {
            queryWrapper.eq(MprEvaBaseInfo.PROCESS_STATUS, EnumMprProcessStatus.MPR_APPROVED.getCode());
            if (!SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(dept.getDeptCode())) {
                queryWrapper.eq(MprEvaBaseInfo.APPLY_DEPT_CODE, dept.getDeptCode());
            }
        }

        queryWrapper.orderByDesc(MprEvaBaseInfo.CREATE_TIME);

        IPage<MprEvaBaseInfo> page = mprEvaBaseInfoMapper.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
        IPage<MprEvaBaseInfoVo> voPage = new BeanUtils<MprEvaBaseInfoVo>().copyPageObjs(page, MprEvaBaseInfoVo.class);
        if (null != page && CollectionUtils.isNotEmpty(page.getRecords())) {
            List<MprEvaBaseInfoVo> voList = Lists.newArrayList();
            for(MprEvaBaseInfo info : page.getRecords()) {
                String proTypeName = dictService.selectValueByCode(SysDictEnums.PRO_CAT, info.getProjectTypeCode());
                info.setProjectTypeCode(proTypeName);
                info.setReportType(ReportTypeEnum.getDescByCode(info.getReportType()));
                MprEvaBaseInfoVo vo = new BeanUtils<MprEvaBaseInfoVo>().copyObj(info, MprEvaBaseInfoVo.class);
                voList.add(vo);
            }
            voPage.setRecords(voList);
        }
        return voPage;
    }

//    @Override
//    public List<MprEvaBaseInfo> selectOneList(MprEvaBaseInfoQueryForm queryForm, UserVo user, DeptVo dept) {
//        queryForm.setLeadPersonId(new Long(user.getId()));
//
//        // 自定义sql查询
////        Map<String, Object> paramMap = new HashMap<>();
////        paramMap.put("personId", user.getId());
////        paramMap.put("processStatus", queryForm.getProcessStatus());
//
//        QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper <MprEvaBaseInfo>();
//        getQueryWrapper(queryWrapper, queryForm);
//
//        // 流程执行状态：0未提交，10审核中，20已拒绝，30已驳回，40已同意, "我的申请"只查询状态为"未提交"的,其它查询不传入状态字段，默认不查询未提交数据
//        if(StringUtils.isBlank(queryForm.getProcessStatus())) {
//            queryWrapper.ne(MprEvaBaseInfo.PROCESS_STATUS, EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
//        } else if (StringUtils.equals(EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode(), queryForm.getProcessStatus())) {
//            queryWrapper.eq(MprEvaBaseInfo.PROCESS_STATUS, EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
//        } // 查询时随便传个值，此处不作为条件
//
//        return mprEvaBaseInfoMapper.selectList(queryWrapper); // 查询未提交的
//    }
//
//    @Override
//    public List<MprEvaBaseInfo> selectAllDeptList(MprEvaBaseInfoQueryForm queryForm, UserVo user, DeptVo dept) {
//        String roleCode = user.getHonor();
//        String deptCode = dept.getDeptCode();
//        String proType = user.getRemark();
//        if (SrpmsConstant.SRPMS_ADMIN.equals(roleCode)) {
//            if (SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode) && StringUtils.isBlank(queryForm.getProjectTypeCode())) {
//                queryForm.setProjectTypeCode(proType);
//                queryForm.setLeadDeptId(null);
//            } else {
//                queryForm.setLeadDeptId(new Long(dept.getDeptId()));
//            }
//            queryForm.setLeadPersonId(null);
//        } else if (SrpmsConstant.SRPMS_LEADER.equals(roleCode)) {
//            if (!SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
//                queryForm.setLeadDeptId(new Long(dept.getDeptId()));
//            } else {
//                queryForm.setLeadDeptId(null);
//            }
//            queryForm.setLeadPersonId(null);
//        } else {
//            // 在前端此接口不开放给一般用户，此逻辑应该永不会执行
//            throw new BaseException(SrpmsErrorType.NO_ELEMENTS);
//        }
//
//
//        QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper <MprEvaBaseInfo>();
//        getQueryWrapper(queryWrapper, queryForm);
//
//        return mprEvaBaseInfoMapper.selectList(queryWrapper); // 查询未提交的
//    }

    /**
     * 判断年度报告是否存在,条件：项目ID+年份+报告类型
     * @param queryForm
     * @return
     */
    @Override
    public JSONObject checkReportExists(MprEvaBaseInfoQueryForm queryForm) {
        if(null == queryForm) {
            throw new BaseException(SrpmsErrorType.PARAM_NULL);
        }
        JSONObject result = new JSONObject();
        QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        MprEvaBaseInfo baseInfo = this.getOne(queryWrapper);
        if (null != baseInfo) {
            result.put("exist", "true");
            result.put("processStatus", baseInfo.getProcessStatus());
        } else {
            result.put("exist", "false");
            result.put("processStatus", "");
        }
        return result;
    }

    /**
     * 根据报告其它组合唯一（如：项目ID+年份+报告类型）条件查询报告详情
     * @param queryForm
     * @return
     */
    @Override
    public JSONObject getByConditions(MprEvaBaseInfoQueryForm queryForm) {
        JSONObject json = new JSONObject();

        if(null == queryForm) {
            throw new BaseException(SrpmsErrorType.PARAM_NULL);
        }


        // ID不为空，则为查询；否则为增加，只查询项目信息
        if(null != queryForm.getReportId() && 0L != queryForm.getReportId() ) {
            // 查询年度报告基础信息
            QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper <MprEvaBaseInfo>();
            getQueryWrapper(queryWrapper, queryForm);
            MprEvaBaseInfo baseInfo = mprEvaBaseInfoMapper.selectOne(queryWrapper);
            MprEvaBaseInfoVo baseInfoVo = new BeanUtils<MprEvaBaseInfoVo>().copyObj(baseInfo, MprEvaBaseInfoVo.class);
            //json = JSONObject.parseObject(JSONObject.toJSONString(baseInfo));
            json.put("mprEvaBaseInfo", JSONObject.parseObject(JSONObject.toJSONString(baseInfoVo)));

            // 存在reportid时，代办列表中不存在projectid，因此要查一遍作为projectinfo查询条件
            queryForm.setId(baseInfo.getId());

            // 查询附件信息
            List<SrpmsProjectAttachment> attachmentList = attachmentService.queryAttachmentListReport(baseInfo.getReportType(), queryForm.getReportId());
            List<SrpmsProjectAttachmentVo> attachmentVoList = new BeanUtils<SrpmsProjectAttachmentVo>().copyObjs(attachmentList, SrpmsProjectAttachmentVo.class);
            if(null != attachmentList) {
                json.put("attachmentList", attachmentVoList);
            }

            //查询审批历史记录
            if (!EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode().equals(baseInfo.getProcessStatus())) {
                SrpmsVoucher voucherEntity = voucherService.getSrpmsVoucherByUpdateId(baseInfo.getReportId());
                if (voucherEntity != null) {
                    json.put("approveHistory", bpmService.queryAuditHistory(CommonUtil.objectToString(voucherEntity.getId())));
                }
            }
            json.put("status", baseInfo.getProcessStatus());
        }

        // 查询项目基础信息
        if(null == queryForm.getId() || 0L == queryForm.getId()) {
            throw new BaseException(SrpmsErrorType.PARAM_NULL);
        }
        SrpmsProject srpmsProject=projectService.getById(new Long(queryForm.getId()));//getProjectInfoForReport(projectForm);**

        if(null == srpmsProject) {
            throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
        }

        String typeName = dictService.selectValueByCode(SysDictEnums.PRO_CAT, srpmsProject.getProjectType());
        json.put("projectId", ""+srpmsProject.getId());
        json.put("projectTypeName", typeName);
        json.put("projectInfo", JSONObject.parseObject(JSONObject.toJSONString(srpmsProject)));

        JSONConvert.convertJson(json);
        return json;
    }

    /**
     *年度报告保存
     * @param mprSaveOrUpdateForm
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long saveOrUpdate(MprSaveOrUpdateForm mprSaveOrUpdateForm, UserVo user, DeptVo dept) {
        if (mprSaveOrUpdateForm == null || mprSaveOrUpdateForm.getBaseInfoForm() == null) {
            throw new BaseException(SrpmsErrorType.PARAM_NULL);
        }

        MprEvaBaseInfoForm baseInfoForm = mprSaveOrUpdateForm.getBaseInfoForm();
        SrpmsProject srpmsProject = projectService.getById(baseInfoForm.getId());
        if(null == srpmsProject) {
            throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
        }

        Long reportId = baseInfoForm.getReportId();
        String processStatus = EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode();
        if(null != reportId && 0L != reportId){
            QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper <MprEvaBaseInfo>();
            MprEvaBaseInfoQueryForm queryForm = new MprEvaBaseInfoQueryForm();
            queryForm.setReportId(reportId);
            getQueryWrapper(queryWrapper, queryForm);
            MprEvaBaseInfo baseInfo = this.getOne(queryWrapper);
            //删除之前的数据
            if (null != baseInfo) {
                processStatus = baseInfo.getProcessStatus();
                this.remove(queryWrapper);

                // 删附件
                attachmentService.cleanAttachmentProjectReport(reportId, baseInfo.getReportType());
            }
        } else {
            // 新增生成reportid
            reportId = IdWorker.getId();
            baseInfoForm.setReportId(reportId);
        }

        //保存数据
        MprEvaBaseInfo baseInfo = this.copyProject2BaseInfo(srpmsProject);
        baseInfo.setReportId(reportId);
        baseInfo.setProcessStatus(processStatus);
        baseInfo.setReportType(baseInfoForm.getReportType());
        baseInfo.setReportTitle(baseInfoForm.getReportTitle());
        baseInfo.setReportYear(baseInfoForm.getReportYear());
        baseInfo.setRepOtherType(baseInfoForm.getRepOtherType());
        baseInfo.setCreateBy(user.getId());
        baseInfo.setApplyDeptCode(dept.getDeptCode());
        baseInfo.setProjectTypeCode(srpmsProject.getProjectType());
        this.save(baseInfo);

        // 保存附件
        if (CollectionUtils.isNotEmpty(mprSaveOrUpdateForm.getSrpmsProjectAttachmentFormList())) {
            List<SrpmsProjectAttachment> taskEntityList = new ArrayList<>();
            for(SrpmsProjectAttachmentVo vo: mprSaveOrUpdateForm.getSrpmsProjectAttachmentFormList()){
                SrpmsProjectAttachment attachment = new BeanUtils<SrpmsProjectAttachment>().copyObj(vo, SrpmsProjectAttachment.class);
                if (StringUtils.equals(baseInfo.getReportType(), ReportTypeEnum.YEAR_REPORT.getCode())) {
                    attachment.setAttachmentCategory(AttachmentCategoryEnums.ATTACHMENT_PROJECT_REPORT_YEAR.getCode());
                } else if (StringUtils.equals(baseInfo.getReportType(), ReportTypeEnum.MID_REPORT.getCode())) {
                    attachment.setAttachmentCategory(AttachmentCategoryEnums.ATTACHMENT_PROJECT_REPORT_MID.getCode());
                } else if (StringUtils.equals(baseInfo.getReportType(), ReportTypeEnum.OTHER.getCode())) {
                    attachment.setAttachmentCategory(AttachmentCategoryEnums.ATTACHMENT_PROJECT_REPORT_OTHER.getCode());
                }
                attachment.setProjectId(reportId);

                taskEntityList.add(attachment);
            }
            attachmentService.saveBatch(taskEntityList);
        }
        return reportId;
    }

    /**
     * 年度报告流程发起
     * @param mprSaveOrUpdateForm
     * @param user
     * @param dept
     */
    @Override
    public void submitReport(MprSaveOrUpdateForm mprSaveOrUpdateForm,  UserVo user, DeptVo dept) {
        if (mprSaveOrUpdateForm == null || mprSaveOrUpdateForm.getBaseInfoForm() == null) {
            throw new BaseException(SrpmsErrorType.PARAM_NULL);
        }
        MprEvaBaseInfoForm baseInfoForm = mprSaveOrUpdateForm.getBaseInfoForm();

        if (null == baseInfoForm.getId() || 0L == baseInfoForm.getId()) {
            throw new BaseException(SrpmsErrorType.PROJECT_ID_NULL);
        }

        Long reportId = 0L;
        if (null == baseInfoForm.getReportId() || 0L == baseInfoForm.getReportId()) {
            // 先保存
            reportId = this.saveOrUpdate(mprSaveOrUpdateForm, user, dept);
        } else {
            reportId = baseInfoForm.getReportId();
        }

        MprEvaBaseInfoQueryForm form = new MprEvaBaseInfoQueryForm();
        form.setReportId(reportId);
        QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper <MprEvaBaseInfo>();
        getQueryWrapper(queryWrapper, form);
        MprEvaBaseInfo baseInfo = this.getOne(queryWrapper);
        baseInfo.setProcessStatus(EnumMprProcessStatus.MPR_AUDIT.getCode());
        this.update(baseInfo, queryWrapper);

        SrpmsVoucher voucherEntity = voucherService.getSrpmsVoucherByReject(baseInfo.getReportId());// 查询项目是否进行驳回操作
        if (voucherEntity != null ) {
            flowService.againSubmit(voucherEntity, dept, false);
        } else {
            // 新建一个单据
            voucherEntity = new SrpmsVoucher();
            //获取承担单位code
            String deptCode = projectService.getLeadDeptCodeByProjectId(baseInfo.getId());
            voucherEntity.setLeadDeptCode(deptCode);
            voucherEntity.setBizNumber("MPR" + baseInfo.getReportId());
            voucherEntity.setVoucherType(VoucherTypeEnums.MPR_EVA_YEAR.getCode());
            if (StringUtils.equals(ReportTypeEnum.YEAR_REPORT.getCode(), baseInfo.getReportType())) {
                // 年度
                voucherEntity.setVoucherName(baseInfo.getProjectName() == null ? "" : (baseInfo.getProjectName() + "-") + VoucherTypeEnums.MPR_EVA_YEAR.getCode());
//            voucherEntity.setVoucherType(VoucherTypeEnums.MPR_EVA_YEAR.getCode());
            } else if (StringUtils.equals(ReportTypeEnum.OTHER.getCode(), (baseInfo.getReportType()))) {
                // 其它
                voucherEntity.setVoucherName(baseInfo.getProjectName() == null ? "" : (baseInfo.getProjectName() + "-") + VoucherTypeEnums.MPR_EVA_OTHER.getCode());
//            voucherEntity.setVoucherType(VoucherTypeEnums.MPR_EVA_OTHER.getCode());
            } else if (StringUtils.equals(ReportTypeEnum.MID_REPORT.getCode(), baseInfo.getReportType())) {
                // 中期
                voucherEntity.setVoucherName(baseInfo.getProjectName() == null ? "" : (baseInfo.getProjectName() + "-") + VoucherTypeEnums.MPR_EVA_MID.getCode());
//            voucherEntity.setVoucherType(VoucherTypeEnums.MPR_EVA_MID.getCode());
            } else {
                throw new BaseException(SrpmsErrorType.PARAM_NULL);
            }
            voucherEntity.setPersonName(user.getName());
            voucherEntity.setUserId(CommonUtil.getLongValue(user.getId()));
            voucherEntity.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
            voucherEntity.setStartTime(LocalDateTime.now());
            voucherEntity.setProjectId(baseInfo.getReportId());
            voucherEntity.setAuditMode(VoucherAuditModeEnums.ALL.getCode());

            voucherService.save(voucherEntity);

            bpmService.startProcess(voucherEntity, user, dept, null);
        }
    }

    /**
     * 审核通过年度报告
     * @param actionVO
     * @param deptVo
     */
    @Override
    public void agreeReport(TaskNodeActionVO actionVO, DeptVo deptVo) {
        boolean hasEnd = bpmService.auditApprove(actionVO, deptVo);
        SrpmsVoucher voucher = voucherService.getById(actionVO.getObjectId());

        // 审批后更新单据撤回标识
        voucher.setRecallFlag(1);
        if (hasEnd) {
            QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper <MprEvaBaseInfo>();
            MprEvaBaseInfoQueryForm queryForm = new MprEvaBaseInfoQueryForm();
            queryForm.setReportId(voucher.getProjectId());
            getQueryWrapper(queryWrapper, queryForm);
            MprEvaBaseInfo baseInfo = this.getOne(queryWrapper);
            baseInfo.setProcessStatus(EnumMprProcessStatus.MPR_APPROVED.getCode());

            this.update(baseInfo, queryWrapper);

            //更新单据状态
//            voucherService.updateStatus(Long.parseLong(actionVO.getObjectId()), VoucherStatusEnums.APPROVED);
            voucher.setVoucherStatus(VoucherStatusEnums.APPROVED.getCode());
            voucherService.saveOrUpdate(voucher);
        }
    }

    /**
     * 拒绝年度报告
     * @param actionVO
     */
    @Override
    @Transactional
    public void refuseReport(TaskNodeActionVO actionVO) {
        bpmService.auditRefuse(actionVO);

        SrpmsVoucher voucher = voucherService.getById(actionVO.getObjectId());

        QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper <MprEvaBaseInfo>();
        MprEvaBaseInfoQueryForm queryForm = new MprEvaBaseInfoQueryForm();
        queryForm.setReportId(voucher.getProjectId());
        getQueryWrapper(queryWrapper, queryForm);
        MprEvaBaseInfo baseInfo = this.getOne(queryWrapper);
        baseInfo.setProcessStatus(EnumMprProcessStatus.MPR_REFUSE.getCode());

        this.update(baseInfo, queryWrapper);

        voucher.setRecallFlag(1);
        // 更新单据状态
        voucher.setVoucherStatus(VoucherStatusEnums.REFUSED.getCode());
        voucherService.saveOrUpdate(voucher);
//		voucherService.updateStatus(CommonUtil.getLongValue(actionVO.getObjectId()), VoucherStatusEnums.REFUSED);
    }

//    /**
//     * 驳回年度报告
//     * @param actionVO
//     */
//    @Override
//    public void rejectReport(TaskNodeActionVO actionVO) {
//        SrpmsVoucher voucher = voucherService.getById(actionVO.getObjectId());
//        if (null == voucher) {
//            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "单据不存在");
//        }
//        bpmService.auditBackToFirst(actionVO, voucher);
//
//        QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper <MprEvaBaseInfo>();
//        MprEvaBaseInfoQueryForm queryForm = new MprEvaBaseInfoQueryForm();
//        queryForm.setReportId(voucher.getProjectId());
//        getQueryWrapper(queryWrapper, queryForm);
//
//        MprEvaBaseInfo baseInfo = this.getOne(queryWrapper);
//        baseInfo.setProcessStatus(EnumMprProcessStatus.MPR_REJECT.getCode());
//
//        this.update(baseInfo, queryWrapper);
//
//        //更新单据状态
//        voucherService.updateStatus(Long.parseLong(actionVO.getObjectId()), VoucherStatusEnums.REJECT);
//    }
}

