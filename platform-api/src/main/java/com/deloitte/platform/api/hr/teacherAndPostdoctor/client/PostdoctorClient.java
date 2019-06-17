package com.deloitte.platform.api.hr.teacherAndPostdoctor.client;

import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;


/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  Postdoctor控制器接口
 * @Modified :
 */
public interface PostdoctorClient {

    public static final String path="/hr/postdoctor";


    /**
     *  POST---新增
     * @param file
     * @return
     */
    @PostMapping(value = path+"/add")
    Result add(MultipartFile file);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     *
     * @param  id
     * @param   postdoctorForm
     * @return
     */
    @PatchMapping(value = path+"/pull/{id}")
    Result updatePull(@PathVariable(value="id") long id, @Valid @RequestBody PostdoctorForm postdoctorForm);

    /**
     *  根据ID修改维护博士后出站退站信息
     *
     * @param id
     * @param  postdoctorPushForm
     * @param  type
     * @return
     */
    @PatchMapping(value = path+"/push/{id}")
    Result updatePush(@PathVariable(value="id") long id, @Valid @RequestBody PostdoctorPushForm postdoctorPushForm,@RequestParam Integer type);

    /**
     *  根据ID修改维护博士后延期信息
     *
     * @param id
     * @param postdoctorDelayForm
     * @return
     */
    @PatchMapping(value = path+"/delay/{id}")
    Result updateDelay(@PathVariable(value="id") long id, @Valid @RequestBody PostdoctorDelayForm postdoctorDelayForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PostdoctorInfoVo> get(@PathVariable(value="id") long id);

    /**
     *  通过博士后编号搜索获取信息
     *
     * @param code  博士后编号
     * @return
     */
    @GetMapping(value = path+"/getPostdoctorByCode")
    Result<PostdoctorInfoVo> getPostdoctorByCode(@RequestParam(required = true) String code,@RequestParam Integer status);

    /**
     *  POST----分页条件查询博士后列表
     *
     * @param  postdoctorQueryForm 搜索条件
     * @return
     */
    @PostMapping(value = path+"/getPostdoctorList")
    Result<IPage<PostdoctorVo>> getPostdoctorList(@Valid @RequestBody PostdoctorQueryForm postdoctorQueryForm,@RequestParam Integer type);

    /**
     *  条件导出博士后信息列表
     *
     * @param postdoctorExportForm 条件
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportPostdoctorList")
    void exportPostdoctorList(@ModelAttribute  PostdoctorExportForm postdoctorExportForm, HttpServletRequest request, HttpServletResponse response,@RequestParam Integer type);

    /**
     * 分页条件查询博士后列表（到期提醒）
     *
     * @param postdoctorQueryForm
     * @return
     */
    @PostMapping(value = path+"/getPostdoctorExpireList")
    Result<IPage<PostdoctorExpireVo>> getPostdoctorExpireList(@Valid @RequestBody PostdoctorQueryForm postdoctorQueryForm);

    /**
     *  导出博士后列表（到期提醒）
     *
     * @param postdoctorExportForm  搜索条件
     */
    @GetMapping(value = path+"/exportPostdoctorExpireList")
    void exportPostdoctorExpireList(@ModelAttribute PostdoctorExportForm postdoctorExportForm, HttpServletRequest request, HttpServletResponse response);

    /**
     * 合同到期提醒，发送代办
     *
     * @return
     */
    @PostMapping(value = path+"/sendExpireToUser")
    Result sendExpireToUser(@RequestBody PostdoctorSearchForm msg);

    /**
     *  条件搜索下拉框选项初始化
     *
     * @return
     */
    @GetMapping(value = path+"/getInitTermSearch")
    Result<HashMap<String,Object>> getInitTermSearch();

    /**
     *  搜索查询博士后信息
     *
     * @param postdoctorSearchForm 搜索条件
     * @return
     */
    @PostMapping(value = path+"/getPostdoctorInfo")
    Result<IPage<PostdoctorSearchVo>> getPostdoctorInfo(@Valid @RequestBody PostdoctorSearchForm postdoctorSearchForm);

    /**
     *  博士后信息管理进站/出站导出
     *
     * @param postdoctorExportForm  导出条件
     * @param request
     * @param response
     * @param type
     */
    @GetMapping(value = path+"/export")
    void export(@ModelAttribute PostdoctorExportForm postdoctorExportForm, HttpServletRequest request, HttpServletResponse response, @RequestParam Integer type);

    /**
     *  根据职工编号code获取职工自助博士后进站/出站信息
     *
     *  @param  userCode  人员信息code（非必填，当前登录用户）
     *  @param  type  查看类型  1进站信息，2出站信息
     * @return
     */
    @ApiOperation(value = "根据职工编号code获取职工自助博士后进站/出站信息", notes = "根据指定职工编号code获取职工自助博士后进站/出站信息")
    @GetMapping(value = path+"/getPostdoctorByUserCode")
    Result<PostdoctorSelfVo> getPostdoctorByUserCode(@RequestParam(required = false) String userCode,@RequestParam Integer type);
}
