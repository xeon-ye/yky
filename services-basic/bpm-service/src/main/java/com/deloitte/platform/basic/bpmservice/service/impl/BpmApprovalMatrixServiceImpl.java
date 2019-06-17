package com.deloitte.platform.basic.bpmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryFormForApproval;
import com.deloitte.platform.api.bpmservice.vo.BpmTaskNextNodeVo;
import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.api.isump.feign.OrganizationFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserBpmForm;
import com.deloitte.platform.api.isump.vo.UserBpmVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.basic.bpmservice.core.IActivityService;
import com.deloitte.platform.basic.bpmservice.entity.BpmApprovalMatrix;
import com.deloitte.platform.basic.bpmservice.entity.BpmProcessTask;
import com.deloitte.platform.basic.bpmservice.entity.BpmProcessTaskApprove;
import com.deloitte.platform.basic.bpmservice.mapper.BpmApprovalMatrixMapper;
import com.deloitte.platform.basic.bpmservice.service.IBpmApprovalMatrixService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.basic.bpmservice.service.IBpmProcessTaskApproveService;
import com.deloitte.platform.basic.bpmservice.service.IBpmProcessTaskService;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

/**
 * @Author : jackliu
 * @Date : Create in 2019-03-16
 * @Description :  BpmApprovalMatrix服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BpmApprovalMatrixServiceImpl extends ServiceImpl<BpmApprovalMatrixMapper, BpmApprovalMatrix> implements IBpmApprovalMatrixService {

    @Autowired
    private IActivityService activityService;

    @Autowired
    private BpmApprovalMatrixMapper bpmApprovalMatrixMapper;

    @Override
    public IPage<BpmApprovalMatrix> selectPage(BpmApprovalMatrixQueryForm queryForm ) {
        QueryWrapper<BpmApprovalMatrix> queryWrapper = new QueryWrapper <BpmApprovalMatrix>();
        getQueryWrapper(queryWrapper,queryForm);
        return bpmApprovalMatrixMapper.selectPage(new Page<BpmApprovalMatrix>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<BpmApprovalMatrix> selectList(BpmApprovalMatrixQueryForm queryForm) {
        QueryWrapper<BpmApprovalMatrix> queryWrapper = new QueryWrapper <BpmApprovalMatrix>();
        getQueryWrapper(queryWrapper,queryForm);
        return bpmApprovalMatrixMapper.selectList(queryWrapper);
    }

    @Autowired
    IBpmProcessTaskService bpmProcessTaskService;

    @Autowired
    IBpmProcessTaskApproveService bpmProcessTaskApproveService;

    @Autowired
    UserFeignService userFeignService;

    @Autowired
    OrganizationFeignService organizationFeignService;

    @Override
    public GdcPage<BpmApprovalMatrix> findApprover(BpmApprovalMatrixQueryFormForApproval queryForm, boolean isNextPersion){
        GdcPage<BpmApprovalMatrix> page = new GdcPage<BpmApprovalMatrix>();
        QueryWrapper<BpmApprovalMatrix> queryWrapper = new QueryWrapper <BpmApprovalMatrix>();
        BpmApprovalMatrixQueryForm bpmApprovalMatrix =new BeanUtils<BpmApprovalMatrixQueryForm>().copyObj(queryForm,BpmApprovalMatrixQueryForm.class);
        BpmProcessTask history = null;
        if(isNextPersion){
            ActivityImpl activity=null;
            Map varibales= queryForm.getProcessVariables();
            if(varibales!=null){
                Iterator<String> it = varibales.keySet().iterator();
                while (it.hasNext()){
                    String key = it.next();
                    Object obj = varibales.get(key);
                    if(obj!=null && NumberUtils.isNumber(obj.toString())){
                        if(obj.toString().indexOf(".")>0){
                            varibales.put(key,Double.valueOf(obj.toString()));
                        }
                    }
                }
            }
            if("start".equals(queryForm.getTaskID())){
                 varibales.put("processDefineKey",queryForm.getProcessDefineKey());
                 activity=activityService.getFirstActivity(varibales);
            }else{
                activity=activityService.getNextActivity(queryForm.getTaskID(),varibales);
            }
            if(activity==null){
                page.setContent(new ArrayList<BpmApprovalMatrix>());
                page.setTotal(0);
                page.setPageNo(1);
                page.setPageSize(0);
                return page;
                //return new ArrayList<BpmApprovalMatrix>();
            }else{
               history = bpmProcessTaskService.findBpmProcessTask("",queryForm.getTaskID());
               if(history!=null&&activity.getId().equals(history.getTaskKey())){
                   //下一节点和当前节点一致，为多实例串行节点
                   BpmProcessTaskApprove approve = new BpmProcessTaskApprove();
                   approve.setProcessInstanceId(history.getProcessInstanceId());
                   approve.setTaskKey(history.getTaskKey());
                   approve.setPreviousTaskId(history.getPreviousTaskId());
                   approve.setEndFlag("0");
                   List<BpmProcessTaskApprove> approveList = bpmProcessTaskApproveService.selectList(approve);
                   if (approveList != null && approveList.size() > 0) {
                       approve = approveList.get(0);
                       BpmApprovalMatrix approvalMatrix = new BpmApprovalMatrix();
                       approvalMatrix.setAccountNum(approve.getAcountId());
                       approvalMatrix.setAccountName(approve.getAcountName());
                       bpmApprovalMatrix.setOrgCode(approve.getOrgId());
                       bpmApprovalMatrix.setPosition(approve.getStationId());
                       approvalMatrix.setActivityId(activity.getId());
                       List <BpmApprovalMatrix> list = new ArrayList<BpmApprovalMatrix>();
                       list.add(approvalMatrix);
                       page.setContent(list);
                       page.setTotal(1);
                       page.setPageNo(1);
                       page.setPageSize(1);
                       return page;
                   }
               }
                bpmApprovalMatrix.setActivityId(activity.getId());
            }
        }else{
            if("start".equals(queryForm.getTaskID())){
                page.setContent(new ArrayList<BpmApprovalMatrix>());
                page.setTotal(0);
                page.setPageNo(1);
                page.setPageSize(0);
                return page;
            }else{
                BpmTaskNextNodeVo currentNode = activityService.getCurrentNodeInfo(queryForm.getProcessDefineKey(),"",queryForm.getTaskID());
                bpmApprovalMatrix.setActivityId(currentNode.getTaskKey());
            }
        }
        getQueryWrapper(queryWrapper,bpmApprovalMatrix);
        //此处需要改造，根据自定义的规则从4a取数
        List<BpmApprovalMatrix> list = bpmApprovalMatrixMapper.selectList(queryWrapper);
        if(list!=null&&list.size()==1){
            //返回的数据为一条，那么这条记录则可能为组合或者自定义规则
            BpmApprovalMatrix bpmApprovalMatrix1 = list.get(0);
            if(bpmApprovalMatrix1.getRuleType()==null||"".equals(bpmApprovalMatrix1.getRuleType())){
                bpmApprovalMatrix1.setRuleType("PERSON");
            }
            if("PERSON".equals(bpmApprovalMatrix1.getRuleType())||"CUSTOM".equals(bpmApprovalMatrix1.getRuleType())){
                //如果为单人或自定义规则，则直接返回结果

            }else{
                UserBpmForm userBpmForm = new UserBpmForm();
                //调用4a的接口，获取数据
                String orgNoList = bpmApprovalMatrix1.getOrgNoList();
                if(orgNoList!=null){
                    if(orgNoList.contains("CURRENT_ORG")){
                        //当前处理人所属部门，即为当前待办的审批人的部门
                        if("start".equals(queryForm.getTaskID())){
                            //start环节
                            if(queryForm.getProcessVariables().get("CURRENT_ORG")==null){
                                throw new ServiceException(PlatformErrorType.ARGUMENT_NOT_VALID, "无法解析审批矩阵中的 CURRENT_ORG，请通过processVariables传入对应的值 ");
                            }
                            orgNoList = orgNoList.replace("CURRENT_ORG", queryForm.getProcessVariables().get("CURRENT_ORG"));
                        }else{
                            if(history==null){
                                history = bpmProcessTaskService.findBpmProcessTask("",queryForm.getTaskID());
                            }
                            if (history == null) {
                                throw new ServiceException(PlatformErrorType.NO_DATA_FOUND, "通过taskId，无法查询到对应的审批历史 ");
                            }
                            orgNoList = orgNoList.replace("CURRENT_ORG", history.getApproverOrg());
                        }

                    }
                    if(orgNoList.contains("SUBMIT_ORG")){
                        //流程提交人所属部门
                        if("start".equals(queryForm.getTaskID())){
                            //start环节
                            if(queryForm.getProcessVariables().get("SUBMIT_ORG")==null){
                                throw new ServiceException(PlatformErrorType.ARGUMENT_NOT_VALID, "无法解析审批矩阵中的 SUBMIT_ORG，请通过processVariables传入对应的值 ");
                            }
                            orgNoList = orgNoList.replace("SUBMIT_ORG", queryForm.getProcessVariables().get("SUBMIT_ORG"));
                        }else {
                            if (history == null) {
                                history = bpmProcessTaskService.findBpmProcessTask("", queryForm.getTaskID());
                            }
                            if (history == null) {
                                throw new ServiceException(PlatformErrorType.NO_DATA_FOUND, "通过taskId，无法查询到对应的审批历史 ");
                            }
                            orgNoList = orgNoList.replace("SUBMIT_ORG", history.getSubmitterOrg());
                        }
                    }
                    if(orgNoList.contains("CURRENT_DEPT_ORG")){
                        //当前处理人所属单位
                        if("start".equals(queryForm.getTaskID())){
                            //start环节
                            if(queryForm.getProcessVariables().get("CURRENT_DEPT_ORG")==null){
                                throw new ServiceException(PlatformErrorType.ARGUMENT_NOT_VALID, "无法解析审批矩阵中的 CURRENT_DEPT_ORG，请通过processVariables传入对应的值 ");
                            }
                            orgNoList = orgNoList.replace("CURRENT_DEPT_ORG", queryForm.getProcessVariables().get("CURRENT_DEPT_ORG"));
                            userBpmForm.setOrgCode(queryForm.getProcessVariables().get("CURRENT_DEPT_ORG"));
                        }else {
                            if (history == null) {
                                history = bpmProcessTaskService.findBpmProcessTask("", queryForm.getTaskID());
                            }
                            if (history == null) {
                                throw new ServiceException(PlatformErrorType.NO_DATA_FOUND, "通过taskId，无法查询到对应的审批历史 ");
                            }
                            Result<OrganizationVo> result = organizationFeignService.getByCode(history.getApproverOrg());
                            if (result.isSuccess()) {
                                String path = result.getData().getPath();
                                if (path == null) {
                                    path = "1001";
                                } else {
                                    path = path.split("/")[0];
                                }
                                orgNoList = orgNoList.replace("CURRENT_DEPT_ORG", path);
                                userBpmForm.setOrgCode(path);
                            } else {
                                throw new ServiceException(PlatformErrorType.GATEWAY_CONNECT_TIME_OUT, result.getMesg());
                            }
                        }
                    }
                    if(orgNoList.contains("SUBMIT_DEPT_ORG")){
                        //流程提交人所属单位
                        if("start".equals(queryForm.getTaskID())){
                            //start环节
                            if(queryForm.getProcessVariables().get("SUBMIT_DEPT_ORG")==null){
                                throw new ServiceException(PlatformErrorType.ARGUMENT_NOT_VALID, "无法解析审批矩阵中的 SUBMIT_DEPT_ORG，请通过processVariables传入对应的值 ");
                            }
                            orgNoList = orgNoList.replace("SUBMIT_DEPT_ORG", queryForm.getProcessVariables().get("SUBMIT_DEPT_ORG"));
                            userBpmForm.setOrgCode(queryForm.getProcessVariables().get("SUBMIT_DEPT_ORG"));
                        }else {
                            if (history == null) {
                                history = bpmProcessTaskService.findBpmProcessTask("", queryForm.getTaskID());
                            }
                            if (history == null) {
                                throw new ServiceException(PlatformErrorType.NO_DATA_FOUND, "通过taskId，无法查询到对应的审批历史 ");
                            }
                            Result<OrganizationVo> result = organizationFeignService.getByCode(history.getSubmitterOrg());
                            if (result.isSuccess()) {
                                String path = result.getData().getPath();
                                if (path == null) {
                                    path = "1001";
                                } else {
                                    path = path.split("/")[0];
                                }
                                orgNoList = orgNoList.replace("SUBMIT_DEPT_ORG", path);
                                userBpmForm.setOrgCode(path);
                            } else {
                                throw new ServiceException(PlatformErrorType.GATEWAY_CONNECT_TIME_OUT, result.getMesg());
                            }
                        }
                    }
                    List orgList = Arrays.asList(orgNoList.split(","));
                    userBpmForm.setOrgCodeList(orgList);
                }
                if(bpmApprovalMatrix1.getRoleNoList()!=null&&!"".equals(bpmApprovalMatrix1.getRoleNoList())){
                    List roleList = Arrays.asList(bpmApprovalMatrix1.getRoleNoList().split(","));
                    userBpmForm.setRoleCodeList(roleList);
                    userBpmForm.setSystemName(bpmApprovalMatrix1.getSystemCode());
                }
                if(bpmApprovalMatrix1.getPositionList()!=null&&!"".equals(bpmApprovalMatrix1.getPositionList())){
                    List positionList = Arrays.asList(bpmApprovalMatrix1.getPositionList().split(","));
                    userBpmForm.setOrgNameList(positionList);
                }
                userBpmForm.setSize(queryForm.getPageSize());
                userBpmForm.setPage(queryForm.getCurrentPage());

                Result<GdcPage<UserBpmVo>> rs = userFeignService.getBpmUserListPage(userBpmForm);
                if(rs.isSuccess()){
                    list.remove(0);
                    for(UserBpmVo user : rs.getData().getContent()){
                        BpmApprovalMatrix approve = new BeanUtils<BpmApprovalMatrix>().copyObj(bpmApprovalMatrix1,BpmApprovalMatrix.class);
                        approve.setAccountName(user.getName());
                        approve.setAccountNum(user.getId());
                        approve.setAccountEmpNo(user.getEmpNo());
                        approve.setPosition(user.getStationName());
                        approve.setOrgCode(user.getOrgCode());
                        list.add(approve);
                    }
                }else{
                    throw new ServiceException(PlatformErrorType.GATEWAY_CONNECT_TIME_OUT,rs.getMesg());
                }

                page.setContent(list);
                page.setTotal(rs.getData().getTotal());
                page.setPageNo(rs.getData().getPageNo());
                page.setPageSize(rs.getData().getPageSize());
                return page;
            }
        }
        page.setContent(list);
        page.setTotal(list.size());
        page.setPageNo(1);
        page.setPageSize(list.size());
        return page;
        //return bpmApprovalMatrixMapper.selectList(queryWrapper);
    }


    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     * */
    public QueryWrapper<BpmApprovalMatrix> getQueryWrapper(QueryWrapper<BpmApprovalMatrix> queryWrapper,BpmApprovalMatrixQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProcessDefineKey())){
            queryWrapper.eq(BpmApprovalMatrix.PROCESS_DEFINE_KEY,queryForm.getProcessDefineKey());
        }
        if(StringUtils.isNotBlank(queryForm.getActivityId())){
            queryWrapper.eq(BpmApprovalMatrix.ACTIVITY_ID,queryForm.getActivityId());
        }
        if(StringUtils.isNotBlank(queryForm.getActivityName())){
            queryWrapper.eq(BpmApprovalMatrix.ACTIVITY_NAME,queryForm.getActivityName());
        }
        if(StringUtils.isNotBlank(queryForm.getChargeOrg())){
            queryWrapper.and(wrapper->wrapper.eq(BpmApprovalMatrix.CHARGE_ORG,queryForm.getChargeOrg()).or().eq(BpmApprovalMatrix.CHARGE_ORG, -1));
        }
        if(StringUtils.isNotBlank(queryForm.getChargeBusiness())){
            queryWrapper.eq(BpmApprovalMatrix.CHARGE_BUSINESS,queryForm.getChargeBusiness());
        }
        if(StringUtils.isNotBlank(queryForm.getAccountNum())){
            queryWrapper.eq(BpmApprovalMatrix.ACCOUNT_NUM,queryForm.getAccountNum());
        }
        if(StringUtils.isNotBlank(queryForm.getAccountName())){
            queryWrapper.eq(BpmApprovalMatrix.ACCOUNT_NAME,queryForm.getAccountName());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgCode())){
            queryWrapper.eq(BpmApprovalMatrix.ORG_CODE,queryForm.getOrgCode());
        }
        if(StringUtils.isNotBlank(queryForm.getPosition())){
            queryWrapper.eq(BpmApprovalMatrix.POSITION,queryForm.getPosition());
        }
        if(StringUtils.isNotBlank(queryForm.getEffective())){
            queryWrapper.eq(BpmApprovalMatrix.EFFECTIVE,queryForm.getEffective());
        }
        if(queryForm.getEffectiveStart()!=null){
            queryWrapper.eq(BpmApprovalMatrix.EFFECTIVE_START,queryForm.getEffectiveStart());
        }
        if(queryForm.getEffectiveEnd()!=null){
            queryWrapper.eq(BpmApprovalMatrix.EFFECTIVE_END,queryForm.getEffectiveEnd());
        }
        if(queryForm.getCreateTime()!=null){
            queryWrapper.eq(BpmApprovalMatrix.CREATE_TIME,queryForm.getCreateTime());
        }
        if(queryForm.getUpdateTime()!=null){
            queryWrapper.eq(BpmApprovalMatrix.UPDATE_TIME,queryForm.getUpdateTime());
        }
        queryWrapper.orderByAsc(BpmApprovalMatrix.ORDER_NUM);
        return queryWrapper;
    }

}

