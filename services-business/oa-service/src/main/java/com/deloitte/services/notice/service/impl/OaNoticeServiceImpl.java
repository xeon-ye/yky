package com.deloitte.services.notice.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.bpmservice.feign.BpmApprovalMatrixFeignService;
import com.deloitte.platform.api.bpmservice.feign.BpmOperatorFeignService;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryFormForApproval;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.vo.*;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.feign.OrganizationFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.DeputyAccountVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskForm;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentForm;
import com.deloitte.platform.api.oaservice.notice.param.OaNoticeQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeBpmProcessTaskForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeForm;
import com.deloitte.platform.api.portal.vo.NoticeForm;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.applycenter.service.ISendProcessTaskService;
import com.deloitte.services.attachment.entity.OaAttachment;
import com.deloitte.services.attachment.mapper.OaAttachmentMapper;
import com.deloitte.services.notice.biz.OaBpmEnum;
import com.deloitte.services.notice.biz.OaProcessStatusEnum;
import com.deloitte.services.notice.entity.*;
import com.deloitte.services.notice.mapper.*;
import com.deloitte.services.notice.service.IOaNoticeService;
import com.deloitte.services.noticeper.entity.OaNoticePermission;
import com.deloitte.services.noticeper.mapper.OaNoticePermissionMapper;
import com.deloitte.services.oa.exception.OaErrorType;
import com.deloitte.services.oa.util.OABPMConstantUtil;
import com.deloitte.services.rulesystem.entity.OaRuleSystem;
import com.deloitte.services.rulesystem.mapper.OaRuleSystemMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-15
 * @Description :  OaNotice服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaNoticeServiceImpl extends ServiceImpl<OaNoticeMapper, OaNotice> implements IOaNoticeService {

    private final String CHARGE_BUSINESS = "oaservice";

    private final String SOURCE_SYSTEM = "oaservice";

    private final String CHARGE_ORG = "-1";

    private final String PROCESS_DEFINE_KEY = "oa_notice_approval_process";

    // 处室负责人
    private final String TASKKEY_NOTICE_OWNERS = "oa_notice_owners";

    @Autowired
    private OaNoticeMapper oaNoticeMapper;

    @Autowired
    private OaAttachmentMapper attachmentMapper;

    @Autowired
    private BpmOperatorFeignService bpmOperatorFeignService;

    @Autowired
    private BpmApprovalMatrixFeignService bpmApprovalMatrixFeignService;

    @Autowired
    private BpmProcessTaskFeignService bpmProcessTaskFeignService;

    @Autowired
    private OrganizationFeignService organizationFeignService;

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private OaRuleSystemMapper oaRuleSystemMapper;

    @Autowired
    private OaNoticeOtherMapper oaNoticeOtherMapper;

    @Autowired
    private ISendProcessTaskService sendProcessTaskService;

    @Autowired
    private OaNoticePermissionMapper permissionMapper;

    @Override
    public boolean removeById(Serializable id) {
        boolean flag = super.removeById(id);
        permissionMapper.deletePermissionByObjId(String.valueOf(id));
        return flag;
    }

    @Override
    public OaNotice getById(Serializable id) {
        OaNotice oaNotice = super.getById(id);
        OaAttachment oaAttachment = new OaAttachment();
        oaAttachment.setBusicessId(String.valueOf(oaNotice.getId()));
        QueryWrapper<OaAttachment> queryWrapper = new QueryWrapper<>(oaAttachment);
        oaNotice.setAttachments(attachmentMapper.selectList(queryWrapper));

        List<OaNoticePermission> permissions = permissionMapper.getPermissionsByObjId(String.valueOf(oaNotice.getId()));
        oaNotice.setPermissionDepts(permissions);

        //更新点击率
        OaNotice newHit = new OaNotice();
        newHit.setId(oaNotice.getId());
        newHit.setNoticeHit(oaNotice.getNoticeHit() + 1);
        oaNoticeMapper.updateById(newHit);

        return oaNotice;
    }

    @Override
    public Result save(OaNoticeForm entity) {
        OaNotice oaNotice =new BeanUtils<OaNotice>().copyObj(entity, OaNotice.class);
        boolean flag = super.save(oaNotice);
        for(OaAttachmentForm atta : entity.getAttachments()){
            //atta.setBusicessId(String.valueOf(oaNoticeAdd.getId())); // 设置新增的附件业务id
            atta.setBusicessId(String.valueOf(oaNotice.getId()));
            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(atta, OaAttachment.class);
            attachmentMapper.insert(temp); // 持久化业务附件
        }

        // 存储部门节点
        entity.getPermissionDepts().forEach(dept -> {
            dept.setObjectId(oaNotice.getId());
            OaNoticePermission temp = new BeanUtils<OaNoticePermission>().copyObj(dept, OaNoticePermission.class);
            permissionMapper.insert(temp);
        });

        if(flag){
            return Result.success(String.valueOf(oaNotice.getId()));
        }else {
            return Result.fail("新增数据失败");
        }
    }

    @Override
    public Result update(long id, OaNoticeForm entity) {
        OaNotice oaNotice =new BeanUtils<OaNotice>().copyObj(entity, OaNotice.class);
        oaNotice.setId(id);
        boolean flag = super.updateById(oaNotice);
        attachmentMapper.deleteByBusinessId(String.valueOf(id));
        entity.getAttachments().forEach(atta -> {
            atta.setBusicessId(String.valueOf(id));
            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(atta, OaAttachment.class);
            attachmentMapper.insert(temp);
        });

        permissionMapper.deletePermissionByObjId(String.valueOf(id));
        if("inter".equals(entity.getDeptPer())) {
            entity.getPermissionDepts().forEach(dept -> {
                dept.setObjectId(id);
                OaNoticePermission temp = new BeanUtils<OaNoticePermission>().copyObj(dept, OaNoticePermission.class);
                permissionMapper.insert(temp);
            });
        }

        return Result.success(flag);
    }

    @Override
    public Result submitStart(OaNoticeForm oaNoticeForm, String token) {
        Result result = null;
        String taskId = oaNoticeForm.getApprovalParams().get("taskID");
        if (null != taskId && taskId.equals("start")){ //开始流程
            // 1. 查询下一节点负责人
            String approvalType = oaNoticeForm.getApprovalParams().get("approvalType");
            ArrayList<NextNodeParamVo> nextMatrixs = null;
            if (null != oaNoticeForm.getNextNodeParamVos() && oaNoticeForm.getNextNodeParamVos().size() > 0) {
                nextMatrixs = (ArrayList<NextNodeParamVo>) oaNoticeForm.getNextNodeParamVos();
            } else {
                nextMatrixs = this.getNextNodeParamVo(taskId, oaNoticeForm.getApprovalParams(), approvalType, true);
            }
            BpmProcessOperatorFormStart startParms = new BpmProcessOperatorFormStart();
            startParms.setNextNodeParamVo(nextMatrixs);
            Map<String,String> processVariable = oaNoticeForm.getApprovalParams();
            UserVo userVo = UserUtil.getUserVo();
            if(null != userVo){ // 设置虚拟组织
                processVariable.put("CURRENT_ORG", userVo.getCurrentDeputyAccount().getOrgCode());
            } else {
                throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取当前登录用户信息失败！");
            }

            startParms.setProcessVariables(processVariable);
            String orderNum = this.getOrderNum();
            String objId = oaNoticeForm.getApprovalParams().get("id");
            BpmProcessTaskFormStart startForm = new BpmProcessTaskFormStart();
            startForm.setObjectId(objId);
            startForm.setObjectDescription(oaNoticeForm.getApprovalParams().get("title"));
            startForm.setProcessDefineKey(this.PROCESS_DEFINE_KEY);
            // startForm.setSourceSystem(this.CHARGE_BUSINESS);
            startForm.setSourceSystem(this.SOURCE_SYSTEM);
            startForm.setSubmitterCode(userVo.getId());
            startForm.setSubmitterName(userVo.getName());
            startForm.setSubmitterOrg(userVo.getDept());
            String origin = oaNoticeForm.getApprovalParams().get("origin");
            String url = (null != origin ? origin + "#/" : "") + OaBpmEnum.getUrl(oaNoticeForm.getApprovalParams().get("approvalType"));
            startForm.setObjectUrl(url);
            //startForm.setObjectDescription(); 描述
            startForm.setObjectOrderNum(orderNum);
            startForm.setObjectType(oaNoticeForm.getApprovalParams().get("objectType"));
            // url
            //startForm.setSubmitterStation();

            startParms.setBpmProcessTaskForm(startForm);
            try {
                result = bpmOperatorFeignService.startProcess(startParms);
                //更新状态
                this.updateOrderNumAndStatus(objId, orderNum, OaProcessStatusEnum.OA_PS_NOTREAD.type, approvalType);
            } catch (Exception e){
                result = Result.fail("提交流程失败!");
                e.printStackTrace();
            }
        } else { //流程处理
            result = Result.fail("请提交开始请求节点");
        }

        return result;
    }

    @Override
    public Result submit(OaNoticeBpmProcessTaskForm bpmProcessTaskForm) {
        Result result = null;
        BpmProcessOperatorApprove operatorApprove = new BpmProcessOperatorApprove();
        String approvalType = bpmProcessTaskForm.getTaskParams().get("approvalType");

        ArrayList<NextNodeParamVo> nextNodes = null;

        // 同意审批
        // 寻找下个个节点流程人员
        if (this.isNextNodeIsOwners(bpmProcessTaskForm.getTaskParams().get("taskId"), bpmProcessTaskForm.getTaskParams())){
            BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
            bpmProcessTaskQueryForm.setId(bpmProcessTaskForm.getId());
            bpmProcessTaskQueryForm.setProcessDefineKey(this.PROCESS_DEFINE_KEY);
            Result<List<BpmProcessTaskVo>> re = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
            if(re.isSuccess() && re.getData() != null && re.getData().size() > 0){
                BpmProcessTaskVo vo = re.getData().get(0);
                String orgCode = vo.getSubmitterOrg();
                Result<OrganizationVo> byCode = organizationFeignService.getByCode(orgCode);
                if (byCode.isSuccess()) {
                    //处室负责人的empNo
                    String dutyPerson = byCode.getData().getDutyperson();
                    Result byEmpNo = userFeignService.getByEmpNo(dutyPerson);
                    Object data = byEmpNo.getData();
                    if (data != null) {
                        UserVo userInfo = JSON.parseObject(JSON.toJSONString(data), UserVo.class);
                        String id = userInfo.getId();
                        String name = userInfo.getName();
                        NextNodeParamVo nextNodeParamVo = new NextNodeParamVo();
                        nextNodeParamVo.setAcountId(id);
                        nextNodeParamVo.setAcountName(name);
                        String url = (null != bpmProcessTaskForm.getTaskParams().get("origin") ? bpmProcessTaskForm.getTaskParams().get("origin") + "#/" : "")
                                + OaBpmEnum.getUrl(approvalType);
                        nextNodeParamVo.setObjectUrl(url);

                        nextNodes = new ArrayList<>();
                        nextNodes.add(nextNodeParamVo);
                    } else {
                        throw new ServiceException(OaErrorType.BPM_NEXT_APPROVER_ERROR);
                    }
                }
            } else {
                throw new ServiceException(OaErrorType.BPM_NEXT_APPROVER_ERROR, re.getMesg());
            }
        } else if (null != bpmProcessTaskForm.getNextNodeParamVos() && bpmProcessTaskForm.getNextNodeParamVos().size() > 0){
            nextNodes = (ArrayList<NextNodeParamVo>) bpmProcessTaskForm.getNextNodeParamVos();
        } else {
            nextNodes = this.getNextNodeParamVo(bpmProcessTaskForm.getTaskParams().get("taskId"),
                    bpmProcessTaskForm.getTaskParams(), approvalType, false);
        }
        operatorApprove.setProcessVariables(bpmProcessTaskForm.getTaskParams());
        operatorApprove.setNextNodeParamVo(nextNodes);
        operatorApprove.setBpmProcessTaskForm(bpmProcessTaskForm);

        result = bpmOperatorFeignService.approveProcess(operatorApprove);
        // 判断最后一个节点
        if(result.isSuccess() && null == nextNodes.get(0).getAcountId()) { // 已经进入最后一个节点，更新状态
            //String id, String orderNum, String approvalStatus, String approvalType
            this.updateOrderNumAndStatus(bpmProcessTaskForm.getTaskParams().get("id"),
                    null, OaProcessStatusEnum.OA_PS_NORMAL.type, approvalType);
            // 抄送数据给发起人
            String url = (null != bpmProcessTaskForm.getTaskParams().get("origin") ? bpmProcessTaskForm.getTaskParams().get("origin") + "#/" : "");
            this.cc2Submitter(bpmProcessTaskForm.getTaskParams().get("taskId"), url, approvalType);
        }

        return result;
    }

    @Override
    public Result reSubmit(OaNoticeBpmProcessTaskForm bpmProcessTaskForm) {
        Result result = null;
        BpmProcessOperatorApprove operatorApprove = new BpmProcessOperatorApprove();
        String approvalType = bpmProcessTaskForm.getTaskParams().get("approvalType");

        ArrayList<NextNodeParamVo> nextNodes = null;

        // 同意审批
        // 寻找下个个节点流程人员
        if ("0".equals(bpmProcessTaskForm.getTaskParams().get("needBusiness"))){
            BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
            bpmProcessTaskQueryForm.setId(bpmProcessTaskForm.getId());
            bpmProcessTaskQueryForm.setProcessDefineKey(this.PROCESS_DEFINE_KEY);
            Result<List<BpmProcessTaskVo>> re = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
            if(re.isSuccess() && re.getData() != null && re.getData().size() > 0){
                BpmProcessTaskVo vo = re.getData().get(0);
                String orgCode = vo.getSubmitterOrg();
                Result<OrganizationVo> byCode = organizationFeignService.getByCode(orgCode);
                if (byCode.isSuccess()) {
                    //处室负责人的empNo
                    String dutyPerson = byCode.getData().getDutyperson();
                    Result byEmpNo = userFeignService.getByEmpNo(dutyPerson);
                    Object data = byEmpNo.getData();
                    if (data != null) {
                        UserVo userInfo = JSON.parseObject(JSON.toJSONString(data), UserVo.class);
                        String id = userInfo.getId();
                        String name = userInfo.getName();
                        NextNodeParamVo nextNodeParamVo = new NextNodeParamVo();
                        nextNodeParamVo.setAcountId(id);
                        nextNodeParamVo.setAcountName(name);
                        String url = (null != bpmProcessTaskForm.getTaskParams().get("origin") ? bpmProcessTaskForm.getTaskParams().get("origin") + "#/" : "")
                                + OaBpmEnum.getUrl(approvalType);
                        nextNodeParamVo.setObjectUrl(url);

                        nextNodes = new ArrayList<>();
                        nextNodes.add(nextNodeParamVo);
                    } else {
                        throw new ServiceException(OaErrorType.BPM_NEXT_APPROVER_ERROR);
                    }
                }
            } else {
                throw new ServiceException(OaErrorType.BPM_NEXT_APPROVER_ERROR, re.getMesg());
            }
        } else if (null != bpmProcessTaskForm.getNextNodeParamVos() && bpmProcessTaskForm.getNextNodeParamVos().size() > 0){
            nextNodes = (ArrayList<NextNodeParamVo>) bpmProcessTaskForm.getNextNodeParamVos();
        } else {
            nextNodes = this.getNextNodeParamVo(bpmProcessTaskForm.getTaskParams().get("taskId"),
                    bpmProcessTaskForm.getTaskParams(), approvalType, false);
        }

        operatorApprove.setProcessVariables(bpmProcessTaskForm.getTaskParams());
        operatorApprove.setNextNodeParamVo(nextNodes);
        operatorApprove.setBpmProcessTaskForm(bpmProcessTaskForm);

        result = bpmOperatorFeignService.approveProcess(operatorApprove);
        this.updateOrderNumAndStatus(bpmProcessTaskForm.getTaskParams().get("id"),
                null, OaProcessStatusEnum.OA_PS_NOTREAD.type, approvalType);

        return result;
    }

    @Override
    public Result reply(OaNoticeBpmProcessTaskForm bpmProcessTaskForm) {
        Result result = null;
        BpmProcessOperatorApprove operatorApprove = new BpmProcessOperatorApprove();
        String approvalType = bpmProcessTaskForm.getTaskParams().get("approvalType");
//        if(OABPMConstantUtil.OPTION_TYPE_REJECT.equals(bpmProcessTaskForm.getOpinion())){
            // 拒绝审批, 跳转到第一个节点，即开始节点
        BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
        bpmProcessTaskQueryForm.setId(bpmProcessTaskForm.getId());
        bpmProcessTaskQueryForm.setProcessDefineKey(this.PROCESS_DEFINE_KEY);
        Result<List<BpmProcessTaskVo>> re = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
        if(re.isSuccess() && re.getData() != null && re.getData().size() > 0){
            BpmProcessTaskVo vo = re.getData().get(0);
            ArrayList<NextNodeParamVo> nextList = new ArrayList<>();
            NextNodeParamVo firstNodeParamVo = new NextNodeParamVo();

            firstNodeParamVo.setAcountId(vo.getSubmitterCode());
            firstNodeParamVo.setAcountName(vo.getSubmitterName());
            firstNodeParamVo.setOrgId(vo.getSubmitterOrg());
            String url = (null != bpmProcessTaskForm.getTaskParams().get("origin") ? bpmProcessTaskForm.getTaskParams().get("origin") + "#/" : "") + OaBpmEnum.getUrl(bpmProcessTaskForm.getTaskParams().get("approvalType"));
            firstNodeParamVo.setObjectUrl(url);
            nextList.add(firstNodeParamVo);
            operatorApprove.setNextNodeParamVo(nextList);
            operatorApprove.setBpmProcessTaskForm(bpmProcessTaskForm);
            operatorApprove.setProcessVariables(bpmProcessTaskForm.getTaskParams());
            result = bpmOperatorFeignService.rejectToFirstProcess(operatorApprove);
            // 更新状态为草稿状态
            this.updateOrderNumAndStatus(bpmProcessTaskForm.getTaskParams().get("id"), null, OaProcessStatusEnum.OA_PS_DRAFT.type, approvalType);

            return result;
        } else {
            throw new ServiceException(OaErrorType.BPM_NEXT_APPROVER_ERROR, re.getMesg());
        }
//        }
//        throw new ServiceException(OaErrorType.SAVE_ERROR, "审批意见出错");
    }

    /**
     * 更新流水号
     */
    private void updateOrderNumAndStatus(String id, String orderNum, String approvalStatus, String approvalType){
        if (approvalType.equals(OaBpmEnum.OA_NOTICE_APPROVAL_URL.getType()) || approvalType.equals(OaBpmEnum.OA_NOTICE_APPROVAL_START_URL.getType())){
            // 通知公告
            OaNotice oaNotice = new OaNotice();
            oaNotice.setId(Long.valueOf(id));
            oaNotice.setOrderNum(orderNum);
            oaNotice.setApprovalStatus(approvalStatus);
            oaNoticeMapper.updateById(oaNotice);
        } else if (approvalType.equals(OaBpmEnum.OA_RULESYSTEM_APPROVAL_START_URL.getType()) || approvalType.equals(OaBpmEnum.OA_RULESYSTEM_APPROVAL_URL.getType())){
            // 规章制度流程
            OaRuleSystem oaRuleSystem = new OaRuleSystem();
            oaRuleSystem.setId(Long.valueOf(id));
            oaRuleSystem.setOrderNum(orderNum);
            oaRuleSystem.setApprovalStatus(approvalStatus);
            oaRuleSystemMapper.updateById(oaRuleSystem);
        } else {
            OaNoticeOther oaNoticeOther = new OaNoticeOther();
            oaNoticeOther.setId(Long.valueOf(id));
            oaNoticeOther.setOrderNum(orderNum);
            oaNoticeOther.setApprovalStatus(approvalStatus);
            oaNoticeOtherMapper.updateById(oaNoticeOther);
        }
    }

    private String getOrderNum() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String date = sdf.format(new Date());
        // 规章制度流程
        String head = "DZGG" + date + "%";
        String num = "000" + (Integer.valueOf(oaNoticeMapper.getOrderNum(head)) + 1);
        return "DZGG" + date + num.substring(num.length() - 4);
    }

    @Override
    public Result<List<NextNodeParamVo>> getNextNodeParamVos(Map<String, Object> map){
        if(null == map.get("taskId") || null == map.get("processVariables") || null == map.get("url")) {
            throw new ServiceException(OaErrorType.BPM_NEXT_APPROVER_ERROR, "输入参数不完整");
        }
        String taskId = String.valueOf(map.get("taskId"));
        Map<String, String> processVariables = (Map<String, String>) map.get("processVariables");
        String url = String.valueOf(map.get("url"));
        List<NextNodeParamVo> list = this.getNextNodeParamVos(taskId, processVariables, url);
        return Result.success(list);
    }

    private ArrayList<NextNodeParamVo> getNextNodeParamVo(String taskId, Map<String, String> processVariables, String approvalType, boolean isStart){
        String url = OaBpmEnum.getUrl(approvalType);
        if(isStart && "0".equals(processVariables.get("needBusiness"))) {
            // 判断下一个节点是否为处室负责人，是则寻找处室负责人审批
            ArrayList<NextNodeParamVo> nextNodeParamVos = this.getOwners(processVariables, url);
            if (nextNodeParamVos != null) {
                return nextNodeParamVos;
            }
        }
        return this.getNextNodeParamVos(taskId, processVariables, url);
    }

    private ArrayList<NextNodeParamVo> getNextNodeParamVos(String taskId, Map<String, String> processVariables, String url){
        ArrayList<NextNodeParamVo> nextList = null;
        BpmApprovalMatrixQueryFormForApproval nextMatrixForm = new BpmApprovalMatrixQueryFormForApproval();
        nextMatrixForm.setTaskID(taskId);
        nextMatrixForm.setChargeBusiness(this.CHARGE_BUSINESS);
        UserVo userVo = UserUtil.getUserVo();
        String chargeOrg = null;
        if (null != userVo) {
            chargeOrg = userVo.getCurrentDeputyAccount().getOrgCode();
        }
        nextMatrixForm.setChargeOrg(null != chargeOrg ? chargeOrg : this.CHARGE_ORG);
        nextMatrixForm.setProcessDefineKey(this.PROCESS_DEFINE_KEY);
        nextMatrixForm.setProcessVariables(processVariables);
        //nextMatrixForm.setCurrentPage(0);
        //nextMatrixForm.setPageSize(10);
        Result<List<BpmApprovalMatrixVo>> result = bpmApprovalMatrixFeignService.findNextApprover(nextMatrixForm);
        if (result != null && result.isSuccess() && result.getData() != null){
            nextList = new ArrayList<>();
            List<BpmApprovalMatrixVo> bpmMatrixVos =  result.getData();
            if (bpmMatrixVos.size() == 0) {
                nextList.add(new NextNodeParamVo());
                return nextList;
            }
            for(BpmApprovalMatrixVo matrixVo : bpmMatrixVos) {
                NextNodeParamVo nodeParamVo = new NextNodeParamVo();
                nodeParamVo.setAcountId(matrixVo.getAccountNum());
                nodeParamVo.setAcountName(matrixVo.getAccountName());
                nodeParamVo.setOrgId(matrixVo.getOrgCode());
                nodeParamVo.setStationId(matrixVo.getPosition());
                // 添加origin
                url = (null != processVariables.get("origin") ? processVariables.get("origin") + "#/" : "") + url;
                nodeParamVo.setObjectUrl(url); // 设置审批路径URL
                nextList.add(nodeParamVo);
            }
        }else{
            //抛异常
            throw new ServiceException(OaErrorType.BPM_NEXT_APPROVER_ERROR,result.getMesg());
        }
        return nextList;
    }

    /**
     * 判断下一个节点是否为处室负责人
     */
    private boolean isNextNodeIsOwners (String taskId, Map<String, String> processVariables) {
        boolean re = false;
        BpmTaskNextNodeForm bpmTaskNextNodeForm = new BpmTaskNextNodeForm();
        bpmTaskNextNodeForm.setTaskId(taskId);
        bpmTaskNextNodeForm.setProcessVariables(processVariables);
        Result result = bpmOperatorFeignService.findNextNodeList(bpmTaskNextNodeForm);
        if (result.isSuccess()) {
            List<Map<String, String>> list = (List<Map<String, String>>) result.getData();
            if (list.size() > 0) {
                String taskKey = list.get(0).get("taskKey");
                if (taskKey.equals(TASKKEY_NOTICE_OWNERS)) {
                    re = true;
                }
            }
        }

        return re;
    }

    private ArrayList<NextNodeParamVo> getOwners(Map<String, String> processVariables, String url) {
        UserVo userVo = UserUtil.getUserVo();
        String orgCode;
        if (userVo != null) {
            DeputyAccountVo account = userVo.getCurrentDeputyAccount();
            if (account != null) {
                orgCode = account.getOrgCode();
            } else {
                throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取用户部门信息失败 !");
            }
        } else {
            throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取用户部门信息失败 !");
        }
        ArrayList<NextNodeParamVo> nextNodeParamVos = null;
        NextNodeParamVo nextNodeParamVo = null;
        Result<OrganizationVo> byCode = organizationFeignService.getByCode(orgCode);
        if (byCode.isSuccess()) {
            //处室负责人的empNo
            String dutyPerson = byCode.getData().getDutyperson();
            Result byEmpNo = userFeignService.getByEmpNo(dutyPerson);
            Object data = byEmpNo.getData();
            if (data != null) {
                UserVo userInfo = JSON.parseObject(JSON.toJSONString(data), UserVo.class);
                String id = userInfo.getId();
                String name = userInfo.getName();
                nextNodeParamVos = new ArrayList<>();
                nextNodeParamVo = new NextNodeParamVo();
                nextNodeParamVo.setAcountId(id);
                nextNodeParamVo.setAcountName(name);
                url = url = (null != processVariables.get("origin") ? processVariables.get("origin") + "#/" : "") + url;
                nextNodeParamVo.setObjectUrl(url);
                nextNodeParamVos.add(nextNodeParamVo);
            } else {
                throw new ServiceException(OaErrorType.BPM_NEXT_APPROVER_ERROR);
            }
        }
        return nextNodeParamVos;
    }

    /**
     * 抄送
     * 当审批完成时，将审批抄送给发起人
     */
    private void cc2Submitter(String taskId, String url ,String approvalType){
        BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
        bpmProcessTaskQueryForm.setTaskId(taskId);
        Result<List<BpmProcessTaskVo>> result = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
        if(result.isSuccess() && null != result.getData() && result.getData().size() > 0) {
            BpmProcessTaskVo bpmProcessTaskVo = result.getData().get(0);
            SendProcessTaskForm sendProcessTaskForm = new BeanUtils<SendProcessTaskForm>().copyObj(bpmProcessTaskVo, SendProcessTaskForm.class);
            sendProcessTaskForm.setApproverAcount(sendProcessTaskForm.getSubmitterCode());
            sendProcessTaskForm.setApproverName(sendProcessTaskForm.getSubmitterName());
            sendProcessTaskForm.setApproverOrg(sendProcessTaskForm.getSubmitterOrg());
            sendProcessTaskForm.setApproverStation(sendProcessTaskForm.getApproverStation());
            sendProcessTaskForm.setTaskStauts("待阅");
            if (approvalType.equals(OaBpmEnum.OA_NOTICE_APPROVAL_URL.getType()) || approvalType.equals(OaBpmEnum.OA_NOTICE_APPROVAL_START_URL.getType())){
                url = url + OaBpmEnum.OA_NOTICE_APPROVAL_START_URL.getUrl();
            } else if (approvalType.equals(OaBpmEnum.OA_RULESYSTEM_APPROVAL_START_URL.getType()) || approvalType.equals(OaBpmEnum.OA_RULESYSTEM_APPROVAL_URL.getType())){
                url = url + OaBpmEnum.OA_RULESYSTEM_APPROVAL_START_URL.getUrl();
            } else if (approvalType.equals(OaBpmEnum.OA_CALENDAR_APPROVAL_START_URL.getType()) || approvalType.equals(OaBpmEnum.OA_CALENDAR_APPROVAL_URL.getType())) {
                url = url + OaBpmEnum.OA_CALENDAR_APPROVAL_START_URL.getUrl();
            } else if (approvalType.equals(OaBpmEnum.OA_INFOCHANGE_APPROVAL_URL.getType()) || approvalType.equals(OaBpmEnum.OA_INFOCHANGE_APPROVAL_START_URL.getType())) {
                url = url + OaBpmEnum.OA_INFOCHANGE_APPROVAL_START_URL.getUrl();
            } else if (approvalType.equals(OaBpmEnum.OA_MEETINGARRGE_APPROVAL_START_URL.getType()) || approvalType.equals(OaBpmEnum.OA_MEETINGARRGE_APPROVAL_URL.getType())) {
                url = url + OaBpmEnum.OA_MEETINGARRGE_APPROVAL_START_URL.getUrl();
            } else if (approvalType.equals(OaBpmEnum.OA_MEETING_APPROVAL_START_URL.getType()) || approvalType.equals(OaBpmEnum.OA_MEETING_APPROVAL_URL.getType())) {
                url = url + OaBpmEnum.OA_MEETING_APPROVAL_START_URL.getUrl();
            } else if (approvalType.equals(OaBpmEnum.OA_RESOURCE_APPROVAL_START_URL.getType()) || approvalType.equals(OaBpmEnum.OA_RESOURCE_APPROVAL_URL)) {
                url = url + OaBpmEnum.OA_RESOURCE_APPROVAL_START_URL.getUrl();
            }
            url = url + "?isCc=true";

            sendProcessTaskForm.setObjectUrl(url);
            sendProcessTaskService.save(sendProcessTaskForm);
        } else {
            throw new ServiceException(OaErrorType.BPM_TASKINFO_ERROR, result.getMesg());
        }
    }

    @Override
    public IPage<OaNotice> selectPage(OaNoticeQueryForm queryForm, String token) {
        IPage<OaNotice> pages = null;
        QueryWrapper<OaNotice> queryWrapper = new QueryWrapper <OaNotice>();
        String orgCode = queryForm.getApplyOrgCode();
        queryForm.setApplyDeptCode(null);
        queryForm.setApplyOrgCode(null);
        // 设置审批状态为 'normal' 可见
        queryForm.setApprovalStatus(OaProcessStatusEnum.OA_PS_NORMAL.type);
        getQueryWrapper(queryWrapper, queryForm);
        // 如果是院校公告，全部可见
        if(null != queryForm.getNoticeTypeCode() && !queryForm.getNoticeTypeCode().equals("oa_notice_type_academy")) {
            UserVo userVo = UserUtil.getUserVo();
            if (null != userVo) {
                String dept = userVo.getDept();
                String curOrgCode = userVo.getCurrentDeputyAccount().getOrgCode();
                String userId = userVo.getId();
                String typeCode = queryForm.getNoticeTypeCode();
                String sortCode = queryForm.getNoticeSortCode();
                // 判断当前登录用户是不是外部人员
                DeptVo deptVo = UserUtil.getDept();
                if(2 == deptVo.getGroupType()) { // 外部人员
                    queryWrapper.eq(OaNotice.DEPT_PER, "outer");
                    queryWrapper.eq(OaNotice.APPROVAL_STATUS, OaProcessStatusEnum.OA_PS_NORMAL);
                    pages = oaNoticeMapper.selectPage(new Page<OaNotice>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
                } else {
                    pages = oaNoticeMapper.getOrgPerList(new Page<OaNotice>(queryForm.getCurrentPage(),queryForm.getPageSize()),
                            curOrgCode, dept, orgCode, userId, typeCode, sortCode);
                }
            } else {
                throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取当前登录用户信息失败！");
            }
        } else {
            if(orgCode != null && !"".equals(orgCode)) { // 寻找该部门和子级部门数据
                queryWrapper.likeRight(OaNotice.APPLY_ORG_CODE, orgCode);
            }
            pages = oaNoticeMapper.selectPage(new Page<OaNotice>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
        }

        return pages;
    }

    @Override
    public IPage<OaNotice> selectPageWithPermission(OaNoticeQueryForm queryForm, String token) {
        QueryWrapper<OaNotice> queryWrapper = new QueryWrapper <OaNotice>();
        String applyOrgName = queryForm.getApplyOrgName();
        queryForm.setApplyOrgName(null);
        queryForm.setApplyOrgCode(null);
        getQueryWrapper(queryWrapper,queryForm);
        queryWrapper.like(OaNotice.APPLY_ORG_NAME, applyOrgName);
        UserVo userVo = UserUtil.getUserVo();
        if(null != userVo){
            DeputyAccountVo accountVo = userVo.getCurrentDeputyAccount();
            if(null != accountVo){
                String orgCode = accountVo.getOrgCode();
                String userId = String.valueOf(accountVo.getId());
                queryWrapper.apply(" ( APPLY_USER_ID = {0} OR ( APPLY_ORG_CODE like {1} and APPLY_ORG_CODE <> {2} ) )",
                        userId, orgCode + "%", orgCode);
            } else {
                throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取用户部门信息失败 !");
            }
        } else {
            throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取当前登录用户信息失败！");
        }

        return oaNoticeMapper.selectPage(new Page<OaNotice>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
    }

    @Override
    public List<OaNotice> selectList(OaNoticeQueryForm queryForm) {
        QueryWrapper<OaNotice> queryWrapper = new QueryWrapper <OaNotice>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaNoticeMapper.selectList(queryWrapper);
    }

    @Override
    public List<OaNotice> getHomeList(int num, String noticeTypeCode, String token) {
        OaNoticeQueryForm noticeForm = new OaNoticeQueryForm();
        noticeForm.setNoticeTypeCode(noticeTypeCode);
        IPage<OaNotice> pages = this.selectPage(noticeForm, token);
        int len = pages.getRecords().size();
        return pages.getRecords().subList(0, len > num ? num : len);
//        UserVo userVo = UserUtil.getUserVo();
//        if(!noticeTypeCode.equals("oa_notice_type_academy")) { // 如果不是院校公告，则查询所有的数据
//            if (null != userVo) {
//                String dept = userVo.getDept();
//                if (null != dept) {
//                    return oaNoticeMapper.getHomeList(num, noticeTypeCode, dept);
//                } else {
//                    throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取用户部门信息失败 !");
//                }
//            } else {
//                throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取当前登录用户信息失败");
//            }
//        } else {
//            return oaNoticeMapper.getHomeList(num, noticeTypeCode, null);
//        }
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<OaNotice> getQueryWrapper(QueryWrapper<OaNotice> queryWrapper,OaNoticeQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getTitle())){
            queryWrapper.like(OaNotice.TITLE, queryForm.getTitle());
        }
        if(StringUtils.isNotBlank(queryForm.getUrgentLevel())){
            queryWrapper.eq(OaNotice.URGENT_LEVEL,queryForm.getUrgentLevel());
        }
        if(null != queryForm.getNoticeTypeCode()){
            queryWrapper.eq(OaNotice.NOTICE_TYPE_CODE, queryForm.getNoticeTypeCode());
        }
        if(null != queryForm.getId()){
            queryWrapper.eq(OaNotice.ID,queryForm.getId ());
        }
        if(StringUtils.isNotBlank(queryForm.getNoticeTypeName())){
            queryWrapper.eq(OaNotice.NOTICE_TYPE_NAME,queryForm.getNoticeTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyOrgCode())){
            queryWrapper.eq(OaNotice.APPLY_ORG_CODE,queryForm.getApplyOrgCode());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyOrgName())){
            queryWrapper.like(OaNotice.APPLY_ORG_NAME,queryForm.getApplyOrgName());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyUserId())){
            queryWrapper.eq(OaNotice.APPLY_USER_ID,queryForm.getApplyUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyUserName())){
            queryWrapper.eq(OaNotice.APPLY_USER_NAME,queryForm.getApplyUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getNoticeSortCode())){
            queryWrapper.eq(OaNotice.NOTICE_SORT_CODE,queryForm.getNoticeSortCode());
        }
        if(StringUtils.isNotBlank(queryForm.getNoticeSortName())){
            queryWrapper.eq(OaNotice.NOTICE_SORT_NAME,queryForm.getNoticeSortName());
        }
        if(StringUtils.isNotBlank(queryForm.getNoticeContent())){
            queryWrapper.eq(OaNotice.NOTICE_CONTENT,queryForm.getNoticeContent());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyDeptCode())){
            queryWrapper.eq(OaNotice.APPLY_DEPT_CODE,queryForm.getApplyDeptCode());
        }
        if(StringUtils.isNotBlank(queryForm.getApprovalStatus())){
            queryWrapper.eq(OaNotice.APPROVAL_STATUS, queryForm.getApprovalStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptPer())){
            queryWrapper.eq(OaNotice.DEPT_PER, queryForm.getDeptPer());
        }
        queryWrapper.orderByDesc(OaNotice.APPLY_DATETIME);
        return queryWrapper;
    }

}

