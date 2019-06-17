package com.deloitte.platform.api.oaservice.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.param.OaScheduleTableQueryForm;
import com.deloitte.platform.api.oaservice.param.OaScheduleTableQueryParam;
import com.deloitte.platform.api.oaservice.param.OaWorkflowParamForm;
import com.deloitte.platform.api.oaservice.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-03-26
 * @Description :  OaScheduleTable控制器接口
 * @Modified :
 */
public interface OaScheduleTableClient {

    public static final String path="/oaservice/schedule";


    /**
     *  POST---新增
     * @param oaScheduleTableForm
     * @return
     */
    @PostMapping(value = "/other/schedule")
    Result addFormOtherSys(@Valid @ModelAttribute OaScheduleTableForm oaScheduleTableForm);
    /**
     *  POST---新增
     * @param oaScheduleTableForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OaScheduleTableForm oaScheduleTableForm);

    /**
     *  POST---领导视图批量新增
     * @param oaScheduleTableLeadForms
     * @return
     */
    @PostMapping(value = path+"/weekview/add")
    Result addLeaderSchedule(@Valid @ModelAttribute OaScheduleTableLeadForm[] oaScheduleTableLeadForms);

    /**
     *  POST---领导视图批量新增
     * @param oaScheduleTableForms
     * @return
     */
    @PostMapping(value = path+"/monthview/update")
    Result updateMonthSchedule(@Valid @RequestBody @ApiParam(name="oaScheduleTableForms",value="更新月视图OaScheduleTable的form表单",required=true) OaScheduleTableForm[] oaScheduleTableForms);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") String id);

    /**
     *  Delete---删除
     * @param businessId
     * @param rowNum
     * @return
     */
    @DeleteMapping(value = path+"/row/{businessId}/{rowNum}")
    Result deleteRow(@PathVariable String businessId,@PathVariable int rowNum);

    /**
     *  Patch----部分更新
     * @param  id, oaScheduleTableForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") String id, @Valid @RequestBody OaScheduleTableForm oaScheduleTableForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OaScheduleTableVo> get(@PathVariable(value="id") String id);

    /**
     *  GET----根据businessId获取当前的日程安排
     * @param  businessId
     * @param  param
     * @return
     */
    @PostMapping(value = path+"/allSchedule/{businessId}")
    Result<List<OaScheduleTableVo>> getScheduleByBusinessId(@PathVariable(value="businessId") String businessId,@RequestBody OaScheduleTableQueryParam param);

    /**
     *  GET----根据businessId获取领导的日程安排
     * @param  businessId
     * @param  param
     * @return
     */
    @PostMapping(value = path+"/weekViewSchedule/{businessId}")
    Result<List<OaScheduleTableLeadVO>> getWeekViewScheduleByBusinessId(@PathVariable(value="businessId") String businessId,@RequestBody OaScheduleTableQueryParam param);


    /**
     *  POST----列表查询
     * @param   oaScheduleTableQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OaScheduleMonthVo>> list(@Valid @RequestBody OaScheduleTableQueryForm oaScheduleTableQueryForm);


    /**
     *  POST----复杂查询
     * @param  oaScheduleTableQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OaScheduleTableVo>> search(@Valid @RequestBody OaScheduleTableQueryForm oaScheduleTableQueryForm);

    /**
     *  POST----流程提交
     * @param  taskForm
     * @return
     */
    @PostMapping(value = path+"/submit")
    Result submit(@Valid @RequestBody OaWorkflowParamForm taskForm);

}
