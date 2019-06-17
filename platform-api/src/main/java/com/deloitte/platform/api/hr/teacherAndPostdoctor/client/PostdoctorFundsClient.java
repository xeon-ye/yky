package com.deloitte.platform.api.hr.teacherAndPostdoctor.client;

import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorFundsExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorFundsQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorFundsRecordQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  PostdoctorFunds控制器接口
 * @Modified :
 */
public interface PostdoctorFundsClient {

    public static final String path="/hr/postdoctor-funds";


    /**
     *  POST---新增
     * @param postdoctorFundsForm
     * @return
     */
    @PostMapping(value = path+"/add")
    Result add(@Valid @ModelAttribute PostdoctorFundsForm postdoctorFundsForm);


    /**
     * 删除博士后项目经费录入信息
     * @param ids
     * @param type
     * @return
     */
    @PostMapping(value = path+"/delete")
    Result delete(@RequestParam String[] ids,@RequestParam Integer type);

    /**
     *  Patch----部分更新
     * @param  id, postdoctorFundsForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody PostdoctorFundsForm postdoctorFundsForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PostdoctorFundsVo> get(@PathVariable(value="id") long id);


    /**
     *  导出博士后项目经费录入信息列表
     *
     * @param   postdoctorFundsExportForm
     * @return
     */
    @GetMapping(value = path+"/exportPostdoctorFundsList")
    void exportPostdoctorFundsList(@ModelAttribute PostdoctorFundsExportForm postdoctorFundsExportForm, HttpServletRequest request, HttpServletResponse response);


    /**
     *  分页条件查询博士后项目经费录入信息列表
     *
     * @param  postdoctorFundsQueryForm  条件查询
     * @return
     */
    @PostMapping(value = path+"/getPostdoctorFundsList")
    Result<IPage<PostdoctorFundsVo>> getPostdoctorFundsList(@Valid @RequestBody PostdoctorFundsQueryForm postdoctorFundsQueryForm);

    /**
     *  新增博士后经费使用记录报销录入
     *
     * @param postdoctorFundsRecordForm   经费使用记录
     * @return
     */
    @PostMapping(value = path+"/addRecord")
    Result addRecord(@Valid @RequestBody PostdoctorFundsRecordForm postdoctorFundsRecordForm);

    /**
     *  删除经费使用记录信息
     *
     * @param id  使用记录id
     * @return
     */
    @DeleteMapping(value = path+"/deleteRecord/{id}")
    Result deleteRecord(@PathVariable(value="id") long id);

    /**
     *  修改经费使用记录
     *
     * @param id   使用记录ID
     * @param postdoctorFundsRecordForm  修改经费使用记录信息的form表单
     * @return
     */
    @PatchMapping(value = path+"/updateRecord/{id}")
    Result updateRecord(@PathVariable(value="id") long id, @Valid @RequestBody PostdoctorFundsRecordForm postdoctorFundsRecordForm);

    /**
     *  分页条件查询经费使用记录信息列表
     *
     * @param postdoctorFundsQueryForm  条件查询
     * @return
     */
    @PostMapping(value = path+"/getPostdoctorFundsSurplusList")
    Result<IPage<PostdoctorFundsVo>> getPostdoctorFundsSurplusList(@Valid @RequestBody PostdoctorFundsQueryForm postdoctorFundsQueryForm);

    /**
     *  导出经费结余信息列表
     *
     * @param postdoctorFundsExportForm
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportPostdoctorFundsSurplusList")
    void exportPostdoctorFundsSurplusList(@ModelAttribute PostdoctorFundsExportForm postdoctorFundsExportForm, HttpServletRequest request, HttpServletResponse response);

    /**
     *  分页查询博士后项目经费使用记录信息列表
     *
     * @param postdoctorFundsRecordQueryForm 经费信息ID
     * @return
     */
    @PostMapping(value = path+"/getPostdoctorFundsRecordList")
    Result<IPage<PostdoctorFundsRecordVo>> getPostdoctorFundsRecordList(@Valid @RequestBody PostdoctorFundsRecordQueryForm postdoctorFundsRecordQueryForm);

    /**
     *  导出博士后项目经费使用记录信息列表
     *
     * @param postdoctorFundsExportForm 导出条件
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportPostdoctorFundsRecordList")
    void exportPostdoctorFundsRecordList(@ModelAttribute PostdoctorFundsExportForm postdoctorFundsExportForm, HttpServletRequest request, HttpServletResponse response);

    /**
     *  根据经费信息ID获取博士后信息
     *
     * @param id  经费信息ID
     * @return
     */
    @GetMapping(value = path+"/getPostdoctorByFundId/{id}")
    Result<PostdoctorFundsInfoVo> getPostdoctorByFundId(@PathVariable long id);

    /**
     *  根据经费使用信息ID获取经费使用记录信息
     * @param id
     * @return
     */
    @GetMapping(value = path+"/getRecodeById/{id}")
    Result<PostdoctorFundsInfoVo> getRecodeById(@PathVariable long id);
}
