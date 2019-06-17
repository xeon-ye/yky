package com.deloitte.services.srpmp.relust.service.impl;

import java.time.LocalDateTime;
import java.util.*;

import com.deloitte.platform.api.srpmp.outline.vo.*;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.relust.vo.*;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectUpdateStatusEnums;
import com.deloitte.services.srpmp.common.enums.VoucherAuditModeEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISerialNoCenterService;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.outline.service.*;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectAttachmentService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.deloitte.services.srpmp.relust.entity.*;
import com.deloitte.services.srpmp.relust.mapper.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.bpmservice.feign.BpmOperatorFeignService;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessOperatorFormStart;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskFormStart;
import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.relust.param.SrpmsResultQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.common.constant.ProcessConstant;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.enums.VoucherStatusEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.project.base.entity.SrpmsVoucher;
import com.deloitte.services.srpmp.project.base.service.ISrpmsVoucherService;
import com.deloitte.services.srpmp.project.base.service.impl.SrpmsProjectBpmServiceImpl;
import com.deloitte.services.srpmp.relust.service.ISrpmsResultService;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-27
 * @Description : SrpmsResult服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsResultServiceImpl extends ServiceImpl<SrpmsResultMapper, SrpmsResult> implements ISrpmsResultService {

    @Autowired
    private SrpmsResultMapper srpmsResultMapper;

    @Autowired
    private ISrpmsVoucherService voucherService;

    @Autowired
    private BpmOperatorFeignService bpmOperatorFeignService;

    @Autowired
    private ISerialNoCenterService serialNoService;

    @Autowired
    private SrpmsProjectBpmServiceImpl bpmService;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private ISrpmsProjectAttachmentService attachmentService;

    @Autowired
    private SrpmsResultAcaExcMapper acaExcMapper;

    @Autowired
    private SrpmsResultAwardMapper awardMapper;

    @Autowired
    private SrpmsResultMedicalMapper medicalMapper;

    @Autowired
    private SrpmsResultPaperMapper paperMapper;

    @Autowired
    private SrpmsResultPatentMapper patentMapper;

    @Autowired
    private SrpmsResultSatBookMapper bookMapper;

    @Autowired
    private ISrpmsProjectFlowService flowService;

    @Override
    public JSONObject selectPage(SrpmsResultQueryForm queryForm, UserVo userVo, DeptVo dept) {

        String roleCode = userVo.getHonor();
        String deptCode = dept.getDeptCode();
        int tableFlg = queryForm.getTableFlag();

        if (tableFlg == SrpmsConstant.TABLE_FLAG_1) {
            queryForm.setCreateBy(userVo.getId());
        } else if (tableFlg == SrpmsConstant.TABLE_FLAG_2){
            if (queryForm.getSelfDataFlag() == 1) {// 此标识判断管理员是否查询自己的数据
                queryForm.setCreateBy(userVo.getId());
            }
            if (!SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
                queryForm.setDeptCode(deptCode);
            }
        } else {// 初始化权限显示table
            if (SrpmsConstant.SRPMS_ADMIN.equals(roleCode) || SrpmsConstant.SRPMS_LEADER.equals(roleCode)) {
                tableFlg = SrpmsConstant.TABLE_FLAG_2;
                if(org.apache.commons.lang3.StringUtils.isBlank(queryForm.getStatus())) {
                    queryForm.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_APPROVE_PASS.getCode());
                }
                if (!SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
                    queryForm.setDeptCode(deptCode);
                }
                if (queryForm.getSelfDataFlag() == 1) {// 此标识判断管理员是否查询自己的数据
                    queryForm.setCreateBy(userVo.getId());
                }
            } else {
                queryForm.setCreateBy(userVo.getId());
                tableFlg = SrpmsConstant.TABLE_FLAG_1;
                if(org.apache.commons.lang3.StringUtils.isBlank(queryForm.getStatus())) {
                    queryForm.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
                }
            }
        }

        queryForm.setTableFlag(tableFlg);
        QueryWrapper<SrpmsResult> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);

        IPage<SrpmsResult> page = srpmsResultMapper.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
        List<SrpmsResult> list = page.getRecords();
        List<SrpmsResultVo> voList = new ArrayList<>();

        Map<String, String> dictMap = sysDictService.getSysDictMap(new String[]{SrpmsConstant.RESULT_MANAGER_TYPE});

        if (list != null && list.size() > 0) {
            SrpmsResult result;
            SrpmsResultVo resultVo;
            for (Iterator e = list.iterator(); e.hasNext(); ) {
                result = (SrpmsResult) e.next();
                resultVo = new SrpmsResultVo();
                BeanUtils.copyProperties(result, resultVo);
                resultVo.setId(CommonUtil.objectToString(result.getId()));
                resultVo.setDetail(result.getDetail() == null ? new JSONObject() : JSONObject.parseObject(result.getDetail()));
                if (dictMap != null) {
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(result.getResultType())) {
                        resultVo.setResultTypeName(dictMap.get(SrpmsConstant.RESULT_MANAGER_TYPE.concat(result.getResultType())));
                    }
                }
                voList.add(resultVo);
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

    @Override
    public SrpmsResultVo queryById(Long id) {
        SrpmsResult srpmsResult = this.getById(id);
        if (srpmsResult == null) {
            throw new BaseException(SrpmsErrorType.RESULT_NO_DATA);
        }
        SrpmsResultVo resultVo = new SrpmsResultVo();
        BeanUtils.copyProperties(srpmsResult, resultVo);
        resultVo.setId(CommonUtil.objectToString(srpmsResult.getId()));
        resultVo.setDetail(srpmsResult.getDetail() == null ? new JSONObject() : JSONObject.parseObject(srpmsResult.getDetail()));

        Map<String, String> dictMap = sysDictService.getSysDictMap(new String[]{SrpmsConstant.RESULT_MANAGER_TYPE});
        if (dictMap != null) {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(srpmsResult.getResultType())) {
                resultVo.setResultTypeName(dictMap.get(SrpmsConstant.RESULT_MANAGER_TYPE.concat(srpmsResult.getResultType())));
            }
        }
        //查询审批历史记录
        if (!SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(srpmsResult.getStatus())) {
            SrpmsVoucher voucherEntity = voucherService.getSrpmsVoucherByUpdateId(id);
            if (voucherEntity != null) {
                resultVo.setApproveHistory(bpmService.queryAuditHistory(CommonUtil.objectToString(voucherEntity.getId())));
            }
        }

        // 相关附件
        resultVo.setAttachmentFile(attachmentService.queryAttachmentListAccept(id));

        return resultVo;
    }

    /**
     * 通用查询
     *
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<SrpmsResult> getQueryWrapper(QueryWrapper<SrpmsResult> queryWrapper,
                                                     SrpmsResultQueryForm queryForm) { // 条件拼接
        if (StringUtils.isNotBlank(queryForm.getResultNum())) {
            queryWrapper.like(SrpmsResult.RESULT_NUM, queryForm.getResultNum());
        }
        if (StringUtils.isNotBlank(queryForm.getResultName())) {
            queryWrapper.like(SrpmsResult.RESULT_NAME, queryForm.getResultName());
        }
        if (StringUtils.isNotBlank(queryForm.getResultType())) {
            queryWrapper.eq(SrpmsResult.RESULT_TYPE, queryForm.getResultType());
        }
        if (StringUtils.isNotBlank(queryForm.getTransFlag())) {
            if ("1".equals(queryForm.getTransFlag())) {
                queryWrapper.eq(SrpmsResult.TRANS_FLAG, queryForm.getTransFlag());
            } else {
                queryWrapper.isNull(SrpmsResult.TRANS_FLAG);
            }
        }
        if (StringUtils.isNotBlank(queryForm.getProjectNum())) {
            queryWrapper.like(SrpmsResult.PROJECT_NUM, queryForm.getProjectNum());
        }
        if (StringUtils.isNotBlank(queryForm.getProjectName())) {
            queryWrapper.like(SrpmsResult.PROJECT_NAME, queryForm.getProjectName());
        }
        if (StringUtils.isNotBlank(queryForm.getDeptCode())) {
            queryWrapper.eq(SrpmsResult.DEPT_CODE, queryForm.getDeptCode());
        }
        if (StringUtils.isNotBlank(queryForm.getDeptName())) {
            queryWrapper.eq(SrpmsResult.DEPT_NAME, queryForm.getDeptName());
        }
        if (StringUtils.isNotBlank(queryForm.getPersonName())) {
            queryWrapper.eq(SrpmsResult.PERSON_NAME, queryForm.getPersonName());
        }
        if (StringUtils.isNotBlank(queryForm.getStatus())) {
            if (!queryForm.getStatus().contains(",")) {
                queryWrapper.eq(SrpmsResult.STATUS, queryForm.getStatus());
            } else {
                String[] arr = queryForm.getStatus().split(",");
                queryWrapper.in(SrpmsResult.STATUS, arr);
            }
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(SrpmsResult.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(SrpmsResult.UPDATE_BY, queryForm.getUpdateBy());
        }
        queryWrapper.orderByDesc(SrpmsResult.CREATE_TIME);
        return queryWrapper;
    }

    @Override
    public String saveOrUpdate(SrpmsResultForm form, UserVo userVo, DeptVo deptVo) {
        Long id = form.getId();

        SrpmsResult entity = new SrpmsResult();
        BeanUtils.copyProperties(form, entity);
        if (id == null || id == 0L) {// 执行保存操作
            String year = DateFormatUtils.format(new Date(), "yyyy");
            String header = year.concat(entity.getResultType());
            String resultNum = serialNoService.getNextResultNo(header);

            entity.setDeptCode(deptVo.getDeptCode());
            entity.setDeptName(deptVo.getDeptName());
            entity.setStatus(SrpmsConstant.NOT_SUBMIT);
            entity.setResultNum(resultNum);
            entity.setCreateBy(userVo.getId());
            entity.setId(null);
            entity.setDetail(form.getDetail() == null ? "" : JSONObject.toJSONString(form.getDetail()));
            this.save(entity);

            id = entity.getId();

        } else {// 执行更新操作
            entity.setDeptCode(deptVo.getDeptCode());
            entity.setDeptName(deptVo.getDeptName());
            entity.setDetail(form.getDetail() == null ? "" : JSONObject.toJSONString(form.getDetail()));
            entity.setUpdateBy(userVo.getId());
            this.saveOrUpdate(entity);
        }

        // 附件
        if (form.getAttachmentFile() != null && form.getAttachmentFile().size() > 0) {
            attachmentService.saveAttachmentListAccept(form.getAttachmentFile(), id);
        }

        return id.toString();
    }

    @Override
    public void submit(SrpmsResultForm form, UserVo userVo, DeptVo deptVo) {
        String id = this.saveOrUpdate(form, userVo, deptVo);

        SrpmsResult srpmsResult = this.getById(id);
        if (srpmsResult == null) {
            throw new BaseException(SrpmsErrorType.RESULT_NO_DATA);
        }
        if (!SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(srpmsResult.getStatus())) {
            throw new BaseException(SrpmsErrorType.RESULT_HAVE_SUBMITTED);
        }
        String resultNum = srpmsResult.getResultNum();
        srpmsResult.setStatus(SrpmsConstant.HAS_SUBMIT);
        this.saveOrUpdate(srpmsResult);

        SrpmsVoucher voucherEntity = voucherService.getSrpmsVoucherByReject(srpmsResult.getId());// 查询项目是否进行驳回操作
        if (voucherEntity != null ) {
            flowService.againSubmit(voucherEntity, deptVo, false);
        } else {
            // 新建一个单据
            voucherEntity = new SrpmsVoucher();
            voucherEntity.setBizNumber(resultNum);
            String voucherType = "";
            switch (srpmsResult.getResultType()) {// 成果类型
                case SrpmsConstant.RESULT_PAPER:// 论文
                    voucherType = VoucherTypeEnums.RESULT_PAPER.getCode();
                    break;
                case SrpmsConstant.RESULT_PATENT_FIRST:// 专利
                case SrpmsConstant.RESULT_PATENT_SECOND:// 专利
                case SrpmsConstant.RESULT_PATENT_THREE:// 专利
                    voucherType = VoucherTypeEnums.RESULT_PATENT.getCode();
                    break;
                case SrpmsConstant.RESULT_BOOK_FIRST:// 著作
                case SrpmsConstant.RESULT_BOOK_SECOND:// 著作
                case SrpmsConstant.RESULT_BOOK_THREE:// 著作
                case SrpmsConstant.RESULT_BOOK_FOUR:// 著作
                case SrpmsConstant.RESULT_BOOK_FIVE:// 著作
                    voucherType = VoucherTypeEnums.RESULT_BOOK.getCode();
                    break;
                case SrpmsConstant.RESULT_ACA_EXC_FIRST:// 学术交流
                case SrpmsConstant.RESULT_ACA_EXC_SECOND:// 学术交流
                    voucherType = VoucherTypeEnums.RESULT_ACA_EXC.getCode();
                    break;
                case SrpmsConstant.RESULT_AWARD_FIRST:// 奖励
                case SrpmsConstant.RESULT_AWARD_SECOND:// 奖励
                case SrpmsConstant.RESULT_AWARD_THREE:// 奖励
                case SrpmsConstant.RESULT_AWARD_FOUR:// 奖励
                case SrpmsConstant.RESULT_AWARD_FIVE:// 奖励
                    voucherType = VoucherTypeEnums.RESULT_AWARD.getCode();
                    break;
                case SrpmsConstant.RESULT_MEDICAL_FIRST:// 新药器械
                case SrpmsConstant.RESULT_MEDICAL_SECOND:// 新药器械
                case SrpmsConstant.RESULT_MEDICAL_THREE:// 新药器械
                    voucherType = VoucherTypeEnums.RESULT_MEDICAL.getCode();
                    break;
                case SrpmsConstant.RESULT_APPLIANCE:// 医疗器械
                    voucherType = VoucherTypeEnums.RESULT_APPLIANCE.getCode();
                    break;
                case SrpmsConstant.RESULT_SOFTWARE:// 软件
                    voucherType = VoucherTypeEnums.RESULT_SOFTWARE.getCode();
                    break;
                default:
                    break;
            }
            voucherEntity.setVoucherName(voucherType);
            voucherEntity.setPersonName(userVo.getName());
            voucherEntity.setUserId(Long.parseLong(userVo.getId()));
            voucherEntity.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
            voucherEntity.setStartTime(LocalDateTime.now());
            voucherEntity.setVoucherType(voucherType);
            voucherEntity.setProjectId(srpmsResult.getId());
            //成果的承担单位CODE 就是当前登录用户的单位CODE
            voucherEntity.setLeadDeptCode(deptVo.getDeptCode());
            voucherEntity.setAuditMode(VoucherAuditModeEnums.SELF_ONLY.getCode());

            voucherService.save(voucherEntity);

            BpmProcessOperatorFormStart startForm = new BpmProcessOperatorFormStart();
//            String processKey = ProcessConstant.SRPMP_SHARING_GENERAL_PROCESS;
            String processKey = ProcessConstant.APPLY_AUDIT_PROCESS_SEC_NEW;
            BpmProcessTaskFormStart processConfig = new BpmProcessTaskFormStart();
            processConfig.setProcessDefineKey(processKey);
            processConfig.setSubmitterCode(String.valueOf(userVo.getId()));// 提交人账号
            processConfig.setSubmitterName(userVo.getName());// 提交人姓名
            processConfig.setSubmitterStation("PI");// 提交人岗位
            processConfig.setObjectId(String.valueOf(voucherEntity.getId()));// 审批对象ID
            processConfig.setObjectOrderNum(resultNum);// 审批对象业务单据编号
            processConfig.setObjectType(voucherEntity.getVoucherType());
            processConfig.setSourceSystem(ProcessConstant.BPM_CLIENT_NAME);
            ArrayList<NextNodeParamVo> nextNodeParamVo = bpmService.getNextNodeParamVo(voucherEntity,
                    deptVo.getDeptCode(), processKey, "start");

            // 流程变量
            Map<String, String> processVariables = new HashMap<>();

            startForm.setBpmProcessTaskForm(processConfig);
            startForm.setNextNodeParamVo(nextNodeParamVo);
            startForm.setProcessVariables(processVariables);
            log.info("[BPM]bpmOperatorFeignService.startProcess req:{}", JSONObject.toJSONString(startForm));
            Result result = bpmOperatorFeignService.startProcess(startForm);
            log.info("[BPM]bpmOperatorFeignService.startProcess rsp:{}", JSONObject.toJSONString(result));
        }
    }

    @Override
    public void approvePassBpm(TaskNodeActionVO actionVO, DeptVo deptVo) {
        boolean hasEnd = bpmService.auditApprove(actionVO, deptVo);
        SrpmsVoucher voucherEntity = voucherService.getById(actionVO.getObjectId());
        // 审批后更新单据撤回标识
        voucherEntity.setRecallFlag(1);

        if (!hasEnd) { // 未结束，就返回
            voucherService.saveOrUpdate(voucherEntity);
            return;
        }
        SrpmsResult result = this.getById(voucherEntity.getProjectId());// 查询成果管理信息
        if (result == null) {
            throw new BaseException(SrpmsErrorType.RESULT_NO_DATA);
        }
        if (!SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(result.getStatus())) {
            throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
        }

        result.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_APPROVE_PASS.getCode());// 设置状态
        // 更新状态
        this.updateById(result);
        // 更新单据状态
        voucherEntity.setVoucherStatus(VoucherStatusEnums.APPROVED.getCode());
        voucherService.saveOrUpdate(voucherEntity);

        // 审批通过更新相关成果数据信息，题录统计需要
        Map<String, Object> paramMap = new HashMap<>();
        LocalDateTime dateTime;
        String year = "";
        String month = "";
        switch (result.getResultType()) {// 成果类型
            case SrpmsConstant.RESULT_PAPER:// 论文
                SrpmsResultPaperVo paperVo = JSONObject.parseObject(result.getDetail(), SrpmsResultPaperVo.class);
                paramMap.put("orgCode", CommonUtil.getLongValue(result.getDeptCode()));
                paramMap.put("paperTitle", paperVo.getPaperTitle() == null ? "" : CommonUtil.deleteStringSpace(paperVo.getPaperTitle()));
                paramMap.put("journalTitle", paperVo.getJournalTitle() == null ? "" : CommonUtil.deleteStringSpace(paperVo.getJournalTitle()));
                if (paperMapper.getQueryCount(paramMap) > 0) {
                    break;
                }
                SrpmsResultPaper resultPaper = new SrpmsResultPaper();
                BeanUtils.copyProperties(paperVo, resultPaper);
                dateTime = paperVo.getPublishDate();
                if (dateTime != null) {
                    resultPaper.setYear(CommonUtil.objectToString(dateTime.getYear()));
                    resultPaper.setMonth(CommonUtil.objectToString(dateTime.getMonthValue()));
                }
                resultPaper.setDeptCode(CommonUtil.getLongValue(result.getDeptCode()));
                paperMapper.insert(resultPaper);
                break;
            case SrpmsConstant.RESULT_PATENT_FIRST:// 专利-实用新型
            case SrpmsConstant.RESULT_PATENT_SECOND:// 专利-发明
            case SrpmsConstant.RESULT_PATENT_THREE:// 专利-外观设计
                SrpmsResultPatentVo patentVo = JSONObject.parseObject(result.getDetail(), SrpmsResultPatentVo.class);
                dateTime = patentVo.getApplicationTime();
                if (dateTime != null) {
                    year = CommonUtil.objectToString(dateTime.getYear());
                    month = CommonUtil.objectToString(dateTime.getMonthValue());
                }
                paramMap.put("orgCode", CommonUtil.getLongValue(result.getDeptCode()));
                paramMap.put("year", year);
                paramMap.put("month", month);
                paramMap.put("authorizedFlag", patentVo.getAuthorizedFlag());
                paramMap.put("applicationNum", patentVo.getApplicationNum() == null ? "" : CommonUtil.deleteStringSpace(patentVo.getApplicationNum()));
                paramMap.put("patentNum", patentVo.getPatentNum() == null ? "" : CommonUtil.deleteStringSpace(patentVo.getPatentNum()));
                if (patentMapper.getQueryCount(paramMap) > 0) {
                    break;
                }
                SrpmsResultPatent resultPatent = new SrpmsResultPatent();
                BeanUtils.copyProperties(patentVo, resultPatent);
                resultPatent.setYear(year);
                resultPatent.setMonth(month);
                resultPatent.setDeptCode(CommonUtil.getLongValue(result.getDeptCode()));
                patentMapper.insert(resultPatent);
                break;
            case SrpmsConstant.RESULT_BOOK_FIRST:// 著作
            case SrpmsConstant.RESULT_BOOK_SECOND:// 著作
            case SrpmsConstant.RESULT_BOOK_THREE:// 著作
            case SrpmsConstant.RESULT_BOOK_FOUR:// 著作
            case SrpmsConstant.RESULT_BOOK_FIVE:// 著作
                SrpmsResultSatBookVo bookVo = JSONObject.parseObject(result.getDetail(), SrpmsResultSatBookVo.class);
                dateTime = bookVo.getPressTime();
                if (dateTime != null) {
                    year = CommonUtil.objectToString(dateTime.getYear());
                    month = CommonUtil.objectToString(dateTime.getMonthValue());
                }
                paramMap.put("orgCode", CommonUtil.getLongValue(result.getDeptCode()));
                paramMap.put("bookName", bookVo.getBookName() == null ? "" : CommonUtil.deleteStringSpace(bookVo.getBookName()));
                paramMap.put("press", bookVo.getPress() == null ? "" : CommonUtil.deleteStringSpace(bookVo.getPress()));
                if (bookMapper.getQueryCount(paramMap) > 0) {
                    break;
                }
                SrpmsResultSatBook book = new SrpmsResultSatBook();
                BeanUtils.copyProperties(bookVo, book);
                book.setYear(year);
                book.setMonth(month);
                book.setDeptCode(CommonUtil.getLongValue(result.getDeptCode()));
                bookMapper.insert(book);
                break;
            case SrpmsConstant.RESULT_ACA_EXC_FIRST:// 学术交流-国际会议
                SrpmsResultAcaExcVo acaExcVo = JSONObject.parseObject(result.getDetail(), SrpmsResultAcaExcVo.class);
                dateTime = acaExcVo.getHandleTime();
                if (dateTime != null) {
                    year = CommonUtil.objectToString(dateTime.getYear());
                    month = CommonUtil.objectToString(dateTime.getMonthValue());
                }
                paramMap.put("orgCode", CommonUtil.getLongValue(result.getDeptCode()));
                paramMap.put("year", year);
                paramMap.put("month", month);
                paramMap.put("teamName", acaExcVo.getTeamName() == null ? "" : CommonUtil.deleteStringSpace(acaExcVo.getTeamName()));
                if (acaExcMapper.getQueryCount(paramMap) > 0) {
                    break;
                }
                SrpmsResultAcaExc acaExc = new SrpmsResultAcaExc();
                BeanUtils.copyProperties(acaExcVo, acaExc);
                acaExc.setYear(year);
                acaExc.setMonth(month);
                acaExc.setRegion(SrpmsConstant.REGION_20);
                acaExc.setDeptCode(CommonUtil.getLongValue(result.getDeptCode()));
                acaExcMapper.insert(acaExc);
                break;
            case SrpmsConstant.RESULT_ACA_EXC_SECOND:// 学术交流-国内会议
                acaExcVo = JSONObject.parseObject(result.getDetail(), SrpmsResultAcaExcVo.class);
                dateTime = acaExcVo.getHandleTime();
                if (dateTime != null) {
                    year = CommonUtil.objectToString(dateTime.getYear());
                    month = CommonUtil.objectToString(dateTime.getMonthValue());
                }
                paramMap.put("orgCode", CommonUtil.getLongValue(result.getDeptCode()));
                paramMap.put("year", year);
                paramMap.put("month", month);
                paramMap.put("teamName", acaExcVo.getTeamName() == null ? "" : CommonUtil.deleteStringSpace(acaExcVo.getTeamName()));
                if (acaExcMapper.getQueryCount(paramMap) > 0) {
                    break;
                }
                acaExc = new SrpmsResultAcaExc();
                BeanUtils.copyProperties(acaExcVo, acaExc);
                acaExc.setYear(year);
                acaExc.setMonth(month);
                acaExc.setRegion(SrpmsConstant.REGION_10);
                acaExc.setDeptCode(CommonUtil.getLongValue(result.getDeptCode()));
                acaExcMapper.insert(acaExc);
                break;
            case SrpmsConstant.RESULT_AWARD_FIRST:// 奖励-国家级奖项
                SrpmsResultAwardVo awardVo = JSONObject.parseObject(result.getDetail(), SrpmsResultAwardVo.class);
                dateTime = awardVo.getApplyDate();
                if (dateTime != null) {
                    year = CommonUtil.objectToString(dateTime.getYear());
                    month = CommonUtil.objectToString(dateTime.getMonthValue());
                }
                paramMap.put("orgCode", CommonUtil.getLongValue(result.getDeptCode()));
                paramMap.put("year", year);
                paramMap.put("month", month);
                paramMap.put("awardName", awardVo.getAwardName() == null ? "" : CommonUtil.deleteStringSpace(awardVo.getAwardName()));
                if (awardMapper.getQueryCount(paramMap) > 0) {
                    break;
                }
                SrpmsResultAward award = new SrpmsResultAward();
                BeanUtils.copyProperties(awardVo, award);
                award.setYear(year);
                award.setMonth(month);
                award.setAwardCat(SrpmsConstant.RESULT_AWARD_5001);
                award.setDeptCode(CommonUtil.getLongValue(result.getDeptCode()));
                awardMapper.insert(award);
                break;
            case SrpmsConstant.RESULT_AWARD_SECOND:// 奖励-省部级奖项
                awardVo = JSONObject.parseObject(result.getDetail(), SrpmsResultAwardVo.class);
                dateTime = awardVo.getApplyDate();
                if (dateTime != null) {
                    year = CommonUtil.objectToString(dateTime.getYear());
                    month = CommonUtil.objectToString(dateTime.getMonthValue());
                }
                paramMap.put("orgCode", CommonUtil.getLongValue(result.getDeptCode()));
                paramMap.put("year", year);
                paramMap.put("month", month);
                paramMap.put("awardName", awardVo.getAwardName() == null ? "" : CommonUtil.deleteStringSpace(awardVo.getAwardName()));
                if (awardMapper.getQueryCount(paramMap) > 0) {
                    break;
                }
                award = new SrpmsResultAward();
                BeanUtils.copyProperties(awardVo, award);
                award.setYear(year);
                award.setMonth(month);
                award.setAwardCat(SrpmsConstant.RESULT_AWARD_5002);
                award.setDeptCode(CommonUtil.getLongValue(result.getDeptCode()));
                awardMapper.insert(award);
                break;
            case SrpmsConstant.RESULT_AWARD_THREE:// 奖励-高校科技奖项
                awardVo = JSONObject.parseObject(result.getDetail(), SrpmsResultAwardVo.class);
                dateTime = awardVo.getApplyDate();
                if (dateTime != null) {
                    year = CommonUtil.objectToString(dateTime.getYear());
                    month = CommonUtil.objectToString(dateTime.getMonthValue());
                }
                paramMap.put("orgCode", CommonUtil.getLongValue(result.getDeptCode()));
                paramMap.put("year", year);
                paramMap.put("month", month);
                paramMap.put("awardName", awardVo.getAwardName() == null ? "" : CommonUtil.deleteStringSpace(awardVo.getAwardName()));
                if (awardMapper.getQueryCount(paramMap) > 0) {
                    break;
                }
                award = new SrpmsResultAward();
                BeanUtils.copyProperties(awardVo, award);
                award.setYear(year);
                award.setMonth(month);
                award.setAwardCat(SrpmsConstant.RESULT_AWARD_5003);
                award.setDeptCode(CommonUtil.getLongValue(result.getDeptCode()));
                awardMapper.insert(award);
                break;
            case SrpmsConstant.RESULT_AWARD_FOUR:// 奖励-中华医学奖项
                awardVo = JSONObject.parseObject(result.getDetail(), SrpmsResultAwardVo.class);
                dateTime = awardVo.getApplyDate();
                if (dateTime != null) {
                    year = CommonUtil.objectToString(dateTime.getYear());
                    month = CommonUtil.objectToString(dateTime.getMonthValue());
                }
                paramMap.put("orgCode", CommonUtil.getLongValue(result.getDeptCode()));
                paramMap.put("year", year);
                paramMap.put("month", month);
                paramMap.put("awardName", awardVo.getAwardName() == null ? "" : CommonUtil.deleteStringSpace(awardVo.getAwardName()));
                if (awardMapper.getQueryCount(paramMap) > 0) {
                    break;
                }
                award = new SrpmsResultAward();
                BeanUtils.copyProperties(awardVo, award);
                award.setYear(year);
                award.setMonth(month);
                award.setAwardCat(SrpmsConstant.RESULT_AWARD_5004);
                award.setDeptCode(CommonUtil.getLongValue(result.getDeptCode()));
                awardMapper.insert(award);
                break;
            case SrpmsConstant.RESULT_AWARD_FIVE:// 奖励-其他社会奖项
                awardVo = JSONObject.parseObject(result.getDetail(), SrpmsResultAwardVo.class);
                dateTime = awardVo.getApplyDate();
                if (dateTime != null) {
                    year = CommonUtil.objectToString(dateTime.getYear());
                    month = CommonUtil.objectToString(dateTime.getMonthValue());
                }
                paramMap.put("orgCode", CommonUtil.getLongValue(result.getDeptCode()));
                paramMap.put("year", year);
                paramMap.put("month", month);
                paramMap.put("awardName", awardVo.getAwardName() == null ? "" : CommonUtil.deleteStringSpace(awardVo.getAwardName()));
                if (awardMapper.getQueryCount(paramMap) > 0) {
                    break;
                }
                award = new SrpmsResultAward();
                BeanUtils.copyProperties(awardVo, award);
                award.setYear(year);
                award.setMonth(month);
                award.setAwardCat(SrpmsConstant.RESULT_AWARD_5005);
                award.setDeptCode(CommonUtil.getLongValue(result.getDeptCode()));
                awardMapper.insert(award);
                break;
            case SrpmsConstant.RESULT_MEDICAL_FIRST:// 新药器械-西药
                SrpmsResultMedicalVo medicalVo = JSONObject.parseObject(result.getDetail(), SrpmsResultMedicalVo.class);
                dateTime = medicalVo.getApplyDate();
                if (dateTime != null) {
                    year = CommonUtil.objectToString(dateTime.getYear());
                    month = CommonUtil.objectToString(dateTime.getMonthValue());
                }
                paramMap.put("orgCode", CommonUtil.getLongValue(result.getDeptCode()));
                paramMap.put("medicalName", medicalVo.getMedicalName() == null ? "" : CommonUtil.deleteStringSpace(medicalVo.getMedicalName()));
                paramMap.put("medicalCertificateNum", medicalVo.getMedicalCertificateNum() == null ? "" : CommonUtil.deleteStringSpace(medicalVo.getMedicalCertificateNum()));
                if (medicalMapper.getQueryCount(paramMap) > 0) {
                    break;
                }
                SrpmsResultMedical medical = new SrpmsResultMedical();
                BeanUtils.copyProperties(medicalVo, medical);
                medical.setYear(year);
                medical.setMonth(month);
                medical.setMedicalCat(SrpmsConstant.MEDICAL_CAT_10);
                medical.setMedicalType(SrpmsConstant.MEDICAL_TYPE_10);
                medical.setDeptCode(CommonUtil.getLongValue(result.getDeptCode()));
                medicalMapper.insert(medical);
                break;
            case SrpmsConstant.RESULT_MEDICAL_SECOND:// 新药器械-中药
                medicalVo = JSONObject.parseObject(result.getDetail(), SrpmsResultMedicalVo.class);
                dateTime = medicalVo.getApplyDate();
                if (dateTime != null) {
                    year = CommonUtil.objectToString(dateTime.getYear());
                    month = CommonUtil.objectToString(dateTime.getMonthValue());
                }
                paramMap.put("orgCode", CommonUtil.getLongValue(result.getDeptCode()));
                paramMap.put("medicalName", medicalVo.getMedicalName() == null ? "" : CommonUtil.deleteStringSpace(medicalVo.getMedicalName()));
                paramMap.put("medicalCertificateNum", medicalVo.getMedicalCertificateNum() == null ? "" : CommonUtil.deleteStringSpace(medicalVo.getMedicalCertificateNum()));
                if (medicalMapper.getQueryCount(paramMap) > 0) {
                    break;
                }
                medical = new SrpmsResultMedical();
                BeanUtils.copyProperties(medicalVo, medical);
                medical.setYear(year);
                medical.setMonth(month);
                medical.setMedicalCat(SrpmsConstant.MEDICAL_CAT_10);
                medical.setMedicalType(SrpmsConstant.MEDICAL_TYPE_20);
                medical.setDeptCode(CommonUtil.getLongValue(result.getDeptCode()));
                medicalMapper.insert(medical);
                break;
            case SrpmsConstant.RESULT_MEDICAL_THREE:// 新药器械-生物药剂
                medicalVo = JSONObject.parseObject(result.getDetail(), SrpmsResultMedicalVo.class);
                dateTime = medicalVo.getApplyDate();
                if (dateTime != null) {
                    year = CommonUtil.objectToString(dateTime.getYear());
                    month = CommonUtil.objectToString(dateTime.getMonthValue());
                }
                paramMap.put("orgCode", CommonUtil.getLongValue(result.getDeptCode()));
                paramMap.put("medicalName", medicalVo.getMedicalName() == null ? "" : CommonUtil.deleteStringSpace(medicalVo.getMedicalName()));
                paramMap.put("medicalCertificateNum", medicalVo.getMedicalCertificateNum() == null ? "" : CommonUtil.deleteStringSpace(medicalVo.getMedicalCertificateNum()));
                if (medicalMapper.getQueryCount(paramMap) > 0) {
                    break;
                }
                medical = new SrpmsResultMedical();
                BeanUtils.copyProperties(medicalVo, medical);
                medical.setYear(year);
                medical.setMonth(month);
                medical.setMedicalCat(SrpmsConstant.MEDICAL_CAT_10);
                medical.setMedicalType(SrpmsConstant.MEDICAL_TYPE_30);
                medical.setDeptCode(CommonUtil.getLongValue(result.getDeptCode()));
                medicalMapper.insert(medical);
                break;
            case SrpmsConstant.RESULT_APPLIANCE:// 医疗器械
                medicalVo = JSONObject.parseObject(result.getDetail(), SrpmsResultMedicalVo.class);
                dateTime = medicalVo.getApplyDate();
                if (dateTime != null) {
                    year = CommonUtil.objectToString(dateTime.getYear());
                    month = CommonUtil.objectToString(dateTime.getMonthValue());
                }
                paramMap.put("orgCode", CommonUtil.getLongValue(result.getDeptCode()));
                paramMap.put("medicalName", medicalVo.getMedicalName() == null ? "" : CommonUtil.deleteStringSpace(medicalVo.getMedicalName()));
                paramMap.put("medicalCertificateNum", medicalVo.getMedicalCertificateNum() == null ? "" : CommonUtil.deleteStringSpace(medicalVo.getMedicalCertificateNum()));
                if (medicalMapper.getQueryCount(paramMap) > 0) {
                    break;
                }
                medical = new SrpmsResultMedical();
                BeanUtils.copyProperties(medicalVo, medical);
                medical.setYear(year);
                medical.setMonth(month);
                medical.setMedicalCat(SrpmsConstant.MEDICAL_CAT_20);
                medical.setDeptCode(CommonUtil.getLongValue(result.getDeptCode()));
                medicalMapper.insert(medical);
                break;
            case SrpmsConstant.RESULT_SOFTWARE:// 软件
                break;
            default:
                break;
        }
    }

    @Override
    public void refuseBpm(TaskNodeActionVO actionVO) {

        bpmService.auditRefuse(actionVO);
        SrpmsVoucher voucherEntity = voucherService.getById(actionVO.getObjectId());
        SrpmsResult result = this.getById(voucherEntity.getProjectId());// 查询成果管理信息
        if (result == null) {
            throw new BaseException(SrpmsErrorType.RESULT_NO_DATA);
        }
        if (!SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(result.getStatus())) {
            throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
        }
        result.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_APPROVE_NO.getCode());// 设置状态
        // 更新状态
        this.updateById(result);

        voucherEntity.setRecallFlag(1);
        // 更新单据状态
        voucherEntity.setVoucherStatus(VoucherStatusEnums.REFUSED.getCode());
        voucherService.saveOrUpdate(voucherEntity);
//		voucherService.updateStatus(CommonUtil.getLongValue(actionVO.getObjectId()), VoucherStatusEnums.REFUSED);
    }

    /**
     * 根据ID删除成果管理service接口实现
     *
     * @param id
     */
    @Override
    public void delete(long id) {

        SrpmsResult result = this.getById(id);// 查询成果管理信息
        if (result == null) {
            throw new BaseException(SrpmsErrorType.RESULT_NO_DATA);
        }
        if (!SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(result.getStatus())) {
            throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
        }

        this.removeById(id);
    }
}