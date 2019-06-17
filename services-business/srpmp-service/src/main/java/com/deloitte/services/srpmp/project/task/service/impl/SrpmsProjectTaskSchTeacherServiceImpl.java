package com.deloitte.services.srpmp.project.task.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskSchTeacherForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskSchTeacherVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.enums.PersonJoinWayEnums;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectStatusEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.impl.WordExportServiceImpl;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplySchTeach;
import com.deloitte.services.srpmp.project.apply.mapper.SrpmsProjectApplySchTeachMapper;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.impl.PdfMergeServiceImpl;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskSchTeacher;
import com.deloitte.services.srpmp.project.task.mapper.SrpmsProjectTaskSchTeacherMapper;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskSchTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-03-14
 * @Description :  SrpmsProjectTaskSchTeacher服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsProjectTaskSchTeacherServiceImpl extends ServiceImpl<SrpmsProjectTaskSchTeacherMapper, SrpmsProjectTaskSchTeacher> implements ISrpmsProjectTaskSchTeacherService {


    @Autowired
    private SrpmsProjectTaskSchTeacherMapper srpmsProjectTaskMapper;

    @Autowired
    private SrpmsProjectApplySchTeachMapper srpmsProjectApplyMapper;

    @Autowired
    private ISrpmsProjectService projectService;

    @Autowired
    private ISrpmsProjectPersonService personService;

    @Autowired
    private ISrpmsProjectPersonJoinService personJoinService;

    @Autowired
    private WordExportServiceImpl wordExportService;

    @Autowired
    private ISrpmsProjectFlowService flowServicel;

    /**
     * 根据ID查询基科费任务书service接口实现
     *
     * @param projectId
     * @return
     */
    @Override
    public SrpmsProjectTaskSchTeacherVo queryById(Long projectId) {
        SrpmsProjectTaskSchTeacherVo taskVo = new SrpmsProjectTaskSchTeacherVo();
        // 查询基科费任务书数据
        SrpmsProjectTaskSchTeacher srpmsProjectTask = srpmsProjectTaskMapper.selectById(projectId);

        if (srpmsProjectTask == null) {
            // 查询基科费申请书数据
            SrpmsProjectApplySchTeach srpmsProjectApply = srpmsProjectApplyMapper.selectById(projectId);
            if (srpmsProjectApply == null) {
                throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
            }
            BeanUtils.copyProperties(srpmsProjectApply, taskVo);
            taskVo.setId(CommonUtil.objectToString(projectId));// 主键赋值

        } else {
            BeanUtils.copyProperties(srpmsProjectTask, taskVo);
            taskVo.setId(CommonUtil.objectToString(projectId));// 主键赋值
        }

        //获取项目信息
        SrpmsProjectVo srpmsProjectVo = new SrpmsProjectVo();
        SrpmsProject projectEntity = projectService.getById(projectId);
        if (projectEntity == null) {
            throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
        }
        BeanUtils.copyProperties(projectEntity, srpmsProjectVo);
        srpmsProjectVo.setId(projectId);
        if(StringUtils.isNotBlank(projectEntity.getLeadPerson())) {
            taskVo.setLeadPerson(JSONObject.parseObject(projectEntity.getLeadPerson()));
        }
        if(StringUtils.isNotBlank(projectEntity.getLeadDept())) {
            taskVo.setLeadDept(JSONObject.parseObject(projectEntity.getLeadDept()));
        }
        BeanUtils.copyProperties(srpmsProjectVo, taskVo);
        taskVo.setSrpmsProjectVo(srpmsProjectVo);


        // 项目参加人员
        PersonJoinWayEnums personJoinWay = PersonJoinWayEnums.APPLY_MAIN_MEMBER;
        List<SrpmsProjectPersonJoinVo> personList = personJoinService.queryPersonJoinListByJoinWay(personJoinWay, projectId);
        if (personList != null && personList.size() > 0) {
            taskVo.setPersonJoinVoList(personList);
        } else {
            taskVo.setPersonJoinVoList(new ArrayList<>());
        }

        return taskVo;
    }

    /**
     * 保存基科费任务书操作service接口实现
     *
     * @param taskForm
     * @return
     */
    @Override
    public Result saveSrpmsProjectTask(SrpmsProjectTaskSchTeacherForm taskForm, boolean taskFlag) {
        long projectId = taskForm.getId();

        // 更新项目参加人员
        PersonJoinWayEnums personJoinWay = PersonJoinWayEnums.TASK_MAIN_MEMBER;
        personJoinService.cleanAndSavePersonJoin(taskForm.getPersonJoinVoList(), personJoinWay, projectId);

        // 保存/更新基科费任务书
        SrpmsProjectTaskSchTeacher srpmsProjectTask = new SrpmsProjectTaskSchTeacher();
        BeanUtils.copyProperties(taskForm, srpmsProjectTask);
        srpmsProjectTask.setId(projectId);
        if (taskFlag && taskForm.getId() == null) {
            this.save(srpmsProjectTask);// 保存操作
        } else {
            this.saveOrUpdate(srpmsProjectTask);// 更新操作
        }

        return Result.success(CommonUtil.objectToString(projectId));
    }

    /**
     * 提交基科费任务书操作service接口实现
     *
     * @param taskForm
     * @return
     */
    @Override
    public Result submitSrpmsProjectTask(SrpmsProjectTaskSchTeacherForm taskForm, UserVo userVo, DeptVo deptVo) {
        long projectId = taskForm.getId();

        SrpmsProject projectEntity = projectService.getById(projectId);
        if (projectEntity == null) {
            throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
        }
        if (!SrpmsProjectStatusEnums.PEROJECT_HAS_PUBLICITY.getCode().equals(projectEntity.getStatus())) {
            throw new BaseException(SrpmsErrorType.PUBLICED_CAN_SUBMITTED);
        }

        boolean checkFlag = false;
        StringBuilder stringBuilder = new StringBuilder();
        checkFlag = checkTask(taskForm, checkFlag, stringBuilder);

        // 提交的时候验证数据
        if (checkFlag) {
            String errorMsg = stringBuilder.toString();
            errorMsg = errorMsg.substring(0, errorMsg.length() - 1);
            throw new BaseException(SrpmsErrorType.TASK_NOT_COMPLETE, errorMsg);
        }

        //更新项目状态
        projectService.submitTaskProject(projectId);

        log.info("校基科费教师，提交任务书，已更新项目状态, projectId:{}", taskForm.getId());
        //发起流程
        flowServicel.startAuditProcess(taskForm.getId(), VoucherTypeEnums.TASK_BOOK, userVo, deptVo);

        log.info("校基科费教师，提交申请书，已发起流程, projectId:{}", taskForm.getId());

        return Result.success(CommonUtil.objectToString(projectId));
    }

    /**
     * 任务书提交check验证
     *
     * @param taskForm
     * @param checkFlag
     * @param errorMsg
     * @return
     */
    public boolean checkTask(SrpmsProjectTaskSchTeacherForm taskForm, boolean checkFlag, StringBuilder errorMsg) {
        // 项目编号
        errorMsg.append("任务书-校基科费-学生项目提交数据校验不通过字段：");

        // 项目摘要
        if (taskForm.getProjectAbstract() == null) {
            checkFlag = true;
            errorMsg.append("项目摘要为空，");
        } else if (CommonUtil.maxLengthCheck(taskForm.getProjectAbstract(), 500)) {
            checkFlag = true;
            errorMsg.append("项目摘要录入超过设定长度，");
        }

        // 提交成果形式和主要考核指标
        if (taskForm.getResultAssessmentIndicators() == null) {
            checkFlag = true;
            errorMsg.append("提交成果形式和主要考核指标为空，");
        } else if (CommonUtil.maxLengthCheck(taskForm.getResultAssessmentIndicators(), 1000)) {
            checkFlag = true;
            errorMsg.append("提交成果形式和主要考核指标录入超过设定长度，");
        }

        // 年度计划
        if (taskForm.getResearchYearPlan() == null) {
            checkFlag = true;
            errorMsg.append("研究计划及指标完成情况为空，");
        }

        return checkFlag;
    }
}

