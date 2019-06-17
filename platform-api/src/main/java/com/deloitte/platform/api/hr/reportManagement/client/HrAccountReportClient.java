package com.deloitte.platform.api.hr.reportManagement.client;

import com.deloitte.platform.api.hr.reportManagement.param.HrAccountReportQueryForm;
import com.deloitte.platform.api.hr.reportManagement.vo.HrAccountReportForm;
import com.deloitte.platform.api.hr.reportManagement.vo.HrAccountReportVo;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrEmployeeBaseVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-08
 * @Description :  HrAccount控制器接口
 * @Modified :
 */

public interface HrAccountReportClient {

    public static final String path="/hr/reportManagement/hr-account";


    /**
     *  POST---新增
     * @param hrAccountReportForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrAccountReportForm hrAccountReportForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrAccountReportForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody HrAccountReportForm hrAccountReportForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrAccountReportVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   hrAccountReportQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrAccountReportVo>> list(@Valid @RequestBody HrAccountReportQueryForm hrAccountReportQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrAccountReportQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrAccountReportVo>> search(@Valid @RequestBody HrAccountReportQueryForm hrAccountReportQueryForm);


    /**
     * 获取员工信息
     * @param id
     * @return
     */
    @GetMapping(value = path+"/employ/{id}")
    Result<HrEmployeeBaseVo> getEmployee(@PathVariable(value="id") long id);

    /**
     * 获取应聘信息
     * @param id
     * @return
     */
    @GetMapping(value = path+"/apply/{id}")
    Result getInformation(@PathVariable(value="id") long id);

}
