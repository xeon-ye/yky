package com.deloitte.services.srpmp.project.accept.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.push.feign.SendMssFeignService;
import com.deloitte.platform.api.push.form.SendMssInfoForm;
import com.deloitte.platform.api.srpmp.project.accept.param.SrpmsProjectAcceptQueryForm;
import com.deloitte.platform.api.srpmp.project.accept.vo.SrpmsProjectAcceptForm;
import com.deloitte.platform.api.srpmp.project.accept.vo.SrpmsProjectAcceptVo;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.constant.ProcessConstant;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.enums.PersonJoinWayEnums;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectStatusEnums;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectUpdateStatusEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISerialNoCenterService;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.service.impl.WordExportServiceImpl;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.outline.util.DateUtil;
import com.deloitte.services.srpmp.project.accept.entity.SrpmsProjectAccept;
import com.deloitte.services.srpmp.project.accept.entity.SrpmsProjectAcceptAcademy;
import com.deloitte.services.srpmp.project.accept.entity.SrpmsProjectAcceptSchool;
import com.deloitte.services.srpmp.project.accept.mapper.SrpmsProjectAcceptMapper;
import com.deloitte.services.srpmp.project.accept.service.ISrpmsProjectAcceptAcademyService;
import com.deloitte.services.srpmp.project.accept.service.ISrpmsProjectAcceptSchoolService;
import com.deloitte.services.srpmp.project.accept.service.ISrpmsProjectAcceptService;
import com.deloitte.services.srpmp.project.apply.entity.*;
import com.deloitte.services.srpmp.project.apply.service.*;
import com.deloitte.services.srpmp.project.apply.util.LocalDateUtils;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsVoucher;
import com.deloitte.services.srpmp.project.base.service.*;
import com.deloitte.services.srpmp.project.base.service.impl.PdfMergeServiceImpl;
import com.deloitte.services.srpmp.project.base.service.impl.SrpmsProjectBpmServiceImpl;
import com.deloitte.services.srpmp.project.task.entity.*;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskAcademyService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskReformService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskSchStudentService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskTranLongService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.docx4j.wml.P;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-25
 * @Description :  SrpmsProjectAccept服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsProjectAcceptServiceImpl extends ServiceImpl<SrpmsProjectAcceptMapper, SrpmsProjectAccept> implements ISrpmsProjectAcceptService {

    @Autowired
    private SrpmsProjectAcceptMapper srpmsProjectAcceptMapper;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private ISrpmsProjectAttachmentService attachmentService;

    @Autowired
    private ISrpmsVoucherService voucherService;

    @Autowired
    public SrpmsProjectBpmServiceImpl bpmService;

    @Autowired
    public ISrpmsProjectAcceptSchoolService acceptSchoolService;

    @Autowired
    public ISrpmsProjectAcceptAcademyService acceptAcademyService;

    @Autowired
    public ISrpmsProjectService srpmsProjectService;

    @Autowired
    public ISrpmsProjectTaskTranLongService taskTranLongService;

    @Autowired
    private ISerialNoCenterService serialNoCenterService;

    @Autowired
    private ISrpmsProjectFlowService flowServicel;

    @Autowired
    private ISrpmsProjectTaskAcademyService taskAcademyService;

    @Autowired
    private ISrpmsProjectTaskSchStudentService taskSchStudentService;

    @Autowired
    private ISrpmsProjectTaskReformService taskReformService;

    @Autowired
    private ISrpmsProjectPersonJoinService personJoinService;

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private SendMssFeignService sendMssFeignService;

    @Autowired
    private PdfMergeServiceImpl pdfMergeService;

    @Autowired
    private WordExportServiceImpl wordExportService;

    @Autowired
    private ISrpmsProjectApplyInnBcooService applyInnBcooService;

    @Autowired
    private ISrpmsProjectApplyInnCooService applyInnCooService;

    @Autowired
    private ISrpmsProjectApplyInnPreService applyInnPreService;

    @Autowired
    private ISrpmsProjectApplySchStuService applySchStuService;

    @Autowired
    private ISrpmsProjectApplySchTeachService applySchTeachService;

    @Autowired
    private ISrpmsProjectFlowService flowService;

    /**
     * 分页查询
     *
     * @param queryForm
     * @param userVo
     * @param deptVo
     * @return
     */
    @Override
    public JSONObject selectPage(SrpmsProjectAcceptQueryForm queryForm, UserVo userVo, DeptVo deptVo) {
        String roleCode = userVo.getHonor();
        String proType = userVo.getRemark();
        String deptCode = deptVo.getDeptCode();
        int tableFlg = queryForm.getTableFlag();

        if (tableFlg == SrpmsConstant.TABLE_FLAG_1) {
            if (StringUtils.isBlank(queryForm.getStatus())) {
                queryForm.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
            }
            queryForm.setCreateBy(userVo.getId());
        } else if (tableFlg == SrpmsConstant.TABLE_FLAG_2) {
            if (StringUtils.isBlank(proType)) {
                return CommonUtil.getEmptyRelust();
            }
            if (StringUtils.isBlank(queryForm.getStatus())) {
                queryForm.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_APPROVE_PASS.getCode());
            }
            if (queryForm.getSelfDataFlag() == 1) {// 此标识判断管理员是否查询自己的数据
                queryForm.setCreateBy(userVo.getId());
            }
            if (!SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
                queryForm.setDeptId(deptVo.getDeptId());
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
                if (StringUtils.isBlank(queryForm.getStatus())) {
                    queryForm.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_APPROVE_PASS.getCode());
                }
                if (!SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
                    queryForm.setDeptId(deptVo.getDeptId());
                }
                if (queryForm.getSelfDataFlag() == 1) {// 此标识判断管理员是否查询自己的数据
                    queryForm.setCreateBy(userVo.getId());
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
                queryForm.setCreateBy(userVo.getId());
                if (StringUtils.isBlank(queryForm.getStatus())) {
                    queryForm.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
                }
            }
        }

        queryForm.setTableFlag(tableFlg);

        QueryWrapper<SrpmsProjectAccept> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        IPage<SrpmsProjectAccept> page = srpmsProjectAcceptMapper.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
        List<SrpmsProjectAccept> list = page.getRecords();
        List<SrpmsProjectAcceptVo> voList = new ArrayList<>();

        Map<String, String> dictMap = sysDictService.getSysDictMap(new String[]{SrpmsConstant.PRO_UPADATE_STATE});
        Map<String, String> dictCategoryMap = sysDictService.getSysDictTranLong(SrpmsConstant.PRO_CATEGORY);
        if (list != null && list.size() > 0) {
            SrpmsProjectAccept accept;
            SrpmsProjectAcceptVo acceptVo;
            for (Iterator e = list.iterator(); e.hasNext(); ) {
                accept = (SrpmsProjectAccept) e.next();
                acceptVo = new SrpmsProjectAcceptVo();
                BeanUtils.copyProperties(accept, acceptVo);
                if (dictCategoryMap != null) {
                    if (StringUtils.isNotBlank(accept.getProjectType())) {
                        acceptVo.setProjectTypeName(dictCategoryMap.get(accept.getProjectType()));
                    }
                }
                if (dictMap != null) {
                    if (StringUtils.isNotBlank(accept.getStatus())) {
                        acceptVo.setStatusName(dictMap.get(SrpmsConstant.PRO_UPADATE_STATE.concat(accept.getStatus())));
                    }
                }
                acceptVo.setId(CommonUtil.objectToString(accept.getId()));
                acceptVo.setProjectId(CommonUtil.objectToString(accept.getProjectId()));
                voList.add(acceptVo);
            }
        }
        JSONObject json = new JSONObject();
        json.put("current", page.getCurrent());
        json.put("pages", page.getPages());
        json.put("records", voList);
        json.put("searchCount", page.isSearchCount());
        json.put("size", page.getSize());
        json.put("total", page.getTotal());
        json.put("tableFlag", queryForm.getTableFlag());
        return json;
    }

    /**
     * 根据ID查询验收报告service接口实现
     *
     * @param id
     * @return
     */
    @Override
    public JSONObject queryById(Long id) {

        SrpmsProjectAccept accept = this.getById(id);
        if (accept == null) {
            throw new BaseException(SrpmsErrorType.ACCEPT_NO_DATA);
        }
        SrpmsProject srpmsProject = srpmsProjectService.getById(accept.getProjectId());
        if (srpmsProject == null) {
            throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id.toString());
        jsonObject.put("projectType", srpmsProject.getProjectType());
        jsonObject.put("projectId", CommonUtil.objectToString(srpmsProject.getId()));
        jsonObject.put("projectNum", srpmsProject.getProjectNum());
        jsonObject.put("status", accept.getStatus());
        jsonObject.put("projectName", srpmsProject.getProjectName());
        jsonObject.put("projectFlag", srpmsProject.getProjectFlag());
        jsonObject.put("projectActionDateStart", srpmsProject.getProjectActionDateStart());
        jsonObject.put("projectActionDateEnd", srpmsProject.getProjectActionDateEnd());
        jsonObject.put("leadPerson", srpmsProject.getLeadPerson() == null ? "" : JSONObject.parseObject(srpmsProject.getLeadPerson()));
        jsonObject.put("leadDept", srpmsProject.getLeadDept() == null ? "" : JSONObject.parseObject(srpmsProject.getLeadDept()));

        if (SrpmsConstant.PROJECT_TYPE_1.equals(srpmsProject.getProjectFlag())) {// 横纵向项目
            SrpmsProjectTranLong taskTranLong = taskTranLongService.getById(srpmsProject.getId());
            jsonObject.put("projectResultType", taskTranLong.getProjectResultType());
            jsonObject.put("belongDomain", taskTranLong.getBelongDomain());
        }
        Map<String, String> dictMap = sysDictService.getSysDictTranLong(SrpmsConstant.PRO_CATEGORY);
        if (dictMap != null) {
            if (StringUtils.isNotBlank(accept.getProjectType())) {
                jsonObject.put("projectTypeName", dictMap.get(accept.getProjectType()));
            }
        }

        switch (accept.getProjectType()) {// 项目类型
            case SrpmsConstant.MODIFY_PRO_TYPE_10010101:// 创新工程-重大协同创新
                SrpmsProjectApplyInnBcoo applyInnBcoo = applyInnBcooService.getById(srpmsProject.getId());
                jsonObject.put("projectResultType", applyInnBcoo.getAchievementType() == null ? "" : JSONArray.parseArray(applyInnBcoo.getAchievementType()));
                jsonObject.put("belongDomain", applyInnBcoo.getBelongDomain());
                break;
            case SrpmsConstant.MODIFY_PRO_TYPE_10010102:// 创新工程-协同创新团队
                SrpmsProjectApplyInnCoo applyInnCoo = applyInnCooService.getById(srpmsProject.getId());
                jsonObject.put("projectResultType", "");
                jsonObject.put("belongDomain", applyInnCoo.getDirection());
                break;
            case SrpmsConstant.MODIFY_PRO_TYPE_10010103:// 创新工程-先导专项
                SrpmsProjectApplyInnPre applyInnPre = applyInnPreService.getById(srpmsProject.getId());
                jsonObject.put("projectResultType", applyInnPre.getAchievementType() == null ? "" : JSONArray.parseArray(applyInnPre.getAchievementType()));
                jsonObject.put("belongDomain", "");
                break;
            case SrpmsConstant.MODIFY_PRO_TYPE_10010401:// 改革经费
            case SrpmsConstant.MODIFY_PRO_TYPE_10010201:// 院基科费
                jsonObject.putAll(acceptAcademyService.queryByAcceptId(id));
                break;
            case SrpmsConstant.MODIFY_PRO_TYPE_10010301:// 校基科费-青年教师
            case SrpmsConstant.MODIFY_PRO_TYPE_10010302:// 校基科费-学生项目
                jsonObject.putAll(acceptSchoolService.queryByAcceptId(id));
                break;
            default:
                break;
        }
        jsonObject.put("createTime", accept.getCreateTime());

        // 相关附件
        jsonObject.put("attachmentFile", attachmentService.queryAttachmentListAccept(id));

        //查询审批历史记录
        if (!SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(accept.getStatus())) {
            SrpmsVoucher voucherEntity = voucherService.getSrpmsVoucherByUpdateId(id);
            if (voucherEntity != null) {
                jsonObject.put("approveHistory", bpmService.queryAuditHistory(CommonUtil.objectToString(voucherEntity.getId())));
            }
        }

        return jsonObject;
    }

    /**
     * 项目验收报告跳转service请求接口实现
     *
     * @param form
     * @return
     */
    @Override
    public JSONObject searchNewVersion(SrpmsProjectAcceptQueryForm form) {
        Long projectId = form.getProjectId();
        SrpmsProject srpmsProject = srpmsProjectService.getById(projectId);

        if (srpmsProject == null) {
            throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
        }
        JSONObject projectJson = new JSONObject();
        projectJson.put("projectType", srpmsProject.getProjectType());
        projectJson.put("projectNum", srpmsProject.getProjectNum());
        projectJson.put("projectName", srpmsProject.getProjectName());
        projectJson.put("projectFlag", srpmsProject.getProjectFlag());
        projectJson.put("projectActionDateStart", srpmsProject.getProjectActionDateStart());
        projectJson.put("projectActionDateEnd", srpmsProject.getProjectActionDateEnd());
        projectJson.put("leadPerson", srpmsProject.getLeadPerson() == null ? "" : JSONObject.parseObject(srpmsProject.getLeadPerson()));
        projectJson.put("leadDept", srpmsProject.getLeadDept() == null ? "" : JSONObject.parseObject(srpmsProject.getLeadDept()));
        projectJson.put("projectId", projectId.toString());

        Map<String, String> dictMap = sysDictService.getSysDictTranLong(SrpmsConstant.PRO_CATEGORY);
        if (dictMap != null) {
            if (StringUtils.isNotBlank(srpmsProject.getProjectType())) {
                projectJson.put("projectTypeName", dictMap.get(srpmsProject.getProjectType()));
            }
        }
        if (SrpmsConstant.PROJECT_TYPE_1.equals(srpmsProject.getProjectFlag())) {
            SrpmsProjectTranLong taskTranLong = taskTranLongService.getById(projectId);
            projectJson.put("projectResultType", taskTranLong.getProjectResultType());
            projectJson.put("belongDomain", taskTranLong.getBelongDomain());
        }

        form.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
        QueryWrapper<SrpmsProjectAccept> queryWrapper = new QueryWrapper<>();
        getAcceptQueryWrapper(queryWrapper, form);
        SrpmsProjectAccept accept = srpmsProjectAcceptMapper.selectOne(queryWrapper);


        switch (srpmsProject.getProjectType()) {// 项目类型
            case SrpmsConstant.MODIFY_PRO_TYPE_10010101:// 创新工程-重大协同创新
                SrpmsProjectApplyInnBcoo applyInnBcoo = applyInnBcooService.getById(srpmsProject.getId());
                projectJson.put("projectResultType", applyInnBcoo.getAchievementType() == null ? "" : JSONArray.parseArray(applyInnBcoo.getAchievementType()));
                projectJson.put("belongDomain", applyInnBcoo.getBelongDomain());
                break;
            case SrpmsConstant.MODIFY_PRO_TYPE_10010102:// 创新工程-协同创新团队
                SrpmsProjectApplyInnCoo applyInnCoo = applyInnCooService.getById(srpmsProject.getId());
                projectJson.put("projectResultType", "");
                projectJson.put("belongDomain", applyInnCoo.getDirection());
                break;
            case SrpmsConstant.MODIFY_PRO_TYPE_10010103:// 创新工程-先导专项
                SrpmsProjectApplyInnPre applyInnPre = applyInnPreService.getById(srpmsProject.getId());
                projectJson.put("projectResultType", applyInnPre.getAchievementType() == null ? "" : JSONArray.parseArray(applyInnPre.getAchievementType()));
                projectJson.put("belongDomain", "");
                break;
            case SrpmsConstant.MODIFY_PRO_TYPE_10010401:// 体制改革
                if (accept != null) {
                    projectJson.putAll(acceptAcademyService.queryByAcceptId(accept.getId()));
                } else {
                    SrpmsProjectTaskReform taskReform = taskReformService.getById(projectId);
                    projectJson.put("assessmentIndicators", taskReform.getAssessmentIndicators());
                }
                break;
            case SrpmsConstant.MODIFY_PRO_TYPE_10010201:// 院基科费
                if (accept != null) {
                    projectJson.putAll(acceptAcademyService.queryByAcceptId(accept.getId()));
                } else {
                    SrpmsProjectTaskAcademy taskAcademy = taskAcademyService.getById(projectId);
                    projectJson.put("assessmentIndicators", taskAcademy.getAssessmentIndicators());
                }
                break;
            case SrpmsConstant.MODIFY_PRO_TYPE_10010301:// 校基科费-青年教师
            case SrpmsConstant.MODIFY_PRO_TYPE_10010302:// 校基科费-学生项目
                if (accept != null) {
                    projectJson.putAll(acceptSchoolService.queryByAcceptId(accept.getId()));
                } else {
                    SrpmsProjectTaskSchStudent schStudent = taskSchStudentService.getById(projectId);
                    projectJson.put("projectAbstract", schStudent.getProjectAbstract());
                    projectJson.put("applyFunds", schStudent.getApplyFunds());
                }
                // 项目参加人员
                List<SrpmsProjectPersonJoinVo> personList = personJoinService.queryPersonJoinListByJoinWay(PersonJoinWayEnums.TASK_MAIN_MEMBER, projectId);
                if (personList != null && personList.size() > 0) {
                    SrpmsProjectPersonJoinVo personJoinVo;
                    int indexCount = 0;
                    Long id = 0L;
                    String name = "";
                    if (StringUtils.isNotBlank(srpmsProject.getLeadPerson())) {
                        JSONObject jsonObject = JSONObject.parseObject(srpmsProject.getLeadPerson());
                        id = jsonObject.getLong("id");
                        name = jsonObject.getString("name");
                    }
                    for (Iterator e = personList.iterator(); e.hasNext(); ) {
                        personJoinVo = (SrpmsProjectPersonJoinVo) e.next();
                        if (id != personJoinVo.getPersonId()) {
                            indexCount++;
                        }
                    }
                    if (indexCount == personList.size()) {
                        personJoinVo = new SrpmsProjectPersonJoinVo();
                        personJoinVo.setPersonId(id);
                        personJoinVo.setPersonName(name);
                        personList.add(personJoinVo);
                    }
                    projectJson.put("mostMemberList", personList);
                }
                break;
            default:
                break;
        }
        // 相关附件
        if (accept != null) {
            projectJson.put("attachmentFile", attachmentService.queryAttachmentListAccept(accept.getId()));
            projectJson.put("id", accept.getId().toString());
            projectJson.put("createTime", accept.getCreateTime());
        } else {
            projectJson.put("createTime", LocalDateTime.now());
        }

        return projectJson;
    }


    /**
     * 验证是否其他PI执行验收操作
     *
     * @param queryForm
     * @param user
     */
    @Override
    public void searchAcceptExists(SrpmsProjectAcceptQueryForm queryForm, UserVo user) {

        QueryWrapper<SrpmsProjectAccept> queryWrapper = new QueryWrapper<>();
        getAcceptQueryWrapper(queryWrapper, queryForm);
        SrpmsProjectAccept accept = srpmsProjectAcceptMapper.selectOne(queryWrapper);
        if (accept != null && !SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(accept.getStatus())) {
            throw new BaseException(SrpmsErrorType.ACCEPT_NO_ADD_DATA);
        }
    }

    /**
     * 项目验收保存操作service接口实现
     *
     * @param form
     * @param user
     * @param dept
     * @return
     */
    @Override
    public String saveOrUpdate(SrpmsProjectAcceptForm form, UserVo user, DeptVo dept, boolean submitFlag) {
        Long id = form.getId();
        Long projectId = form.getProjectId();
        SrpmsProject srpmsProject = srpmsProjectService.getById(projectId);
        if (srpmsProject == null) {
            throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
        }

        SrpmsProjectAcceptQueryForm queryForm = new SrpmsProjectAcceptQueryForm();
        queryForm.setProjectId(projectId);
        searchAcceptExists(queryForm, user);

        SrpmsProjectAccept accept = new SrpmsProjectAccept();
        if (id == null || id == 0L) {// 执行保存操作
            BeanUtils.copyProperties(srpmsProject, accept);
            String year = DateFormatUtils.format(new Date(), "yyyy");
            String acceptNum = serialNoCenterService.getNextAcceptNo(year, srpmsProject.getProjectNum());

            accept.setAcceptNum(acceptNum);
            accept.setDeptId(CommonUtil.getLongValue(dept.getDeptId()));
            accept.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
            accept.setDeptName(srpmsProject.getLeadDept() == null ? "" : JSONObject.parseObject(srpmsProject.getLeadDept()).getString("deptName"));
            accept.setCreateBy(user.getId());
            accept.setProjectId(projectId);
            accept.setId(null);
            this.save(accept);

            srpmsProject.setAcceptNum(acceptNum);
            srpmsProjectService.saveOrUpdate(srpmsProject);// 更新验收编号到项目表

            id = accept.getId();
        } else {// 执行更新操作
            accept.setId(id);
            if (submitFlag) {
                accept.setStatus(form.getStatus());
            }
            accept.setUpdateBy(user.getId());
            this.saveOrUpdate(accept);
        }


        List<SrpmsProjectAttachmentVo> attachmentVoList = form.getAttachmentFile();
        switch (srpmsProject.getProjectType()) {// 项目类型
            case SrpmsConstant.MODIFY_PRO_TYPE_10010401:// 改革经费
            case SrpmsConstant.MODIFY_PRO_TYPE_10010201:// 院基科费
                SrpmsProjectAcceptAcademy academy = new SrpmsProjectAcceptAcademy();
                BeanUtils.copyProperties(form, academy);
                academy.setAcceptId(id);
                acceptAcademyService.cleanAndSaveAcceptAcademy(academy, id);

                break;
            case SrpmsConstant.MODIFY_PRO_TYPE_10010301:// 校基科费-青年教师
            case SrpmsConstant.MODIFY_PRO_TYPE_10010302:// 校基科费-学生项目
                SrpmsProjectAcceptSchool school = new SrpmsProjectAcceptSchool();
                BeanUtils.copyProperties(form, school);
                school.setAcceptId(id);
                school.setProjectResultDirectory(form.getProjectResultDirectory() == null ? "" : JSONArray.toJSONString(form.getProjectResultDirectory()));
                acceptSchoolService.cleanAndSaveAcceptSchool(school, id);

                break;
            default:
                break;
        }

        // 附件
        if (attachmentVoList != null && attachmentVoList.size() > 0) {
            attachmentService.saveAttachmentListAccept(attachmentVoList, id);
        }

        return id.toString();
    }

    /**
     * 项目验收报告提交操作service接口实现
     *
     * @param form
     * @param user
     * @param dept
     * @return
     */
    @Override
    public void submit(SrpmsProjectAcceptForm form, UserVo user, DeptVo dept) {
        Long id = form.getId();
        if (id == null) {
            throw new BaseException(SrpmsErrorType.UPDATE_SAVE_BEFORE_SUBMIT);
        }
        SrpmsProjectAccept accept = this.getById(id);
        if (accept == null) {
            throw new BaseException(SrpmsErrorType.ACCEPT_NO_DATA);
        }
        if (!SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(accept.getStatus())) {
            throw new BaseException(SrpmsErrorType.ACCEPT_HAVE_SUBMITTED);
        }
        // 执行保存操作
        form.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode());
        this.saveOrUpdate(form, user, dept, true);

        // 调用流程
        log.info("项目验收，提交验收记录，已更新项目验收状态, acceptId:{}", id);
        SrpmsVoucher voucher = voucherService.getSrpmsVoucherByReject(accept.getId());// 查询项目是否进行驳回操作
        if (voucher != null ) {
            flowService.againSubmit(voucher, dept, false);
        } else {
            // 新增一个单据
            voucher = voucherService.generateNewVoucherAccept(accept, VoucherTypeEnums.ACCEPT_BOOK, user);
            // 发起流程
            bpmService.startProcess(voucher, user, dept, null);
        }

        log.info("项目验收，提交验收记录，已发起流程, acceptId:{}", id);

    }

    /**
     * 项目验收催告数据查询service接口实现
     */
    @Override
    public void projectAcceptRemain() {

        SrpmsProjectQueryForm queryForm = new SrpmsProjectQueryForm();
        QueryWrapper<SrpmsProject> queryWrapper = new QueryWrapper<>();
        LocalDateTime nowDate = LocalDateTime.now();
        LocalDateTime current = nowDate.minusMonths(1);// 当前时间往前推一个月
        String strCurrent = current.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        queryForm.setProjectActionDateStart(DateUtil.strToLocalDateTime(strCurrent));// 设置开始时间
        queryForm.setProjectActionDateEnd(DateUtil.strToLocalDateTimeEnd(strCurrent));// 设置结束时间
        getProjectQueryWrapper(queryWrapper, queryForm);
        List<SrpmsProject> list = srpmsProjectService.list(queryWrapper);// 查询需要催告的项目数据
        List<SrpmsProject> newList = new ArrayList<>();// 查询需要催告的项目数据
        if (list != null && list.size() > 0) {
            String key = "person_";
            String deptKey;
            List<String> deptList = new ArrayList<>();
            SendMssInfoForm sendMes = new SendMssInfoForm();// 组装短信对象数据
            StringBuilder stringBuilder = new StringBuilder();// 拼接电话号码对象
            SrpmsProject srpmsProject;
            Map<String, String> keyMap = new HashMap<>();
            Map<String, String> deptMap = new HashMap<>();
            JSONObject personJson;
            for (Iterator e = list.iterator(); e.hasNext(); ) {
                srpmsProject = (SrpmsProject) e.next();
                if (srpmsProject.getLeadPerson() != null) {
                    personJson = JSONObject.parseObject(srpmsProject.getLeadPerson());
                    if (personJson.getString("id") == null) {
                        continue;
                    }
                    key = key.concat(personJson.getString("id"));
                    if (keyMap.containsKey(key)) {
                        continue;
                    }
                    deptKey = personJson.getString("dept").concat(personJson.getString("deptName"));
                    if (!deptMap.containsKey(deptKey)) {
                        deptList.add(personJson.getString("dept"));
                    }
                    stringBuilder.append(",");
                    stringBuilder.append(personJson.getString("phone"));
                    keyMap.put(key, key);
                    deptMap.put(deptKey, deptKey);

                    srpmsProject.setStatus(SrpmsProjectStatusEnums.PEROJECT_NOT_ACCEPT.getCode());
                    newList.add(srpmsProject);
                }
            }

            if (newList != null && newList.size() > 0) {
                srpmsProjectService.saveBatch(newList);
            }

            // 查询4A中对应的管理员
            Result<List<UserVo>> listResult = userFeignService.getByRoleCode(ProcessConstant.BPM_CLIENT_NAME, SrpmsConstant.SRPMS_ADMIN, deptList);
            if (!PlatformErrorType.GATEWAY_SERVICE_UNABLE.getCode().equals(listResult.getCode())) {
                List<UserVo> userVoList = listResult.getData();
                if (userVoList != null && userVoList.size() > 0) {
                    UserVo userVo;
                    for (Iterator e = userVoList.iterator(); e.hasNext(); ) {
                        userVo = (UserVo) e.next();
                        stringBuilder.append(",");
                        stringBuilder.append(userVo.getPhone());
                    }
                }
            }

            String telePhone = stringBuilder.toString();
            String mssContent = "尊敬的用户，您好！您有项目将在一个月后执行结束，请您及时登录系统确认，祝您生活愉快!";
            if (StringUtils.isNotBlank(telePhone)) {
                telePhone = telePhone.substring(1);
                sendMes.setReceiveTelephone(telePhone);
                sendMes.setMssContent(mssContent);
                log.info("项目验收催告，开始调用发短信功能, telePhone:{}", telePhone);
                sendMssFeignService.sendMss(sendMes);
                log.info("项目验收催告，调用发短信功能结束, telePhone:{}", telePhone);
            }
        }
    }

    /**
     * 导出pdf文件
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public String exportPdfFile(Long id) throws Exception {
        String wordFilePath = this.exportWordFile(id);
        //转格式
        String pdfFilePath = pdfMergeService.wordToPdf(wordFilePath);
        String pdfFinalName = wordFilePath.replace(SrpmsConstant.EXT_WORD_DOCX, SrpmsConstant.EXT_PDF);
        log.info("PDF文件合并前路径：{}, 合并后路径：{}, acceptId:{}", pdfFilePath, pdfFinalName, id);
        //合并
        pdfMergeService.mergeAttachmentAccept(pdfFilePath, id, pdfFinalName);
        return pdfFinalName;
    }

    /**
     * 生成word文件方法
     *
     * @param id
     * @return
     */
    public String exportWordFile(Long id) {
        InputStream fileIn = null;
        try {
            Map<String, Object> dataMap = new HashMap<>();
            //获取所有信息
            JSONObject voJson = this.queryById(id);

            JSONObject perJson = JSONObject.parseObject(voJson.getString("leadPerson"));
            JSONObject dept = JSONObject.parseObject(voJson.getString("leadDept"));
            String title = "";
            String templateName = "";
            String projectId = voJson.getString("projectId");
            switch (voJson.getString("projectType")) {// 项目类型
                case SrpmsConstant.MODIFY_PRO_TYPE_10010201:// 院基科费
                    title = "医科院院基科费验收报告_";
                    templateName = "template_accept_academy";
                    SrpmsProjectTaskAcademy taskAcademy = taskAcademyService.getById(projectId);
                    dataMap.put("assessmentIndicators", WordConvertUtil.htmlToText(taskAcademy.getAssessmentIndicators()));
                    dataMap.put("projectCompletionCase", WordConvertUtil.htmlToText(voJson.getString("projectCompletionCase")));// 研究内容考核指标完成情况
                    dataMap.put("projectUnfinishReason", WordConvertUtil.htmlToText(voJson.getString("projectUnfinishReason")));// 分析未完成原因
                    dataMap.put("projectImplementExperience", WordConvertUtil.htmlToText(voJson.getString("projectImplementExperience")));// 项目实施经验
                    break;
                case SrpmsConstant.MODIFY_PRO_TYPE_10010301:// 校基科费-青年教师
                    title = "医科院校基科费验收报告_";
                    templateName = "template_accept_school";
                    SrpmsProjectApplySchTeach schTeacher = applySchTeachService.getById(projectId);
                    dataMap.put("projectKeyword", schTeacher.getProjectKeyword());
                    SrpmsProjectTaskSchStudent schStudent = taskSchStudentService.getById(projectId);
                    dataMap.put("projectAbstract", WordConvertUtil.htmlToText(schStudent.getProjectAbstract()));
                    dataMap.put("projectContentIndicators", WordConvertUtil.htmlToText(voJson.getString("projectContentIndicators")));// 研究内容及考核指标执行情况描述
                    dataMap.put("projectGainResult", WordConvertUtil.htmlToText(voJson.getString("projectGainResult")));// 研究工作取得的重要进展与成果
                    dataMap.put("projectTeamConstruction", WordConvertUtil.htmlToText(voJson.getString("projectTeamConstruction")));// 团队建设，人才培养
                    dataMap.put("projectQuestionAdvice", WordConvertUtil.htmlToText(voJson.getString("projectQuestionAdvice")));// 存在问题及建议

                    break;
                case SrpmsConstant.MODIFY_PRO_TYPE_10010302:// 校基科费-学生项目
                    title = "医科院校基科费验收报告_";
                    templateName = "template_accept_school";
                    schStudent = taskSchStudentService.getById(projectId);
                    SrpmsProjectApplySchStu applySchStu = applySchStuService.getById(projectId);
                    dataMap.put("projectKeyword", applySchStu.getProjectKeyword());
                    dataMap.put("projectAbstract", WordConvertUtil.htmlToText(schStudent.getProjectAbstract()));
                    dataMap.put("projectContentIndicators", WordConvertUtil.htmlToText(voJson.getString("projectContentIndicators")));// 研究内容及考核指标执行情况描述
                    dataMap.put("projectGainResult", WordConvertUtil.htmlToText(voJson.getString("projectGainResult")));// 研究工作取得的重要进展与成果
                    dataMap.put("projectTeamConstruction", WordConvertUtil.htmlToText(voJson.getString("projectTeamConstruction")));// 团队建设，人才培养
                    dataMap.put("projectQuestionAdvice", WordConvertUtil.htmlToText(voJson.getString("projectQuestionAdvice")));// 存在问题及建议

                    break;
                case SrpmsConstant.MODIFY_PRO_TYPE_10010401:// 改革经费
                    title = "医科院体制改革验收报告_";
                    templateName = "template_accept_reform";
                    SrpmsProjectTaskReform taskReform = taskReformService.getById(projectId);
                    dataMap.put("assessmentIndicators", WordConvertUtil.htmlToText(taskReform.getAssessmentIndicators()));
                    dataMap.put("projectCompletionCase", WordConvertUtil.htmlToText(voJson.getString("projectCompletionCase")));// 研究内容考核指标完成情况
                    dataMap.put("projectUnfinishReason", WordConvertUtil.htmlToText(voJson.getString("projectUnfinishReason")));// 分析未完成原因
                    dataMap.put("projectImplementExperience", WordConvertUtil.htmlToText(voJson.getString("projectImplementExperience")));// 项目实施经验
                    break;
                default:
                    break;
            }
            dataMap.put("data", voJson);
            dataMap.put("leader", perJson);
            dataMap.put("dept", dept);

            //当前时间
            String nowDate = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
            dataMap.put("nowDate", nowDate);

            String projectActionDateStart = voJson.getString("projectActionDateStart");
            if (projectActionDateStart != null && projectActionDateStart.length() != 0) {
                dataMap.put("projectActionDateStart", projectActionDateStart.split("T")[0]);
            } else {
                dataMap.put("projectActionDateStart", "");
            }
            String projectActionDateEnd = voJson.getString("projectActionDateEnd");
            if (projectActionDateEnd != null && projectActionDateEnd.length() != 0) {
                dataMap.put("projectActionDateEnd", projectActionDateEnd.split("T")[0]);
            } else {
                dataMap.put("projectActionDateEnd", "");
            }

            String createTime = voJson.getString("createTime");
            if (createTime != null && createTime.length() != 0) {
                dataMap.put("createTime", createTime.split("T")[0]);
            } else {
                dataMap.put("createTime", "");
            }

            String filename = new StringBuffer(title)
                    .append(DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"))
                    .append(RandomUtils.nextInt(0, 9999))
                    .append(SrpmsConstant.EXT_WORD_DOCX).toString();
            String finalWordName = wordExportService.exportWord(templateName, dataMap, filename);
            fileIn = new FileInputStream(finalWordName);
            return finalWordName;
        } catch (Exception e) {
            log.error("导出word异常。", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        } finally {
            if (fileIn != null) {
                try {
                    fileIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通用查询
     *
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<SrpmsProjectAccept> getQueryWrapper(QueryWrapper<SrpmsProjectAccept> queryWrapper, SrpmsProjectAcceptQueryForm queryForm) {
        //条件拼接
        if (queryForm.getProjectId() != null && queryForm.getProjectId() != 0L) {
            queryWrapper.eq(SrpmsProjectAccept.PROJECT_ID, queryForm.getProjectId());
        }
        if (StringUtils.isNotBlank(queryForm.getProjectNum())) {
            queryWrapper.like(SrpmsProjectAccept.PROJECT_NUM, queryForm.getProjectNum());
        }
        if (StringUtils.isNotBlank(queryForm.getProjectName())) {
            queryWrapper.like(SrpmsProjectAccept.PROJECT_NAME, queryForm.getProjectName());
        }
        if (StringUtils.isNotBlank(queryForm.getProjectType())) {
            if (!queryForm.getProjectType().contains(",")) {
                queryWrapper.eq(SrpmsProjectAccept.PROJECT_TYPE, queryForm.getProjectType());
            } else {
                String[] arr = queryForm.getProjectType().split(",");
                queryWrapper.in(SrpmsProjectAccept.PROJECT_TYPE, arr);
            }
        }
        if (StringUtils.isNotBlank(queryForm.getDeptName())) {
            queryWrapper.eq(SrpmsProjectAccept.DEPT_NAME, queryForm.getDeptName());
        }
        if (StringUtils.isNotBlank(queryForm.getDeptId())) {
            queryWrapper.eq(SrpmsProjectAccept.DEPT_ID, queryForm.getDeptId());
        }
        if (StringUtils.isNotBlank(queryForm.getStatus())) {
            if (!queryForm.getStatus().contains(",")) {
                queryWrapper.eq(SrpmsProjectAccept.STATUS, queryForm.getStatus());
            } else {
                String[] arr = queryForm.getStatus().split(",");
                queryWrapper.in(SrpmsProjectAccept.STATUS, arr);
            }
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(SrpmsProjectAccept.CREATE_BY, queryForm.getCreateBy());
        }
        queryWrapper.orderByDesc(SrpmsProjectAccept.CREATE_TIME);
        return queryWrapper;
    }

    public QueryWrapper<SrpmsProjectAccept> getAcceptQueryWrapper(QueryWrapper<SrpmsProjectAccept> queryWrapper,
                                                                  SrpmsProjectAcceptQueryForm queryForm) {
        if (queryForm.getId() != null && queryForm.getId() != 0L) {
            queryWrapper.notIn(SrpmsProjectAccept.ID, queryForm.getId());
        }
        if (queryForm.getProjectId() != null && queryForm.getProjectId() != 0L) {
            queryWrapper.eq(SrpmsProjectAccept.PROJECT_ID, queryForm.getProjectId());
        }
        if (StringUtils.isNotBlank(queryForm.getProjectType())) {
            queryWrapper.eq(SrpmsProjectAccept.PROJECT_TYPE, queryForm.getProjectType());
        }
        if (StringUtils.isNotBlank(queryForm.getStatus())) {
            queryWrapper.eq(SrpmsProjectAccept.STATUS, queryForm.getStatus());
        }
        return queryWrapper;
    }

    /**
     * 根据项目执行结束时间查询项目数据信息
     *
     * @param queryWrapper
     * @param queryForm
     * @return
     */
    public QueryWrapper<SrpmsProject> getProjectQueryWrapper(QueryWrapper<SrpmsProject> queryWrapper, SrpmsProjectQueryForm queryForm) {
        queryWrapper.between(SrpmsProject.PROJECT_ACTION_DATE_END, queryForm.getProjectActionDateStart(), queryForm.getProjectActionDateEnd());
        queryWrapper.isNull(SrpmsProject.PROJECT_FLAG);
        return queryWrapper;
    }

}

