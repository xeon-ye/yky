package com.deloitte.services.isump.service.impl;

import com.deloitte.platform.api.bpmservice.feign.BpmApprovalMatrixFeignService;
import com.deloitte.platform.api.bpmservice.feign.BpmOperatorFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryFormForApproval;
import com.deloitte.platform.api.bpmservice.vo.*;
import com.deloitte.platform.api.isump.param.DeputyAccountQueryForm;
import com.deloitte.platform.api.isump.param.bpm.ApprovalForm;
import com.deloitte.platform.api.isump.param.bpm.ApprovalMatrixVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.push.feign.SendMssFeignService;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.isump.entity.*;
import com.deloitte.services.isump.mapper.DeptMapper;
import com.deloitte.services.isump.service.*;
import com.deloitte.services.isump.util.ConfigPropertiesUtil;
import com.deloitte.services.isump.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhangdi
 * @Date 11/05/2019
 */
@Service
@Slf4j
@Transactional
public class BpmServiceImpl implements IBpmService {

    @Autowired
    private BpmApprovalMatrixFeignService bpmApprovalMatrixFeignService;
    @Autowired
    private BpmOperatorFeignService bpmOperatorFeignService;
    @Autowired
    private IUserService userService;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private SendMssFeignService sendMssFeignService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private ConfigPropertiesUtil configPropertiesUtil;
    @Autowired
    private IDeputyAccountService deputyAccountService;
    @Autowired
    private IDeputyAccountRoleService deputyAccountRoleService;
    @Autowired
    private IDeptService deptService;

    /**
     * 发起流程（用户注册）
     * @param user user
     * @return
     */
    @Override
    public Result submitProcess(User user, String objectUrl) {
        //查询机构主要联系人信息
        User userEntity = userService.getDeptByCode(user.getDept());
        if(userEntity == null){
            throw new BaseException(PlatformErrorType.USER_SAVE_ERROR);
        }
        BpmProcessOperatorFormStart bpmStartForm = new BpmProcessOperatorFormStart();
        // 组装下一个审批人参数
        ArrayList<NextNodeParamVo> processNext = new ArrayList<>();
        NextNodeParamVo nodeParamVo = new NextNodeParamVo();
        nodeParamVo.setAcountName(userEntity.getName());
        nodeParamVo.setAcountId(String.valueOf(userEntity.getId()));
        nodeParamVo.setObjectUrl(objectUrl);
        processNext.add(nodeParamVo);
        bpmStartForm.setNextNodeParamVo(processNext);
        // 流程参数对象
        BpmProcessTaskFormStart startForm = new BpmProcessTaskFormStart();
        startForm.setObjectOrderNum(ObjectUtil.getOrderNum(configPropertiesUtil.userRegisterProcessDefineKey, configPropertiesUtil));
        startForm.setProcessDefineKey(configPropertiesUtil.userRegisterProcessDefineKey);
        startForm.setSourceSystem(configPropertiesUtil.userRegisterSourceSystem);
        startForm.setObjectType(configPropertiesUtil.userRegisterObjectType);
        startForm.setObjectDescription("注册申请人："+user.getName());
        //提交人信息
        startForm.setObjectId(String.valueOf(user.getId()));
        startForm.setSubmitterCode(String.valueOf(user.getId()));
        startForm.setSubmitterName(user.getName());
        startForm.setSubmitterOrg(user.getDept());
        bpmStartForm.setBpmProcessTaskForm(startForm);
        Result result =bpmOperatorFeignService.startProcess(bpmStartForm);
        //发送注册邮件
        sendUserMailRegisterMessage(user);
        return result;
    }

    /**
     *  用户注册审批
     * @param approvalForm
     * @return
     */
    @Override
    public Result approvalProcess(ApprovalForm approvalForm) {
        BpmProcessOperatorApprove bpmApproveForm = new BpmProcessOperatorApprove();
        ArrayList<NextNodeParamVo> processNext = new ArrayList<>();
        if(approvalForm.getSystemList().size() > 0){
            for(String sys : approvalForm.getSystemList()){
                if(configPropertiesUtil.systemNameFssc.equals(sys)){
                    log.info("财务");
                    NextNodeParamVo nodeParamVo = new NextNodeParamVo();
                    nodeParamVo.setAcountName(configPropertiesUtil.fsscAdminName);
                    nodeParamVo.setAcountId(configPropertiesUtil.fsscAdminId);
                    nodeParamVo.setObjectUrl(approvalForm.getObjectUrl()+"?pageType="+configPropertiesUtil.systemNameFssc);
                    processNext.add(nodeParamVo);
                } else if(configPropertiesUtil.systemNameSrpmp.equals(sys)){
                    log.info("科研");
                    NextNodeParamVo nodeParamVo = new NextNodeParamVo();
                    nodeParamVo.setAcountName(configPropertiesUtil.srpmpAdminName);
                    nodeParamVo.setAcountId(configPropertiesUtil.srpmpAdminId);
                    nodeParamVo.setObjectUrl(approvalForm.getObjectUrl()+"?pageType="+configPropertiesUtil.systemNameSrpmp);
                    processNext.add(nodeParamVo);
                } else if(configPropertiesUtil.systemNameOaService.equals(sys)){
                    log.info("协同办公");
                    NextNodeParamVo nodeParamVo = new NextNodeParamVo();
                    nodeParamVo.setAcountName(configPropertiesUtil.oaserviceAdminName);
                    nodeParamVo.setAcountId(configPropertiesUtil.oaserviceAdminId);
                    nodeParamVo.setObjectUrl(approvalForm.getObjectUrl()+"?pageType="+configPropertiesUtil.systemNameOaService);
                    processNext.add(nodeParamVo);
                } else if(configPropertiesUtil.systemNameHR.equals(sys)){
                    log.info("HR");
                    NextNodeParamVo nodeParamVo = new NextNodeParamVo();
                    nodeParamVo.setAcountName(configPropertiesUtil.hrAdminName);
                    nodeParamVo.setAcountId(configPropertiesUtil.hrAdminId);
                    nodeParamVo.setObjectUrl(approvalForm.getObjectUrl()+"?pageType="+configPropertiesUtil.systemNameHR);
                    processNext.add(nodeParamVo);
                } else if(configPropertiesUtil.systemNameContract.equals(sys)){
                    log.info("contract");
                    NextNodeParamVo nodeParamVo = new NextNodeParamVo();
                    nodeParamVo.setAcountName(configPropertiesUtil.contractAdminName);
                    nodeParamVo.setAcountId(configPropertiesUtil.contractAdminId);
                    nodeParamVo.setObjectUrl(approvalForm.getObjectUrl()+"?pageType="+configPropertiesUtil.systemNameContract);
                    processNext.add(nodeParamVo);
                } else if(configPropertiesUtil.systemNameDssv1.equals(sys)){
                    log.info("contract");
                    NextNodeParamVo nodeParamVo = new NextNodeParamVo();
                    nodeParamVo.setAcountName(configPropertiesUtil.dssv1AdminName);
                    nodeParamVo.setAcountId(configPropertiesUtil.dssv1AdminId);
                    nodeParamVo.setObjectUrl(approvalForm.getObjectUrl()+"?pageType="+configPropertiesUtil.systemNameDssv1);
                    processNext.add(nodeParamVo);
                }
            }
            bpmApproveForm.setNextNodeParamVo(processNext);
            // 流程参数对象
            BpmProcessTaskFormApprove bpmApproveTaskFrom = new BpmProcessTaskFormApprove();
            bpmApproveTaskFrom.setId(approvalForm.getId());
            bpmApproveTaskFrom.setOpinion(approvalForm.getOpinion());
            bpmApproveTaskFrom.setObjectDescription(approvalForm.getObjectDescription());
            bpmApproveForm.setBpmProcessTaskForm(bpmApproveTaskFrom);

            /**
             * 添加权限
             * 只有会签时PageType才会不为空，当会签流程审批通过后
             * 如果会签类型不为空，根据会签类型添加系统权限
             */
            if(StringUtils.isNotBlank(approvalForm.getPageType())){
                DeputyAccountQueryForm deputyAccountQueryForm = new DeputyAccountQueryForm();
                deputyAccountQueryForm.setUserId(Long.parseLong(approvalForm.getObjectId()));
                List<DeputyAccount> deputyAccountsList = deputyAccountService.selectList(deputyAccountQueryForm);
                if(deputyAccountsList.size() > 0){
                    List<Role> roleList = null;
                    if(configPropertiesUtil.systemNameSrpmp.equals(approvalForm.getPageType())){
                        roleList = roleService.getRoleList(configPropertiesUtil.systemNameSrpmp, configPropertiesUtil.srpmpRole);
                    }else if(configPropertiesUtil.systemNameFssc.equals(approvalForm.getPageType())){
                        roleList = roleService.getRoleList(configPropertiesUtil.systemNameFssc, configPropertiesUtil.fsscRole);
                    }else if(configPropertiesUtil.systemNameOaService.equals(approvalForm.getPageType())){
                        roleList = roleService.getRoleList(configPropertiesUtil.systemNameOaService, configPropertiesUtil.oaserviceRole);
                    }else if(configPropertiesUtil.systemNameHR.equals(approvalForm.getPageType())){
                        roleList = roleService.getRoleList(configPropertiesUtil.systemNameHR, configPropertiesUtil.hrRole);
                    }else if(configPropertiesUtil.systemNameContract.equals(approvalForm.getPageType())){
                        roleList = roleService.getRoleList(configPropertiesUtil.systemNameContract, configPropertiesUtil.contractRole);
                    }else if(configPropertiesUtil.systemNameDssv1.equals(approvalForm.getPageType())){
                        roleList = roleService.getRoleList(configPropertiesUtil.systemNameDssv1, configPropertiesUtil.dssv1Role);
                    }
                    //添加副账号和角色的关系
                    for(Role r : roleList){
                        DeputyAccountRole dar = new DeputyAccountRole();
                        dar.setDeputyAccountId(deputyAccountsList.get(0).getId());
                        dar.setRoleId(r.getId());
                        deputyAccountRoleService.save(dar);
                    }
                }
            }
        }
        Result result = bpmOperatorFeignService.approveProcess(bpmApproveForm);
        if (result.isFail()) {
            return Result.fail(result.getMesg());
        }
        if ("审批结束".equals(result.getData())) {
            //查询人员信息
            User userEntity = userService.getUserByIdOne(approvalForm.getObjectId());
            //查询用户有权限的所有系统
            List<String> listSystemName = roleService.getSystemNameList(approvalForm.getObjectId());
            if(listSystemName.size() > 0) {
                String autoString = "";
                for (String systemName : listSystemName) {
                    if(configPropertiesUtil.systemNameSrpmp.equals(systemName)){
                        autoString += "科研系统、" ;
                    } else if(configPropertiesUtil.systemNameFssc.equals(systemName)){
                        autoString += "财务资源管理、" ;
                    } else if(configPropertiesUtil.systemNameOaService.equals(systemName)){
                        autoString += "协同办公管理、" ;
                    } else if(configPropertiesUtil.systemNameHR.equals(systemName)){
                        autoString += "人力资源管理、" ;
                    } else if(configPropertiesUtil.systemNameContract.equals(systemName)){
                        autoString += "合同管理、" ;
                    } else if(configPropertiesUtil.systemNameDssv1.equals(systemName)){
                        autoString += "决策分析、" ;
                    }
                    log.info("系统权限为："+autoString);
                }
                if(autoString.length() > 0){
                    autoString = autoString.substring(0, autoString.length()-1);
                }
                //更新状态
                userService.updateState("1", approvalForm.getObjectId());
                //发送短信通知
                sendUserRegisterSuccessMessage(userEntity, autoString);
                //发送邮件通知
                sendUserMailSuccessMessage(userEntity);
            }else{
                //发送短信通知
                sendUserRegisterFailMessage(userEntity);
                //发送邮件通知
                sendUserMailFailMessage(userEntity, approvalForm.getOpinion());
                //更新状态
                userService.updateState("-1", approvalForm.getObjectId());
            }
        }
        return Result.success();
    }

    /**
     * 用户注册发起流程
     * @param processVariables
     * @param user
     * @return
     */
    @Override
    public Result submitProcessOrg(Map<String, String> processVariables, User user, Dept dept) {
        Result result;
        /**
         * 启动流程
         */
        BpmProcessOperatorFormStart startParams = new BpmProcessOperatorFormStart();
        //下一个节点审批人
        ArrayList<NextNodeParamVo> nodeParamVos = new ArrayList<>();
        Result result1 = buildBpmForm(processVariables);
        if(result1.isSuccess()){
            nodeParamVos = (ArrayList<NextNodeParamVo>)result1.getData();
        }
        if(nodeParamVos == null || nodeParamVos.size() <1){
            throw new BaseException(PlatformErrorType.ORG_SAVE_ERROR);
        }
        startParams.setNextNodeParamVo(nodeParamVos);
        //流程参数对象
        BpmProcessTaskFormStart startForm = new BpmProcessTaskFormStart();
        String orderNum = getOrderNum(processVariables.get("processDefineKey"));
        startForm.setObjectOrderNum(orderNum);
        startForm.setProcessDefineKey(processVariables.get("processDefineKey"));
        startForm.setSourceSystem(processVariables.get("sourceSystem"));
        startForm.setObjectType(processVariables.get("objectType"));
        startForm.setObjectDescription(processVariables.get("objectDescription"));
        //提交人信息
        startForm.setObjectId(String.valueOf(user.getId()));
        startForm.setSubmitterCode(String.valueOf(user.getId()));
        startForm.setSubmitterName(user.getName());
        startForm.setSubmitterOrg(user.getDept());
        startParams.setBpmProcessTaskForm(startForm);

        result =bpmOperatorFeignService.startProcess(startParams);
        //发送邮件通知
        sendOrgMailRegisterMessage(dept, user);
        return result;
    }

    /**
     * 流程终止
     * @param approvalForm
     * @return
     */
    @Override
    public Result stopProcess(ApprovalForm approvalForm) {
        BpmProcessTaskFormApprove bpmProcessTaskForm = new BpmProcessTaskFormApprove();
        bpmProcessTaskForm.setId(approvalForm.getId());
        bpmProcessTaskForm.setOpinion(approvalForm.getOpinion());
        bpmProcessTaskForm.setObjectDescription(approvalForm.getObjectDescription());
        Result result = bpmOperatorFeignService.stopProcess(bpmProcessTaskForm);
        if("27".equals(approvalForm.getChargeOrg())){
            //查询单位信息
            DeptVo deptVo = deptMapper.getById(approvalForm.getObjectId());
            //查询单位主要联人信息
            User userEntity = userService.getDeptById(approvalForm.getObjectId());
            //更新状态
            deptService.updateState("-1", approvalForm.getObjectId());
            User deptUser = userService.getDeptById(approvalForm.getObjectId());
            if(deptUser != null){
                userService.updateState("-1", deptUser.getId().toString());
            }
            //机构注册失败发送短信
            sendOrgRegisterFailMessage(deptVo, userEntity);
            //发送邮件通知
            sendOrgMailFailMessage(deptVo, userEntity, approvalForm.getOpinion());
        }else{
            //查询人员信息
            User userEntity = userService.getUserByIdOne(approvalForm.getObjectId());
            //更新状态
            userService.updateState("-1", approvalForm.getObjectId());
            //发送短信通知
            sendUserRegisterFailMessage(userEntity);
            //发送邮件通知
            sendUserMailFailMessage(userEntity,approvalForm.getOpinion());
        }
        return result;
    }

    /**
     * 机构流程审批
     * @param approvalForm
     * @return
     */
    @Override
    public Result orgApprovalCheck(ApprovalForm approvalForm){
        BpmProcessOperatorApprove operatorApprove = new BpmProcessOperatorApprove();
        BpmProcessTaskFormApprove bpmProcessTaskForm = new BpmProcessTaskFormApprove();
        //查询下一个审批人
        ArrayList<NextNodeParamVo> nodeParamVos = new ArrayList<>();
        NextNodeParamVo nodeParamVo = new NextNodeParamVo();
        nodeParamVos.add(nodeParamVo);
        operatorApprove.setNextNodeParamVo(nodeParamVos);
        //流程信息
        bpmProcessTaskForm.setId(approvalForm.getId());
        bpmProcessTaskForm.setOpinion(approvalForm.getOpinion());
        bpmProcessTaskForm.setObjectDescription(approvalForm.getObjectDescription());
        operatorApprove.setBpmProcessTaskForm(bpmProcessTaskForm);
        Result result = bpmOperatorFeignService.approveProcess(operatorApprove);

        //更新状态
        Dept dept = new Dept();
        dept.setId(Long.parseLong(approvalForm.getObjectId()));
        dept.setState("1");
        deptMapper.updateById(dept);
        //查询单位信息
        DeptVo deptVo = deptMapper.getById(approvalForm.getObjectId());
        //查询单位主要联人信息
        User userEntity = userService.getDeptById(approvalForm.getObjectId());
        //更新主要联系人状态
        User user = new User();
        user.setId(userEntity.getId());
        user.setState("1");
        userService.updateById(user);
        //发送短信通知
        sendOrgRegisterSuccessMessage(deptVo, userEntity);
        //发送邮件通知
        sendOrgMailSuccessMessage(deptVo, userEntity);
        return result;
    }

    /**
     * 查询下一节点的负责人
     * @param processVariables 查询参数
     * @return
     */
    @Override
    public Result buildBpmForm(Map<String, String> processVariables) {
        ArrayList<NextNodeParamVo> nodeParamVoList = new ArrayList<>();
        try {
            //构建审批人参数对象
            BpmApprovalMatrixQueryFormForApproval nextMatrixForm = new BpmApprovalMatrixQueryFormForApproval();
            nextMatrixForm.setTaskID(processVariables.get("taskId"));
            nextMatrixForm.setChargeOrg(processVariables.get("chargeOrg"));
            nextMatrixForm.setProcessDefineKey(processVariables.get("processDefineKey"));
            Result<List<BpmApprovalMatrixVo>> nextApprover = bpmApprovalMatrixFeignService.findNextApprover(nextMatrixForm);
            if (nextApprover.isSuccess()) {
                List<BpmApprovalMatrixVo> dataList = nextApprover.getData();
                if (!CollectionUtils.isEmpty(dataList)) {
                    for (BpmApprovalMatrixVo matrixVo : dataList) {
                        NextNodeParamVo nodeParamVo = new NextNodeParamVo();
                        nodeParamVo.setAcountName(matrixVo.getAccountName());
                        nodeParamVo.setAcountId(matrixVo.getAccountNum());
                        nodeParamVo.setOrgId(matrixVo.getOrgCode());
                        nodeParamVo.setStationId(matrixVo.getPosition());
                        nodeParamVo.setObjectUrl(processVariables.get("objectUrl"));
                        nodeParamVoList.add(nodeParamVo);
                    }
                }
            } else {
                log.error("获取下一个节点审批人失败");
                return null;
            }
            //最后一个节点：如果没有审批人 加一个空对象
            if (nodeParamVoList.size() == 0) {
                nodeParamVoList.add(new NextNodeParamVo());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.success(nodeParamVoList);
    }

    /**
     * 当下一个节点的审批人由前端传入时，调用该方法。
     * @return
     */
    private Result<ArrayList<NextNodeParamVo>> receiveBpmForm(List<ApprovalMatrixVo> nextMatriList) {
        ArrayList<NextNodeParamVo> nodeParamVos = new ArrayList<>();
        for (ApprovalMatrixVo approvalMatrixVo : nextMatriList) {
            NextNodeParamVo nextNode = new NextNodeParamVo();
            nextNode.setAcountId(approvalMatrixVo.getAccountNum());
            nextNode.setAcountName(approvalMatrixVo.getAccountName());
            nextNode.setOrgId(approvalMatrixVo.getOrgCode());
            nextNode.setObjectUrl(approvalMatrixVo.getObjectUrl());
            nodeParamVos.add(nextNode);
        }
        return Result.success(nodeParamVos);
    }

    /**
     * 用户注册成功短信模板
     * @param userEntity USER
     */
    public void sendUserRegisterSuccessMessage(User userEntity, String autoString){
        String userBirthDate = userEntity.getBirthDate().toString();
        userBirthDate = userBirthDate.substring(0, 10).replaceAll("-", "");
        String contentMessage = userEntity.getName() + "，欢迎您使用" +
                "中国医学科学院北京协和医学院MacRP系统！\n" +
                "温馨提示您，MacRP登陆网址为http://124.17.100.183:31080/portal/index.html " +
                "您的系统权限有：" + autoString+ "\n" +
                "您的登录账号为：" + userEntity.getPhone() + "初始密码为：" + userBirthDate + " \n" +
                "谢谢您的使用，祝您生活愉快！";
        log.info(contentMessage);
        sendMssFeignService.send(userEntity.getPhone(), contentMessage);
    }

    /**
     * 用户注册失败发送短信
     * @param userEntity USER
     */
    public void sendUserRegisterFailMessage(User userEntity){
        String contentMessage = "，很遗憾的通知您，" +
                "您申请的中国医学科学院北京协和医学院MacRP系统外部人员账户暂未获得通过，" +
                "请登录邮箱查看明细原因，或者联系您所在机构的MacRP管理员。\n" +
                "谢谢您的使用，祝您生活愉快！";
        String reMessage;
        if("男".equals(userEntity.getGender()) || "Y".equals(userEntity.getGender())){
            reMessage = userEntity.getName() +" 先生";
        }else{
            reMessage = userEntity.getName() +" 女士";
        }
        reMessage += contentMessage;
        log.info(reMessage);
        sendMssFeignService.send(userEntity.getPhone() ,reMessage);
    }

    /**
     *  机构注册成功发送短信
     * @param deptVo dept
     * @param userEntity user
     */
    public void sendOrgRegisterSuccessMessage(DeptVo deptVo, User userEntity){
        String userBirthDate = userEntity.getBirthDate().toString();
        userBirthDate = userBirthDate.substring(0, 10).replaceAll("-", "");
        String contentMessage = deptVo.getDeptName()+"，欢迎您使用" +
                "中国医学科学院北京协和医学院MacRP系统！\n" +
                "温馨提示您，MacRP登陆网址为http://124.17.100.183:31080/portal/index.html " +
                "您的机构账号为："+userEntity.getPhone()+"初始密码为："+userBirthDate+" \n" +
                "谢谢您的使用，祝您生活愉快！";
        log.info(contentMessage);
        sendMssFeignService.send(userEntity.getPhone() ,contentMessage);
    }

    /**
     * 机构注册失败发送短信
     * @param deptVo dept
     * @param userEntity user
     */
    public void sendOrgRegisterFailMessage(DeptVo deptVo, User userEntity){
        String contentMessage = "，很遗憾的通知您，您为 "+deptVo.getDeptName()+" 申请的中国医学科学院北京协和医学院MacRP系统" +
                "外部机构账户暂未获得通过，请登录邮箱查看明细原因。\n" +
                "谢谢您的使用，祝您生活愉快！";
        String reMessage;
        if("男".equals(userEntity.getGender()) || "Y".equals(userEntity.getGender())){
            reMessage = userEntity.getName() +" 先生";
        }else{
            reMessage = userEntity.getName() +" 女士";
        }
        reMessage += contentMessage;
        log.info(reMessage);
        sendMssFeignService.send(userEntity.getPhone() ,reMessage);
    }

    /**
     * 机构注册通知（邮件）
     * @param userEntity user
     */
    public void sendOrgMailRegisterMessage(Dept dept, User userEntity){
        String message = "，您好，\n" +
                "您申请的 "+dept.getDeptName()+" 已经进入审核流程，审核结果会通过邮箱反馈给您，谢谢！";
        String reMessage;
        if("男".equals(userEntity.getGender()) || "Y".equals(userEntity.getGender())){
            reMessage = userEntity.getName() +" 先生";
        }else{
            reMessage = userEntity.getName() +" 女士";
        }
        reMessage += message;
        log.info(reMessage);
        sendMssFeignService.sendMail(userEntity.getEmail(), "机构注册通知", reMessage);
    }

    /**
     * 机构注册成功通知（邮件）
     * @param deptVo dept
     * @param userEntity user
     */
    public void sendOrgMailSuccessMessage(DeptVo deptVo, User userEntity){
        String userBirthDate = userEntity.getBirthDate().toString();
        userBirthDate = userBirthDate.substring(0, 10).replaceAll("-", "");
        String message = "，您好，\n" +
                "您申请的 "+deptVo.getDeptName()+" 已经注册成功，" +
                "您的登录账号为 "+userEntity.getEmpNo()+"，密码为 "+userBirthDate+"，请及时登录并修改您的密码，谢谢！";
        String reMessage;
        if("男".equals(userEntity.getGender()) || "Y".equals(userEntity.getGender())){
            reMessage = userEntity.getName() +" 先生";
        }else{
            reMessage = userEntity.getName() +" 女士";
        }
        reMessage += message;
        log.info(reMessage);
        sendMssFeignService.sendMail(userEntity.getEmail(), "机构注册通知", reMessage);
    }

    /**
     * 机构注册失败通知（邮件）
     * @param deptVo dept
     * @param userEntity user
     * @param opinion 审批意见
     */
    public void sendOrgMailFailMessage(DeptVo deptVo, User userEntity, String opinion){
        String message = "，您好，\n" +
                "您申请的 "+deptVo.getDeptName()+" 审核未通过，未通过原因为 "+opinion+"，谢谢！";
        String reMessage;
        if("男".equals(userEntity.getGender()) || "Y".equals(userEntity.getGender())){
            reMessage = userEntity.getName() +" 先生";
        }else{
            reMessage = userEntity.getName() +" 女士";
        }
        reMessage += message;
        log.info(reMessage);
        sendMssFeignService.sendMail(userEntity.getEmail(), "机构注册通知", reMessage);
    }

    /**
     * 用户注册通知（邮件）
     * @param userEntity user
     */
    public void sendUserMailRegisterMessage(User userEntity){
        String message = "，您好，\n" +
                "您的用户注册申请已经进入审核流程，审核结果会通过邮箱反馈给您，谢谢！";
        String reMessage;
        if("男".equals(userEntity.getGender()) || "Y".equals(userEntity.getGender())){
            reMessage = userEntity.getName() +" 先生";
        }else{
            reMessage = userEntity.getName() +" 女士";
        }
        reMessage += message;
        log.info(reMessage);
        sendMssFeignService.sendMail(userEntity.getEmail(), "用户注册通知", reMessage);
    }

    /**
     * 用户注册成功通知（邮件）
     * @param userEntity user
     */
    public void sendUserMailSuccessMessage(User userEntity){
        String userBirthDate = userEntity.getBirthDate().toString();
        userBirthDate = userBirthDate.substring(0, 10).replaceAll("-", "");
        String message = "，您好，\n" +
                "您的用户注册申请已经审核通过，您的登录账号为 "+userEntity.getEmpNo()+"，" +
                "密码为 "+userBirthDate+"，请及时登录并修改您的密码，谢谢！";
        String reMessage;
        if("男".equals(userEntity.getGender()) || "Y".equals(userEntity.getGender())){
            reMessage = userEntity.getName() +" 先生";
        }else{
            reMessage = userEntity.getName() +" 女士";
        }
        reMessage += message;
        log.info(reMessage);
        sendMssFeignService.sendMail(userEntity.getEmail(), "用户注册通知", reMessage);
    }

    /**
     * 用户注册失败通知（邮件）
     * @param userEntity user
     * @param opinion 审批意见
     */
    public void sendUserMailFailMessage(User userEntity, String opinion){
        String userBirthDate = userEntity.getBirthDate().toString();
        userBirthDate = userBirthDate.substring(0, 10).replaceAll("-", "");
        String message = "，您好，\n" +
                "您的用户注册申请审核未通过，未通过原因为 "+opinion+"，谢谢！";
        String reMessage;
        if("男".equals(userEntity.getGender()) || "Y".equals(userEntity.getGender())){
            reMessage = userEntity.getName() +" 先生";
        }else{
            reMessage = userEntity.getName() +" 女士";
        }
        reMessage += message;
        log.info(reMessage);
        sendMssFeignService.sendMail(userEntity.getEmail(), "用户注册通知", reMessage);
    }

    /**
     * 生成流水号
     * @return
     */
    private String getOrderNum(String type) {
        String str ="0123456789";
        String s = "";
        for(int i=0; i<4; i++){
            char ch = str.charAt(new Random().nextInt(str.length()));
            s += ch;
        }
        System.out.println(s);
        String head = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String date = sdf.format(new Date());
        if("process_org_register".equals(type)){
            head = "JGZC" + date + s;
        }
        if("process_user_register".equals(type)){
            head = "RYZC" + date + s;
        }
        return head;
    }
}
