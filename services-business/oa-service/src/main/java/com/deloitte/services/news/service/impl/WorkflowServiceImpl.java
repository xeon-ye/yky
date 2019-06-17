package com.deloitte.services.news.service.impl;

import com.alibaba.fastjson.JSON;
import com.deloitte.platform.api.bpmservice.feign.BpmApprovalMatrixFeignService;
import com.deloitte.platform.api.bpmservice.feign.BpmOperatorFeignService;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryFormForApproval;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.vo.*;
import com.deloitte.platform.api.isump.feign.OrganizationFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.vo.DeputyAccountVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.news.vo.*;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.services.news.entity.News;
import com.deloitte.services.news.mapper.NewsMapper;
import com.deloitte.services.news.service.INewsService;
import com.deloitte.services.news.service.WorkflowService;
import com.deloitte.services.oa.exception.OaErrorType;
import com.deloitte.services.oa.util.OABPMConstantUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class WorkflowServiceImpl implements WorkflowService {

    private final String CHARGE_BUSINESS = "新闻审批";

    private final String PROCESS_DEFINE_KEY = "oa_news_approval_process";

    @Autowired
    private INewsService iNewsService;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private BpmApprovalMatrixFeignService bpmApprovalMatrixFeignService;

    @Autowired
    private BpmOperatorFeignService bpmOperatorFeignService;

    @Autowired
    private BpmProcessTaskFeignService bpmProcessTaskFeignService;

    @Autowired
    private OrganizationFeignService organizationFeignService;

    @Autowired
    private UserFeignService userFeignService;

    @Override
    public Result submitProcess(NewsForm newsForm, String token) {
        log.info("newsForm:{}", JSON.toJSON(newsForm));
        //保存新闻信息与附件信息
        newsForm.setNewsStatus("notRead"); //该新闻处于待审阅状态
        Result save = iNewsService.saveOrUpdate(newsForm);
        if (save.isFail()) {
            return save;
        }
        Result result;
        String taskId = newsForm.getJudgeParam().get("taskId");
        if ("start".equals(taskId)) { //开始流程
            ArrayList<NextNodeParamVo> nodeParamVos = null;
            if ("1".equals(newsForm.getJudgeParam().get("isNeedBusiness"))) {
                Result<ArrayList<NextNodeParamVo>> arrayListResult = this.receiveBpmForm(newsForm.getNextMatriList(), newsForm.getJudgeParam());
                nodeParamVos = arrayListResult.getData();
            } else {
//                nodeParamVos = (ArrayList<NextNodeParamVo>) buildBpmForm(newsForm.getJudgeParam()).getData();
                nodeParamVos = getDutyPerson(newsForm.getJudgeParam());
            }
            //构建流程启动参数对象
            BpmProcessOperatorFormStart startParams = new BpmProcessOperatorFormStart();
            startParams.setNextNodeParamVo(nodeParamVos);
            startParams.setProcessVariables(newsForm.getJudgeParam());
            UserVo userVo = UserUtil.getUserVo();
            if (userVo != null) {
                //构建流程启动参数
                BpmProcessTaskFormStart startForm = new BpmProcessTaskFormStart();
                startForm.setObjectId(String.valueOf(save.getData()));
                startForm.setProcessDefineKey(this.PROCESS_DEFINE_KEY);
                startForm.setSourceSystem("oaservice");
                startForm.setSubmitterCode(String.valueOf(userVo.getId()));
                startForm.setSubmitterName(userVo.getName());
                startForm.setObjectUrl(newsForm.getJudgeParam().get("startUrl"));
                DeputyAccountVo account = userVo.getCurrentDeputyAccount();
                if (account != null) {
                    startForm.setSubmitterOrg(account.getOrgCode());
                }
                String orderNum = getOrderNum();
                startForm.setObjectOrderNum(orderNum);
                startForm.setObjectType(newsForm.getJudgeParam().get("objectType"));
                startForm.setObjectDescription(newsForm.getJudgeParam().get("objectDescription"));
                startParams.setBpmProcessTaskForm(startForm);
                try {
                    result = bpmOperatorFeignService.startProcess(startParams);
                    if (result.isFail()) {
                        newsForm.setNewsStatus("draft");
                        save = iNewsService.saveOrUpdate(newsForm);
                    }
                    updateOrderNum(String.valueOf(save.getData()), orderNum);
                } catch (Exception e) {
                    result = Result.fail("提交流程失败!");
                }
            } else {
                throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取用户部门信息失败 !");
            }

        } else {
            result = Result.fail("请提交开始请求节点");
        }
        return result;
    }

    /**
     * 审批人查询待办之后，获取返回值传入并调用该方法。
     *
     * @param newsProcessForm
     * @return
     */
    @Override
    public Result approval(NewsProcessForm newsProcessForm, String token) {
        BpmProcessOperatorApprove operatorApprove = new BpmProcessOperatorApprove();
        BpmProcessTaskFormApprove bpmProcessTaskForm = new BpmProcessTaskFormApprove();
        Result result = null;
        //批准
        if (OABPMConstantUtil.OPTION_TYPE_SUMBIT.equals(newsProcessForm.getOpinionType())) {
            ArrayList<NextNodeParamVo> nodeParamVos;
            //顺序串行审批
            if ("1".equals(newsProcessForm.getJudgeParam().get("isNeedBusiness"))) {
                Result<ArrayList<NextNodeParamVo>> arrayListResult = this.receiveBpmForm(newsProcessForm.getNextMatriList(), newsProcessForm.getJudgeParam());
                nodeParamVos = arrayListResult.getData();
                //处理待重新提交
            } else if ("0".equals(newsProcessForm.getJudgeParam().get("isNeedBusiness")) && "resubmit".equals(newsProcessForm.getJudgeParam().get("status"))) {
                nodeParamVos = getDutyPerson(newsProcessForm.getJudgeParam());
            } else {
                nodeParamVos = (ArrayList<NextNodeParamVo>) buildBpmForm(newsProcessForm.getJudgeParam()).getData();
            }
            //处理待重新提交
//            if ("0".equals(newsProcessForm.getJudgeParam().get("isNeedBusiness")) && "resubmit".equals(newsProcessForm.getJudgeParam().get("status"))) {
//                nodeParamVos = getDutyPerson(newsProcessForm.getJudgeParam());
//            }
            operatorApprove.setNextNodeParamVo(nodeParamVos);
            operatorApprove.setProcessVariables(newsProcessForm.getJudgeParam());

            bpmProcessTaskForm.setId(newsProcessForm.getId());
            bpmProcessTaskForm.setOpinion(newsProcessForm.getOpinion());
            bpmProcessTaskForm.setObjectDescription(newsProcessForm.getObjectDescription());
            operatorApprove.setBpmProcessTaskForm(bpmProcessTaskForm);
            log.info("流程参数:{}", JSON.toJSONString(operatorApprove));
            result = bpmOperatorFeignService.approveProcess(operatorApprove);
            if (result.isSuccess()) {
                if (PROCESS_DEFINE_KEY.equals(newsProcessForm.getProcessDefineKey()) && StringUtils.isBlank(nodeParamVos.get(0).getAcountId())) {
                    News news = new News();
                    news.setNewsId(Long.valueOf(newsProcessForm.getObjectId()));
                    news.setNewsStatus("normal"); //修改新闻状态
                    UserVo userVo = UserUtil.getUserVo();
                    if (userVo != null) {
                        news.setNewsEditor(userVo.getName());
                        news.setNewsPublictime(LocalDateTime.now());
                        iNewsService.updateById(news);
                    }
                }
            }
        }
        //驳回给指定人,前端根据待办中查询的processInstanceId入参先调用历史查询接口，返回list，选择list中的一个对象传入后台，后台接收直接set
        if (OABPMConstantUtil.OPTION_TYPE_REJECT.equals(newsProcessForm.getOpinionType())) {
            ArrayList<NextNodeParamVo> nextList = new ArrayList<>();
            NextNodeParamVo nodeParamVo = new NextNodeParamVo();
            nodeParamVo.setAcountId(newsProcessForm.getProcessTaskVo().getApproverAcount());
            nodeParamVo.setAcountName(newsProcessForm.getProcessTaskVo().getApproverName());
            nodeParamVo.setOrgId(newsProcessForm.getProcessTaskVo().getApproverOrg());
            nodeParamVo.setObjectUrl(newsProcessForm.getProcessTaskVo().getObjectUrl());
            nextList.add(nodeParamVo);
            operatorApprove.setNextNodeParamVo(nextList);

            bpmProcessTaskForm.setId(newsProcessForm.getId());
//            bpmProcessTaskForm.setId(Long.valueOf(newsProcessForm.getBpmProcessTaskVo().getId()));
            bpmProcessTaskForm.setOpinion(newsProcessForm.getOpinion());
            operatorApprove.setBpmProcessTaskForm(bpmProcessTaskForm);
            String destTaskKey = newsProcessForm.getProcessTaskVo().getTaskKey();
            result = bpmOperatorFeignService.rejectProcess(destTaskKey, operatorApprove);
        }
        //驳回给发起人
        if (OABPMConstantUtil.OPTION_TYPE_REJECT_TO_START.equals(newsProcessForm.getOpinionType())) {
            BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
            bpmProcessTaskQueryForm.setProcessInstanceId(newsProcessForm.getProcessInstanceId());
            bpmProcessTaskQueryForm.setProcessDefineKey(this.PROCESS_DEFINE_KEY);
            //查询历史流程
            Result<List<BpmProcessTaskVo>> re = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
            if (re.isSuccess() && re.getData() != null && re.getData().size() > 0) {
                BpmProcessTaskVo taskVo = re.getData().get(0);
                ArrayList<NextNodeParamVo> nextList = new ArrayList<>();
                NextNodeParamVo firstNodeParamVo = new NextNodeParamVo();

                firstNodeParamVo.setAcountId(taskVo.getSubmitterCode());
                firstNodeParamVo.setAcountName(taskVo.getSubmitterName());
                firstNodeParamVo.setOrgId(taskVo.getSubmitterOrg());
//              firstNodeParamVo.setStationId();
                firstNodeParamVo.setObjectUrl(newsProcessForm.getJudgeParam().get("nextUrl"));
                nextList.add(firstNodeParamVo);
                bpmProcessTaskForm.setId(newsProcessForm.getId());
                bpmProcessTaskForm.setOpinion(newsProcessForm.getOpinion());
                bpmProcessTaskForm.setObjectDescription(newsProcessForm.getObjectDescription());
                operatorApprove.setNextNodeParamVo(nextList);
                operatorApprove.setBpmProcessTaskForm(bpmProcessTaskForm);
                operatorApprove.setProcessVariables(newsProcessForm.getJudgeParam());
                result = bpmOperatorFeignService.rejectToFirstProcess(operatorApprove);
                updateNewsStatus(newsProcessForm.getObjectId());
            } else {
                throw new ServiceException(OaErrorType.BPM_NEXT_APPROVER_ERROR, re.getMesg());
            }
        }
        return result;
    }

    @Override
    public Result rollBackProcess(RollBackProcessVo rollBackProcessVo) {
        BpmProcessOperatorApprove bpmProcessParamForm = new BpmProcessOperatorApprove();
        BpmProcessTaskFormApprove bpmProcessTaskFormApprove = new BpmProcessTaskFormApprove();
        //该id由我的请求中查询
        bpmProcessTaskFormApprove.setId(rollBackProcessVo.getId());
        ArrayList<NextNodeParamVo> nodeParamVoList = new ArrayList<>();
        NextNodeParamVo nextNodeParamVo = new NextNodeParamVo();
        UserVo userVo = UserUtil.getUserVo();
        if (userVo != null) {
            nextNodeParamVo.setAcountId(userVo.getId());
            nextNodeParamVo.setAcountName(userVo.getName());
            DeputyAccountVo account = userVo.getCurrentDeputyAccount();
            if (account != null) {
                nextNodeParamVo.setOrgId(account.getOrgCode());
            }
        }
//        nextNodeParamVo.setAcountId(userVo.get("id"));
//        nextNodeParamVo.setAcountName(userVo.get("name"));
//        nextNodeParamVo.setOrgId(userVo.get("orgCode"));
        nodeParamVoList.add(nextNodeParamVo);
        Map<String, String> processVariables = new HashMap<>();
        bpmProcessParamForm.setBpmProcessTaskForm(bpmProcessTaskFormApprove);
        bpmProcessParamForm.setNextNodeParamVo(nodeParamVoList);
        bpmProcessParamForm.setProcessVariables(processVariables);
        return bpmOperatorFeignService.rollBackProcess(bpmProcessParamForm);
    }

    /**
     * 获取处室负责人
     */
    public ArrayList<NextNodeParamVo> getDutyPerson(Map<String, String> processVariables) {
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
        ArrayList<NextNodeParamVo> nextNodeParamVos = new ArrayList<>();
        NextNodeParamVo nextNodeParamVo = new NextNodeParamVo();
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
                String nextUrl = processVariables.get("nextUrl");
                nextNodeParamVo.setAcountId(id);
                nextNodeParamVo.setAcountName(name);
                nextNodeParamVo.setObjectUrl(nextUrl);
                nextNodeParamVos.add(nextNodeParamVo);
            } else {
                throw new ServiceException(OaErrorType.DUTYINFO_ERROR);
            }
        }
        return nextNodeParamVos;
    }

    @Override
    public Result getHistoryList(String processInstanceId, String taskKey) {
        BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
        bpmProcessTaskQueryForm.setProcessInstanceId(processInstanceId);
        bpmProcessTaskQueryForm.setProcessDefineKey(this.PROCESS_DEFINE_KEY);
        Result<List<BpmProcessTaskVo>> re = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
        List<BpmProcessTaskVo> bpmList = new ArrayList<>();
        List<BpmProcessTaskVo> dataList = re.getData();
        //对审批历史去重
        List<BpmProcessTaskVo> list = dataList.stream().
                collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getTaskKey() + ";" + o.getApproverAcount()))), ArrayList::new));
        for (BpmProcessTaskVo bpmProcessTaskVo : list) {
            if ("business_approval".equals(taskKey)) {
                if ("start".equals(bpmProcessTaskVo.getTaskKey())) {
                    bpmList.add(bpmProcessTaskVo);
                }
            }
            if ("lead_approval".equals(taskKey)) {
                if ("start".equals(bpmProcessTaskVo.getTaskKey()) || "business_approval".equals(bpmProcessTaskVo.getTaskKey())) {
                    bpmList.add(bpmProcessTaskVo);
                }
            }
            if ("charge_leader_approval".equals(taskKey)) {
                if ("start".equals(bpmProcessTaskVo.getTaskKey()) || "business_approval".equals(bpmProcessTaskVo.getTaskKey())
                        || "lead_approval".equals(bpmProcessTaskVo.getTaskKey())) {
                    bpmList.add(bpmProcessTaskVo);
                }
            }
            if ("news_center_editor".equals(taskKey)) {
                if ("start".equals(bpmProcessTaskVo.getTaskKey()) || "business_approval".equals(bpmProcessTaskVo.getTaskKey())
                        || "lead_approval".equals(bpmProcessTaskVo.getTaskKey()) || "charge_leader_approval".equals(bpmProcessTaskVo.getTaskKey())) {
                    bpmList.add(bpmProcessTaskVo);
                }
            }
            if ("news_center_approval".equals(taskKey)) {
                if ("start".equals(bpmProcessTaskVo.getTaskKey()) || "business_approval".equals(bpmProcessTaskVo.getTaskKey())
                        || "lead_approval".equals(bpmProcessTaskVo.getTaskKey()) || "charge_leader_approval".equals(bpmProcessTaskVo.getTaskKey())
                        || "news_center_editor".equals(bpmProcessTaskVo.getTaskKey())) {
                    bpmList.add(bpmProcessTaskVo);
                }
            }
            if ("leader_approval".equals(taskKey)) {
                if ("start".equals(bpmProcessTaskVo.getTaskKey()) || "business_approval".equals(bpmProcessTaskVo.getTaskKey())
                        || "lead_approval".equals(bpmProcessTaskVo.getTaskKey()) || "charge_leader_approval".equals(bpmProcessTaskVo.getTaskKey())
                        || "news_center_editor".equals(bpmProcessTaskVo.getTaskKey()) || "news_center_approval".equals(bpmProcessTaskVo.getTaskKey())) {
                    bpmList.add(bpmProcessTaskVo);
                }
            }
        }
        return Result.success(bpmList);
    }

    /**
     * 去重
     */
    private static List<BpmProcessTaskVo> removeDuplicate(List<BpmProcessTaskVo> bpmList) {
        Set<BpmProcessTaskVo> set = new TreeSet<BpmProcessTaskVo>(new Comparator<BpmProcessTaskVo>() {
            @Override
            public int compare(BpmProcessTaskVo a, BpmProcessTaskVo b) {
                int compareToResult = 1;//==0表示重复
                if (StringUtils.equals(a.getTaskKey(), b.getTaskKey()) &&
                        StringUtils.equals(a.getApproverAcount(), b.getApproverAcount())) {
                    compareToResult = 0;
                }
                return compareToResult;
            }
        });
        set.addAll(bpmList);
        return new ArrayList<BpmProcessTaskVo>(set);
    }

    /**
     * 查询下一节点的负责人
     *
     * @return
     */
    @Override
    public Result buildBpmForm(Map<String, String> processVariables) {
        ArrayList<NextNodeParamVo> nodeParamVoList = new ArrayList<>();
        //构建审批人参数对象
        BpmApprovalMatrixQueryFormForApproval nextMatrixForm = new BpmApprovalMatrixQueryFormForApproval();
        nextMatrixForm.setTaskID(processVariables.get("taskId"));
        nextMatrixForm.setChargeOrg(processVariables.get("chargeOrg"));
        nextMatrixForm.setChargeBusiness(CHARGE_BUSINESS);
        nextMatrixForm.setProcessDefineKey(PROCESS_DEFINE_KEY);
        nextMatrixForm.setProcessVariables(processVariables);
        Result<List<BpmApprovalMatrixVo>> nextApprover = bpmApprovalMatrixFeignService.findNextApprover(nextMatrixForm);
        if (nextApprover.isSuccess()) {
            List<BpmApprovalMatrixVo> dataList = nextApprover.getData();
            if (!CollectionUtils.isEmpty(dataList)) {
                for (BpmApprovalMatrixVo matrixVo : dataList) {
                    NextNodeParamVo nodeParamVo = new NextNodeParamVo();
                    nodeParamVo.setAcountName(matrixVo.getAccountName());
                    nodeParamVo.setAcountId(matrixVo.getAccountNum());
//                    nodeParamVo.setOrgId(matrixVo.getOrgCode());
//                    nodeParamVo.setStationId(matrixVo.getPosition());
                    nodeParamVo.setObjectUrl(processVariables.get("nextUrl"));
                    nodeParamVoList.add(nodeParamVo);
                }
            }
        } else {
            throw new ServiceException(OaErrorType.BPM_NEXT_APPROVER_ERROR, nextApprover.getMesg());
        }
        //最后一个节点：如果没有审批人 加一个空对象
        if (nodeParamVoList.size() == 0) {
            nodeParamVoList.add(new NextNodeParamVo());
        }
        return Result.success(nodeParamVoList);

    }

    /**
     * 生成流水号
     *
     * @return
     */
    private String getOrderNum() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String date = sdf.format(new Date());
        String head = "XWFB" + date + "%";
        String num = "000" + (Integer.valueOf(newsMapper.getOrderNum(head)) + 1);
        return "XWFB" + date + num.substring(num.length() - 4);
    }

    /**
     * 更新流水号
     *
     * @return
     */
    private void updateOrderNum(String objectId, String orderNum) {
        News news = new News();
        news.setNewsId(Long.valueOf(objectId));
        news.setOrderNum(orderNum);
        newsMapper.updateById(news);
    }

    /**
     * 更新新闻状态
     *
     * @return
     */
    private void updateNewsStatus(String objectId) {
        News news = new News();
        news.setNewsId(Long.valueOf(objectId));
        news.setNewsStatus("draft");
        newsMapper.updateById(news);
    }

    /**
     * 当下一个节点的审批人由前端传入时，调用该方法。
     *
     * @return
     */
    private Result<ArrayList<NextNodeParamVo>> receiveBpmForm(List<ApprovalMatrixVo> nextMatriList, Map<String, String> map) {
        ArrayList<NextNodeParamVo> nodeParamVos = new ArrayList<>();
        for (ApprovalMatrixVo approvalMatrixVo : nextMatriList) {
            NextNodeParamVo nextNode = new NextNodeParamVo();
            nextNode.setAcountId(approvalMatrixVo.getAccountNum());
            nextNode.setAcountName(approvalMatrixVo.getAccountName());
//            nextNode.setOrgId(approvalMatrixVo.getOrgCode());
            nextNode.setObjectUrl(map.get("nextUrl"));
            nodeParamVos.add(nextNode);
        }
        return Result.success(nodeParamVos);
    }
}
