package com.deloitte.services.fssc.business.bpm.biz;

import com.alibaba.fastjson.JSON;
import com.deloitte.platform.api.bpmservice.feign.BpmOperatorFeignService;
import com.deloitte.platform.api.bpmservice.vo.BpmTaskNextNodeForm;
import com.deloitte.platform.api.bpmservice.vo.BpmTaskNextNodeVo;
import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.api.fssc.bpm.param.FsscBpmParam;
import com.deloitte.platform.api.isump.feign.OrganizationFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.fssc.budget.entity.BudgetProject;
import com.deloitte.services.fssc.budget.service.IBudgetProjectService;
import com.deloitte.services.fssc.business.bpm.config.ApproveProperties;
import com.deloitte.services.fssc.business.bpm.eum.FsscBpmEum;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmProcessValService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ProcessPersonBiz {

    @Autowired
    private BpmOperatorFeignService bpmOperatorFeignService;

    @Autowired
    private IBaseBpmProcessValService processValService;

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private IBudgetProjectService budgetProjectService;

    @Autowired
    private OrganizationFeignService organizationFeignService;

    @Autowired
    private ApproveProperties approveProperties;
    /**
     * 查询下一个审批人
     */
    public ArrayList<NextNodeParamVo> findNextAuditPerson(FsscBpmParam bpmParam) {
        ArrayList<NextNodeParamVo> paramVos = new ArrayList<>();
        if (MapUtils.isEmpty(bpmParam.getProcessVariables())) {
            Map<String, String> map =
                    processValService.findValueByDocument(bpmParam.getDocumentId(), bpmParam.getDocumentType());
            bpmParam.setProcessVariables(map);
        }
        Map<String, String> processVariables = bpmParam.getProcessVariables();
        String deptCode = processVariables.get("deptCode");
        String unitCode = processVariables.get("unitCode");
        BpmTaskNextNodeVo nextTask = findNextTask(bpmParam);
        String taskKey = nextTask.getTaskKey();
        //结束
        if (FsscBpmEum.END.getValue().equals(nextTask.getTaskNodeName())) {
            NextNodeParamVo vo = new NextNodeParamVo();
            paramVos.add(vo);
            return paramVos;
        }
        //出纳
        if (FsscBpmEum.CASHIER.getValue().equals(taskKey)) {
           if("1001".equals(unitCode)){
               paramVos.add(buildByEmpNo(approveProperties.getCnzh()));
               return paramVos;
           }else {
               throw new FSSCException(FsscErrorType.CHUNA_BUCUNZAI);
           }

        }
        //财务
        if (FsscBpmEum.ACCOUNT.getValue().equals(taskKey)) {
            if("1001".equals(unitCode)){
                paramVos.add(buildByEmpNo(approveProperties.getCwzh()));
                return paramVos;
            }else {
                throw new FSSCException(FsscErrorType.CHUNA_BUCUNZAI_CAIWU);
            }

        }

        //核算主管
        if (FsscBpmEum.GL_MANAGER.getValue().equals(taskKey)) {
            paramVos.add(buildByEmpNo(approveProperties.getHxzgzh()));
            return paramVos;
        }

        //资产
        if (FsscBpmEum.ASSET.getValue().equals(taskKey)) {
            paramVos.add(buildByEmpNo(approveProperties.getZcglsprzh()));
            return paramVos;
        }

        //行政基建处审批
        if (FsscBpmEum.OFFICE_DEPT_LEADER.getValue().equals(taskKey)) {
            NextNodeParamVo vo =  findOrgLeader(approveProperties.getXzjj_code(),"duty");
            paramVos.add(vo);
            return paramVos;
        }

        //处室负责人审批
        if (FsscBpmEum.DEPT_LEADER.getValue().equals(taskKey)) {
            NextNodeParamVo vo =  findOrgLeader(deptCode,"duty");
            paramVos.add(vo);
            return paramVos;
        }

        //院校办公室审批
        if (FsscBpmEum.ADMIN_DEPT_LEADER.getValue().equals(taskKey)) {
            NextNodeParamVo vo = findOrgLeader(approveProperties.getYxbgs_code(),"duty");
            paramVos.add(vo);
            return paramVos;
        }

        //分管财务院校领导审批
        if (FsscBpmEum.FI_MANAGER_LEADER.getValue().equals(taskKey)) {
            NextNodeParamVo vo = findOrgLeader(approveProperties.getCwc_code(),"leader");
            paramVos.add(vo);
            return paramVos;
        }

        //部门分管院校领导审批
        if (FsscBpmEum.MANAGER_LEADER.getValue().equals(taskKey)) {
            NextNodeParamVo vo = findOrgLeader(deptCode,"leader");
            paramVos.add(vo);
            return paramVos;
        }


        //后勤服务中心审批
        if (FsscBpmEum.BACK_DEPT_LEADER.getValue().equals(taskKey)) {
            NextNodeParamVo vo = findOrgLeader(approveProperties.getHqfwqzx_code(),"duty");
            paramVos.add(vo);
            return paramVos;
        }



        //分管科研院校领导审批
        if (FsscBpmEum.RE_MANAGER_LEADER.getValue().equals(taskKey)) {
            NextNodeParamVo vo = findOrgLeader(approveProperties.getKjglc_code(),"leader");
            paramVos.add(vo);
            return paramVos;
        }

        //科技管理处负责人审批
        if (FsscBpmEum.RE_DEPT_LEADER.getValue().equals(taskKey)) {
            NextNodeParamVo vo = findOrgLeader(approveProperties.getKjglc_code(),"duty");
            paramVos.add(vo);
            return paramVos;
        }

        //财务处负责人审核
        if (FsscBpmEum.FI_DEPTLEADER.getValue().equals(taskKey)) {
            NextNodeParamVo vo = findOrgLeader(approveProperties.getCwc_code(),"duty");
            paramVos.add(vo);
            return paramVos;
        }

        //全过程审计
        if (FsscBpmEum.AUDIT.getValue().equals(taskKey)) {
            paramVos.add(buildByEmpNo(approveProperties.getQgcsjzh()));
            return paramVos;
        }

        //项目负责人
        if (FsscBpmEum.PROJECT_LEADER.getValue().equals(taskKey)) {
            String projectId = processVariables.get("projectId");
            if (StringUtil.isNotEmpty(projectId)) {
                BudgetProject project = budgetProjectService.getById(StringUtil.castTolong(projectId));
                if (project != null) {
                    String projectDuty = project.getProjectDutyId();
                    AssertUtils.asserts(StringUtil.isNotEmpty(projectDuty), FsscErrorType.PROJECT_LEADER_IS_NULL);
                    Result<UserVo> pic = userFeignService.getUserById(projectDuty);
                    AssertUtils.asserts(pic.isSuccess(), FsscErrorType.GET_USER_NOT_EXIST);
                    UserVo data = pic.getData();
                    if (data != null) {
                        NextNodeParamVo vo = new NextNodeParamVo();
                        vo.setAcountId(data.getId());
                        vo.setAcountName(data.getName());
                        vo.setStationId(data.getManagePositionRankName());
                        paramVos.add(vo);
                        return paramVos;
                    }
                }
            }

        }

        AssertUtils.asserts(CollectionUtils.isNotEmpty(paramVos),FsscErrorType.FIND_NODE_FIAILD);
        return paramVos;
    }


    /**
     * 查询部门领导人
     * @param deptCode
     * @param type
     * @return
     */
    private NextNodeParamVo findOrgLeader(String deptCode, String type) {

        AssertUtils.asserts(StringUtil.isNotEmpty(deptCode), FsscErrorType.DEPT_CODE_CANTBE_NUMM);
        Result<OrganizationVo> organizationVoResult = organizationFeignService.getByCode(deptCode);
        AssertUtils.asserts(organizationVoResult.isSuccess(), FsscErrorType.GET_ORG_NOT_EXIST);
        OrganizationVo org = organizationVoResult.getData();
        AssertUtils.asserts(org != null, FsscErrorType.GET_ORG_NOT_EXIST);
        UserVo userVo=null;
        if ("duty".equals(type)) {
            String dutyperson = org.getDutyperson();
            userVo = findUserByEmpNo(dutyperson);
        }

        if ("leader".equals(type)) {
            String leader = org.getLeader();
            userVo= findUserByEmpNo(leader);
        }
        AssertUtils.asserts(userVo!=null,FsscErrorType.LEADER_EMP_NO_NOT_NULL_2);
        NextNodeParamVo vo = new NextNodeParamVo();
        vo.setAcountName(userVo.getName());
        vo.setAcountId(userVo.getId());
        vo.setStationId(userVo.getPositionTitle());

        return vo;
    }

    private UserVo findUserByEmpNo(String empNo) {
        AssertUtils.asserts(StringUtil.isNotEmpty(empNo), FsscErrorType.LEADER_EMP_NO_NOT_NULL);
        Result result = userFeignService.getByEmpNo(empNo);
        AssertUtils.asserts(result.isSuccess(), FsscErrorType.LEADER_EMP_NO_NOT_NULL_2);
        Object data = result.getData();
        if (data != null) {
            UserVo userVo = JSON.parseObject(JSON.toJSONString(data), UserVo.class);
            return userVo;
        }
        throw new FSSCException(FsscErrorType.LEADER_EMP_NO_NOT_NULL_2);
    }


    private NextNodeParamVo buildByEmpNo(String empNo){

        UserVo user = findUserByEmpNo(empNo);

        NextNodeParamVo vo = new NextNodeParamVo();
        vo.setAcountId(user.getId());
        vo.setAcountName(user.getName());
        vo.setStationId(user.getPositionTitle());

        return vo;
    }

    /**
     * 查询下一个审批节点
     *
     * @param bpmParam
     * @return
     */
    private BpmTaskNextNodeVo findNextTask(FsscBpmParam bpmParam) {
        BpmTaskNextNodeForm form = new BpmTaskNextNodeForm();
        form.setProcessDefId(bpmParam.getProcessDefKey());
        form.setProcessInstanceId(bpmParam.getProcessInstanceId());
        form.setProcessVariables(bpmParam.getProcessVariables());
        form.setTaskId(bpmParam.getTaskId());
        log.info("查询审批下一节点参数{}", JSON.toJSONString(form));
        Result nextNodeList = bpmOperatorFeignService.findNextNodeList(form);
        AssertUtils.asserts(nextNodeList.isSuccess(), FsscErrorType.FIND_NEXT_TASK_FAILED);
        List<BpmTaskNextNodeVo> bpmTaskNextNodeVos =
                JSON.parseArray(JSON.toJSONString(nextNodeList.getData()), BpmTaskNextNodeVo.class);
        if (CollectionUtils.isNotEmpty(bpmTaskNextNodeVos)) {
            return bpmTaskNextNodeVos.get(0);
        }
        throw new FSSCException(FsscErrorType.FIND_NEXT_TASK_FAILED_IS_NULL);
    }
}
