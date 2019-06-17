package com.deloitte.services.srpmp.project.task.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeVO;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskTranLongForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskTranLongVo;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.enums.BudgetCategoryEnums;
import com.deloitte.services.srpmp.common.enums.PersonJoinWayEnums;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectStatusEnums;
import com.deloitte.services.srpmp.common.enums.TaskCategoryEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.util.JSONConvert;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.project.apply.service.impl.SrpmsProjectApplyInnCommonServiceImpl;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsVoucher;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectAttachmentService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectBudgetDetailService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectTaskService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsVoucherService;
import com.deloitte.services.srpmp.project.base.service.impl.SrpmsProjectBpmServiceImpl;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTranLong;
import com.deloitte.services.srpmp.project.task.mapper.SrpmsProjectTaskTranLongMapper;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskTranLongService;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-17
 * @Description :  SrpmsProjectTaskTranLong服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsProjectTaskTranLongServiceImpl extends ServiceImpl<SrpmsProjectTaskTranLongMapper, SrpmsProjectTranLong> implements ISrpmsProjectTaskTranLongService {


    @Autowired
    private SrpmsProjectTaskTranLongMapper taskTranLongMapper;

    @Autowired
    private ISrpmsProjectService projectService;

    @Autowired
    private ISrpmsProjectPersonJoinService personJoinService;

    @Autowired
    private ISrpmsProjectAttachmentService attachmentService;

    @Autowired
    private ISrpmsProjectBudgetDetailService budgetDetailService;

    @Autowired
    private SrpmsProjectApplyInnCommonServiceImpl commonService;

    @Autowired
    private ISrpmsProjectFlowService flowServicel;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private ISrpmsVoucherService voucherService;

    @Autowired
    public SrpmsProjectBpmServiceImpl bpmService;

    @Autowired
    private ISrpmsProjectTaskService taskService;

    /**
     * 点击新增按钮跳转请求service接口实现
     *
     * @param user
     * @param dept
     * @return
     */
    @Override
    public JSONObject saveJumpPage(UserVo user, DeptVo dept) {
        JSONObject result = new JSONObject();
        user.setWorkPerYear(null);
        result.put("leadPerson", user);
        result.put("leadDept", dept);
//        if(StringUtils.isNotBlank(user.getRemark())) {
//            Map<String, String> dictMap = sysDictService.getSysDictMap(new String[]{SrpmsConstant.PRO_CATEGORY});
//            result.put("belongDomain", user.getRemark());
//            if(dictMap != null) {
//                result.put("belongDomainName", dictMap.get(SrpmsConstant.PRO_CATEGORY.concat(user.getRemark())));
//            }
//        } else {
//            result.put("belongDomain", "");
//            result.put("belongDomainName", "");
//        }
        JSONConvert.convertJson(result);
        return result;
    }

    /**
     * 根据ID查询横纵向项目任务书service接口实现
     *
     * @param id
     * @return
     */
    @Override
    public SrpmsProjectTaskTranLongVo queryById(Long id) {
        SrpmsProjectTaskTranLongVo taskTranLongVo = new SrpmsProjectTaskTranLongVo();

        // 获取项目信息
        SrpmsProject projectEntity = projectService.getById(id);
        if (projectEntity == null) {
            throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
        }

        // 查询横纵向任务书数据
        SrpmsProjectTranLong taskTranLong = taskTranLongMapper.selectById(id);
        if (taskTranLong == null) {
            throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
        }


        Map<String, String> dictCategoryMap = sysDictService.getSysDictTranLong(SrpmsConstant.PRO_CATEGORY);
        Map<String, String> dictMap = sysDictService.getSysDictMap(new String[]{SrpmsConstant.PRO_ENTER_TYPE});
        if (dictCategoryMap != null) {
            if (StringUtils.isNotBlank(projectEntity.getProjectType())) {
                taskTranLongVo.setProjectTypeName(dictCategoryMap.get(projectEntity.getProjectType()));
            }
        }
        if(dictMap != null) {
            if (StringUtils.isNotBlank(taskTranLong.getProEnterType())) {
                taskTranLongVo.setProEnterTypeName(dictMap.get(SrpmsConstant.PRO_ENTER_TYPE.concat(taskTranLong.getProEnterType())));
            }
        }

        BeanUtils.copyProperties(taskTranLong, taskTranLongVo);
        taskTranLongVo.setProjectNum(projectEntity.getProjectNum());// 项目编号
        taskTranLongVo.setProjectName(projectEntity.getProjectName());// 项目名称
        taskTranLongVo.setProjectType(projectEntity.getProjectType());// 项目类型
        taskTranLongVo.setStatus(projectEntity.getStatus());// 项目状态
        taskTranLongVo.setSubjectCategory(projectEntity.getSubjectCategory());// 学科分类
        taskTranLongVo.setProjectActionDateStart(projectEntity.getProjectActionDateStart());// 项目执行开始时间
        taskTranLongVo.setProjectActionDateEnd(projectEntity.getProjectActionDateEnd());// 项目执行结束时间
        if (StringUtils.isNotBlank(projectEntity.getLeadPerson())) {// 项目负责人
            taskTranLongVo.setLeadPerson(JSONObject.parseObject(projectEntity.getLeadPerson()));
        }
        if (StringUtils.isNotBlank(projectEntity.getLeadDept())) {// 项目负责人承担单位
            taskTranLongVo.setLeadDept(JSONObject.parseObject(projectEntity.getLeadDept()));
        }
        taskTranLongVo.setId(CommonUtil.objectToString(id));// 主键赋值

        // 院内项目参加人员
        taskTranLongVo.setMainMemberList(personJoinService.queryPersonJoinListByJoinWay(PersonJoinWayEnums.TASK_MAIN_MEMBER, id));

        // 院外项目参加人员
        taskTranLongVo.setOutJoinPerson(JSONArray.parseArray(taskTranLong.getOutJoinPerson()));

        // 项目经费来源
        taskTranLongVo.setProFundsSource(JSONArray.parseArray(taskTranLong.getProFundsSource()));

        //年度预算
        taskTranLongVo.setBudgetPreYearList(budgetDetailService.queryBudgetDetailListByCategory(BudgetCategoryEnums.TASK_TRAN_LONG_DETAIL, id));

        // 相关附件
        taskTranLongVo.setAttachmentFile(attachmentService.queryAttachmentListTranLong(id));

        // 任务分解
        taskTranLongVo.setSubTaskList(taskService.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_TRAN_LONG, id));

        //查询审批历史记录
        SrpmsVoucher voucherEntity = voucherService.getSrpmsVoucherByUpdateId(id);
        if(voucherEntity != null) {
            List<TaskNodeVO> list = bpmService.queryAuditHistory(CommonUtil.objectToString(voucherEntity.getId()));
            taskTranLongVo.setApproveHistory(list);
        }
        return taskTranLongVo;
    }

    @Override
    public SrpmsProjectTaskTranLongVo getTranById(Long id) {
        SrpmsProjectTaskTranLongVo taskTranLongVo = new SrpmsProjectTaskTranLongVo();

        // 查询横纵向任务书数据
        SrpmsProjectTranLong taskTranLong = taskTranLongMapper.selectById(id);
        if (taskTranLong == null) {
            throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
        }

        BeanUtils.copyProperties(taskTranLong, taskTranLongVo);

        //年度预算
        taskTranLongVo.setBudgetPreYearList(budgetDetailService.queryBudgetDetailListByCategory(BudgetCategoryEnums.TASK_TRAN_LONG_DETAIL, id));

        // 项目经费来源
        taskTranLongVo.setProFundsSource(JSONArray.parseArray(taskTranLong.getProFundsSource()));
        return taskTranLongVo;
    }


    /**
     * 横纵项向项目执行保存操作service接口实现
     *
     * @param form
     * @param user
     * @param dept
     * @return
     */
    @Override
    public String saveOrUpdate(SrpmsProjectTaskTranLongForm form, UserVo user, DeptVo dept) {
        Long projectId = form.getId();

        form.setProjectFlag(SrpmsConstant.PROJECT_TYPE_1);
        JSONObject projectJson = JSONObject.parseObject(JSONObject.toJSONString(form));
        SrpmsProjectTranLong taskTranLong = JSONObject.parseObject(projectJson.toJSONString(), SrpmsProjectTranLong.class);
        BeanUtils.copyProperties(form, taskTranLong);
        taskTranLong.setProjectCode(form.getProjectCode());// 项目编码
        // 院外项目参加人员
        taskTranLong.setOutJoinPerson(form.getOutJoinPerson() == null ? "" : JSONArray.toJSONString(form.getOutJoinPerson()));
        // 项目经费来源
        taskTranLong.setProFundsSource(form.getProFundsSource() == null ? "" : JSONArray.toJSONString(form.getProFundsSource()));
        ProjectCategoryEnums categoryEnums = ProjectCategoryEnums.TRAN_LONG_TASK;
        categoryEnums.setHeader(form.getProjectType());
        if (projectId == null || projectId == 0L) {// 执行保存操作

            projectId = commonService.insertCommonInfo(projectJson, categoryEnums,dept);

            taskTranLong.setId(projectId);
            this.save(taskTranLong);
        } else {// 执行变更操作
            this.saveOrUpdate(taskTranLong);
            projectId = commonService.saveOrUpdateCommonInfo(projectJson, categoryEnums);
        }

        // 分解任务
        taskService.cleanAndSaveTask(form.getSubTaskList(), TaskCategoryEnums.TASK_INNOVATE_TRAN_LONG, projectId);

        return projectId.toString();
    }

    /**
     * 横纵项向项目执行提交操作service接口实现
     *
     * @param form
     * @param user
     * @param dept
     * @return
     */
    @Override
    public String submit(SrpmsProjectTaskTranLongForm form, UserVo user, DeptVo dept) {
        Long id = form.getId();

        if(id == null || id == 0L) {
            throw new BaseException(SrpmsErrorType.TASK_NOT_COMPLETE);
        }

        // 校验任务书状态，只有未提交的任务书才能提交
        SrpmsProject project = projectService.getById(id);
        if (!SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(project.getStatus())){
            throw new BaseException(SrpmsErrorType.TASK_HAVE_SUBMITTED);
        }
        form.setStatus(SrpmsProjectStatusEnums.PEROJECT_TASK_SUBMIT.getCode());// 项目状态
        //提交任务书
        this.saveOrUpdate(form, user, dept);

        log.info("横纵项向项目，提交任务书，已更新项目状态, projectId:{}", id);
        //发起流程
        flowServicel.startAuditProcess(id, VoucherTypeEnums.TRAN_LONG_TASK_BOOK, user, dept);

        log.info("横纵项向项目，提交任务书，已发起流程, projectId{}", id);

        return id.toString();
    }


}

