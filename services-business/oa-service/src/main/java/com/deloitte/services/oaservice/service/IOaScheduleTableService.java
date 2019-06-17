package com.deloitte.services.oaservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.param.OaScheduleTableQueryForm;
import com.deloitte.platform.api.oaservice.param.OaScheduleTableQueryParam;
import com.deloitte.platform.api.oaservice.param.OaWorkflowDriverForm;
import com.deloitte.services.oaservice.entity.OaScheduleTable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-03-26
 * @Description : OaScheduleTable服务类接口
 * @Modified :
 */
public interface IOaScheduleTableService extends IService<OaScheduleTable> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaScheduleTable>
     */
    IPage<OaScheduleTable> selectPage(OaScheduleTableQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaScheduleTable>
     */
    List<OaScheduleTable> selectList(OaScheduleTableQueryForm queryForm);

    /**
     *  通过businessId获取所有行程安排
     * @param businessId
     * @param param
     * @return List<OaScheduleTable>
     */
    List<OaScheduleTable> getScheduleByBusinessId(String businessId, OaScheduleTableQueryParam param);

    /**
     *  通过businessId和rowNum删除行
     * @param businessId
     * @param rowNum
     * @return boolean
     */
    boolean removeByBusinessIdAndRowNum(String businessId,int rowNum);

    boolean submitApply(OaWorkflowDriverForm taskForm, ArrayList<NextNodeParamVo> nextNodeParamVo, String filterIds, UserVo user, DeptVo dept);
}
