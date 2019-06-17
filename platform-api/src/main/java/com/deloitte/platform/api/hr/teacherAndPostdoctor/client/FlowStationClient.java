package com.deloitte.platform.api.hr.teacherAndPostdoctor.client;

import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.FlowStationExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.FlowStationQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  FlowStation控制器接口
 * @Modified :
 */
public interface FlowStationClient {

    public static final String path="/hr/flow-station";


    /**
     *  POST---新增
     * @param flowStationForm
     * @return
     */
    @PostMapping(value = path+"/add")
    Result add(@Valid @ModelAttribute FlowStationForm flowStationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, flowStationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody FlowStationForm flowStationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<FlowStationVo> get(@PathVariable(value="id") long id);


    /**
     *  GET----查询流动站树形列表
     * @return
     */
    @GetMapping(value = path+"/list/getTree")
    Result<List<FlowStationTreeVo>> getTree();


    /**
     *  POST----复杂查询
     * @param  flowStationQueryForm
     * @return
     */
    @PostMapping(value = path+"/getFlowStationList")
    Result<IPage<FlowStationVo>> getFlowStationList(@Valid @RequestBody FlowStationQueryForm flowStationQueryForm);

    /**
     *  导出流动站信息
     *
     * @param flowStationExportForm
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/list/exportFlowStationList")
    void exportFlowStationList(@ModelAttribute FlowStationExportForm flowStationExportForm, HttpServletRequest request, HttpServletResponse response);

    /**
     *  设置流动站到期提醒人
     *
     * @param userCode  用户code
     * @param stationCode   流动站
     * @return
     */
    @PostMapping(value = path+"/addExpireUser")
    Result addExpireUser(@RequestParam String userCode, @RequestParam String stationCode,String effectiveDate,Integer isValid);

    /**
     *  根据一级学科code获取二级学科
     *
     * @param code
     * @return
     */
    @GetMapping(value = path+"/getDiscussMajorByPid")
    Result getDiscussMajorByPid(@RequestParam String code);

    /**
     *  分页查询流动站列表（到期提醒人列表）
     *
     * @param flowStationQueryForm
     * @return
     */
    @PostMapping(value = path+"/getExpireFlowStationList")
    Result<IPage<FlowStationVo>> getExpireFlowStationList(@Valid @RequestBody FlowStationQueryForm flowStationQueryForm);

    /**
     *  条件导出流动站列表（到期提醒人列表）
     *
     * @param flowStationExportForm
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportExpireFlowStationList")
    void exportExpireFlowStationList(@ModelAttribute FlowStationExportForm flowStationExportForm, HttpServletRequest request, HttpServletResponse response);

    /**
     *  删除设置流动站到期提醒人
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = path+"/deleteExpire/{id}")
    Result deleteExpire(@PathVariable(value="id") long id);

    /**
     * 根据ID获取到期提醒信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = path+"/getExpire/{id}")
    Result<FlowExpireVo> getExpire(@PathVariable long id);

    /**
     * 获取一级流动站下拉框
     *
     * @return
     */
    @GetMapping(value = path+"/getFlowStation")
    Result getFlowStation();

    /**
     * 更新流动站到期提醒人
     *
     * @param flowExpireForm
     * @return
     */
    @PostMapping(value = path+"/updateExpireUser")
    Result updateExpireUser(@RequestBody @Valid FlowExpireForm flowExpireForm);
}
