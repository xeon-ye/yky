package com.deloitte.services.processcenter.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmConductListQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryWrapper;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessParamVo;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.oaservice.applycenter.param.SendProcessTaskQueryForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.BpmProcessVo;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.applycenter.entity.SendProcessTask;
import com.deloitte.services.applycenter.service.ISendProcessTaskService;
import com.deloitte.services.processcenter.entity.ProcessCenterDto;
import com.deloitte.services.processcenter.service.ProcessCenterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-03-26
 * @Description :  bpm服务实现类
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class ProcessCenterServiceImpl implements ProcessCenterService {

    @Autowired
    private BpmProcessTaskFeignService bpmProcessTaskFeignService;

    @Autowired
    private ISendProcessTaskService sendProcessTaskService;

    /**
     * 流程中心模块（即流程中心按钮）
     *
     * @param processCenterDto
     * @return 返回所有模块的待办事宜、以及其他模块的total。当点击其他模块的时候，根据modelType,显示其他模块的具体信息
     */
    @Override
    public Result<BpmProcessParamVo> search(ProcessCenterDto processCenterDto) {
        BpmProcessParamVo bpmProcessParamVo = new BpmProcessParamVo();
        BpmConductListQueryForm bpmConductListQueryForm = new BpmConductListQueryForm();
        bpmConductListQueryForm.setApproverAcount(processCenterDto.getUserId());
        bpmConductListQueryForm.setCurrentPage(processCenterDto.getCurrentPage());
        bpmConductListQueryForm.setPageSize(processCenterDto.getPageSize());
        if (StringUtils.isNotBlank(processCenterDto.getSourceSystem())) {
            bpmConductListQueryForm.setSourceSystem(processCenterDto.getSourceSystem());
        }
        bpmConductListQueryForm.setObjectDescription(processCenterDto.getObjectDescription());
        //其他按钮
//        if ("otherSystem".equals(processCenterDto.getSourceSystem())) {
//            bpmConductListQueryForm.setSourceSystem("not in(srpms,FSSC,OA,HR)");
//            //默认待办事宜info
//            Result<GdcPage<BpmProcessTaskVo>> backLog = bpmProcessTaskFeignService.searchBackLog(bpmConductListQueryForm);
//            GdcPage<BpmProcessTaskVo> backLogData = backLog.getData();
//            bpmProcessParamVo.setBpmProcessTaskVo(backLogData);
//
//            //已办事宜total
//            Result<GdcPage<BpmProcessTaskVo>> done = bpmProcessTaskFeignService.searchDone(bpmConductListQueryForm);
//            GdcPage<BpmProcessTaskVo> doneData = done.getData();
//            long doneTotal;
//            if (doneData != null) {
//                doneTotal = doneData.getTotal();
//                bpmProcessParamVo.setDoneTotal(doneTotal);
//            }
//
//            //办结total
//            bpmConductListQueryForm.setObjectStauts("已通过");
//            bpmConductListQueryForm.setTaskKey("start");
//            Result<GdcPage<BpmProcessTaskVo>> end = bpmProcessTaskFeignService.searchDone(bpmConductListQueryForm);
//            GdcPage<BpmProcessTaskVo> endData = end.getData();
//            long endTotal;
//            if (endData != null) {
//                endTotal = endData.getTotal();
//                bpmProcessParamVo.setEndTotal(endTotal);
//            }
//
//            //抄送total
//            SendProcessTaskQueryForm queryForm = new SendProcessTaskQueryForm();
//            queryForm.setTaskStauts("待阅");
//            queryForm.setSourceSystem("otherSystem");
//            queryForm.setApproverAcount(processCenterDto.getUserId());
//            IPage<SendProcessTask> send = sendProcessTaskService.selectPage(queryForm);
//            IPage<SendProcessTaskVo> sendTaskPage = new BeanUtils<SendProcessTaskVo>().copyPageObjs(send, SendProcessTaskVo.class);
//            IPage<BpmProcessTaskVo> sendTaskVoPage = new BeanUtils<BpmProcessTaskVo>().copyPageObjs(sendTaskPage, BpmProcessTaskVo.class);
//            GdcPage<BpmProcessTaskVo> sendData = new GdcPage<>(sendTaskVoPage);
//            long sendTotal;
//            if (sendData != null) {
//                sendTotal = sendData.getTotal();
//                bpmProcessParamVo.setSendTotal(sendTotal);
//            }
//
//            //我的请求total
//            bpmConductListQueryForm.setObjectStauts(null);
//            Result<GdcPage<BpmProcessTaskVo>> myReq = bpmProcessTaskFeignService.searchDone(bpmConductListQueryForm);
//            GdcPage<BpmProcessTaskVo> myReqData = myReq.getData();
//            long myReqTotal;
//            if (myReqData != null) {
//                myReqTotal = myReqData.getTotal();
//                bpmProcessParamVo.setMyReqTotal(myReqTotal);
//            }
//            this.getData(bpmProcessParamVo, endData, doneData, sendData, myReqData, processCenterDto.getModelType());
//            return Result.success(bpmProcessParamVo);
//        }

        //默认待办事宜info
        if ("otherSystem".equals(processCenterDto.getSourceSystem())) {
            bpmConductListQueryForm.setSourceSystem("not in(srpms,FSSC,oaservice,OA,HR)");
        }
        if ("OA".equals(processCenterDto.getSourceSystem())) {
            bpmConductListQueryForm.setSourceSystem("in(oaservice,OA)");
        }
        Result<GdcPage<BpmProcessTaskVo>> backLog = bpmProcessTaskFeignService.searchBackLog(bpmConductListQueryForm);
        GdcPage<BpmProcessTaskVo> backLogDataPage = backLog.getData();
        IPage<BpmProcessVo> bpmProcessVo = new BeanUtils<BpmProcessVo>().copyPageObjs(backLogDataPage, BpmProcessVo.class);
        GdcPage<BpmProcessVo> backLogData = new GdcPage<>(bpmProcessVo);
        bpmProcessParamVo.setBpmProcessTaskVo(backLogData);

        //流程中心模块已办事宜total
        if ("otherSystem".equals(processCenterDto.getSourceSystem())) {
            bpmConductListQueryForm.setSourceSystem("not in(srpms,FSSC,oaservice,OA,HR)");
        }
        if ("OA".equals(processCenterDto.getSourceSystem())) {
            bpmConductListQueryForm.setSourceSystem("in(oaservice,OA)");
        }
        Result<GdcPage<BpmProcessTaskVo>> done = bpmProcessTaskFeignService.searchDone(bpmConductListQueryForm);
        GdcPage<BpmProcessTaskVo> doneDataPage = done.getData();
        IPage<BpmProcessVo> objectIPage = new BeanUtils<BpmProcessVo>().copyPageObjs(doneDataPage, BpmProcessVo.class);
        GdcPage<BpmProcessVo> doneData = new GdcPage<>(objectIPage);
        long doneTotal;
        if (doneData != null) {
            doneTotal = doneData.getTotal();
            bpmProcessParamVo.setDoneTotal(doneTotal);
        }
        //流程中心办结事宜total
        BpmProcessTaskQueryWrapper endWrapper = new BpmProcessTaskQueryWrapper();
        Map<String, String[]> endQueryMap = new HashMap<>();
        String[] ss1 = {"eq", String.valueOf(processCenterDto.getUserId())};
        String[] ss2 = {"eq", "已提交"};
        String[] ss3 = {"eq", "已通过"};
        String[] ss4 = {"eq", processCenterDto.getSourceSystem()};
        String[] ss5 = {"notIn", "srpms,FSSC"};
        endQueryMap.put(BpmProcessTaskQueryForm.APPROVER_ACOUNT, ss1);
        endQueryMap.put(BpmProcessTaskQueryForm.TASK_STAUTS, ss2);
        endQueryMap.put(BpmProcessTaskQueryForm.OBJECT_STAUTS, ss3);
        if (StringUtils.isNotBlank(processCenterDto.getSourceSystem())) {
            if("otherSystem".equals(processCenterDto.getSourceSystem())){
                endQueryMap.put(BpmProcessTaskQueryForm.SOURCE_SYSTEM, new String[]{"notIn","srpms,FSSC,oaservice,OA,HR"});
            } else if ("OA".equals(processCenterDto.getSourceSystem())) {
                endQueryMap.put(BpmProcessTaskQueryForm.SOURCE_SYSTEM, new String[]{"in","oaservice,OA"});
            } else {
                endQueryMap.put(BpmProcessTaskQueryForm.SOURCE_SYSTEM, ss4);
            }
        } else {
            endQueryMap.put(BpmProcessTaskQueryForm.SOURCE_SYSTEM, ss5);
        }
        endWrapper.setWrapperMap(endQueryMap);
        endWrapper.setCurrentPage(processCenterDto.getCurrentPage());
        endWrapper.setPageSize(processCenterDto.getPageSize());
        Result<GdcPage<BpmProcessTaskVo>> taskVopage = bpmProcessTaskFeignService.searchByWrapper(endWrapper);
        GdcPage<BpmProcessTaskVo> endDataPage = taskVopage.getData();
        IPage<BpmProcessVo> bpmProcessVoIPage = new BeanUtils<BpmProcessVo>().copyPageObjs(endDataPage, BpmProcessVo.class);
        GdcPage<BpmProcessVo> endData = new GdcPage<>(bpmProcessVoIPage);
        long endTotal;
        if (endData != null) {
            endTotal = endData.getTotal();
            bpmProcessParamVo.setEndTotal(endTotal);
        }
        if ("srpms".equals(processCenterDto.getSourceSystem()) || "FSSC".equals(processCenterDto.getSourceSystem())) {
            bpmProcessParamVo.setEndTotal(0);
            endData = new GdcPage<>();
        }
        //流程中心中的抄送total
        SendProcessTaskQueryForm taskQueryForm = new SendProcessTaskQueryForm();
        taskQueryForm.setCurrentPage(processCenterDto.getCurrentPage());
        taskQueryForm.setPageSize(processCenterDto.getPageSize());
        taskQueryForm.setApproverAcount(processCenterDto.getUserId());
        taskQueryForm.setSourceSystem(processCenterDto.getSourceSystem());
        taskQueryForm.setObjectDescription(processCenterDto.getObjectDescription());
        taskQueryForm.setTaskStauts("待阅");
        if (StringUtils.isNotBlank(processCenterDto.getSourceSystem())) {
            if("otherSystem".equals(processCenterDto.getSourceSystem())){
                taskQueryForm.setSourceSystem("otherSystem");
            } else if ("OA".equals(processCenterDto.getSourceSystem())) {
                taskQueryForm.setSourceSystem("OA");
            } else {
                taskQueryForm.setSourceSystem(processCenterDto.getSourceSystem());
            }
        }
        IPage<SendProcessTask> sendPage = sendProcessTaskService.selectPage(taskQueryForm);
        IPage<SendProcessTaskVo> sendTaskPage = new BeanUtils<SendProcessTaskVo>().copyPageObjs(sendPage, SendProcessTaskVo.class);
        IPage<BpmProcessVo> sendProcessTaskVoPage = new BeanUtils<BpmProcessVo>().copyPageObjs(sendTaskPage, BpmProcessVo.class);
        GdcPage<BpmProcessVo> sendData = new GdcPage<>(sendProcessTaskVoPage);
        long sendTotal;
        if (sendData != null) {
            sendTotal = sendData.getTotal();
            bpmProcessParamVo.setSendTotal(sendTotal);
        }
        if ("srpms".equals(processCenterDto.getSourceSystem())) {
            bpmProcessParamVo.setSendTotal(0);
            sendData = new GdcPage<>();
        }
        //流程中心下的我的请求total
        BpmProcessTaskQueryForm queryForm = new BpmProcessTaskQueryForm();
        //第一个节点的审批人就是提交人
        queryForm.setApproverAcount(processCenterDto.getUserId());
        queryForm.setTaskStauts("已提交");
        queryForm.setCurrentPage(processCenterDto.getCurrentPage());
        queryForm.setPageSize(processCenterDto.getPageSize());
        if (StringUtils.isNotBlank(processCenterDto.getSourceSystem())) {
            if("otherSystem".equals(processCenterDto.getSourceSystem())){
                queryForm.setSourceSystem("not in(srpms,FSSC,oaservice,OA,HR)");
            } else if ("OA".equals(processCenterDto.getSourceSystem())) {
                queryForm.setSourceSystem("in(oaservice,OA)");
            } else{
                queryForm.setSourceSystem(processCenterDto.getSourceSystem());
            }
        } else {
            queryForm.setSourceSystem("not in(srpms)");
        }
        Result<GdcPage<BpmProcessTaskVo>> search = bpmProcessTaskFeignService.search(queryForm);
        GdcPage<BpmProcessTaskVo> myReqDataPage = search.getData();
        IPage<BpmProcessVo> page = new BeanUtils<BpmProcessVo>().copyPageObjs(myReqDataPage, BpmProcessVo.class);
        GdcPage<BpmProcessVo> myReqData = new GdcPage<>(page);
        long myReqTotal;
        if (myReqData != null) {
            myReqTotal = myReqData.getTotal();
            bpmProcessParamVo.setMyReqTotal(myReqTotal);
        }
        if ("srpms".equals(processCenterDto.getSourceSystem())) {
            bpmProcessParamVo.setMyReqTotal(0);
            myReqData = new GdcPage<>();
        }
        this.getData(bpmProcessParamVo, endData, doneData, sendData, myReqData, processCenterDto.getModelType());
        return Result.success(bpmProcessParamVo);
    }

    private void getData(BpmProcessParamVo bpmProcessParamVo, GdcPage<BpmProcessVo> endData, GdcPage<BpmProcessVo> doneData,
                         GdcPage<BpmProcessVo> sendData, GdcPage<BpmProcessVo> myReqData, String modelType) {
        //流程中心模块的已办事宜按钮
        if ("done".equals(modelType)) {
            bpmProcessParamVo.setBpmProcessTaskVo(doneData);
        }
        //流程中心模块的办结事宜按钮
        if ("end".equals(modelType)) {
            bpmProcessParamVo.setBpmProcessTaskVo(endData);
        }
        //流程中心模块的抄送按钮
        if ("send".equals(modelType)) {
            bpmProcessParamVo.setBpmProcessTaskVo(sendData);
        }
        //流程中心模块的我的请求按钮
        if ("myReq".equals(modelType)) {
            bpmProcessParamVo.setBpmProcessTaskVo(myReqData);
        }
    }

}
