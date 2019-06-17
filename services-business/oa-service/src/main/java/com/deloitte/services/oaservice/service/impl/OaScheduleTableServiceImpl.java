package com.deloitte.services.oaservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.param.OaScheduleTableQueryForm;
import com.deloitte.platform.api.oaservice.param.OaScheduleTableQueryParam;
import com.deloitte.platform.api.oaservice.param.OaWorkflowDriverForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.oa.util.OABPMConstantUtil;
import com.deloitte.services.oaservice.entity.OaScheduleTable;
import com.deloitte.services.oaservice.mapper.OaScheduleTableMapper;
import com.deloitte.services.oaservice.service.IOaBpmFlowService;
import com.deloitte.services.oaservice.service.IOaScheduleTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
/**
 * @Author : fuqiao
 * @Date : Create in 2019-03-26
 * @Description :  OaScheduleTable服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaScheduleTableServiceImpl extends ServiceImpl<OaScheduleTableMapper, OaScheduleTable> implements IOaScheduleTableService {


    @Autowired
    private OaScheduleTableMapper oaScheduleTableMapper;

    @Autowired
    private IOaBpmFlowService oaBpmFlowService;

    @Override
    public IPage<OaScheduleTable> selectPage(OaScheduleTableQueryForm queryForm ) {
        QueryWrapper<OaScheduleTable> queryWrapper = new QueryWrapper <OaScheduleTable>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaScheduleTableMapper.selectPage(new Page<OaScheduleTable>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OaScheduleTable> selectList(OaScheduleTableQueryForm queryForm) {
        QueryWrapper<OaScheduleTable> queryWrapper = new QueryWrapper <OaScheduleTable>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaScheduleTableMapper.selectList(queryWrapper);
    }

    @Override
    public List<OaScheduleTable> getScheduleByBusinessId(String businessId, OaScheduleTableQueryParam param) {
        return oaScheduleTableMapper.getScheduleByBusinessId(businessId,param);
    }

    @Override
    public boolean removeByBusinessIdAndRowNum(String businessId,int rowNum){
        int rs = oaScheduleTableMapper.removeByBusinessIdAndRowNum(businessId,rowNum);
        return rs > 0 ? true : false;
    }


    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     *  */
    public QueryWrapper<OaScheduleTable> getQueryWrapper(QueryWrapper<OaScheduleTable> queryWrapper,OaScheduleTableQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getBusinessId())){
            queryWrapper.eq(OaScheduleTable.BUSINESS_ID,queryForm.getBusinessId());
        }
        if(StringUtils.isNotBlank(queryForm.getWorkType())){
            queryWrapper.eq(OaScheduleTable.WORK_TYPE,queryForm.getWorkType());
        }
        if(StringUtils.isNotBlank(queryForm.getWorkName())){
            queryWrapper.eq(OaScheduleTable.WORK_NAME,queryForm.getWorkName());
        }
        if(StringUtils.isNotBlank(queryForm.getWorkDesc())){
            queryWrapper.eq(OaScheduleTable.WORK_DESC,queryForm.getWorkDesc());
        }
        if(StringUtils.isNotBlank(queryForm.getWorkStatus())){
            if(queryForm.getWorkStatus().indexOf(",")>0){
                String[] workStatusArr = queryForm.getWorkStatus().split(",");
                queryWrapper.in(OaScheduleTable.WORK_STATUS,workStatusArr);
            }else{
                queryWrapper.eq(OaScheduleTable.WORK_STATUS,queryForm.getWorkStatus());
            }
        }
        if(StringUtils.isNotBlank(queryForm.getShowFlag())){
            queryWrapper.eq(OaScheduleTable.SHOW_FLAG,queryForm.getShowFlag());
        }
        if(StringUtils.isNotBlank(queryForm.getUserId())){
            if("share".equals(queryForm.getExt1())){
                queryWrapper.and(wrapper  -> wrapper.eq(OaScheduleTable.USER_ID,queryForm.getUserId()).or()
                        .exists("select 1 from OA_SCHEDULE_SHARE_CONFIG c where c.user_id = OA_SCHEDULE_TABLE.user_id and c.enable_flag = 'Y' and c.share_to_id = '"+queryForm.getUserId()+"'")
                );
            }else {
                queryWrapper.eq(OaScheduleTable.USER_ID,queryForm.getUserId());
            }
        }
        if(StringUtils.isNotBlank(queryForm.getUserName())){
            queryWrapper.eq(OaScheduleTable.USER_NAME,queryForm.getUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptId())){
            queryWrapper.eq(OaScheduleTable.DEPT_ID,queryForm.getDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptName())){
            queryWrapper.eq(OaScheduleTable.DEPT_NAME,queryForm.getDeptName());
        }
        if(queryForm.getStartTime()!=null){
            queryWrapper.ge(OaScheduleTable.START_TIME,queryForm.getStartTime());
        }
        if(queryForm.getEndTime()!=null){
            queryWrapper.le(OaScheduleTable.START_TIME,queryForm.getEndTime());
        }
        if(!org.springframework.util.StringUtils.isEmpty(queryForm.getCreateBy())){
            queryWrapper.eq(OaScheduleTable.CREATE_BY, queryForm.getCreateBy());
        }
        if(!org.springframework.util.StringUtils.isEmpty(queryForm.getCreateTime())){
            queryWrapper.eq(OaScheduleTable.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(OaScheduleTable.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(!org.springframework.util.StringUtils.isEmpty(queryForm.getUpdateTime())){
            queryWrapper.eq(OaScheduleTable.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())&&!"share".equals(queryForm.getExt1())){
            queryWrapper.eq(OaScheduleTable.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getPersonType())){
            queryWrapper.eq(OaScheduleTable.PERSON_TYPE,queryForm.getPersonType());
        }
        if(StringUtils.isNotBlank(queryForm.getNoticeType())){
            queryWrapper.eq(OaScheduleTable.NOTICE_TYPE,queryForm.getNoticeType());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(OaScheduleTable.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(OaScheduleTable.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(OaScheduleTable.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(OaScheduleTable.EXT5,queryForm.getExt5());
        }
        return queryWrapper;
    }

    public boolean submitApply(OaWorkflowDriverForm taskForm, ArrayList<NextNodeParamVo> nextNodeParamVo, String filterIds, UserVo user, DeptVo dept){
        String flowStatus = "process";
        Result result = oaBpmFlowService.driveProcess(taskForm,nextNodeParamVo,user,dept);
        if(result.isSuccess()) {
            if ("审批结束".equals(result.getData())) {
                //表示流程结束
                flowStatus = "end";
            }
            if (OABPMConstantUtil.OPTION_TYPE_REJECT_TO_START.equals(taskForm.getOptionType())) {
                //退回发起人
                flowStatus = "draft";
            }
            if (OABPMConstantUtil.OPTION_TYPE_REJECT.equals(taskForm.getOptionType())) {
                JSONObject resultObj = JSONObject.parseObject(JSONObject.toJSONString(result.getData()));
                if("start".equals(resultObj.getString("taskKey"))){
                    flowStatus = "draft";
                }
            }
            updateStatusAfterSubmit(taskForm.getObjectOrderNum(),taskForm.getObjectType(), filterIds, flowStatus);
            return true;
        }else{
            return false;
        }
    }

    public void updateStatusAfterSubmit(String businessId,String workType,String filterIds,String flowStaus){
        //提交时，S==>O   filterIds 状态不变;
        String newFilters = filterIds;
        String oldStatus = "S";
        String newStatus = "O";
        if("draft".equals(flowStaus)){
            //流程退回起草
            oldStatus = "O";
            newStatus = "S";
            newFilters = "";
        }else if("end".equals(flowStaus)){
            //流程结束：C,O ==> A
            oldStatus = "'C','O'";
            newStatus = "A";
            newFilters = "";
        }else{
            //流程提交：C,O ==> A
            oldStatus = "S";
            newStatus = "O";
        }
        String startTime = "";
        String endTime = "";
        if("院校日历".equals(workType)){
            startTime = businessId!=null?"":businessId.split("~")[0];
            endTime = businessId!=null?"":businessId.split("~")[1];
            oaScheduleTableMapper.updateStatusAfterSubmit("",startTime,endTime,workType,newFilters,oldStatus,newStatus);
        }else{
            oaScheduleTableMapper.updateStatusAfterSubmit(businessId,startTime,endTime,workType,newFilters,oldStatus,newStatus);
        }

    }
}

