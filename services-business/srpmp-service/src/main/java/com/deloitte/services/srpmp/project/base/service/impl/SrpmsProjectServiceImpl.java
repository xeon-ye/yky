package com.deloitte.services.srpmp.project.base.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatFormException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.srpmp.common.DateTimeFormatUtil;
import com.deloitte.services.srpmp.common.enums.*;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.util.CheckUtils;
import com.deloitte.services.srpmp.common.util.JSONConvert;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsVoucher;
import com.deloitte.services.srpmp.project.base.mapper.SrpmsProjectMapper;
import com.deloitte.services.srpmp.project.base.service.*;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaBaseInfo;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaBaseInfoService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description :  SrpmsProject服务实现类
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class SrpmsProjectServiceImpl extends ServiceImpl<SrpmsProjectMapper, SrpmsProject> implements ISrpmsProjectService {

    @Autowired
    private SrpmsProjectMapper srpmsProjectMapper;

    @Autowired
    public ISrpmsProjectAttachmentService attachmentService;

    @Autowired
    public ISrpmsProjectExpertService expertService;

    @Autowired
    public ISrpmsProjectApprovalService approvalService;

    @Autowired
    private IMprEvaBaseInfoService mprEvaBaseInfoService;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private ISrpmsVoucherService voucherService;

    @Autowired
    private ISrpmsProjectFlowService flowService;

    /**
     * 查询承担单位Code
     * @param id
     * @return
     */
    @Override
    public String getLeadDeptCodeByProjectId(Long id){
        SrpmsProject projectEntity = this.getById(id);
        if (projectEntity == null){
            return null;
        }
        String leadDeptStr = projectEntity.getLeadDept();
        JSONObject leadDeptJson = JSONObject.parseObject(leadDeptStr);
        return leadDeptJson.getString("deptCode");
    }


    /**
     * 是否承担2016-2018的创新工程
     * @param leadPersonId
     * @return
     */
    @Override
    public boolean isManageProjectHistory(Long leadPersonId){
        List<String> innTypeList = Lists.newArrayList(ProjectCategoryEnums.INNOVATE_BCOO.getHeader(),ProjectCategoryEnums.INNOVATE_COO.getHeader(),
                ProjectCategoryEnums.INNOVATE_PRE.getHeader(), ProjectCategoryEnums.INNOVATE_YOU.getHeader());
        QueryWrapper<SrpmsProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(SrpmsProject.PROJECT_TYPE, innTypeList);
        queryWrapper.eq(SrpmsProject.LEAD_PERSON_ID, leadPersonId);
        queryWrapper.gt(SrpmsProject.STATUS, SrpmsProjectStatusEnums.PEROJECT_TASK_PASS.getCode());
        queryWrapper.lt(SrpmsProject.PROJECT_ACTION_DATE_START, LocalDateTime.of(2019,1,1,0,0,0));
        int size = this.count(queryWrapper);
        if (size > 0){
            return true;
        }
        return false;
    }

    @Override
    public IPage<SrpmsProjectVo> searchNoUser(SrpmsProjectQueryForm queryForm) {
        QueryWrapper<SrpmsProject> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        IPage<SrpmsProject> projectPage = srpmsProjectMapper.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
        List<SrpmsProjectVo> voList = Lists.newArrayList();
        for (SrpmsProject project : projectPage.getRecords()) {
            SrpmsProjectVo vo = new BeanUtils<SrpmsProjectVo>().copyObj(project, SrpmsProjectVo.class);
            if (StringUtils.isNotBlank(project.getLeadPerson()) && JSONObject.isValid(project.getLeadPerson())) {
                vo.setLeadPerson(JSONObject.parseObject(project.getLeadPerson()));
            }
            if (StringUtils.isNotBlank(project.getLeadDept()) && JSONObject.isValid(project.getLeadDept())) {
                vo.setLeadDept(JSONObject.parseObject(project.getLeadDept()));
            }
            if (StringUtils.isNotBlank(project.getBothTopExpertPerson()) && JSONObject.isValid(project.getBothTopExpertPerson())) {
                vo.setBothTopExpertPerson(JSONObject.parseObject(project.getBothTopExpertPerson()));
            }
            voList.add(vo);
        }
        IPage<SrpmsProjectVo> voIPage = new Page<>();
        voIPage.setRecords(voList);
        voIPage.setTotal(projectPage.getTotal());
        voIPage.setCurrent(projectPage.getCurrent());
        voIPage.setPages(projectPage.getPages());
        voIPage.setSize(projectPage.getSize());
        return voIPage;
    }

    @Override
    public List<SrpmsProjectVo> listByProjectIds(String projectIds) {
        if (StringUtils.isBlank(projectIds)) {
            throw new PlatFormException(PlatformErrorType.ARGUMENT_NOT_VALID);
        }
        projectIds = projectIds.trim();
        String[] idArray = projectIds.split(",");
        List<SrpmsProjectVo> voList = Lists.newArrayList();
        if (idArray.length != 0) {
            List<Long> idList = Lists.newArrayList();
            for (String id : idArray) {
                if (NumberUtils.isParsable(id)) {
                    idList.add(NumberUtils.toLong(id));
                }
            }
            List<SrpmsProject> list = (List<SrpmsProject>) this.listByIds(idList);

            for (SrpmsProject project : list) {
                SrpmsProjectVo vo = new BeanUtils<SrpmsProjectVo>().copyObj(project, SrpmsProjectVo.class);
                if (StringUtils.isNotBlank(project.getLeadPerson()) && JSONObject.isValid(project.getLeadPerson())) {
                    vo.setLeadPerson(JSONObject.parseObject(project.getLeadPerson()));
                }
                if (StringUtils.isNotBlank(project.getLeadDept()) && JSONObject.isValid(project.getLeadDept())) {
                    vo.setLeadDept(JSONObject.parseObject(project.getLeadDept()));
                }
                if (StringUtils.isNotBlank(project.getBothTopExpertPerson()) && JSONObject.isValid(project.getBothTopExpertPerson())) {
                    vo.setBothTopExpertPerson(JSONObject.parseObject(project.getBothTopExpertPerson()));
                }
                voList.add(vo);
            }
        }
        return voList;
    }

    @Override
    public JSONObject selectPage(SrpmsProjectQueryForm queryForm, UserVo user, DeptVo dept) {

        String roleCode = user.getHonor();
        String deptCode = dept.getDeptCode();
        String proType = user.getRemark();
        int tableFlg = queryForm.getTableFlag();

        if (queryForm.getMenuFlag() == 1) {
            queryForm.setLeadPersonId(CommonUtil.getLongValue(user.getId()));
        } else {

            if (tableFlg == SrpmsConstant.TABLE_FLAG_1) {
                queryForm.setLeadPersonId(CommonUtil.getLongValue(user.getId()));
            } else if (tableFlg == SrpmsConstant.TABLE_FLAG_2) {
                if (StringUtils.isBlank(proType)) {
                    return CommonUtil.getEmptyRelust();
                }
                if (queryForm.getSelfDataFlag() == 1) {// 此标识判断管理员是否查询自己的数据
                    queryForm.setLeadPersonId(CommonUtil.getLongValue(user.getId()));
                }
                if (!SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
                    queryForm.setLeadDeptId(CommonUtil.getLongValue(dept.getDeptId()));
                }
                if (StringUtils.isBlank(queryForm.getProjectType())) {
                    queryForm.setProjectType(proType);
                } else if (proType.contains(queryForm.getProjectType())) {
                    queryForm.setProjectType(queryForm.getProjectType());
                } else {
                    return CommonUtil.getEmptyRelust();
                }
            } else {// 初始化权限显示table
                if (SrpmsConstant.SRPMS_ADMIN.equals(roleCode) || SrpmsConstant.SRPMS_LEADER.equals(roleCode)) {
                    if (StringUtils.isBlank(proType)) {
                        return CommonUtil.getEmptyRelust();
                    }
                    tableFlg = SrpmsConstant.TABLE_FLAG_2;
                    if (!SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
                        queryForm.setLeadDeptId(CommonUtil.getLongValue(dept.getDeptId()));
                    }
                    if (queryForm.getSelfDataFlag() == 1) {// 此标识判断管理员是否查询自己的数据
                        queryForm.setLeadPersonId(CommonUtil.getLongValue(user.getId()));
                    }
                    if (StringUtils.isBlank(queryForm.getProjectType())) {
                        queryForm.setProjectType(proType);
                    } else if (proType.contains(queryForm.getProjectType())) {
                        queryForm.setProjectType(queryForm.getProjectType());
                    } else {
                        return CommonUtil.getEmptyRelust();
                    }
                } else {
                    tableFlg = SrpmsConstant.TABLE_FLAG_1;
                    queryForm.setLeadPersonId(CommonUtil.getLongValue(user.getId()));
                }
            }
        }

        queryForm.setTableFlag(tableFlg);

        QueryWrapper<SrpmsProject> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        log.info("加入数据权限之后的查询条件为" + JSONObject.toJSONString(queryForm));

        IPage<SrpmsProject> page = srpmsProjectMapper.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
        JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(page));
        JSONArray jsonArray = json.getJSONArray("records");

        Map<String, String> dictMap = sysDictService.getSysDictTranLong(SrpmsConstant.PRO_CATEGORY);
        List<Long> ids = Lists.newArrayList();
        Map<String, String> optionMap = null;
        if (SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(queryForm.getStatus())) {
            JSONArray jsonOptions = (JSONArray) redisTemplate.opsForValue().get("sysDict_" + SrpmsConstant.SUBJECT_OPTIONS);
            optionMap = new HashMap<>();
            if (jsonOptions != null && jsonOptions.size() > 0) {
                JSONObject jsonObject;
                JSONArray tempArr;
                for (int i = 0; i < jsonOptions.size(); i++) {
                    jsonObject = jsonOptions.getJSONObject(i);
                    optionMap.put(jsonObject.getString("id"), jsonObject.getString("label"));
                    tempArr = JSONArray.parseArray(jsonObject.getString("children"));
                    if (tempArr != null && tempArr.size() > 0) {
                        for (int j = 0; j < tempArr.size(); j++) {
                            jsonObject = tempArr.getJSONObject(j);
                            optionMap.put(jsonObject.getString("id"), jsonObject.getString("label"));
                        }
                    }
                }
            }
        }
        JSONArray optionsArr;
        for (int i = 0; i < jsonArray.size(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONConvert.convertJson(jsonObject);
            if (jsonObject.get("leadPerson") != null && JSONObject.isValid(jsonObject.getString("leadPerson"))) {
                jsonObject.put("applyPerson", jsonObject.getJSONObject("leadPerson").getString("name"));
            }
            if (jsonObject.get("leadDept") != null && JSONObject.isValid(jsonObject.getString("leadDept"))) {
                jsonObject.put("applyOrg", jsonObject.getJSONObject("leadDept").getString("deptName"));
            }
            if (dictMap != null) {
                if (StringUtils.isNotBlank(jsonObject.getString("projectType"))) {
                    jsonObject.put("projectTypeName", dictMap.get(jsonObject.getString("projectType")));
                }
            }
            if (SrpmsConstant.PROJECT_TYPE_1.equals(jsonObject.getString("projectFlag"))) {
                jsonObject.put("projectFlag", jsonObject.getString("projectFlag"));
            } else {
                jsonObject.put("projectFlag", "");
                if (optionMap != null && StringUtils.isNotBlank(jsonObject.getString("subjectCategory"))) {
                    optionsArr = JSONArray.parseArray(jsonObject.getString("subjectCategory"));
                    if (optionsArr != null && optionsArr.size() > 0) {
                        if (optionsArr.size() == 1) {
                            jsonObject.put("subjectCategory", optionMap.get(optionsArr.get(0)));
                        } else {
                            jsonObject.put("subjectCategory", String.join("/", optionMap.get(optionsArr.get(0)), optionMap.get(optionsArr.get(1))));
                        }
                    }
                }
            }
            ids.add(jsonObject.getLong("id"));
        }

        //补充中期绩效报告状态信息
        if (CollectionUtils.isNotEmpty(ids)) {
            List<MprEvaBaseInfo> baseInfoList = (List<MprEvaBaseInfo>) mprEvaBaseInfoService.listByIds(ids);
            Map<String, String> statusMap = Maps.newHashMap();
            for (MprEvaBaseInfo info : baseInfoList) {
                statusMap.put(info.getId() + "-" + info.getReportType(), info.getProcessStatus());
            }
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONConvert.convertJson(jsonObject);
                if (StringUtils.isNotBlank(MapUtils.getString(statusMap, jsonObject.getLong("id") + "-" + ReportTypeEnum.MPR_REPORT.getCode()))) {
                    jsonObject.put("mprProcessStatus", MapUtils.getString(statusMap, jsonObject.getLong("id") + "-" + ReportTypeEnum.MPR_REPORT.getCode()));
                } else {
                    jsonObject.put("mprProcessStatus", EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
                }
                if (StringUtils.isNotBlank(MapUtils.getString(statusMap, jsonObject.getLong("id") + "-" + ReportTypeEnum.YEAR_REPORT.getCode()))) {
                    jsonObject.put("yearProcessStatus", MapUtils.getString(statusMap, jsonObject.getLong("id") + "-" + ReportTypeEnum.YEAR_REPORT.getCode()));
                } else {
                    jsonObject.put("yearProcessStatus", EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
                }
                if (StringUtils.isNotBlank(MapUtils.getString(statusMap, jsonObject.getLong("id") + "-" + ReportTypeEnum.MID_REPORT.getCode()))) {
                    jsonObject.put("midProcessStatus", MapUtils.getString(statusMap, jsonObject.getLong("id") + "-" + ReportTypeEnum.MID_REPORT.getCode()));
                } else {
                    jsonObject.put("midProcessStatus", EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
                }
                if (StringUtils.isNotBlank(MapUtils.getString(statusMap, jsonObject.getLong("id") + "-" + ReportTypeEnum.OTHER.getCode()))) {
                    jsonObject.put("otherProcessStatus", MapUtils.getString(statusMap, jsonObject.getLong("id") + "-" + ReportTypeEnum.OTHER.getCode()));
                } else {
                    jsonObject.put("otherProcessStatus", EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
                }

            }
        }

        json.put("tableFlag", queryForm.getTableFlag());
        return json;
    }

    @Override
    public JSONObject queryTaskPage(UserVo user, DeptVo dept) {

        SrpmsProjectQueryForm queryForm = new SrpmsProjectQueryForm();

        String roleCode = user.getHonor();
        String deptCode = dept.getDeptCode();
        String proType = user.getRemark();
        if ("admin".equals(roleCode)) {
            if ("1001".equals(deptCode)) {
                queryForm.setProjectType(proType);
            } else {
                queryForm.setLeadDept(dept.getDeptId());
            }
        } else if ("leader".equals(roleCode)) {
            if (!"1001".equals(deptCode)) {
                queryForm.setLeadDept(dept.getDeptId());
            }
        } else {
            queryForm.setLeadPersonId(Long.parseLong(user.getId()));
        }

        queryForm.setStatus(SrpmsProjectStatusEnums.PEROJECT_HAS_PUBLICITY.getCode());
        QueryWrapper<SrpmsProject> queryWrapper = new QueryWrapper<SrpmsProject>();
        getQueryWrapper(queryWrapper, queryForm);
        IPage<SrpmsProject> page = srpmsProjectMapper.selectPage(new Page<SrpmsProject>(queryForm.getCurrentPage(), 1000), queryWrapper);
        JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(page));
        JSONArray jsonArray = json.getJSONArray("records");

        for (int i = 0; i < jsonArray.size(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONConvert.convertJson(jsonObject);
            if (jsonObject.get("leadPerson") != null && JSONObject.isValid(jsonObject.getString("leadPerson"))) {
                jsonObject.put("applyPerson", jsonObject.getJSONObject("leadPerson").getString("name"));
            }
            if (jsonObject.get("leadDept") != null && JSONObject.isValid(jsonObject.getString("leadDept"))) {
                jsonObject.put("applyOrg", jsonObject.getJSONObject("leadDept").getString("deptName"));
            }
        }
        return json;
    }

    @Override
    public JSONObject getProjectApply(Long id) {
        JSONObject json = null;
        SrpmsProject srpmsProject = this.getById(id);

        json = JSONObject.parseObject(JSONObject.toJSONString(srpmsProject));

        for (int j = 0; j < ProjectCategoryEnums.values().length; j++) {
            if (srpmsProject.getProjectCategory().equals(ProjectCategoryEnums.values()[j].getCode())) {
                json.put("projectCategory", ProjectCategoryEnums.values()[j].getDesc());
            }
        }

        json.putAll(attachmentService.queryAttachmentListApply(id));

        json.put("expertList", expertService.queryListByProjectId(id, "1"));

        json.put("approvalCommentList", approvalService.queryListByProjectId(id));

        if (srpmsProject.getLeadPerson() != null && !"".equals(srpmsProject.getLeadPerson())) {
            json.put("applyPerson", JSONObject.parseObject(srpmsProject.getLeadPerson()).getString("name"));
        }
        if (srpmsProject.getLeadDept() != null && !"".equals(srpmsProject.getLeadDept())) {
            json.put("applyOrg", JSONObject.parseObject(srpmsProject.getLeadDept()).getString("deptName"));
        }

        JSONConvert.convertJson(json);
        return json;
    }

    @Override
    public JSONObject getProjectTask(Long id) {
        JSONObject json = null;
        SrpmsProject srpmsProject = this.getById(id);

        json = JSONObject.parseObject(JSONObject.toJSONString(srpmsProject));

        for (int j = 0; j < ProjectCategoryEnums.values().length; j++) {
            if (srpmsProject.getProjectCategory().equals(ProjectCategoryEnums.values()[j].getCode())) {
                json.put("projectCategory", ProjectCategoryEnums.values()[j].getDesc());
            }
        }

        if (ProjectCategoryEnums.INNOVATE_BCOO.getHeader().equals(srpmsProject.getProjectType())
                || ProjectCategoryEnums.INNOVATE_COO.getHeader().equals(srpmsProject.getProjectType())
                || ProjectCategoryEnums.INNOVATE_PRE.getHeader().equals(srpmsProject.getProjectType())) {
            json.putAll(attachmentService.queryAttachmentListTaskType1(id));
        } else {
            json.putAll(attachmentService.queryAttachmentListTaskType2(id));
        }

        json.put("expertList", expertService.queryListByProjectId(id, "2"));

        json.put("approvalCommentList", approvalService.queryListByProjectId(id));

        if (srpmsProject.getLeadPerson() != null && !"".equals(srpmsProject.getLeadPerson())) {
            json.put("applyPerson", JSONObject.parseObject(srpmsProject.getLeadPerson()).getString("name"));
        }
        if (srpmsProject.getLeadDept() != null && !"".equals(srpmsProject.getLeadDept())) {
            json.put("applyOrg", JSONObject.parseObject(srpmsProject.getLeadDept()).getString("deptName"));
        }

        JSONConvert.convertJson(json);
        return json;
    }

    @Override
    public void delete(Long id) {
        SrpmsProject project = this.getById(id);
        if (project != null) {
            SrpmsVoucher voucher = voucherService.getSrpmsVoucherByReject(id);// 查询项目是否进行驳回操作
            if (voucher != null ) {
                flowService.againSubmit(voucher, null, true);
            }
            project.setDelFlg("1");
            this.updateById(project);
        }
    }

    @Override
    public void submitProject(Long id) {
        //查询项目
        SrpmsProject projectEntity = this.getById(id);
        if (!SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(projectEntity.getStatus())) {
            throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
        }
        projectEntity.setStatus(SrpmsProjectStatusEnums.PEROJECT_HAS_SUBMIT.getCode());
        this.updateById(projectEntity);
    }

    /**
     * 提交任务书
     *
     * @param id
     */
    @Override
    public void submitTaskProject(Long id) {
        //查询项目
        SrpmsProject projectEntity = this.getById(id);
        if (!SrpmsProjectStatusEnums.PEROJECT_HAS_PUBLICITY.getCode().equals(projectEntity.getStatus())) {
            throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
        }
        projectEntity.setStatus(SrpmsProjectStatusEnums.PEROJECT_TASK_SUBMIT.getCode());
        this.updateById(projectEntity);
    }

    /**
     * 通用查询
     *
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<SrpmsProject> getQueryWrapper(QueryWrapper<SrpmsProject> queryWrapper, SrpmsProjectQueryForm srpmsProject) {
        //条件拼接
        if (CheckUtils.notEmpty(srpmsProject.getProjectNum())) {
            queryWrapper.like(SrpmsProject.PROJECT_NUM, srpmsProject.getProjectNum());
        }
        if (CheckUtils.notEmpty(srpmsProject.getProjectName())) {
            queryWrapper.like(SrpmsProject.PROJECT_NAME, srpmsProject.getProjectName());
        }
        if (CheckUtils.notEmpty(srpmsProject.getApplyYear())) {
            queryWrapper.like(SrpmsProject.APPLY_YEAR, srpmsProject.getApplyYear());
        }
        if (CheckUtils.notEmpty(srpmsProject.getSubjectCategory())) {
            queryWrapper.like(SrpmsProject.SUBJECT_CATEGORY, srpmsProject.getSubjectCategory());
        }
        if (CheckUtils.notEmpty(srpmsProject.getLeadDeptId())) {
            queryWrapper.eq(SrpmsProject.LEAD_DEPT_ID, srpmsProject.getLeadDeptId());
        }
        if (CheckUtils.notEmpty(srpmsProject.getProjectRole())) {
            queryWrapper.like(SrpmsProject.PROJECT_ROLE, srpmsProject.getProjectRole());
        }
        if (CheckUtils.notEmpty(srpmsProject.getProjectCategory())) {
            queryWrapper.eq(SrpmsProject.PROJECT_TYPE, srpmsProject.getProjectCategory());
        }
        if (CheckUtils.notEmpty(srpmsProject.getProjectType())) {
            if (!srpmsProject.getProjectType().contains(",")) {
                queryWrapper.eq(SrpmsProject.PROJECT_TYPE, srpmsProject.getProjectType());
            } else {
                String[] arr = srpmsProject.getProjectType().split(",");
                queryWrapper.in(SrpmsProject.PROJECT_TYPE, arr);
            }
        }
        if (CheckUtils.notEmpty(srpmsProject.getAplNum())) {
            queryWrapper.eq(SrpmsProject.APL_NUM, srpmsProject.getAplNum());
        }
        if (CheckUtils.notEmpty(srpmsProject.getApdNum())) {
            queryWrapper.eq(SrpmsProject.APD_NUM, srpmsProject.getApdNum());
        }
        if (CheckUtils.notEmpty(srpmsProject.getPudNum())) {
            queryWrapper.eq(SrpmsProject.PUD_NUM, srpmsProject.getPudNum());
        }

        if (CheckUtils.notEmpty(srpmsProject.getStatus())) {
            if (!srpmsProject.getStatus().contains(",")) {
                queryWrapper.eq(SrpmsProject.STATUS, srpmsProject.getStatus());
            } else {
                String[] arr = srpmsProject.getStatus().split(",");
                queryWrapper.in(SrpmsProject.STATUS, arr);
            }
        }

        if (CheckUtils.notEmpty(srpmsProject.getLeadPersonId())) {
            queryWrapper.eq(SrpmsProject.LEAD_PERSON_ID, srpmsProject.getLeadPersonId());
        }
        if (SrpmsProjectStatusEnums.PEROJECT_HAS_PUBLICITY.getCode().equals(srpmsProject.getStatus())) {
            queryWrapper.orderByDesc(SrpmsProject.PUBLIC_TIME);
        } else if (SrpmsProjectStatusEnums.PEROJECT_SET_UP.getCode().equals(srpmsProject.getStatus())) {
            queryWrapper.orderByDesc(SrpmsProject.APPROVE_TIME);
        } else {
            queryWrapper.orderByDesc(SrpmsProject.UPDATE_TIME);
        }
        if (CheckUtils.notEmpty(srpmsProject.getProjectFlag())) {// 项目标识条件
            queryWrapper.eq(SrpmsProject.PROJECT_FLAG, srpmsProject.getProjectFlag());
        }
        if (srpmsProject.getMenuFlag() == 1) {
            queryWrapper.isNull(SrpmsProject.PROJECT_FLAG);
        }
        queryWrapper.ne(SrpmsProject.DEL_FLG, '1');
        return queryWrapper;
    }

    /**
     * wuhebiao 20190426 添加：年度报告附件对应附件表中project_id字段存储的是年度报告ID（一个项目有多个报告）
     *
     * @param queryForm
     * @return
     */
    @Override
    public SrpmsProject getProjectInfoForReport(SrpmsProjectQueryForm queryForm) {
        QueryWrapper<SrpmsProject> queryWrapper = new QueryWrapper<SrpmsProject>();
        getQueryWrapper(queryWrapper, queryForm);

        return this.getOne(queryWrapper);
    }
}

